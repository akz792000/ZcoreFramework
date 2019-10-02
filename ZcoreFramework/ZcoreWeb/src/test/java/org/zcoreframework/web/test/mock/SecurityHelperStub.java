package org.zcoreframework.web.test.mock;

import mockit.Mock;
import mockit.MockUp;
import org.zcoreframework.security.core.SecurityHelper;
import org.zcoreframework.security.model.UserInfo;

/**
 * Created by vesalian on 2017 Jun 10
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@Gmail.com)
 */
public class SecurityHelperStub extends MockUp<SecurityHelper> {

    /**
     * Stub implementation of {@link SecurityHelper#getUserInfo()}
     */
    @Mock
    public static UserInfo getUserInfo() {
        new UserInfoStub(); // Active UserInfoStub to proxy any instance of UserInfo
        return new UserInfo();
    }
}
