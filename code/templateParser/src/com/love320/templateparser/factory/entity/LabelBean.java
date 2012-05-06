 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.factory.entity;

import java.util.Map;

/** 
 * @ClassName: LabelBean 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-6 下午08:13:59 
 *  标签对应 的信息对象
 */
public class LabelBean {
	private String name ;//标签名
	private String parameters;//参数
	private String template;//模板内容
	private String classpath;//标签类型
	private String note;//备注
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
	public String getClasspath() {
		return classpath;
	}
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	
}
