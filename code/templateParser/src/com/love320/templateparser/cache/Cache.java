/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package com.love320.templateparser.cache;

public interface Cache {
	Object getObject(String key);//获取缓存对象
	boolean putObject(String key, Object object);//添加缓存对象
}
