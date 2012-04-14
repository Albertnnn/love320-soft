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
import cms.entity.account.EntityMode;
import cms.service.account.EntityModeManager;
import cms.service.account.ServicesManager;
import cms.web.CrudActionSupport;

@Results({
	@Result(name=CrudActionSupport.RELOAD,location="entitymode.action",type="redirect")
})
public class EntitymodeAction extends CrudActionSupport<EntityMode> {

	private Long id;//
	private EntityMode entity ;//数据模型对象
	private Page page = new Page<EntityMode>(10);//
	private ServicesManager sm ;//服务层工厂对象
	private EntityModeManager emm;//数据模型服务对象
	
	
	@Override
	public String delete() throws Exception {
		emm = sm.getEntityModeManager();//获取数据模型服务对象
		emm.delete(id);//删除对象

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "entitymode","forward"));
		
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {

		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		//设置默认排序方式
		if(!page.isOrderBySetted()){
			page.setOrderBy("id");
			page.setOrder(page.DESC);
		}
		
		emm = sm.getEntityModeManager();//获取数据模型服务对象
	
		page = emm.search(page,filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			emm = sm.getEntityModeManager();//获取数据模型服务对象
			entity = emm.getEntity(id);//获取数据模型对象
		}else{
			entity = new EntityMode();
		}
		
	}

	@Override
	public String save() throws Exception {
		emm = sm.getEntityModeManager();//获取数据模型服务对象
		emm.save(entity);//保存对象

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "entitymode","closeCurrent"));

		return null;
	}

	public EntityMode getModel() {
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Page getPage() {
		return page;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}
	
	

}
