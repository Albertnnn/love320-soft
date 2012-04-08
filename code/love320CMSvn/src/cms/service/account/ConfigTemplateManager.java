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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import cms.service.ov.ConfigTemplate;
import cms.service.ov.ConfigValue;


@Component
public class ConfigTemplateManager {

	private String configFilePath ; //配置文件路径
	private ConfigTemplate configFile ;//获取配置文件装载类ConfigValue
	private XMLConfigManager XMLcm;//XML操作类
	
	ConfigTemplateManager(){
		configFilePath = this.getClass().getResource("/").getPath()+"configLove320template.xml";
		//System.out.println("配置文件:"+configFilePath);
	}
	
	//初始化配置文件
	private void initializationconfigfile(){
		try {			
			Document document = XMLcm.newDocument();
			//根结点
			Element root = document.createElement("root");
			
			document.appendChild(root);
			
			//子结点homepage首页模板
			Element homepage = document.createElement("homepage");
			homepage = InitTemplateElement(homepage,"网站首页模板","/default/homepage.html", "网站首页的模板文件");
			//加入根的子结点
			root.appendChild(homepage);
			
			//子结点Guestbook模板
			Element guestbook = document.createElement("guestbook");
			guestbook = InitTemplateElement(guestbook,"留言板模板","/default/guestbook.html","留言板模板文件");
			//加入根的子结点
			root.appendChild(guestbook);
			
			//子结点search模板
			Element search = document.createElement("search");
			search = InitTemplateElement(search,"搜索模板","/default/search.html","搜索模板文件");
			//加入根的子结点
			root.appendChild(search);
			
			//errorhome
			Element errorHome = document.createElement("errorhome");
			errorHome = InitTemplateElement(errorHome,"操作失败界面模板","/default/errorHome.html","操作失败界面模板文件");
			root.appendChild(errorHome);
			
			
			/*
			 * 会员
			 * */
			
			Element memberHome = document.createElement("memberhome");
			memberHome = InitTemplateElement(memberHome,"会员界面模板","/default/memberHome.html","会员界面模板文件");
			root.appendChild(memberHome);
			
			Element memberLogin = document.createElement("memberlogin");
			memberLogin = InitTemplateElement(memberLogin,"会员登录模板","/default/memberLogin.html","会员登录模板文件");
			root.appendChild(memberLogin);
			
			Element memberReg = document.createElement("memberreg");
			memberReg = InitTemplateElement(memberReg,"会员注册模板","/default/memberReg.html","会员注册模板文件");
			root.appendChild(memberReg);
			
			Element information = document.createElement("information");
			information = InitTemplateElement(information,"修改个人信息模板","/default/information.html","修改个人信息模板文件");
			root.appendChild(information);
			
			Element textMessaging = document.createElement("textmessaging");
			textMessaging = InitTemplateElement(textMessaging,"发短信模板","/default/textMessaging.html","发短信模板文件");
			root.appendChild(textMessaging);
			
			Element acceptShortMailbox = document.createElement("acceptshortmailbox");
			acceptShortMailbox = InitTemplateElement(acceptShortMailbox,"收短信箱模板","/default/acceptShortMailbox.html","收短信箱模板文件");
			root.appendChild(acceptShortMailbox);
			
			Element theHairShortMailbox = document.createElement("thehairshortmailbox");
			theHairShortMailbox = InitTemplateElement(theHairShortMailbox,"发短信箱模板","/default/theHairShortMailbox.html","发短信箱 模板文件");
			root.appendChild(theHairShortMailbox);
			
			Element classNotice = document.createElement("classnotice");
			classNotice = InitTemplateElement(classNotice,"班级通知模板","/default/classNotice.html","班级通知 模板文件");
			root.appendChild(classNotice);
			
			Element classNoticeIssuedByThe = document.createElement("classnoticeissuedbythe");
			classNoticeIssuedByThe = InitTemplateElement(classNoticeIssuedByThe,"发布班级通知模板","/default/classNoticeIssuedByThe.html","发布班级通知模板文件");
			root.appendChild(classNoticeIssuedByThe);
			
			Element classProject = document.createElement("classproject");
			classProject = InitTemplateElement(classProject,"班级作业模板","/default/classProject.html","班级作业模板文件");
			root.appendChild(classProject);
			
			Element releassClassProject = document.createElement("releassclassproject");
			releassClassProject = InitTemplateElement(releassClassProject,"发布班级作业模板","/default/releassClassProject.html","发布班级作业模板文件");
			root.appendChild(releassClassProject);
			
			Element cchoolPerformance = document.createElement("cchoolperformance");
			cchoolPerformance = InitTemplateElement(cchoolPerformance,"在校表现模板","/default/cchoolPerformance.html","在校表现模板文件");
			root.appendChild(cchoolPerformance);
			
			Element examinationManagement = document.createElement("examinationmanagement");
			examinationManagement = InitTemplateElement(examinationManagement,"考试管理模板","/default/examinationManagement.html","考试管理模板文件");
			root.appendChild(examinationManagement);
			
			Element releaseSchoolPerformance = document.createElement("releaseschoolperformance");
			releaseSchoolPerformance = InitTemplateElement(releaseSchoolPerformance,"发布在校表现模板","/default/releaseSchoolPerformance.html","发布在校表现模板文件");
			root.appendChild(releaseSchoolPerformance);
			
			Element releaseTestScores = document.createElement("releasetestscores");
			releaseTestScores = InitTemplateElement(releaseTestScores,"发布考试成绩模板","/default/releaseTestScores.html","发布考试成绩模板文件");
			root.appendChild(releaseTestScores);
			
			/* 会员 end
			 *  */
			
			
			//写入配置文件
			setWConfig(document);
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Element InitTemplateElement(Element element ,String name, String value ,String explain){
		element.setAttribute("name", name);
		element.setAttribute("value", value);
		element.setAttribute("explain", explain);
		return element;
	}
	
	//写入配置文件
	private void setWConfig(Document doc){
		XMLcm.setWConfig(doc, configFilePath);
	}
	
	//获取配置文件
	public Document getConfig(){
		File isconfigfile = new File(configFilePath);
		if(!isconfigfile.exists()){
			initializationconfigfile();//初始化配置文件
		}
		return XMLcm.getConfig(configFilePath);
	}
	
	//获取配置文件装载类ConfigValue
	public ConfigTemplate fillconfigFile(){
		
		Document document = getConfig();
		
		configFile = new ConfigTemplate();
		
		Class ctObject = ConfigTemplate.class;
		

		Method[] methods = ctObject.getDeclaredMethods();
		
		for(Method method:methods){
			//System.out.println("Method:"+method.getName());
			//过滤，只要get的方法
			if(method.getName().substring(0, 3).equals("set")){
				//System.out.println(method.getName());
				try {
					
					method.invoke(configFile, fffAuxiliary(method.getName().substring(3).toLowerCase()));
					
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		}

		return configFile;
	}
	//辅助fillconfigFile()方法装载变量值
	private String fffAuxiliary(String var ){
		//if(document != null) System.out.println("document对象不为空");
		Document document = XMLcm.getConfig(configFilePath);
		return document.getElementsByTagName(var).item(0).getAttributes().getNamedItem("value").getNodeValue();
	}
	
	//保存配置文件
	public void saveDocument(Document doc){
		//写入配置文件
		setWConfig(doc);
	}


	@Autowired
	public void setXMLcm(XMLConfigManager xMLcm) {
		XMLcm = xMLcm;
	}	
	
	
	
}
