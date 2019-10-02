/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

public class GridQuery extends AbstractGridImpl<GridQueryDAOImpl, GridQueryModel> {		
	
	@Override
	public void setParameter(String key, String value) {
		getDao().setParameter(key, value);		
	}

}
