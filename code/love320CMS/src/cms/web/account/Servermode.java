/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.RiaJsonObject;
import cms.service.account.ConfigManager;
import cms.service.account.ServicesManager;
import cms.service.ov.ConfigValue;

import com.opensymphony.xwork2.ActionSupport;

public class Servermode extends ActionSupport {

	private ServicesManager sm;// 服务层工厂

	// 网站运行模式切换
	@Override
	public String execute() throws Exception {
		ConfigManager cm = sm.getConfigManager();// 生产系统设置管理对象
		ConfigValue cv = cm.getConfigFile();// 获取系统配置对象
		String modeStr = cv.getMode().trim();// 运行模式字串符
		String msgmodestr = null;
		File urlrewFile ;
		File urlrewpseudo = null ;
		
		urlrewFile = new File(this.getClass().getResource("/")
				.getPath()
				+ "../urlrewrite.xml"); // URL重写配置文件
		
		//静态模式
		if(modeStr.equals("0")){
			urlrewpseudo = new File(this.getClass().getResource("/")
					.getPath()
					+ "../urlrewrite.xml.html.file"); // URL重写配置文件(静态模式)
			
			addActionMessage("静态模式以启动");
			msgmodestr = "静态模式以启动";
		}
		
		//开发和伪静态模式
		if(modeStr.equals("1")||modeStr.equals("3")){
			urlrewpseudo = new File(this.getClass().getResource("/")
					.getPath()
					+ "../urlrewrite.xml.pseudo.file"); // URL重写配置文件(伪静态模式)
			
			addActionMessage("动态模式以启动(伪静态)");
			msgmodestr = "动态模式以启动(伪静态)";
		}
		
		//伪静态模式+文档静态模式
		if(modeStr.equals("5")){
			urlrewpseudo = new File(this.getClass().getResource("/")
					.getPath()
					+ "../urlrewrite.xml.pseudoandhtml.file"); // URL重写配置文件(伪静态模式)
			
			addActionMessage("文档静态模式以启动");
			msgmodestr = "文档静态模式以启动";
		}
		
		fileCopyFile(urlrewFile,urlrewpseudo);//URL重写配置文件的重写

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(msgmodestr+",操作成功", "",""));

		return null;
	}
	
	//URL重写配置文件的重写
	private void fileCopyFile(File fosFile,File fisFile) throws IOException{
		FileInputStream fis = new FileInputStream(fisFile);//读
		FileOutputStream fos = new FileOutputStream(fosFile);//写
		byte[] bt = new byte[1024];
		int count;
		while((count = fis.read(bt))>0){	//开始读写文件
			fos.write(bt, 0, count);
		}
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}

}
