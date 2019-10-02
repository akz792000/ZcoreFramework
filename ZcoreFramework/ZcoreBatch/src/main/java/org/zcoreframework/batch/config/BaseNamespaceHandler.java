/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.batch.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class BaseNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("batch-config", new BatchConfigDefinitionParser());
    }
}
