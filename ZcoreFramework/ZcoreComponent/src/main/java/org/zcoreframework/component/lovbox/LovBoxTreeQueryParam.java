/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.lovbox;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.base.type.QueryType;
import org.zcoreframework.component.lovbox.LovBoxTreeQuery;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=LovBoxTreeQueryParam.class, clazz=LovBoxTreeQuery.class)
public @interface LovBoxTreeQueryParam {

	String query();
	
	QueryType type();
	
}

