 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.label;

/** 
 * @ClassName: ClassForTemplate 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-9 下午11:00:30 
 *  模板引擎接口
 */
public interface ClassForTemplate {
	public String process(Object data,String template);//数据与模板合成
}
