/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */
package org.zcoreframework.base.core;

import java.sql.SQLException;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class CustomSQLErrorCodesTranslator {

	private static final String PREFIX = "SQL_ERROR_";

	private final MessageSource messageSource;

	public CustomSQLErrorCodesTranslator(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String translate(SQLException sqlex) {
		String code = PREFIX + String.valueOf(sqlex.getErrorCode());
		return messageSource.getMessage(code, new Object[] {}, null, LocaleContextHolder.getLocale());
	}

	public static String translate (MessageSource messageSource, String code) {
		return messageSource.getMessage(code, new Object[] {}, null, LocaleContextHolder.getLocale());
	}

}
