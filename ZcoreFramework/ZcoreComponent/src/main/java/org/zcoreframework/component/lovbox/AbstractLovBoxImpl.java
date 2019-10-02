/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.lovbox;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.component.AjaxClientable;
import org.zcoreframework.base.component.Clientable;
import org.zcoreframework.base.component.Component;
import org.zcoreframework.base.component.ResponseResult;
import org.zcoreframework.base.exception.BaseException;
import org.zcoreframework.base.util.ApplicationContextUtils;
import org.zcoreframework.base.util.JsonUtils;

public abstract class AbstractLovBoxImpl<T extends Component> extends AbstractComponentImpl implements LovBox<T>, AjaxClientable {
	
	private T service;

	private List<List<Object>> val; 
		
	@Override
	public T getService() {
		return service;
	}

	public void setService(T service) {
		this.service = service;
	}
	
	@Override
	public List<List<Object>> getVal() {
		return val;
	}

	@Override
	public void setVal(List<List<Object>> val) {
		this.val = val;
	}
	
	@Override
	public Object getVal(int row, int column) {
		return val.get(row).get(column);
	}	
	
	@Override
	public List<Object> getVal(int column) {
		List<Object> res = new ArrayList<>();
		for (List<Object> obj : val) {
			res.add(obj.get(column));
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public void appendItem(Object... object) {
		List<Object> list = Arrays.asList(object);
		if (object[0] instanceof List) {
			for (Object obj : list) {
				val.add((List<Object>) obj);	
			}
		} else {
			val.add(list);
		}	
	}
	
	@Override
	public void setVal(Object... object) {
		val = new ArrayList<>();
		appendItem(object);
	}
	
	@Override
	public void addItem(Object... object) {
		if (this.val == null) {
			this.val = new ArrayList<>();
		}
		appendItem(object);
	}	

	@SuppressWarnings("unchecked")
	public AbstractLovBoxImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		AutowireCapableBeanFactory beanFactory = ApplicationContextUtils.getApplicationContext().getAutowireCapableBeanFactory();
		service = (T) beanFactory.createBean((Class<T>) genericSuperclass.getActualTypeArguments()[0]);
	}
	
	@Override
	public void processAnnotation() throws Throwable {
		getService().setAnnotation(getAnnotation());
		getService().processAnnotation();
	}	
	
	@Override
	public Object parseValue(String text) {
		return JsonUtils.toListOfList(text);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void bindValue(Object object) {		
		val = (List<List<Object>>) object;
	}
		
	@Override
	public Object data() throws BaseException {
		return ((Clientable) getService()).data();
	}
		
	@Override
	public Object value() {
		if (val == null) {
			return null;
		}
		return val;
	}
	
	@Override
	public Component getClientModel() {
		return ((AjaxClientable) getService()).getClientModel();
	}
	
	@Override
	public ResponseResult partial() throws BaseException {
		return ((AjaxClientable) getService()).partial();
	}
	
}