package org.zcoreframework.base.http;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;

@SuppressWarnings("serial")
public class HttpServerErrorException extends HttpStatusCodeException {

	public HttpServerErrorException(HttpStatusCode statusCode) {
		super(statusCode);
	}

	public HttpServerErrorException(HttpStatusCode statusCode, String statusText) {
		super(statusCode, statusText);
	}

	public HttpServerErrorException(HttpStatusCode statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseBody, responseCharset);
	}

	public HttpServerErrorException(HttpStatusCode statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
	}
}
