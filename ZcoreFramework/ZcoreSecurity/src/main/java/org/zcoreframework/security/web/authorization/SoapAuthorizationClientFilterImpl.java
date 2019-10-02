package org.zcoreframework.security.web.authorization;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.eclipse.jetty.server.Request;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.base.http.ResponseStatusCode;
import org.zcoreframework.security.cas.rest.AuthorizationClientErrorException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.MessageContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 11/6/2017 AD
 */
public class SoapAuthorizationClientFilterImpl extends AbstractAuthorizationClientFilter {

    private final List<String> requestMatchers;
    private final List<String> deterrentMatchers;
    private final List<String> serviceMatchers;

    public SoapAuthorizationClientFilterImpl(List<String> requestMatchers, List<String> deterrentMatchers, List<String> serviceMatchers) {
        this.requestMatchers = requestMatchers;
        this.deterrentMatchers = deterrentMatchers;
        this.serviceMatchers = serviceMatchers;
    }

    @Override
    protected boolean isGeneralBypassed(Object request, String uri) {
        return requestMatchers.contains(uri);
    }

    @Override
    protected boolean isDeterrentBypassed(Object request, String uri) {
        return deterrentMatchers.contains(uri);
    }

    @Override
    protected boolean isLoggedInBypassed(Object request, String uri) {
        return serviceMatchers.contains(uri);
    }

    @Override
    protected HttpServletRequest getHttpServletRequest(Object request) {
        return (HttpServletRequest) ((WrappedMessageContext) request).get(MessageContext.SERVLET_REQUEST);
    }

    @Override
    protected HttpServletResponse getHttpServletResponse(Object response) {
        return (HttpServletResponse) ((WrappedMessageContext) response).get(MessageContext.SERVLET_RESPONSE);
    }

    @Override
    protected String extractSessionId(Object request) {
        final Header header = ((SoapMessage) ((WrappedMessageContext) request).getWrappedMessage()).getHeader(SESSION_KEY);
        if (header == null) {
            throw new AuthorizationClientErrorException(ResponseStatusCode.AUTHENTICATION_TIMEOUT);
        }

        return ((ElementNSImpl) header.getObject()).getTextContent();
    }

    @Override
    protected void addSessionId(HttpTemplate httpTemplate, String sessionId) {
        super.addSessionId(httpTemplate, sessionId);

        final Request request = (Request) httpTemplate.getRequest();
        final List<Cookie> cookieList = ofNullable(request.getCookies()).map(Arrays::asList).orElse(new ArrayList<>());
        cookieList.add(new Cookie(HttpTemplate.SESSION_KEY, sessionId));
        request.setCookies(cookieList.toArray(new Cookie[cookieList.size()]));
    }
}
