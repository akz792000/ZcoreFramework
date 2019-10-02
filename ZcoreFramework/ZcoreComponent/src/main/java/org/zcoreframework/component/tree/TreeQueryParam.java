/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.tree;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.base.type.QueryType;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=TreeQueryParam.class, clazz=TreeQuery.class)
public @interface TreeQueryParam {
	
	String query();
	
	QueryType type();
		
}

