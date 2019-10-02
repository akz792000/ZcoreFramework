/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.form.AbstractHtmlElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;
import org.zcoreframework.web.servlet.resource.ResourceServletUtils;

@SuppressWarnings("serial")
public abstract class AbstractLoadResourceTag extends AbstractHtmlElementTag {	
	
	private TagWriter tagWriter;
	
	HttpServletRequest request;	
	
	private String version;	
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	protected String getDynamicVersion(String src) {
		String ver = getVersion();
		if (StringUtils.isEmpty(ver))
			return "";
		String res = "?v=";
		res += (ver == "DYNAMIC") ? ResourceServletUtils.getLastModified(request, src) : ver;
		return res;		
	}
	
	protected void runScript(String src) throws JspException {	
		tagWriter.startTag("script");
		tagWriter.forceBlock();
		tagWriter.appendValue(src);
		tagWriter.endTag();
	}	
		
	protected void loadScript(String src) throws JspException {	
		tagWriter.startTag("script");
		writeOptionalAttribute(tagWriter, "type", "text/javascript");	
		String urlstr = request.getContextPath() + src;
		writeOptionalAttribute(tagWriter, "src", urlstr + getDynamicVersion(urlstr));
		tagWriter.forceBlock();
		tagWriter.endTag();
	}
	
	protected void loadStyle(String src) throws JspException {	
		tagWriter.startTag("link");
		writeOptionalAttribute(tagWriter, "type", "text/css");	
		writeOptionalAttribute(tagWriter, "rel", "stylesheet");
		String urlstr = request.getContextPath() + src;
		writeOptionalAttribute(tagWriter, "href", request.getContextPath() + src + getDynamicVersion(urlstr));
		tagWriter.endTag();
	}	
	
	protected abstract void prepareTagContent(TagWriter tagWriter, HttpServletRequest httpServletRequest) throws JspException;
	
	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		this.tagWriter = tagWriter;		
		this.request = (HttpServletRequest) pageContext.getRequest();	
		prepareTagContent(this.tagWriter, this.request);
		return EVAL_BODY_INCLUDE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public void doFinally() {
		super.doFinally();
		tagWriter = null;
	}
	
}