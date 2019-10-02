package org.zcoreframework.base.gateway.model.registry;

import org.zcoreframework.base.gateway.constant.ProtocolType;

import java.net.URI;
import java.util.Map;

/**
 *
 */
public class ServiceInfo {

    private String key;

    private String host;

    private ProtocolType protocol;

    private Integer port;

    private Boolean isSecured;

    private Map<String, String> metaData;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public ProtocolType getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolType protocol) {
        this.protocol = protocol;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public boolean isSecure() {
        return Boolean.TRUE.equals(isSecured);
    }

    public void setSecure(Boolean secure) {
        isSecured = secure;
    }

    public Map<String, String> getMetaData() {
        return metaData;
    }

    public void setMetaData(Map<String, String> metaData) {
        this.metaData = metaData;
    }

    public String getProperty(String name) {
        return this.metaData == null ? null : this.metaData.get(name);
    }

    public URI getUri() {
        StringBuilder sb = new StringBuilder();
        sb.append(protocol == null ? "http" : protocol.toString().toLowerCase());

        if (isSecure()) {
            sb.append("s");
        }

        sb.append("://").append(host);

        if (port != null) {
            sb.append(":").append(port);
        }

        return URI.create(sb.toString());
    }

    public URI getBaseUri() {
        StringBuilder sb = new StringBuilder();
        sb.append(host);

        if (port != null) {
            sb.append(":").append(port);
        }

        return URI.create(sb.toString());
    }
}
