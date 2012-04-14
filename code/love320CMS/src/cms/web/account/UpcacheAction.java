/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.web.account;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.RiaJsonObject;
import cms.web.CrudActionSupport;

import com.opensymphony.xwork2.ActionSupport;

@Results( { @Result(name = CrudActionSupport.RELOAD, location = "taglabel.action", type = "redirect") })
public class UpcacheAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] fileNames = null;//目录下的文件

	@Override
	public String execute() throws Exception {
		//System.out.println(">>"+this.getClass().getResource("/").getPath());
		
		//目录
		File dir;
		
		//缓存路径模板templateCache
		String temCache = this.getClass().getResource("/").getPath()+"../content/sysCache/templateCache";
		deleteFiles(temCache,"tmp");
		
		//缓存路径模板html
		String htmlCache = this.getClass().getResource("/").getPath()+"../content/html";
		deleteFiles(htmlCache ,"ftl");
		
		return SUCCESS;
	}
	
	//清空生成的静态页面(root/html/*)
	public String deletehtml(){
		//清空生成的静态页面路径html
		String htmlFiles = this.getClass().getResource("/").getPath()+"../../html";
		deleteFiles(htmlFiles ,"html");
		
		//删除根目下的index.html
		File rootindex = new File(this.getClass().getResource("/").getPath()+"../../index.html");
		if(rootindex.isFile()&&rootindex.exists()){
			rootindex.delete();//删除根目下的index.html
		}
		
		try {
			PrintWriter out;
			out = Struts2Utils.getResponse().getWriter();
			out.println(new RiaJsonObject().getAjaxObject("操作成功", "archives","closeCurrent"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	//删除文件路径下的所有文件
	private int deleteFiles(String filePath,final String fileType){
		//目录
		File dir;
		
		//缓存路径模板templateCache
		String temCache = filePath;
		//System.out.println("temCache"+temCache);
		dir = new File(temCache);
		if(dir.exists() && dir.isDirectory()){
			fileNames = dir.list(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(fileType);
				}
			});
		}
		
		//System.out.println("文件多少:"+fileNames.length);
		for(int i = 0 ; i < fileNames.length ; i++){
			//System.out.println("文件名:"+fileNames[i].toString());
			File Delfile = new File(temCache+"/"+fileNames[i].toString());
			Delfile.delete();
		}
		return fileNames.length;
	}

	public String[] getFileNames() {
		return fileNames;
	}
	
	

}
