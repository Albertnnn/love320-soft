package com.templateparser.factory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.templateparser.cache.Cache;
import com.templateparser.cache.impl.SysCache;
import com.templateparser.factory.impl.FactoryImpl;

public class AppFactory {
	
	/*单实例化
	 * */
	private AppFactory(){};//私有构造
	
	private static class AppFactorySing{
		private static AppFactory appFactory = new AppFactory();
	}
	
	public static AppFactory getAppFactory(){
		return AppFactorySing.appFactory;
	}
	
	//单实例化 end
	private Factory factory;
	private String conPath ;
	private static Element DOCROOT ;
	
	public void setConPath(String conPath) {
		this.conPath = conPath;
		xmlStr();
	}
	
	private void xmlStr(){
		SAXReader sax = new SAXReader();
		try {
			Document document= sax.read(conPath);
			DOCROOT = document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public Factory getFactory() {
		if(factory == null){
			Cache cache = new SysCache();
			factory = new FactoryImpl(DOCROOT,cache);
		}
		return factory;
	}
	
	
	
	
}
