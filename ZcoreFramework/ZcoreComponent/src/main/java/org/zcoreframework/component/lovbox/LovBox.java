/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.lovbox;

import java.util.List;

import org.zcoreframework.base.component.Component;

public interface LovBox<T extends Component> {
	
	public T getService();
	
	public List<List<Object>> getVal();
	
	public void setVal(List<List<Object>> val);
	
	public Object getVal(int row, int column);	
	
	public List<Object> getVal(int column);
	
	public void setVal(Object... object);
	
	public void addItem(Object... object);

}
