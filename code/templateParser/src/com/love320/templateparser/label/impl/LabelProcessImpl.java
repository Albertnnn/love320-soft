 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.label.impl;

import com.love320.templateparser.factory.Factory;
import com.love320.templateparser.factory.LabelBeanFactory;
import com.love320.templateparser.factory.entity.LabelBean;
import com.love320.templateparser.label.LabelProcess;

/** 
 * @ClassName: LabelProcessImpl 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-6 下午07:48:26 
 *  
 */
public class LabelProcessImpl implements LabelProcess {
	
	private LabelBeanFactory labelBeanFactory;//标签对应 的信息工厂
	private Factory factoryService;//bean工厂
	
	public void setLabelBeanFactory(LabelBeanFactory labelBeanFactory) {
		this.labelBeanFactory = labelBeanFactory;
	}

	public void setFactoryService(Factory factoryService) {
		this.factoryService = factoryService;
	}

	/* (non-Javadoc)
	 * @see com.love320.templateparser.label.LabelProcess#get(java.lang.String)
	 */
	@Override
	public String get(String labelName) {
		LabelBean labelBean = labelBeanFactory.get(labelName);//以标签名获取标签信息对象装载体
		System.out.println("labelName:"+labelName);
		System.out.println("bean:"+labelBean.getBean());
		System.out.println(factoryService.getbean(labelBean.getBean()));
		System.out.println(labelBean.getTemplate());
		return labelBean.getTemplate();
	}

}
