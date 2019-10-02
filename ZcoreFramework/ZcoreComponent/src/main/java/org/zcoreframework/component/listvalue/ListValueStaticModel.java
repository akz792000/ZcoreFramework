/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import java.util.LinkedList;
import java.util.List;

import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.component.entryset.EntrySetSimple;

public class ListValueStaticModel extends AbstractComponentImpl {
	
	private List<EntrySetSimple> items = new LinkedList<>();
	
	public List<EntrySetSimple> getItems() {
		return items;
	}

	public void setItems(List<EntrySetSimple> items) {
		this.items = items;
	}

}
