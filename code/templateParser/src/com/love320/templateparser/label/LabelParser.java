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
 * @ClassName: LabelParser 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-6 下午07:00:16 
 *  标签分析器接口
 */
public interface LabelParser {
	public Label get(Label label);//分析单个标签对象
	
	public List<Label> get(Label... labels);//分析多个标签对象
	
	public List<Label> get(List<Label> labeList);//分析List<Label>标签对象
}
