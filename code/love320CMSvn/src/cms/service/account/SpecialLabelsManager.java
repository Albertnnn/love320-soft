/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.account;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cms.pageList.entity.PageContent;
import cms.specialLabel.IncludeLabel;

//特殊标签管理与处理对象
@Component
public class SpecialLabelsManager {

	private String beforeTag = "\\{love320_sys_";// 特殊标签体前缀
	private String afterTag = "sys/}";// 特殊标签体后缀
	private IncludeLabel includeLabel;//引用文件特殊标签处理对象

	int numint = 1;// 防止同一时间内多个线程使用同一临时缓存文件

	public String analyticalTmp(String tmp) throws IOException {

		// 模板原文件路径
		tmp = tmp;
		// 模板生成文件路径
		String Wtmp = this.getClass().getResource("/").getPath()
				+ "../content/sysCache/templateCache/tempcacheSpecial"
				+ new Date().getTime() + "I" + (numint++) + ".tmp";

		// 防止在同一时间多个线程使用同一个临时缓存文件和防止整数超出
		if (numint > 999) {
			numint = 1;// 归零
		}
		// 临时缓存文件
		String Ctmp = this.getClass().getResource("/").getPath()
				+ "../content/sysCache/templateCache/tempcacheSpecial"
				+ new Date().getTime() + "I" + (numint++) + ".tmp";

		// 模板文件路径不能为空
		if (tmp == null)
			return null;
		// System.out.println("."+tmp);
		// 加载并实例化模板
		File tmpFile = new File(tmp);
		// 文件是否存在
		if (!tmpFile.exists()) return null;
		
		InputStreamReader tmpread = new InputStreamReader(new FileInputStream(
				tmpFile), "UTF-8");
		OutputStreamWriter CtmpW = new OutputStreamWriter(new FileOutputStream(
				Ctmp), "UTF-8");
		// 加入文件缓存
		BufferedReader bufferedReader = new BufferedReader(tmpread);
		BufferedWriter bufferedWriter = new BufferedWriter(CtmpW);

		// 开始读取
		String lineTXT = null;

		while ((lineTXT = bufferedReader.readLine()) != null) {
			// System.out.println(lineTXT );
			 ItemTag( lineTXT, bufferedWriter);
		}

		// 关闭文件流
		bufferedWriter.close();
		bufferedReader.close();
		CtmpW.close();
		tmpread.close();	
		
		// 模板文件是否存在，存在跨过，不存在创建
		File tmpTestfile = new File(Wtmp);
		File CtmpFile = new File(Ctmp);// 临时缓存文件
		if (!tmpTestfile.exists()){
			// 更新模板文件
			byte[] buffer = new byte[1024];
			int len;
			FileInputStream fis = new FileInputStream(Ctmp);
			FileOutputStream fos = new FileOutputStream(Wtmp);
			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}

			fis.close();
			fos.close();

			// 删除临时缓存文件
			CtmpFile.delete();
		}

		return Wtmp;
	}

	// 标记解析（一行信息）
	private String ItemTag(String strTag, BufferedWriter bufferedWriter)
			throws IOException {

		// 一行多标签解析方法,一行中可以出现多个标签，标签前后可以有字符和字符串,但标签体内 不可以换行
		strTag = tagStrRow(strTag);

		bufferedWriter.write(strTag);
		bufferedWriter.newLine();
		return null;
	}

	// 标记解析(一行中多个标记)
	private String tagStrRow(String strTag) {

		// String strTag = "sdfdsf{love320_第一个/} sdfsdfds";

		// System.out.println("开始字符:"+strTag);

		// 装载解析后的字符串
		String newStrRow = "";

		// 以"{love320_sys_"分解成字符串组
		String[] newStrTags = strTag.split(beforeTag);
		
		for (String varStr : newStrTags) {
			
			if (varStr.indexOf(afterTag) != -1) {
				//System.out.println("有标记："+varStr);
				String[] varStrS = varStr.split(afterTag);
				if (varStrS.length == 2) {
//				    System.out.println("标记体："+varStrS[0].toString().trim());
//				    System.out.println("字符串:"+varStrS[1].toString().trim());

					newStrRow += TagStr(varStrS[0].toString().trim());
					newStrRow += varStrS[1].toString().trim();
				} else {
					//System.out.println("标记体:"+varStrS[0].toString().trim());
					newStrRow += TagStr(varStrS[0].toString().trim());
				}
			} else {
				newStrRow += varStr;
			}

		}

		return newStrRow;
	}

	// 处理特殊标签，标签类型和参数
	private String TagStr(String strTag) {
		String[] typevalue = strTag.split("=");
		
		if(typevalue[0].trim().equals("include")){
			return include(typevalue[1].trim());
		}
		
		return strTag;
	}
	
	private String include(String strTagValue){
		return includeLabel.processing(strTagValue);
	}

	@Autowired
	public void setIncludeLabel(IncludeLabel includeLabel) {
		this.includeLabel = includeLabel;
	}
	
	
	
	

}
