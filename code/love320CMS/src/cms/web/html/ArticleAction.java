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
import org.springside.modules.orm.PropertyFilter;
import cms.entity.account.Archives;
import cms.entity.account.Arctype;
import cms.pageList.entity.PageContent;
import cms.service.account.ArchivesTypeManager;
import cms.service.account.ServicesManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ArticleAction extends ActionSupport implements ModelDriven<Archives>, Preparable {
		
	/**
	 * MyEclipse自动生成的标记
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 首页显示的内容
	 * */
	private ServicesManager sm;//服务层工厂
	private ArchivesTypeManager atm;//工厂类
	private int beforeId;//前一文章
	private int afterId ;//下一文章
	private Archives beforeArc;
	private Archives afterArc;
	private Long id;//文章ID
	
	private Archives entity;
	
	private PageContent pagecontent = new PageContent();
	
	public Archives getModel() {
		return entity;
	}
	

	public void prepare() throws Exception {
		entity = atm.getArchivesManager().getArchives(id);	
		
		beforeId = atm.getArchivesManager().getBeforeId(entity.getTypeid(),entity.getSenddate());//前文章id
		beforeArc = atm.getArchivesManager().getBeforeArc(entity.getTypeid(),entity.getSenddate());//前文章对象
		//System.out.println("前文章的ID为:"+beforeId);
		afterId = atm.getArchivesManager().getAfterId(entity.getTypeid(),entity.getSenddate());//后文章ID
		afterArc = atm.getArchivesManager().getAfterArc(entity.getTypeid(),entity.getSenddate());//后文章对象
		//System.out.println("后文章的ID为:"+afterId);
	}
	
	@Override
	public String execute() throws Exception {
		return pageAction();
	}
	
	//进行页面处理
	private String pageAction(){
		//以文章ID为索引查出模板文件
		Archives entity = atm.getArchivesManager().getArchives(id);
		Arctype arctype = atm.getArctypeManager().getArctype(new Long(entity.getTypeid()));
		//文章的栏目ID
//		love320Core.getTemplate().setTypeid(entity.getTypeid());
//		//文章栏目的同级栏目
//		if(arctype.getReid() != 0){
//			love320Core.getTemplate().setTypeidT(arctype.getReid());
//		}else{
//			love320Core.getTemplate().setTypeidT(entity.getTypeid());
//		}
		
		//模板解析
			//加入环境变量说明
			pagecontent.getPfs().add(new PropertyFilter("EQI_typeId",""+arctype.getId()));//栏目
			pagecontent.setTheTypeId(new Long(arctype.getTopid()));//顶级栏目
			
			try {
				pagecontent = sm.getTemplate().analyticalTmp(arctype.getTemparticle(),arctype.getTemparticlename()+".ftl",pagecontent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//设置位置导航
			sm.getPageList().position(id.intValue(), 2,pagecontent);
			//System.out.println("位置导航："+pageList.getPosition());
			
		return arctype.getTemparticlename();
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Autowired
	public void setAtm(ArchivesTypeManager atm) {
		this.atm = atm;
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


	public Archives getBeforeArc() {
		return beforeArc;
	}


	public Archives getAfterArc() {
		return afterArc;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}


	
	
	
	
}
