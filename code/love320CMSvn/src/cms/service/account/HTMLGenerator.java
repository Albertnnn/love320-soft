/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.account;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;   


//静态生成页面类
@Component
public class HTMLGenerator {
	
	//获取网页的XHTML方法
	public final String generate(final String url){
		// isBlank　判断某字符串是否为空或长度为0或由空白符(whitespace) 构成 
		if(StringUtils.isBlank(url)) return null;
		
		//检验URL正确性
		Pattern pattern = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+");
		Matcher matcher = pattern.matcher(url);
		if(!matcher.find()) return null;
		
		//字符串缓冲区
		StringBuffer sb = new StringBuffer();
		
		try {
			//获取网站的XHMTL
			URL _url = new URL(url);
			URLConnection urlconnection = _url.openConnection();
			
			//System.out.println(urlconnection.getContentType());
			
			//创建获取网站的XHMTL字符串缓冲区
			BufferedReader in = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
			
			String inputLine;//临时装载XHML字符串,辅助SB字符串缓冲区装载
			while((inputLine = in.readLine()) != null){
				sb.append(inputLine+"\n");
				//System.out.println(inputLine);
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return sb.toString();
	}
	
	//保存获取的网页
	public int save(String urlstr , String htmlName){
		//System.out.println("网站页面："+urlstr);
		String src = generate(urlstr);  
		
		//System.out.println("html:"+src.toString());
		
        File file = new File(this.getClass().getResource("/").getPath() +"../../html/" + htmlName);       
        try {
			FileUtils.writeStringToFile(file, src ,"UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		 //return Math.abs(src.hashCode());//散列值
		return src.length();//页面的大小
	}
}
