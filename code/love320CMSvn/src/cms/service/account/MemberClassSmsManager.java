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

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cms.dao.account.MemberClassSmsDao;
import cms.entity.account.Member;
import cms.entity.account.MemberClassSms;
import cms.entity.account.SMS;

@Service
public class MemberClassSmsManager {
	
	private MemberClassSmsDao mcsDao;
	
	private SmsManager smsM;
	private MemberManager mm;

	@Autowired
	public void setMcsDao(MemberClassSmsDao mcsDao) {
		this.mcsDao = mcsDao;
	}

	@Autowired
	public void setSmsM(SmsManager smsM) {
		this.smsM = smsM;
	}
	
	@Autowired
	public void setMm(MemberManager mm) {
		this.mm = mm;
	}

	@Transactional
	public int processing() {

		int numP = 0;
		
		List<SMS> notP = smsM.getNotP();//获取没有处理的短信list
		
		for(SMS smsEntity : notP){
			//查找手机号者对象
			Member mEntity = mm.findPhone(smsEntity.getPhone());
			if(mEntity != null){
				MemberClassSms mcsEntity = new MemberClassSms();
				mcsEntity.setMemberClass(mEntity.getMemberclassstudent());//关联短信与班级
				smsTomcs(smsEntity,mcsEntity);//复制短信对象
				mcsDao.save(mcsEntity);//班级短信对象保存
				
				//修改短信处理状态
				smsEntity.setSmsAction(1);//处理
				smsM.save(smsEntity,true);//保存
				
				numP++;//记录处理条数
			}
			
		}
		
		return numP;
	}
	
	private boolean smsTomcs(SMS smsEntity,MemberClassSms mcsEntity){
		mcsEntity.setTypeId(smsEntity.getTypeId());
		mcsEntity.setPhone(smsEntity.getPhone());
		mcsEntity.setContent(smsEntity.getContent());
		mcsEntity.setSmsAction(smsEntity.getSmsAction());
		mcsEntity.setSmsDate(smsEntity.getSmsDate());
		mcsEntity.setSendType(smsEntity.getSendType());
		mcsEntity.setNewDate(new Date());
		return true;
	}
	
	private boolean smsTomcs(MemberClassSms mcsEntity,SMS smsEntity){
		//smsEntity mcsEntity
		smsEntity.setTypeId(mcsEntity.getTypeId());
		smsEntity.setPhone(mcsEntity.getPhone());
		smsEntity.setContent(mcsEntity.getContent());
		smsEntity.setSmsAction(mcsEntity.getSmsAction());
		smsEntity.setSmsDate(mcsEntity.getSmsDate());
		smsEntity.setSendType(mcsEntity.getSendType());
		smsEntity.setNewDate(new Date());
		return true;
	}

	@Transactional
	public void save(MemberClassSms mcsEntity) {
		mcsEntity.setNewDate(new Date());//创建时间
		mcsEntity.setTypeId(1);
		mcsEntity.setSmsAction(1);

		SMS smsEntity = new SMS();
		smsTomcs(mcsEntity,smsEntity);
		
		mcsEntity.setSmsDate(new Date());
		smsM.save(smsEntity);//发短信
		
		mcsDao.save(mcsEntity);//保存到班级发件箱中
		
	}

	@Transactional(readOnly=true)
	public MemberClassSms getEntity(Long smsid) {
		return mcsDao.get(smsid);
	}


	@Transactional(readOnly=true)
	public List<MemberClassSms> listMCSMS(Member entity) {
		if(entity.getMemberClassLists().size() > 0){
			return entity.getMemberClassLists().get(0).getClassSms();
		}
		return null;
	}
	
	
}
