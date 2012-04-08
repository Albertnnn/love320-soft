package cms.simpletag.app;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import cms.simpletag.Tag;

public class test extends Tag {
	

	@Override
	public void doTag() throws JspException, IOException {
		getJspContext().getOut().write("成功之路");
	}

}
