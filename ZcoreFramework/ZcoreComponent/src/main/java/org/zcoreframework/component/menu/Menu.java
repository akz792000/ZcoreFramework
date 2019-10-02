/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.menu;

import java.util.List;

import org.zcoreframework.base.component.Component;

public interface Menu extends Component {

	public List<MenuItemModel> getItems();
	
	public List<MenuItemModel> getArrangeItems();

	public void setItems(List<MenuItemModel> items);	
	
}
