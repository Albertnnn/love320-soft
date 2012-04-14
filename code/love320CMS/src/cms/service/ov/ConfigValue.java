/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.ov;

import org.springframework.stereotype.Component;



@Component
public class ConfigValue {
	private String basehost;
	private String cmspath;
	private String indexname;
	private String adminemail;
	private String mode;
	private String keywords;
	private String description;
	private String powerby;
	private String beian;
	private String htmlgeneration;//生成分页数
	
	public String getBasehost() {
		return basehost;
	}
	public void setBasehost(String basehost) {
		this.basehost = basehost;
	}
	public String getCmspath() {
		return cmspath;
	}
	public void setCmspath(String cmspath) {
		this.cmspath = cmspath;
	}
	public String getIndexname() {
		return indexname;
	}
	public void setIndexname(String indexname) {
		this.indexname = indexname;
	}
	public String getAdminemail() {
		return adminemail;
	}
	public void setAdminemail(String adminemail) {
		this.adminemail = adminemail;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPowerby() {
		return powerby;
	}
	public void setPowerby(String powerby) {
		this.powerby = powerby;
	}
	public String getBeian() {
		return beian;
	}
	public void setBeian(String beian) {
		this.beian = beian;
	}
	public String getHtmlgeneration() {
		return htmlgeneration;
	}
	public void setHtmlgeneration(String htmlgeneration) {
		this.htmlgeneration = htmlgeneration;
	}
	
	
	
	
}
