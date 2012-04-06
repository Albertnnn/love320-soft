package com.templateParser.cache.impl;

import com.templateParser.cache.Cache;

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
