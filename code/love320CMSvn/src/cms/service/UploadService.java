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

import java.io.File;


/*
 * 文件上接口
 * */
public interface UploadService {

	public Boolean uploadFile(File inFile,String dirPath,String uploadFileName);//上传文件
	
	public Boolean uploadFile(File inFile,String uploadFileName);//上传文件
	
	public String reName(String fileName);//重名命
	
	public String mkdir(String dirStr);//创建文件夹
}
