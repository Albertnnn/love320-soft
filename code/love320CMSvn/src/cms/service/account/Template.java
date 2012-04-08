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
import java.io.StringReader;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;

import com.google.common.collect.Lists;

import cms.entity.account.TagLabel;
import cms.pageList.entity.PageContent;

@Component
public class Template {

	private PageList pageList; // 页面信息装载体
	private TagLabelManager tagLabelManager; // 标签解析工厂
	private ConfigManager cm;// 系统配置文件工厂
	private SpecialLabelsManager slm;//特殊标签处理

	int numint = 1;// 防止同一时间内多个线程使用同一临时缓存文件

	public PageContent analyticalTmp(String tmp, String Wtmp,
			PageContent pagecontent) throws IOException {
		
		//内容装载体加入系统环境
		pagecontent.setConfigFile(cm.getConfigFile());

		// 模板原文件路径
		tmp = this.getClass().getResource("/").getPath() + "../template" + tmp;
		
		//特殊标签处理 并返回处理后的文件路径
		String slmtmp = slm.analyticalTmp(tmp);
		if(slmtmp != null) tmp = slmtmp;

		// 模板生成文件路径
		Wtmp = this.getClass().getResource("/").getPath() + "../content/html/"
				+ Wtmp;

		// 防止在同一时间多个线程使用同一个临时缓存文件和防止整数超出
		if (numint > 999) {
			numint = 1;// 归零
		}
		// 临时缓存文件
		String Ctmp = this.getClass().getResource("/").getPath()
				+ "../content/sysCache/templateCache/tempcache"
				+ new Date().getTime() + "I" + (numint++) + ".tmp";

		// 模板文件路径不能为空
		if (tmp == null)
			return pagecontent;
		// System.out.println("."+tmp);
		// 加载并实例化模板
		File tmpFile = new File(tmp);
		// 文件是否存在
		if (!tmpFile.exists()){
			return pagecontent;// tmpFile.createNewFile();
			// System.out.println(">."+Wtmp);
		}

		InputStreamReader tmpread = new InputStreamReader(new FileInputStream(
				tmpFile), "UTF-8");
		// System.out.println(">>."+Wtmp);
		// System.out.println("缓存文件路径："+Ctmp);
		OutputStreamWriter CtmpW = new OutputStreamWriter(new FileOutputStream(
				Ctmp), "UTF-8");
		// 加入文件缓存
		BufferedReader bufferedReader = new BufferedReader(tmpread);
		BufferedWriter bufferedWriter = new BufferedWriter(CtmpW);
		// 加入解析模板文件被调用的环境(*网站路径与编码)
		//bufferedWriter.write("<%@ page contentType=\"text/html;charset=UTF-8\" %><%@ include file=\"/common/htmltop.jsp\" %>");
		// bufferedWriter
		// .write("<%@ page contentType=\"text/html;charset=UTF-8\" %>");

		// 开始读取
		String lineTXT = null;

		while ((lineTXT = bufferedReader.readLine()) != null) {
			// System.out.println(lineTXT );
			pageList = ItemTag(pageList, lineTXT, bufferedWriter, pagecontent);
		}
		// System.out.println("out\n"+bufferedWriter.toString());

		// 关闭文件流
		bufferedWriter.close();
		bufferedReader.close();
		CtmpW.close();
		tmpread.close();

		// //打开系统配置文件，是不为开发模式
		// Document document = cm.getDocument();
		// Node nd = document.getElementsByTagName("mode").item(0);
		// //System.out.println("开发模式："+nd.getAttributes().getNamedItem("value").getNodeValue());
		//		

		// 模板文件是否存在，存在跨过，不存在创建
		File tmpTestfile = new File(Wtmp);
		File CtmpFile = new File(Ctmp);// 临时缓存文件
		if ((!tmpTestfile.exists())||(cm.getConfigFile().getMode().trim().equals("1"))) {
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

			// System.out.println("创建模板解析文件:"+Wtmp);
		}

		// 删除临时缓存文件
		if(CtmpFile.exists()){
			CtmpFile.delete();
		}
		
		//删除特殊标签解析程序临时使用文件
		File slmtmpFile = new File(slmtmp);
		if(slmtmpFile.exists()){
			slmtmpFile.delete();
		}

		return pagecontent;
	}

	// 标记解析（一行信息）
	private PageList ItemTag(PageList pageList, String strTag,
			BufferedWriter bufferedWriter, PageContent pagecontent)
			throws IOException {

		// 一行多标签解析方法,一行中可以出现多个标签，标签前后可以有字符和字符串,但标签体内 不可以换行
		strTag = tagStrRow(strTag, pagecontent);

		bufferedWriter.write(strTag);
		bufferedWriter.newLine();
		return pageList;
	}

	// 标记解析(一行中多个标记)
	private String tagStrRow(String strTag, PageContent pagecontent) {

		// String strTag = "sdfdsf{love320_第一个/} sdfsdfds";

		// System.out.println("开始字符:"+strTag);

		// 装载解析后的字符串
		String newStrRow = "";

		// 以"{love320_"分解成字符串组
		String[] newStrTags = strTag.split("\\{love320_");
		
		for (String varStr : newStrTags) {

			if (varStr.indexOf("/}") != -1) {
			    //System.out.println("有标记："+varStr);
				String[] varStrS = varStr.split("/}");
				if (varStrS.length == 2) {
					 //System.out.println("标记体："+varStrS[0].toString().trim());
					// System.out.println("字符串:"+varStrS[1].toString().trim());

					newStrRow += TagStr(varStrS[0].toString().trim(),
							pagecontent);
					newStrRow += varStrS[1].toString().trim();//字符串
				} else {
					// System.out.println("标记体:"+varStrS[0].toString().trim());
					newStrRow += TagStr(varStrS[0].toString().trim(),
							pagecontent);
				}
			} else {
				// System.out.println("字符串:"+varStr);
				newStrRow += varStr;
			}

		}

		// System.out.println();
		// System.out.println("结束字符");

		return newStrRow;
	}

	// 从数据中查找标记信息
	private String TagStr(String strTag, PageContent pagecontent) {
		// System.out.println("提取的:"+strTag);
		// 解析以ID为索引的信息
		TagLabel taglabel = tagLabelManager.getTagbel(strTag.trim());
		if (taglabel != null) {
			strTag = pageList.AnalyticLabel(strTag, pagecontent);
		}

		return strTag;
	}

	@Autowired
	public void setTagLabelManager(TagLabelManager tagLabelManager) {
		this.tagLabelManager = tagLabelManager;
	}

	@Autowired
	public void setCm(ConfigManager cm) {
		this.cm = cm;
	}

	@Autowired
	public void setSlm(SpecialLabelsManager slm) {
		this.slm = slm;
	}
	
	@Autowired
	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}
	
	

}
