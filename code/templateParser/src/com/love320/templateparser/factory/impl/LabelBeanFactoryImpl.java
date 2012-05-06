 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.factory.impl;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.love320.templateparser.factory.LabelBeanFactory;
import com.love320.templateparser.factory.entity.LabelBean;

/** 
 * @ClassName: LabelBeanFactoryImpl 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-6 下午08:33:41 
 *  
 */
public class LabelBeanFactoryImpl implements LabelBeanFactory {

	private String configPath; //标签配置文件路径
	private Element DOCROOT ;//标签配置文件
	
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
		
		SAXReader sax = new SAXReader();
		try {
			Document document= sax.read(configPath);
			DOCROOT = document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.love320.templateparser.factory.LabelBeanFactory#get(java.lang.String)
	 */
	@Override
	public LabelBean get(String labelName) {
		LabelBean labelBean = new LabelBean();//实例标签信息装载体
		//配置文件获取xml信息
		Element labelElement = (Element) DOCROOT.selectSingleNode("/labels/label[@name='"+labelName+"']");
		if(labelElement != null){
			//获取 xml信息
			String name = labelElement.attributeValue("name");
			String parameters = labelElement.elementText("parameters");
			String template = labelElement.elementText("template");
			String classpath = labelElement.elementText("classpath");
			String note = labelElement.elementText("note");
			
			//设置LabelBean信息
			labelBean.setName(name);
			labelBean.setParameters(parameters);
			labelBean.setTemplate(template);
			labelBean.setClasspath(classpath);
			labelBean.setNote(note);
		}else{
			labelBean.setName(labelName);
		}
		
		//打印
		/*System.out.println(labelBean.getName());
		System.out.println(labelBean.getParameters());
		System.out.println(labelBean.getTemplate());
		System.out.println(labelBean.getClasspath());
		System.out.println(labelBean.getNote());*/
		
		return labelBean;
	}
}
