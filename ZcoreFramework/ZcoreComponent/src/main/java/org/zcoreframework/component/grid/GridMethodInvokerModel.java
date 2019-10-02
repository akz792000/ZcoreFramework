/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import org.zcoreframework.component.method.MethodInvoker;

public class GridMethodInvokerModel extends GridModel {
	
	private MethodInvoker methodInvoker;
	
	public MethodInvoker getMethodInvoker() {
		return methodInvoker;
	}
	
	public void setMethodInvoker(MethodInvoker methodInvoker) {
		this.methodInvoker = methodInvoker;
	}
	
}
