/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.map;

import org.zcoreframework.base.component.AbstractComponentImpl;
import org.zcoreframework.component.map.SimpleMapEntityTarget;

public class BaseMapEntityModel extends AbstractComponentImpl {
			
	private SimpleMapEntityTarget master;
		
	public SimpleMapEntityTarget getMaster() {
		return master;
	}

	public void setMaster(SimpleMapEntityTarget master) {
		this.master = master;
	}
	
}
