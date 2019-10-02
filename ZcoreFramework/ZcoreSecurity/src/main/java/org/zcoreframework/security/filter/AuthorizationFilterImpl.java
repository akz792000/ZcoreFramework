/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;
import org.zcoreframework.base.http.ResponseStatusCode;

/*
 * implement according ConcurrentSessionFilter
 */

public class AuthorizationFilterImpl extends GenericFilterBean {

	private final Log logger = LogFactory.getLog(getClass());

	private final SessionRegistry sessionRegistry;

	private final CsrfTokenRepository tokenRepository;
	
	private final boolean csrfEnabled;

	private LogoutHandler[] handlers = new LogoutHandler[] { new SecurityContextLogoutHandler() };

	public AuthorizationFilterImpl(SessionRegistry sessionRegistry, CsrfTokenRepository tokenRepository, boolean csrfEnabled) {
		this.sessionRegistry = sessionRegistry;
		this.tokenRepository = tokenRepository;
		this.csrfEnabled = csrfEnabled;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// get http servlet request & response
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// get session
		HttpSession session = request.getSession(false);
		if (session != null) {

			// validate session information
			SessionInformation info = sessionRegistry.getSessionInformation(session.getId());
			if (info == null || info.isExpired()) {
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Invalid session information " + UrlUtils.buildFullRequestUrl(request));
				}
				doLogout(request, response);
				return;
			}

			// validate CSRF token : org.springframework.security.web.csrf.CsrfFilter
			if (csrfEnabled) {
				CsrfToken csrfToken = this.tokenRepository.loadToken(request);
				String actualToken = request.getHeader(csrfToken.getHeaderName());
				if (actualToken == null) {
					actualToken = request.getParameter(csrfToken.getParameterName());
				}
				if (!csrfToken.getToken().equals(actualToken)) {
					if (this.logger.isDebugEnabled()) {
						this.logger.debug("Invalid CSRF token found for " + UrlUtils.buildFullRequestUrl(request));
					}
					doLogout(request, response);
					return;
				}
			}

		} else {
			doLogout(request, response);
			return;
		}

		// filter
		chain.doFilter(request, response);
	}

	private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (LogoutHandler handler : handlers) {
			handler.logout(request, response, auth);
		}
		// prepare response
		response.setStatus(ResponseStatusCode.AUTHENTICATION_TIMEOUT);
		response.flushBuffer();
	}

	public void setLogoutHandlers(LogoutHandler[] handlers) {
		Assert.notNull(handlers);
		this.handlers = handlers;
	}

}