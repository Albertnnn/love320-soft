 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.factory.entity;

/** 
 * @ClassName: Label 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-6 下午03:50:59 
 *  标签实例
 */
public class Label {
	
	private String name ;//标签名
	private String template;//模板内容
	private String type;//标签类型
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
