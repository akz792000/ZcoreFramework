package org.zcoreframework.web.test.service;

import org.zcoreframework.base.exception.BaseException;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Ali Asghar Momeni Vesalian (momeni.vesalian@gmail.com)
 *         Date: 11/14/17
 */
@WebService
public interface SampleSoapService {

    String BEAN_NAME = "ZC_sampleSoapService";

    String WEB_SERVICE_NAME = BEAN_NAME;

    String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) throws Exception;

    String doSomething(@WebParam(name = "firstParam") String firstParam, @WebParam(name = "secondParam") String secondParam) throws BaseException;

    void logout(@WebParam(name = "username") String username, @WebParam(name = "password") String password) throws Exception;

}
