/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.security.filter;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.captcha.Constants;
import org.zcoreframework.captcha.util.Config;
import org.zcoreframework.security.util.RequestMatcherUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

import static java.util.Optional.ofNullable;

// instead of org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

public class CaptchaFilterImpl extends GenericFilterBean {

    public static final String SPRING_SECURITY_FORM_CAPTCHA_KEY = "captcha";
    public static final String SPRING_SECURITY_FORM_USER_DETAILS_KEY = "userDetails";

    private String captchaParameter = SPRING_SECURITY_FORM_CAPTCHA_KEY;

    private String userDetailsParameter = SPRING_SECURITY_FORM_USER_DETAILS_KEY;

    private RequestMatcher loginUrl;

    private RequestMatcher captchaUrl;
    private boolean captchaEnabled;
    private int captchaTimeout;

    private Config config;

    private AuthenticationFailureHandler captchaFailureHandler = new SimpleUrlAuthenticationFailureHandler();

    public CaptchaFilterImpl(String loginUrl, String captchaUrl, boolean captchaEnabled, int captchaTimeout, Config config) {
        this.loginUrl = RequestMatcherUtil.createAntMatcher(HttpMethod.POST, loginUrl);
        this.captchaUrl = RequestMatcherUtil.createAntMatcher(captchaUrl);

        this.captchaEnabled = captchaEnabled;
        this.captchaTimeout = captchaTimeout;
        this.config = config;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final CaptchaSession captchaSession = new CaptchaSession(request, response);

        try {
            if (captchaUrl.matches(request)) {
                // Do not continue filter chain if captcha is not enabled
                if (!captchaEnabled) return;

                final HttpMethod requestMethod = HttpMethod.resolve(request.getMethod());
                switch (requestMethod) {
                    case DELETE:
                        // Remove captcha session key
                        captchaSession.remove();
                        return;
                    case GET:
                        // Get a new captcha
                        captchaSession.getCaptcha();
                        return;
                    case POST:
                        // Validate captcha key
                        captchaSession.validate();
                        return;
                    default:
                        throw new AuthenticationServiceException(String.format("Captcha method not supported: %s", requestMethod));
                }
            }

            if (loginUrl.matches(request) && captchaEnabled) {
                captchaSession.check();
            }
        } catch (AuthenticationException failed) {
            // Authentication failed
            captchaFailureHandler.onAuthenticationFailure(request, response, failed);
            return;
        }

        // Continue filter chain
        chain.doFilter(request, response);
    }

    private LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

    protected String obtainCaptcha(HttpServletRequest request) throws IOException {
        return ofNullable(request.getParameter(captchaParameter)).orElse("");
    }

    public void setCaptchaParameter(String captchaParameter) {
        Assert.hasText(captchaParameter, "Captcha parameter must not be empty or null");
        this.captchaParameter = captchaParameter;
    }

    public void setLoginUrl(RequestMatcher loginUrl) {
        Assert.notNull(loginUrl, "loginUrl cannot be null");
        this.loginUrl = loginUrl;
    }

    public void setCaptchaUrl(RequestMatcher captchaUrl) {
        Assert.notNull(captchaUrl, "captchaUrl cannot be null");
        this.captchaUrl = captchaUrl;
    }

    public void setCaptchaEnabled(boolean captchaEnabled) {
        this.captchaEnabled = captchaEnabled;
    }

    public String getUserDetailsParameter() {
        return userDetailsParameter;
    }

    public void setUserDetailsParameter(String userDetailsParameter) {
        this.userDetailsParameter = userDetailsParameter;
    }

    public AuthenticationFailureHandler getCaptchaFailureHandler() {
        return captchaFailureHandler;
    }

    public void setCaptchaFailureHandler(AuthenticationFailureHandler captchaFailureHandler) {
        Assert.notNull(captchaFailureHandler, "captchaFailureHandler cannot be null");
        this.captchaFailureHandler = captchaFailureHandler;
    }

