package org.zcoreframework.base.gateway.ftp.iml;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.zcoreframework.base.gateway.constant.GatewayConstants;
import org.zcoreframework.base.gateway.constant.ProtocolType;
import org.zcoreframework.base.gateway.exception.GatewayException;
import org.zcoreframework.base.gateway.ftp.FtpSession;
import org.zcoreframework.base.gateway.model.registry.ServiceInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */

public class FtpSessionImpl implements FtpSession {

    private final FTPClient client;

    private final ServiceInfo serviceInfo;

    public FtpSessionImpl(ServiceInfo serviceInfo) throws GatewayException {
        if (serviceInfo == null) {
            throw new GatewayException("_TODO");
        } else if (!ProtocolType.FTP.equals(serviceInfo.getProtocol())) {
            throw new GatewayException("_TODO");
        }

        this.client = new FTPClient();
        this.serviceInfo = serviceInfo;
    }

    @Override
    public boolean delete(String path) throws GatewayException {
        try {
            connect();
            return this.client.deleteFile(path);
        } catch (IOException e) {
            throw new GatewayException("_TODO", e);
        } finally {
            close();
        }
    }

    @Override
    public boolean exists(String path) throws GatewayException {
        try {
            connect();
            this.client.getStatus(path);
            return FTPReply.isPositiveCompletion(this.client.getReplyCode());
        } catch (IOException e) {
            throw new GatewayException("_TODO", e);
        } finally {
            close();
        }
    }

    @Override
    public boolean makeDirectory(String path) throws GatewayException {
        try {
            connect();
            makeDirectoryTree(path);
            return true;
        } catch (IOException e) {
            throw new GatewayException("_TODO", e);
        } finally {
            close();
        }
    }

    @Override
    public byte[] read(String resource) throws GatewayException {
        try {
            return IOUtils.toByteArray(stream(resource));
        } catch (IOException e) {
            throw new GatewayException("_TODO", e);
        } finally {
            close();
        }
    }

    @Override
    public List<String> list(String directory, String endWith) throws GatewayException {
        List<String> list = new ArrayList<>();
        try {
            connect();
            for (FTPFile file : getClient().listFiles(directory, ftpFile -> ftpFile.getName().endsWith(endWith))) {
                list.add(directory + file.getName());
            }
        } catch (IOException e) {
            throw new GatewayException("_TODO", e);
        } finally {
            close();
        }

        return list;
    }

    @Override
    public boolean save(String remote, byte[] bytes) throws GatewayException {
        return save(remote, new ByteArrayInputStream(bytes));
    }

    @Override
    public boolean save(String remote, InputStream inputStream) throws GatewayException {
        try {
            connect();
            return getClient().storeFile(remote, inputStream);
        } catch (IOException e) {
            throw new GatewayException("_TODO", e);
        } finally {
            close();
        }
    }


    protected FTPClient getClient() {
        return client;
    }

    protected ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    protected void close() throws GatewayException {
        if (this.client.isConnected()) {
            try {
                this.client.disconnect();
            } catch (IOException e) {
                throw new GatewayException("_TODO", e);
            }
        }
    }

    protected void connect() throws GatewayException {
        if (client.isConnected()) {
            return;
        }

        try {
            client.connect(serviceInfo.getHost(), serviceInfo.getPort());
            int reply = client.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                throw new GatewayException("_TODO");
            }
        } catch (IOException e) {
            throw new GatewayException("_TODO", e);
        }

        client.enterLocalPassiveMode();

        String username = serviceInfo.getProperty(GatewayConstants.KEY_USERNAME);
        String password = serviceInfo.getProperty(GatewayConstants.KEY_PASSWORD);

        if (username == null) {
            return;
        }

        try {
            if (!client.login(username, password)) {
                client.disconnect();
                throw new GatewayException("_TODO");
            }
        } catch (IOException e) {
            throw new GatewayException("_TODO", e);
        }
    }

    private InputStream stream(String resource) throws GatewayException {
        try {
            connect();
            return getClient().retrieveFileStream(resource);
        } catch (IOException e) {
            throw new GatewayException("_TODO", e);
        }
    }

    private void makeDirectoryTree(String dirTree) throws IOException {

        boolean dirExists = true;

        //tokenize the string and attempt to change into each directory level.  If you cannot, then start creating.
        String[] directories = dirTree.split("/");
        for (String dir : directories) {
            if (!dir.isEmpty()) {
                if (dirExists) {
                    dirExists = client.changeWorkingDirectory(dir);
                }
                if (!dirExists) {
                    if (!client.makeDirectory(dir)) {
                        throw new IOException("Unable to create remote directory '" + dir + "'.  error='" + client.getReplyString() + "'");
                    }
                    if (!client.changeWorkingDirectory(dir)) {
                        throw new IOException("Unable to change into newly created remote directory '" + dir + "'.  error='" + client.getReplyString() + "'");
                    }
                }
            }
        }
    }

}
