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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.entity.account.TagLabel;
import cms.pageList.LabelInterface;

@Component
public class FeedBackLabel implements LabelInterface {

	public String getIterativeString(TagLabel tagLabel,
			List<PropertyFilter> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page getPage() {
		// TODO Auto-generated method stub
		return null;
	}

}
