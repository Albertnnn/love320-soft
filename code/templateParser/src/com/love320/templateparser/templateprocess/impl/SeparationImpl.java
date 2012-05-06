/**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.templateprocess.impl;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.love320.templateparser.templateprocess.Separation;

/**
 * @ClassName: SeparationImpl
 * @Description: TODO
 * @author love320.com
 * @date 2012-5-3 下午09:42:49
 * 
 */
public class SeparationImpl implements Separation {

	private String tagNamehead = "\\{love320_";
	private String tagNamefoot = "/}";

	public void setTagNamehead(String tagNamehead) {
		this.tagNamehead = tagNamehead;
	}

	public void setTagNamefoot(String tagNamefoot) {
		this.tagNamefoot = tagNamefoot;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.love320.templateparser.templateprocess.Separation#getXML(java.lang
	 * .String)
	 */
	@Override
	public String getXML(String str) {
		Document document = DocumentHelper.createDocument();//初始化xml
		Element rootElement = document.addElement("root");//设置根目录 root
		rootElement.addElement("site").setText("www.love320.com");//添加版权 
		Element contentElement = rootElement.addElement("content");//装载模板信息
		for (String singStr : str.split("\r\n")) {//遍例每一条信息
			 tagStrRow(singStr,contentElement);//分析并解析信息
			 addContent("/r/n", 3,contentElement);//换行标记
		}
		return document.asXML();//返回xml字符串
	}

	/**
	 * 分析一条字符串有多少个标签并分离出来 ，方法为二分法
	 * */
	private void tagStrRow(String str, Element contentElement) {
		String[] strS = str.split("\\{" + tagNamehead);//二分法以 tagNamehead 内容分解
		if (strS.length > 1) { //字条串中有存在标签
			for (String singStr : strS) {
				if(singStr.indexOf(tagNamefoot+"}") != -1){//提取标签名
					String[] singStrS = singStr.split(tagNamefoot+"}");
					if (singStrS.length == 2) {
						addContent(singStrS[0], 2,contentElement);
					}else{
						addContent(singStrS[0], 2,contentElement);
					}
				}else{
					addContent(singStr, 1,contentElement);
				}
			}
		} else {
			addContent(str, 1,contentElement);
		}

	}

	//写入xml格式的文档
	private void addContent(String str,int type,Element contentElement){
		switch (type) {
		case 1:
			Element singStringElement = contentElement.addElement("item");
			singStringElement.addAttribute("type", "string");
			singStringElement.addCDATA(str);
			break;
		case 2:
			Element singLalbelElement = contentElement.addElement("item");
			singLalbelElement.addAttribute("type", "label");
			singLalbelElement.setText(str);
			break;
		case 3:
			Element singSymbolsElement = contentElement.addElement("item");
			singSymbolsElement.addAttribute("type", "symbols");
			singSymbolsElement.setText(str);
			break;
		default:
			break;
		}
	}
	 
}
