/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zcoreframework.base.component.AbstractPyramidComponentImpl;
import org.zcoreframework.base.component.AjaxClientable;
import org.zcoreframework.base.component.Component;
import org.zcoreframework.base.component.ResponseResult;
import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.base.exception.BaseException;
import org.zcoreframework.base.util.JsonUtils;
import org.zcoreframework.component.entryset.EntrySetSimpleList;

public class AbstractListValueImpl<D extends AbstractPyramidDAOImpl<M>, M> extends AbstractPyramidComponentImpl<D, M> implements ListValue, AjaxClientable {
	
	private static final Log log = LogFactory.getLog(AbstractListValueImpl.class);	
	
	private EntrySetSimpleList item;
		
	@Override
	public EntrySetSimpleList getItem() {
		return item;
	}

	@Override
	public void setItem(EntrySetSimpleList item) {
		this.item = item;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		if (item == null) { 
			if (log.isDebugEnabled()) {
				log.debug("list value initialized.");
			}
			ListValueDAO<M, EntrySetSimpleList> dao = (ListValueDAO<M, EntrySetSimpleList>) getDao();
			item = dao.isNotResultable() ? null : dao.getResult();
		}
	}
	
	@Override
	public void clean() {
		if (item != null) {
			item.clearSelected();
		}
	}
	
	@Override
	public void setParameter(String key, String value) {
		// NOP
	}	 
	
	@Override
	public Object parseValue(String text) {
		return JsonUtils.toList(text);
	}
		
	@Override
	public void bindValue(Object object) {
		item.clearSelected();
		for (Object value : (List<?>) object) {
			item.setSelected(value);	
		}			
	}
	
	@Override
	public Map<Object, Object> data() {
		return item == null ? null : item.data();
	}
	
	@Override
	public String[] value() {
		return item == null ? null : item.value();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		AbstractListValueImpl<?, ?> cloned = (AbstractListValueImpl<?, ?>) super.clone();
		cloned.setItem((EntrySetSimpleList) item.clone());
		return cloned;
	}

	@Override
	public Component getClientModel() {
		return null;
	}

	@Override
	public ResponseResult partial() throws BaseException {
		return new ResponseResult(data());
	}

}