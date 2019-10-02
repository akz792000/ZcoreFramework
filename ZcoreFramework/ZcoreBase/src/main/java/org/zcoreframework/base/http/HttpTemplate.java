/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.base.http;

import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.zcoreframework.base.util.JsonUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository
public class HttpTemplate {

    public static final String SESSION_IDENTIFIER = "SESSION_IDENTIFIER";
    public static final String SESSION_KEY = "JSESSIONID";
    public static final String REQUEST_LOG_KEY = "REQUEST_LOG_KEY";
    public static final String DEFAULT_CSRF_PARAMETER_NAME = "_csrf";
    public static final String DEFAULT_CSRF_HEADER_NAME = "X-CSRF-TOKEN";
    public static final String DEFAULT_CSRF_REMOVE = "X-CSRF-REMOVE";

    HttpServletRequest request;

    HttpServletResponse response;

    HttpHeaders headers = new HttpHeaders();

    private RestTemplate restTemplate = new RestTemplate();

    private HttpPattern pattern;

    private String sessionId;

    private MediaType mediaType = MediaType.APPLICATION_JSON;

    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();

    Map<String, String> actionParameters = new HashMap<String, String>();

    public String getSessionId() {
        return sessionId;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public MultiValueMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(MultiValueMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String key, String value) {
        this.parameters.add(key, value);
    }

    public Map<String, String> getActionParameters() {
        return actionParameters;
    }

    public void setActionParameters(Map<String, String> actionParameters) {
        this.actionParameters = actionParameters;
    }

    public void addActionParameter(String key, String value) {
        this.actionParameters.put(key, value);
    }

    public HttpTemplate() {
        this.restTemplate.setErrorHandler(new ResponseErrorHandler());
        this.pattern = new HttpSimplePattern();
    }

    public HttpTemplate(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.restTemplate.setErrorHandler(new ResponseErrorHandler());
        this.pattern = new HttpServletPattern();
    }

    public static String getCookie(Cookie[] cookies, String name) {
        for (Cookie cookie : cookies) {
            if (cookie.getName() != null && cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    protected void initialize() {
        // set content type
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // set accept type
        headers.setAccept(Arrays.asList(mediaType));

        // prepare action parameter
        if (!actionParameters.isEmpty()) {
            addParameter("serviceActionParam", JsonUtils.encode(actionParameters, DefaultTyping.JAVA_LANG_OBJECT));
            addParameter("serviceActionParamType", "true");
        }

        // initialize pattern
        pattern.initialize(this);
    }

    protected void finalize(ResponseEntity<?> entity) {
        // add session key/value to response headers
        List<String> cookies = entity.getHeaders().get("Set-Cookie");
        sessionId = null;
        if (cookies != null && cookies.size() != 0) {
            sessionId = (String) cookies.stream().filter(line -> line.startsWith(SESSION_KEY)).findAny().orElse(null);
            if (!StringUtils.isEmpty(sessionId)) {
                sessionId = sessionId.split(";")[0];
                sessionId = sessionId.split("=", 2)[1];
            }
        }

        // finalize pattern
        pattern.finalize(this, entity);
    }

    @SuppressWarnings("unchecked")
    public <T> T execute(String url, HttpMethod httpMethod, Class<T> clazz) throws IOException {
        initialize();
        ResponseEntity<?> entity = restTemplate.exchange(url, httpMethod, new HttpEntity<>(parameters, headers), clazz);
        finalize(entity);
        return (T) entity.getBody();
    }

    @SuppressWarnings("unchecked")
    public <T> T execute(String url, HttpMethod httpMethod, ParameterizedTypeReference<T> responseType) throws IOException {
        initialize();
        ResponseEntity<?> entity = restTemplate.exchange(url, httpMethod, new HttpEntity<>(parameters, headers), responseType);
        finalize(entity);
        return (T) entity.getBody();
    }

    public Object execute(String url, HttpMethod httpMethod) throws IOException {
        return execute(url, httpMethod, Object.class);
    }

    public <T> T execute(String url, Class<T> clazz) throws IOException {
        return execute(url, HttpMethod.POST, clazz);
    }

    public Object execute(String url) throws IOException {
        return execute(url, HttpMethod.POST, Object.class);
    }

}
