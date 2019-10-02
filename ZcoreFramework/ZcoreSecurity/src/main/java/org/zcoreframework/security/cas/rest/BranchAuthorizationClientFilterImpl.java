/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.security.cas.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.filter.GenericFilterBean;
import org.zcoreframework.base.core.PropertiesFactoryBean;
import org.zcoreframework.security.core.SystemChannelsType;
import org.zcoreframework.security.model.UserInfo;
import org.zcoreframework.security.web.authorization.AbstractAuthorizationClientFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import static org.zcoreframework.base.http.ResponseStatusCode.*;

public class BranchAuthorizationClientFilterImpl extends GenericFilterBean {

    private int statusCode;

    private LogoutHandler[] handlers = new LogoutHandler[]{new SecurityContextLogoutHandler()};

    private AbstractAuthorizationClientFilter authorizationClientFilter;

    public BranchAuthorizationClientFilterImpl() {
        final Properties properties = PropertiesFactoryBean.getProperties();
        this.statusCode = properties.getProperty("zcore.application.deploy").equals("development") ? HANDLE_DEVELOPMENT_MODE : HANDLE_PRODUCTION_MODE;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        try {
            final UserInfo userInfo = authorizationClientFilter.doFilter(request, response, request.getRequestURI());

            if (userInfo != null) {
                if (userInfo.getChannelTypeId() != SystemChannelsType.BRANCH.getValue().longValue()) {
                    AuthorizationClientErrorException.deliver(FORBIDDEN, "");
                }
            }

            chain.doFilter(request, response);
        } catch (Exception e) {
            doLogout(request, response, e);
        }
    }

    private void switchStatusCode(HttpServletRequest request, HttpServletResponse response, int value, String responseBody) throws IOException {
        switch (value) {
            case FORBIDDEN:
            case AUTHENTICATION_TIMEOUT:
            case SECURITY_CREDENTIAL:
                statusCode = value;
                response.getWriter().write(responseBody);
                break;
            default:
                break;
        }
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        // print stack trace
        exception.printStackTrace();

        // handle HTTP client error exception and ZCore handle HTTP client error exception
        if (exception instanceof HttpStatusCodeException) {
            HttpStatusCodeException exp = (HttpStatusCodeException) exception;
            switchStatusCode(request, response, exp.getStatusCode().value(), exp.getResponseBodyAsString());
        } else if (exception instanceof org.zcoreframework.base.http.HttpStatusCodeException) {
            org.zcoreframework.base.http.HttpStatusCodeException exp = (org.zcoreframework.base.http.HttpStatusCodeException) exception;
            switchStatusCode(request, response, exp.getStatusCode().value(), exp.getResponseBodyAsString());
        }
    }

    protected void doLogout(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        // do logout
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (LogoutHandler handler : handlers) {
            handler.logout(request, response, auth);
        }

        // handle exception
        handleException(request, response, exception);

        // flush
        response.setCharacterEncoding("UTF-8");
        response.setStatus(statusCode);
        response.flushBuffer();
    }

    public void setAuthorizationClientFilter(AbstractAuthorizationClientFilter authorizationClientFilter) {
        this.authorizationClientFilter = authorizationClientFilter;
    }
}
