/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.templateparser.cache.impl;

import com.templateparser.cache.Cache;

public class EhcacheFactory implements Cache{

	@Override
	public Object getObject(String key) {
		return null;
	}

	@Override
	public boolean putObject(String key, Object object) {
		return false;
	}
	
}
