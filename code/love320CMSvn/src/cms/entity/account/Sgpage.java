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
public class Sgpage extends IdEntity {
		
		private String title ;//单页面标题
		private String fileName;//文件名
		private String template;//模板文件名
		private String likeid ;//关联标识
		private int typeid ;//所属栏目
		private Date uptime;//时间
		private String keywords;//关键字
		private String description;//页面摘要信息
		private String body;//内容
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getTemplate() {
			return template;
		}
		public void setTemplate(String template) {
			this.template = template;
		}
		public String getLikeid() {
			return likeid;
		}
		public void setLikeid(String likeid) {
			this.likeid = likeid;
		}
		public Date getUptime() {
			return uptime;
		}
		public void setUptime(Date uptime) {
			this.uptime = uptime;
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
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public int getTypeid() {
			return typeid;
		}
		public void setTypeid(int typeid) {
			this.typeid = typeid;
		}
		
		
}
