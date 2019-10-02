/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.util;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

public class Chain implements Serializable {

	private static final long serialVersionUID = 1L;

	private Object object;

	private Map<Object, Chain> map;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public Map<Object, Chain> getMap() {
		return map;
	}

	public void setMap(Map<Object, Chain> map) {
		this.map = map;
	}

	public Chain() {
	}

	public Chain(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		String result = getObject() == null ? "null" : getObject().toString();
		String subStr = "";
		if (map != null) {
			for (Entry<Object, Chain> entry : map.entrySet()) {
				subStr += StringUtils.isEmpty(subStr) ? ",items:[" : ",";
				subStr += "{key:" + entry.getKey() + ",value:" + entry.getValue().getObject() + "}";
			}
			subStr += "]";
		}
		return result + subStr;
	}

}
