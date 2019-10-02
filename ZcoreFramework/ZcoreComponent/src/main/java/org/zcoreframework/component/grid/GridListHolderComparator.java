/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.util.Comparator;

import org.zcoreframework.base.type.ColumnType;

public class GridListHolderComparator implements Comparator<Object> {
	
	private int item;
	
	private boolean asc;
	
	private ColumnType type;
	
	public GridListHolderComparator(int item, boolean asc, ColumnType type) {
		this.item = item;
		this.asc = asc;
		this.type = type;
	}
    public int compare(Object object1, Object object2) {
    	int res = 0;
    	Object[] arr1 = (Object[]) object1;
    	Object[] arr2 = (Object[]) object2;   	
    	switch (type) {
			case NUMERIC:
		        res = (Long.valueOf(arr1[item].toString()) > Long.valueOf(arr2[item].toString())) ? 1 : -1;				
				break;    	
			case STRING:
			case GREGORIAN_DATE:
			case PERSIAN_DATE:
			case BOOLEAN:	
			case TABLE:
				String arr1Str = (arr1[item] == null) ? "" : arr1[item].toString();
				String arr2Str = (arr2[item] == null) ? "" : arr2[item].toString();
		        res = arr1Str.compareTo(arr2Str);				
				break;
		}
        return asc ? res : res * -1;
    }
}