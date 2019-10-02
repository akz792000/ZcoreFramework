package org.zcoreframework.security.cas.rest;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 * Date: 10/25/2017 AD
 */
public class CasConfiguration {

    private final String authenticationUrl;

    public CasConfiguration(String authenticationUrl) {
        this.authenticationUrl = authenticationUrl;
    }

    public String getAuthenticationUrl() {
        return authenticationUrl;
    }
}