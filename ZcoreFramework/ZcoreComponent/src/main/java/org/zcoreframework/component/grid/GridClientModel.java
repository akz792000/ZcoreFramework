/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import org.zcoreframework.base.component.AbstractComponentImpl;

public class GridClientModel extends AbstractComponentImpl {
	
	private int limitPage = 10;

	private int activePage = 1;

	private String orderByItem;

	private String orderByItemSort;

	private String filterColumnsValue;
	
	private String filterColumnsCondition;
	
	public String getFilterColumnsCondition() {
		return filterColumnsCondition;
	}

	public void setFilterColumnsCondition(String filterColumnsCondition) {
		this.filterColumnsCondition = filterColumnsCondition;
	}

	public String getFilterColumnsValue() {
		return filterColumnsValue;
	}

	public void setFilterColumnsValue(String filterColumnsValue) {
		this.filterColumnsValue = filterColumnsValue;
	}

	public String getOrderByItem() {
		return orderByItem;
	}

	public void setOrderByItem(String orderByItem) {
		this.orderByItem = orderByItem;
	}

	public String getOrderByItemSort() {
		return orderByItemSort;
	}

	public void setOrderByItemSort(String orderByItemSort) {
		this.orderByItemSort = orderByItemSort;
	}
	
	public int getLimitPage() {
		return (limitPage < 0) ? 10 : limitPage;
	}

	public void setLimitPage(int limitPage) {
		this.limitPage = limitPage;
	}

	public int getActivePage() {
		return (activePage <= 0) ? 1 : activePage;
	}

	public void setActivePage(int activePage) {
		this.activePage = activePage;
	}
	
}
