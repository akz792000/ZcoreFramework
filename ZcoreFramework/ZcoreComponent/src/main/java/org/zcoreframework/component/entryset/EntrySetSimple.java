/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.entryset;

import org.zcoreframework.base.component.AbstractComponentImpl;

/*
 * the order of value and label
 * because of the select component on html
 */

public class EntrySetSimple extends AbstractComponentImpl implements EntrySet {
	
	private String label;		
	
	private Object value;		
	
	private boolean selected = false;
	
	private Object object;	
	
	@Override
	public String getEntryLabel() {
		return label;
	}
	
	@Override
	public Object getEntryValue() {
		return value;
	}	
	
	public String getLabel() {
		return label;
	}	

	public void setLabel(String label) {
		this.label = label;
	}
	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public Object getObject() {
		return object;
	}
	
	public void setObject(Object object) {
		this.object = object;
	}

	public EntrySetSimple() {}
	
	public EntrySetSimple(String label, Object value) {
		super();
		this.label = label;					
		this.value = value;
	}

	public EntrySetSimple(String label, Object value, boolean selected) {
		super();
		this.label = label;				
		this.value = value;		
		this.selected = selected;
	}
	
	public EntrySetSimple(String label, Object value, boolean selected, Object object) {
		super();
		this.label = label;				
		this.value = value;		
		this.selected = selected;
		this.object = object;
	}	
		
}
