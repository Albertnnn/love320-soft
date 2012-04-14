/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.account;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@Component
public class XMLConfigManager {
	
	public XMLConfigManager(){
		dbf = DocumentBuilderFactory.newInstance();
		tff = TransformerFactory.newInstance();
	}
	
	
	private DocumentBuilderFactory dbf ;//XML工厂
	private TransformerFactory tff ;//XML写入工厂
	
	public Document newDocument() throws ParserConfigurationException{
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.newDocument();
	}
	
	//写入配置文件
	public void setWConfig(Document doc,String configFilePath){
		Transformer tf;
		try {
			tf = tff.newTransformer();
			tf.transform(new DOMSource(doc), new StreamResult(configFilePath));
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取配置文件方法
	public Document getConfig(String configFilePath){
		Document document = null ;
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			//文档是否存
			File isconfigfile = new File(configFilePath);
			if(isconfigfile.exists()){
				document = db.parse(configFilePath);
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return document;
	}
	
}
