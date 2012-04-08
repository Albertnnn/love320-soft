/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.pageList.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.entity.account.Guestbook;
import cms.entity.account.TagLabel;
import cms.pageList.LabelInterface;
import cms.service.account.GuestbookManager;

@Component
public class GuestBookLabel implements LabelInterface {
	
	private GuestbookManager gm;//留言服务类
	private Page page;
	
	//获取PropertyFilter指定值
	private String pfValue(String pfName ,List<PropertyFilter> filters){
		
		String pfValue = "0";
		//迭代模板代码
		for(PropertyFilter pfTest : filters){
			if(pfTest.getPropertyName().equals(pfName)){
				//迭代代码
				pfValue = pfTest.getMatchValue().toString();
			}
			//System.out.println("propertyFilter:"+pfTest.getPropertyName()+":"+pfTest.getMatchValue());
		}
		
		return pfValue;
	}

	public String getIterativeString(TagLabel tagLabel,
			List<PropertyFilter> filters) {
		
		String strTag = tagLabel.getTagName();
		//System.out.println("参数："+filters.size());
		if(tagLabel.getRow() == 0){
			page = new Page<Guestbook>(10);//每页
		}else{
			page = new Page<Guestbook>(tagLabel.getRow());//每页
		}
		
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.DESC);
		}
		
		page.setPageNo(Integer.parseInt(pfValue("pageNo" ,filters)));
		
		List<PropertyFilter> filtersTest = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		
		
		page = gm.searchArchives(page, filtersTest);
		
		//迭代模板代码

		/*strTag = "<s:iterator value=\"pagecontent.listLabel.get("+pfValue("getNum" ,filters)+").getPage().getResult()\" status=\"st\">"
				+ tagLabel.getBody() + "</s:iterator>";*/
		
		strTag = "<#list pagecontent.listLabel.get("
			+ pfValue("getNum", filters)
			+ ").getPage().getResult() as xx >"+ tagLabel.getBody() + "</#list>";
		
		return strTag;
	}

	public Page getPage() {
		return page;
	}
	
	@Autowired
	public void setGm(GuestbookManager gm) {
		this.gm = gm;
	}
	
	
	
	
	

}