    private class CaptchaSession {

        final HttpServletRequest request;

        final HttpServletResponse response;

        public CaptchaSession(HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            this.response = response;
        }

        /**
         * Get or renew session and remove captcha key
         */
        public void remove() {
            final HttpSession session = ofNullable(request.getSession(false))
                    .filter(currentSession -> !((boolean) currentSession.getAttribute(Constants.LOGIN_CAPTCHA_SESSION_CHECK)))
                    .orElse(request.getSession(true));
            session.setAttribute(Constants.CAPTCHA_SESSION_KEY, null);
            session.setAttribute(Constants.CAPTCHA_SESSION_TIMEOUT, null);
        }

        public void getCaptcha() throws IOException {
            // Get session
            HttpSession session = request.getSession(false);
            if (session == null) {
                // Renew the current session
                session = request.getSession(true);

                // Set cookies
                response.addHeader("Set-Cookie", HttpTemplate.SESSION_KEY + "=" + session.getId() + ";  Path=/; HttpOnly");
            }

            // Set session attribute
            final String captchaKey = config.getTextProducerImpl().getText();
            final Object loginCaptchaCheck = session.getAttribute(Constants.LOGIN_CAPTCHA_SESSION_CHECK);

            session.setAttribute(Constants.CAPTCHA_SESSION_KEY, captchaKey);
            session.setAttribute(Constants.CAPTCHA_SESSION_TIMEOUT, getCurrentTime().plusSeconds(captchaTimeout));
            session.setAttribute((loginCaptchaCheck == null) ? Constants.LOGIN_CAPTCHA_SESSION_CHECK : Constants.CHANGE_PASSWORD_CAPTCHA_SESSION_CHECK, true);

            // Write to output
            response.setContentType("text/html");
            response.getWriter().write(captchaKey);
            response.flushBuffer();
        }

        private void validate() throws IOException {
            // Obtain captcha
            final String captcha = obtainCaptcha(request);

            // Check session
            final HttpSession session = request.getSession(false);
            if (session == null) {
                throw new CaptchaNotFoundException("Bad Captcha");
            }

            // Get session attributes
            final String captchaTxt = (String) session.getAttribute(Constants.CAPTCHA_SESSION_KEY);
            final LocalDateTime captchaTimeout = (LocalDateTime) session.getAttribute(Constants.CAPTCHA_SESSION_TIMEOUT);
            final boolean loginCaptchaCheck = (boolean) session.getAttribute(Constants.LOGIN_CAPTCHA_SESSION_CHECK);

            // Check the captcha
            if (StringUtils.isEmpty(captchaTxt) || (!captchaTxt.equals(captcha)) || captchaTimeout.isBefore(getCurrentTime())) {
                session.setAttribute(Constants.CAPTCHA_SESSION_KEY, null);
                session.setAttribute(Constants.CAPTCHA_SESSION_TIMEOUT, null);
                throw new CaptchaNotFoundException("Bad Captcha");
            }

            // Set session attributes
            session.setAttribute(loginCaptchaCheck ? Constants.LOGIN_CAPTCHA_SESSION_CHECK : Constants.CHANGE_PASSWORD_CAPTCHA_SESSION_CHECK, false);

            // Flush buffer
            response.flushBuffer();
        }

        /**
         * Get the current session and check captcha
         */
        private void check() {
            final HttpSession session = request.getSession(false);
            boolean loginCaptchaCheck = (boolean) session.getAttribute(Constants.LOGIN_CAPTCHA_SESSION_CHECK);
            if (loginCaptchaCheck) {
                throw new AuthenticationServiceException("Captcha is not validated!");
            }
        }
    }

    public class CaptchaNotFoundException extends AuthenticationException {

        private static final long serialVersionUID = 1L;

        CaptchaNotFoundException(String msg) {
            super(msg);
        }

        CaptchaNotFoundException(String msg, Throwable t) {
            super(msg, t);
        }
    }
}
