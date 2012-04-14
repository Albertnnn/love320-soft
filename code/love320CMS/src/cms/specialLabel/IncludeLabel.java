/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.specialLabel;

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

import org.springframework.stereotype.Component;


//包括特殊标签 include
@Component
public class IncludeLabel {
	
	public String processing(String filePath){
		String strFilePath ="";
		
		File thefile = new File(this.getClass().getResource("/").getPath()+"../template/"+filePath);
		//文件是否存在
		if(thefile.exists()){
			InputStreamReader tmpread;
			try {
				tmpread = new InputStreamReader(new FileInputStream(thefile), "UTF-8");
				// 加入文件缓存
				BufferedReader bufferedReader = new BufferedReader(tmpread);
				// 开始读取
				String lineTXT = null;
				while ((lineTXT = bufferedReader.readLine()) != null) {
					 strFilePath += lineTXT+"\n";
				}

				// 关闭文件流
				bufferedReader.close();
				tmpread.close();
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
			
		}else{
			strFilePath = "引用文件不存在" ;
		}
		
		return strFilePath;
	}
}
