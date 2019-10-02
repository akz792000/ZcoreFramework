/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.service.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.eclipse.persistence.exceptions.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.zcoreframework.base.core.CustomSQLErrorCodesTranslator;
import org.zcoreframework.base.core.Executor;
import org.zcoreframework.base.exception.*;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.base.http.ResponseStatusCode;
import org.zcoreframework.base.log.FactoryLog;
import org.zcoreframework.base.log.LogMock;
import org.zcoreframework.base.log.LogMock.Type;
import org.zcoreframework.base.log.Logger;
import org.zcoreframework.base.method.MethodExecutor;
import org.zcoreframework.base.method.MethodTemplateExecutor;
import org.zcoreframework.base.method.MethodTemplateExecutorImpl;
import org.zcoreframework.web.log.RequestLogger;
import org.zcoreframework.web.util.ResponseUtils;

@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = FactoryLog.getLogger(RequestLogger.class);

	private int statusCode;

	@Value("${zcore.application.deploy}")
	public void setStatusCode(String deploy) {
		this.statusCode = deploy.equals("development") ? ResponseStatusCode.HANDLE_DEVELOPMENT_MODE : ResponseStatusCode.HANDLE_PRODUCTION_MODE;
	}

	@Autowired
	MethodTemplateExecutor methodTemplateExecutor;

	@Autowired
	MessageSource messageSource;
	
	private boolean switchStatusCode(HttpServletRequest request, HttpServletResponse response, int value, String responseBody) throws IOException {
		boolean result = true;
		switch (value) {
		case ResponseStatusCode.UNAUTHORIZED:
		case ResponseStatusCode.FORBIDDEN:
		case ResponseStatusCode.AUTHENTICATION_TIMEOUT:
		case ResponseStatusCode.SECURITY_CREDENTIAL:
		case ResponseStatusCode.VALIDATE:
		case ResponseStatusCode.EXCEPTION:
			result = false;
			response.setCharacterEncoding("UTF-8");
			response.setStatus(value);
			response.getWriter().write(responseBody);
			response.flushBuffer();
			break;
		default:
			break;
		}
		return result;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class, Throwable.class })
	public void defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws Exception {

		// Get Error
		Throwable throwable = exception.getCause();
		if (throwable == null) {
			throwable = exception;
		}
		Object error = null;

        // set response log key
        response.setHeader(HttpTemplate.REQUEST_LOG_KEY, (String) request.getAttribute(HttpTemplate.REQUEST_LOG_KEY));

        // check type of exception
		if (throwable instanceof ClientException) {

            // Client Exception
            error = ((BaseException) throwable).getMessageArgs();
            ResponseUtils.exception(request, response, error);

        } else if (throwable instanceof ClientModalException) {

            // Client model Exception
            error = ((BaseException) throwable).getMessageArgs();
            ResponseUtils.modalException(request, response, error);

		} else if (throwable instanceof ClientValidateException) {

			// Client Validate Exception
			error = ((BaseException) throwable).getMessageArgs();
			ResponseUtils.validate(request, response, error);

		} else if (throwable instanceof AuthenticationException) {

			// Authentication Exception
			ResponseUtils.authenticationException(response, (AuthenticationException) throwable);

		} else if (throwable instanceof UnsupportedException) {

			// Unsupported Exception (for business process)
			error = throwable.getMessage();
			ResponseUtils.unsupportedException(request, response, error);

		} else {

			boolean doResponse = true;

			// Print Stack Trace
			exception.printStackTrace();

			// check throwable exception

			// optimistic locking
			if (throwable.getCause() instanceof OptimisticLockException) {
				doResponse = false;
				ResponseUtils.exception(request, response, CustomSQLErrorCodesTranslator.translate(messageSource, "SQL_ERROR_54"));
			}

			// SQL Exception
			else if (throwable.getCause() instanceof SQLException) {
				CustomSQLErrorCodesTranslator errorCodeTranslator = new CustomSQLErrorCodesTranslator(messageSource);
				String errMsg = errorCodeTranslator.translate((SQLException) throwable.getCause());
				if (!StringUtils.isEmpty(errMsg)) {
					doResponse = false;
					ResponseUtils.exception(request, response, errMsg);
				}
			}

			// HTTP Statue Code Exception and ZCore handle HTTP client error exception
			if (exception instanceof HttpStatusCodeException) {
				HttpStatusCodeException exp = (HttpStatusCodeException) exception;
				doResponse = switchStatusCode(request, response, exp.getStatusCode().value(), exp.getResponseBodyAsString());
			} else if (exception instanceof org.zcoreframework.base.http.HttpStatusCodeException) {
				org.zcoreframework.base.http.HttpStatusCodeException exp = (org.zcoreframework.base.http.HttpStatusCodeException) exception;
				doResponse = switchStatusCode(request, response, exp.getStatusCode().value(), exp.getResponseBodyAsString());
			}			

			// Do Response
			if (doResponse) {

				// REQUEST_HANDLE_DEVELOPMENT_MODE
				if (statusCode == ResponseStatusCode.HANDLE_DEVELOPMENT_MODE) {
					exception.printStackTrace(response.getWriter());
				}

				// Flush Response
				response.setStatus(statusCode);
				response.setContentType("text/html");
				response.flushBuffer();
			}

		}

		// Log
		if (request.getAttribute("log-mock") == null) {
			final Throwable except = throwable;
			methodTemplateExecutor.execute(new MethodExecutor() {

				LogMock logMock = new LogMock();

				@Override
				public Executor getExecutor() {
					return () -> {
						logMock.setType(MethodTemplateExecutorImpl.getExceptionType(except));
						logMock.setObject(request);
						logMock.setResult(ExceptionUtils.getFullStackTrace(except));
						logger.log(logMock);
						return null;
					};
				}

			});
		}

	}

}
