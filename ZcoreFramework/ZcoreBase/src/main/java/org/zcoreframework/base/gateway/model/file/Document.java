package org.zcoreframework.base.gateway.model.file;



import org.zcoreframework.base.gateway.model.BaseModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 */
public class Document<E extends BaseModel> extends BaseModel {

    private HeadInfo headInfo;

    private List<E> entries;

    public Class getEntryType() {
        Type tp = getClass().getGenericSuperclass();
        return tp instanceof ParameterizedType ? (Class) ((ParameterizedType) tp).getActualTypeArguments()[0] : Object.class;
    }

    public HeadInfo getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(HeadInfo headInfo) {
        this.headInfo = headInfo;
    }

    public List<E> getEntries() {
        return entries;
    }

    public void setEntries(List<E> entries) {
        this.entries = entries;
    }

    public String getResource() {
        return headInfo == null ? null : headInfo.getResource();
    }

    public void setResource(String resource) {
        if (this.headInfo == null) {
            this.headInfo = new HeadInfo(resource);
        } else {
            this.headInfo.setResource(resource);
        }
    }
}
