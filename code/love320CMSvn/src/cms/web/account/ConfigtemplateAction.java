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
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.web.struts2.Struts2Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cms.bean.RiaJsonObject;
import cms.service.account.ConfigManager;
import cms.service.account.ConfigTemplateManager;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;
import cms.service.ov.ConfigItem;
import cms.service.ov.ConfigTemplate;
import cms.service.ov.ConfigValue;

@Result(name="RELOAD",location="configtemplate.action" ,type = "redirect")
public class ConfigtemplateAction extends ActionSupport {
		
	
	private ConfigTemplateManager ctm ;//配置文件模板管理类
	private Document document ;
	private List<ConfigItem> configs = Lists.newArrayList();
	private ConfigTemplate ct;
	
	@Override
	public String execute() throws Exception {
		return show();
	}
	
	
	//显示配置文件
	private String show(){
		document = ctm.getConfig();
		//System.out.println(document);
		
		Class ctObject = ConfigTemplate.class;

		Field[] fields = ctObject.getDeclaredFields();
		
		//System.out.println("数为:"+fields.length);
		
		for(Field field:fields){
			//System.out.println("field:"+field.getName());
			Node nd = document.getElementsByTagName(field.getName()).item(0); 
			addconfigs(nd,field.getName());
		}	
		return SUCCESS;
	}
	
	private void addconfigs(Node nd,String nameform){
		//System.out.println("nl:"+nd.getAttributes().getNamedItem("value"));
		//System.out.println("nl:"+nd.getAttributes().getNamedItem("name"));
		ConfigItem cibasehost = new ConfigItem();
		cibasehost.setName(nd.getAttributes().getNamedItem("name").getNodeValue());
		cibasehost.setNameform(nameform);
		cibasehost.setValue(nd.getAttributes().getNamedItem("value").getNodeValue());
		cibasehost.setExplain(nd.getAttributes().getNamedItem("explain").getNodeValue());
		configs.add(cibasehost);
	}


	//保存配置文件
	public String save() throws IOException{
		document = ctm.getConfig();
		
		Class ctObject = ConfigTemplate.class;

		Method[] methods = ctObject.getDeclaredMethods();
		
		//System.out.println("数为:"+fields.length);
		
		for(Method method:methods){
			//System.out.println("Method:"+method.getName());
			//过滤，只要get的方法
			if(method.getName().substring(0, 3).equals("get")){
				//System.out.println(method.getName());
				try {
					//System.out.println(method.getName().substring(3).toLowerCase());
					//System.out.println(method.invoke(cv));
					document = savevalue(method.getName().substring(3).toLowerCase(),method.invoke(ct).toString(),document);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		ctm.saveDocument(document);
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "configtemplate","forward"));

		return null;
	}

	private Document savevalue(String name,String value,Document doc){
		Node nd = doc.getElementsByTagName(name).item(0);
		nd.getAttributes().getNamedItem("value").setNodeValue(value);
		return doc;
	}

	@Autowired
	public void setCtm(ConfigTemplateManager ctm) {
		this.ctm = ctm;
	}


	public Document getDocument() {
		return document;
	}


	public List<ConfigItem> getConfigs() {
		return configs;
	}


	public ConfigTemplate getCt() {
		return ct;
	}


	@Autowired
	public void setCt(ConfigTemplate ct) {
		this.ct = ct;
	}

	
	
	
}
