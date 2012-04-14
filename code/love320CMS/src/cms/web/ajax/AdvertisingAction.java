/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.ajax;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cms.entity.account.Advertising;
import cms.service.account.AdvertisingManager;
import cms.service.account.ServicesManager;

import com.opensymphony.xwork2.ActionSupport;

public class AdvertisingAction extends ActionSupport {
	
	private Long id;
	
	private ServicesManager sm;//服务层工厂
	private AdvertisingManager adm;//广告管理对象
	
	
	@Override
	public String execute() throws Exception {
		adm = sm.getAdvertising();//服务层工厂广告管理对象
		
        //获取原始的PrintWriter对象,以便输出响应结果,而不用跳转到某个试图    
        HttpServletResponse response = ServletActionContext.getResponse();    
        
        PrintWriter out =  response.getWriter(); 
        
        Advertising entity = adm.getEntity(id);
        
        Date theTime = new Date();//当前时间
        
        //广告有效期
        if((entity.getTimeset() == 0)||((entity.getStarttime().getTime() <= theTime.getTime())&&(entity.getEndtime().getTime() >= theTime.getTime()))){
        	out.println("<!--\r\ndocument.write(\""+strToJSstr(entity.getNormbody())+"\");\r\n-->\r\n");
        }else{
        	out.println("<!--\r\ndocument.write(\""+strToJSstr(entity.getExpbody())+"\");\r\n-->\r\n");//过期广告内容
        }
 
        //   ${ctx}/ajax/advertising.action?id=1
        
		return null;
	}
	
	//字符串转换为js字符串
	private String strToJSstr(String str){
		//System.out.println("前:"+str);
		str = str.replace("\"", "\\\"");//替换 "
		str = str.replace("\r", "\\r");//替换 \r
		str = str.replace("\n", "\\n");//替换 \n
		//System.out.println("后:"+str);
		return str;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}
	
	
}
