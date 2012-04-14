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

import cms.entity.account.Archives;
import cms.entity.account.Arctype;
import cms.entity.account.TagLabel;
import cms.pageList.LabelInterface;
import cms.service.account.ArchivesTypeManager;

@Component
public class GeneralArcPageLabel implements LabelInterface {

	private Page<Archives> page;

	private ArchivesTypeManager atm;

	// 获取PropertyFilter指定值
	private String pfValue(String pfName, List<PropertyFilter> filters) {

		String pfValue = "0";
		// 迭代模板代码
		for (PropertyFilter pfTest : filters) {
			if (pfTest.getPropertyName().equals(pfName)) {
				// 迭代代码
				pfValue = pfTest.getMatchValue().toString();
			}
			// System.out.println("propertyFilter:"+pfTest.getPropertyName()+":"+pfTest.getMatchValue());
		}

		return pfValue;
	}
	
	//递归
	private String arctypeidStr(Long id){
		List<Arctype> arctypeList = atm.getArctypeManager().getAdviceList(id);
		String typeidStr ="";
		for (Arctype arctypeTest : arctypeList) {
			typeidStr += "," + arctypeTest.getId();
			typeidStr += arctypeidStr(arctypeTest.getId());
		}
		return typeidStr;
	}

	public String getIterativeString(TagLabel tagLabel,
			List<PropertyFilter> filters) {

		String strTag = tagLabel.getTagName();

		// System.out.println("得到3:"+pageNo);

		//递归获取所有子栏目	
		String typeidStr = pfValue("typeId", filters);//第一个为当前栏目ID
		typeidStr +=arctypeidStr(new Long(Integer.parseInt(pfValue("typeId", filters))));//子栏目的所有栏目ID


		// 是否设置了文档的排序
		if ((!tagLabel.getOrderby().trim().equalsIgnoreCase(""))
				&& (!tagLabel.getOrderbyType().trim().equalsIgnoreCase(""))) {

			addArchivesList(tagLabel.getRow(), Integer.parseInt(pfValue(
					"pageNo", filters)), tagLabel.getOrderby(), tagLabel
					.getOrderbyType(), Restrictions.ge("arcrank", 0),Restrictions.sqlRestriction("typeid in ("+typeidStr+")"));

		} else {// 没有设置了文档的排序
			System.out.println("B:"+typeidStr);
			addArchivesList(tagLabel.getRow(), Integer.parseInt(pfValue(
					"pageNo", filters)), "senddate", "desc", Restrictions.ge(
					"arcrank", 0), Restrictions.sqlRestriction("typeid in ("+typeidStr+")"));
		}

		// 迭代模板代码

		/*strTag = "<s:iterator value=\"pagecontent.listLabel.get("
				+ pfValue("getNum", filters)
				+ ").getPage().getResult()\" status=\"st\">"
				+ tagLabel.getBody() + "</s:iterator>";*/
		strTag = "<#list pagecontent.listLabel.get("
			+ pfValue("getNum", filters)
			+ ").getPage().getResult() as xx >"+ tagLabel.getBody() + "</#list>";

		return strTag;
	}

	// 新增文章信息(默认排序)
	public void addArchivesList(int size, final Criterion... criterions) {
		addArchivesList(size, 1, null, null, criterions);
	}

	// 新增文章信息(指定排序)
	public void addArchivesList(int size, int pageNo, String orderBy,
			String order, final Criterion... criterions) {
		// 每页
		if (size == 0)
			size = 10;
		page = new Page<Archives>(size);
		// 设置排序方式
		if ((orderBy != null) && (order != null)) {
			page.setOrderBy(orderBy);
			page.setOrder(order);
		}
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("sortrank");
			page.setOrder(Page.DESC);
		}
		// 指定第pageNo页
		if (pageNo > 0) {
			page.setPageNo(pageNo);
		}
		// 从数据库中抽出文章数据
		page = atm.getArchivesManager().getAdviceList(page, criterions);
		// 实例化文章对象

		// System.out.println("hivesList数："+hivesList.size());
		// System.out.println(">>>>"+hivesList.get(0).getHivesPage().getResult().get(0));
	}

	public Page getPage() {
		return page;
	}

	@Autowired
	public void setAtm(ArchivesTypeManager atm) {
		this.atm = atm;
	}

}
