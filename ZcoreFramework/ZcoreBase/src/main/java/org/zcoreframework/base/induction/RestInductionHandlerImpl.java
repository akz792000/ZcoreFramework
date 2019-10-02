/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.induction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.base.util.JsonUtils;

import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;

public class RestInductionHandlerImpl implements InvocationHandler {

	private String context;

	private String name;

	public RestInductionHandlerImpl(String context, String name) {
		this.context = context;
		this.name = name;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
		// prepare url
		StringBuffer url = new StringBuffer();
		url.append(ServiceUtils.getCompletePath(context, name, method.getName()));

		// get servlet request attribute
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		// create template
		HttpTemplate template = new HttpTemplate(servletRequestAttributes.getRequest(), servletRequestAttributes.getResponse());

		// add parameter
		Map<String, Object> actionParameters = new LinkedHashMap<>();
		int index = 0;
		for (Parameter parameter : method.getParameters()) {
			actionParameters.put(parameter.getName(), params[index++]);
		}
		template.addParameter("serviceActionParam", JsonUtils.encode(actionParameters, DefaultTyping.JAVA_LANG_OBJECT));
		template.addParameter("serviceActionParamType", "true");

		return template.execute(url.toString(), HttpMethod.POST, new ParameterizedTypeReference<Void>() {

			@Override
			public Type getType() {
				return method.getGenericReturnType();
			}

		});
	}

}
