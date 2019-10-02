/**
 *
 * @author Ali Karimizandi
 * @since 2009
 *
 */

package org.zcoreframework.web.log.domain;

import org.zcoreframework.base.util.ConstantsUtils;
import org.zcoreframework.security.domain.AuditEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "LG_SOAP_LOG", schema = ConstantsUtils.Schema.ZC)
public class SoapLogEntity extends AuditEntity implements Serializable {

    @Id
    private String id;

    @Column(name="REMOTE_ADDR", length=255, nullable=false)
    private String remoteAddr;

    @Column(name="REMOTE_PORT", length=8, nullable=false)
    private Integer remotePort;

    @Column(name="SERVER_NAME", length=255, nullable=false)
    private String serverName;

    @Column(name="SERVER_PORT", length=8, nullable=false)
    private Integer serverPort;

    @Column(name="PROTOCOL", length=255, nullable=false)
    private String protocol;

    @Column(name="METHOD", length=255, nullable=false)
    private String method;

    @Column(name="URI", length=2000, nullable=false)
    private String uri;

    @Column(name="LOCALE", length=255, nullable=false)
    private String locale;

    @Column(name="USER_AGENT", length=2000, nullable=false)
    private String userAgent;

    @Column(name="TOTAL_TIME_MILLIS", nullable=false)
    private Long totalTimeMillis;

    @Column(name="EFFECTIVE_DATE", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;

    @Column(name="STATUS", length=10, nullable=false)
    private String status;

    @Lob
    @Column(name = "PARAMETER" , nullable = true)
    private String parameter ;

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public String getRemoteAddr() {return remoteAddr;}

    public void setRemoteAddr(String remoteAddr) {this.remoteAddr = remoteAddr;}

    public Integer getRemotePort() {return remotePort;}

    public void setRemotePort(Integer remotePort) {this.remotePort = remotePort;}

    public String getServerName() {return serverName;}

    public void setServerName(String serverName) {this.serverName = serverName;}

    public Integer getServerPort() {return serverPort;}

    public void setServerPort(Integer serverPort) {this.serverPort = serverPort;}

    public String getProtocol() {return protocol;}

    public void setProtocol(String protocol) {this.protocol = protocol;}

    public String getMethod() {return method;}

    public void setMethod(String method) {this.method = method;}

    public String getUri() {return uri;}

    public void setUri(String uri) {this.uri = uri;}

    public String getLocale() {return locale;}

    public void setLocale(String locale) {this.locale = locale;}

    public String getUserAgent() {return userAgent;}

    public void setUserAgent(String userAgent) {this.userAgent = userAgent;}

    public Long getTotalTimeMillis() {return totalTimeMillis;}

    public void setTotalTimeMillis(Long totalTimeMillis) {this.totalTimeMillis = totalTimeMillis;}

    public Date getEffectiveDate() {return effectiveDate;}

    public void setEffectiveDate(Date effectiveDate) {this.effectiveDate = effectiveDate;}

    public String getParameter() {return parameter;}

    public void setParameter(String parameter) {this.parameter = parameter;}

}
