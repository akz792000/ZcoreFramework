/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.log;

import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.base.log.LogMock;
import org.zcoreframework.base.log.Logger;
import org.zcoreframework.web.log.domain.RequestLogEntity;

public class RequestLogger implements Logger, InitializeAware {

	@RepositoryInstance
	private DefaultRepository repository;

	@SuppressWarnings("rawtypes")
	protected String getParameters(HttpServletRequest request) {
		String messageResult = "[";
		if (request.getParameterNames() != null) {
			Enumeration paramEnum = request.getParameterNames();
			while (paramEnum.hasMoreElements()) {
				String paramName = (String) paramEnum.nextElement();
				messageResult += "{" + paramName + ", " + Arrays.toString(request.getParameterValues(paramName)) + "}";
			}
		}
		messageResult += "]";
		return messageResult;
	}

	protected void persist(HttpServletRequest request, StopWatch stopWatch, String status, String msg) {
		// queue entity
		RequestLogEntity entity = new RequestLogEntity();

		// request
		entity.setId(UUID.randomUUID().toString());
		entity.setUri(request.getRequestURI());
		entity.setRemoteAddr(request.getRemoteAddr());
		entity.setRemotePort(request.getRemotePort());
		entity.setServerName(request.getServerName());
		entity.setServerPort(request.getServerPort());
		String sessionId = request.getRequestedSessionId();
		if (StringUtils.isEmpty(sessionId)) {
			sessionId = (String) request.getAttribute(HttpTemplate.SESSION_IDENTIFIER);
			if (StringUtils.isEmpty(sessionId)) {
				sessionId = "-1";
			}
		}
		entity.setSessionId(sessionId);
		entity.setProtocol(request.getProtocol());
		entity.setMethod(request.getMethod());
		entity.setParameter(getParameters(request));
		entity.setLocale(request.getLocale().toString());
		entity.setUserAgent(request.getHeader("user-agent"));
		entity.setTotalTimeMillis(stopWatch.getTotalTimeMillis());
		entity.setEffectiveDate(new Date());
		entity.setStatus(status);
		entity.setMsg(msg);

		// set request log key
        request.setAttribute(HttpTemplate.REQUEST_LOG_KEY, entity.getId());

		// persist
		repository.persist(entity);
	}

	@Override
	public void log(LogMock logMock) throws Exception {
		if (logMock.getStopWatch().isRunning()) {
			logMock.getStopWatch().stop();
		}
		persist((HttpServletRequest) logMock.getObject(), logMock.getStopWatch(), logMock.getType().toString(), logMock.getResult());
	}

}
