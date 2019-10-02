/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.web.service.soap;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.service.invoker.Invoker;
import org.zcoreframework.base.method.MethodTemplateExecutor;
import org.zcoreframework.security.web.authorization.AbstractAuthorizationClientFilter;

public class SoapServerFactoryBean extends JaxWsServerFactoryBean {

    private final String serviceName;
    private final AbstractAuthorizationClientFilter authorizationClientFilter;
    private final MethodTemplateExecutor methodTemplateExecutor;

    public SoapServerFactoryBean(String serviceName, AbstractAuthorizationClientFilter authorizationClientFilter, MethodTemplateExecutor methodTemplateExecutor) {
        super();
        this.serviceName = serviceName;
        this.authorizationClientFilter = authorizationClientFilter;
        this.methodTemplateExecutor = methodTemplateExecutor;
    }

    protected Invoker createInvoker() {
        return new SoapMethodInvoker(new SoapBeanFactory(this.getServiceClass()), serviceName, authorizationClientFilter, methodTemplateExecutor);
    }
}
