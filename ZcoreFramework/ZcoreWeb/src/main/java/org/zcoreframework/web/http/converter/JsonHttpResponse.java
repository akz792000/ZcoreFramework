/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.http.converter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;

public class JsonHttpResponse {
	
	ServletServerHttpResponse servletServerHttpResponse;
		
	public JsonHttpResponse(HttpServletResponse response) {
		servletServerHttpResponse = new ServletServerHttpResponse(response);
	}
	
	public void setStatusCode(HttpStatus httpStatus) {
		servletServerHttpResponse.setStatusCode(httpStatus);
	}
	
	public ServletServerHttpResponse getServletServerHttpResponse() {
		return servletServerHttpResponse;
	}
	
	public void write(Object object) throws HttpMessageNotWritableException, IOException {
		new MappingJsonHttpMessageConverter().write(object, null, servletServerHttpResponse);
	}

}
