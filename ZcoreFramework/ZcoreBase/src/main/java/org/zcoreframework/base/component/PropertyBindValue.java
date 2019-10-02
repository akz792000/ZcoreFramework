/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.component;

import org.zcoreframework.base.event.BindValueEvent;
import org.zcoreframework.base.exception.BaseException;

public class PropertyBindValue {
	
	public static enum FireType {BEFORE, AFTER};
	
	private BindValueEvent beforeBindValueEvent;
	
	private BindValueEvent afterBindValueEvent;

	public BindValueEvent getBeforeBindValueEvent() {
		return beforeBindValueEvent;
	}

	public void setBeforeBindValueEvent(BindValueEvent beforeBindValueEvent) {
		this.beforeBindValueEvent = beforeBindValueEvent;
	}

	public BindValueEvent getAfterBindValueEvent() {
		return afterBindValueEvent;
	}

	public void setAfterBindValueEvent(BindValueEvent afterBindValueEvent) {
		this.afterBindValueEvent = afterBindValueEvent;
	}
	
	public void fireBindValueEvent(FireType invokeType) throws BaseException {
		BindValueEvent bindValueEvent = invokeType.equals(FireType.BEFORE) ? beforeBindValueEvent : afterBindValueEvent; 
		if (bindValueEvent != null) {
			bindValueEvent.onBindValue(this);
		}
	}
	
}
