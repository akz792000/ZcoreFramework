package org.zcoreframework.base.data;

import java.util.List;

public interface CriteriaExecutor<T> {
	
	Object invoke(T entity);

}
