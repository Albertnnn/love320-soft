/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.account;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import cms.dao.account.WebSMSDao;
import cms.entity.account.WebSMS;


@Service
public class WebSMSManager {

	private WebSMSDao websmsDao;
	
	private SmsManager smsM;
	
	
	@Autowired
	public void setSmsM(SmsManager smsM) {
		this.smsM = smsM;
	}

	@Autowired
	public void setWebsmsDao(WebSMSDao websmsDao) {
		this.websmsDao = websmsDao;
	}

	@Transactional(readOnly=true)
	public Page search(Page page, List<PropertyFilter> filters) {
		return websmsDao.findPage(page, filters);
	}

	@Transactional
	public void save(WebSMS entity) {
		entity.setNewDate(new Date());
		websmsDao.save(entity);
		if(entity.getTypeid() == 1){
			smsM.sendSms(entity.getStudents().getPhone(), entity.getContent(), "考试成绩");
		}else{
			smsM.sendSms(entity.getStudents().getPhone(), entity.getContent(), "在校表现");
		}
		
	}

	public String[] subjects(String subjectsOrder,
			String[] memberStrs) {

		String soStr[] = strSplit(subjectsOrder);
		
		for(int i = 0 ; i < memberStrs.length ; i++){
			String[] mStrs = strSplit(memberStrs[i]);
			String mStrOne = "";
			if (mStrs.length == soStr.length) {
				for (int k = 0; k < mStrs.length; k++) {
					mStrOne +=  soStr[k] +mStrs[k];
				}
				//System.out.println(mStrOne);
			}
			memberStrs[i] = mStrOne;
			
		}

		return memberStrs;
	}
	
	private String[] strSplit(String str){
		String[] strs = str.split("\\,");
		if(strs.length == 1){
			strs = str.split("，");
		}
		return strs;
	}

	@Transactional
	public void delete(Long id) {
		websmsDao.delete(id);
	}

}
