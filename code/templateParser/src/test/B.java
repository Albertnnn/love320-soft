/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package test;

public class B {
	
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
		System.out.println("goB");
	}
	
	
}
