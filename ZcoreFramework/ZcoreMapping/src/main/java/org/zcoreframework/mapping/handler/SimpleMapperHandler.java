/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.mapping.handler;

import java.lang.annotation.Annotation;

import org.zcoreframework.mapping.model.SimpleMapperModel;

public class SimpleMapperHandler extends AbstractHandler<SimpleMapperModel> {

	public SimpleMapperHandler(Annotation annotation) throws InstantiationException, IllegalAccessException {
		super(annotation);
	}

}