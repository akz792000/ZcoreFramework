/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.core;

import java.lang.reflect.Method;

import org.zcoreframework.base.component.AjaxClientable;
import org.zcoreframework.base.util.ReflectionUtils;

public class ActionMethodImpl implements ActionMethod {
	
	private Object target;
	
	private Method method;
	
	private Object[] args;
	 
	@Override
	public Object getTarget() {
		return target;
	}
	
	public void setTarget(Object target) {
		this.target = target;
	}
	
	@Override
	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
	
	@Override
	public Object[] getArgs() {
		return args;
	}	
	
	public void setArgs(Object... args) {
		this.args = args;
	}	

	@Override
	public Object execute() throws Exception {
		Object result = ReflectionUtils.invokeMethod(method, target, args);
		
		// partial for ajaxable component
		if (result instanceof AjaxClientable) {
			result = ((AjaxClientable) result).partial();
		}
		
		return result;
	}
		
}
