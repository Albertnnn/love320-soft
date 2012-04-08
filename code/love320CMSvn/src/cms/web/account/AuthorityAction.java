/**
 * Copyright (c) 2010-2011 love320.com
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
import cms.dao.HibernateUtils;
import cms.entity.account.Authority;
import cms.entity.account.Role;
import cms.service.account.AuthorityManager;
import cms.web.CrudActionSupport;

//@Results( { @Result(name = CrudActionSupport.RELOAD, location = "archives.action", type = "redirect") })

@Results({
	@Result(name = "RELOAD" , location = "authority.action" ,type = "redirect")
})
public class AuthorityAction extends CrudActionSupport<Authority> {

	private Long id;//
	private Page page = new Page<Authority>(10);//
	
	private Authority entity;
	private AuthorityManager am;
	private List<Long> checkedAuthIds;//页面中钩选的权限id列表
	
	@Override
	public String delete() throws Exception {
		am.delete(id);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "authority","forward"));
		
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
			page.setOrder("DESC");
		}
		
		page = am.searchAuthority(page,filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = am.getAuthority(id);
		}else{
			entity = new Authority();
		}
	}

	@Override
	public String save() throws Exception {
		//根据页面上的checkbox 整合Role的Authorities Set.
		am.save(entity);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "authority","closeCurrent"));

		return null;
	}

	public Authority getModel() {
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
	public void setAm(AuthorityManager am) {
		this.am = am;
	}

	public List<Long> getCheckedAuthIds() {
		return checkedAuthIds;
	}

	public void setCheckedAuthIds(List<Long> checkedAuthIds) {
		this.checkedAuthIds = checkedAuthIds;
	}
	
	
	

}
