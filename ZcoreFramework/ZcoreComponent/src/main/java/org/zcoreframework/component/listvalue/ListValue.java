/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.listvalue;

import org.zcoreframework.base.component.Component;
import org.zcoreframework.base.component.Itemable;
import org.zcoreframework.component.entryset.EntrySetSimpleList;

public interface ListValue extends Itemable<EntrySetSimpleList>, Component {
	
	public void setParameter(String key, String value);	

}
