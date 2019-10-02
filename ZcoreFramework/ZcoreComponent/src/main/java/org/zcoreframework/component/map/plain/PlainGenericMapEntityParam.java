/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map.plain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.component.map.SimpleMapEntityTargetParam;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=PlainGenericMapEntityParam.class, clazz=PlainGenericMapEntity.class)
public @interface PlainGenericMapEntityParam {
				
	public SimpleMapEntityTargetParam master();
			
}