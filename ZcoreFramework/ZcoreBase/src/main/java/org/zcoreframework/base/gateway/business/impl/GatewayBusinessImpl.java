package org.zcoreframework.base.gateway.business.impl;

import org.zcoreframework.base.gateway.business.GatewayBusiness;
import org.zcoreframework.base.gateway.exception.GatewayException;
import org.zcoreframework.base.gateway.ftp.DocumentSession;
import org.zcoreframework.base.gateway.ftp.FtpSession;
import org.zcoreframework.base.gateway.ftp.SessionFactory;
import org.zcoreframework.base.gateway.model.registry.ServiceInfo;

/**
 * @author Hossein Amiri Parian - parian66@gmail.com
 *         Date 10/29/2017.
 */
public class GatewayBusinessImpl implements GatewayBusiness {

    @Override
    public FtpSession openFtpSession(ServiceInfo serviceInfo) throws GatewayException {
        return SessionFactory.newSession(serviceInfo);
    }

    @Override
    public DocumentSession openCsvFtpSession(ServiceInfo serviceInfo) throws GatewayException {
        return SessionFactory.newCsvSession(serviceInfo);
    }
}
