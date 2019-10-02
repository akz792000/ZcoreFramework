/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.tree;

import java.util.List;

import org.zcoreframework.base.component.Component;

public interface Tree extends Component {
	
	public static enum RelType {DEFAULT, WITHOUT_CHILDREN, ROOT}; 
	
	void setParameter(String key, String value);

	List<TreeResultEvent> getEvents();

	void setEvent(List<TreeResultEvent> events);
	
	void addEvent(TreeResultEvent event);

}