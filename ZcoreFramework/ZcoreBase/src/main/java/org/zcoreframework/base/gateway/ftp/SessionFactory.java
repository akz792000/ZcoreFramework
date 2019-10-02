package org.zcoreframework.base.gateway.ftp;

import org.zcoreframework.base.gateway.exception.GatewayException;
import org.zcoreframework.base.gateway.ftp.iml.CsvSessionImpl;
import org.zcoreframework.base.gateway.ftp.iml.FtpSessionImpl;
import org.zcoreframework.base.gateway.model.registry.ServiceInfo;


/**
 *
 */
public final class SessionFactory {

    public static DocumentSession newCsvSession(ServiceInfo info) throws GatewayException {
        return new CsvSessionImpl(info);
    }

    public static FtpSession newSession(ServiceInfo info) throws GatewayException {
        return new FtpSessionImpl(info);
    }
}
