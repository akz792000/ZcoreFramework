/**
 *
 * @author Ali Karimizandi
 * @since 2009
 *
 */

package org.zcoreframework.web.service.soap;

import org.apache.cxf.message.Exchange;
import org.apache.cxf.service.invoker.Factory;
import org.zcoreframework.base.util.ApplicationContextUtils;

public class SoapBeanFactory implements Factory {

    private final Class<?> beanClass;

    public SoapBeanFactory(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public Object create(Exchange exchange) throws Throwable {
        return ApplicationContextUtils.createBean(beanClass);
    }

    @Override
    public void release(Exchange exchange, Object o) {
    }

}
