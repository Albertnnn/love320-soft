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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cms.dao.account.MemberClassNoticeDao;
import cms.entity.account.MemberClassNotice;

@Service
public class MemberClassNoticeManager {
	private MemberClassNoticeDao mcnDao;


	@Autowired
	public void setMcnDao(MemberClassNoticeDao mcnDao) {
		this.mcnDao = mcnDao;
	}


	@Transactional
	public void save(MemberClassNotice entity) {
		if(entity.getMember().getMemberClassLists().size() > 0){
			entity.setMemberClass(entity.getMember().getMemberClassLists().get(0));//设置班级
		}
		
		entity.setSendDate(new Date());
		mcnDao.save(entity);
		
	}

	@Transactional
	public void delete(Long id) {
		mcnDao.delete(id);
	}
	
	
	
}
