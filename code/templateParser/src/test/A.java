/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package test;

public class A {
	private B b;
	
	
	public B getB() {
		System.out.println("getB");
		return b;
	}

	public void setB(B b) {
		System.out.println("setB");
		this.b = b;
	}

	private C c;
	

	public C getC() {
		System.out.println("getC");
		return c;
	}

	public void setC(C c) {
		System.out.println("setC");
		this.c = c;
	}
	
	public void print(){
		System.out.println("goA");
	}
	
	private Acateols acsdkfei;


	public Acateols getAcsdkfei() {
		return acsdkfei;
	}

	public void setAcsdkfei(Acateols acsdkfei) {
		this.acsdkfei = acsdkfei;
	}
	
	private String cacheName;

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String var){
		System.out.println("A以注入:"+var);
	}
	
	private Integer intk;


	public Integer getIntk() {
		System.out.println(intk);
		return intk;
	}

	public void setIntk(int intk) {
		System.out.println("整数注入"+intk);
		this.intk = intk;
	}
	
	
}
