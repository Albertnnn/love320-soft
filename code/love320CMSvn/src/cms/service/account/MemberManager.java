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

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.ucenter.util.XMLHelper;

import cms.dao.account.MemberDao;
import cms.entity.account.Member;

@Service
public class MemberManager {
	private MemberDao memberDao;
	private UcenterClientManager ucCM;
	

	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}


	@Transactional(readOnly=true)
	public Page searchArchives(Page page, List<PropertyFilter> filters) {
		return memberDao.findPage(page, filters);
	}

	@Transactional(readOnly=true)
	public Member getEntity(Long id) {
		return memberDao.get(id);
	}

	@Transactional
	public void save(Member entity) {
		
		//uc注册与修改
		if(entity.getId() == null){
			ucCM.register(entity.getUserid(), entity.getPwd(), entity.getEmail());//新增
		}else{
			ucCM.edit(entity.getUserid(), entity.getPwd(), entity.getEmail());//修改
		}
		
		memberDao.save(entity);
	
	}

	@Transactional
	public void deleteId(Long id) {
		memberDao.delete(id);
	}

	@Transactional
	public Member findPhone(String phone) {
		List<Member> memberList = memberDao.find(Restrictions.eq("phone",phone));
		if(memberList.size() > 0 ){
			return memberList.get(0);
		}else{
			return null;
		}
	}

	@Transactional(readOnly=true)
	public boolean validationUserName(String userid) {
		List<Member> memberList = memberDao.find(Restrictions.eq("userid",userid));
		if(memberList.size() == 0){
			return true;
		}else{
			return false;
		}

	}

	@Transactional(readOnly=true)
	public boolean login(Member entity) {
		List<Member> memberList = memberDao.find(Restrictions.eq("userid",entity.getUserid()));
		if(memberList.size() > 0){
			if(entity.getPwd().equals(memberList.get(0).getPwd())){
				HttpSession userSession = Struts2Utils.getSession();
				//userSession.setAttribute("userId", entity.getUserid());
				userSession.setAttribute("mid", memberList.get(0).getId());
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}

	@Transactional
	public List<Member> listPhoneUser() {
		List<Member> memberList = memberDao.find(Restrictions.isNotNull("phone"));
		return memberList;
	}

	@Transactional
	public Member getSessionEntity() {   
		
		if(Struts2Utils.getSession().getAttribute("mid") != null){
			String userIdSession = Struts2Utils.getSession().getAttribute("mid").toString();
			return getEntity(new Long(userIdSession));	
		}else{
			return null;
		}
		
	}

	public String idsToPhoneStr(Long[] sendPhoneNum) {
		
		/*
		if (sendPhoneNum.length > 0) {
			mcsEntity.setPhone(sendPhoneNum[0]);// 一个手机号
			for (int i = 1; i < sendPhoneNum.length; i++) {// 多个手机号
				mcsEntity.setPhone(mcsEntity.getPhone() + ","
						+ sendPhoneNum[i]);
			}
		}
		*/
		String strPhone = "";

		if(sendPhoneNum.length > 0 ){
			strPhone = getEntity(sendPhoneNum[0]).getPhone();
			for(int i = 1; i < sendPhoneNum.length ; i++){
				Member entity = getEntity(sendPhoneNum[i]);
				strPhone = strPhone + "," + entity.getPhone();
				smsNum(entity);
			}
		}
		
		return strPhone;
	}
	
	@Transactional
	public boolean smsNum(Member entity){
		
		Date nowDate = new Date();
		//初始化时间
		if(entity.getSmsDate() == null){
			entity.setSmsDate(nowDate);
		}
		
		if(entity.getSmsDate().getDay() == nowDate.getDay()){
			entity.setSmsint(entity.getSmsint()+1);
		}else{
			entity.setSmsDate(nowDate);
			entity.setSmsint(0);
		}
		save(entity);
		
		return true;
	}
	
	public Member ucUserTOSysUser(String username,String password){
		String userStr = ucCM.userGet(username);
		LinkedList ucuser = XMLHelper.uc_unserialize(userStr);
		Member entity = new Member();
		//[3, love320cms, love320cms@love320.com, 0]
		entity.setUserid(ucuser.get(1).toString());
		entity.setUname(ucuser.get(1).toString());
		entity.setPwd(password);
		entity.setEmail(ucuser.get(2).toString());
		entity.setSex(Member.Sex.secret);
		return entity;
	}


	@Autowired
	public void setUcCM(UcenterClientManager ucCM) {
		this.ucCM = ucCM;
	}

	@Transactional
	public void setSessionEntity(String userName) {
		List<Member> memberList = memberDao.find(Restrictions.eq("userid",userName));
		if(memberList.size() > 0){
				HttpSession userSession = Struts2Utils.getSession();
				userSession.setAttribute("mid", memberList.get(0).getId());	
		}
	}


	public String loginout() {
		HttpSession userSession = Struts2Utils.getSession();
		userSession.removeAttribute("mid");
		return ucCM.synlogout();
	}
	
	public void ucloginout() {
		HttpSession userSession = Struts2Utils.getSession();
		userSession.removeAttribute("mid");
	}
	
	
}
