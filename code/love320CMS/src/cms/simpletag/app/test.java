/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
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
