/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.tree;

import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.component.method.MethodInvoker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeMethodInvokerDAOImpl extends AbstractPyramidDAOImpl<TreeMethodInvokerModel> implements TreeDAO<TreeMethodInvokerModel> {

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Object> getResultList() {
		// get method invoker model and set arguments
		MethodInvoker methodInvoker = getModel().getMethodInvoker();
		Object[] args = methodInvoker.getArgs();
		
		// invoke
		if (args == null) {
			//methodInvoker.setArgs(new Object[] { getModel().getClientModel().getId() });
			methodInvoker.setArgs(new Object[]{getModel().getClientModel()});
		} else {
			List<Object> list = new ArrayList<>(Arrays.asList(args));
			//list.add(getModel().getClientModel().getId());
			list.add(getModel().getClientModel());
			methodInvoker.setArgs(list.toArray());
		}
		Object result = methodInvoker.invoke();
		
		// set main args
		methodInvoker.setArgs(args);		
		return (List<? extends Object>) result;
	}

}