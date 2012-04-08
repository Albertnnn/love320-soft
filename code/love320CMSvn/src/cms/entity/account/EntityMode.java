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

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cms.entity.IdEntity;


@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EntityMode extends IdEntity {

	private String modeName; //模型名
	private String entityName; //模型对象名
	private String nid ;//识别id
	private String addTable ;//附加表
	private String listTmpl ;//模板列表模板
	private String articleTmpl ;//模板文档模板
	
	private Set<Arctype> arctypeList;//关联栏目

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getAddTable() {
		return addTable;
	}

	public void setAddTable(String addTable) {
		this.addTable = addTable;
	}

	public String getListTmpl() {
		return listTmpl;
	}

	public void setListTmpl(String listTmpl) {
		this.listTmpl = listTmpl;
	}

	public String getArticleTmpl() {
		return articleTmpl;
	}

	public void setArticleTmpl(String articleTmpl) {
		this.articleTmpl = articleTmpl;
	}

	@OneToMany(mappedBy="entitymode")
	@OrderBy("id desc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Set<Arctype> getArctypeList() {
		return arctypeList;
	}

	public void setArctypeList(Set<Arctype> arctypeList) {
		this.arctypeList = arctypeList;
	}
	
	
	

	
}
