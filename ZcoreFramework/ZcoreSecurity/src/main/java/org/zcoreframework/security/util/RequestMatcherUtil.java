package org.zcoreframework.security.util;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

public class RequestMatcherUtil {

    private RequestMatcherUtil() {
    }

    /**
     * Create a {@link AntPathRequestMatcher} instance.
     *
     * @param httpMethod the {@link String} to use or {@code null} for any {@link HttpMethod}.
     * @param antPattern the ant pattern to create {@link AntPathRequestMatcher}
     * @return a {@link AntPathRequestMatcher} instance
     */
    public static RequestMatcher createAntMatcher(String httpMethod, String antPattern) {
        return new AntPathRequestMatcher(antPattern, httpMethod);
    }

    /**
     * Create a {@link AntPathRequestMatcher} instance.
     *
     * @param httpMethod the {@link HttpMethod} to use or {@code null} for any {@link HttpMethod}.
     * @param antPattern the ant pattern to create {@link AntPathRequestMatcher}
     * @return a {@link AntPathRequestMatcher} instance
     */
    public static RequestMatcher createAntMatcher(HttpMethod httpMethod, String antPattern) {
        return createAntMatcher(ofNullable(httpMethod).map(HttpMethod::toString).orElse(null), antPattern);
    }

    /**
     * Create a {@link AntPathRequestMatcher} instance.
     *
     * @param antPattern the ant pattern to create {@link AntPathRequestMatcher}
     * @return a {@link AntPathRequestMatcher} instance
     */
    public static RequestMatcher createAntMatcher(String antPattern) {
        return createAntMatcher((String) null, antPattern);
    }

    /**
     * Create a {@link List} of {@link AntPathRequestMatcher} instances.
     *
     * @param httpMethod  the {@link HttpMethod} to use or {@code null} for any {@link HttpMethod}.
     * @param antPatterns the ant patterns to create {@link AntPathRequestMatcher}
     * @return a {@link List} of {@link AntPathRequestMatcher} instances
     */
    public static List<RequestMatcher> createAntMatcherList(HttpMethod httpMethod, String... antPatterns) {
        final String method = ofNullable(httpMethod).map(HttpMethod::toString).orElse(null);
        final List<RequestMatcher> matcherList = new ArrayList<RequestMatcher>();
        for (String pattern : antPatterns) {
            matcherList.add(createAntMatcher(method, pattern));
        }
        return matcherList;
    }

    /**
     * Create a {@link List} of {@link AntPathRequestMatcher} instances that do not specify an {@link HttpMethod}.
     *
     * @param antPatterns the ant patterns to create {@link AntPathRequestMatcher} from
     * @return a {@link List} of {@link AntPathRequestMatcher} instances
     */
    public static List<RequestMatcher> createAntMatcherList(String... antPatterns) {
        return createAntMatcherList(null, antPatterns);
    }

    /**
     * Create a {@link RegexRequestMatcher} instance.
     *
     * @param httpMethod   the {@link String} to use or {@code null} for any {@link HttpMethod}.
     * @param regexPattern the regular expressions to create {@link RegexRequestMatcher}
     * @return a {@link RegexRequestMatcher} instance
     */
    public static RequestMatcher createRegexMatcher(String httpMethod, String regexPattern) {
        return new RegexRequestMatcher(regexPattern, httpMethod);
    }

    /**
     * Create a {@link RegexRequestMatcher} instance.
     *
     * @param httpMethod   the {@link HttpMethod} to use or {@code null} for any {@link HttpMethod}.
     * @param regexPattern the regular expressions to create {@link RegexRequestMatcher}
     * @return a {@link RegexRequestMatcher} instance
     */
    public static RequestMatcher createRegexMatcher(HttpMethod httpMethod, String regexPattern) {
        return new RegexRequestMatcher(regexPattern, ofNullable(httpMethod).map(HttpMethod::toString).orElse(null));
    }

    /**
     * Create a {@link RegexRequestMatcher} instance.
     *
     * @param regexPattern the regular expressions to create {@link RegexRequestMatcher}
     * @return a {@link RegexRequestMatcher} instance
     */
    public static RequestMatcher createRegexMatcher(String regexPattern) {
        return createRegexMatcher((String) null, regexPattern);
    }

    /**
     * Create a {@link List} of {@link RegexRequestMatcher} instances.
     *
     * @param httpMethod    the {@link HttpMethod} to use or {@code null} for any {@link HttpMethod}.
     * @param regexPatterns the regular expressions to create {@link RegexRequestMatcher}
     * @return a {@link List} of {@link RegexRequestMatcher} instances
     */
    public static List<RequestMatcher> createRegexMatcherList(HttpMethod httpMethod, String... regexPatterns) {
        final String method = ofNullable(httpMethod).map(HttpMethod::toString).orElse(null);
        final List<RequestMatcher> matcherList = new ArrayList<RequestMatcher>();
        for (String pattern : regexPatterns) {
            matcherList.add(createRegexMatcher(method, pattern));
        }
        return matcherList;
    }

    /**
     * Create a {@link List} of {@link RegexRequestMatcher} instances that do
     * not specify an {@link HttpMethod}.
     *
     * @param regexPatterns the regular expressions to create {@link RegexRequestMatcher}
     *                      from
     * @return a {@link List} of {@link RegexRequestMatcher} instances
     */
    public static List<RequestMatcher> createRegexMatcherList(String... regexPatterns) {
        return createRegexMatcherList(null, regexPatterns);
    }
}
