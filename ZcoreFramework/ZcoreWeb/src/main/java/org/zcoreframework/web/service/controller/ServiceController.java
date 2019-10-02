/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.service.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zcoreframework.base.annotation.ServiceAction;
import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.component.AjaxClientable;
import org.zcoreframework.base.component.PropertyBindValue.FireType;
import org.zcoreframework.base.component.ResponseResult;
import org.zcoreframework.base.core.ActionMethod;
import org.zcoreframework.base.core.Executor;
import org.zcoreframework.base.http.HttpTemplate;
import org.zcoreframework.base.util.JsonUtils;
import org.zcoreframework.base.util.ReflectionUtils;
import org.zcoreframework.web.util.RequestUtils;
import org.zcoreframework.web.util.ResponseUtils;

@Controller
public class ServiceController extends AbstractServiceController {

	/*
	 * application/json -> json output e.g: validation, exception, data, ...
	 * application/xml -> form e.g: form format, ... text/html -> data e.g:
	 * image, ...
	 */

	@RequestMapping(value = "/service/{name}/{property}", method = RequestMethod.GET)
	public void component(HttpServletRequest request, HttpServletResponse response, @PathVariable String name, @PathVariable String property,
			@RequestParam(required = false) String serviceParam) throws Exception {

		Object result = null;

		// get service and initialize
		AbstractServiceImpl service = getBean(name);
		service.setHttpServlet(request, response);

		// fire before bind value event
		service.fireBindValueEvent(FireType.BEFORE);

		// bind service params
		service.bindRequestValue(JsonUtils.toMap(serviceParam), false);

		// get component
		final AjaxClientable component = ((AjaxClientable) ReflectionTestUtils.getField(service.getTarget(), property));

		// bind component's client model
		AbstractComponentImpl clientModel = (AbstractComponentImpl) component.getClientModel();
		if (clientModel != null) {
			clientModel.fireBindValueEvent(FireType.BEFORE);
			new ServletRequestDataBinder(clientModel).bind(request);
			clientModel.fireBindValueEvent(FireType.AFTER);
		}

		// fire after bind value event
		service.fireBindValueEvent(FireType.AFTER);

		// get result
		result = invoke(request, new Executor() {

			@Override
			public Object execute() throws Exception {
				return component.partial();
			}

		});

		// flush and get the result for log
		ResponseUtils.success(request, response, (ResponseResult) result);

	}

	@RequestMapping(value = "/service/{name}/{action}", method = RequestMethod.POST)
	public void action(HttpServletRequest request, HttpServletResponse response, @PathVariable String name, @PathVariable String action,
			@RequestParam(required = false) String serviceActionParam) throws Exception {

		Object result = null;

		// get service and initialize
		AbstractServiceImpl service = getBean(name);
		service.setHttpServlet(request, response);

		// fire before bind value event
		service.fireBindValueEvent(FireType.BEFORE);

		// get request parameter
		Map<String, Object> map = RequestUtils.prepareParameter(request);

		// remove serviceActionParam
		Object paramObject = map.remove("serviceActionParam");

		// remove serviceActionParam
		Object serviceActionParamType = map.remove("serviceActionParamType");

		// remove ticket (for request that doesn't AJAX e.g. download)
		map.remove(HttpTemplate.DEFAULT_CSRF_PARAMETER_NAME);

		// bind request values
		service.bindRequestValue(map, true);

		// bind component's client model from serviceActionParam
		if (paramObject != null) {
			String paramString = (String) paramObject;
			if (paramString.indexOf("clientModel") != -1) {
				@SuppressWarnings("unchecked")
				Map<String, Object> clientModel = (Map<String, Object>) JsonUtils.toMap(paramString).get("clientModel");
				service.bindClientModel(clientModel);
				serviceActionParam = null;
			}
		}

		// fire after bind value event
		service.fireBindValueEvent(FireType.AFTER);

		// find action method
		ActionMethod actionMethod = ReflectionUtils.getActionMethod(service, action, serviceActionParam,
				serviceActionParamType == null ? false : Boolean.parseBoolean(serviceActionParamType.toString()));

		// find action method annotation
		ServiceAction annotation = AnnotationUtils.findAnnotation(actionMethod.getMethod(), ServiceAction.class);
		Assert.notNull(annotation, "Method can not invoke ...");

		// validate
		if (annotation.validation()) {
			BindingResult bindingResult = service.getBindingResult();
			getValidator().validate(service.getTarget(), bindingResult);
			if (bindingResult.hasErrors()) {
				result = JsonUtils.model(bindingResult, true);
				ResponseUtils.validate(request, response, result);
				return;
			}
		}

		// execute action method
		result = annotation.methodTemplateExecutor() ? invoke(request, actionMethod, annotation.noRollbackFor()) : actionMethod.execute();

		// create new response result if it doesn't exist
		if (!(result instanceof ResponseResult)) {
			result = new ResponseResult(annotation.response(), result);
		}

		// send response
		ResponseUtils.success(request, response, (ResponseResult) result);

	}

}