package org.zcoreframework.pos.data;

import org.jpos.iso.ISOMsg;
import org.zcoreframework.pos.constant.ISOMethodReturnType;

import java.lang.reflect.Method;

/**
 *
 */
public class MethodMetadata {

    private Method method;

    private int paramCount;

    private int idxInJMsg;

    private int idxInZMsg;

    private ISOMethodReturnType returnType;

    public MethodMetadata(Method method) {
        this.idxInJMsg = -1;
        this.idxInZMsg = -1;
        this.method = method;
        this.paramCount = method.getParameterCount();
        this.returnType = ISOMethodReturnType.VOID;

        if (ISOMsg.class.equals(method.getReturnType())) {
            this.returnType = ISOMethodReturnType.JPOS;
        } else if (ISOMessage.class.equals(method.getReturnType())) {
            this.returnType = ISOMethodReturnType.ZMSG;
        }

        for (int i = 0; i < this.paramCount; i++) {
            if (ISOMsg.class.equals(method.getParameterTypes()[i])) {
                this.idxInJMsg = i;
            } else if (ISOMessage.class.equals(method.getParameterTypes()[i])) {
                this.idxInZMsg = i;
            }
        }
    }

    public Method getMethod() {
        return method;
    }

    public int getIdxInJMsg() {
        return idxInJMsg;
    }

    public int getIdxInZMsg() {
        return idxInZMsg;
    }

    public ISOMethodReturnType getReturnType() {
        return returnType;
    }

    public int getParamCount() {
        return paramCount;
    }
}
