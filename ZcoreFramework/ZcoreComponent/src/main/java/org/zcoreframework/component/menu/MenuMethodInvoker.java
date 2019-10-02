package org.zcoreframework.component.menu;

import java.util.List;

/**
 * Created by z.azadi on 7/11/2016.
 */
public class MenuMethodInvoker extends AbstractMenuEntityImpl<MenuMethodInvokerDAOImpl ,MenuMethodInvokerModel> {

    public List<MenuItemModel> getArrangeItems() {
        init();
        return getItems();
    }

    @Override
    public void init() {
        if (items == null) {
            MenuMethodInvokerDAOImpl dao=  getDao();
            items = (List<MenuItemModel>) dao.getResultList();
        }
    }
}
