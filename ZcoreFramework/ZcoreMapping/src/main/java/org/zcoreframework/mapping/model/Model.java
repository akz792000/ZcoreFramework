/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.model;

import java.lang.annotation.Annotation;

import org.zcoreframework.mapping.core.BeanMapper.MappingDirection;

public interface Model {
	
	MappingDirection getMappingDirection();
	
	void setProperties(Annotation annotation);
		
}