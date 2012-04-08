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
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OnlineOrder extends IdEntity {
	
	private String prpName ;//产 品 名 称 
	private double money ;//期望价格： 
	private int num;//定 购 数 量 
	private String company ;//公 司 名 称 
	private String tel;//联 系 电 话 
	private String perple;//联 系 人 
	private String fax;//传　　真 
	private String address;//联 系 地 址 
	private String postalCode;//邮 政 编 码 
	private String email;//联 系 Email 
	private String webSite;//公 司 网 址 
	private String test;//订购要求和其他
	private Date dateTime;//订单时间
	public String getPrpName() {
		return prpName;
	}
	public void setPrpName(String prpName) {
		this.prpName = prpName;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPerple() {
		return perple;
	}
	public void setPerple(String perple) {
		this.perple = perple;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	

}
