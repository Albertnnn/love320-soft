package cms.pageList.entity;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.entity.account.TagLabel;
import cms.pageList.LabelInterface;

@Component
@Scope("prototype")
public class StaticLabel implements LabelInterface {

	public String getIterativeString(TagLabel tagLabel,
			List<PropertyFilter> filters) {
		
		return tagLabel.getBody();
	}

	public Page getPage() {
		return null;
	}

}
