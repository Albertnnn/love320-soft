package cms.web.account;

import java.io.PrintWriter;

import org.springside.modules.utils.web.struts2.Struts2Utils;

import cms.bean.RiaJsonObject;

import com.opensymphony.xwork2.ActionSupport;

public class AjaxloginAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		
		PrintWriter out =  Struts2Utils.getResponse().getWriter();
		out.println(new RiaJsonObject().getAjaxObject(301,"\u4f1a\u8bdd\u8d85\u65f6\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\u3002",null,null,null,null));
		
		return null;
	}
	
	
	
}
