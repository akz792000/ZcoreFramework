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

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.zcoreframework.base.http.ResponseStatusCode;
import org.zcoreframework.security.model.UserInfo;

public class AuthenticationFailureHandlerImpl<M extends UserInfo> implements AuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {				
		response.setStatus(ResponseStatusCode.UNAUTHORIZED);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write(exception.getLocalizedMessage());
		response.flushBuffer();	
	}
	
}
