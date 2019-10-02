package org.zcoreframework.base.gateway.ftp;


import org.zcoreframework.base.gateway.exception.GatewayException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 */
public interface FtpSession {

    boolean delete(String path) throws GatewayException;

    boolean exists(String path) throws GatewayException;

    boolean makeDirectory(String path) throws GatewayException;

    byte[] read(String resource) throws GatewayException;

    List<String> list(String directory, String endWith) throws GatewayException;

    boolean save(String remote, byte[] bytes) throws GatewayException;

    boolean save(String remote, InputStream inputStream) throws GatewayException;
}