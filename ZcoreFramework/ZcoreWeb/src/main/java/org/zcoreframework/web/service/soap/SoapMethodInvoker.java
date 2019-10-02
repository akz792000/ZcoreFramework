/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.web.service.soap;

import org.apache.cxf.jaxws.JAXWSMethodInvoker;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.service.invoker.Factory;
import org.zcoreframework.base.core.Executor;
import org.zcoreframework.base.log.FactoryLog;
import org.zcoreframework.base.log.LogMock;
import org.zcoreframework.base.log.Logger;
import org.zcoreframework.base.method.MethodExecutor;
import org.zcoreframework.base.method.MethodTemplateExecutor;
import org.zcoreframework.base.util.ReflectionUtils;
import org.zcoreframework.security.web.authorization.AbstractAuthorizationClientFilter;
import org.zcoreframework.web.log.SoapLogger;

import javax.xml.ws.handler.MessageContext;
import java.lang.reflect.Method;
import java.util.List;

public class SoapMethodInvoker extends JAXWSMethodInvoker {

    static final Logger logger = FactoryLog.getLogger(SoapLogger.class);

    private final String serviceName;
    private final AbstractAuthorizationClientFilter authorizationClientFilter;
    private final MethodTemplateExecutor methodTemplateExecutor;

    public SoapMethodInvoker(Factory factory, String serviceName, AbstractAuthorizationClientFilter authorizationClientFilter, MethodTemplateExecutor methodTemplateExecutor) {
        super(factory);
        this.serviceName = serviceName;
        this.authorizationClientFilter = authorizationClientFilter;
        this.methodTemplateExecutor = methodTemplateExecutor;
    }

    @Override
    protected Object invoke(Exchange exchange, Object serviceObject, Method method, List<Object> params) {
        WrappedMessageContext context = new WrappedMessageContext(exchange.getInMessage(), MessageContext.Scope.APPLICATION);
        Object result = null;
        try {
            authorizationClientFilter.doFilter(context, context, buildRequestURI(serviceName, method));

            result = methodTemplateExecutor.execute(
                    new MethodExecutor() {
                        final LogMock logMock = new LogMock(new SoapContext(context, params));

                        @Override
                        public Executor getExecutor() {
                            return () -> SoapMethodInvoker.super.invoke(exchange, serviceObject, method, params);
                        }

                        @Override
                        public Executor getLogger(String result, LogMock.Type type) {
                            return () -> {
                                logMock.setType(type);
                                logMock.setResult(result);
                                logger.log(logMock);
                                return null;
                            };
                        }

                        @Override
                        public Class<? extends Throwable>[] noRollbackFor() {
                            return null;
                        }

                    }
            );
        } catch (Exception e) {
            ReflectionUtils.handleReflectionException(e);
        }
        return result;
    }

    private String buildRequestURI(String serviceUrl, Method method) {
        return String.format("/service/%s/%s", serviceUrl, method.getName());
    }
}
