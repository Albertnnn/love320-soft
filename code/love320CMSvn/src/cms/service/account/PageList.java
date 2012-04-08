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

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.PropertyFilter;
import cms.entity.account.Archives;
import cms.entity.account.Arctype;
import cms.entity.account.TagLabel;
import cms.pageList.LabelInterface;
import cms.pageList.entity.PageContent;
import cms.service.ov.ConfigValue;

@Component
public class PageList{	
	private ArchivesTypeManager atm;//栏目文章处理工厂
	private TagLabelManager tagLabelManager; // 标签解析工厂
	private BeanFactory beanFactory;//spring框架工厂
	private Love320SpringBeanFactory love320SpringBeanFactory;
	private ConfigManager cm;//
	private TagLabel taglabel; // 标签单体
	
	//位置导航
	public void position(int id,int type, PageContent pagecontent){

		ConfigValue configFile = cm.getConfigFile();//系统配置环境对象
		
		String position = "";
		String typeName = "";
		String articleName = "" ;
		String theTypeName = "" ;
		//ID为文章ID
		if(type == 2){
			Archives arc = atm.getArchivesManager().getArchives(new Long(id));
			id = arc.getTypeid();
			articleName = arc.getTitle();
			type = 1;
		}
		//ID栏目
		if(type == 1){	
		while(true){
			Arctype arctype = atm.getArctypeManager().getArctype(new Long(id));
			
			//网站在不根目录下(根目录的子目录中)
			if(!configFile.getCmspath().equals("/")){
				configFile.setCmspath(configFile.getCmspath()+"/");
			}
			
			/* 动态模式与静态模式 现在修改为一个模式
			if(configFile.getMode().trim().equals("0")){
				//查栏目名
				position = ">><a href='"+configFile.getCmspath()+"html/list-"+arctype.getId()+".html'>"+arctype.getTypename()+"</a>"+position;
				//System.out.println("栏目名:"+arctype.getTypename());
				//System.out.println("上级ID>>:"+arctype.getReid()+"|顶级ID>>："+arctype.getTopid());
			}else{
				position = ">><a href='"+configFile.getCmspath()+"html/list-"+arctype.getId()+"/'>"+arctype.getTypename()+"</a>"+position;
			}
			 */
			//现在修改为一个模式
			position = ">><a href='"+configFile.getCmspath()+"html/list-"+arctype.getId()+".html'>"+arctype.getTypename()+"</a>"+position;
			
			
			
			//路径栏目名
			typeName = arctype.getTypename()+"-"+typeName;
			//当前栏目名
			if(theTypeName == ""){
			theTypeName = arctype.getTypename() ;
			}
			
			if(arctype.getReid() == 0){break;}
			id = arctype.getReid();
			//System.out.println(position);
			}
		}
		position = "<a href='"+configFile.getCmspath()+"'>首页</a>"+position;
		
		pagecontent.setPosition(position);
		pagecontent.setTheTypeName(theTypeName);
		pagecontent.setTypeName(theTypeName);
	}
	

	
	//传送一个标签字符串,返回解析文件标签的字符串
	public String AnalyticLabel(String labelStr,PageContent pagecontent){

		//标签名获取标签对象
		taglabel = tagLabelManager.getTagbel(labelStr.trim());
		
		//增加迭代数标记
		pagecontent.getPfs().add(new PropertyFilter("EQI_getNum",""+pagecontent.getListLabel().size()));
		//spring工厂生成对象
		LabelInterface lif = getFacoryLabel(taglabel,pagecontent);

		
		//生成模板迭代对象模板代码
		String tagLabelTmplStr = lif.getIterativeString(taglabel,pagecontent.getPfs());
		//listLabel.add(lif);//增加到listLabel对象列表中
		pagecontent.getListLabel().add(lif);
		
		
		return tagLabelTmplStr;
	} 
	
	//if标签类型spring工厂生成对象的类
	private LabelInterface getFacoryLabel(TagLabel tl,PageContent pagecontent){
		
		beanFactory = love320SpringBeanFactory.getBeanFactory();
		
		if(beanFactory == null){
			System.out.println("beanFactory空");
		}

		if(tl.getTagLabelType() != null){
			
			if((tl.getTagLabelType().getClassName().equals("generalArcPageLabel"))||(tl.getTagLabelType().getClassName().equals("generalShopPageLabel"))){
				//通用文档标签
				pagecontent.setTemIterationId(pagecontent.getListLabel().size());
				
				return (LabelInterface) beanFactory.getBean(tl.getTagLabelType().getClassName());
			}else if(tl.getTagLabelType().getClassName().equals("generalArcTypeLabel")){
				//通用栏目标签
				pagecontent.setTemIterationTyepId(pagecontent.getListLabel().size());
				return (LabelInterface) beanFactory.getBean(tl.getTagLabelType().getClassName());
			}else if((tl.getTagLabelType().getClassName().equals("guestBookLabel"))||(tl.getTagLabelType().getClassName().equals("searcherLabel"))){
				//留言和搜索标签
				pagecontent.setTemIterationId(pagecontent.getListLabel().size());
				return (LabelInterface) beanFactory.getBean(tl.getTagLabelType().getClassName());
			}else{
				return (LabelInterface) beanFactory.getBean(tl.getTagLabelType().getClassName());
			}
		}else{
			return null;
		}

	}
	
	

	@Autowired
	public void setTagLabelManager(TagLabelManager tagLabelManager) {
		this.tagLabelManager = tagLabelManager;
	}

	@Autowired
	public void setAtm(ArchivesTypeManager atm) {
		this.atm = atm;
	}

	@Autowired
	public void setLove320SpringBeanFactory(
			Love320SpringBeanFactory love320SpringBeanFactory) {
		this.love320SpringBeanFactory = love320SpringBeanFactory;
	}

	@Autowired
	public void setCm(ConfigManager cm) {
		this.cm = cm;
	}
	
	

}

