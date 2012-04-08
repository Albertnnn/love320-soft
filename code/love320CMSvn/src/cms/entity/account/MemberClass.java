/**
 * Copyright (c) 2010-2011 love320.com
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import cms.entity.IdEntity;

/*
 * 班级类(年级)
 * */

@Entity
public class MemberClass extends IdEntity {
	
	private int gradeId ;//年级号
	private String classId;//班级号
	private String className;//班级名
	private String classText;//班级说明
	
	private List<Member> students;//一个班级中有多个学生
	private List<Member> teachers;//一个班级中有多个老师
	
	private List<MemberClassSms> classSms;//班级短信库
	
	private List<MemberClassNotice> classNotice;//班级通知
	
	@OneToMany(mappedBy="memberClass")
	@Cascade(CascadeType.ALL)
	@OrderBy("id desc")
	public List<MemberClassSms> getClassSms() {
		return classSms;
	}
	public void setClassSms(List<MemberClassSms> classSms) {
		this.classSms = classSms;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassText() {
		return classText;
	}
	public void setClassText(String classText) {
		this.classText = classText;
	}
	
	@OneToMany(mappedBy="memberclassstudent")
	@Cascade(CascadeType.ALL)
	@OrderBy("id desc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Member> getStudents() {
		return students;
	}
	public void setStudents(List<Member> students) {
		this.students = students;
	}
	
	@ManyToMany
	public List<Member> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<Member> teachers) {
		this.teachers = teachers;
	}
	
	@OneToMany(mappedBy="memberClass")
	@OrderBy("id desc")
	public List<MemberClassNotice> getClassNotice() {
		return classNotice;
	}
	public void setClassNotice(List<MemberClassNotice> classNotice) {
		this.classNotice = classNotice;
	}
	
	
	
	
}
