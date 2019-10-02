package org.zcoreframework.web.test.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import mockit.Mock;
import mockit.MockUp;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.base.util.JsonUtils;
import org.zcoreframework.security.model.UserInfo;

import java.io.IOException;

/**
 * Created by vesalian on 2017 Jun 10
 *
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@Gmail.com)
 */
public class HttpTemplateStub extends MockUp<HttpTemplate> {

    /**
     * Mock implementation of {@link HttpTemplate#execute(java.lang.String, java.lang.Class)}
     */
    @Mock
    @SuppressWarnings("unchecked")
    public <T> T execute(String url, Class<T> clazz) throws IOException {
        new UserInfoStub(); // Active UserInfoStub to proxy any instance of UserInfo
        return (T) JsonUtils.encode(new UserInfo(), ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT);
    }
}
