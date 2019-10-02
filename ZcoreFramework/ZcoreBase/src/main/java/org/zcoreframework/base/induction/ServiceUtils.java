/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.induction;

import java.util.Properties;

public class ServiceUtils {

	public static final String REPORT_PREFIX = "zcore.service.";

	private static Properties properties;

	public static Properties properties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		ServiceUtils.properties = properties;
	}

	public static Object getPropertyValue(String key) {
		return properties.get(key);
	}

	public static String getProtocol() {
		return (String) getPropertyValue(REPORT_PREFIX + "protocol");
	}

	public static String getServer() {
		return (String) getPropertyValue(REPORT_PREFIX + "server");
	}

	public static String getPort() {
		return (String) getPropertyValue(REPORT_PREFIX + "port");
	}

	public static String getCompletePath(String context, String service, String action) {
		StringBuffer url = new StringBuffer();
		url.append(ServiceUtils.getProtocol()).append("://").append(ServiceUtils.getServer()).append(":").append(ServiceUtils.getPort()).append("/")
				.append(context).append("/service/").append(service).append("/").append(action);
		return url.toString();
	}

}
