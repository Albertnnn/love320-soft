/**
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
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.entity.account.Member;
import cms.entity.account.MemberClassSms;
import cms.pageList.entity.PageContent;
import cms.service.account.ConfigTemplateManager;
import cms.service.account.MemberClassSmsManager;
import cms.service.account.MemberManager;
import cms.service.account.SmsManager;
import cms.service.account.Template;
import cms.service.account.UcenterClientManager;
import cms.web.CrudActionSupport;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Results({@Result(name = "reuserlogin", location = "user!login.action", type = "redirect"),
	@Result(name = "memberhome", location = "member.action", type = "redirect")})
public class MemberAction extends ActionSupport{
	
	private Template tmp ;//加载模板类并解析
	private ConfigTemplateManager ctm;//系统模板配置文件管理对象
	
	private PageContent pagecontent = new PageContent();

	
	private Member member = new Member();
	private MemberManager mm;
	private MemberClassSmsManager mcsm;
	private MemberClassSms mcsEntity = new MemberClassSms();
	private Long smsid;
	
	private List<Member> memberPhone;
	private Long sendPhoneNum[];

	private List<MemberClassSms> listmcsms;
	
	private UcenterClientManager ucCM;
	
	private SmsManager smsM;
	
	private String synloginjs;
	
	public String receive(){

		//mcsm.processing();
		member = mm.getSessionEntity();
		if(member == null){
			return "user-login";
		}
		listmcsms = mcsm.listMCSMS(member);
		
		String tmpfileconfig = ctm.fillconfigFile().getAcceptshortmailbox();	
		pageAction(tmpfileconfig,"acceptshortmailbox.ftl");//模板渲染
		
		return "acceptshortmailbox";
	}
	
	public String send(){
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		listmcsms = mcsm.listMCSMS(member);
		
		String tmpfileconfig = ctm.fillconfigFile().getThehairshortmailbox();	
		pageAction(tmpfileconfig,"thehairshortmailbox.ftl");//模板渲染
		return "thehairshortmailbox";
	}
	
	public String show(){
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		mcsEntity = mcsm.getEntity(smsid);
		return "show";
	}
	
	public String sendsms(){
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		//获取有手机号的用户
		memberPhone = mm.listPhoneUser();	
		
		
		if (mcsEntity.getContent() != null) {
			
			mcsEntity.setPhone(mm.idsToPhoneStr(sendPhoneNum));
			
			if(member.getMemberClassLists().size() > 0){
				mcsEntity.setMemberClass(member.getMemberClassLists().get(0));
			}
			
			mcsm.save(mcsEntity);
			return "memberhome";
		} else {
			
			String tmpfileconfig = ctm.fillconfigFile().getTextmessaging();	
			pageAction(tmpfileconfig,"textmessaging.ftl");//模板渲染
			
			return "textmessaging";
		}
	
	}

	@Override
	public String execute() throws Exception {
		member = mm.getSessionEntity();
		if(member == null){
			return "reuserlogin";
		}
		
	    synloginjs = ucCM.synloginjs(member.getUserid());
		String tmpfileconfig = ctm.fillconfigFile().getMemberhome();	
		pageAction(tmpfileconfig,"memberindex.ftl");//模板渲染
		
		return "memberindex";
	}

	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String list() throws Exception {
		member = mm.getSessionEntity();
		
		return SUCCESS;
	}


	public String save() throws Exception {
		mm.save(member);
		return null;
	}

	@Autowired
	public void setMm(MemberManager mm) {
		this.mm = mm;
	}



	public List<MemberClassSms> getListmcsms() {
		return listmcsms;
	}

	@Autowired
	public void setMcsm(MemberClassSmsManager mcsm) {
		this.mcsm = mcsm;
	}

	public MemberClassSms getMcsEntity() {
		return mcsEntity;
	}

	public List<Member> getMemberPhone() {
		return memberPhone;
	}

	public Long getSmsid() {
		return smsid;
	}

	public void setSmsid(Long smsid) {
		this.smsid = smsid;
	}

	@Autowired
	public void setSmsM(SmsManager smsM) {
		this.smsM = smsM;
	}

	public void setSendPhoneNum(Long[] sendPhoneNum) {
		this.sendPhoneNum = sendPhoneNum;
	}

	public Member getMember() {
		return member;
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

	@Autowired
	public void setUcCM(UcenterClientManager ucCM) {
		this.ucCM = ucCM;
	}

	public String getSynloginjs() {
		return synloginjs;
	}
	
	
	
	
}
