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
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.PageRIA;
import cms.bean.RiaJsonObject;
import cms.entity.account.Sgpage;
import cms.service.account.ArctypeManager;
import cms.service.account.HTMLAllPageManager;
import cms.service.account.SgpageManager;
import cms.service.account.Typeunit;
import cms.web.CrudActionSupport;

@Results( { @Result(name = CrudActionSupport.RELOAD, location = "sgpage.action", type = "redirect") })
public class SgpageAction extends CrudActionSupport<Sgpage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;//
	private Long[] ids;//批量修改用的ids
	private Sgpage entity;//
	private List<Typeunit> checkedarctypes;//栏目
	private HTMLAllPageManager hapm;	//静态页面生成管理
	private SgpageManager sgpageManager;//
	private ArctypeManager arctypeManager;//
	private Page page = new Page<Sgpage>(10);

	@Override
	public String delete() throws Exception {
		sgpageManager.delete(id);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "sgpage","forward"));
		
		return null;
	}
	
	public String deleteIds() throws Exception {
		String delmsg = "成功删除单页面:";//记录成功删除信息
		//开始批量删除
		for(Long delId : ids){
			sgpageManager.delete(delId);
			delmsg += delId + ",";
		}
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(delmsg, "sgpage","forward"));
		
		return null;
	}


	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		page = sgpageManager.searchArchives(page, filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = sgpageManager.getSgpage(id);
		}else{
			entity = new Sgpage();
		}
	}

	@Override
	public String save() throws Exception {
		sgpageManager.Save(entity);
		hapm.htmlOneSgpage(entity.getId());//生成单页面的静态页面

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "sgpage","closeCurrent"));
		
		return null;
	}

	public Sgpage getModel() {
		return entity;
	}


	@Override
	public String input() throws Exception {
		checkedarctypes = arctypeManager.getALLTypeunit();
		checkedarctypes.remove(0);//删除 顶级栏目
		return INPUT;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	public void setSgpageManager(SgpageManager sgpageManager) {
		this.sgpageManager = sgpageManager;
	}

	public Page getPage() {
		return page;
	}

	public List<Typeunit> getCheckedarctypes() {
		return checkedarctypes;
	}
	
	@Autowired
	public void setArctypeManager(ArctypeManager arctypeManager) {
		this.arctypeManager = arctypeManager;
	}

	@Autowired
	public void setHapm(HTMLAllPageManager hapm) {
		this.hapm = hapm;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	
	
	
	
}
