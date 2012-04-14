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
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.RiaJsonObject;
import cms.entity.account.Arctype;
import cms.entity.account.EntityMode;
import cms.service.account.ArctypeManager;
import cms.service.account.EntityModeManager;
import cms.service.account.ServicesManager;
import cms.service.account.Typeunit;
import cms.web.CrudActionSupport;
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "arctype.action", type = "redirect") })
public class ArctypeAction extends CrudActionSupport<Arctype> {

	/**
	 * 栏目
	 */
	private static final long serialVersionUID = 1L;
	
	private ArctypeManager arctypeManager;//栏目管理对象
	private Arctype entity;//栏目对象
	private Long id;
	private List<Typeunit> typeList;//栏目列表对象
	private Typeunit typeUnit;//特栏目对象
	private List<EntityMode> emList;//数据模型列表对象
	private Long emid ;//数据模型ID
	
	private ServicesManager sm;//服务层工厂
	
	//获取栏目
	@Override
	public String list() throws Exception {
		arctypeManager = sm.getArctypeManager();//服务层管理工厂生成栏目管理对象
		
		typeList = arctypeManager.getALLTypeunit();
//		for(int i = 0 ;i < typeList.size();i++){
//			System.out.println(typeList.get(i).getTypename());
//		}
//		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
//		//设置默认排序方式
//		if (!page.isOrderBySetted()) {
//			page.setOrderBy("id");
//			page.setOrder(Page.ASC);
//		}
//		page = arctypeManager.searchArctype(page, filters);
		
		typeUnit = arctypeManager.getTypeunit();
		
		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {
		arctypeManager = sm.getArctypeManager();//服务层管理工厂生成栏目管理对象
		arctypeManager.deleteArctype(id);//删除栏目对象

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "arctype","closeCurrent"));
		
		return null;
	}

	@Override
	public String input() throws Exception {
		arctypeManager = sm.getArctypeManager();//服务层管理工厂生成栏目管理对象
		typeList = arctypeManager.getALLTypeunit();//有序栏目
		EntityModeManager emm = sm.getEntityModeManager();//服务工厂生厂数据模型对象
		emList = emm.getAll();//获取所有数据模型对象	
		return INPUT;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			arctypeManager = sm.getArctypeManager();//服务层管理工厂生成栏目管理对象
			entity = arctypeManager.getArctype(id);
			emid = entity.getEntitymode().getId();//设置数据模型ID
		}else{
			entity = new Arctype();
		}
		
	}

	@Override
	public String save() throws Exception {

		EntityModeManager emm = sm.getEntityModeManager();//服务工厂生厂数据模型对象
		EntityMode em = emm.getEntity(emid);//获取数据模型对象
		entity.setEntitymode(em);//设置关联数据模型对象
		
		arctypeManager = sm.getArctypeManager();//服务层管理工厂生成栏目管理对象
		
		//批量生成栏目
		String[] typeNames = entity.getTypename().split(",");
		if((typeNames.length > 0)&&(entity.getId() == null)){
			for(String str :typeNames){
				Arctype strentity = arctypeManager.newcopy(entity);
				strentity.setTypename(str);//设置栏目名
//				System.out.println("栏目名:"+strentity.getTypename());
//				System.out.println("栏目ID:"+strentity.getId());
				arctypeManager.SaveArctype(strentity);
			}
		}else{
			arctypeManager.SaveArctype(entity);//保存对象
		}
		addActionMessage("保存栏目成功");

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "arctype","closeCurrent"));

		return null;
		
	}

	public Arctype getModel(){
		return entity;
	}


	public List<Typeunit> getTypeList() {
		return typeList;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}

	public Long getEmid() {
		return emid;
	}

	public void setEmid(Long emid) {
		this.emid = emid;
	}

	public List<EntityMode> getEmList() {
		return emList;
	}

	public Typeunit getTypeUnit() {
		return typeUnit;
	}
	
	

}
