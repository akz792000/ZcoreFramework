/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.core;

import java.lang.reflect.Method;

public interface ActionMethod extends Executor {
	
	Object getTarget();
	
	Method getMethod();
	
	Object[] getArgs();
		
}
