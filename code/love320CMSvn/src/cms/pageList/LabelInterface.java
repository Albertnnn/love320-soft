package cms.pageList;


import java.util.List;

import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.entity.account.TagLabel;

public interface LabelInterface {
	
	//获取<list> 数据
	public Page getPage();
	
	//解析迭代字符串
	public String getIterativeString(TagLabel tagLabel ,List<PropertyFilter> filters );
	
	
}
