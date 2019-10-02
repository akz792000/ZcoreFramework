/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.util.JsonUtils;
import org.zcoreframework.security.model.UserInfo;
import org.zcoreframework.security.safebox.UserDetailsAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// instead of org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

public class AuthenticationFilterImpl extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_USER_DETAILS_KEY = "userDetails";
    private String userDetailsParameter = SPRING_SECURITY_FORM_USER_DETAILS_KEY;

    public AuthenticationFilterImpl(String filterProcessesUrl) {
        super(StringUtils.isEmpty(filterProcessesUrl) ? "/j_spring_security_check" : filterProcessesUrl);
    }

    public String getUserDetailsParameter() {
        return userDetailsParameter;
    }

    public void setUserDetailsParameter(String userDetailsParameter) {
        this.userDetailsParameter = userDetailsParameter;
    }

    protected String obtainUserDetails(HttpServletRequest request) {
        return request.getParameter(userDetailsParameter);
    }

    private AbstractAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String userDetails = obtainUserDetails(request);
        UserInfo userInfo = JsonUtils.decode(userDetails, UserInfo.class, DefaultTyping.JAVA_LANG_OBJECT);
        userInfo.setStream(userDetails);
        return new UserDetailsAuthenticationToken(userInfo);
    }

    protected void setDetails(HttpServletRequest request, AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        final HttpMethod requestMethod = HttpMethod.resolve(request.getMethod());

        // validate request method
        if (requestMethod != HttpMethod.POST) {
            throw new AuthenticationServiceException(String.format("Authentication method not supported: %s", requestMethod));
        }

        // get authentication token
        AbstractAuthenticationToken authRequest = getAuthenticationToken(request);

        // allow subclasses to set the "details" property
        setDetails(request, authRequest);

        // authenticate
        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
