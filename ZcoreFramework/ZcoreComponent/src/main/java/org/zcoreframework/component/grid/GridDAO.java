/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.util.List;
import java.util.Set;

import org.zcoreframework.base.model.PyramidModel;
import org.zcoreframework.component.grid.AbstractGridDataDAOImpl.OperationType;

public interface GridDAO<M> extends PyramidModel<M> {

	Double getResultSize();

	List<? extends Object> getResultList();

	Boolean isNotResultable();

	void prepare();

	void setSize(double size);

	void setData(List<Object> data);

	void setOperationTypes(Set<OperationType> operationTypes);

}
