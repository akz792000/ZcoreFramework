/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItemModel  {
	
	private long id;
	
	private Long code;
	
	private String name;
	
	private long parent;
	
	private long seq;
	
	private String path;
	
	private List<MenuItemModel> items;
	
	private Boolean visible;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Long getCode() {
		return code;
	}
	
	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public List<MenuItemModel> getItems() {
		return items;
	}

	public void setItems(List<MenuItemModel> items) {
		this.items = items;
	}
	
	public Boolean getVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public MenuItemModel() {}
	

	public MenuItemModel(MenuItemModel menuItemModel) {		
		id = menuItemModel.getId();
		code = menuItemModel.getCode();
		name = menuItemModel.getName();
		parent = menuItemModel.getParent();
		seq = menuItemModel.getSeq();
		path = menuItemModel.getPath();
		items = new ArrayList<MenuItemModel>();
	}	
	
	public MenuItemModel(long id, long code, String name, long parent, long seq, String path) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.parent = parent;
		this.seq = seq;
		this.path = path;
	}
			
	public MenuItemModel getParentMenu(long parent) {
		if (getId() == parent) 
			return this;
		for (MenuItemModel item : getItems()) {
			MenuItemModel menuItem = item.getParentMenu(parent);
			if (menuItem != null)
				return menuItem;
		}
		return null;
	}	
		
}
