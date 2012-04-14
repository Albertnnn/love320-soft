/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.html;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import cms.entity.account.Archives;
import cms.entity.account.Arctype;
import cms.entity.account.Shop;
import cms.pageList.entity.PageContent;
import cms.service.account.ArchivesManager;
import cms.service.account.ArchivesTypeManager;
import cms.service.account.ArctypeManager;
import cms.service.account.PageList;
import cms.service.account.ServicesManager;
import cms.service.account.ShopManager;
import cms.service.account.Template;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ShopAction extends ActionSupport implements ModelDriven<Shop>, Preparable {
		
	/**
	 * MyEclipse自动生成的标记
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 首页显示的内容
	 * */
	private ServicesManager sm;//服务层工厂
	private int beforeId;//前一文章
	private int afterId ;//下一文章
	private Long id;//文章ID
	
	private Shop entity;
	
	private PageContent pagecontent = new PageContent();
	
	public Shop getModel() {
		return entity;
	}
	

	public void prepare() throws Exception {
		ShopManager shopM = sm.getShopManager();//从服务层工厂中获取文章服务对象
		
		entity = shopM.getEntity(id);	
		
//		beforeId = arc.getBeforeId(entity.getTypeid(),entity.getSenddate());
//		//System.out.println("前文章的ID为:"+beforeId);
//		afterId = arc.getAfterId(entity.getTypeid(),entity.getSenddate());
//		//System.out.println("后文章的ID为:"+afterId);
	}
	
	@Override
	public String execute() throws Exception {
		return pageAction();
	}
	
	//进行页面处理
	private String pageAction(){
		ShopManager shopM = sm.getShopManager();//从服务层工厂中获取文章服务对象
		ArctypeManager arctypeM = sm.getArctypeManager();//从服务层工厂中获取栏目服务对象
		//以文章ID为索引查出模板文件
		Shop entity = shopM.getEntity(id);	
		Arctype arctype = arctypeM.getArctype(new Long(entity.getTypeid()));
		//文章的栏目ID
//		love320Core.getTemplate().setTypeid(entity.getTypeid());
//		//文章栏目的同级栏目
//		if(arctype.getReid() != 0){
//			love320Core.getTemplate().setTypeidT(arctype.getReid());
//		}else{
//			love320Core.getTemplate().setTypeidT(entity.getTypeid());
//		}
		
		//模板解析
			//tmp.analyticalTmp(arctype.getTemparticle(),arctype.getTemparticlename()+".jsp");
			Template tmpl = sm.getTemplate();
			try {
				pagecontent = tmpl.analyticalTmp(arctype.getTemparticle(),arctype.getTemparticlename()+".jsp",pagecontent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//设置位置导航
			//pageList.position(arctype.getId().intValue(), 1,pagecontent);
			//System.out.println("位置导航："+pageList.getPosition());
			
		return arctype.getTemparticlename();
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}


	public int getBeforeId() {
		return beforeId;
	}


	public int getAfterId() {
		return afterId;
	}


	public PageContent getPagecontent() {
		return pagecontent;
	}


	
	
	
	
}
