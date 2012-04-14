/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.ajax.account;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.RiaJsonObject;
import cms.service.account.HTMLAllPageManager;
import cms.service.account.ServicesManager;

import com.opensymphony.xwork2.ActionSupport;

public class HtmlgeneratorajaxAction extends ActionSupport {
	
	private HTMLAllPageManager hapm ;//生成静态页面ajax
	
	private ServicesManager sm ;//服务层工厂
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	//一键更新
	public String oneHtmlGPage(){
		
		hapm = sm.getHtmlAllPageManager();
		
		//生成首页
		hapm.htmlHomePage();
		
		//生成单页面
		hapm.htmlSgpage();
		
		//生成栏目
		hapm.htmlType();
		
		//生成文档
		hapm.htmlArticle();
		
		//生成商品
		hapm.htmlShop();
		
		return successGetHtmlFiles();
	}
	
	//生成首页
	public String htmlHomePage(){
		
		hapm = sm.getHtmlAllPageManager();
		hapm.htmlHomePage();
		return successGetHtmlFiles();
	}
	
	//生成栏目
	public String htmlType(){
		
		hapm = sm.getHtmlAllPageManager();
		hapm.htmlType();
		return successGetHtmlFiles();
	}
	
	//生成文档
	public String htmlArticle(){
		hapm = sm.getHtmlAllPageManager();
		hapm.htmlArticle();
		return successGetHtmlFiles();
	}
	
	//生成商品
	public String htmlShop(){
		hapm = sm.getHtmlAllPageManager();
		hapm.htmlShop();
		return successGetHtmlFiles();
	}
	
	//生成单页面
	public String htmlSgpage(){
		hapm = sm.getHtmlAllPageManager();
		hapm.htmlSgpage();
		return successGetHtmlFiles();
	}
	
	//清空生成提示信息
	public String clearMsg(){
		hapm = sm.getHtmlAllPageManager();
		hapm.setHtmlFileMsg(null);
		return successGetHtmlFiles();
	}
	
	private String successGetHtmlFiles(){
		
		try {
			PrintWriter out;
			out = Struts2Utils.getResponse().getWriter();
			out.println(new RiaJsonObject().getAjaxObject("操作成功", "htmlgeneratorajax","forward"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}

	
	
	
	
}
