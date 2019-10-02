package org.zcoreframework.component.menu;

import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.component.method.MethodInvoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by z.azadi on 7/11/2016.
 */
public class MenuMethodInvokerDAOImpl extends AbstractPyramidDAOImpl<MenuMethodInvokerModel> implements MenuDAO<MenuMethodInvokerModel> {

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuItemModel> getResultList() {
		// get method invoker model and set arguments
		MethodInvoker methodInvoker = getModel().getMethodInvoker();
		Object[] args = methodInvoker.getArgs();
		// invoke
		if (args != null) {
			List<Object> list = new ArrayList<>(Arrays.asList(args));
			methodInvoker.setArgs(list.toArray());
		}
		Object result = methodInvoker.invoke();

		return (List<MenuItemModel>) result;
	}

	@Override
	public List<?> loadAll() {
		return null;
	}

}
