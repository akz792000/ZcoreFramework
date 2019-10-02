/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

public class ListValueQuery extends AbstractListValueImpl<ListValueQueryDAOImpl, ListValueQueryModel> {
	
	@Override
	public void setParameter(String key, String value) {
		getDao().setParameter(key, value);
	}	 
	
}
