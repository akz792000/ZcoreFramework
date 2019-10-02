package org.zcoreframework.web.test.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.springframework.stereotype.Component;
import org.zcoreframework.base.annotation.RepositoryInstance;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.data.base.DefaultRepository;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/27/17
 */
@Component
public class TestUtils implements InitializeAware {

    @RepositoryInstance
    private DefaultRepository repository;

    private DynamicClientFactory webServiceClientFactory = DynamicClientFactory.newInstance();

    public Client getWebServiceClient(String wsdlUrl) {
        return webServiceClientFactory.createClient(wsdlUrl);
    }
}
