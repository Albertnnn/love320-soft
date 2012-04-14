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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.SgpageDao;
import cms.entity.account.Sgpage;
import cms.entity.account.TagLabel;


@Component
public class SgpageManager {
	private SgpageDao sgpageDao;
	
	//获取所有单页面
	@Transactional(readOnly = true)
	public List<Sgpage> getAllSgpage(){
		return sgpageDao.getAll();
	}
	
	//获取单体
	@Transactional(readOnly = true)
	public Sgpage getSgpage(Long id){
		return sgpageDao.get(id);
	}
	
	//保存对象
	@Transactional
	public void Save(Sgpage entity){
		entity.setFileName("love320cmssgpage"+new Date().getTime());//设置单页面输出文件名
		sgpageDao.save(entity);//保存对象		
	}
	
	//搜索
	@Transactional
	public Page<Sgpage> searchArchives(final Page<Sgpage> page, final List<PropertyFilter> filters){
		return sgpageDao.findPage(page, filters);
	}
	
	//删除以ID对象
	@Transactional
	public void delete(Long id){
		sgpageDao.delete(id);
	}
	
	@Autowired
	public void setSgpageDao(SgpageDao sgpageDao) {
		this.sgpageDao = sgpageDao;
	}

	
	
	
}
