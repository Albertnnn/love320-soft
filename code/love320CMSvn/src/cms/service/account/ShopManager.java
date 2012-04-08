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

import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.ShopDao;
import cms.entity.account.Shop;

//商品管理服务
@Service
public class ShopManager {
	private ShopDao shopDao;//商品Dao

	//获取商品对象
	@Transactional(readOnly = true)
	public Shop getEntity(Long id) {
		return shopDao.get(id);
	}
	
	//保存商品对象
	@Transactional
	public void save(Shop entity) {
		shopDao.save(entity);//保存商品对象
	}

	@Autowired
	public void setShopDao(ShopDao shopDao) {
		this.shopDao = shopDao;
	}

	//搜索
	@Transactional(readOnly = true)
	public Page search(Page page, Criterion[] criterions) {
		return shopDao.findPage(page, criterions);//
	}
	
	//搜索
	@Transactional(readOnly = true)
	public Page search(Page page, List<PropertyFilter> filters) {
		return shopDao.findPage(page, filters);//
	}

	//删除商品对象
	@Transactional
	public void delete(Long id) {
		shopDao.delete(id);
	}

	//获取所有商品
	@Transactional
	public List<Shop> getAll() {
		return shopDao.getAll();
	}
	
	

}
