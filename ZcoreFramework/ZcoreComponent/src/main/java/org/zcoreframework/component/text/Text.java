/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.text;

import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.component.Valuable;

public class Text extends AbstractComponentImpl implements Valuable {
	
	private String item;
	
	public String getItem() {
		return item;
	}
	
	public void setItem(String item) {
		this.item = item;
	}
		
	@Override
	public void clean() {
		item = null;
	}
	
	@Override
	public Object parseValue(String text) {
		return text;
	}
	
	@Override
	public void bindValue(Object object) {
		item = (String) object;	
	}
	
	@Override
	public String value() {
		return item;
	}	
			
}
