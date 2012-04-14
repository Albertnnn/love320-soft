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
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cms.entity.IdEntity;


@Entity
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Feedback extends IdEntity{
	
	//文档评论
	
	//private int aid ; //关联的文档ID
	private int typeid ; //栏目ID
	private String username; //评论用户名
	private String title; //评论标题
	private String ip; //评论客户机的IP
	private int ischeck;//
	private Date dtime; //评论时间
	private int mid;//会员ID
	private int bad;//坏评
	private int good;//好评
	private String ftype; //  `ftype` set('feedback','good','bad') NOT NULL default 'feedback',
	private int face; //评论头像
	private String msg;//评论内容
	
	private Archives archives ;//关联的文档类
	private Shop shop ;//关联商品类
	
	@ManyToOne
	//@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="shopid")
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	@ManyToOne
	//@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="aid")
	public Archives getArchives() {
		return archives;
	}
	public void setArchives(Archives archives) {
		this.archives = archives;
	}
	//	public int getAid() {
//		return aid;
//	}
//	public void setAid(int aid) {
//		this.aid = aid;
//	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getIscheck() {
		return ischeck;
	}
	public void setIscheck(int ischeck) {
		this.ischeck = ischeck;
	}
	public Date getDtime() {
		return dtime;
	}
	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public String getFtype() {
		return ftype;
	}
	public void setFtype(String ftype) {
		this.ftype = ftype;
	}
	public int getFace() {
		return face;
	}
	public void setFace(int face) {
		this.face = face;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
		
	
}
