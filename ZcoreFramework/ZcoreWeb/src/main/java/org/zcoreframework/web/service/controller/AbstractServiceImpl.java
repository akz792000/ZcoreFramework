/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.web.service.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.SimpleTypeConverter;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.DefaultMessageCodesResolver.Format;
import org.springframework.validation.MessageCodesResolver;
import org.zcoreframework.base.annotation.Orderable;
import org.zcoreframework.base.annotation.ServiceAction;
import org.zcoreframework.base.component.ResponseResult.ResponseType;
import org.zcoreframework.base.annotation.ServiceParam;
import org.zcoreframework.base.beans.factory.InitializeAware;
import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.component.AjaxClientable;
import org.zcoreframework.base.component.Clientable;
import org.zcoreframework.base.component.Component;
import org.zcoreframework.base.component.PropertyBindValue;
import org.zcoreframework.base.component.Valuable;
import org.zcoreframework.base.core.FieldOrder;
import org.zcoreframework.base.exception.BaseException;
import org.zcoreframework.base.util.ReflectionUtils;

public abstract class AbstractServiceImpl extends PropertyBindValue implements InitializeAware {

	private HttpServletRequest request;

	private HttpServletResponse response;

	public void setHttpServlet(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	@PostConstruct
	public void init() throws BaseException {
		// init
	}

	@SuppressWarnings("rawtypes")
	protected Object getTarget() throws BaseException {
		return this instanceof AbstractBusinessServiceImpl ? ((AbstractBusinessServiceImpl) this).getBusiness().getModel() : this;
	}

	protected void bindClientModel(Map<String, Object> model) throws BaseException {
		// set component values
		for (Entry<String, Object> entry : model.entrySet()) {
			AjaxClientable component = ((AjaxClientable) ReflectionTestUtils.getField(getTarget(), entry.getKey()));
			AbstractComponentImpl clientModel = (AbstractComponentImpl) component.getClientModel();
			if (clientModel != null) {
				clientModel.fireBindValueEvent(FireType.BEFORE);
				BeanWrapper beanWrapper = new BeanWrapperImpl(clientModel);
				beanWrapper.setPropertyValues((Map<?, ?>) entry.getValue());
				clientModel.fireBindValueEvent(FireType.AFTER);
			}
		}
	}

	/*
	 * access specifier is public for 2 reason I. could be called by any classes
	 * that exists in org.zcoreframework.service.controller II. couldn't be
	 * called by classes that extends this class
	 * 
	 * (AopUtils.isCglibProxy(this)) bindValueEvent.onBindValue(((Advised)
	 * this).getTargetSource().getTarget());
	 */

	@ServiceAction(response = ResponseType.JSON, validation = false)
	public Map<String, Object> param() throws BaseException {
		Map<String, Object> result = new LinkedHashMap<>();
		Object target = getTarget();
		Class<?> clazz = target.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			ServiceParam annotation = field.getAnnotation(ServiceParam.class);
			if (annotation != null && !annotation.type().equals(ServiceParam.Type.POST)) {
				Object value;
				try {
					value = field.get(target);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					ReflectionUtils.handleReflectionException(e);
					value = null;
				}
				Map<String, Object> model = new LinkedHashMap<>();
				// Clientable
				if (value instanceof Clientable) {
					Clientable clientable = (Clientable) value;
					if (annotation.data()) {
						model.put("data", clientable.data());
					}
					if (annotation.value()) {
						model.put("value", clientable.value());
					}
					// Valuable
				} else if (value instanceof Valuable) {
					Valuable valuable = (Valuable) value;
					if (annotation.value()) {
						model.put("value", valuable.value());
					}
					// others
				} else {
					if (annotation.value()) {
						/*
						 * maximum number in javascript is 9007199254740991, and
						 * for larger numbers occurs type conversion error
						 */
						if (value instanceof Long) {
							model.put("value", String.valueOf(value));
						} else {
							model.put("value", value);
						}
					}
				}
				String key = annotation.key();
				result.put(key.isEmpty() ? field.getName() : key, model);
			}
		}
		return result.isEmpty() ? null : result;
	}

	public BindingResult getBindingResult() {
		BindException exception = new BindException(this, getClass().getSimpleName());
		BindingResult result = exception.getBindingResult();
		MessageCodesResolver messageCodesResolver = ((AbstractBindingResult) result).getMessageCodesResolver();
		((DefaultMessageCodesResolver) messageCodesResolver).setMessageCodeFormatter(Format.POSTFIX_ERROR_CODE);
		return result;
	}

	public void bindRequestValue(Map<String, Object> map, boolean parse) throws BaseException {
		if (map != null) {
			Object target = getTarget();
			Class<?> clazz = target.getClass();
			List<FieldOrder> fieldOrders = new ArrayList<>();

			// prepare params
			for (Entry<String, Object> entry : map.entrySet()) {
				try {
					Field field = clazz.getField(entry.getKey());
					ServiceParam annotation = field.getAnnotation(ServiceParam.class);
					if (annotation != null && !annotation.type().equals(ServiceParam.Type.GET)) {
						fieldOrders.add(new FieldOrder(field, entry.getValue(), annotation.order()));
					}
				} catch (NoSuchFieldException | SecurityException e) {
					ReflectionUtils.handleReflectionException(e);
				}
			}

			// set params value
			if (fieldOrders.size() > 0) {

				// sort
				if (clazz.getAnnotation(Orderable.class) != null) {
					Collections.sort(fieldOrders, new Comparator<FieldOrder>() {

						@Override
						public int compare(FieldOrder fst, FieldOrder snd) {
							return snd.getOrder() - fst.getOrder();
						}

					});
				}

				// set
				for (FieldOrder fieldOrder : fieldOrders) {
					// get key & val from entry
					Field field = fieldOrder.getField();
					Object val = fieldOrder.getValue();
					try {
						// all the properties must be public due to getField
						Object value = field.get(target);
						if (value instanceof PropertyBindValue) {
							Component component = (Component) value;
							PropertyBindValue propertyBindValue = (PropertyBindValue) value;
							// before
							propertyBindValue.fireBindValueEvent(FireType.BEFORE);
							// bind value
							component.bindValue(parse ? ((Component) value).parseValue((String) val) : val);
							// after
							propertyBindValue.fireBindValueEvent(FireType.AFTER);
						} else {
							SimpleTypeConverter simpleTypeConverter = new SimpleTypeConverter();
							val = simpleTypeConverter.convertIfNecessary(val, field.getType());
							field.set(target, val);
						}
					} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
						ReflectionUtils.handleReflectionException(e);
					}
				}

			}

		}
	}
}