package org.zcoreframework.base.http;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;

@SuppressWarnings("serial")
public class HttpClientErrorException extends HttpStatusCodeException {

	public HttpClientErrorException(HttpStatusCode statusCode) {
		super(statusCode);
	}

	public HttpClientErrorException(HttpStatusCode statusCode, String statusText) {
		super(statusCode, statusText);
	}

	public HttpClientErrorException(HttpStatusCode statusCode, String statusText, byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseBody, responseCharset);
	}

	public HttpClientErrorException(HttpStatusCode statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {
		super(statusCode, statusText, responseHeaders, responseBody, responseCharset);
	}

}