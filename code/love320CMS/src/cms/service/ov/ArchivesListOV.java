/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.ov;

import java.util.List;

import org.springside.modules.orm.Page;

import cms.entity.account.Archives;


public class ArchivesListOV {
	private Page<Archives> hivesPage ;

	public void setHivesPage(Page<Archives> hivesPage) {
		this.hivesPage = hivesPage;
	}

	public Page<Archives> getHivesPage() {
		return hivesPage;
	}
	
	

	
}
