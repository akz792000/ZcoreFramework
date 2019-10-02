/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.calendar.icu;

import java.util.*;

import com.ibm.icu.text.DateFormatSymbols;
import com.ibm.icu.util.ULocale;

public class PersianDateFormatSymbols extends DateFormatSymbols
{

	private static final long serialVersionUID = -8307141821442248232L;
	
	private static final String BUNDLE = "org.zcoreframework.base.calendar.icu.Resources";
	/**
		Creates a <code>PersianDateFormatSymbols</code> for the default locale.
	*/
	public PersianDateFormatSymbols()
	{
		this(Locale.getDefault());
	}
	/**
		Creates a <code>PersianDateFormatSymbols</code> for the specified locale.
		
		@param uLocale the provided locale for this object.
	*/
	public PersianDateFormatSymbols(ULocale uLocale)
	{
		this(uLocale.toLocale());
	}
	/**
		Creates a <code>PersianDateFormatSymbols</code> for the specified locale.
		
		@param locale the provided locale for this object.
	*/
	public PersianDateFormatSymbols(Locale locale)
	{
		super(locale);
		initializePersianData(locale);
	}
	
	protected void initializePersianData(Locale locale)
	{
		ResourceBundle rb = ResourceBundle.getBundle(BUNDLE, locale);
		setEras(new String[]
			{
				rb.getString("persianCalendar.era0"),
				rb.getString("persianCalendar.era1"),
			}
		);
		
		//<ZANDI>
		if (locale.getLanguage() == "fz") {
			setWeekdays(new String[] {
						rb.getString("persianCalendar.dayWeek00"),
						rb.getString("persianCalendar.dayWeek01"),
						rb.getString("persianCalendar.dayWeek02"),
						rb.getString("persianCalendar.dayWeek03"),
						rb.getString("persianCalendar.dayWeek04"),
						rb.getString("persianCalendar.dayWeek05"),
						rb.getString("persianCalendar.dayWeek06"),
						
						
						// error for 2013 August 3
						rb.getString("persianCalendar.dayWeek00"),
						

			});
			
		}
		//</ZANDI>
		setMonths(new String[]
			{
				rb.getString("persianCalendar.month00"),
				rb.getString("persianCalendar.month01"),
				rb.getString("persianCalendar.month02"),
				rb.getString("persianCalendar.month03"),
				rb.getString("persianCalendar.month04"),
				rb.getString("persianCalendar.month05"),
				rb.getString("persianCalendar.month06"),
				rb.getString("persianCalendar.month07"),
				rb.getString("persianCalendar.month08"),
				rb.getString("persianCalendar.month09"),
				rb.getString("persianCalendar.month10"),
				rb.getString("persianCalendar.month11"),
			}
		);
		setShortMonths(new String[]
			{
				rb.getString("persianCalendar.monthShort00"),
				rb.getString("persianCalendar.monthShort01"),
				rb.getString("persianCalendar.monthShort02"),
				rb.getString("persianCalendar.monthShort03"),
				rb.getString("persianCalendar.monthShort04"),
				rb.getString("persianCalendar.monthShort05"),
				rb.getString("persianCalendar.monthShort06"),
				rb.getString("persianCalendar.monthShort07"),
				rb.getString("persianCalendar.monthShort08"),
				rb.getString("persianCalendar.monthShort09"),
				rb.getString("persianCalendar.monthShort10"),
				rb.getString("persianCalendar.monthShort11"),
			}
		);
	}
}
