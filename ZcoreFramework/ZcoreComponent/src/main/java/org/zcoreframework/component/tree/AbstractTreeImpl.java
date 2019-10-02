/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.component.tree;

import org.springframework.util.StringUtils;
import org.zcoreframework.base.component.AbstractPyramidComponentImpl;
import org.zcoreframework.base.component.AjaxClientable;
import org.zcoreframework.base.component.Component;
import org.zcoreframework.base.component.ResponseResult;
import org.zcoreframework.base.dao.AbstractPyramidDAOImpl;
import org.zcoreframework.base.exception.BaseException;
import org.zcoreframework.component.tree.TreeClientModel.OperationType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractTreeImpl<D extends AbstractPyramidDAOImpl<M>, M> extends AbstractPyramidComponentImpl<D, M> implements Tree, AjaxClientable {

    private List<TreeResultEvent> events = new ArrayList<>();

    @Override
    public List<TreeResultEvent> getEvents() {
        return events;
    }

    @Override
    public void setEvent(List<TreeResultEvent> events) {
        this.events = events;
    }

    @Override
    public void addEvent(TreeResultEvent event) {
        this.events.add(event);
    }

    protected void processEventHanlder(TreeResultModelImpl model) throws BaseException {
        if (model != null) {
            for (TreeResultEvent event : events) {
                event.onGetResult(model);
            }
        }
    }

    protected List<Object> getLeaves(TreeDAO<? extends TreeModel> dao) {
        List<Object> result = new ArrayList<>();
        for (Object node : dao.getResultList()) {
            TreeNodeModel nodeModel = (TreeNodeModel) node;
            // attr & state

            Map<String, Object> map = new HashMap<String, Object>();
            if (!StringUtils.isEmpty(((TreeClientModel) getClientModel()).getSearch())) {
                String searchValue = ((TreeClientModel) getClientModel()).getSearch();
                getFilter(nodeModel.getName(), searchValue, map);
            }
            Map<String, Object> attr = new HashMap<>();

            // data
            map.put("data", nodeModel.getName());

            // id
            attr.put("id", nodeModel.getId());
            // rel
            RelType relType = nodeModel.getRelType();
            if (nodeModel.getChild() == 0) {
                attr.put("rel", relType == null ? "without_children" : relType.toString().toLowerCase());
            }
            else {
                attr.put("rel", relType == null ? "default" : relType.toString().toLowerCase());
                if (map.get("search") != null && map.get("search").equals(true)) {
                    map.put("state", "open");
                } else
                    map.put("state", "closed");
                if (((TreeClientModel) getClientModel()).getOperation().equals(OperationType.ALL_CHILDREN)) {
                    dao.getModel().getClientModel().setId(nodeModel.getId());
                    map.put("children", getLeaves(dao));
                }
            }
            // attr
            map.put("attr", attr);
            result.add(map);
        }

        return result;
    }

    private void getFilter(String nodeTitle, String searchValue, Map<String, Object> map) {
        if (searchValue.length() > 0) {
            if (searchValue.length() <= nodeTitle.length()) {
                if (nodeTitle.contains(searchValue))
                    map.put("search", true);
                else
                    map.put("search", false);
            }

        }
    }

    @Override
    public Component getClientModel() {
        return ((TreeModel) getModel()).getClientModel();
    }

    @SuppressWarnings("unchecked")
    public TreeResultModelImpl getResultModel() {
        TreeResultModelImpl resultModel = new TreeResultModelImpl();
        resultModel.setData(getLeaves((TreeDAO<? extends TreeModel>) getDao()));
        return resultModel;
    }

    @Override
    public ResponseResult partial() throws BaseException {
        // get result model
        TreeResultModelImpl resultModel = getResultModel();

        // process event handler
        processEventHanlder(resultModel);

        // return response result
        return new ResponseResult(resultModel.model());
    }

    @Override
    public Object data() {
        return null;
    }

    @Override
    public Object value() {
        return null;
    }

}