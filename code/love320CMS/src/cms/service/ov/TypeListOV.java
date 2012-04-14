/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.ov;

import org.springside.modules.orm.Page;

import cms.entity.account.Arctype;

public class TypeListOV {
	private Page<Arctype> typePage;

	public Page<Arctype> getTypePage() {
		return typePage;
	}

	public void setTypePage(Page<Arctype> typePage) {
		this.typePage = typePage;
	}

	
	
}
