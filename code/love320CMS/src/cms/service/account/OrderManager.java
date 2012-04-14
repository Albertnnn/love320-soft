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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.OrderDao;
import cms.entity.account.OnlineOrder;


//在线订单
@Service
public class OrderManager {
	private OrderDao orderDao;//

	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//获取在线订单
	@Transactional(readOnly=true)
	public OnlineOrder getEntity(Long id) {
		return orderDao.get(id);//获取订单对象
	}

	//搜索
	@Transactional(readOnly=true)
	public Page search(Page page, List<PropertyFilter> filters) {
		return orderDao.findPage(page, filters);
	}

	//保存订单对象
	@Transactional
	public void save(OnlineOrder entity) {
		Date dateTime = new Date();
		entity.setDateTime(dateTime);
		orderDao.save(entity);
	}

	//删除
	@Transactional
	public void delete(Long id) {
		orderDao.delete(id);
	}
	
	
	
	
}
