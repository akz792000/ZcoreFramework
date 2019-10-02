/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.util.LinkedList;
import java.util.List;

import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.base.type.ColumnType;
import org.zcoreframework.component.listvalue.ListValue;

public class GridColumn extends AbstractComponentImpl {
	
	private String name;
	
	private ColumnType type;
	
	private String mask;
	
	private String filterBean;
	
	private ListValue filter;
	
	private String staticValue;
	
	private List<GridColumn> columns = new LinkedList<>();
			
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ColumnType getType() {
		return type;
	}

	public void setType(ColumnType type) {
		this.type = type;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}
	
	public String getFilterBean() {
		return filterBean;
	}
	
	public void setFilterBean(String filterBean) {
		this.filterBean = filterBean;
	}
	
	public ListValue getFilter() {
		return filter;
	}
	
	public void setFilter(ListValue filter) {
		this.filter = filter;
	}
	
	public String getStaticValue() {
		return staticValue;
	}
	
	public void setStaticValue(String staticValue) {
		this.staticValue = staticValue;
	}
	
	public List<GridColumn> getColumns() {
		return columns;
	}
	
	public void setColumns(List<GridColumn> columns) {
		this.columns = columns;
	}
		
}
