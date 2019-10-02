/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.tree;

import org.zcoreframework.base.type.QueryType;

public class TreeQueryModel extends TreeModel {

	private String query;
	
	private QueryType type; 
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public QueryType getType() {
		return type;
	}
	
	public void setType(QueryType type) {
		this.type = type;
	}
	
}
