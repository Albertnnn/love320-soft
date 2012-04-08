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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import cms.entity.IdEntity;

@Entity
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TagLabelType extends IdEntity {
	
	
	private String typeName;//标签类型名
	private List<TagLabel> tagList;//标签类型的使用数
	
	private String className;//标签类的类名

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@OneToMany(mappedBy="tagLabelType")
	@OrderBy("id desc")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<TagLabel> getTagList() {
		return tagList;
	}

	public void setTagList(List<TagLabel> tagList) {
		this.tagList = tagList;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
	
	
}
