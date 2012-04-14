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

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.PageRIA;
import cms.bean.RiaJsonObject;
import cms.entity.account.MemberType;
import cms.service.account.MemberTypeManager;
import cms.web.CrudActionSupport;

public class MembertypeAction extends CrudActionSupport<MemberType> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long[] ids;
	private Page page = new Page<MemberType>(10);
	private MemberTypeManager mtm ;
	private MemberType entity;

	@Override
	public String delete() throws Exception {
		mtm.deleteId(id);
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "membertype","forward"));
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		page = mtm.searchList(page,filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = mtm.getEntity(id);
		}else{
			entity = new MemberType();
		}
		
	}

	@Override
	public String save() throws Exception {
		mtm.save(entity);
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "membertype","closeCurrent"));
		return null;
	}

	public MemberType getModel() {
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	public void setMtm(MemberTypeManager mtm) {
		this.mtm = mtm;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Page getPage() {
		return page;
	}
	
	
	

}
