package cms.web.account;

import com.opensymphony.xwork2.ActionSupport;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;

public class IndexAction extends ActionSupport {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	// freemarker静态方法调用
	public TemplateHashModel getStatics() {
		return BeansWrapper.getDefaultInstance().getStaticModels();
	}
}
