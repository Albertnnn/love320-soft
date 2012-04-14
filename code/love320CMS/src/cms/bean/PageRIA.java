/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.bean;

import org.springside.modules.orm.Page;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/*
 * 富客户端的page转为系统page的变量
 * */
public class PageRIA {
	public static Page RiaToPage(Page page){
		
		//获取dwz排序
		String orderField = Struts2Utils.getRequest().getParameter("orderField");
		String orderDirection = Struts2Utils.getRequest().getParameter("orderDirection");
		if(( orderField != null)&&(orderDirection != null)){
			page.setOrderBy(orderField);
			page.setOrder(orderDirection);
			//System.out.println("数年:"+ orderField + ":" + orderDirection);
		}
		
		//分页数
		String numPerPage = Struts2Utils.getRequest().getParameter("numPerPage");
		if(numPerPage != null){
			page.setPageSize(Integer.valueOf(numPerPage));
		}
		
		//第几页
		String pageNum = Struts2Utils.getRequest().getParameter("pageNum");
		if(pageNum != null){
			page.setPageNo(Integer.valueOf(pageNum));
		}
		return page;
	}
}
