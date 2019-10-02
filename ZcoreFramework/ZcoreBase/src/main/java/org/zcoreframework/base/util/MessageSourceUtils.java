/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.util;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;

public class MessageSourceUtils implements MessageSourceAware {
	
	private static MessageSource messageSource;	

	@Override
	public void setMessageSource(MessageSource messageSource) {
		MessageSourceUtils.messageSource = messageSource;		
	}
	
	public static MessageSource getMessageSource() {
		return messageSource;
	}
	
	public static String getMessage(String code) {
		return MessageSourceUtils.getMessageSource().getMessage(code, null, code, LocaleContextHolder.getLocale());
	}
	
	public static String getMessage(String code, String defaultMessage) {
		return MessageSourceUtils.getMessageSource().getMessage(code, null, defaultMessage, LocaleContextHolder.getLocale());
	}		
	
	public static String getMessage(String code, Object[] args) {
		return MessageSourceUtils.getMessageSource().getMessage(code, args, code, LocaleContextHolder.getLocale());
	}
	
	public static String getMessage(String code, Object[] args, String defaultMessage) {
		return MessageSourceUtils.getMessageSource().getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
	}	

}
