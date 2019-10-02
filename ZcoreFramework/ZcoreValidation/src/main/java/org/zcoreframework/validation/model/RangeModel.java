/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.validation.model;

public class RangeModel<T extends Number> extends BaseModel {
	
	private T min;
	
	private T max;

	public T getMin() {
		return min;
	}

	public void setMin(T min) {
		this.min = min;
	}

	public T getMax() {
		return max;
	}

	public void setMax(T max) {
		this.max = max;
	}
	
	@Override
	public Object[] getArgs() {
		return new Object[] { min, max };
	}	
	
}
