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
import cms.entity.account.OnlineOrder;
import cms.service.account.OrderManager;
import cms.service.account.ServicesManager;
import cms.web.CrudActionSupport;

@Results({
	@Result(name= CrudActionSupport.RELOAD,location="order.action",type="redirect")
})
public class OrderAction extends CrudActionSupport<OnlineOrder> {
	
	private Long id;
	private Long[] ids;//批量修改用的ids
	private Page page = new Page<OnlineOrder>(10);//
	private OnlineOrder entity ;//在线订单对象
	private OrderManager om;//在线订单管理对象
	private ServicesManager sm;//服务层工厂


	@Override
	public String delete() throws Exception {
		om = sm.getOrderManager();//生产在线订单管理对象
		om.delete(id);//删除

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "order","forward"));
		
		return null;
	}
	
	public String deleteIds() throws Exception {
		om = sm.getOrderManager();//生产在线订单管理对象
		String delmsg = "成功删除订单:";//记录成功删除信息
		//开始批量删除
		for(Long delId : ids){
			om.delete(delId);
			delmsg += delId + ",";
		}
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(delmsg, "order","forward"));
		
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());//
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		//设置默认排序
		if(!page.isOrderBySetted()){
			page.orderBy("id");
			page.order(page.DESC);
		}
		
		om = sm.getOrderManager();//生产在线订单管理对象
		
		page = om.search(page,filters);//获取搜索列表
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			om = sm.getOrderManager();//生产在线订单管理对象
			entity = om.getEntity(id);//获取订单对象
		}else{
			entity = new OnlineOrder();//实例化对象
		}
		
	}

	@Override
	public String save() throws Exception {
		
		om = sm.getOrderManager();//生产在线订单管理对象
		
		om.save(entity);//保存
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "order","closeCurrent"));

		return null;
	}

	public OnlineOrder getModel() {
		return entity;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}

	public Page getPage() {
		return page;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	
	
}
