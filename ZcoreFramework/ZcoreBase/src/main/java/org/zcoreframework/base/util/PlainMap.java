/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.util;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class PlainMap extends LinkedHashMap<Object, Object> {

	private static final long serialVersionUID = 1L;
	
	public <E> List<E> getDetails(Comparable<E> comparable) {
		List<E> result = null;
		for (java.util.Map.Entry<Object, Object> entry : entrySet()) {
			@SuppressWarnings("unchecked")
			E object = (E) entry.getValue();
			if (comparable.compareTo(object) == 0) {
				if (result == null) {
					result = new LinkedList<>();
				}
				result.add(object);
			}
		}
		return result;
	}

}
