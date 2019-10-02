/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.util.Iterator;
import java.util.List;

import org.springframework.util.ReflectionUtils;
import org.zcoreframework.base.core.ResultEventCallable;
import org.zcoreframework.base.exception.BaseException;

public class GridResultEventIterator implements GridResultEvent {
	
	private ResultEventCallable func;

	public ResultEventCallable getFunc() {
		return func;
	}
	
	public void setFunc(ResultEventCallable func) {
		this.func = func;
	}
	
	public GridResultEventIterator(ResultEventCallable func) {
		this.func = func;
	}

	@Override
	public void onGetResult(GridResultModelImpl model) throws BaseException {
		@SuppressWarnings("unchecked")
		List<Object> data = (List<Object>) model.getData();
		for (Iterator<Object> item = data.listIterator(); item.hasNext(); ) {
			@SuppressWarnings("unchecked")
			List<Object> objects = (List<Object>) item.next();
			try {
				if (!func.call(objects)) {
					item.remove();
					model.setTotalCount(model.getTotalCount() - 1);
				}
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
	}
}
