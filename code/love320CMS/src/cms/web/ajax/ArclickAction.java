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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import cms.entity.account.Archives;
import cms.service.account.ArchivesManager;

import com.opensymphony.xwork2.ActionSupport;


public class ArclickAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id ;//文档ID
	
	private ArchivesManager am;
	
	@Override
	public String execute() throws Exception {
		 //读取请求的参数    
       // HttpServletRequest request = ServletActionContext.getRequest();  
        
        //获取原始的PrintWriter对象,以便输出响应结果,而不用跳转到某个试图    
        HttpServletResponse response = ServletActionContext.getResponse();    
        
        PrintWriter out =  response.getWriter();   
        
        //文档点击增加
        Archives entity = am.getArchives(id);//获取对象单体
        
        entity.setClick(entity.getClick()+1);//修改点击数+1
        
        am.SaveAcrhivesEntity(entity);//保存对象
        
        //System.out.println("点击数为:"+entity.getClick());
        
        out.println("<!--\r\ndocument.write(\""+entity.getClick()+"\");\r\n-->\r\n");
        
        //out.println("点击数为:"+entity.getClick());
        
		return null;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	@Autowired
	public void setAm(ArchivesManager am) {
		this.am = am;
	}


	
	
	
}
