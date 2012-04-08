/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.account;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.springframework.beans.factory.annotation.Autowired;
import cms.service.UploadService;

import com.opensymphony.xwork2.ActionSupport;


//文件上传类

//文件类型过滤
@InterceptorRef(value="defaultStack",params={
"fileUpload.allowedTypes","image/bmp,image/png,image/gif,image/jpeg,image/pjpeg,image/jpg,image/x-png,application/x-shockwave-flash,application/octet-stream"
})
public class UpfileAction extends ActionSupport {
	
	private String revarname ;//用于JS返回给父窗口的对象名
	private File upload ;// 上传的文件
	private String uploadFileName;//上传的文件名
	private String uploadContentType;//上传的文件类型
	private String fileName;//重命名文件
	private String[] files;//文件列表
	private String[] dirs;//目录列表
	private String dirRoot;//管理文件的跟目录
	private String dirThe ;//当前的目录
	private String dir;//查询的目录
	
	private UploadService uploadservice;//上传文件服务
	
	@Override
	public String execute() throws Exception {
		return list();
	}
	
	//返回上级目录
	public void returnStr(){
		int Rindex = dirThe.lastIndexOf("/");
		dir = "" ;
		//IF为DirRoot目录
		if(Rindex > 0){
			dirThe = dirThe.substring(0, Rindex);
		}else{
			dirThe = "";
		}
		
		//System.out.println("最右边出现的索引："+Rindex+dirThe);
		//return list();
	}
	
	//显示文件夹的所有文件
	private String list(){
		
		//返回上级目录
		if(dir.equals("revarname")){
			returnStr();
		}
		
		if((!dir.isEmpty())&&(dirThe != "/")){
			dirThe = dirThe +"/"+dir;
		}else{
			if((dirThe.equals("/"))) dirThe = dir;
		}
		//System.out.println("dirThe:"+dirThe);
		//检测并修正查询目录的路径合法性
//		if(!dirThe.isEmpty()){
//			dir = this.getClass().getResource("/").getPath()+"../../"+dirRoot+"/"+dirThe+"/";
//		}else{
//			dir = this.getClass().getResource("/").getPath()+"../../"+dirRoot+"/";
//		}
		dir = this.getClass().getResource("/").getPath()+"../../"+dirRoot+dirThe+"/";//当前文件夹下的目录和文件
		//System.out.println(dir);
		upload = new File(dir);
		files = upload.list();
		
		//文件与目录分离
		int fNum = 0;//文件数
		int dNum = 0;//文件数
		String fNumStr = "";//文件组名字符串
		String dNumStr = "";//目录组名字符串
		//统计文件数与目录数
		for(int i = 0 ;i < files.length ;i++){
			File testFD = new File(dir+files[i].toString());
			//统计文件数
			if(testFD.isFile()) {
				fNumStr += "<love320>"+testFD.getName();
				fNum++;
			}
			//统计目录数
			if(testFD.isDirectory()) {
				dNumStr += "<love320>"+testFD.getName();
				dNum++;	
			}
		}
		
//		System.out.println("文件组字符串:"+fNumStr);
//		System.out.println("目录组字符串:"+dNumStr);
		//实例化文件与目录的字符串组
		files = fNumStr.split("<love320>");
		dirs = dNumStr.split("<love320>");//当前文件夹下的目录
		//dirs = dirs();//uploads文件夹下的目录
//		System.out.println("文件数:"+files.length);
		//升序倒着输出
		Arrays.sort(files);
//		System.out.println(files.length);
//		String filesTest = "<-- 文件  -->";//文件列表
//		for(int i = (files.length-1) ; 0 <= i ; i--){
//			filesTest += "<love320>"+files[i];
//			System.out.println("字符串files[i]:"+files[i]);
//		}
//		files = filesTest.split("<love320>");
//		System.out.println("字符串:"+filesTest);
		return SUCCESS;
	}
	
	//uploads文件夹下的目录
	private String[] dirs(){
		String dirPath = this.getClass().getResource("/").getPath()+"../../"+dirRoot;
		File dirFile = new File(dirPath);
		String[] dirfiles = dirFile.list();
		//文件与目录分离
		String dNumStr = "";//目录组名字符串
		//统计文件数与目录数
		for(int i = 0 ;i < dirfiles.length ;i++){
			File testFD = new File(dirPath+"/"+dirfiles[i].toString());
			//统计目录数
			if(testFD.isDirectory()) {
				dNumStr += "<love320>"+testFD.getName();
			}
		}
		return dNumStr.split("<love320>");
	}
	
	//上传文件模板
	public String upfile() throws IOException{
		if(upload != null){
//			System.out.println(upload.getPath());
//			System.out.println("文件大小:"+upload.length());
//			System.out.println("文件名:"+uploadFileName);
//			System.out.println("文件类型:"+uploadContentType);
	
				//创建以当前时期为目录名文件夹
				Date Mytime = new Date();
				dir = "Love"+String.format("%ty%tm%td",Mytime.getTime(),Mytime.getTime(),Mytime.getTime());
				dir = uploadservice.mkdir(dir);

				//是否重命名
				fileName = uploadservice.reName(uploadFileName);

				uploadservice.uploadFile(upload,dir,fileName);//保存上传文件
				
				/* 写出，出的点问题，还没有处理
				PrintWriter out =  Struts2Utils.getResponse().getWriter();
				out.println("{filePath:'"+dir+"/"+fileName+"'}");
				*/
		}
		
		//设置查询目录
		dirThe = "";
		return "uploadfile";
		//return list();
		
		
	}

	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getFiles() {
		return files;
	}
	
	

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getDirRoot() {
		return dirRoot;
	}

	public void setDirRoot(String dirRoot) {
		this.dirRoot = dirRoot;
	}

	public String getDirThe() {
		return dirThe;
	}

	public void setDirThe(String dirThe) {
		this.dirThe = dirThe;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String[] getDirs() {
		return dirs;
	}

	public void setDirs(String[] dirs) {
		this.dirs = dirs;
	}

	public String getFileName() {
		return fileName;
	}

	public String getRevarname() {
		return revarname;
	}

	public void setRevarname(String revarname) {
		this.revarname = revarname;
	}

	@Autowired
	public void setUploadservice(UploadService uploadservice) {
		this.uploadservice = uploadservice;
	}
	
	
	
}
