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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.AdvertisingDao;
import cms.entity.account.Advertising;


@Service
public class AdvertisingManager {
	
	private AdvertisingDao advertisingDao;//广告对象Dao对象

	@Autowired
	public void setAdvertisingDao(AdvertisingDao advertisingDao) {
		this.advertisingDao = advertisingDao;
	}

	@Transactional(readOnly=true)
	public Advertising getEntity(Long id) {
		return advertisingDao.get(id);
	}

	@Transactional(readOnly=true)
	public Page search(Page page, List<PropertyFilter> filters) {
		return advertisingDao.findPage(page, filters);
	}

	@Transactional
	public void save(Advertising entity){
		advertisingDao.save(entity);
	}

	@Transactional
	public void delete(Long id) {
		advertisingDao.delete(id);
	}

	
	
	
	
	
}
