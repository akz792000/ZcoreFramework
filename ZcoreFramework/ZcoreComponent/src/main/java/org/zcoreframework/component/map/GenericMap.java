/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map;

import org.zcoreframework.base.component.Itemable;
import org.zcoreframework.component.listvalue.ListValue;

public interface GenericMap<E> extends Itemable<E> {
	
	ListValue listValue(Object key);
	
	ListValue listValue(Object key, String method);
	
	ListValue listValue(Object key, String method, String expression);
	
}
