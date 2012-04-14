/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.account;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cms.service.ov.ConfigValue;


@Component
public class ConfigManager {

	private String configFilePath ; //配置文件路径
	private ConfigValue configFile ;//获取配置文件装载类ConfigValue
	private XMLConfigManager XMLcm;//XML操作类
	
	ConfigManager(){
		configFilePath = this.getClass().getResource("/").getPath()+"configLove320.xml";
		//System.out.println("配置文件:"+configFilePath);
	}
	
	//初始化配置文件
	private void initializationconfigfile(){
		try {			
			Document document = XMLcm.newDocument();
			//根结点
			Element root = document.createElement("root");
			
			document.appendChild(root);
			
			//子结点basehost网址
			Element basehost = document.createElement("basehost");
			basehost.setAttribute("name", "网址");
			basehost.setAttribute("value", "http://www.love320.com");
			basehost.setAttribute("explain", "访问网站的网址");
			//加入根的子结点
			root.appendChild(basehost);
			
			//子结点cmspath网站路径
			Element cmspath = document.createElement("cmspath");
			cmspath.setAttribute("name", "网站路径");
			cmspath.setAttribute("value", "/");
			cmspath.setAttribute("explain", "网站在空间的路径，默认可以不填");
			//加入根的子结点
			root.appendChild(cmspath);
			
			//子结点indexname首页名称
			Element indexname = document.createElement("indexname");
			indexname.setAttribute("name", "网站名称");
			indexname.setAttribute("value", "冰迪网络");
			indexname.setAttribute("explain", "网站的站名");
			//加入根的子结点
			root.appendChild(indexname);
			
			//子结点adminemail网站邮箱
			Element adminemail = document.createElement("adminemail");
			adminemail.setAttribute("name", "网站邮箱");
			adminemail.setAttribute("value", "admin@love320.com");
			adminemail.setAttribute("explain", "网站的管理员的联系邮箱");
			//加入根的子结点
			root.appendChild(adminemail);
			
			//子结点mode网站运行模式
			Element mode = document.createElement("mode");
			mode.setAttribute("name", "网站运行模式");
			mode.setAttribute("value", "0");//0为普通,1为开发,5(文档静态)
			mode.setAttribute("explain", "网站的运行模式，0为服务器模式，1为开发模式(制作网站)，3为伪静态模式,5为文档静态(推荐)");
			//加入根的子结点
			root.appendChild(mode);
			
			//网站关键字
			Element keywords = document.createElement("keywords");
			keywords.setAttribute("name", "网站关键字");
			keywords.setAttribute("value","冰迪网站，冰迪设计，冰迪系统");
			keywords.setAttribute("explain", "设置网站的关键字");
			//加入根的子结点
			root.appendChild(keywords);
			
			//站点描述
			Element description = document.createElement("description");
			description.setAttribute("name", "站点描述");
			description.setAttribute("value", "冰迪设计,冰迪作品,开源项目");
			description.setAttribute("explain", "设置站点描述");
			//加入根的子结点
			root.appendChild(description);			
			
			//网站版权信息powerby
			Element powerby = document.createElement("powerby");
			powerby.setAttribute("name", "网站版权信息");
			powerby.setAttribute("value", "冰迪系统版权所有@love320.com-2011");
			powerby.setAttribute("explain", "设置网站版权所有权-love320.com");
			//加入根的子结点
			root.appendChild(powerby);
			
			//网站备案号beian
			Element beian = document.createElement("beian");
			beian.setAttribute("name", "网站备案号");
			beian.setAttribute("value", "湘XXXXXXXX");
			beian.setAttribute("explain", "设置网站备案号");
			//加入根的子结点
			root.appendChild(beian);
			
			//生成分页数htmlgeneration
			Element htmlgeneration = document.createElement("htmlgeneration");
			htmlgeneration.setAttribute("name", "生成分页数");
			htmlgeneration.setAttribute("value", "3");
			htmlgeneration.setAttribute("explain", "生成分页数");
			//加入根的子结点
			root.appendChild(htmlgeneration);
			
			
			//写入配置文件
			setWConfig(document);
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	private ConfigValue fillconfigFile(){
		
		Document document = getConfig();
		
		configFile = new ConfigValue();
		
		Class cvObject = ConfigValue.class;
		

		Method[] methods = cvObject.getDeclaredMethods();
		
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
	
	
	public ConfigValue getConfigFile() {
		return fillconfigFile();
	}

	@Autowired
	public void setXMLcm(XMLConfigManager xMLcm) {
		XMLcm = xMLcm;
	}	
	
	
	
}
