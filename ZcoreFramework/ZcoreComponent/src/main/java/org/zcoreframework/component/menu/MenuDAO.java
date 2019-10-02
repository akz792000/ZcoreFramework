package org.zcoreframework.component.menu;

import org.zcoreframework.base.model.PyramidModel;

import java.util.List;

/**
 * Created by z.azadi on 7/11/2016.
 */
public interface MenuDAO<M> extends PyramidModel<M> {

    public List<? extends Object> getResultList();

    public List<? extends Object> loadAll();

}

