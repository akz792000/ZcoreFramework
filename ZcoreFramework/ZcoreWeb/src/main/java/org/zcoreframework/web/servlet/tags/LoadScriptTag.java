/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.springframework.web.servlet.tags.form.TagWriter;

@SuppressWarnings("serial")
public class LoadScriptTag extends AbstractLoadResourceTag {

	private String src;
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	protected void prepareTagContent(TagWriter tagWriter,
			HttpServletRequest httpServletRequest) throws JspException {
		loadScript(src);		
	}
	
}