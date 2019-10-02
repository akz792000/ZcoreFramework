/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.base.core;

import java.util.List;

public class DataResultModelImpl implements DataResultModel {
	
	private List<? extends Object> data;
		
	public List<? extends Object> getData() {
		return data;
	}
	
	public void setData(List<? extends Object> data) {
		this.data = data;
	}
	
	@Override
	public Object model() {
		return data;
	}
	
}