/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.mapper;

import org.zcoreframework.mapping.core.FieldBeanWrapper;
import org.zcoreframework.mapping.core.FieldBeanWrapperImpl;

public class ModelFieldBeanMapperImpl<T, M> extends FieldBeanWrapperImpl implements ModelFieldBeanMapper<M> {
	
	private final M model;		
	
	@Override
	public M getModel() {
		return model;
	}
	
	public ModelFieldBeanMapperImpl(FieldBeanWrapper mappingBeanWrapper, M model) {
		super(mappingBeanWrapper.getWrappedInstance());
		setName(mappingBeanWrapper.getName());
		this.model = model;
	}
	
	@SuppressWarnings("unchecked")
	public T getValue() throws Exception {
		return (T) super.getPropertyValue();
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getClazz() throws Exception {
		return (Class<T>) super.getPropertyType();
	}
		
}
