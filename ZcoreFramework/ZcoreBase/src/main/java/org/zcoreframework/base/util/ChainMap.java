/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.util.StringUtils;

public class ChainMap implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * You should not use a HashMap without handling the thread-safety of your code. Else, you may end with a Live-lock.
	 * 
	 * To be able to iterate your Map with the order in which keys were inserted, you can use a LinkedHashMap.
	 * 
	 * Map m = Collections.synchronizedMap(new LinkedHashMap(...));
	 * 
	 * http://stackoverflow.com/questions/11107433/is-it-possible-to-create-a-queue-for-hashmap-set
	 * 
	 */
	private Map<Object, Chain> member = new LinkedHashMap<>();
	
	public Map<Object, Chain> getMember() {
		return member;
	}

	public void setMember(Map<Object, Chain> member) {
		this.member = member;
	}

	public void put(Object master, Object value) {
		member.put(master, new Chain(value));
	}
	
	public Chain get(Object master) {
		return member.get(master);
	}	
		
	public Object getObject(Object master) {
		return member.get(master).getObject();
	}
	
	public Chain get(Object master, Object detail) {
		Chain chainMap = member.get(master);
		return chainMap == null ? null : chainMap.getMap().get(detail);
	}
	
	public Object getObject(Object master, Object detail) {
		Chain chainMap = member.get(master);
		return chainMap == null ? null : chainMap.getMap().get(detail).getObject();
	}		
	
	public Map<Object, Chain> getByGroupMap(Object master) {
		return member.get(master).getMap();
	}	
	
	public <E> List<E> getByGroupList(Object master) {
		List<E> result = new ArrayList<>();
		Chain chainMap = member.get(master);
		Map<Object, Chain> subMap = chainMap.getMap();
		if (subMap != null) {
			for (Entry<Object, Chain> entry : subMap.entrySet()) {
				@SuppressWarnings("unchecked")
				E object = (E) entry.getValue().getObject();
				result.add(object);
			}
		}
		return result;
	}
	
	public <E> List<E> getByGroupList(Object master, Comparable<E> comparable) {
		List<E> result = null;
		Chain chainMap = member.get(master);
		Map<Object, Chain> subMap = chainMap.getMap();
		if (subMap != null) {
			for (Entry<Object, Chain> entry : subMap.entrySet()) {
				@SuppressWarnings("unchecked")
				E object = (E) entry.getValue().getObject();
				if (comparable.compareTo((E) object) == 0) {
					if (result == null) {
						result = new LinkedList<>();
					}
					result.add(object);
				}
			}
		}
		return result;
	}
	
	public <E> List<E> getDetails(Comparable<E> comparable) {
		List<E> result = null;
		for (Entry<Object, Chain> entry : member.entrySet()) {
			Chain chainMap = entry.getValue();
			Map<Object, Chain> subMap = chainMap.getMap();
			if (subMap != null) {
				for (Entry<Object, Chain> subEntry : subMap.entrySet()) {
					@SuppressWarnings("unchecked")
					E object = (E) subEntry.getValue().getObject();
					if (comparable.compareTo((E) object) == 0) {
						if (result == null) {
							result = new LinkedList<>();
						}
						result.add(object);
					}
				}
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> getMasters(Comparable<E> comparable) {
		List<E> result = null;
		for (Entry<Object, Chain> entry : member.entrySet()) {
			E object = (E) entry.getValue().getObject();
			if (comparable.compareTo((E) object) == 0) {
				if (result == null) {
					result = new LinkedList<>();
				}
				result.add(object);
			}
		}
		return result;
	}
		
	public void put(Object master, Object detail, Object value) {
		Chain chainMap = member.get(master);
		if (chainMap == null) {
			chainMap = new Chain(null);
			member.put(master, chainMap);
		}
		Map<Object, Chain> map = chainMap.getMap();
		if (map == null) {
			map = new LinkedHashMap<Object, Chain>();
			chainMap.setMap(map);
		}
		map.put(detail, new Chain(value));				
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Entry<Object, Chain> entry : member.entrySet()) {
			result += StringUtils.isEmpty(result) ? "[" : ",";
			result += "{key:" + entry.getKey() + ",value:" + entry.getValue() + "}";		
		}
		result += !StringUtils.isEmpty(result) ? "]" : "";
		return result;
	}

}