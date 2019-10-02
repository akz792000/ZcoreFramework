/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.core;

import org.springframework.expression.ParseException;

public interface ExpressionParser {
	
	boolean parseExpression(String expressionString) throws ParseException;

}
