package test;

import java.io.File;

import com.templateparser.factory.AppFactory;
import com.templateparser.factory.Factory;
import com.templateparser.factory.impl.FactoryImpl;

import junit.framework.TestCase;

public class factorytest extends TestCase {
	public void testsysprintln(){
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
}
