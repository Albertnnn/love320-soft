/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.pageList.entity;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.entity.account.Arctype;
import cms.entity.account.TagLabel;
import cms.pageList.LabelInterface;
import cms.service.account.ArchivesTypeManager;

@Component
@Scope("prototype")
public class ArcTypeLabel implements LabelInterface {
	
	private Page<Arctype> page;
	
	private ArchivesTypeManager atm;
	
	//获取PropertyFilter指定值
	private String pfValue(String pfName ,List<PropertyFilter> filters){
		
		String pfValue = "0";
		//迭代模板代码
		for(PropertyFilter pfTest : filters){
			if(pfTest.getPropertyName().equals(pfName)){
				//迭代代码
				pfValue = pfTest.getMatchValue().toString();
			}
		}
		
		return pfValue;
	}

	public String getIterativeString(TagLabel tagLabel, List<PropertyFilter> filters ) {
		
		String strTag = tagLabel.getTagName();
		
		addTypeList(tagLabel.getRow(), Restrictions.eq("reid",
				tagLabel.getTypeid()),Restrictions.eq("ishidden", 0));

		//迭代模板代码

		/*strTag = "<s:iterator value=\"pagecontent.listLabel.get("+pfValue("getNum" ,filters)+").getPage().getResult()\" status=\"st\">"
				+ tagLabel.getBody() + "</s:iterator>";*/
		
		strTag = "<#list pagecontent.listLabel.get("
			+ pfValue("getNum", filters)
			+ ").getPage().getResult() as xx >"+ tagLabel.getBody() + "</#list>";
	
		return strTag;
	}
	
	//新增栏目条信息(默认排序)
	public void addTypeList(int size,final Criterion... criterions){
		addTypeList(size,null,null,criterions);
	}
	//新增栏目条信息(指定排序)
	public void addTypeList(int size,String orderBy,String order,final Criterion... criterions){
		//每页
		if(size == 0 ) size =10 ;
		page = new Page<Arctype>(size);
		//设置排序方式
		if((orderBy != null)&&(order != null)){
			page .setOrderBy(orderBy);
			page .setOrder(order);
		}
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("sortrank");
			page.setOrder(Page.DESC);
		}
		//从数据库中抽出文章数据
		page = atm.getArctypeManager().getAdviceList(page, criterions);
		//实例化文章对象
	}
	

	public Page getPage() {
		return page;
	}
	
	@Autowired
	public void setAtm(ArchivesTypeManager atm) {
		this.atm = atm;
	}

	
	
	
}
