/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.html;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.octo.captcha.service.CaptchaService;

import cms.entity.account.Archives;
import cms.entity.account.Feedback;
import cms.service.account.ArchivesTypeManager;
import cms.service.account.FeedBackManager;
import cms.service.account.PageList;
import cms.web.CrudActionSupport;

@Results( { @Result(name = "RELOAD", location = "article-${id}.html", type = "redirect")})
public class FeedbackAction extends CrudActionSupport<Feedback> {
	
	private Feedback entity;
	private Long id;
	
	private FeedBackManager fbm;
	private ArchivesTypeManager atm;
	
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		entity = new Feedback();
	}


	//保存评论
	public String save() throws Exception {
		
		//验证码
		boolean flag = false;
		 try {
	            HttpServletRequest request = Struts2Utils.getRequest();
	            String captchaID = request.getSession().getId();
	            String captchaValue = request.getParameter("j_captcha");
	            ApplicationContext context = WebApplicationContextUtils
	                    .getWebApplicationContext(Struts2Utils.getSession()
	                            .getServletContext());
	            CaptchaService captchaService = (CaptchaService) context
	                    .getBean("captchaService");
	            flag = captchaService
	                    .validateResponseForID(captchaID, captchaValue);
	        } catch (Exception e) {
	            flag = false;
	        }
	        
	    
	    if(flag){//通过验证码
		
		entity.setId(null);//设置对象ID为空(用于增加评论)
		
		Archives archives = atm.getArchivesManager().getArchives(id);//获取关联文档对象
		
//		System.out.println("评论栏目名："+archives.getArctype().getTypename());
//		
//		System.out.println("评论栏目数据模型名："+archives.getArctype().getEntitymode().getModeName());
	
		entity.setArchives(archives);	//绑定关联文档对象
		
		fbm.save(entity);//保存（给文档新增一条评论）
			
		entity.setId(id);//利用entity对象的ID做中转到对应的文档中 id -> entity.id
		
	    }
		
		return "RELOAD";
	}
	

	@Autowired
	public void setFbm(FeedBackManager fbm) {
		this.fbm = fbm;
	}
	
	
	

	public Feedback getModel() {
		return entity;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Feedback getEntity() {
		return entity;
	}

	@Autowired
	public void setAtm(ArchivesTypeManager atm) {
		this.atm = atm;
	}

	
	
	
	
}
