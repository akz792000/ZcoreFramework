/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.zcoreframework.base.component.AbstractComponentImpl;

public class GridModel extends AbstractComponentImpl {
	
	private List<GridColumn> columns = new LinkedList<>();
	
	private List<GridTable> tables = new LinkedList<>();
		
	private GridClientModel clientModel = new GridClientModel();	
	
	public List<GridColumn> getColumns() {
		return columns;
	}
	
	public void setColumns(List<GridColumn> columns) {
		this.columns = columns;
	}
	
	public List<GridTable> getTables() {
		return tables;
	}
	
	public void setTables(List<GridTable> tables) {
		this.tables = tables;
	}
	
	@Override
	public void processAnnotation() throws Throwable {
		super.processAnnotation();
		/*
		 * JDK 7 & 8 
		 * does not support cyclic annotation 
		 * does not get declared methods in order of declaration
		 */
		for (GridTable table : tables) {			
			getColumn(table.getName()).setColumns(table.getColumns());
		}
	}
	
	public GridClientModel getClientModel() {
		return clientModel;
	}
	
	public void setBaseModel(GridClientModel clientModel) {
		this.clientModel = clientModel;
	}

	public int getColumnNo(String name) {
		int res = -1;	
		for (int i = 0; i < columns.size(); i++) {
			if (columns.get(i).getName().trim().equals(name.trim())) {
				res = i;
				break;
			}
		}
		return res;
	}	
	
	public GridColumn getColumn(String name) {
		for (int i = 0; i < columns.size(); i++) {
			if (columns.get(i).getName().trim().equals(name.trim())) {
				return columns.get(i);
			}
		}
		return null;
	}

    public List getDataObject(Object object) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            String name = columns.get(i).getName().trim();
            result.add(beanWrapper.getPropertyValue(name));
        }
        return result;
    }

    public List getDataList(List<Object> list) {
        List<Object> result = new ArrayList<>();
	    for (Object object: list) {
            result.add(getDataObject(object));
        }
        return result;
    }
	
}
