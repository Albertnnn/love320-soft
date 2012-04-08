/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.html;

import java.io.IOException;
import java.util.List;

import javassist.expr.NewArray;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.entity.account.Member;
import cms.entity.account.MemberClassNotice;
import cms.pageList.entity.PageContent;
import cms.service.account.ConfigTemplateManager;
import cms.service.account.MemberClassNoticeManager;
import cms.service.account.MemberManager;
import cms.service.account.SmsManager;
import cms.service.account.Template;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;

@Results({@Result(name = "relist", location = "memberclassnotice!list.action", type = "redirect"),
		@Result(name = "relistwork", location = "memberclassnotice!listwork.action", type = "redirect"),
		@Result(name = "reuserlogin", location = "user!login.action", type = "redirect")
	})
public class MemberclassnoticeAction extends ActionSupport {
	
	
	private Template tmp ;//加载模板类并解析
	private ConfigTemplateManager ctm;//系统模板配置文件管理对象
	
	private PageContent pagecontent = new PageContent();
	
	private MemberClassNoticeManager mcnm;
	private MemberManager mm;
	private Member member;
	private MemberClassNotice mcnEntity = new MemberClassNotice();
	private List<MemberClassNotice> mcnList = Lists.newArrayList();
	private List<Member> memberPhone;
	private SmsManager smsM;
	private Long id;
	
	
	@Autowired
	public void setSmsM(SmsManager smsM) {
		this.smsM = smsM;
	}
	
	private Long memberids[];

	@Autowired
	public void setMcnm(MemberClassNoticeManager mcnm) {
		this.mcnm = mcnm;
	}
	
	@Autowired
	public void setMm(MemberManager mm) {
		this.mm = mm;
	}

	@Override
	public String execute() throws Exception {
		return list();
	}
	
	public String list(){
		
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		
		if (member.getMtype().getTypeMark().equals("teacher")) {
			mcnList = member.getMemberClassNoticeList();
		}else{
			mcnList = member.getMemberclassstudent().getClassNotice();
		}
		
		String tmpfileconfig = ctm.fillconfigFile().getClassnotice();	
		pageAction(tmpfileconfig,"classnotice.ftl");//模板渲染
		
		return "classnotice";
	}
	
	public String listwork(){
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		if (member.getMtype().getTypeMark().equals("teacher")) {
			mcnList = member.getMemberClassNoticeList();
		}else{
			mcnList = member.getMemberclassstudent().getClassNotice();
		}

		String tmpfileconfig = ctm.fillconfigFile().getClassproject();	
		pageAction(tmpfileconfig,"classproject.ftl");//模板渲染
		
		return "classproject";
	}
	
	public String input(){
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		memberPhone = mm.listPhoneUser();
		
		String tmpfileconfig = ctm.fillconfigFile().getClassnoticeissuedbythe();	
		pageAction(tmpfileconfig,"classnoticeissuedbythe.ftl");//模板渲染
		
		return "classnoticeissuedbythe";
	}
	
	public String save(){
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		mcnEntity.setMember(member);
		mcnm.save(mcnEntity);
		smsM.sendSms(mm.idsToPhoneStr(memberids), mcnEntity.getContent(), "班级通知");
		
		return "relist";
	}
	
	public String delete(){
		mcnm.delete(id);
		return "relist";
	}
	
	public String inputwork(){
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		memberPhone = mm.listPhoneUser();
		
		String tmpfileconfig = ctm.fillconfigFile().getReleassclassproject();	
		pageAction(tmpfileconfig,"releassclassproject.ftl");//模板渲染
		
		return "releassclassproject";
	}
	
	public String savework(){
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		mcnEntity.setMember(member);
		mcnm.save(mcnEntity);
		smsM.sendSms(mm.idsToPhoneStr(memberids), mcnEntity.getContent(), "布置作业");
		return "relistwork";
	}
	
	public String deletework(){
		mcnm.delete(id);
		return "relistwork";
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MemberClassNotice getMcnEntity() {
		return mcnEntity;
	}

	public void setMcnEntity(MemberClassNotice mcnEntity) {
		this.mcnEntity = mcnEntity;
	}

	public List<MemberClassNotice> getMcnList() {
		return mcnList;
	}

	public List<Member> getMemberPhone() {
		return memberPhone;
	}

	public void setMemberids(Long[] memberids) {
		this.memberids = memberids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	private void pageAction(String tmpfile,String outfile){
		//模板解析	
			try {
				pagecontent = tmp.analyticalTmp(tmpfile,outfile,pagecontent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Autowired
	public void setTmp(Template tmp) {
		this.tmp = tmp;
	}
	
	@Autowired
	public void setCtm(ConfigTemplateManager ctm) {
		this.ctm = ctm;
	}
	
	public PageContent getPagecontent() {
		return pagecontent;
	}
	
	
}
