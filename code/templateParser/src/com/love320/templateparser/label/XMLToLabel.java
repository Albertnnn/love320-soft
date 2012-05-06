 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.label;

import java.util.List;

import com.love320.templateparser.factory.entity.Label;

/** 
 * @ClassName: XMLToLabel 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-6 下午03:59:48 
 *  转换器，从xml数据格式转换为label对象
 */
public interface XMLToLabel {
	public List<Label> get(String xmlstr);//转换处理
}
