/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.account;

import java.io.PrintWriter;
import java.util.List;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.PageRIA;
import cms.bean.RiaJsonObject;
import cms.dao.HibernateUtils;
import cms.entity.account.Arcatt;
import cms.entity.account.Archives;
import cms.service.ServiceException;
import cms.service.account.ArcattManager;
import cms.service.account.ArchivesManager;
import cms.service.account.Typeunit;
import cms.web.CrudActionSupport;
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "archives.action", type = "redirect") })
public class ArchivesAction extends CrudActionSupport<Archives> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 *	属性
	 */
	private Long id;
	private Long[] ids;//批量修改用的ids
	private Archives entity;//文章
	private Page<Archives> page = new Page<Archives>(20);//页文章条数
	private List<Typeunit> checkedarctypes;//栏目
	private List<Arcatt> acratts;
	private List<Long> checkedAcattIds;//页面中钩选的文档属性id列表
	
	public void setId(Long id){
		this.id = id;
	}

	//文章管理manager
	private ArchivesManager archivesManager;
	
	//文档属性管理manager
	private ArcattManager arcattManager;
	
	@Autowired
	public void setArchivesManager(ArchivesManager archivesManager) {
		this.archivesManager = archivesManager;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		
		//搜索时选择顶级栏目，则remove些PropertyFilter对象
		for(PropertyFilter pf : filters){
			if(pf.getPropertyName().equals("typeid")){
				if(pf.getMatchValue().toString().trim().equals("0")){
					//System.out.println(pf.getMatchValue());
					filters.remove(pf);//删除栏目条件(因 是0,)
					break;//跳出,因为处理了filters对象,破坏了FOT的循环次数的条件
				}
			}
		}
		
		
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		
		filters.add(new PropertyFilter("GEI_arcrank","0"));
		page = archivesManager.searchArchives(page, filters);
		
		checkedarctypes = archivesManager.getTypeunit();
		//checkedarctypes.remove(0);//删除顶级栏目
		
		//System.out.println("size:"+page.getPageSize());
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = archivesManager.getArchives(id);
		}else{
			entity = new Archives();
		}	
	}
	
	@Override
	public String delete() throws Exception {
		try {
			archivesManager.DeleteArchives(id);
			//addActionMessage("删除文章成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			//addActionMessage("删除文章失败");
		}
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "archives","forward"));
		
		return null;
	}
	
	public String deleteIds() throws Exception {
		String delmsg = "成功删除文档:";//记录成功删除信息
		//开始批量删除
		for(Long delId : ids){
			archivesManager.DeleteArchives(delId);
			delmsg += delId + ",";
		}
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(delmsg, "archives","forward"));
		
		return null;
	}

	@Override
	public String input() throws Exception {
		checkedarctypes = archivesManager.getTypeunit();
		checkedarctypes.remove(0);//删除顶级栏目
		acratts = arcattManager.getAll();
		checkedAcattIds = entity.getflagIds();
		//System.out.println("文档属性:"+checkedAcattIds.size());
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//根据页面上的checkbox 整合Arcatt的Arcatt Set.
		HibernateUtils.mergeByCheckedIds(entity.getFlagList(),checkedAcattIds, Arcatt.class);
		archivesManager.SaveAcrhives(entity);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "archives","closeCurrent"));

		return null;
	}

	public Archives getModel() {
		return entity;
	}

	public Page<Archives> getPage() {
		return page;
	}

	public List<Typeunit> getCheckedarctypes() {
		return checkedarctypes;
	}
	
	@Autowired
	public void setArcattManager(ArcattManager arcattManager) {
		this.arcattManager = arcattManager;
	}

	public List<Arcatt> getAcratts() {
		return acratts;
	}

	public List<Long> getCheckedAcattIds() {
		return checkedAcattIds;
	}

	public void setCheckedAcattIds(List<Long> checkedAcattIds) {
		this.checkedAcattIds = checkedAcattIds;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	
	
	

}
