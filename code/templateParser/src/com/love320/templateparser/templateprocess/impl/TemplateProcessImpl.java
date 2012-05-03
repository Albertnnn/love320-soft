package com.love320.templateparser.templateprocess.impl;

import java.io.File;

import com.love320.templateparser.io.FileToString;
import com.love320.templateparser.templateprocess.Separation;
import com.love320.templateparser.templateprocess.TemplateProcess;

/**
 * 模板核心处理器
 * */
public class TemplateProcessImpl implements TemplateProcess {
	
	private FileToString fileToString ;//文件获取对象
	private String templateDir = ""; //模板目录
	private Separation separation;//分离器(内容与标签分离)

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	public void setFileToString(FileToString fileToString) {
		this.fileToString = fileToString;
	}

	public void setSeparation(Separation separation) {
		this.separation = separation;
	}

	@Override
	public String get(String path) {
		return get(new File(path));
	}

	@Override
	public String get(File file) {
		String templateFileStr = fileToString.get(file);
		System.out.println(">>>"+separation.getXML(templateFileStr));
		
		return null;
	}

}
