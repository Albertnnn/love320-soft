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
import cms.entity.account.Guestbook;
import cms.service.account.GuestbookManager;
import cms.web.CrudActionSupport;
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "guestbook.action", type = "redirect") })
public class GuestbookAction extends CrudActionSupport<Guestbook> {
	
	
	private Long id;
	private Long[] ids;//批量修改用的ids
	private Guestbook entity;
	private GuestbookManager guestbookM ;
	private Page page = new Page<Guestbook>(10);
	

	@Override
	public String delete() throws Exception {
		guestbookM.Delete(id);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "guestbook","forward"));
		
		return null;
	}
	
	public String deleteIds() throws Exception {
		String delmsg = "成功删除留言:";//记录成功删除信息
		//开始批量删除
		for(Long delId : ids){
			guestbookM.Delete(delId);
			delmsg += delId + ",";
		}
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(delmsg, "guestbook","forward"));
		
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
		
		page = guestbookM.searchArchives(page, filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(id != null){
			entity = guestbookM.getGuestbook(id);
		}else{
			entity = new Guestbook();
		}
		
	}

	@Override
	public String save() throws Exception {
		guestbookM.Save(entity);

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "guestbook","closeCurrent"));

		return null;
	}

	public Guestbook getModel() {
		// TODO Auto-generated method stub
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
	public void setGuestbookM(GuestbookManager guestbookM) {
		this.guestbookM = guestbookM;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	
	
	

}
