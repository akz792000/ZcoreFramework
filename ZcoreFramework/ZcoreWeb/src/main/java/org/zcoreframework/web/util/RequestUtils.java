/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;

import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.util.WebUtils;

public class RequestUtils {

	public static Map<String, Object> prepareParameter(ServletRequest request) {
		Assert.notNull(request, "Request must not be null");
		Map<String, Object> params = new LinkedHashMap<>();

		// multi part request
		MultipartRequest multipartRequest = WebUtils.getNativeRequest(request, MultipartRequest.class);
		if (multipartRequest != null) {
			Map<String, List<MultipartFile>> multipartFiles = multipartRequest.getMultiFileMap();
			for (Map.Entry<String, List<MultipartFile>> entry : multipartFiles.entrySet()) {
				String key = entry.getKey();
				List<MultipartFile> values = entry.getValue();
				if (values.size() == 1) {
					MultipartFile value = values.get(0);
					if (!value.isEmpty()) {
						params.put(key, value);
					}
				} else {
					params.put(key, values);
				}
			}
		}

		// request parameters
		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			if (values == null || values.length == 0) {
				// Do nothing, no values found at all.
			} else if (values.length > 1) {
				params.put(key, values);
			} else {
				params.put(key, values[0]);
			}
		}
		
		return params;
	}

}
