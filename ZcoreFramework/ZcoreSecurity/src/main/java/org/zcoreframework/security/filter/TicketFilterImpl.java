/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.zcoreframework.base.http.ResponseStatusCode;
import org.zcoreframework.security.model.UserInfo;
import org.zcoreframework.security.util.FilterListProcessUrlRequestMatcher;

public class TicketFilterImpl extends GenericFilterBean {
	
	private final RequestMatcher requestMatcher;
	
	private SessionRegistry sessionRegistry;

	public TicketFilterImpl(SessionRegistry sessionRegistry, List<String> defaultFilterProcessesUrls) {
		this.sessionRegistry = sessionRegistry;
		requestMatcher = new FilterListProcessUrlRequestMatcher(defaultFilterProcessesUrls);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// get http servlet request & response
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// check user state
		if (requestMatcher.matches(request)) {
						
			// initialize
			int statusCode = ResponseStatusCode.AUTHENTICATION_TIMEOUT;	
			
			// prepare character encoding and charset
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			
			if (request.getMethod().equals("POST")) {				
				// get id							
				String id = request.getParameter("id");
								
				// prepare result
				if (!StringUtils.isEmpty(id)) {		
					// get session info
					SessionInformation info = sessionRegistry.getSessionInformation(id);
					
					// check session info
					if (info != null && !info.isExpired()) {
						// get user info
						UserInfo userInfo = (UserInfo) info.getPrincipal();
												
						// refresh last request
						sessionRegistry.refreshLastRequest(info.getSessionId());							
						
						// process command
						switch (request.getParameter("command")) {
						case "instance":
							statusCode = ResponseStatusCode.OK;
							response.getWriter().write(userInfo.getStream());
							break;
						case "validate":
							statusCode = ResponseStatusCode.OK;
							break;
						default:
							Assert.state(false, "Invalid command.");
							break;
						}						
					}
				}
			}
			
			// flush
			response.setStatus(statusCode);
			response.flushBuffer();
			
			return;
		}

		// filter
		chain.doFilter(request, response);
	}

}