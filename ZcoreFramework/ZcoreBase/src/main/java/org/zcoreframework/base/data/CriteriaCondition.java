/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.data;

import java.util.ArrayList;
import java.util.List;

public abstract class CriteriaCondition {

	private List<CriteriaColumn> columns = new ArrayList<>();

	private int firstResult;

	private int MaxResults;

	public List<CriteriaColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<CriteriaColumn> columns) {
		this.columns = columns;
	}

	public void addColumn(CriteriaColumn column) {
		this.columns.add(column);
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResults() {
		return MaxResults;
	}

	public void setMaxResults(int maxResults) {
		MaxResults = maxResults;
	}

	public abstract <T> Object execute(Class<T> clazz, CriteriaExecutor<T> gridCriteriaExecutor);

}
