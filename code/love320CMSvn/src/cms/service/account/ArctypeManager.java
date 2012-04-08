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

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;


import cms.dao.account.ArctypeDao;
import cms.entity.account.Arctype;

@Component
public class ArctypeManager {
	
	//创建栏目对象
	private ArctypeDao arctypeDao;
	
	//顶级与子栏目排列
	private Typeunit typeunit;
	private List<Typeunit> typeList;
	
	//获取有序栏目
	public List<Typeunit> getALLTypeunit(){
		return typeunit.ArctypeList();
	}
	
	//获取关系栏目
	public Typeunit getTypeunit(){
		return typeunit.getTyenuit();
	}
	
	//获取栏目列表
	@Transactional(readOnly=true)
	public List<Arctype> getAllArctype(){
		return arctypeDao.getAll("id", true);
	}
	
	//获取顶级栏目
	public List<Typeunit> getTypeTop(){
		return typeunit.getTypeTop();
	}
	
	//获取指点栏目下的子栏目
	@Transactional(readOnly=true)
	public List<Arctype> getAdviceList(Long id){
		return arctypeDao.find("from Arctype where reid = ?", id.intValue());
	}
	
	@Transactional(readOnly=true)
	public Page<Arctype> getAdviceList(Page page,final Criterion... criterions){
		return arctypeDao.findPage(page, criterions);
	}
	
	//获取搜索的内容
	@Transactional(readOnly = true)
	public Page<Arctype> searchArctype(final Page<Arctype> page, final List<PropertyFilter> filters){
		return arctypeDao.findPage(page, filters);
	}
	
	//获取单栏目信息
	@Transactional(readOnly=true)
	public Arctype getArctype(Long id){
		return arctypeDao.get(id);
	}
	
	//克隆操作
	public Arctype newcopy(Arctype arctype){
		Arctype entity = new Arctype();
		entity.setTypename(arctype.getTypename());//栏目名
		entity.setReid(arctype.getReid());//上级栏目
		entity.setTemparticle(arctype.getTemparticle());//内容模板
		entity.setTemplist(arctype.getTemplist());//列表模板
		entity.setKeywords(arctype.getKeywords());//关键字
		entity.setEntitymode(arctype.getEntitymode());//数据模型
		return entity;
	}
	
	//保存栏目
	@Transactional
	public void SaveArctype(Arctype entity){
		
		//顶级栏目变子栏目
		if(entity.getReid() > 0){
			entity.setTopid(getArctype(new Long(entity.getReid())).getTopid());//变更与关联的顶级栏目
		}
		//模板解析后出文件 
		entity.setTemplistname("love320list"+new Date().getTime());
		entity.setTemparticlename("love320arc"+new Date().getTime());
		//保存对象
		arctypeDao.save(entity);
		
		//设置顶级栏目
		if(entity.getReid() <= 0 ){
			//System.out.println("上级栏目:"+entity.getReid());
			entity.setTopid(entity.getId().intValue());
			//entity.setReid(entity.getId().intValue());
		}
		
		//System.out.println("上级栏目:"+entity.getReid());
		//System.out.println("处理后自己栏目:"+entity.getId());
	}
	
	//删除
	@Transactional
	public void deleteArctype(Long id){
		//删除当前栏目
		arctypeDao.delete(id);
		//System.out.println("以删除栏目ID为:"+id);
		
		delArctype(id);
	}
	
	//递归删除栏目与文章
	private void delArctype(Long id){

		//查询上级栏目为当前栏目ID
		List<Arctype> delArctype = arctypeDao.find(Restrictions.eq("reid",id.intValue()));
		
		//for递归删除栏目
		if(delArctype.size() > 0 ){

			for(int i = 0 ; i < delArctype.size();i++){
				//arctypeDao.batchExecute("delete Arctype where reid = ?",delArctype.get(i).getId().intValue());
				//删除文章
				//arctypeDao.batchExecute("UPDATE Archives SET arcrank = -2 WHERE typeid = ?",delArctype.get(i).getId().intValue());
				deleteArctype(delArctype.get(i).getId());
			}
		}
	}
	
	@Autowired
	public void setArctypDao(ArctypeDao arctypeDao) {
		this.arctypeDao = arctypeDao;
	}
	@Autowired
	public void setTypeunit(Typeunit typeunit) {
		this.typeunit = typeunit;
	}
	
	
	
}
