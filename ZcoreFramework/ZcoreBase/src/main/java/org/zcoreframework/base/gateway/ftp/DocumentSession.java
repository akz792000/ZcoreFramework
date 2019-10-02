package org.zcoreframework.base.gateway.ftp;

import org.zcoreframework.base.gateway.exception.GatewayException;
import org.zcoreframework.base.gateway.model.file.Document;
import org.zcoreframework.base.gateway.model.file.HeadInfo;

/**
 * @author Hossein Amiri Parian - parian66@gmail.com
 *         Date 10/29/2017.
 */
public interface DocumentSession extends FtpSession {

    <M extends Document> M readData(HeadInfo headInfo, Class<M> clazz) throws GatewayException;

    <M extends Document> boolean saveData(M document) throws GatewayException;

}
