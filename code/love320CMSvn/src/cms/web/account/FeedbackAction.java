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
import cms.entity.account.Feedback;
import cms.service.account.FeedBackManager;
import cms.web.CrudActionSupport;

@Results( { @Result(name = CrudActionSupport.RELOAD, location = "feedback.action", type = "redirect")})
public class FeedbackAction extends CrudActionSupport<Feedback> {
	
	//后台评论管理
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long[] ids;//批量处理ids 

	private FeedBackManager fbm;
	
	private Page page = new Page<Feedback>(10);//评论页条数10
	
	private Feedback entity;
	
	
	@Override
	public String delete() throws Exception {
		fbm.delete(id);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "feedback","forward"));
		
		return null;
	}
	
	public String deleteIds() throws Exception {
		String delmsg = "成功删除评论:";//记录成功删除信息
		for(Long delId : ids){
			fbm.delete(delId);
			delmsg += delId + ",";
		}
	
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(delmsg, "feedback","forward"));
		
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
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		
		page = fbm.searchArchives(page, filters);
		
		return SUCCESS;
		
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = fbm.getEntity(id);
		}else{
			entity = new Feedback();
		}
	}

	@Override
	public String save() throws Exception {
		fbm.save(entity);
		return RELOAD;
	}

	public Feedback getModel() {
		return entity;
	}
	
	@Autowired
	public void setFbm(FeedBackManager fbm) {
		this.fbm = fbm;
	}

	public Page getPage() {
		return page;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	
	
	

}
