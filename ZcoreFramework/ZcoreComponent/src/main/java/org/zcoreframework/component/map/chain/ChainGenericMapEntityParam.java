/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map.chain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.zcoreframework.base.beans.factory.annotation.ComponentType;
import org.zcoreframework.component.map.SimpleMapEntityTargetParam;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@ComponentType(annotation=ChainGenericMapEntityParam.class, clazz=ChainGenericMapEntity.class)
public @interface ChainGenericMapEntityParam {
			
	public SimpleMapEntityTargetParam master();
	
	public SimpleMapEntityTargetParam detail();
			
}