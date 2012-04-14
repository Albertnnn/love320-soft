/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.ov;

import org.springframework.stereotype.Component;

import cms.service.account.XMLConfigManager;


//系统必需模板管理类

@Component
public class ConfigTemplate {
	
	private String homepage ;//首页模板
	private String guestbook;//留言模板
	private String search;//搜索模板
	
	private String errorhome;//操作失败
	
	/*
	 * 会员 
	 * */
	private String memberhome ;//会员 页面
	private String memberlogin;//会员登录
	private String memberreg;// 会员注册
	private String information;//修改个人信息   
	private String textmessaging;//发短信
	private String acceptshortmailbox;//收短信箱
	private String thehairshortmailbox;//发短信箱  
	private String classnotice;//班级通知 
	private String classnoticeissuedbythe;//发布班级通知   
	private String classproject;//班级作业  
	private String releassclassproject;// 发布班级作业 
	private String cchoolperformance;//在校表现
	private String examinationmanagement;//考试管理
	private String releaseschoolperformance;//发布在校表现
	private String releasetestscores;// 发布考试成绩  
	
	
	
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getGuestbook() {
		return guestbook;
	}
	public void setGuestbook(String guestbook) {
		this.guestbook = guestbook;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getTextmessaging() {
		return textmessaging;
	}
	public void setTextmessaging(String textmessaging) {
		this.textmessaging = textmessaging;
	}
	public String getAcceptshortmailbox() {
		return acceptshortmailbox;
	}
	public void setAcceptshortmailbox(String acceptshortmailbox) {
		this.acceptshortmailbox = acceptshortmailbox;
	}
	public String getThehairshortmailbox() {
		return thehairshortmailbox;
	}
	public void setThehairshortmailbox(String thehairshortmailbox) {
		this.thehairshortmailbox = thehairshortmailbox;
	}
	public String getClassnotice() {
		return classnotice;
	}
	public void setClassnotice(String classnotice) {
		this.classnotice = classnotice;
	}
	public String getClassnoticeissuedbythe() {
		return classnoticeissuedbythe;
	}
	public void setClassnoticeissuedbythe(String classnoticeissuedbythe) {
		this.classnoticeissuedbythe = classnoticeissuedbythe;
	}
	public String getClassproject() {
		return classproject;
	}
	public void setClassproject(String classproject) {
		this.classproject = classproject;
	}
	public String getReleassclassproject() {
		return releassclassproject;
	}
	public void setReleassclassproject(String releassclassproject) {
		this.releassclassproject = releassclassproject;
	}
	public String getCchoolperformance() {
		return cchoolperformance;
	}
	public void setCchoolperformance(String cchoolperformance) {
		this.cchoolperformance = cchoolperformance;
	}
	public String getExaminationmanagement() {
		return examinationmanagement;
	}
	public void setExaminationmanagement(String examinationmanagement) {
		this.examinationmanagement = examinationmanagement;
	}
	public String getReleaseschoolperformance() {
		return releaseschoolperformance;
	}
	public void setReleaseschoolperformance(String releaseschoolperformance) {
		this.releaseschoolperformance = releaseschoolperformance;
	}
	public String getReleasetestscores() {
		return releasetestscores;
	}
	public void setReleasetestscores(String releasetestscores) {
		this.releasetestscores = releasetestscores;
	}
	public String getMemberhome() {
		return memberhome;
	}
	public void setMemberhome(String memberhome) {
		this.memberhome = memberhome;
	}
	public String getMemberreg() {
		return memberreg;
	}
	public void setMemberreg(String memberreg) {
		this.memberreg = memberreg;
	}
	public String getMemberlogin() {
		return memberlogin;
	}
	public void setMemberlogin(String memberlogin) {
		this.memberlogin = memberlogin;
	}
	public String getErrorhome() {
		return errorhome;
	}
	public void setErrorhome(String errorhome) {
		this.errorhome = errorhome;
	}
	
	
}
