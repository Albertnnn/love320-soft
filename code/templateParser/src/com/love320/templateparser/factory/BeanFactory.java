 /**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.factory;

import com.love320.templateparser.factory.entity.BeanString;

/** 
 * @ClassName: BeanFactory 
 * @Description: TODO
 * @author love320.com
 * @date 2012-4-15 下午04:59:40 
 *  
 */

/**
 * 生产工厂所需要的bean信息对象  接口
 * */

public interface BeanFactory {
	public BeanString getBeanString(String name);
}
