package com.love320.templateparser.io.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.love320.templateparser.io.FileToString;

public class FileToStringImpl implements FileToString {

	@Override
	public String get(String path) {
		return get(new File(path));
	}

	@Override
	public String get(File file) {
		String fileContent = "";
        try {
        
        //读取到内存中
        InputStreamReader tmpread = new InputStreamReader(new FileInputStream(file), "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(tmpread);// 加入文件缓存
        
        // 开始读取
        String lineTXT = null;
        while ((lineTXT = bufferedReader.readLine()) != null) {
                fileContent += lineTXT+"\n";//加入换行符
        }
        
        // 关闭文件流
        bufferedReader.close();
        //tmpread.close();
        
        } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return fileContent;
	}

}
