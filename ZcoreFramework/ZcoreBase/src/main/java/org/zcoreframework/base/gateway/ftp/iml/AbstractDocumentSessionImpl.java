package org.zcoreframework.base.gateway.ftp.iml;

import org.zcoreframework.base.gateway.exception.GatewayException;
import org.zcoreframework.base.gateway.ftp.DocumentSession;
import org.zcoreframework.base.gateway.model.file.Document;
import org.zcoreframework.base.gateway.model.file.HeadInfo;
import org.zcoreframework.base.gateway.model.registry.ServiceInfo;

/**
 * @author Hossein Amiri Parian - parian66@gmail.com
 *         Date 10/29/2017.
 */
public abstract class AbstractDocumentSessionImpl extends FtpSessionImpl implements DocumentSession {

    public AbstractDocumentSessionImpl(ServiceInfo serviceInfo) throws GatewayException {
        super(serviceInfo);
    }

    public <M extends Document> M read(String resource, Class<M> clazz) throws GatewayException {
        return read(new HeadInfo(resource), clazz);
    }

    public <M extends Document> M read(HeadInfo headInfo, Class<M> clazz) throws GatewayException {
        validateHead(headInfo);

        try {
            connect();
            return readData(headInfo, clazz);
        } finally {
            close();
        }
    }

    public <M extends Document> boolean save(M document) throws GatewayException {
        validateDocumnet(document);
        validateHead(document.getHeadInfo());

        try {
            connect();
            return saveData(document);
        } finally {
            close();
        }
    }

    protected void validateHead(HeadInfo head) throws GatewayException {
        if (head == null) {
            throw new GatewayException("_TODO");
        } else if (head.getResource() == null || head.getResource().isEmpty()) {
            throw new GatewayException("_TODO");
        }
    }

    protected void validateDocumnet(Document document) throws GatewayException {
        if (document == null) {
            throw new GatewayException("_TODO");
        } else if (document.getEntries() == null || document.getEntries().isEmpty()) {
            throw new GatewayException("_TODO");
        }
    }
}
