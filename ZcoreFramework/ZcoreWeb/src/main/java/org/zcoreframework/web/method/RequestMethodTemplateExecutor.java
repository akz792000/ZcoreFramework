package org.zcoreframework.web.method;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.zcoreframework.base.core.Executor;
import org.zcoreframework.base.log.FactoryLog;
import org.zcoreframework.base.log.LogMock;
import org.zcoreframework.base.log.LogMock.Type;
import org.zcoreframework.base.log.Logger;
import org.zcoreframework.base.method.MethodExecutor;
import org.zcoreframework.base.method.MethodTemplateExecutor;
import org.zcoreframework.web.log.RequestLogger;

public interface RequestMethodTemplateExecutor {
	
	static final Logger logger = FactoryLog.getLogger(RequestLogger.class);

	MethodTemplateExecutor getMethodTemplateExecutor();
	
	default Object invoke(HttpServletRequest request, Executor executor) throws Exception {

		return invoke(request, executor, null);
	}	

	default Object invoke(HttpServletRequest request, Executor executor, Class<? extends Throwable>[] noRollbackFor) throws Exception {

		return getMethodTemplateExecutor().execute(new MethodExecutor() {

			LogMock logMock = new LogMock();

			@Override
			public Executor getExecutor() {
				return () -> executor.execute();
			}			

			@Override
			public Executor getLogger(String result, Type type) {
				return () -> {
					request.setAttribute("log-mock", true);
					logMock.setType(type);
					logMock.setObject(request);
					logMock.setResult(result);
					logger.log(logMock);
					return null;
				};
			}
			
			@Override
			public Class<? extends Throwable>[] noRollbackFor() {
				return noRollbackFor;
			}

		});
	}

}
