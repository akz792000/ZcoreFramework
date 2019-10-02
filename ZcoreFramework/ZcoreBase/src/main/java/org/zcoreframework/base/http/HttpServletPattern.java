/**
 *
 * @author Ali Karimizandi
 * @since 2009
 *
 */

package org.zcoreframework.base.http;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

public class HttpServletPattern implements HttpPattern {

    @Override
    public void initialize(HttpTemplate template) {
        // add session key/value to header request.. headers
        String sessionId = template.getCookie(template.request.getCookies(), HttpTemplate.SESSION_KEY);
        if (!StringUtils.isEmpty(sessionId)) {
            template.headers.add("Cookie", HttpTemplate.SESSION_KEY + "=" + sessionId);
        }

        // add CSRF key/value to header request headers
        String csrfVal = template.request.getHeader(HttpTemplate.DEFAULT_CSRF_HEADER_NAME);
        if (csrfVal == null) {
            csrfVal = template.request.getParameter(HttpTemplate.DEFAULT_CSRF_PARAMETER_NAME);
        }
        if (!StringUtils.isEmpty(csrfVal)) {
            template.headers.add(HttpTemplate.DEFAULT_CSRF_HEADER_NAME, csrfVal);
        }
    }

    @Override
    public void finalize(HttpTemplate template, ResponseEntity<?> entity) {
        // set request and response
        String sessionId = template.getSessionId();
        if (!StringUtils.isEmpty(sessionId)) {
            String expires = "";
            if (StringUtils.isEmpty(sessionId) || sessionId.equals("\"\"") || sessionId.equals("''")) {
                expires = "expires=Thu, 01 Jan 1970 00:00:01 GMT;"; // remove cookie
                sessionId = "";
            }
            template.response.addHeader("Set-Cookie", HttpTemplate.SESSION_KEY + "=" + sessionId + ";" + expires + "  Path=/; HttpOnly");
            template.request.setAttribute(HttpTemplate.SESSION_IDENTIFIER, sessionId);
        }

        // add CSRF key/value to header response headers
        String csrfVal = entity.getHeaders().getFirst(HttpTemplate.DEFAULT_CSRF_HEADER_NAME);
        if (!StringUtils.isEmpty(csrfVal)) {
            template.response.addHeader(HttpTemplate.DEFAULT_CSRF_HEADER_NAME, csrfVal);
        }
    }

}
