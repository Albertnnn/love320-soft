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

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucenter.client.Client;
import com.ucenter.util.XMLHelper;

@Service
public class UcenterClientManager {
	private Client ucClient = new Client();
	
	public boolean register(String username, String password, String email){
		ucClient.uc_user_register(username, password, email);
		return true;
	}
	
	public boolean edit(String username,String newpw, String email) {
		ucClient.uc_user_edit(username, "love320.com", newpw, email, 1, "", "");
		return true;
	}
	
	public boolean login(String username, String password){
		LinkedList ucuser = XMLHelper.uc_unserialize(ucClient.uc_user_login(username,password));
		Long cherLo = new Long(ucuser.get(0).toString());
		if(cherLo > 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	public String userGet(String username){
		return ucClient.uc_get_user(username,0);
	}

	public String synloginjs(String userid) {
		LinkedList ucuser = XMLHelper.uc_unserialize(userGet(userid));
		String synloginjs = ucClient.uc_user_synlogin(Integer.parseInt(ucuser.get(0).toString().trim()));

		return synloginjs;
	}
	
	private MemberManager mm;

	public void synloginApi(String userName) {
		mm.setSessionEntity(userName);
	}

	@Autowired
	public void setMm(MemberManager mm) {
		this.mm = mm;
	}
	
	//API同步登出
	public void synloginoutApi() {
		mm.ucloginout();
	}
	
	//本站退出，同步登出
	public String synlogout(){
		return ucClient.uc_user_synlogout();
	}
	
	
	
}
