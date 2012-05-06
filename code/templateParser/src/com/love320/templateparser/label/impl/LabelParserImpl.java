 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.label.impl;

import java.util.ArrayList;
import java.util.List;

import com.love320.templateparser.factory.entity.Label;
import com.love320.templateparser.label.LabelParser;

/** 
 * @ClassName: LabelParserImpl 
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-6 下午07:04:31 
 *  标签分析器实现类
 */
public class LabelParserImpl implements LabelParser {

	/* (non-Javadoc)
	 * @see com.love320.templateparser.label.LabelParser#get(com.love320.templateparser.factory.entity.Label)
	 */
	@Override
	public Label get(Label label) {//标签分析器
		System.out.println(label.getTemplate());
		return label;
	}

	/* (non-Javadoc)
	 * @see com.love320.templateparser.label.LabelParser#get(com.love320.templateparser.factory.entity.Label[])
	 */
	@Override
	public List<Label> get(Label... labels) {
		List<Label> labeList = new ArrayList<Label>();
		for(Label sing :labels){
			labeList.add(sing);
		}
		return get(labeList);
	}

	/* (non-Javadoc)
	 * @see com.love320.templateparser.label.LabelParser#get(java.util.List)
	 */
	@Override
	public List<Label> get(List<Label> labeList) {
		for(int i = 0 ;i < labeList.size() ;i++){
			labeList.set(i, get(labeList.get(i)));
		}
		return labeList;
	}

}
