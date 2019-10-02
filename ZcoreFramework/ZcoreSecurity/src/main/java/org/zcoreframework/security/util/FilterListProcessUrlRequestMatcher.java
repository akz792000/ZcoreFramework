/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.security.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.UrlUtils;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

public class FilterListProcessUrlRequestMatcher implements RequestMatcher {

	private final List<String> filterProcessesUrls;

	public FilterListProcessUrlRequestMatcher(List<String> filterProcessesUrls) {
		this.filterProcessesUrls = filterProcessesUrls;
		Assert.notEmpty(filterProcessesUrls, "filterProcessesUrl must be specified");
		for (String filterProcessesUrl : filterProcessesUrls) {	
			Assert.isTrue(UrlUtils.isValidRedirectUrl(filterProcessesUrl), filterProcessesUrl + " isn't a valid redirect URL");
		}
	}

	public boolean matches(HttpServletRequest request) {
		String uri = request.getRequestURI();
		int pathParamIndex = uri.indexOf(';');

		if (pathParamIndex > 0) {
			// strip everything after the first semi-colon
			uri = uri.substring(0, pathParamIndex);
		}

		if ("".equals(request.getContextPath())) {
			for (String filterProcessesUrl : filterProcessesUrls) {			
				if (uri.endsWith(filterProcessesUrl)) {
					return true;
				}
			}
			return false;
		}

		for (String filterProcessesUrl : filterProcessesUrls) {
			if (uri.endsWith(request.getContextPath() + filterProcessesUrl)) {
				return true;
			}
		}
		return false;
	}

}
