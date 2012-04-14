/**
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

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.GuestbookDao;
import cms.entity.account.Archives;
import cms.entity.account.Guestbook;
import cms.web.CrudActionSupport;

@Component
public class GuestbookManager {
	private GuestbookDao guestbookDao;
	
	
	//留言列表所有文章
	@Transactional(readOnly = true)
	public Page<Guestbook> searchArchives(final Page<Guestbook> page, final List<PropertyFilter> filters){
		return guestbookDao.findPage(page, filters);
	}
	
	//获取单体
	@Transactional(readOnly = true)
	public Guestbook getGuestbook(Long id){
		return guestbookDao.get(id);
	}
	
	
	//保存留言
	@Transactional
	public void Save(Guestbook entity){
		entity.setDtime(new Date());
		guestbookDao.save(entity);
	}
	
	//删除留言
	@Transactional
	public void Delete(Long id){
		guestbookDao.delete(id);
	}

	@Autowired
	public void setGuestbookDao(GuestbookDao guestbookDao) {
		this.guestbookDao = guestbookDao;
	}
	
	
}
