/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.member;

import org.springframework.beans.factory.annotation.Autowired;

import cms.entity.account.Member;
import cms.service.account.MemberManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class registerAction extends ActionSupport implements ModelDriven<Member> {

	private Member entity;
	private MemberManager mm;
	
	public Member getModel() {
		return entity;
	}

	@Override
	public String execute() throws Exception {
		
		return super.execute();
	}

	@Autowired
	public void setMm(MemberManager mm) {
		this.mm = mm;
	}

}
