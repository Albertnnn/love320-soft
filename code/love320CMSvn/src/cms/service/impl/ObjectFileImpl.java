/**
 * Copyright (c) 2010-2011 love320.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.stereotype.Component;

import cms.service.ObjectFile;

@Component
public class ObjectFileImpl implements ObjectFile {
	
	private String objectFilPath = this.getClass().getResource("/").getPath()
	+ "../content/sysCache/objectCache/";

	public Object objectOpen(String objectName) {
		
		System.out.println("打开对象文件");
		
		Object object = null;
		try {

			File file = new File(objectFilPath + objectName);

			// 对象文件存在
			if (file.isFile() && file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				// 从文件读取对象
				object = ois.readObject();

				ois.close();
				fis.close();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return object;
	}

	public void objectSave(String objectName, Object object) {
		
		
		System.out.println("对象保存到文件");
		
		try {
			
			FileOutputStream fos = new FileOutputStream(objectFilPath+objectName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(object);
			
			oos.close();
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
