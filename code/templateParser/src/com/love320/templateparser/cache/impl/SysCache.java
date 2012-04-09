/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.templateparser.cache.impl;

import java.util.HashMap;
import java.util.Map;

import com.templateparser.cache.Cache;

public class SysCache implements Cache {
	
	private Map<String, Object> data = new HashMap<String, Object>(); //缓存  

	@Override
	public Object getObject(String key) {
		return data.get(key);
	}

	@Override
	public boolean putObject(String key, Object object) {
		data.put(key, object);
		return true;
	}

}
