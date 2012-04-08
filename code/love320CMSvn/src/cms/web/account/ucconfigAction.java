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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.RiaJsonObject;

import com.opensymphony.xwork2.ActionSupport;

public class ucconfigAction extends ActionSupport {
	
	private String api;
	private String ip;
	private String key;
	private String appid;
	private String connect;
	

	@Override
	public String execute() throws Exception {
		File ucconfigfile = new ClassPathResource("ucenter.properties").getFile();
		InputStream in = new FileInputStream(ucconfigfile);
		Properties properties = new Properties();
		  try {
				properties.load(in);
				api = properties.getProperty("UC_API");
				ip = properties.getProperty("UC_IP");
				key = properties.getProperty("UC_KEY");
				appid = properties.getProperty("UC_APPID");
				connect = properties.getProperty("UC_CONNECT");
			} catch (Exception e) {
				e.printStackTrace();
			}
		return super.execute();
	}
	
	public void save(){
		
		try {
			File databasePropertiesFile = new ClassPathResource("ucenter.properties").getFile();
			Properties properties = new Properties();
			properties.put("UC_API", api);
			properties.put("UC_IP", ip);
			properties.put("UC_KEY", key);
			properties.put("UC_APPID", appid);
			properties.put("UC_CONNECT", connect);
			OutputStream outputStream = new FileOutputStream(databasePropertiesFile);
	        properties.store(outputStream, "ucenter.properties");
	        outputStream.close();
			
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			out.println(new RiaJsonObject().getAjaxObject("保存成功", "ucconfig","forward"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getConnect() {
		return connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}
	
	
	
}
