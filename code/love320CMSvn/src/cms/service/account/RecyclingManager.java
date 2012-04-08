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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.ArchivesDao;
import cms.entity.account.Archives;


@Component
public class RecyclingManager {
	
	private ArchivesDao archivesDao;
	
	//显示所有文章(搜索)
	@Transactional(readOnly = true)
	public Page<Archives> searchArchives(final Page<Archives> page, final List<PropertyFilter> filters){
		return archivesDao.findPage(page, filters);
	}
	
	
	//删除文档
	@Transactional
	public void delete(Long id){
		archivesDao.delete(id);
	}
	
	//还原文档
	@Transactional
	public void reduction(Long id){
		archivesDao.get(id).setArcrank(0);
	}

	@Autowired
	public void setArchivesDao(ArchivesDao archivesDao) {
		this.archivesDao = archivesDao;
	}
	
	
	
	
}
