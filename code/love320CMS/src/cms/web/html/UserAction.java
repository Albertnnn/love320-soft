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

import cms.entity.account.Member;
import cms.entity.account.MemberClass;
import cms.entity.account.MemberType;
import cms.pageList.entity.PageContent;
import cms.service.account.ConfigTemplateManager;
import cms.service.account.MemberClassManager;
import cms.service.account.MemberManager;
import cms.service.account.MemberTypeManager;
import cms.service.account.ServicesManager;
import cms.service.account.Template;
import cms.service.account.UcenterClientManager;
import cms.web.CrudActionSupport;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


@Results({@Result(name = "member", location = "member.action", type = "redirect")})
public class UserAction extends ActionSupport implements ModelDriven<Member>, Preparable{
	
	
	private Template tmp ;//加载模板类并解析
	private ConfigTemplateManager ctm;//系统模板配置文件管理对象
	
	private PageContent pagecontent = new PageContent();

	private MemberClassManager mcm;
	private List<MemberClass> listMC;
	private MemberTypeManager mtm;
	private List<MemberType> listMT;
	private Long id;
	private Long mtmId;
	private Long mcId;
	
	private MemberManager mm;
	private Member member = new Member();
	
	private UcenterClientManager ucCM;
	
	private String repwd;
	
	private String msg;
	
	private String synloginjs;
	
	@Override
	public String execute() throws Exception {
		System.out.println("success");
		return super.execute();
	}

	//注册页面
	public String reg(){

		listMC = mcm.getAll();
		listMT = mtm.getAll();
		
		String tmpfileconfig = ctm.fillconfigFile().getMemberreg();	
		pageAction(tmpfileconfig,"memberreg.ftl");//模板渲染
		
		return "memberreg";
	}
	
	//用户注册
	public String regSave(){
		
		if(mm.validationUserName(member.getUserid())){
			if(member.getPwd().equals(repwd)){
				member.setMtype(mtm.getEntity(mtmId));//设置会员类型
				member.setMemberclassstudent(mcm.getEntity(mcId));//设置班级
				mm.save(member);
				String tmpfileconfig = ctm.fillconfigFile().getMemberlogin();	
				pageAction(tmpfileconfig,"user-login.ftl");//模板渲染
				
				return "user-login";
			}else{
				msg = "二次密码有误!";
				String tmpfileconfig = ctm.fillconfigFile().getErrorhome();	
				pageAction(tmpfileconfig,"errorhome.ftl");//模板渲染
				return "errorhome";
			}
		}else{
			msg = "帐号以存在!";
			String tmpfileconfig = ctm.fillconfigFile().getErrorhome();	
			pageAction(tmpfileconfig,"errorhome.ftl");//模板渲染
			return "errorhome";
		}
		
		//msg = "操作有误!";
		//return "error";
		
	}
	
	//登录
	public String login(){
		if(member.getUserid() != null){
			
			if(mm.login(member) == true){//验证帐号和密码
				//System.out.println(">>"+member.getUserid()+ member.getPwd());
				return "member";
			}else if(ucCM.login(member.getUserid(), member.getPwd())){
				mm.save(mm.ucUserTOSysUser(member.getUserid(),member.getPwd()));
				return "member";
			}else{
				msg = "帐号或密码有误!";
				String tmpfileconfig = ctm.fillconfigFile().getErrorhome();	
				pageAction(tmpfileconfig,"errorhome.ftl");//模板渲染
				return "errorhome";
			}
		}else{
			String tmpfileconfig = ctm.fillconfigFile().getMemberlogin();	
			pageAction(tmpfileconfig,"user-login.ftl");//模板渲染
			
			return "user-login";
		}		
	}
	
	public String loginout(){
		synloginjs = mm.loginout();
		String tmpfileconfig = ctm.fillconfigFile().getMemberlogin();	
		pageAction(tmpfileconfig,"user-login.ftl");//模板渲染
		return "user-login";
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
	

	public String edit(){
		listMC = mcm.getAll();
		listMT = mtm.getAll();
		member = mm.getSessionEntity();
		
		String tmpfileconfig = ctm.fillconfigFile().getInformation();	
		pageAction(tmpfileconfig,"information.ftl");//模板渲染
		
		return "information";
	}
	
	public void prepareModel() throws Exception {
		member = mm.getSessionEntity();
		//member = mm.getEntity(id);
	}

	public Member getModel() {
		return member;
	}
	
	public void prepareSave() throws Exception {
		prepareModel();
	}
	
	public String save(){
		mm.save(member);
		return "member";
	}

	@Autowired
	public void setMcm(MemberClassManager mcm) {
		this.mcm = mcm;
	}

	@Autowired
	public void setMtm(MemberTypeManager mtm) {
		this.mtm = mtm;
	}

	public List<MemberClass> getListMC() {
		return listMC;
	}

	public List<MemberType> getListMT() {
		return listMT;
	}

	@Autowired
	public void setMm(MemberManager mm) {
		this.mm = mm;
	}

	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}

	public String getMsg() {
		return msg;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void prepare() throws Exception {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMtmId() {
		return mtmId;
	}

	public void setMtmId(Long mtmId) {
		this.mtmId = mtmId;
	}

	public Long getMcId() {
		return mcId;
	}

	public void setMcId(Long mcId) {
		this.mcId = mcId;
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
