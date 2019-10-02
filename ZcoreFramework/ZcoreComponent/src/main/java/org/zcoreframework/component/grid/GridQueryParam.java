/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.base.type.QueryType;
import org.zcoreframework.component.grid.GridQuery;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=GridQueryParam.class, clazz=GridQuery.class)
public @interface GridQueryParam {
	
	String query();
	
	QueryType type();
	
	GridColumnParam[] columns() default {};
		
}

