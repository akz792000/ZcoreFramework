/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.reflect.Field;

import org.zcoreframework.validation.core.ValidateBeanWrapper;
import org.zcoreframework.validation.model.Model;

public interface Handler<M extends Model> {
	
	boolean validate(final ValidateBeanWrapper beanWrapper, final Field field);
	
	M getModel();

}