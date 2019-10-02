package org.zcoreframework.base.gateway.model.file;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

import static org.zcoreframework.base.gateway.constant.GatewayConstants.KEY_FILE_COLUMNS;
import static org.zcoreframework.base.gateway.constant.GatewayConstants.KEY_FILE_UNKNOWN_HEAD;


/**
 *
 */
public class HeadInfo {

    private String resource;

    private Map<String, Object> metadata;

    public HeadInfo(String resource) {
        this.resource = resource;
        this.metadata = new HashedMap();
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public boolean isUnknownHeader() {
        return Boolean.TRUE.equals(getMetadata().get(KEY_FILE_UNKNOWN_HEAD));
    }

    public void setUnknownHeader(boolean unknownHeader) {
        getMetadata().put(KEY_FILE_UNKNOWN_HEAD, unknownHeader);
    }

    public String[] getColumns() {
        return (String[]) getMetadata().get(KEY_FILE_COLUMNS);
    }

    public void setColumns(String[] columns) {
        getMetadata().put(KEY_FILE_COLUMNS, columns);
    }

}
