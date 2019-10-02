package org.zcoreframework.base.gateway.business;

import org.zcoreframework.base.gateway.exception.GatewayException;
import org.zcoreframework.base.gateway.ftp.DocumentSession;
import org.zcoreframework.base.gateway.ftp.FtpSession;
import org.zcoreframework.base.gateway.model.registry.ServiceInfo;

/**
 * @author Hossein Amiri Parian - parian66@gmail.com
 *         Date 10/30/2017.
 */
public interface GatewayBusiness {

    FtpSession openFtpSession(ServiceInfo serviceInfo) throws GatewayException;

    DocumentSession openCsvFtpSession(ServiceInfo serviceInfo) throws GatewayException;

}
