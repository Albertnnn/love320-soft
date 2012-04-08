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

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.FeedBackDao;
import cms.entity.account.Feedback;

//评论管理

@Component
public class FeedBackManager {
	
	private FeedBackDao feedbackDao ;//评论管理类
	
	//获取单体
	@Transactional(readOnly = true)
	public Feedback getEntity(Long id){
		return feedbackDao.get(id);
	}
	
	//搜索
	@Transactional(readOnly = true)
	public Page searchArchives(final Page page,final List<PropertyFilter> filters) {
		feedbackDao.findPage(page , filters);
		return page;
	}
	
	//保存评论
	@Transactional
	public void save(Feedback entity){
		entity.setDtime(new Date());
		feedbackDao.save(entity);
	}
	
	//删除评论
	@Transactional
	public void delete(Long id){
		feedbackDao.delete(id);
	}
	
	
	@Autowired
	public void setFeedbackDao(FeedBackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}
	
	
	
}
