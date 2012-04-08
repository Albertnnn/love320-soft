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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cms.entity.IdEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Advertising extends IdEntity {
	
	private String typeid;//广告投放范围
	private String tagname;//广告标识
	private String adname;//广告位名称
	private int timeset;//时间限制 0,1
	private Date starttime;//时间限制
	private Date endtime;//结束时间
	private String normbody;//广告内容
	private String expbody;//过期显示内容
	
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
	public int getTimeset() {
		return timeset;
	}
	public void setTimeset(int timeset) {
		this.timeset = timeset;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getNormbody() {
		return normbody;
	}
	public void setNormbody(String normbody) {
		this.normbody = normbody;
	}
	public String getExpbody() {
		return expbody;
	}
	public void setExpbody(String expbody) {
		this.expbody = expbody;
	}
	
	
	
	
}
