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
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.Node;

import com.love320.templateparser.cache.Cache;
import com.love320.templateparser.cache.impl.SysCache;
import com.love320.templateparser.factory.BeanFactory;
import com.love320.templateparser.factory.Factory;
import com.love320.templateparser.factory.bean.BeanString;

/*
 * 工厂
 * */

public class FactoryBeanImpl implements Factory {

	private Cache cache;// 缓存
	private String cacheKey = "WWW.LOVE320.COMKeyksdjfksdjglksjdfkdhsgksdjfkljsdlkfjsdlkfj>";// 缓存建值(头)
	private BeanFactory beanFactory;//生产beanString 对象工厂

	// 工厂初始化
	public FactoryBeanImpl factoryInit(BeanString beanString,Element docroot) {
		//生产bean工厂
		beanFactory = (BeanFactory)procreationBean(beanString);
		BeanFactoryInit(docroot);

		// 设置缓存
		cache = (Cache) procreationBean(beanFactory.getBeanString("cache"));

		return this;
	}
	
	// 反射注入
	private boolean BeanFactoryInit(Element docroot){
		 try {
				Method method = beanFactory.getClass().getMethod("setDocroot",Element.class);
				method.invoke(beanFactory, docroot);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true;
	}

	@Override
	public Object getbean(String beanName) {
		Object object = cache.getObject(cacheKey + beanName);// 从缓存只取对象
		if (object != null) {
			return object;
		} else {
			object = procreationBean(beanFactory.getBeanString(beanName));// 以ID名实例对象
			cache.putObject(cacheKey + beanName, object);// 放缓存中
			return object;
		}

	}

	// 递归生成对象
	private Object procreationBean(BeanString beanString) {
		Object object = null;
		try {
			object = Class.forName(beanString.getClassName()).newInstance();// 实例化当前类

			List<String[]> refList = beanString.getRefList();
			for (int i = 0; i < refList.size(); i++) {
				Object refObject = null;
				String[] refStrs = refList.get(i);
					refObject = getbean(refStrs[1]);// 有缓存
	
					// 反射注入
					 Method method = object.getClass().getMethod("set"+ refStrs[0].substring(0,1).toUpperCase() +  refStrs[0].substring(1),refObject.getClass());
					 method.invoke(object, refObject);
			}

			List<String[]> valueList = beanString.getValueList();
			for (int i = 0; i < valueList.size(); i++) {
				String[] valueStrs = valueList.get(i);
				// 反射注入
				try {
					Double.parseDouble(valueStrs[1]);
					 Method method =object.getClass().getMethod("set"+valueStrs[0].substring(0,1).toUpperCase() + valueStrs[0].substring(1),int.class);
					 method.invoke(object, Integer.parseInt(valueStrs[1].trim()));
					// 数字
				 } catch(NumberFormatException e) {
					// 字符串
					 Method method =object.getClass().getMethod("set"+valueStrs[0].substring(0,1).toUpperCase() + valueStrs[0].substring(1),valueStrs[1].getClass());
					 method.invoke(object, valueStrs[1]);
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
