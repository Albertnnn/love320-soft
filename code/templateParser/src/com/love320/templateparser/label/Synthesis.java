 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.label;

/** 
 * @ClassName: Synthesis 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-10 下午10:57:09 
 *  数据与模板合成
 */
public interface Synthesis {
	public String process(Object object,String templateSource);//合成
}
