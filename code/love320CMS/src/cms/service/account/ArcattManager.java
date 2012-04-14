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

import cms.dao.account.ArcattDao;
import cms.entity.account.Arcatt;


@Component
public class ArcattManager {
	
	private ArcattDao arcattDao ; //加入arcattDao
	
	
	//获取所有对象
	@Transactional(readOnly= true)
	public List<Arcatt> getAll(){
		return arcattDao.getAll();
	}
	
	//搜索
	@Transactional(readOnly = true)
	public Page searchArchives(Page page, List<PropertyFilter> filters) {
		return arcattDao.findPage(page, filters);
	}
	
	//获取单体
	@Transactional(readOnly = true)
	public Arcatt getArcatt(Long id){
		return arcattDao.get(id);
	}
	
	//保存对象
	@Transactional
	public void save(Arcatt entity){
		arcattDao.save(entity);
	}
	
	//删除对象
	@Transactional
	public void delete(Long id){
		arcattDao.delete(id);
	}
	
	@Autowired
	public void setArcattDao(ArcattDao arcattDao) {
		this.arcattDao = arcattDao;
	}
	
	
	
	
	
}
