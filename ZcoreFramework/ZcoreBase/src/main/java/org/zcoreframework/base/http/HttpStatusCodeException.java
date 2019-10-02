package org.zcoreframework.base.http;

import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientResponseException;

@SuppressWarnings("serial")
public abstract class HttpStatusCodeException extends RestClientResponseException {

	private final HttpStatusCode statusCode;

	protected HttpStatusCodeException(HttpStatusCode statusCode) {
		this(statusCode, statusCode.name(), null, null, null);
	}

	protected HttpStatusCodeException(HttpStatusCode statusCode, String statusText) {
		this(statusCode, statusText, null, null, null);
	}

	protected HttpStatusCodeException(HttpStatusCode statusCode, String statusText, byte[] responseBody, Charset responseCharset) {

		this(statusCode, statusText, null, responseBody, responseCharset);
	}

	protected HttpStatusCodeException(HttpStatusCode statusCode, String statusText, HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {

		super(statusCode.value() + " " + statusText, statusCode.value(), statusText, responseHeaders, responseBody, responseCharset);
		this.statusCode = statusCode;
	}

	public HttpStatusCode getStatusCode() {
		return this.statusCode;
	}

}
