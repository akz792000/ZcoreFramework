/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.handler;

import org.zcoreframework.mapping.core.BeanMapper.MappingDirection;
import org.zcoreframework.mapping.core.FieldBeanWrapper;
import org.zcoreframework.mapping.model.Model;

public interface Handler<M extends Model> {
	
	void mapping(FieldBeanWrapper source, FieldBeanWrapper target, MappingDirection mappingDirection) throws Exception;
	
	M getModel();
	
}