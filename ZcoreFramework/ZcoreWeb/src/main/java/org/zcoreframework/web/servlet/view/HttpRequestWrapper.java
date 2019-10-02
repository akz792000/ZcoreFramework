/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpRequestWrapper extends HttpServletRequestWrapper {
	
	private String method;
	
	private String contentType;
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public HttpRequestWrapper(HttpServletRequest request) {
		super(request);
		this.method = request.getMethod();
		this.contentType = request.getContentType();
	}
	
}
