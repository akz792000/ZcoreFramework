/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.JstlView;

public abstract class AbstractUrlView extends AbstractView {
	
	private String prefix = "/WEB-INF/view/";

	private String suffix = ".jsp";
	
	private String requestPageURI;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getRequestPageURI() {
		return requestPageURI;
	}

	public void setRequestPageURI(String requestPageURI) {
		this.requestPageURI = requestPageURI;
	}

	protected abstract void beforeRenderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		// set character encoding
		response.setCharacterEncoding(getCharacterEncoding());			
		// determine is complete or partial or no content	
		String suffix = getSuffix();	
		
		setRequestPageURI( 
				getPrefix() + 
				request.getRequestURI().substring(request.getContextPath().length() + 1) +
				suffix
		);		
		// change request page URI in this method
		beforeRenderMergedOutputModel(model, request, response);
		// render			
		JstlView jstlView = new JstlView(getRequestPageURI());	
		jstlView.setServletContext(getServletContext());		
		jstlView.render(model, request, response);

		/*
		 * Hint when render a page you can change the content type only with
		 * <%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
		 * and response.setContentType not work and change during jstlView.render call, for this purpose contentType add to
		 * AbstractHttpServletResponseWrapper and AbstractServletOutputStream
		 */
		response.setContentType(getContentType());
	}	

}
