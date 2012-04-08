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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Lists;

import cms.entity.IdEntity;


@Entity
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Arcatt extends IdEntity{	
		//自定义文档属性表
	
		private String att; //属性字符
		private String attName; //属性说明
		private List<Archives> archivesList  = Lists.newArrayList();//自定义属性值 
	
		public String getAtt() {
			return att;
		}
		public void setAtt(String att) {
			this.att = att;
		}
		public String getAttName() {
			return attName;
		}
		public void setAttName(String attName) {
			this.attName = attName;
		}
		
		@ManyToMany(mappedBy="flagList")
		public List<Archives> getArchivesList() {
			return archivesList;
		}
		public void setArchivesList(List<Archives> archivesList) {
			this.archivesList = archivesList;
		}
		
		
		
}
