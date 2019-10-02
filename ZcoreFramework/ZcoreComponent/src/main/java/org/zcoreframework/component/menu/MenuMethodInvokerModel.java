package org.zcoreframework.component.menu;

import org.zcoreframework.component.method.MethodInvoker;

/**
 * Created by z.azadi on 7/11/2016.
 */
public class MenuMethodInvokerModel extends MenuEntityModel {

    private MethodInvoker methodInvoker;

    public MethodInvoker getMethodInvoker() {
        return methodInvoker;
    }

    public void setMethodInvoker(MethodInvoker methodInvoker) {
        this.methodInvoker = methodInvoker;
    }
}
