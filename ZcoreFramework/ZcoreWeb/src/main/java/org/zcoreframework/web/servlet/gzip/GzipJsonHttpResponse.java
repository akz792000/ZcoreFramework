/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.gzip;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import org.zcoreframework.web.http.converter.MappingGzipJsonHttpMessageConverter;

public class GzipJsonHttpResponse {
	
	ServletServerHttpResponse servletServerHttpResponse;
	
	private boolean gzipEnabled = false;
		
	public GzipJsonHttpResponse(HttpServletResponse response) {
		servletServerHttpResponse = new ServletServerHttpResponse(response);
	}
	
	public void setStatusCode(HttpStatus httpStatus) {
		servletServerHttpResponse.setStatusCode(httpStatus);
	}
	
	public ServletServerHttpResponse getServletServerHttpResponse() {
		return servletServerHttpResponse;
	}
	
	public void setGzipEnabled(boolean gzipEnabled) {
		this.gzipEnabled = gzipEnabled;
	}
	
	public void write(Object object) throws HttpMessageNotWritableException, IOException {
		MappingGzipJsonHttpMessageConverter mappingGzipJsonHttpMessageConverter = new MappingGzipJsonHttpMessageConverter();
		mappingGzipJsonHttpMessageConverter.setGzipEnabled(gzipEnabled);
		mappingGzipJsonHttpMessageConverter.write(object, null, servletServerHttpResponse);
	}

}