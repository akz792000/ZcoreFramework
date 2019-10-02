/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.util.ArrayList;
import java.util.List;

import org.zcoreframework.base.component.AbstractComponentImpl;

public class GridTable extends AbstractComponentImpl {
	
	private String name;
	
	private List<GridColumn> columns = new ArrayList<GridColumn>();	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<GridColumn> getColumns() {
		return columns;
	}
	
	public void setColumns(List<GridColumn> columns) {
		this.columns = columns;
	}
		
}