/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.handler;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

import org.zcoreframework.validation.model.ValueModelNoArgs;

public class RegExpHandler extends AbstractHandler<ValueModelNoArgs<String>, String> {
	
	private final Pattern pattern;

	public RegExpHandler(Annotation annotation) throws InstantiationException,
			IllegalAccessException {
		super(annotation, true);
		pattern = Pattern.compile(getModel().getValue(), 0);
	}

	@Override
	protected boolean doValidate(String object) {
		return  pattern.matcher(object).matches();
	}

}
