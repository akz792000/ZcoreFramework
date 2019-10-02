package org.zcoreframework.web.test.service;

import org.springframework.stereotype.Component;
import org.zcoreframework.base.exception.BaseException;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 11/14/17
 */
@Component(SampleSoapService.BEAN_NAME)
public class SampleSoapServiceImpl implements SampleSoapService {

    public static final String SESSION_ID = "1234567890";

    @Override
    public String login(String username, String password) throws Exception {
        return SESSION_ID;
    }

    @Override
    public String doSomething(String firstParam, String secondParam) throws BaseException {
        return String.format("%s %s", firstParam, secondParam);
    }

    @Override
    public void logout(String username, String password) throws Exception {
        // Do not somethings.
    }
}
