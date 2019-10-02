package org.zcoreframework.security.web.authorization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.base.http.ResponseStatusCode;
import org.zcoreframework.base.util.JsonUtils;
import org.zcoreframework.security.cas.rest.AuthorizationClientErrorException;
import org.zcoreframework.security.cas.rest.CasConfiguration;
import org.zcoreframework.security.core.SecurityHelper;
import org.zcoreframework.security.model.UserInfo;
import org.zcoreframework.security.safebox.UserDetailsAuthenticationToken;
import org.zcoreframework.security.util.ServiceValidateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Ali Karimizandi
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 * @since 2009
 */
public abstract class AbstractAuthorizationClientFilter {

    public static final QName SESSION_KEY = new QName(HttpTemplate.SESSION_KEY);

    private CasConfiguration casConfiguration;
    private SessionRegistry sessionRegistry;
    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();

    /**
     * @return null if requested uri is generally ignored.
     */
    public final UserInfo doFilter(Object request, Object response, String uri) throws Exception {
        if (isGeneralBypassed(request, uri)) return null;

        final String sessionId = getSessionId(request);
        final SessionInformation localSessionInfo = sessionRegistry.getSessionInformation(sessionId);

        final UserInfo userInfo = getUserInfo(request, response, sessionId, localSessionInfo);
        userInfo.setTrackId(UUID.randomUUID().toString());

        checkUserAuthority(userInfo, request, uri, sessionId, localSessionInfo);

        setSecurityHelperAuthentication(getHttpServletRequest(request), userInfo);

        return userInfo;
    }

    protected abstract boolean isGeneralBypassed(Object request, String uri);

    protected abstract boolean isDeterrentBypassed(Object request, String uri);

    protected abstract boolean isLoggedInBypassed(Object request, String uri);

    private String getSessionId(Object request) {
        final String sessionId = extractSessionId(request);
        if (StringUtils.isEmpty(sessionId)) {
            throw new AuthorizationClientErrorException(ResponseStatusCode.AUTHENTICATION_TIMEOUT);
        }
        return sessionId;
    }

    protected abstract String extractSessionId(Object request);

    private boolean isSessionInfoExpired(SessionInformation sessionInfo) {
        return (sessionInfo == null) || sessionInfo.isExpired();
    }

    private UserInfo getUserInfo(Object request, Object response, String sessionId, SessionInformation localSessionInfo) throws IOException {
        final HttpTemplate httpTemplate = new HttpTemplate(getHttpServletRequest(request), getHttpServletResponse(response));
        addSessionId(httpTemplate, sessionId);

        if (isSessionInfoExpired(localSessionInfo)) {
            httpTemplate.addParameter("command", "instance");
            String result = httpTemplate.execute(casConfiguration.getAuthenticationUrl(), String.class);
            return JsonUtils.decode(result, UserInfo.class, ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT);
        }

        httpTemplate.addParameter("command", "validate");
        httpTemplate.execute(casConfiguration.getAuthenticationUrl());
        return (UserInfo) localSessionInfo.getPrincipal();
    }

    protected void addSessionId(HttpTemplate httpTemplate, String sessionId) {
        httpTemplate.addParameter("id", sessionId);
    }

    private void checkUserAuthority(UserInfo userInfo, Object request, String uri, String sessionId, SessionInformation localSessionInfo) throws Exception {
        if (userInfo.hasDeterrentState()) {
            if (isDeterrentBypassed(request, uri)) return;
            throw new AuthorizationClientErrorException(ResponseStatusCode.SECURITY_CREDENTIAL, userInfo.getDefaultDeterrentState().name());
        }

        saveToLocalSessionRegistry(userInfo, sessionId, localSessionInfo);

        if (isLoggedInBypassed(request, uri)) return;

        if (!ServiceValidateUtils.validate(userInfo, uri)) {
            throw new AuthorizationClientErrorException(ResponseStatusCode.FORBIDDEN, "");
        }
    }

    private void saveToLocalSessionRegistry(UserInfo userInfo, String sessionId, SessionInformation localSessionInfo) {
        if (isSessionInfoExpired(localSessionInfo)) {
            sessionRegistry.registerNewSession(sessionId, userInfo);
            return;
        }
        sessionRegistry.refreshLastRequest(sessionId);
    }

    protected abstract HttpServletRequest getHttpServletRequest(Object request);

    protected abstract HttpServletResponse getHttpServletResponse(Object response);

    private void setSecurityHelperAuthentication(HttpServletRequest request, UserInfo userInfo) {
        final UserDetailsAuthenticationToken authRequest = new UserDetailsAuthenticationToken(userInfo, userInfo.getAuthorities());
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        SecurityHelper.setAuthentication(authRequest);
    }

    public void setCasConfiguration(CasConfiguration casConfiguration) {
        this.casConfiguration = casConfiguration;
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }
}
