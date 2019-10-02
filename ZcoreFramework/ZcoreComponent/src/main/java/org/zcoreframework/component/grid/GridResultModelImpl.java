/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.grid;

import java.util.LinkedHashMap;
import java.util.Map;

import org.zcoreframework.base.core.DataResultModelImpl;

public class GridResultModelImpl extends DataResultModelImpl {
	
	private Double totalCount;
		
	public Double getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(Double totalCount) {
		this.totalCount = totalCount;
	}
	
	@Override
	public Object model() {
		Map<String, Object> result = new LinkedHashMap<>();
		result.put("type", "grid");
		result.put("data", getData());
		result.put("totalCount", totalCount);
		return result;
	}
	
}