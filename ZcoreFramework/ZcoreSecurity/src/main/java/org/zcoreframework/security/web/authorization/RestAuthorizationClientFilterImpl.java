package org.zcoreframework.security.web.authorization;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.security.util.RequestMatcherUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 11/6/2017 AD
 */
public class RestAuthorizationClientFilterImpl extends AbstractAuthorizationClientFilter {

    private final List<RequestMatcher> requestMatchers;
    private final List<RequestMatcher> deterrentMatchers;
    private final List<RequestMatcher> serviceMatchers;

    public RestAuthorizationClientFilterImpl(String[] filterProcessesUrls, String[] deterrentProcessesUrls, String[] serviceProcessesUrls) {
        this.requestMatchers = RequestMatcherUtil.createAntMatcherList(filterProcessesUrls);
        this.deterrentMatchers = RequestMatcherUtil.createAntMatcherList(deterrentProcessesUrls);
        this.serviceMatchers = RequestMatcherUtil.createAntMatcherList(serviceProcessesUrls);
    }

    @Override
    protected boolean isGeneralBypassed(Object request, String uri) {
        return isRequestMatched(requestMatchers, request);
    }

    @Override
    protected boolean isDeterrentBypassed(Object request, String uri) {
        return isRequestMatched(deterrentMatchers, request);
    }

    @Override
    protected boolean isLoggedInBypassed(Object request, String uri) {
        return isRequestMatched(serviceMatchers, request);
    }

    private boolean isRequestMatched(List<RequestMatcher> requestMatcherList, Object request) {
        final HttpServletRequest httpServletRequest = getHttpServletRequest(request);
        return requestMatcherList.stream().anyMatch(requestMatcher -> requestMatcher.matches(httpServletRequest));
    }

    @Override
    protected String extractSessionId(Object request) {
        return HttpTemplate.getCookie(((HttpServletRequest) request).getCookies(), HttpTemplate.SESSION_KEY);
    }

    @Override
    protected HttpServletRequest getHttpServletRequest(Object request) {
        return (HttpServletRequest) request;
    }

    @Override
    protected HttpServletResponse getHttpServletResponse(Object response) {
        return (HttpServletResponse) response;
    }
}
