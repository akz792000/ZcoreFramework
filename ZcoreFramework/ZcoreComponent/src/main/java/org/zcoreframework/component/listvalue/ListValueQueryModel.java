/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.type.QueryType;

public class ListValueQueryModel extends AbstractComponentImpl {
	
	private QueryType type;
	
	private String query;
	
	public QueryType getType() {
		return type;
	}

	public void setType(QueryType type) {
		this.type = type;
	}
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}
