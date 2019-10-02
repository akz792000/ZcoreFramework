/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import org.zcoreframework.base.component.Resultable;
import org.zcoreframework.base.model.PyramidModel;

public interface ListValueDAO<M, E> extends PyramidModel<M>, Resultable<E> {
	
	public Boolean isNotResultable();

}
