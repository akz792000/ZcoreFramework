/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.menu;

import java.util.List;

public class MenuEntity extends AbstractMenuEntityImpl<MenuDAOImpl, MenuEntityModel> {

	public List<MenuItemModel> getArrangeItems() {
		init();
		return getItems();
	}

	protected void arrangeMenuItem(List<MenuItemModel> items) {
		for (MenuItemModel item : items) {
			if (item.getParent() == 0) {
				getModel().getMenuItemModel().getItems().add(new MenuItemModel(item));
			} else {
				MenuItemModel parent = getModel().getMenuItemModel().getParentMenu(item.getParent());
				parent.getItems().add(new MenuItemModel(item));
			}
		}
	}

	@Override
	public void init() {
		if (items == null) {
			items = getDao().loadAll();
			arrangeMenuItem(items);
		}
	}

}
