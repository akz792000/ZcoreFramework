/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import org.zcoreframework.component.entryset.EntrySetSimpleList;

public class ListValueStatic extends AbstractListValueImpl<ListValueStaticDAOImpl, ListValueStaticModel> {
	
	public ListValueStatic() {}
	
	public ListValueStatic(EntrySetSimpleList item) {
		 setItem(item);
	}

}