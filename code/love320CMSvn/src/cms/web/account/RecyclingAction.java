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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.RiaJsonObject;
import cms.dao.HibernateUtils;
import cms.entity.account.Arcatt;
import cms.entity.account.Archives;
import cms.entity.account.Authority;
import cms.service.ServiceException;
import cms.service.account.ArcattManager;
import cms.service.account.ArchivesManager;
import cms.service.account.RecyclingManager;
import cms.service.account.Typeunit;
import cms.web.CrudActionSupport;
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "recycling.action", type = "redirect") })
public class RecyclingAction extends CrudActionSupport<Archives> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RecyclingManager rm;
	
	private Page<Archives> page = new Page<Archives>(20);//页文章条数
	
	private Long id;
	private Long[] ids; //批量删除文档id

	@Override
	public String delete() throws Exception {
		rm.delete(id);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "recycling","forward"));
		
		return null;
	}
	
	public String deleteIds() throws Exception {
		String delmsg = "成功删除文档:";//记录成功删除信息
		//开始批量删除
		for(Long delId : ids){
			try {
				rm.delete(delId);
				delmsg += delId + ",";
			} catch (ServiceException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(delmsg, "recycling","forward"));
		
		return null;
	}
	
	public String reduction() throws IOException{
		rm.reduction(id);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("恢复成功", "recycling","forward"));
		
		return null;
	}
	
	public String reductionIds() throws Exception {
		String delmsg = "成功恢复文档:";//记录成功删除信息
		//开始批量删除
		for(Long delId : ids){
			try {
				rm.reduction(delId);
				delmsg += delId + ",";
			} catch (ServiceException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(delmsg, "recycling","forward"));
		
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		
		filters.add(new PropertyFilter("EQI_arcrank","-2"));
		
		page = rm.searchArchives(page, filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Archives getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	public void setRm(RecyclingManager rm) {
		this.rm = rm;
	}

	public Page<Archives> getPage() {
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
