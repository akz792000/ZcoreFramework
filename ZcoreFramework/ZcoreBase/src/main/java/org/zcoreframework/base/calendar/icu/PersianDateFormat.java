/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.calendar.icu;

import java.util.*;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.PersianCalendar;
import com.ibm.icu.util.ULocale;

public class PersianDateFormat extends SimpleDateFormat
{

	private static final long serialVersionUID = -2757557919169121212L;
	
	/**
		Creates a <code>PersianDateFormat</code> with the default pattern and locale.
	*/
	public PersianDateFormat()
	{
        this("yyyy/MM/dd G HH:mm:ss z", ULocale.getDefault());
	}
	/**
		Creates a <code>PersianDateFormat</code> with the specified pattern and the 
		default locale.
		
		@param pattern the pattern to be used by this object.
	*/
	public PersianDateFormat(String pattern)
	{
        this(pattern, ULocale.getDefault());
	}
	/**
		Creates a <code>PersianDateFormat</code> with the specified pattern and locale.
		
		@param pattern the pattern to be used by this object.
		@param loc the provided locale for this object.
	*/
	public PersianDateFormat(String pattern, Locale loc)
	{
        this(pattern, ULocale.forLocale(loc));
	}
	/**
		Creates a <code>PersianDateFormat</code> with the specified pattern and locale.
		
		@param pattern the pattern to be used by this object.
		@param loc the provided locale for this object.
	*/
	public PersianDateFormat(String pattern, ULocale loc)
	{
		super(pattern, loc);
		setDateFormatSymbols(new PersianDateFormatSymbols(loc));
		// Should we check if the locale is for Iran or Afghanistan?
		// Anyway, I don't think this class should be used for any other calendar type.
		setCalendar(new PersianCalendar(loc));
	}
}
