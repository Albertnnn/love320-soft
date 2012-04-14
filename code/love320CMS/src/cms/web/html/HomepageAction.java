/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.html;


import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.w3c.dom.Node;
import cms.pageList.entity.PageContent;
import cms.service.ObjectFile;
import cms.service.account.ConfigManager;
import cms.service.account.ConfigTemplateManager;
import cms.service.account.PageList;
import cms.service.account.ServicesManager;
import cms.service.account.Template;


import com.opensymphony.xwork2.ActionSupport;

public class HomepageAction extends ActionSupport {
		
	/**
	 * MyEclipse自动生成的标记
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 首页显示的内容
	 * */
	private Template tmp ;//加载模板类并解析
	private ServicesManager sm;//服务层工厂
	private ConfigTemplateManager ctm;//系统模板配置文件管理对象
	
	private PageContent pagecontent = new PageContent();
	
	@Override
	public String execute() throws Exception {
		
		pageAction();
		return "homepage";
	}
	
	//进行页面处理
	private void pageAction(){
		
		//从系统配置文件获取首页模板
		//Node nd = cm.getDocument().getElementsByTagName("homepage").item(0);
		//System.out.println("首页模板："+nd.getAttributes().getNamedItem("value").getNodeValue());
		String homepage = ctm.fillconfigFile().getHomepage();	
		
		//模板解析	
		try {
			pagecontent = sm.getTemplate().analyticalTmp(homepage,"homepage.ftl",pagecontent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}		
	
	@Autowired
	public void setTmp(Template tmp) {
		this.tmp = tmp;
	}
	
	@Autowired
	public void setCtm(ConfigTemplateManager ctm) {
		this.ctm = ctm;
	}
	
	public PageContent getPagecontent() {
		return pagecontent;
	}
	
	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}	
	
	
	
	
}

