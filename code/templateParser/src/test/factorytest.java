/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package test;

import java.io.File;

import junit.framework.TestCase;

import com.love320.templateparser.factory.AppFactory;
import com.love320.templateparser.factory.Factory;
import com.love320.templateparser.templateprocess.TemplateProcess;

public class factorytest extends TestCase {
	public void testsysprintln(){
		System.out.println("go");
		Thread.currentThread().getContextClassLoader().getResource("");
		System.out.println(factorytest.class.getClassLoader().getResource(""));
		System.out.println(ClassLoader.getSystemResource(""));
		System.out.println(factorytest.class.getResource("factorytest.config"));
		System.out.println(factorytest.class.getResource("/"));
		System.out.println(new File("").getAbsolutePath());
		System.out.println(System.getProperty("user.dir"));
		System.out.println(this.getClass().getResourceAsStream("/"));
	}
	
	public void testobect(){
		AppFactory appfactory = AppFactory.getAppFactory();
		appfactory.setConPath("bin/factoryconfig.xml");
		
		Factory factory = appfactory.getFactory();
		test.A a = (A)factory.getbean("a");
		System.out.println(">>>>>");
		a.print();
		a.getC().print();
		a.getAcsdkfei().privasdf();
		a.getIntk();
		a.getB().get();
		System.out.println(">>>>>获取对象");
		System.out.println(">>>>> end");
	}
	
	public void testobect2(){
		AppFactory appfactory = AppFactory.getAppFactory();
		appfactory.setConPath("bin/factoryconfig.xml");
		
		Factory factory = appfactory.getFactory();
		factory.getbean("cc");
		factory.getbean("b");
		factory.getbean("test2");
				
	}
	
	public void testsing(){
		
		AppFactory appfactory = AppFactory.getAppFactory();
		appfactory.setConPath("bin/factoryconfig.xml");
		Factory factory = appfactory.getFactory();
		System.out.println(appfactory);
		System.out.println(factory);
		
		AppFactory appfactory2 = AppFactory.getAppFactory();
		appfactory2.setConPath("bin/factoryconfig.xml");
		Factory factory2 = appfactory2.getFactory();
		System.out.println(appfactory2);
		System.out.println(factory2);
		
		
	}
	
	public void testBean(){
		AppFactory appfactory = AppFactory.getAppFactory();
		appfactory.setConPath("bin/factoryconfig.xml");
		Factory factory = appfactory.getFactory();
		System.out.println(factory.getbean("cache"));
		System.out.println(factory.getbean("csdsc"));
		System.out.println(factory.getbean("csdsc"));
		System.out.println(factory.getbean("csdsc"));
		System.out.println(factory.getbean("csdsc"));
		System.out.println(factory.getbean("csdsc"));
		System.out.println(factory.getbean("cache"));
		System.out.println(factory.getbean("cache"));
		System.out.println(factory.getbean("cache"));
		System.out.println(factory.getbean("cc"));
		System.out.println(factory.getbean("cc"));
		System.out.println(factory.getbean("cc"));
		
	}
	
	public void testBeanTemplate(){
		AppFactory appfactory = AppFactory.getAppFactory();
		appfactory.setConPath("bin/factoryconfig.xml");
		Factory factory = appfactory.getFactory();
		TemplateProcess templateProcess = (TemplateProcess)factory.getbean("templateProcess");
		System.out.println(templateProcess.get("bin/factoryconfig.xml"));
		
	}
}
