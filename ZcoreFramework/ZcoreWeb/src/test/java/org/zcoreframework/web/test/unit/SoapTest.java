package org.zcoreframework.web.test.unit;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.databinding.DataBinding;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.security.web.authorization.AbstractAuthorizationClientFilter;
import org.zcoreframework.web.test.mock.HttpTemplateStub;
import org.zcoreframework.web.test.mock.SecurityHelperStub;
import org.zcoreframework.web.test.service.SampleSoapServiceImpl;
import org.zcoreframework.web.test.util.TestUtils;


/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 4/26/17
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/testContext.xml"})
@SuppressWarnings({"Duplicates", "SpringJavaAutowiringInspection"})
public class SoapTest implements InitializeAware {

    private final Logger LOGGER = Logger.getLogger(SoapTest.class);

    private static final String SAMPLE_SOAP_SERVICE_WSDL_URL = "http://127.0.0.1:6060/core/service/ZC_sampleSoapService?wsdl";

    @Autowired
    private TestUtils testUtils;

    @Before
    public void setUp() throws Exception {
        LOGGER.info("Database is cleaned.");
    }

    @After
    public void tearDown() throws Exception {
        LOGGER.info("All test is ran.");
    }

    @Test
    public void loginTest() throws Exception {
        // Replay phase
        final String sessionId = login();

        // Verify phase
        Assert.assertNotNull(sessionId);
        Assert.assertEquals(SampleSoapServiceImpl.SESSION_ID, sessionId);
    }

    @Test
    public void callAnyWebServicesTest() throws Exception {
        // Record phase
        new HttpTemplateStub(); // Active HttpTemplateStub to proxy any instance of HttpTemplate
        new SecurityHelperStub(); // Active SecurityHelperStub to proxy any instance of SecurityHelper

        final String sessionId = login();
        final DataBinding sessionType = new JAXBDataBinding(String.class);
        final Header sessionKeyHeader = new Header(AbstractAuthorizationClientFilter.SESSION_KEY, sessionId, sessionType);

        // Replay phase
        final Client client = getSampleSoapServiceClient();
        client.getOutInterceptors().add(new AbstractPhaseInterceptor<Message>(Phase.SETUP) {
            @Override
            public void handleMessage(Message message) throws Fault {
                ((SoapMessage) message).getHeaders().add(sessionKeyHeader);
            }
        });
        final String fullName = (String) client.invoke("doSomething", "Ali Asghar", "Momeni Vesalian")[0];

        // Verify phase
        Assert.assertNotNull(fullName);
        Assert.assertEquals("Ali Asghar Momeni Vesalian", fullName);
    }

    private String login() throws Exception {
        final Client client = getSampleSoapServiceClient();
        return (String) client.invoke("login", "username", "password")[0];
    }

    private Client getSampleSoapServiceClient() {
        return testUtils.getWebServiceClient(SAMPLE_SOAP_SERVICE_WSDL_URL);
    }
}
