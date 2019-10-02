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
import org.zcoreframework.component.grid.GridColumnParam;
import org.zcoreframework.component.lovbox.LovBoxGridQuery;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=LovBoxGridQueryParam.class, clazz=LovBoxGridQuery.class)
public @interface LovBoxGridQueryParam {
	
	String query();
	
	QueryType type();
	
	GridColumnParam[] columns() default {};	
	
}

