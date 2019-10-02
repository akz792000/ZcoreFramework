/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.core;

import java.util.HashMap;
import java.util.Map;

public class HandlerClassLoader {
	
	private Map<Class<?>, HandlerFieldLoader> handlerFieldLoader = new HashMap<>();
	
	public HandlerFieldLoader get(Class<?> clazz) {
		return this.handlerFieldLoader.get(clazz);		
	}
	
	public HandlerFieldLoader put(Class<?> clazz, HandlerFieldLoader handlerFieldLoader) {
		return this.handlerFieldLoader.put(clazz, handlerFieldLoader);
	}

}
