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
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.base.http.ResponseStatusCode;
import org.zcoreframework.security.model.UserInfo;

public class AuthenticationSuccessHandlerImpl<M extends UserInfo> extends SimpleUrlAuthenticationSuccessHandler {
	
	private final CsrfTokenRepository tokenRepository;
	
	public AuthenticationSuccessHandlerImpl(CsrfTokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		// get session
		HttpSession session = request.getSession(false);

		// clearAuthenticationAttributes
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}

		// save last
		M model = (M) authentication.getPrincipal();

		// set session timeout
		int sessionTimeout = model.getSessionTimeout();
		if (sessionTimeout <= 0) {
			sessionTimeout = -1;
		}
		session.setMaxInactiveInterval(sessionTimeout);
		
		// token
		CsrfToken csrfToken = tokenRepository.generateToken(request);
		this.tokenRepository.saveToken(csrfToken, request, response);		
		response.setHeader(HttpTemplate.DEFAULT_CSRF_HEADER_NAME, csrfToken.getToken());

		// flush		
		response.setStatus(ResponseStatusCode.NO_CONTENT);
		response.flushBuffer();
	}

}
