/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.tree;

import org.zcoreframework.base.component.AbstractComponentImpl;

public class TreeClientModel extends AbstractComponentImpl {
	
	public enum OperationType { SELF, ALL_CHILDREN, CHILDREN }
	
	private OperationType operation;
		
	private Long id;

	// ToDo AZADI
	private String search;

	public OperationType getOperation() {
		return operation;
	}

	public void setOperation(OperationType operation) {
		this.operation = operation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}