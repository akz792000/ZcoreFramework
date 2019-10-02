/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.servlet.gzip;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.zcoreframework.web.servlet.gzip.GzipServletResponseWrapper;

public class GzipServletFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;	
		String acceptEncoding = request.getHeader("Accept-Encoding");		
		if (acceptEncoding != null && acceptEncoding.indexOf("gzip") != -1 && isGzipable(request) && !response.isCommitted()) {
			response.addHeader("Content-Encoding", "gzip");
			GzipServletResponseWrapper wrappedResponse = new GzipServletResponseWrapper(response);
			chain.doFilter(req, wrappedResponse);			
			wrappedResponse.close();
			return;
		}
		chain.doFilter(req, res);
	}
	
	protected boolean isGzipable(HttpServletRequest request) {
		String compress = request.getHeader("Compress");
		return (!StringUtils.isEmpty(compress) && Boolean.valueOf(compress)); 		
	}
		
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {
		
	}
    
}