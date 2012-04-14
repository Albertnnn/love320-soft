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

@Entity
public class WebSMS extends IdEntity {
	//学号	 学生姓名 	发布时间	发布者e号	 在校表现内容
	private Member students;//学生
	private Member teachers;//老师
	private Date newDate;//创建时间
	private String content;//内容
	private int typeid;//信息类型   1 考试    2 在校表现

	@ManyToOne
	@JoinTable(
			name="memberstudents_webSMS",
			joinColumns={
					@JoinColumn(name="memberstudents_id")
			},
			inverseJoinColumns={
					@JoinColumn(name="webSMS_id")
			}
	)
	public Member getStudents() {
		return students;
	}

	public void setStudents(Member students) {
		this.students = students;
	}

	@ManyToOne
	@JoinTable(
			name="memberteachers_webSMS",
			joinColumns={
					@JoinColumn(name="memberteachers_id")
			},
			inverseJoinColumns={
					@JoinColumn(name="webSMS_id")
			}
	)
	public Member getTeachers() {
		return teachers;
	}

	public void setTeachers(Member teachers) {
		this.teachers = teachers;
	}

	public Date getNewDate() {
		return newDate;
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	
	
	
	
}
