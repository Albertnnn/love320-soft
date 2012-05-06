 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.factory;

import com.love320.templateparser.factory.entity.LabelBean;

/** 
 * @ClassName: LabelBeanFactory 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-6 下午08:29:45 
 *  标签对应 的信息工厂 接口
 */
public interface LabelBeanFactory {
	public LabelBean get(String labelName);
}
