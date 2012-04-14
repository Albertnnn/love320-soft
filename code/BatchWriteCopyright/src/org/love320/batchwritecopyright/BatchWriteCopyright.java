/**
 * Copyright (c) 2009-2012 love320.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * @Title: BatchWriteCopyright.java 
 * @Package org.love320.batchwritecopyright 
 * @author love320.com 
 * @date 2012-4-8 下午03:44:30 
 */
package org.love320.batchwritecopyright;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import unicodereader.UnicodeReader;

/** 
 * @ClassName: BatchWriteCopyright 
 * @Description: TODO
 * @author love320.com
 * @date 2012-4-8 下午03:44:30 
 *  
 */
public class BatchWriteCopyright {
	
	
	private List filePathList = new List();//文件列表路径

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BatchWriteCopyright bwc = new BatchWriteCopyright();
		//System.out.println("开始");
		bwc.scanDirFile("D:\\Workspaces\\svn\\code\\love320CMSvn\\src");//批量修改的目录
		//System.out.println("结束");
		List fpl = bwc.getFilePathList();
		List fplnew = new List();
		//System.out.println("开始数 ："+ fpl.getItemCount());
		for(int i = 0 ;i < fpl.getItemCount() ;i++){  //获取指定文件类型列表
			String pathstrtmp =  fpl.getItem(i);
			File fileType = new File(pathstrtmp);
			if(fileType.getName().endsWith("java")){ //指定文件后缀名
				System.out.println(fileType.getPath());
				fplnew.add(pathstrtmp);
			}else{
				//fpl.remove(fpl.getItem(i));
			}
			//System.out.println(fileType.getPath());
		}
		
		fpl = null;//释放对象
		//System.out.println("结束数 ："+ fpl.getItemCount());
		
		String addStrPath = "D:\\Workspaces\\svn\\code\\BatchWriteCopyright\\src\\org\\love320\\batchwritecopyright\\addStr.txt";//添加到被修改的文件-文件中要写添加的内容
		
		for(int i = 0 ;i < fplnew.getItemCount() ;i++){
			System.out.println(i + " - action:"+fplnew.getItem(i).toString());
			bwc.editFile(fplnew.getItem(i).toString(),addStrPath);
		}
		System.out.println("处理数 ："+ fplnew.getItemCount());
	}
	
	private void scanDirFile(String fileDirPath){
		
		File filedir = new File(fileDirPath);
		String[] files = filedir.list();//获取目录所的文件和子目录
		for(String singFileName : files){
			String singFilePath = fileDirPath+"\\"+singFileName;
			File singFile = new File(singFilePath);
			if(singFile.isDirectory()){//如果文件是目录就递归
				//System.out.println("目录 :"+singFilePath);
				scanDirFile(singFilePath);
			}else if(singFile.isFile()){
				//System.out.println("文件 :"+singFile.getName());
				filePathList.add(singFilePath);
			}
		}

	}

	private boolean editFile(String editFilePath,String addStrFilePath){
		//读取要加入文件的内容到内存中
		String memoryStr = readFile(addStrFilePath);
		
		//读取文件的内容到内存中
		String memoryEditStr = readFile(editFilePath);
		
		//合并文件字符串
		String newFileStr = memoryStr + memoryEditStr;
		
		//内容写入文件中
		writerFile(editFilePath,newFileStr);
		
		return true;
	}
	
	private String readFile(String filePath){
		String fileContent = "";
		try {
		
		//读取到内存中
		//InputStreamReader tmpread = new InputStreamReader(new FileInputStream(filePath), "utf-8");
		//读取 utf-8 无rom格式 文件
		BufferedReader bufferedReader = new BufferedReader(new UnicodeReader(new FileInputStream(filePath), Charset.defaultCharset().name()));  
		//BufferedReader bufferedReader = new BufferedReader(tmpread);// 加入文件缓存
		
		// 开始读取
		String lineTXT = null;
		while ((lineTXT = bufferedReader.readLine()) != null) {
			fileContent += lineTXT+"\n";//加入换行符
		}
		
		// 关闭文件流
		bufferedReader.close();
		//tmpread.close();
		
		
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileContent;
	}
	
	private boolean writerFile(String filePath , String fileContent){
		
		//从内存中写入文件中
		try {
			//写入准备
			//OutputStreamWriter CtmpW = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			OutputStreamWriter CtmpW = new OutputStreamWriter(new FileOutputStream(filePath));
			//加入缓存
			BufferedWriter bufferedWriter = new BufferedWriter(CtmpW);
			//写文件
			bufferedWriter.write(fileContent);
			//关闭文件流
			bufferedWriter.close();
			CtmpW.close();
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public List getFilePathList() {
		return filePathList;
	}
	
	

}
