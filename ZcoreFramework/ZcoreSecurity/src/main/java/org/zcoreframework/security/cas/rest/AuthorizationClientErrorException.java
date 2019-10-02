package org.zcoreframework.security.cas.rest;

import java.nio.charset.Charset;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.zcoreframework.base.exception.ClientException;

public class AuthorizationClientErrorException extends HttpStatusCodeException {

	private static final long serialVersionUID = 1L;

	public AuthorizationClientErrorException(int statusCode) {
		super(HttpStatus.valueOf(statusCode));
	}

	public AuthorizationClientErrorException(int statusCode, String responseBody) {
		super(HttpStatus.valueOf(statusCode), null, responseBody.getBytes(), Charset.forName("UTF-8"));
	}

	public static void deliver(int statusCode) throws ClientException {
		throw new AuthorizationClientErrorException(statusCode);
	}

	public static void deliver(int statusCode, String responseBody) throws ClientException {
		throw new AuthorizationClientErrorException(statusCode, responseBody);
	}

}