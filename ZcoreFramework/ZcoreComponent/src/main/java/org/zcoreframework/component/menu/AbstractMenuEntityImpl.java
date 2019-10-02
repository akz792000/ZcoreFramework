package org.zcoreframework.component.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zcoreframework.base.component.AbstractPyramidComponentImpl;
import org.zcoreframework.base.component.Clientable;
import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.base.util.ArrayList;

/**
 * Created by z.azadi on 7/11/2016.
 */
public class AbstractMenuEntityImpl<D extends AbstractPyramidDAOImpl<M>, M> extends AbstractPyramidComponentImpl<D, M> implements Menu, Clientable {

    List<MenuItemModel> items;

    @Override
    public List<MenuItemModel> getItems() {
        return items;
    }


    @Override
    public void setItems(List<MenuItemModel> items) {
        this.items = items;
    }


    public List<MenuItemModel> getArrangeItems() {
        return null;
    }


    protected void arrangeMenuItem(List<MenuItemModel> items) {
        for (MenuItemModel item : items) {
            if (item.getParent() == 0) {
                ((MenuMethodInvokerModel) getModel()).getMenuItemModel().getItems().add(new MenuItemModel(item));
            } else {
                MenuItemModel parent = ((MenuMethodInvokerModel) getModel()).getMenuItemModel().getParentMenu(item.getParent());
                parent.getItems().add(new MenuItemModel(item));
            }
        }
    }

    @Override
    public void init() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    protected List<Map<String, ? extends Object>> getResultMenu(List<MenuItemModel> menuItemModels) {
        boolean appended = false;
        List<Map<String, ? extends Object>> result = new ArrayList<>();
        for (MenuItemModel item : menuItemModels) {
        	Boolean visible = item.getVisible();
        	if (visible != null && visible) {
	            appended = true;
	            /*
				 *  parse spring expression language
				 */
	            /*if ((item.getSpel() != null) && (!item.getSpel().trim().isEmpty())) {
	                ExpressionParser securityExpressionParser = (ExpressionParser) ApplicationContextUtils.getBean("org.zcoreframework.security.SecurityExpressionParser");
	                appended = securityExpressionParser.parseExpression(item.getSpel());
	            }*/
	            /*
	             *  if the result is true then append menu
				 */
	            
	            if (appended) {
	                Map<String, Object> nodeMap = new HashMap<String, Object>();
	                if ((item.getPath() != null) && (item.getPath().trim() != "")) {
	                	if (item.getCode() != null) {
	                		nodeMap.put("t", item.getCode());
	                	}
	                    nodeMap.put("o", item.getPath());
	                }
					/*
					 * translate
					 * nodeMap.put("c", MessageSourceUtils.getMessageSource().getMessage(item.getName(), null, item.getName(), LocaleContextHolder.getLocale()));
					 */
	                nodeMap.put("c", item.getName());
	                if (item.getItems() != null && !item.getItems().isEmpty())
	                    nodeMap.put("l", getResultMenu(item.getItems()));
	                result.add(nodeMap);
	            }
        	}
        }
        return result;
    }

    @Override
    public Object data() {
        return getResultMenu(this.getArrangeItems());
    }

    @Override
    public Object value() {
        return null;
    }
}
