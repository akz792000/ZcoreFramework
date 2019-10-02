/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.tree;

import org.zcoreframework.component.method.MethodInvoker;

public class TreeMethodInvokerModel extends TreeModel {
	
	private MethodInvoker methodInvoker;
	
	public MethodInvoker getMethodInvoker() {
		return methodInvoker;
	}
	
	public void setMethodInvoker(MethodInvoker methodInvoker) {
		this.methodInvoker = methodInvoker;
	}

}