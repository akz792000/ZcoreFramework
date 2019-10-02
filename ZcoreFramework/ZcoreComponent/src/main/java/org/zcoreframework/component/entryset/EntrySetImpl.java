/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.entryset;

import org.zcoreframework.base.component.AbstractComponentImpl;

public class EntrySetImpl extends AbstractComponentImpl implements EntrySet {

	private String label;

	private Object value;

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

	public EntrySetImpl() {
	}

	public EntrySetImpl(String label, Object value) {
		super();
		this.label = label;
		this.value = value;
	}

}
