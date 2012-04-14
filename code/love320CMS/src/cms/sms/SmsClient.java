/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.sms;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cms.sms.com.SmsInterface;
import cms.sms.factory.SmsFactory;

@Component
public class SmsClient {
	private SmsFactory smsf; 
	private SmsInterface sms;

	@Autowired
	public void setSmsf(SmsFactory smsf) {
		this.smsf = smsf;
	}
	
	public boolean sendSMS(String mobile,String content){
		sms = smsf.getInstance();
		String state = sms.mt(mobile, content, "","","");
		Long stateNum = new Long(state);
		if(stateNum > -1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean sendSMS(String mobile,String content,Date ext){
		
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");//定时短信设置时间
		
		sms = smsf.getInstance();
		String state = sms.mt(mobile, content, df.format(ext),"","");
		Long stateNum = new Long(state);
		if(stateNum > -1){
			return true;
		}else{
			return false;
		}
	}
	
	
	public String receivingSMS(){		
		sms = smsf.getInstance();
		return sms.mo();
	}
	
	public String balance(){
		sms = smsf.getInstance();
		return sms.getBalance();
	}
	
}
