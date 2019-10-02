/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.http;

/*
 * see org.springframework.http.HttpStatus
 */
public enum HttpStatusCode {
	
	// 2xx Success

	OK(ResponseStatusCode.OK, "OK"),

	NO_CONTENT(ResponseStatusCode.NO_CONTENT, "No Content"),
	
	// 4xx Client Error 

	FORBIDDEN(ResponseStatusCode.FORBIDDEN, "Forbidden"),

	NOT_FOUND(ResponseStatusCode.NOT_FOUND, "Not Found"),
	
	UNAUTHORIZED(ResponseStatusCode.UNAUTHORIZED, "Unauthorized"),

	AUTHENTICATION_TIMEOUT(ResponseStatusCode.AUTHENTICATION_TIMEOUT, "Authentication Timeout"),

	SECURITY_CREDENTIAL(ResponseStatusCode.SECURITY_CREDENTIAL, "Security Credential"),
	
	// 5xx Server Error

	VALIDATE(ResponseStatusCode.VALIDATE, "Validate"),

	EXCEPTION(ResponseStatusCode.EXCEPTION, "Exception"),

	UNSUPPORTED_EXCEPTION(ResponseStatusCode.UNSUPPORTED_EXCEPTION, "Unsupported Exception"),

	HANDLE_DEVELOPMENT_MODE(ResponseStatusCode.HANDLE_DEVELOPMENT_MODE, "Handle Development Mode"),

	HANDLE_PRODUCTION_MODE(ResponseStatusCode.HANDLE_PRODUCTION_MODE, "Handle Production Mode");

	private final int value;

	private final String reasonPhrase;

	HttpStatusCode(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int value() {
		return this.value;
	}

	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	public boolean is1xxInformational() {
		return Series.INFORMATIONAL.equals(series());
	}

	public boolean is2xxSuccessful() {
		return Series.SUCCESSFUL.equals(series());
	}

	public boolean is3xxRedirection() {
		return Series.REDIRECTION.equals(series());
	}

	public boolean is4xxClientError() {
		return Series.CLIENT_ERROR.equals(series());
	}

	public boolean is5xxServerError() {
		return Series.SERVER_ERROR.equals(series());
	}

	public Series series() {
		return Series.valueOf(this);
	}

	@Override
	public String toString() {
		return Integer.toString(this.value);
	}

	public static HttpStatusCode valueOf(int statusCode) {
		for (HttpStatusCode status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}

	public enum Series {

		INFORMATIONAL(1), SUCCESSFUL(2), REDIRECTION(3), CLIENT_ERROR(4), SERVER_ERROR(5);

		private final int value;

		Series(int value) {
			this.value = value;
		}

		public int value() {
			return this.value;
		}

		public static Series valueOf(int status) {
			int seriesCode = status / 100;
			for (Series series : values()) {
				if (series.value == seriesCode) {
					return series;
				}
			}
			throw new IllegalArgumentException("No matching constant for [" + status + "]");
		}

		public static Series valueOf(HttpStatusCode status) {
			return valueOf(status.value);
		}
	}

}
