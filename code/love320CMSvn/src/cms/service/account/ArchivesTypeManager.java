/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.account;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ArchivesTypeManager{
	private ArchivesManager archivesManager;
	private ArctypeManager arctypeManager;
	private SgpageManager sgpageManager;
	
	public ArchivesManager getArchivesManager() {
		return archivesManager;
	}
	
	@Autowired
	public void setArchivesManager(ArchivesManager archivesManager) {
		this.archivesManager = archivesManager;
	}
	public ArctypeManager getArctypeManager() {
		return arctypeManager;
	}
	
	@Autowired
	public void setArctypeManager(ArctypeManager arctypeManager) {
		this.arctypeManager = arctypeManager;
	}

	public SgpageManager getSgpageManager() {
		return sgpageManager;
	}
	
	@Autowired
	public void setSgpageManager(SgpageManager sgpageManager) {
		this.sgpageManager = sgpageManager;
	}
	
	

}
