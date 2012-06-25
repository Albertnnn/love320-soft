 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.label;

import java.io.File;
import java.net.URI;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.love320.templateparser.label.impl.LabelBeanDaoImpl;
import com.love320.templateparser.util.AppPath;

/** 
 * @ClassName: LabelBeanDao 
 * @Description: TODO
 * @author love320.com
 * @date 2012-6-24 下午06:59:24 
 *  
 */
public class LabelBeanDao extends LabelBeanDaoImpl {

	@Override
	protected String appPath(String confpath) {
		URI uripath = new File(confpath).toURI();
		return uripath.toString();
	}


	
	
	
}
