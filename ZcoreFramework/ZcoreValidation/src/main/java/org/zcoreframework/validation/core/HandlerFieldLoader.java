/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.core;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.zcoreframework.validation.handler.Handler;
import org.zcoreframework.validation.model.Model;

public class HandlerFieldLoader {
	
	private Map<Field, List<Handler<? extends Model>>> handlers = new HashMap<>();
	
	public List<Handler<? extends Model>> get(Field field) {
		return handlers.get(field);		
	}
	
	public List<Handler<? extends Model>> put(Field field, List<Handler<? extends Model>> handlers) {
		return this.handlers.put(field, handlers);
	}	
	
	public Map<Field, List<Handler<? extends Model>>> getHandlers() {
		return handlers;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Entry<Field, List<Handler<? extends Model>>> entry : handlers.entrySet()) {			 
			if (!result.isEmpty())
				result += ",";
			result += entry.getKey().getName() + ":" + entry.getValue();
		}
		return "{" + result + "}";
	}
	
}
