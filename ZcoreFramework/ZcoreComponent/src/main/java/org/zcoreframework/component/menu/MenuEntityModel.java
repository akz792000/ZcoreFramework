/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.menu;

import java.util.ArrayList;

import org.zcoreframework.base.component.AbstractComponentImpl;

public class MenuEntityModel extends AbstractComponentImpl {

	private String entity;	
	
	private MenuItemModel menuItemModel;
	
	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public MenuItemModel getMenuItemModel() {
		return menuItemModel;
	}

	public void setMenuItemModel(MenuItemModel menuItemModel) {
		this.menuItemModel = menuItemModel;
	}
	
	public MenuEntityModel() {
		menuItemModel = new MenuItemModel();
		menuItemModel.setId(0);		
		menuItemModel.setParent(-1);
		menuItemModel.setItems(new ArrayList<MenuItemModel>());		
	}
		
}