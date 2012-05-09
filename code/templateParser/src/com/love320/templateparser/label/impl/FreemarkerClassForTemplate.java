 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.label.impl;

import com.love320.templateparser.label.ClassForTemplate;

/** 
 * @ClassName: FreemarkerClassForTemplate 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-9 下午11:14:00 
 *  
 */
public class FreemarkerClassForTemplate implements ClassForTemplate {

	/* (non-Javadoc)
	 * @see com.love320.templateparser.label.ClassForTemplate#process(java.lang.Object, java.lang.String)
	 */
	@Override
	public String process(Object data, String template) {
		
		return template;
	}

}
