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

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import cms.entity.IdEntity;


/*
 * 班级通知>>
 * */

@Entity
public class MemberClassNotice extends IdEntity {
	
	private String content;//通 知 内 容
	private MemberClass memberClass;//班级
	private Member member;//发送者 
	private Date sendDate;//时间 
	
	private int typeid; //1班级通知 2布置作业
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@ManyToOne
	@JoinTable(
			name="memberClassNotice_memberclass",
			joinColumns={
					@JoinColumn(name="memberClassNotice_id")
			},
			inverseJoinColumns={
					@JoinColumn(name="memberclass_id")
			}
	)
	public MemberClass getMemberClass() {
		return memberClass;
	}
	public void setMemberClass(MemberClass memberClass) {
		this.memberClass = memberClass;
	}
	
	@ManyToOne
	@JoinTable(
			name="memberClassNotice_member",
			joinColumns={
					@JoinColumn(name="memberClassNotice_id")
			},
			inverseJoinColumns={
					@JoinColumn(name="member_id")
			}
	)
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	
	
	
}
