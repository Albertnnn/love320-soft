 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.label;

import java.util.Map;

/** 
 * @ClassName: LabelAction 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-8 下午09:41:50 
 *  处理标签类action
 */
public interface LabelAction {
	public Map<String,Object> action(Map<String,Object> data);
}
