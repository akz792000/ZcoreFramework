/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.tree;

public class TreeQuery extends AbstractTreeImpl<TreeQueryDAOImpl, TreeQueryModel> {		
	
	@Override
	public void setParameter(String key, String value) {
		getDao().setParameter(key, value);		
	}

}