package cms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import cms.service.UploadService;

/*
 * 文件上传实现类
 * */

@Service
public class UploadServiceImpl implements UploadService {

	// 网站路径
	private String SitePath = ContextLoader.getCurrentWebApplicationContext()
			.getServletContext().getRealPath("/");

	// 上传文件目录
	private String uploadPath = "uploads";

	public Boolean uploadFile(File upload, String dirPath, String uploadFileName) {
		//System.out.println("uploadsFileName:"+uploadFileName);
		if (upload != null) {
			try {
				byte[] b = new byte[1024];
				FileInputStream fis = new FileInputStream(upload);

				FileOutputStream fos = new FileOutputStream(SitePath
						+ uploadPath + "/" + dirPath + "/" + uploadFileName);
				int len = 0;
				while ((len = fis.read(b)) > 0) {
					fos.write(b, 0, len);
				}

				fis.close();
				fos.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return true;
	}
	
	public Boolean uploadFile(File inFile, String uploadFileName) {
		//创建以当前时期为目录名文件夹
		Date Mytime = new Date();
		String dir = "Love"+String.format("%ty%tm%td",Mytime.getTime(),Mytime.getTime(),Mytime.getTime());
		dir = mkdir(dir);
		return uploadFile(inFile,dir,uploadFileName);
	}

	public String mkdir(String dirStr) {
		// 创建以当前时期为目录名文件夹
		// System.out.println(mdf);
		// 文件是否存在
		File Updir = new File(SitePath + uploadPath + "/" + dirStr + "/");
		if (!Updir.exists()) {
			// 创建目录
			Updir.mkdir();
		}
		return dirStr;
	}

	public String reName(String fileName) {
		if ((fileName != null) && (fileName.trim().length() > 0)) {

			fileName = FilenameUtils.getName(fileName);
			// 转换成拼音
			HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();// 设置拼音格式
			format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不显示声调
			try {
				fileName = PinyinHelper.toHanyuPinyinString(fileName, format,"");
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}

			Date Mytime = new Date();

			// 是否重命名
			// fileName =
			// "love"+String.format("%tH%tM",Mytime.getTime(),Mytime.getTime())+"."+uploadContentType.split("/")[1].toString();
			fileName = "love"
					+ String.format("%tH%tM%tS", Mytime.getTime(), Mytime
							.getTime(), Mytime.getTime()) + fileName;

		}
		//System.out.println("fileName:"+fileName);
		return fileName;
	}


}
