/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.dateconverter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ibm.icu.util.PersianCalendar;
import org.springframework.util.StringUtils;


import org.zcoreframework.base.calendar.icu.PersianDateFormat;
import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.component.Clientable;
import org.zcoreframework.component.dateconverter.DateConverterParam.DateType;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.GregorianCalendar;
import com.ibm.icu.util.ULocale;

public class DateConverter extends AbstractComponentImpl implements Clientable {
	
	private DateType type = DateType.PERSIAN;
	
	private String format = "yyyy/MM/dd";

	private Calendar calendar;
	
	private ULocale loc = ULocale.getDefault();
	
	public DateConverter() {}
	
	public static DateConverter getInstance(DateType type, String format, String value) {
		DateConverter dateConverter = new DateConverter();
		dateConverter.setType(type);
		dateConverter.setFormat(format);
		dateConverter.parse(value);
		return dateConverter;
	}
	
	public static DateConverter getInstance(DateType type, String format) {
		DateConverter dateConverter = new DateConverter();
		dateConverter.setType(type);
		dateConverter.setFormat(format);
		dateConverter.init();
		return dateConverter;
	}	
	
	public static DateConverter getInstance(DateType type) {
		DateConverter dateConverter = new DateConverter();
		dateConverter.setType(type);
		dateConverter.init();
		return dateConverter;
	}	
	
	public DateType getType() {
		return type;
	}

	public void setType(DateType type) {
		this.type = type;		
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}	
	
	public ULocale getLoc() {
		return loc;
	}

	public void setLoc(ULocale loc) {
		this.loc = loc;
	}

	public static Calendar getCalendar(DateType type) {
		switch (type) {
			case PERSIAN:
				return new PersianCalendar();
			default:
				return new GregorianCalendar();
		}
	}
	
	public void convert(DateType type) {
		this.type = type;
		Calendar cal = getCalendar(type);
		cal.setTime(calendar.getTime());
		calendar = cal;
	}

	public String getFormatDate() {
		switch (type) {
		case PERSIAN:
			return new PersianDateFormat(format, loc).format(calendar.getTime());
		default:
			return new SimpleDateFormat(format, loc).format(calendar.getTime());
		}		
	}
	
	public Date getTime() {
		return (calendar == null) ? null : calendar.getTime();
	}	
	
	public void setTime(Date date) {
		init();
		if (date != null) 
			calendar.setTime(date);
		else
			calendar = null;	
	}
	
	public void parse(String value) {
		if (StringUtils.isEmpty(value))
			calendar = null;
		else {
			try {
				init();		
				SimpleDateFormat dateFormat = null;
				switch (type) {
				case PERSIAN:
					dateFormat = new PersianDateFormat(format, loc);
					break;
				default:
					dateFormat = new SimpleDateFormat(format, loc);
					break;
				}
				calendar.setTime(dateFormat.parse(value));
				if (!dateFormat.format(calendar.getTime()).equals(value))
					calendar = null;				
			} catch (ParseException ex) {
				calendar = null;
			}
		}
	}
	
	@Override
	public void init() {
		if (calendar == null) {
			calendar = getCalendar(type);
		}
	}
	
	@Override
	public void clean() {
		calendar = null;
	}
	
	@Override
	public Object parseValue(String text) {
		return text;
	}
	
	@Override
	public void bindValue(Object object) {
		parse((String) object);	
	}
		
	@Override
	public List<String> data() {
		List<String> result = new ArrayList<String>();
		result.add(getFormat().toLowerCase());
		result.add(getType().toString());
		return result;
	}
	
	@Override
	public String value() {
		return getCalendar() != null ? getFormatDate() : null;
	}


}
