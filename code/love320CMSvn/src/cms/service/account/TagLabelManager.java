/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.account;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.TagLabelDao;
import cms.entity.account.TagLabel;


@Component
public class TagLabelManager {
	private TagLabelDao tagLabelDao ;
	private TagLabel entity;
	private ArctypeManager atm;
	
	//获取所有标签
	public List<TagLabel> getAllTagLabel(){
		return tagLabelDao.getAll();
	}
	
	//获取所有标签，用搜索
	@Transactional(readOnly = true)
	public Page<TagLabel> searchArchives(final Page<TagLabel> page, final List<PropertyFilter> filters){
		return tagLabelDao.findPage(page, filters);
	}
	
	//获取单体
	@Transactional(readOnly = true)
	public TagLabel getTagbel(Long id){
		return tagLabelDao.get(id);
	}
	
	//以名称获取单体
	@Transactional(readOnly = true)
	public TagLabel getTagbel(String tagname){
		//System.out.println("进入查询:"+tagname);
		///List<TagLabel> listtext = tagLabelDao.find("from TagLabel where tagName like ?",tagname);
		List<TagLabel> listtext = tagLabelDao.find(Restrictions.eq("tagName",tagname));
		if(listtext.size() > 0){
			return listtext.get(0);
		}
		return null;
	}
	
	//保存
	@Transactional
	public void Save(TagLabel entity){
		tagLabelDao.save(entity);
	}
	
	//删除
	@Transactional
	public void delete(Long id ){
		tagLabelDao.delete(id);
	}
	
	//获取栏目信息
	public List<Typeunit> getListTypeunit(){
		return atm.getALLTypeunit();
	}

	@Autowired
	public void setTagLabelDao(TagLabelDao tagLabelDao) {
		this.tagLabelDao = tagLabelDao;
	}
	
	@Autowired
	public void setAtm(ArctypeManager atm) {
		this.atm = atm;
	}	
	
	
	
}
