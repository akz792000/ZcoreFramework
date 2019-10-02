package org.zcoreframework.pos.utils;

import org.jpos.iso.ISOMsg;
import org.zcoreframework.pos.constant.ISOMethodReturnType;
import org.zcoreframework.pos.data.ISOMessage;
import org.zcoreframework.pos.data.MethodMetadata;
import org.zcoreframework.pos.exception.PosRuntimeException;
import org.zcoreframework.pos.exception.PosStandardException;
import org.zcoreframework.pos.exception.PosSystemException;

import java.lang.reflect.InvocationTargetException;

/**
 *
 */
public final class InvokerUtils {

    public static ISOMsg invoke(Object bean, MethodMetadata metadata, ISOMsg inMsg) {
        if (bean == null || metadata == null) {
            throw new PosSystemException("can not find target for message");
        } else if (metadata.getIdxInJMsg() < 0 && metadata.getIdxInZMsg() < 0) {
            throw new PosSystemException("invalid input parameter of method");
        }

        ISOMessage zMessage = null;
        Object[] params = new Object[metadata.getParamCount()];

        if (metadata.getIdxInJMsg() > -1) {
            params[metadata.getIdxInJMsg()] = inMsg;
        } else if (metadata.getIdxInZMsg() > -1) {
            zMessage = ISOMsgUtils.convert(inMsg);
            params[metadata.getIdxInZMsg()] = zMessage;
        }

        Object output = null;

        try {
            output = metadata.getMethod().invoke(bean, params);
        } catch (IllegalAccessException e) {
            throw new PosSystemException("error in service invocation", e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof PosRuntimeException) {
                throw (PosRuntimeException)e.getCause();
            }

            throw new PosSystemException("fatal error in service invocation", e.getCause());
        }

        if (ISOMethodReturnType.JPOS.equals(metadata.getReturnType())) {
            return (ISOMsg) output;
        } else if (ISOMethodReturnType.ZMSG.equals(metadata.getReturnType())) {
            return ISOMsgUtils.merge(inMsg, (ISOMessage) output);
        }

        return ISOMsgUtils.merge(inMsg, zMessage);
    }
}
