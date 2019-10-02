/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.report.util;

import java.util.Properties;

public class ReportUtils {
	
	public static final String REPORT_PREFIX = "zcore.report.";
	
	private static Properties properties;

	public static Properties properties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		ReportUtils.properties = properties;
	}
	
	public static Object getPropertyValue(String key) {
		return properties.get(key);
	}
	
	public static String getUrl() {
		return (String) getPropertyValue(REPORT_PREFIX + "url");
	}
	
	public static String getAbsolutePath() {
		return (String) getPropertyValue(REPORT_PREFIX + "absolutePath");
	}
	
	public static String getUsername() {
		return (String) getPropertyValue(REPORT_PREFIX + "username");
	}
	
	public static String getPassword() {
		return (String) getPropertyValue(REPORT_PREFIX + "password");
	}
	
}
