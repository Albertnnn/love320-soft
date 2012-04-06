package com.templateParser.cache;

public interface Cache {
	Object getObject(String key);//获取缓存对象
	boolean putObject(String key, Object object);//添加缓存对象
}
