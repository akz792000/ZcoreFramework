/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.core;

import org.springframework.validation.Errors;

public class BeanValidatorImpl extends AbstractBeanValidator {
		
	@Override
    public final void validate(final Object obj, final Errors errors) {    	
    	HandlerFieldLoader handlerFieldLoader = getHandler(obj.getClass());
    	if (handlerFieldLoader != null) {
    		ValidateBeanWrapper beanWrapper = new ValidateBeanWrapperImpl(obj);
    		validate(beanWrapper, handlerFieldLoader, errors);
    	}
    }

}
