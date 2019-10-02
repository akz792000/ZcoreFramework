/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GridMethodInvokerDAOImpl extends AbstractGridDataDAOImpl<GridMethodInvokerModel> implements GridDAO<GridMethodInvokerModel> {

	@SuppressWarnings("unchecked")
	@Override
	public void prepare() {
		List<Object> result;
		try {
			Object object = getModel().getMethodInvoker().invoke();
			if (object != null) {
                if (object instanceof Vector) {
                    result = (List<Object>) ((Vector<Object>) object).clone();
                } else {
    				result = (List<Object>) ((ArrayList<Object>) object).clone();
                }
			} else {
				result = null;
			}			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			result = null;
		}	
		setData(result);
	}
	
}