package cms.web.account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.RiaJsonObject;
import cms.service.UploadService;
import cms.service.account.ServicesManager;
import cms.service.impl.UploadServiceImpl;

import com.opensymphony.xwork2.ActionSupport;


//文件上传类

//文件类型过滤
@InterceptorRef(value="defaultStack",params={
"fileUpload.allowedTypes","image/bmp,image/png,image/gif,image/jpeg,image/pjpeg,image/jpg,image/x-png,application/x-shockwave-flash,application/octet-stream"
})
public class UpfilexheditorAction extends ActionSupport {

	private File filedata ;// 上传的文件
	private String filedataFileName;//上传的文件名
	private String filedataContentType;//上传的文件类型
	
	private UploadService uploadservice;//上传文件服务
	
	private ServicesManager sm;
	
	@Override
	public String execute() throws Exception {
		return null;
	}
	
	//上传图片文件
	public String upfileGeneral() throws IOException{

		if(filedata != null){

				//创建以当前时期为目录名文件夹
				Date Mytime = new Date();
				String dir = "Love"+String.format("%ty%tm%td",Mytime.getTime(),Mytime.getTime(),Mytime.getTime());
				dir = uploadservice.mkdir(dir);

				//是否重命名
				String fileName = uploadservice.reName(filedataFileName);

				uploadservice.uploadFile(filedata,dir,fileName);//保存上传文件

				String sitePath = sm.getConfigManager().getConfigFile().getCmspath();//获取网站根目录
				
				String fileNameSitePath = null;//文件在网站的路径
				if(!sitePath.equals("/")){
					fileNameSitePath = sitePath+"/uploads/"+dir+"/"+fileName;
				}else{
					fileNameSitePath = "/uploads/"+dir+"/"+fileName;
				}
				
				
				PrintWriter out =  Struts2Utils.getResponse().getWriter();
				out.println("{\"err\":\"\",\"msg\":\""+fileNameSitePath+"\"}");//返回文件路径
		}
		
		return null;
	}
	

	@Autowired
	public void setUploadservice(UploadService uploadservice) {
		this.uploadservice = uploadservice;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public void setFiledataContentType(String filedataContentType) {
		this.filedataContentType = filedataContentType;
	}

	@Autowired
	public void setSm(ServicesManager sm) {
		this.sm = sm;
	}
	
	
	
	
}
