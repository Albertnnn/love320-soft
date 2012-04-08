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

import java.util.Date;

import javax.persistence.Entity;

import cms.entity.IdEntity;

/*
 * 短信
 * */

@Entity
public class SMS extends IdEntity {
	
	private int typeId;//短信类型
	private String strType;//信息类别;
	private String phone;//手机号
	private String content;//短信内容
	private int sendType;//发送标志   0：未提交 1：发送成功 2:发送失败
	private Date smsDate;//服务时间 
	private Date newDate;//创建时间 
	private Date optionDate;//定时发送时间 
	
	private int smsAction;//处理状态

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSendType() {
		return sendType;
	}

	public void setSendType(int sendType) {
		this.sendType = sendType;
	}

	public Date getSmsDate() {
		return smsDate;
	}

	public void setSmsDate(Date smsDate) {
		this.smsDate = smsDate;
	}

	public Date getNewDate() {
		return newDate;
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	public Date getOptionDate() {
		return optionDate;
	}

	public void setOptionDate(Date optionDate) {
		this.optionDate = optionDate;
	}

	public int getSmsAction() {
		return smsAction;
	}

	public void setSmsAction(int smsAction) {
		this.smsAction = smsAction;
	}

	public String getStrType() {
		return strType;
	}

	public void setStrType(String strType) {
		this.strType = strType;
	}
	
	
	
	
}
