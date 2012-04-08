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

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import cms.entity.account.Member;
import cms.entity.account.WebSMS;
import cms.pageList.entity.PageContent;
import cms.service.account.ConfigTemplateManager;
import cms.service.account.MemberManager;
import cms.service.account.Template;
import cms.service.account.WebSMSManager;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;

@Results({@Result(name = "relist", location = "websms!list.action", type = "redirect"),
		@Result(name = "relistsubjects", location = "websms!listsubjects.action", type = "redirect"),
		@Result(name = "reuserlogin", location = "user!login.action", type = "redirect")})
public class WebsmsAction extends ActionSupport {
	
	private Template tmp ;//加载模板类并解析
	private ConfigTemplateManager ctm;//系统模板配置文件管理对象
	private PageContent pagecontent = new PageContent();
	
	private WebSMSManager websmsM;
	private List<WebSMS> websmslist = Lists.newArrayList();
	private MemberManager mm;
	private Member member;
	private List<Member> memberPhone;
	private Long memberids[];
	private String memberStrs[];
	private Long id;
	
	private String subjectsOrder;//考试成绩科目顺序
	private String subjectsType;//考试成绩科目类别
	
	@Autowired
	public void setMm(MemberManager mm) {
		this.mm = mm;
	}

	@Autowired
	public void setWebsmsM(WebSMSManager websmsM) {
		this.websmsM = websmsM;
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
			websmslist = member.getWebSmsListteachers();
		}

		if (member.getMtype().getTypeMark().equals("students")) {
			websmslist = member.getWebSmsListStudents();
		}

		String tmpfileconfig = ctm.fillconfigFile().getCchoolperformance();	
		pageAction(tmpfileconfig,"cchoolperformance.ftl");//模板渲染
		
		return "cchoolperformance";
	}
	
	public String input(){
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		//获取有手机号的用户
		memberPhone = mm.listPhoneUser();	
		
		String tmpfileconfig = ctm.fillconfigFile().getReleaseschoolperformance();	
		pageAction(tmpfileconfig,"releaseschoolperformance.ftl");//模板渲染
		
		return "releaseschoolperformance";
	}
	
	public String save(){
		member = mm.getSessionEntity();

		if(member == null){
			return "reuserlogin";
		}
		for(int i =0 ; i < memberids.length ;i++){
			WebSMS websmsEntity = new WebSMS();
			websmsEntity.setTeachers(member);//发送者
			websmsEntity.setStudents(mm.getEntity(memberids[i]));//接收者
			websmsEntity.setContent(memberStrs[i]);//设置内容
			websmsEntity.setTypeid(2); //信息类型
			if(memberStrs[i].trim() != ""){
				websmsM.save(websmsEntity);//保存
			}
			
		}
		
		return "relist";
	}
	
	public String delete(){
		websmsM.delete(id);
		return "relist";
	}
	
	/*
	 * 
	 * */
	public String listsubjects(){
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		if (member.getMtype().getTypeMark().equals("teacher")) {
			websmslist = member.getWebSmsListteachers();
		}

		if (member.getMtype().getTypeMark().equals("students")) {
			websmslist = member.getWebSmsListStudents();
		}
		
		String tmpfileconfig = ctm.fillconfigFile().getExaminationmanagement();	
		pageAction(tmpfileconfig,"examinationmanagement.ftl");//模板渲染

		return "examinationmanagement";
	}
	
	public String inputsubjects(){
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		//获取有手机号的用户
		memberPhone = mm.listPhoneUser();	
		
		String tmpfileconfig = ctm.fillconfigFile().getReleasetestscores();	
		pageAction(tmpfileconfig,"releasetestscores.ftl");//模板渲染
		
		return "releasetestscores";
	}
	
	public String savesubjects(){
		member = mm.getSessionEntity();

		if(member == null){
			return "reuserlogin";
		}

		memberStrs = websmsM.subjects(subjectsOrder,memberStrs);
		
		for(int i =0 ; i < memberids.length ;i++){
			WebSMS websmsEntity = new WebSMS();
			Member rmember = mm.getEntity(memberids[i]);
			websmsEntity.setTeachers(member);//发送者
			websmsEntity.setStudents(rmember);//接收者
			websmsEntity.setContent(rmember.getUname()+subjectsType+memberStrs[i]);//设置内容
			websmsEntity.setTypeid(1); //信息类型
			if(memberStrs[i].trim() != ""){
				websmsM.save(websmsEntity);//保存
			}
			
		}
		
		return "relistsubjects";
	}
	
	
	public String deletesubjects(){
		websmsM.delete(id);
		return "relistsubjects";
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<WebSMS> getWebsmslist() {
		return websmslist;
	}

	public List<Member> getMemberPhone() {
		return memberPhone;
	}

	public Long[] getMemberids() {
		return memberids;
	}

	public void setMemberids(Long[] memberids) {
		this.memberids = memberids;
	}

	public String[] getMemberStrs() {
		return memberStrs;
	}

	public void setMemberStrs(String[] memberStrs) {
		this.memberStrs = memberStrs;
	}

	public void setSubjectsOrder(String subjectsOrder) {
		this.subjectsOrder = subjectsOrder;
	}

	public void setSubjectsType(String subjectsType) {
		this.subjectsType = subjectsType;
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
