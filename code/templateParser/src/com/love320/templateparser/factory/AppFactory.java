/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.love320.templateparser.factory.bean.BeanString;
import com.love320.templateparser.factory.impl.FactoryBeanImpl;
import com.love320.templateparser.factory.impl.FactoryImpl;

public class AppFactory {
	
	/*单实例化
	 * */
	private AppFactory(){};//私有构造
	
	private static class AppFactorySing{
		private static AppFactory appFactory = new AppFactory();
	}
	
	public static AppFactory getAppFactory(){
		return AppFactorySing.appFactory;
	}
	
	//单实例化 end
	private Factory factory;
	private String conPath ;
	private static Element DOCROOT ;
	
	public void setConPath(String conPath) {
		this.conPath = conPath;
		xmlStr();
	}
	
	private void xmlStr(){
		SAXReader sax = new SAXReader();
		try {
			Document document= sax.read(conPath);
			DOCROOT = document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public Factory getFactory() {
		if(factory == null){
			//factory = new FactoryImpl(DOCROOT).factoryInit();//实例化工厂类并初始化
			BeanString bs = new BeanString();
			bs.setName("beanfactory");
			bs.setClassName("com.love320.templateparser.factory.bean.impl.BeanFactoryImpl");
			factory = new FactoryBeanImpl().factoryInit(bs,DOCROOT);//实例化工厂类并初始化
		}
		return factory;
	}
	
	
	
	
}
