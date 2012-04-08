/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.pageList.entity;

import java.util.List;
import org.springside.modules.orm.PropertyFilter;

import com.google.common.collect.Lists;

import cms.pageList.LabelInterface;
import cms.service.ov.ConfigValue;


public class PageContent {
	private List<LabelInterface> listLabel;//页面列表内容
	private List<PropertyFilter> pfs;//页面环境
	private String position ;//位置导航
	private String typeName ;//路径栏目名
	private Long theTypeId;//当前栏目ID
	private String theTypeName ;//当前栏目名
	private int temIterationId = 0;//临时通用文档标签（使用）
	private int temIterationTyepId = 0;//临时通用栏目标签（使用）
	private ConfigValue configFile;//系统配置环境对象
	
	public PageContent(){
		if((listLabel != null)||(pfs != null)){
			if(!listLabel.isEmpty()){
				listLabel.clear();
			}
			
			if(!pfs.isEmpty()){
				pfs.clear();
			}

		}else{			
			listLabel = Lists.newArrayList();
			pfs = Lists.newArrayList();
		}
	}
	

	public List<LabelInterface> getListLabel() {
		return listLabel;
	}

	public void setListLabel(List<LabelInterface> listLabel) {
		this.listLabel = listLabel;
	}

	public List<PropertyFilter> getPfs() {
		return pfs;
	}

	public void setPfs(List<PropertyFilter> pfs) {
		this.pfs = pfs;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getTheTypeName() {
		return theTypeName;
	}


	public void setTheTypeName(String theTypeName) {
		this.theTypeName = theTypeName;
	}


	public int getTemIterationId() {
		return temIterationId;
	}


	public void setTemIterationId(int temIterationId) {
		this.temIterationId = temIterationId;
	}


	public int getTemIterationTyepId() {
		return temIterationTyepId;
	}


	public void setTemIterationTyepId(int temIterationTyepId) {
		this.temIterationTyepId = temIterationTyepId;
	}


	public Long getTheTypeId() {
		return theTypeId;
	}


	public void setTheTypeId(Long theTypeId) {
		this.theTypeId = theTypeId;
	}


	public ConfigValue getConfigFile() {
		return configFile;
	}


	public void setConfigFile(ConfigValue configFile) {
		this.configFile = configFile;
	}
	
	
	
}
