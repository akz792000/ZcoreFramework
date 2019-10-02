package org.zcoreframework.pos.service;

import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.pos.data.MethodMetadata;

/**
 *
 */
public interface ISOServiceRegistry extends InitializeAware {

    Object getBean(String mti);

    MethodMetadata getMethodMetadata(String mti, String processCode);
}
