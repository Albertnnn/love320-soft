/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.sms.factory;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import cms.sms.com.SmsInterface;
import cms.sms.com.impl.Sms10086si;
import cms.sms.com.impl.Smsvv106;
import cms.sms.com.impl.Smszucp;

@Component
public class SmsFactory {
	
	public SmsInterface getInstance(){
		//return new Sms10086si("love320","888888");
		//return new Smszucp("SDK-TTS-010-05217", "344350");
		return new Smsvv106("eee","123456");
	}
	
}
