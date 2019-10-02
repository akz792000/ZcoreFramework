/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.entryset;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.component.Clientable;
import org.zcoreframework.base.util.MessageSourceUtils;

public class EntrySetSimpleList extends AbstractComponentImpl implements Clientable {
	
	List<EntrySetSimple> member = new ArrayList<>();
	
	public List<EntrySetSimple> getMember() {
		return member;
	}

	public void setMember(List<EntrySetSimple> member) {
		this.member = member;
	}

	public void addMember(EntrySetSimple member) {
		this.member.add(member);
	}	
	
	public EntrySetSimpleList() {}

	public EntrySetSimpleList(List<EntrySetSimple> member) {
		this.member = member;
	}
	
	public void clearSelected() {
		for (EntrySetSimple item : member) {	
			item.setSelected(false);
		}
	}
		
	public List<EntrySetSimple> getSelected() {
		List<EntrySetSimple> res = null;
		for (EntrySetSimple item : member) 
			if (item.isSelected()) {
				if (res == null) {
					 res = new ArrayList<EntrySetSimple>();
				}
				res.add(item);
			}
		return res;
	}
	
	public <T> void setSelected(T value) {
		for (EntrySetSimple item : member) {
			if (String.valueOf(item.getValue()).equals(String.valueOf(value))) {
				item.setSelected(true);	
			}
		}
	}
	
	public void setSelected(Object[] value) {
		for (EntrySetSimple item : member) {
			for (Object object : value) {
				if (String.valueOf(item.getValue()).equals(String.valueOf(object))) {
					item.setSelected(true);	
				}
			}
		}
	}	
	
	public <T> void setSelected(List<T> list) {
		for (EntrySetSimple item : member) {
			for (T value : list) {
				if (String.valueOf(item.getValue()).equals(String.valueOf(value))) {
					item.setSelected(true);
				}
			}
		}
	}	
	
	@SuppressWarnings("unchecked")
	public <T> void setSelectedItem(T value) {
		if (value != null) {
			if (value instanceof List) {
				setSelected((List<T>) value);
			} else if (value.getClass().isArray()) {
				setSelected((Object[]) value);
			} else {
				setSelected(value);
			}
		}
	}
	
	@Override
	public Map<Object, Object> data() {	
		Map<Object, Object> result = new LinkedHashMap<>();
		for (EntrySetSimple item : member) {
			String label = item.getLabel(); 
			label = MessageSourceUtils.getMessage(label);
			result.put(label, item.getValue());
		}
		return result;
	}
	
	@Override
	public String[] value() {
		String res = "";
		for (EntrySetSimple item : member) 
			if (item.isSelected()) {
				res += res.equals("") ? "" : ",";
				res += item.getValue();
			}
		return res.equals("") ? null : res.split(",");
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		EntrySetSimpleList cloned = (EntrySetSimpleList) super.clone();
		List<EntrySetSimple> clonedItems = new ArrayList<EntrySetSimple>();
		for (EntrySetSimple item : member) {
			clonedItems.add((EntrySetSimple) item.clone());
		}
		cloned.setMember(clonedItems);
		return cloned;
	}

}