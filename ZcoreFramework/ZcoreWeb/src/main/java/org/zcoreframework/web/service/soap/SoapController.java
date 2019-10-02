/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.web.service.soap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.eclipse.persistence.jpa.jpql.Assert;
import org.zcoreframework.base.method.MethodTemplateExecutor;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.security.web.authorization.AbstractAuthorizationClientFilter;

import javax.jws.WebService;
import java.util.Map;

public class SoapController {

    private final Log logger = LogFactory.getLog(getClass());

    private String protocol = "http";

    private String address = "0.0.0.0";

    private Integer port;

    private String path = "service";

    private AbstractAuthorizationClientFilter authorizationClientFilter;

    private MethodTemplateExecutor methodTemplateExecutor;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public AbstractAuthorizationClientFilter getAuthorizationClientFilter() {
        return authorizationClientFilter;
    }

    public void setAuthorizationClientFilter(AbstractAuthorizationClientFilter authorizationClientFilter) {
        this.authorizationClientFilter = authorizationClientFilter;
    }

    public MethodTemplateExecutor getMethodTemplateExecutor() {
        return methodTemplateExecutor;
    }

    public void setMethodTemplateExecutor(MethodTemplateExecutor methodTemplateExecutor) {
        this.methodTemplateExecutor = methodTemplateExecutor;
    }

    public void publish() throws ClassNotFoundException {
        String url = protocol + "://" + address + ":" + port + "/core/" + path;
        Map<String, Class<?>> beans = ApplicationContextUtils.getBeanNamesForAnnotation(WebService.class);
        beans.forEach((key, value) -> {

            // get proper interface
            Class<?>[] interfaces = new Class[1];
            for (Class clazz : value.getInterfaces()) {
                if (clazz.getAnnotation(WebService.class) != null) {
                    Assert.isNull(interfaces[0], "Class has more than one interface that has WebService annotation.");
                    interfaces[0] = clazz;
                }
            }
            Assert.isNotNull(interfaces[0], "Class does not have any interface that marks with WebService annotation.");

            // create JAX-WS endpoints
            String serviceUrl = url + "/" + key;
            JaxWsServerFactoryBean factoryBean = new SoapServerFactoryBean(key, authorizationClientFilter, methodTemplateExecutor);
            factoryBean.setServiceClass(value);
            factoryBean.setAddress(serviceUrl);
            factoryBean.getInInterceptors().add(new LoggingInInterceptor());
            factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
            factoryBean.create();

            // log
            logger.info(serviceUrl + " has been created.");

        });
    }

}
