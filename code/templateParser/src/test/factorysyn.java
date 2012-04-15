 /**
 * Copyright (c) 2010-2012 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 */
package test;

import com.love320.templateparser.factory.AppFactory;
import com.love320.templateparser.factory.Factory;

/** 
 * @ClassName: factorysyn 
 * @Description: TODO
 * @author love320.com
 * @date 2012-4-15 下午11:18:02 
 *  
 */
public class factorysyn implements Runnable{

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		AppFactory appfactory = AppFactory.getAppFactory();
		appfactory.setConPath("bin/factoryconfig.xml");
		Factory factory = appfactory.getFactory();
		while(true){
			System.out.println(Thread.currentThread().getName()+">>"+factory.getbean("cache"));
			System.out.println(Thread.currentThread().getName()+">>"+factory.getbean("b"));
			System.out.println(Thread.currentThread().getName()+">>"+factory.getbean("cc"));
		}
		//System.out.println("factory:"+factory);
		//System.out.println(factory.getbean("csdsc"));
		//System.out.println(factory.getbean("csdsc"));

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		factorysyn mt=new factorysyn();
	        //基于火车票创建三个窗口
	        new Thread(mt,"a").start();
	        new Thread(mt,"b").start();
	        new Thread(mt,"c").start();
	        new Thread(mt,"d").start();
	        new Thread(mt,"e").start();
		
	}

}
