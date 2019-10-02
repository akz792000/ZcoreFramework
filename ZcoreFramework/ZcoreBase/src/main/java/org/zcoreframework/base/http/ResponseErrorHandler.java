package org.zcoreframework.base.http;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

public class ResponseErrorHandler extends DefaultResponseErrorHandler {

	private HttpStatusCode getHttpStatusCode(ClientHttpResponse response) throws IOException {
		HttpStatusCode statusCode;
		try {
			statusCode = HttpStatusCode.valueOf(response.getRawStatusCode());
		} catch (IllegalArgumentException ex) {
			throw new UnknownHttpStatusCodeException(response.getRawStatusCode(), response.getStatusText(), response.getHeaders(), getResponseBody(response),
					getCharset(response));
		}
		return statusCode;
	}

	protected boolean hasError(HttpStatusCode statusCode) {
		return (statusCode.series() == HttpStatusCode.Series.CLIENT_ERROR || statusCode.series() == HttpStatusCode.Series.SERVER_ERROR);
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return hasError(getHttpStatusCode(response));
	}

	private byte[] getResponseBody(ClientHttpResponse response) {
		try {
			InputStream responseBody = response.getBody();
			if (responseBody != null) {
				return FileCopyUtils.copyToByteArray(responseBody);
			}
		} catch (IOException ex) {
			// ignore
		}
		return new byte[0];
	}

	public void handleError(ClientHttpResponse response) throws IOException {
		HttpStatusCode statusCode = getHttpStatusCode(response);
		switch (statusCode.series()) {
		case CLIENT_ERROR:
			throw new HttpClientErrorException(statusCode, response.getStatusText(), response.getHeaders(), getResponseBody(response), getCharset(response));
		case SERVER_ERROR:
			throw new HttpServerErrorException(statusCode, response.getStatusText(), response.getHeaders(), getResponseBody(response), getCharset(response));
		default:
			throw new RestClientException("Unknown status code [" + statusCode + "]");
		}
	}

	private Charset getCharset(ClientHttpResponse response) {
		HttpHeaders headers = response.getHeaders();
		MediaType contentType = headers.getContentType();
		return contentType != null ? contentType.getCharset() : null;
	}

}
