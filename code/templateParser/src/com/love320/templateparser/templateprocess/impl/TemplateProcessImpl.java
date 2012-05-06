package com.love320.templateparser.templateprocess.impl;

import java.io.File;
import java.util.List;

import com.love320.templateparser.factory.entity.Label;
import com.love320.templateparser.io.FileToString;
import com.love320.templateparser.label.XMLToLabel;
import com.love320.templateparser.templateprocess.Separation;
import com.love320.templateparser.templateprocess.TemplateProcess;

/**
 * 模板核心处理器
 * */
public class TemplateProcessImpl implements TemplateProcess {
	
	private FileToString fileToString ;//文件获取对象
	private String templateDir = ""; //模板目录
	private Separation separation;//分离器(内容与标签分离)
	private XMLToLabel xmlToLabel;//转换器，从xml数据格式转换为label对象

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	public void setFileToString(FileToString fileToString) {
		this.fileToString = fileToString;
	}

	public void setSeparation(Separation separation) {
		this.separation = separation;
	}
	
	public void setXmlToLabel(XMLToLabel xmlToLabel) {
		this.xmlToLabel = xmlToLabel;
	}

	@Override
	public String get(String path) {
		return get(new File(path));
	}

	@Override
	public String get(File file) {
		String templateFileStr = fileToString.get(file);//从文件中读取内容到字符串中
		String tempXML = separation.getXML(templateFileStr);//分离内容与标签并以xml管理，返回xml字符串
		List<Label> labelList = xmlToLabel.get(tempXML);//从xml数据格式转换为label对象
		
		System.out.println(labelList.size());
		
		return null;
	}

}
