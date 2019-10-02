package org.zcoreframework.web.test.mock;

import mockit.Mock;
import mockit.MockUp;
import org.springframework.security.core.GrantedAuthority;
import org.zcoreframework.base.model.PropertyModel;
import org.zcoreframework.base.model.PropertyModelImpl;
import org.zcoreframework.security.core.ComplexGrantedAuthority;
import org.zcoreframework.security.core.SecurityUriImpl;
import org.zcoreframework.security.model.UserInfo;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * Created by vesalian on 2017 Jun 10
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@Gmail.com)
 */
public class UserInfoStub extends MockUp<UserInfo> {

    private static final Long ZERO_LONG = 0L;
    private static final String USER_NAME = "Test";
    public static final String SAMPLE_SOAP_SERVICE_DO_SOMETHING_URI = "/service/ZC_sampleSoapService/doSomething";

    /**
     * Stub implementation of {@link UserInfo#getId()}
     */
    @Mock
    public long getId() {
        return ZERO_LONG;
    }

    /**
     * Stub implementation of {@link UserInfo#getUsername()}
     */
    @Mock
    public String getUsername() {
        return USER_NAME;
    }

    /**
     * Stub implementation of {@link UserInfo#getOrganization()}
     */
    @Mock
    public PropertyModel getOrganization() {
        final PropertyModelImpl organizationModel = new PropertyModelImpl();
        organizationModel.setId(ZERO_LONG);
        return organizationModel;
    }

    /**
     * Stub implementation of {@link UserInfo#getAuthorities()}
     */
    @Mock
    public List<GrantedAuthority> getAuthorities() {
        final SecurityUriImpl securityUri = new SecurityUriImpl();
        final PropertyModelImpl securityModel = new PropertyModelImpl();
        final ComplexGrantedAuthority grantedAuthority = new ComplexGrantedAuthority();

        securityUri.setPrivileges(singletonList(SAMPLE_SOAP_SERVICE_DO_SOMETHING_URI));
        securityModel.setObject(securityUri);
        grantedAuthority.setRoles(singletonList(securityModel));

        return singletonList(grantedAuthority);
    }
}
