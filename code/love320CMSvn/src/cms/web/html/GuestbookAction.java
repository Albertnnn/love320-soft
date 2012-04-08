/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;
import cms.entity.account.Guestbook;
import cms.pageList.entity.PageContent;
import cms.service.account.ConfigTemplateManager;
import cms.service.account.GuestbookManager;
import cms.service.account.PageList;
import cms.service.account.ServicesManager;


import com.octo.captcha.service.CaptchaService;
import com.opensymphony.xwork2.ActionSupport;
@Results( { @Result(name = "RELOAD", location = "guestbook.html", type = "redirect")})
public class GuestbookAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id ;//
	private Guestbook entity = new Guestbook();//单体
	private ServicesManager sm;//服务层工厂
	private int pageNo = 1; //列表页分每号
	private ConfigTemplateManager ctm;//系统模板配置文件管理对象
	
	private GuestbookManager gm;//留言服务类
	
	private PageContent pagecontent = new PageContent();

	@Override
	public String execute() throws Exception {
		pageAction();
		return SUCCESS;
	}
	
	//进行页面处理
	private void pageAction(){
		//从系统配置文件中获得模板
		String gbtmpl = ctm.fillconfigFile().getGuestbook().trim();
		//System.out.println("留言板模板文件:"+gbtmpl);
		//tmp.analyticalTmp(gbtmpl,"guestbook.jsp");
			
			pagecontent.getPfs().add(new PropertyFilter("EQI_pageNo",""+pageNo));
			
			try {
				pagecontent = sm.getTemplate().analyticalTmp(gbtmpl,"guestbook.ftl",pagecontent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			
			
	}
	
	//给用户留言
	public String save() throws Exception{
		
		 boolean flag = false;
	        try {
	            HttpServletRequest request = Struts2Utils.getRequest();
	            String captchaID = request.getSession().getId();
	            String captchaValue = request.getParameter("j_captcha");
	            ApplicationContext context = WebApplicationContextUtils
	                    .getWebApplicationContext(Struts2Utils.getSession()
	                            .getServletContext());
	            CaptchaService captchaService = (CaptchaService) context
	                    .getBean("captchaService");
	            flag = captchaService
	                    .validateResponseForID(captchaID, captchaValue);
	        } catch (Exception e) {
	            flag = false;
	        }
		
		if((entity != null)&&(flag)) {
			entity.setIp(org.apache.struts2.ServletActionContext.getRequest().getRemoteAddr());
			gm.Save(entity);
		}
		
		addActionMessage("留言成功!");
		return "RELOAD";
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public void setEntity(Guestbook entity) {
		this.entity = entity;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public Guestbook getEntity() {
		return entity;
	}
	
	
	@Autowired
	public void setCtm(ConfigTemplateManager ctm) {
		this.ctm = ctm;
	}
	
	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}

	@Autowired
	public void setGm(GuestbookManager gm) {
		this.gm = gm;
	}

	public PageContent getPagecontent() {
		return pagecontent;
	}
	
	
	
	
}
