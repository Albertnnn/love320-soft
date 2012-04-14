/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.entity.account;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springside.modules.utils.reflection.ConvertUtils;

import com.google.common.collect.Lists;

import cms.entity.IdEntity;


@Entity
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Member extends IdEntity{
	
	private MemberType mtype;//用户类型
	
	private String userid ;//用户帐号
	private String pwd;//用户密码 
	private String uname ;//用户名
	public enum Sex {
		male, female, secret
	}
	private Sex sex ;//性别 '男','女','保密'
	private int rank ;//会员级别值
	private int uprank ;//是否待升级
	private int money ;//会员金币
	private int upmoney;//是否待充值
	private String email;//Email
	private String phone;//手机
	private int scores;//积分 
	private int matt ;//推荐
	private int spacesta;//会员空间状况
	private String face;//头像
	private String safequestion ;//安全提示问题 
	private String safeanswer; //安全提示问题答案 
	private Date jointime;//注册时间 
	private String joinip ;//注册IP
	private Date logintime;//登陆时间 
	private String loginip;//登陆IP
	
	private int smsint;//短信数
	private Date smsDate;//短信时间添时间
	

	/*
	 * 学生类
	 * */
	private List<WebSMS> webSmsListStudents;
	
	@OneToMany(mappedBy="students",cascade=CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id desc")
	public List<WebSMS> getWebSmsListStudents() {
		return webSmsListStudents;
	}

	public void setWebSmsListStudents(List<WebSMS> webSmsListStudents) {
		this.webSmsListStudents = webSmsListStudents;
	}


	private MemberClass memberclassstudent ;//学生对应的班级
	
	@ManyToOne
	@JoinTable(
			name="member_memberclassstudent",
			joinColumns={
					@JoinColumn(name="member_id")
			},
			inverseJoinColumns={
					@JoinColumn(name="memberclassstudent_id")
			}
	)
	public MemberClass getMemberclassstudent() {
		return memberclassstudent;
	}

	public void setMemberclassstudent(MemberClass memberclassstudent) {
		this.memberclassstudent = memberclassstudent;
	}
	
	/*
	 * 老师类
	 * */
	
	private List<WebSMS> webSmsListteachers;
	
	@OneToMany(mappedBy="teachers",cascade=CascadeType.ALL)
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id desc")
	public List<WebSMS> getWebSmsListteachers() {
		return webSmsListteachers;
	}

	public void setWebSmsListteachers(List<WebSMS> webSmsListteachers) {
		this.webSmsListteachers = webSmsListteachers;
	}

	private List<MemberClass> memberClassLists  = Lists.newArrayList();//自定义属性值 ;//老师对应多个班级
	private List<MemberClassNotice> memberClassNoticeList;
	
	@OneToMany(mappedBy="member")
	@OrderBy("id desc")
	public List<MemberClassNotice> getMemberClassNoticeList() {
		return memberClassNoticeList;
	}

	public void setMemberClassNoticeList(
			List<MemberClassNotice> memberClassNoticeList) {
		this.memberClassNoticeList = memberClassNoticeList;
	}

	@ManyToMany
	@JoinTable(
			name="memberteacher_memberclass",
			joinColumns={
					@JoinColumn(name="memberteacher_id")
			},
			inverseJoinColumns={
					@JoinColumn(name="memberclass_id")
			}
	)
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("id desc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<MemberClass> getMemberClassLists() {
		return memberClassLists;
	}

	public void setMemberClassLists(List<MemberClass> memberClassLists) {
		this.memberClassLists = memberClassLists;
	}
	
	
	@Transient
	@SuppressWarnings("unchecked")
	public List<Long> getMemberClassListsId() {
		return ConvertUtils.convertElementPropertyToList(memberClassLists, "id");
	}
	
	/*
	 * get set funtcion
	 * */
	
	public String getUserid() {
		return userid;
	}

	@ManyToOne
	@JoinTable(
			name="member_member_type",
			joinColumns={
					@JoinColumn(name="member_id")
			},
			inverseJoinColumns={
					@JoinColumn(name="membertype_id")
			}
	)
	public MemberType getMtype() {
		return mtype;
	}
	public void setMtype(MemberType mtype) {
		this.mtype = mtype;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getUprank() {
		return uprank;
	}
	public void setUprank(int uprank) {
		this.uprank = uprank;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getUpmoney() {
		return upmoney;
	}
	public void setUpmoney(int upmoney) {
		this.upmoney = upmoney;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	public int getMatt() {
		return matt;
	}
	public void setMatt(int matt) {
		this.matt = matt;
	}
	public int getSpacesta() {
		return spacesta;
	}
	public void setSpacesta(int spacesta) {
		this.spacesta = spacesta;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getSafequestion() {
		return safequestion;
	}
	public void setSafequestion(String safequestion) {
		this.safequestion = safequestion;
	}
	public String getSafeanswer() {
		return safeanswer;
	}
	public void setSafeanswer(String safeanswer) {
		this.safeanswer = safeanswer;
	}
	public Date getJointime() {
		return jointime;
	}
	public void setJointime(Date jointime) {
		this.jointime = jointime;
	}
	public String getJoinip() {
		return joinip;
	}
	public void setJoinip(String joinip) {
		this.joinip = joinip;
	}
	public Date getLogintime() {
		return logintime;
	}
	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getSmsint() {
		return smsint;
	}

	public void setSmsint(int smsint) {
		this.smsint = smsint;
	}

	public Date getSmsDate() {
		return smsDate;
	}

	public void setSmsDate(Date smsDate) {
		this.smsDate = smsDate;
	}

	
	
	
}
