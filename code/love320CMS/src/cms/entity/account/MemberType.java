/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.entity.account;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import cms.entity.IdEntity;

@Entity
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MemberType extends IdEntity {
	private String typeName;//会员类型名
	private String typeMark;//会员标记符
	
	private List<Member> memberLists;
	/*
	 * 
	 * 
	 * */
	public String getTypeName() {
		return typeName;
	}
	
	@OneToMany(mappedBy="mtype")
	@Cascade(CascadeType.ALL)
	@OrderBy("id desc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Member> getMemberLists() {
		return memberLists;
	}
	public void setMemberLists(List<Member> memberLists) {
		this.memberLists = memberLists;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeMark() {
		return typeMark;
	}
	public void setTypeMark(String typeMark) {
		this.typeMark = typeMark;
	}
	
	
	
}
