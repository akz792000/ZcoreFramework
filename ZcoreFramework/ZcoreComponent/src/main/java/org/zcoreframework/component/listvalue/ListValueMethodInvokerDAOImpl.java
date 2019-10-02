/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.component.entryset.EntrySetSimpleList;
import org.zcoreframework.component.method.MethodInvoker;

public class ListValueMethodInvokerDAOImpl extends AbstractPyramidDAOImpl<ListValueMethodInvokerModel> implements ListValueDAO<ListValueMethodInvokerModel, EntrySetSimpleList> {

	@Override
	public EntrySetSimpleList getResult() {
		MethodInvoker methodInvoker = getModel().getMethodInvoker();
		return (EntrySetSimpleList) methodInvoker.invoke();
	}	
	
	@Override
	public Boolean isNotResultable() {
		return false;
	}
	
}
