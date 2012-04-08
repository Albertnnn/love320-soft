/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.account;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cms.bean.PageRIA;
import cms.bean.RiaJsonObject;
import cms.dao.HibernateUtils;
import cms.entity.account.Role;
import cms.entity.account.User;
import cms.service.ServiceException;
import cms.service.account.AccountManager;
import cms.web.CrudActionSupport;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

/**
 * 用户管理Action.
 * 
 * 使用Struts2 convention-plugin annotation定义Action参数.
 * 演示带分页的管理界面.
 * 
 * @author calvin
 */
//定义URL映射对应/account/user.action
@Namespace("/account")
//定义名为reload的result重定向到user.action, 其他result则按照convention默认.
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "user.action", type = "redirect") })
public class UserAction extends CrudActionSupport<User> {

	private static final long serialVersionUID = 8683878162525847072L;

	private AccountManager accountManager;

	//-- 页面属性 --//
	private Long id;
	private User entity;
	private Page<User> page = new Page<User>(10);//每页10条记录
	private List<Long> checkedRoleIds; //页面中钩选的角色id列表

	//-- ModelDriven 与 Preparable函数 --//
	public void setId(Long id) {
		this.id = id;
	}

	public User getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = accountManager.getUser(id);
		} else {
			entity = new User();
		}
	}

	//-- CRUD Action 函数 --//
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		
		page = PageRIA.RiaToPage(page);//富客户端的page转为系统page的变量
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = accountManager.searchUser(page, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		checkedRoleIds = entity.getRoleIds();
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		//根据页面上的checkbox选择 整合User的Roles Set
		HibernateUtils.mergeByCheckedIds(entity.getRoleList(), checkedRoleIds, Role.class);

		accountManager.saveUser(entity);
		addActionMessage("保存用户成功");

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("保存成功", "user","closeCurrent"));

		return null;
	}

	@Override
	public String delete() throws Exception {
		try {
			accountManager.deleteUser(id);
			addActionMessage("删除用户成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除用户失败");
		}

		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject("删除成功", "user","forward"));
		
		return null;
	}

	//-- 其他Action函数 --//
	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkLoginName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newLoginName = request.getParameter("loginName");
		String oldLoginName = request.getParameter("oldLoginName");

		if (accountManager.isLoginNameUnique(newLoginName, oldLoginName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<User> getPage() {
		return page;
	}

	/**
	 * input页面显示所有角色列表.
	 */
	public List<Role> getAllRoleList() {
		return accountManager.getAllRole();
	}

	/**
	 * input页面显示用户拥有的角色.
	 */
	public List<Long> getCheckedRoleIds() {
		return checkedRoleIds;
	}

	/**
	 * input页面提交用户拥有的角色.
	 */
	public void setCheckedRoleIds(List<Long> checkedRoleIds) {
		this.checkedRoleIds = checkedRoleIds;
	}

	@Autowired
	public void setAccountManager(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
}
