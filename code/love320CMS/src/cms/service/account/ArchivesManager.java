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


import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.ArchivesDao;
import cms.entity.account.Archives;
import cms.entity.account.Arctype;

@Component
@Transactional
public class ArchivesManager{
	private ArchivesDao archivesDao;
	
	
	//栏目管理，提供栏目列表
	private ArctypeManager arctypeManager;
	
	//静态页面生成管理
	private HTMLAllPageManager hapm;
	
	//显示所有文章(搜索)
	@Transactional(readOnly = true)
	public Page<Archives> searchArchives(final Page<Archives> page, final List<PropertyFilter> filters){
		return archivesDao.findPage(page, filters);
	}
	
	//显示所有文章
	@Transactional(readOnly = true)
	public List<Archives> getAllArchives(){
		return archivesDao.getAll();
	}
	
	//获取所有栏目
	@Transactional(readOnly = true)
	public List<Arctype> getAllArctype(){
		return arctypeManager.getAllArctype();
	}
	//获取所有规范栏目
	@Transactional(readOnly = true)
	public List<Typeunit> getTypeunit(){
		return arctypeManager.getALLTypeunit();
	}
	
	//获得单体
	@Transactional(readOnly = true)
	public Archives getArchives(Long id){
		return archivesDao.get(id);
	}
	
	//获取指点栏目ID的所有文章
	@Transactional(readOnly = true)
	public Page<Archives> getAdviceList(final Page<Archives> page , final Criterion... criterions){
		return archivesDao.findPage(page , criterions);
	}
	
	//获取多条件的所有文章
	
	//获取文章的前一张文章
	@Transactional(readOnly = true)
	public int getBeforeId(int Typeid,Date senddate){
		List<Archives> testList = archivesDao.find("from Archives where typeid="+Typeid+" and senddate<'"+senddate+"' order by senddate desc");
		if(testList.size() > 0){
			return testList.get(0).getId().intValue();
		}else{
			return 0;
		}
	}
	//获取文章的后一张文章
	@Transactional(readOnly = true)
	public int getAfterId(int Typeid,Date senddate){
		List<Archives> testList = archivesDao.find("from Archives where typeid="+Typeid+" and senddate>'"+senddate+"' order by senddate desc");
		if(testList.size() > 0){
			return testList.get((testList.size()-1)).getId().intValue();
		}else{
			return 0;
		}
	}
	
	//获取文章的前一张文章
	@Transactional(readOnly = true)
	public Archives getBeforeArc(int Typeid,Date senddate){
		List<Archives> testList = archivesDao.find("from Archives where typeid="+Typeid+" and senddate<'"+senddate+"' order by senddate desc");
		if(testList.size() > 0){
			return testList.get(0);
		}else{
			return null;
		}
	}
	//获取文章的后一张文章
	@Transactional(readOnly = true)
	public Archives getAfterArc(int Typeid,Date senddate){
		List<Archives> testList = archivesDao.find("from Archives where typeid="+Typeid+" and senddate>'"+senddate+"' order by senddate desc");
		if(testList.size() > 0){
			return testList.get((testList.size()-1));
		}else{
			return null;
		}
	}
	
	//保存
	@Transactional
	public void SaveAcrhives(Archives entity){
		Date newDate = new Date();//初始化时间对象
		if(entity.getId() != null) entity.setSortrank(newDate);
		if(entity.getSenddate() == null){
			entity.setSenddate(newDate);//设置文章对象的录入时间
		}
		entity.setPubdate(newDate);//设置文章对象修改时间
		Arctype arctype = arctypeManager.getArctype(new Long(entity.getTypeid()));//以文章栏目ID获取栏目对象
		entity.setArctype(arctype);//关联文章与栏目
		archivesDao.save(entity);//保存文章对象
		
		//生成静态页面
		hapm.htmlOneArticle(entity.getId());
	}
	
	//保存
	@Transactional
	public void SaveAcrhivesEntity(Archives entity){
		archivesDao.save(entity);//保存文章对象
	}
	
	//删除
	@Transactional
	public void DeleteArchives(Long id){
		archivesDao.get(id).setArcrank(-2);
		//archivesDao.delete(id);
	}
	
	@Autowired
	public void setAcrhivesDao(ArchivesDao archivesDao) {
		this.archivesDao = archivesDao;
	}
	
	@Autowired
	public void setArctypeManager(ArctypeManager arctypeManager) {
		this.arctypeManager = arctypeManager;
	}
	
	@Autowired
	public void setHapm(HTMLAllPageManager hapm) {
		this.hapm = hapm;
	}
	
	
	
}
