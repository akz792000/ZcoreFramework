/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.component.entryset.EntrySetSimpleList;

public class ListValueStaticDAOImpl extends AbstractPyramidDAOImpl<ListValueStaticModel> implements ListValueDAO<ListValueStaticModel, EntrySetSimpleList> {

	@Override
	public EntrySetSimpleList getResult() {
		return new EntrySetSimpleList(getModel().getItems());
	}	
	
	@Override
	public Boolean isNotResultable() {
		return false;
	}
	
}
