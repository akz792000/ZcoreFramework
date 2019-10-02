/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.component.method.MethodInvoker;

public class ListValueMethodInvokerModel extends AbstractComponentImpl {
	
	private MethodInvoker methodInvoker;
	
	public MethodInvoker getMethodInvoker() {
		return methodInvoker;
	}
	
	public void setMethodInvoker(MethodInvoker methodInvoker) {
		this.methodInvoker = methodInvoker;
	}
	
}
