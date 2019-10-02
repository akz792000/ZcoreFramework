/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.zcoreframework.mapping.handler.Handler;
import org.zcoreframework.mapping.model.Model;

public class HandlerFieldLoader {
	
	private Map<Field, Handler<? extends Model>> fieldHandler = new HashMap<>();
	
	public Handler<? extends Model> get(Field field) {
		return fieldHandler.get(field);		
	}
	
	public Handler<? extends Model> put(Field field, Handler<? extends Model> handlers) {
		return this.fieldHandler.put(field, handlers);
	}	
	
	public Map<Field, Handler<? extends Model>> getFieldHandler() {
		return fieldHandler;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Entry<Field, Handler<? extends Model>> entry : fieldHandler.entrySet()) {			 
			if (!result.isEmpty())
				result += ",";
			result += entry.getKey().getName() + ":" + entry.getValue();
		}
		return "{" + result + "}";
	}
	
}
