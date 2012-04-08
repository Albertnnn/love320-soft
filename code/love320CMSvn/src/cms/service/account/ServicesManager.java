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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//服务层管理类

@Component
public class ServicesManager {
	private TagLabelTypeManager tagLabelTypeManager;//标签类型管理对象
	private ArchivesManager archivesManager;//文章管理对象
	private ArctypeManager arctypeManager;//栏目管理对象
	private PageList pageList;//标签管理对象(系统)
	private Template template ;//模板解析对象
	private EntityModeManager entityModeManager;//数据模型管理对象
	private ShopManager shopManager ;//商品管理对象
	private ArcattManager arcattManager ;//文档属性管理对象
	private OrderManager orderManager;//在线订单管理对象
	private ConfigManager configManager ;//系统设置管理对象
	private HTMLAllPageManager htmlAllPageManager;//静态页面生成管理对象
	private AdvertisingManager advertising;//广告管理对象

	public TagLabelTypeManager getTagLabelTypeManager() {
		return tagLabelTypeManager;
	}
	
	@Autowired
	public void setTagLabelTypeManager(TagLabelTypeManager tagLabelTypeManager) {
		this.tagLabelTypeManager = tagLabelTypeManager;
	}

	public ArchivesManager getArchivesManager() {
		return archivesManager;
	}
	
	@Autowired
	public void setArchivesManager(ArchivesManager archivesManager) {
		this.archivesManager = archivesManager;
	}

	public PageList getPageList() {
		return pageList;
	}
	
	@Autowired
	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public Template getTemplate() {
		return template;
	}
	
	@Autowired
	public void setTemplate(Template template) {
		this.template = template;
	}

	public EntityModeManager getEntityModeManager() {
		return entityModeManager;
	}
	
	@Autowired
	public void setEntityModeManager(EntityModeManager entityModeManager) {
		this.entityModeManager = entityModeManager;
	}

	public ArctypeManager getArctypeManager() {
		return arctypeManager;
	}

	@Autowired
	public void setArctypeManager(ArctypeManager arctypeManager) {
		this.arctypeManager = arctypeManager;
	}

	public ShopManager getShopManager() {
		return shopManager;
	}

	@Autowired
	public void setShopManager(ShopManager shopManager) {
		this.shopManager = shopManager;
	}

	public ArcattManager getArcattManager() {
		return arcattManager;
	}

	@Autowired
	public void setArcattManager(ArcattManager arcattManager) {
		this.arcattManager = arcattManager;
	}

	public OrderManager getOrderManager() {
		return orderManager;
	}

	@Autowired
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	@Autowired
	public void setConfigManager(ConfigManager configManager) {
		this.configManager = configManager;
	}

	public HTMLAllPageManager getHtmlAllPageManager() {
		return htmlAllPageManager;
	}

	@Autowired
	public void setHtmlAllPageManager(HTMLAllPageManager htmlAllPageManager) {
		this.htmlAllPageManager = htmlAllPageManager;
	}

	public AdvertisingManager getAdvertising() {
		return advertising;
	}

	@Autowired
	public void setAdvertising(AdvertisingManager advertising) {
		this.advertising = advertising;
	}

	
	
	
}
