 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.factory.impl;

import com.love320.templateparser.factory.AppFactory;
import com.love320.templateparser.factory.Factory;

/** 
 * @ClassName: FactoryServiceImpl 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-6 下午09:44:42 
 *  
 */
public class FactoryServiceImpl implements Factory {

	/* (non-Javadoc)
	 * @see com.love320.templateparser.factory.Factory#getbean(java.lang.String)
	 */
	@Override
	public Object getbean(String beanName) {
		AppFactory appfactory = AppFactory.getAppFactory();
		Factory factory = appfactory.getFactory();
		return factory.getbean(beanName);
	}

}
