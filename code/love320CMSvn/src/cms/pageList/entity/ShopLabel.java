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
import cms.entity.account.Shop;
import cms.entity.account.TagLabel;
import cms.pageList.LabelInterface;
import cms.service.account.ServicesManager;
import cms.service.account.ShopManager;

@Component
@Scope("prototype")
public class ShopLabel implements LabelInterface {

	private Page<Shop> page;
	private ServicesManager sm;//

	// 获取PropertyFilter指定值
	private String pfValue(String pfName, List<PropertyFilter> filters) {

		String pfValue = "0";
		// 迭代模板代码
		for (PropertyFilter pfTest : filters) {
			if (pfTest.getPropertyName().equals(pfName)) {
				// 迭代代码
				pfValue = pfTest.getMatchValue().toString();
			}
		}

		return pfValue;
	}

	public String getIterativeString(TagLabel tagLabel,
			List<PropertyFilter> filters) {
		String strTag = tagLabel.getTagName();// 标签解析模板字符串

		if (tagLabel == null)
			return strTag;

		// 文章

		// 是否设置了文档的排序
		if ((!tagLabel.getOrderby().trim().equalsIgnoreCase(""))
				&& (!tagLabel.getOrderbyType().trim().equalsIgnoreCase(""))) {

			String arcattStr = null;// 标签的文档属性字符串
			for (Long flagid : tagLabel.getflagIds()) {
				arcattStr += "," + flagid;
			}
			// 定义标签调用文档属性
			if (arcattStr != null) {
				// 标签中栏目为0,所有栏目
				if (tagLabel.getTypeid() == 0) {
					addArchivesList(
							tagLabel.getRow(),
							1,
							tagLabel.getOrderby(),
							tagLabel.getOrderbyType(),
							Restrictions.ge("arcrank", 0),
							Restrictions
									.sqlRestriction("shop_id in  ( select shop_id from shop_arcatt where arcatt_id in("
											+ arcattStr + ") )"));
				} else {
					addArchivesList(
							tagLabel.getRow(),
							1,
							tagLabel.getOrderby(),
							tagLabel.getOrderbyType(),
							Restrictions.eq("typeid", tagLabel.getTypeid()),
							Restrictions.ge("arcrank", 0),
							Restrictions
									.sqlRestriction("shop_id in  ( select shop_id from shop_arcatt where arcatt_id in("
											+ arcattStr + ") )"));
				}
			} else { // 没有定义标签调用文档属性

				// 标签中栏目为0,所有栏目
				if (tagLabel.getTypeid() == 0) {
					addArchivesList(tagLabel.getRow(), 1,
							tagLabel.getOrderby(), tagLabel.getOrderbyType(),
							Restrictions.ge("arcrank", 0));
				} else {
					addArchivesList(tagLabel.getRow(), 1,
							tagLabel.getOrderby(), tagLabel.getOrderbyType(),
							Restrictions.ge("arcrank", 0), Restrictions.eq(
									"typeid", tagLabel.getTypeid()));
				}

			}

		} else { // 没有设置了文档的排序
			// 标签中栏目为0,所有栏目

			if (tagLabel.getTypeid() == 0) {
				addArchivesList(tagLabel.getRow(), 1, "senddate", "desc",
						Restrictions.ge("arcrank", 0), Restrictions.eq(
								"typeid", tagLabel.getTypeid()));
			} else {
				addArchivesList(tagLabel.getRow(), 1, "senddate", "desc",
						Restrictions.ge("arcrank", 0));
			}

		}

		strTag = "<s:iterator value=\"pagecontent.listLabel.get("
				+ pfValue("getNum", filters)
				+ ").getPage().getResult()\" status=\"st\">"
				+ tagLabel.getBody() + "</s:iterator>";

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
		page = new Page<Shop>(size);
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
		ShopManager shopM = sm.getShopManager();//服务工厂生产商品服务对象
		// 从数据库中抽出文章数据
		page = shopM.search(page, criterions);
	}

	public Page getPage() {
		return page;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}

}
