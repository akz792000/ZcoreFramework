/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	public static final String DATE_FORMAT = "yyyy/MM/dd";
	
	public static Date parse(String date, String dateFormat) throws ParseException {	
		if ((date != null) && (!date.trim().isEmpty())) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
			return simpleDateFormat.parse(date);
		} else {
			return null;
		}
	}
	
	public static Date parseDefault(String date) throws ParseException {	
		return parse(date, DATE_FORMAT);
	}	
	
	public static String format(Date date, String dateFormat) {
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
			return simpleDateFormat.format(date);
		} else {
			return null;
		}
	}
	
	public static String formatDefault(Date date) {
		return format(date, DATE_FORMAT);
	}	

}
