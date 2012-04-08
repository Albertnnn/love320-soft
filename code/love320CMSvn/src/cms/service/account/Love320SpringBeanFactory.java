/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.account;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Love320SpringBeanFactory implements BeanFactoryAware{
	
	private BeanFactory beanFactory;

	@Autowired
	public void setBeanFactory(BeanFactory bf) throws BeansException {
		beanFactory = bf ;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}
	
	

}
