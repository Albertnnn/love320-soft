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

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.RiaJsonObject;
import cms.service.account.HTMLAllPageManager;

import com.opensymphony.xwork2.ActionSupport;


public class HtmlgeneratorAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HTMLAllPageManager hapm;
	
	private String htmlGFiles = null;
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	//一键更新
	public String oneHtmlGPage(){
		
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
		hapm.htmlHomePage();
		return successGetHtmlFiles();
	}
	
	//生成栏目
	public String htmlType(){
		hapm.htmlType();
		return successGetHtmlFiles();
	}
	
	//生成文档
	public String htmlArticle(){
		hapm.htmlArticle();
		return successGetHtmlFiles();
	}
	
	//生成商品
	public String htmlShop(){
		hapm.htmlShop();
		return successGetHtmlFiles();
	}
	
	//生成单页面
	public String htmlSgpage(){
		hapm.htmlSgpage();
		return successGetHtmlFiles();
	}
	
	private String successGetHtmlFiles(){
		
		try {
			PrintWriter out;
			out = Struts2Utils.getResponse().getWriter();
			out.println(new RiaJsonObject().getAjaxObject("操作成功", "","closeCurrent"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	

	@Autowired
	public void setHapm(HTMLAllPageManager hapm) {
		this.hapm = hapm;
	}
	public String getHtmlGFiles() {
		return htmlGFiles;
	}
	
	
	

}
