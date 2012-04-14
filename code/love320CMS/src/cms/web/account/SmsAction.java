/**
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

import cms.bean.PageRIA;
import cms.bean.RiaJsonObject;
import cms.entity.account.SMS;
import cms.service.account.MemberClassSmsManager;
import cms.service.account.SmsManager;
import cms.web.CrudActionSupport;


public class SmsAction extends CrudActionSupport{
	private SmsManager smsM;
	private MemberClassSmsManager mcsm;
	
	private SMS entity;
	private Long id;
	private Long ids[];
	private Page page = new Page<SMS>(10);
	
	public String input(){
		return INPUT;
	}
	
	/**
	 * 定义在show()前执行二次绑定.
	 */
	public void prepareShow() throws Exception {
		prepareModel();
	}
	
	public String show(){
		return "show";
	}

	public void receiving(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			String smsStr = smsM.receiving();
			out.println(new RiaJsonObject().getAjaxObject("接收"+smsStr, "sms","forward"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void balance(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			String smsStr = smsM.balance();
			out.println(new RiaJsonObject().getAjaxObject("短信余额："+smsStr, "sms","forward"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Autowired
	public void setSmsM(SmsManager smsM) {
		this.smsM = smsM;
	}

	@Override
	public String delete() throws Exception {
		smsM.deleteId(id);
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "sms","forward"));
		return null;
	}
	
	public String deleteIds() throws Exception {
		String delmsg = "成功删除文档:";//记录成功删除信息
		for(Long delId :ids){
			smsM.deleteId(delId);
			delmsg += delId + ",";
		}

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(delmsg, "sms","forward"));
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
		
		
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		page = smsM.search(page,filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = smsM.getEntity(id);
		}else{
			entity = new SMS();
		}
	}

	@Override
	public String save() throws Exception {
		
		smsM.save(entity);
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "sms","closeCurrent"));
		return null;
	}

	public Object getModel() {
		return entity;
	}
	
	
	public void processing() throws Exception {
		int numP = mcsm.processing();
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("处理:"+numP+"条", "sms","forward"));
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

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	@Autowired
	public void setMcsm(MemberClassSmsManager mcsm) {
		this.mcsm = mcsm;
	}
	
	
	
	
}
