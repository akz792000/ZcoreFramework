/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.web.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.base.http.ResponseStatusCode;

public class AuthenticationLogoutHandlerImpl implements LogoutSuccessHandler {
		
	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// remove token
		response.setHeader(HttpTemplate.DEFAULT_CSRF_HEADER_NAME, HttpTemplate.DEFAULT_CSRF_REMOVE);
		
		// flush
		response.setStatus(ResponseStatusCode.NO_CONTENT);
		response.flushBuffer();		
	}
	
}
