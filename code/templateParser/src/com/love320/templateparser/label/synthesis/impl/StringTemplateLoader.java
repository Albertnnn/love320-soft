 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.label.synthesis.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.TemplateLoader;

/** 
 * @ClassName: StringTemplateLoader 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-10 下午11:04:29 
 *  
 */
public class StringTemplateLoader implements TemplateLoader {
	  private static final String DEFAULT_TEMPLATE_KEY = "_default_template_key";  
      private Map templates = new HashMap();  

      public StringTemplateLoader(String defaultTemplate) {  
          if (defaultTemplate != null && !defaultTemplate.equals("")) {  
              templates.put(DEFAULT_TEMPLATE_KEY, defaultTemplate);  
          }  
      }  

      public void AddTemplate(String name, String template) {  
          if (name == null || template == null || name.equals("")  
                  || template.equals("")) {  
              return;  
          }  
          if (!templates.containsKey(name)) {  
              templates.put(name, template);  
          }  
      }  

      public void closeTemplateSource(Object templateSource)  
              throws IOException {  

      }  

      public Object findTemplateSource(String name) throws IOException {  
          if (name == null || name.equals("")) {  
              name = DEFAULT_TEMPLATE_KEY;  
          }  
          return templates.get(name);  
      }  

      public long getLastModified(Object templateSource) {  
          return 0;  
      }  

      public Reader getReader(Object templateSource, String encoding)  
              throws IOException {  
          return new StringReader((String) templateSource);  
      }  
}
