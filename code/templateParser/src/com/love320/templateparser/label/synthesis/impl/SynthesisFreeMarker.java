 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.label.synthesis.impl;

import java.io.IOException;
import java.io.StringWriter;

import com.love320.templateparser.label.Synthesis;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/** 
 * @ClassName: SynthesisFreeMarker 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-10 下午10:58:45 
 *  
 */
public class SynthesisFreeMarker implements Synthesis {

	/* (non-Javadoc)
	 * @see com.love320.templateparser.templateprocess.Synthesis#get(java.lang.Object, java.lang.String)
	 */
	@Override
	public String process(Object object, String templateSource) {
		
		Configuration cfg = new Configuration();    
        cfg.setTemplateLoader(new StringTemplateLoader(templateSource));    
        cfg.setDefaultEncoding("UTF-8");    
   
        Template template = null;    
		try {
			template = cfg.getTemplate("");
			 StringWriter writer = new StringWriter();    
		     template.process(object, writer);    
		     return writer.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		return null;
	}

}
