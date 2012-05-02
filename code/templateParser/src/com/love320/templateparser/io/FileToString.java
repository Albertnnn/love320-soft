package com.love320.templateparser.io;

import java.io.File;


/**
 * 获取文件中的字条串
 * FileToString
 * 
 * */
public interface FileToString {
	String get(String path);//从指定路径中获取
	String get(File file);//从文件对象中获取
}
