package com.templateParser.cache.impl;

import java.util.HashMap;
import java.util.Map;

import com.templateParser.cache.Cache;

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
