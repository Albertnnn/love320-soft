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
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.pageList.entity.PageContent;
import cms.service.account.ConfigTemplateManager;
import cms.service.account.PageList;
import cms.service.account.ServicesManager;
import cms.service.account.Template;
import cms.web.CrudActionSupport;

import com.opensymphony.xwork2.ActionSupport;

@Results( {
		@Result(name = CrudActionSupport.RELOAD, location = "searcher.action", type = "redirect")
		})
public class SearcherAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;// 栏目ID
	private PageList pageList; // 页面处理类
	private ServicesManager sm;// 服务层管理对象(服务工厂)
	private int pageNo; // 列表页分每号
	
	private ConfigTemplateManager ctm;//系统模板配置文件管理对象
	
	private PageContent pagecontent = new PageContent();

	@Override
	public String execute() throws Exception {
		// 模板加入核心中-//模板解析
		pageList = sm.getPageList();
		Template tmpl = sm.getTemplate();// 模板解析对象
		
		String searchertmp = ctm.fillconfigFile().getSearch().trim();

		try {
			pagecontent = tmpl.analyticalTmp(searchertmp, "searcher.ftl",pagecontent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.execute();
	}

	public String searcher() {
		
		List<PropertyFilter> filters = PropertyFilter
				.buildFromHttpRequest(Struts2Utils.getRequest());

		// 模板加入核心中-//模板解析
		pageList = sm.getPageList();
		Template tmpl = sm.getTemplate();// 模板解析对象

		// 加入环境变量说明
		pagecontent.getPfs().add(
				new PropertyFilter("EQI_pageNo", "" + pageNo + ""));// 分页号
		
		for (PropertyFilter py : filters) {
			pagecontent.getPfs().add(py);
		}
		
		String searchertmp = ctm.fillconfigFile().getSearch().trim();

		try {
			pagecontent = tmpl.analyticalTmp(searchertmp, "searcher.ftl",pagecontent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 位置导航
		//pageList.position(id.intValue(), 1);

		return SUCCESS;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public PageList getPageList() {
		return pageList;
	}

	public PageContent getPagecontent() {
		return pagecontent;
	}
	
	@Autowired
	public void setCtm(ConfigTemplateManager ctm) {
		this.ctm = ctm;
	}
	
	

}
