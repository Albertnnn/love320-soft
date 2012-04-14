/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.sms.com.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cms.sms.com.SmsInterface;

public class Smsvv106 implements SmsInterface {

	/*
	 * webservice服务器定义
	 */
	// public String service_old="http://sdk2.entinfo.cn/webservice.asmx";
	private String serviceURL = "http://web.vv106.com/WS/";

	private String sn = "";// 序列号

	private String password = "";

	private String pwd = "";// 密码
	
	public Smsvv106(String sn ,String password){
		this.sn = sn ;
		this.password = password ;
	}
	
	public List RECSMSEx(String subcode) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public String chargeFee(String cardno, String cardpwd) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBalance() {
		try {
			//http://web.vv106.com/WS/SelSum.aspx?CorpID=*&Pwd=*
			String url = serviceURL+"SelSum.aspx?CorpID="+sn+"&Pwd="+password;
			//System.out.println(url);
			URL _url = new URL(url);
		
			return getURLOpenToStr(_url);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	public String gxmt(String mobile, String content, String ext, String stime,
			String rrid) {
		// TODO Auto-generated method stub
		return null;
	}

	public String mo() {

		try {
			//http://web.vv106.com/WS /Get.aspx?CorpID=*&Pwd=*
			String url = serviceURL+"Get.aspx?CorpID="+sn+"&Pwd="+password;
			//System.out.println(url);
			URL _url = new URL(url);
		
			return getURLOpenToStr(_url);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}

	public String msgid() {
		// TODO Auto-generated method stub
		return null;
	}

	public String mt(String mobile, String content, String ext, String stime,
			String rrid) {
			String urlToStr = "";
			try {
				//mobile = URLEncoder.encode(mobile,"GBK");
				content = URLEncoder.encode(content,"GBK"); 
				String SendAspx = "Send.aspx";
				if(mobile.split(",").length > 1){//多个手机号
					SendAspx = "BatchSend.aspx";
				}
				String url = serviceURL+SendAspx+"?CorpID="+sn+"&Pwd="+password+"&Mobile="+mobile+"&Content="+content+"&SendTime="+ext;
				//System.out.println(url);
				URL _url = new URL(url);
			
				urlToStr = getURLOpenToStr(_url);

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			if(urlToStr.length() > 0){
				return Integer.parseInt(urlToStr.trim())+"";
			}else{
				
				return "-1";
			}

			
	}
	
	private String getURLOpenToStr(URL _url ){
		return getURLOpenToStr( _url ,null);
	}
	
	private String getURLOpenToStr(URL _url ,String regex){
		try {
			// 字符串缓冲区
			StringBuffer sb = new StringBuffer();

			URLConnection urlconnection = _url.openConnection();

			InputStreamReader isr = new InputStreamReader(urlconnection
					.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			String result_mt = "";
			while (null != (inputLine = in.readLine())) {
				if (regex != null) {
					Pattern pattern = Pattern.compile(regex);
					Matcher matcher = pattern.matcher(inputLine);
					while (matcher.find()) {
						result_mt = matcher.group(1);
					}
				} else {
					result_mt += inputLine;
				}
			}

			if (!result_mt.startsWith("-") && !result_mt.equals("")) {
				// System.out.println("发送成功返回值为:" + result_mt);
				return result_mt;
			} else {
				// System.out.println("发送失败 返回值为:" + result_mt);
				return "";
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	

	public String register(String province, String city, String trade,
			String entname, String linkman, String phone, String mobile,
			String email, String fax, String address, String postcode) {
		// TODO Auto-generated method stub
		return null;
	}

}
