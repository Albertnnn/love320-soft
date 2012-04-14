/**
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

import cms.dao.account.AuthorityDao;
import cms.entity.account.Authority;


@Component
public class AuthorityManager {
		
	
	private AuthorityDao authorityDao;//权限.Dao
	
	@Transactional(readOnly = true)
	public Authority getAuthority(Long id){
		return authorityDao.get(id);
	}
	
	@Transactional
	public void save(Authority entity){
		authorityDao.save(entity);
	}
	
	//删除
	@Transactional
	public void delete(Long id){
		authorityDao.delete(id);
	}
	
	@Autowired
	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}
	
	@Transactional(readOnly = true)
	public Page searchAuthority(final Page<Authority> page, final List<PropertyFilter> filters) {
		return authorityDao.findPage(page,filters);
	}
	
	
	
}
