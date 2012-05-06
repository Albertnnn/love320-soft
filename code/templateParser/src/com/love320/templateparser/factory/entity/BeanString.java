 /**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.factory.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * @ClassName: Bean 
 * @Description: TODO
 * @author love320.com
 * @date 2012-4-15 下午04:59:50 
 *  
 */
public class BeanString {
	String name = ""; //bean 的id
	String className = "";//对应的类
	String scope = "";//实例模式
	List<String[]> refList = new ArrayList<String[]>();//注入的对象
	List<String[]> valueList =  new ArrayList<String[]>();//常量集
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public List<String[]> getRefList() {
		return refList;
	}
	public void setRefList(List<String[]> refList) {
		this.refList = refList;
	}
	public List<String[]> getValueList() {
		return valueList;
	}
	public void setValueList(List<String[]> valueList) {
		this.valueList = valueList;
	}

	
	
	
	
}
