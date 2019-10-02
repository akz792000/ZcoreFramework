/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map.chain;

import org.zcoreframework.component.map.BaseMapEntityModel;
import org.zcoreframework.component.map.SimpleMapEntityTarget;

public class ChainGenericMapEntityModel extends BaseMapEntityModel {
			
	private SimpleMapEntityTarget detail;

	public SimpleMapEntityTarget getDetail() {
		return detail;
	}

	public void setDetail(SimpleMapEntityTarget detail) {
		this.detail = detail;
	}	
	
}
