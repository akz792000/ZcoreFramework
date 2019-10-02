/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.util;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;
import org.zcoreframework.base.component.ResponseResult;
import org.zcoreframework.base.component.ResponseResult.ResponseType;
import org.zcoreframework.base.http.ResponseStatusCode;
import org.zcoreframework.web.http.converter.JsonHttpResponse;

public class ResponseUtils {

	protected static void setResponseBody(HttpServletRequest request, HttpServletResponse response, ResponseResult responseResult) throws Exception {
		switch (responseResult.getResponseType()) {
		case DOWNLOAD:
			// prepare response
			String fileType = (String) responseResult.getParameter().get("FileType");
			response.setContentType("application/" + fileType + ";charset=utf-8");
			response.setHeader("Content-Disposition", (String) responseResult.getParameter().get("Content"));
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Set-Cookie", "Download=" + fileType + ";  Path=/");
			// out
			byte[] download = (byte[]) responseResult.getValue();
			response.setHeader("Content-Length", String.valueOf(download.length));
			response.getOutputStream().write(download);
			break;
		case BYTE:
			// out
			Object obj = responseResult.getValue();
			if (obj instanceof String) {
				String val = (String) obj;
				response.setHeader("Content-Length", String.valueOf(val.length()));
				response.getWriter().write(val);
			} else {
				byte[] val = (byte[]) obj;
				response.setHeader("Content-Length", String.valueOf(val.length));
				response.getOutputStream().write(val);
			}
			break;
		case JSON:
			new JsonHttpResponse(response).write(responseResult.getValue());
			break;
		default:
			Assert.state(true, "Response type doesn't support");
			break;
		}
	}

	public static void success(HttpServletRequest request, HttpServletResponse response, ResponseResult responseResult) throws Exception {
		if (responseResult.getValue() != null) {
			response.setStatus(ResponseStatusCode.OK);
			setResponseBody(request, response, responseResult);
		} else {
			response.setStatus(ResponseStatusCode.NO_CONTENT);
		}
	}

	public static void validate(HttpServletRequest request, HttpServletResponse response, final Object object) throws Exception {
		response.setStatus(ResponseStatusCode.VALIDATE);
		/*
		 * for handling non ajax call, add status code as first item of output
		 * because you can't get reponse's status code of non ajax call
		 * (window.open or iframe)
		 */
		setResponseBody(request, response, new ResponseResult(ResponseType.JSON, Arrays.asList(ResponseStatusCode.VALIDATE, object)));
	}

	public static void exception(HttpServletRequest request, HttpServletResponse response, final Object object) throws Exception {
		response.setStatus(ResponseStatusCode.EXCEPTION);
		/*
		 * for handling non ajax call, add status code as first item of output
		 * because you can't get reponse's status code of non ajax call
		 * (window.open or iframe)
		 */
		setResponseBody(request, response, new ResponseResult(ResponseType.JSON, Arrays.asList(ResponseStatusCode.EXCEPTION, object)));
	}

    public static void modalException(HttpServletRequest request, HttpServletResponse response, final Object object) throws Exception {
        response.setStatus(ResponseStatusCode.MODAL_EXCEPTION);
		/*
		 * for handling non ajax call, add status code as first item of output
		 * because you can't get reponse's status code of non ajax call
		 * (window.open or iframe)
		 */
        setResponseBody(request, response, new ResponseResult(ResponseType.JSON, Arrays.asList(ResponseStatusCode.EXCEPTION, object)));
    }

	public static void authenticationException(HttpServletResponse response, AuthenticationException exception) throws Exception {
		response.setStatus(ResponseStatusCode.UNAUTHORIZED);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.getWriter().write(exception.getLocalizedMessage());
		response.getWriter().flush();
	}

	public static void unsupportedException(HttpServletRequest request, HttpServletResponse response, final Object object) throws Exception {
		response.setStatus(ResponseStatusCode.UNSUPPORTED_EXCEPTION);
		setResponseBody(request, response, new ResponseResult(ResponseType.JSON, object));
	}

}
