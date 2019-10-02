/**
 * 
 * @author Ali Karimizandi
 * @since 2009 
 *  
 */

package org.zcoreframework.component.menu;

import java.util.List;

import javax.persistence.Query;

import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;

public class MenuDAOImpl extends AbstractPyramidDAOImpl<MenuEntityModel> implements  MenuDAO<MenuEntityModel>  {

	@Override
	public List<?> getResultList() {
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<MenuItemModel> loadAll() {
		Query query = getEntityManager().createQuery("select new org.zcoreframework.component.menu.MenuItemModel(g.id, g.code, g.name, g.parent, g.seq, g.click, g.spel) from " + getModel().getEntity() + " g order by g.parent, g.seq");
		return (List<MenuItemModel>) query.getResultList();
	}
	
}
