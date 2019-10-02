/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.core;

public interface BeanMapper {
	
	enum MappingDirection { BIDIRECTIONAL, DIRECTION, INDIRECTION }
	
	void mapping(Object source, Object target, MappingDirection mappingDirection) throws Exception;

}