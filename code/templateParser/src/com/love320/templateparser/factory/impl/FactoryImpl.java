/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.factory.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.dom4j.Element;
import org.dom4j.Node;

import com.love320.templateparser.cache.Cache;
import com.love320.templateparser.factory.Factory;

/*
 * 工厂
 * */

public class FactoryImpl implements Factory {

	private Cache cache;//缓存
	private String cacheKey = "WWW.LOVE320.COMKeyksdjfksdjglksjdfkdhsgksdjfkljsdlkfjsdlkfj>";//缓存建值(头)
	private Element docroot ;
	
	public FactoryImpl(Element docroot) {
		this.docroot = docroot;
	}
	
	//工厂初始化
	public FactoryImpl factoryInit(){
		
		//设置缓存
		cache = (Cache)procreationXML("cache");

		return this;
	}

	@Override
	public Object getbean(String beanName) {
		Object object = cache.getObject(cacheKey+beanName);//从缓存只取对象
		if(object != null){
			return object ;
		}else{
			object = procreationXML(beanName);//以ID名实例对象
			cache.putObject(cacheKey+beanName, object);//放缓存中
			return object;
		}
		
	}
	
	//递归生成对象
	private Object procreationXML(String beanName){
		Node node = docroot.selectSingleNode("/beans/bean[@id='"+beanName+"']/@class");
		Object object = null;
		try {
			object = Class.forName(node.getText()).newInstance();//实例化当前类
			
			List ls = docroot.selectNodes("/beans/bean[@id='"+beanName+"']/property");//获取当前实例依赖类
			for(int i = 0 ; i < ls.size();i++){
				Element element = (Element) ls.get(i);
				String propertyName = element.attributeValue("name");//注入beanName
				Node propertyBeanNode = docroot.selectSingleNode("/beans/bean[@id='"+beanName+"']/property[@name='"+propertyName+"']/ref/@bean");
		
				if(propertyBeanNode != null){
					Object refObject = null;
					
					if(cache != null){
						refObject = getbean(propertyBeanNode.getText());//有缓存
					}else{
						refObject = procreationXML(propertyBeanNode.getText());//无缓存
					}
					
					//反射注入
					Method method = object.getClass().getMethod("set"+propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1),refObject.getClass());
					method.invoke(object, refObject);
				}else{
					Node valueNode = docroot.selectSingleNode("/beans/bean[@id='"+beanName+"']/property[@name='"+propertyName+"']/value");
					if(valueNode != null){
						//反射注入
						
						try {
			                Double.parseDouble(valueNode.getText().trim());
			                Method method = object.getClass().getMethod("set"+propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1),int.class);
							method.invoke(object, Integer.parseInt(valueNode.getText().trim()));
			                //数字
			            } catch(NumberFormatException e) {
			                //字符串
			            	Method method = object.getClass().getMethod("set"+propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1),valueNode.getText().getClass());
							method.invoke(object, valueNode.getText());
			            }
					}else{
						//System.out.println("<valueNode>"+valueNode);
					}
				}
			}
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
	}

	public void setCache(Cache cache) {
		
		this.cache = cache;
	}
	
	
	
}
