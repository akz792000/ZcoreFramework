/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.tree;

import java.util.List;

import org.zcoreframework.base.model.PyramidModel;

public interface TreeDAO<M> extends PyramidModel<M> {
	
	public List<? extends Object> getResultList();

}
