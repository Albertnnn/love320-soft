/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service;

public interface ObjectFile{
	
	public Object objectOpen(String objectName);
	
	public void objectSave(String objectName,Object object);
	
	
}
