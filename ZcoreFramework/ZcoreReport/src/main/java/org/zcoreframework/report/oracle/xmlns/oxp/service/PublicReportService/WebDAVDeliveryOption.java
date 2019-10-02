/**
 * WebDAVDeliveryOption.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class WebDAVDeliveryOption  implements java.io.Serializable {
    private java.lang.String deliveryAuthType;

    private java.lang.String password;

    private java.lang.String remoteFilePath;

    private java.lang.String server;

    private java.lang.String userName;

    public WebDAVDeliveryOption() {
    }

    public WebDAVDeliveryOption(
           java.lang.String deliveryAuthType,
           java.lang.String password,
           java.lang.String remoteFilePath,
           java.lang.String server,
           java.lang.String userName) {
           this.deliveryAuthType = deliveryAuthType;
           this.password = password;
           this.remoteFilePath = remoteFilePath;
           this.server = server;
           this.userName = userName;
    }


    /**
     * Gets the deliveryAuthType value for this WebDAVDeliveryOption.
     * 
     * @return deliveryAuthType
     */
    public java.lang.String getDeliveryAuthType() {
        return deliveryAuthType;
    }


    /**
     * Sets the deliveryAuthType value for this WebDAVDeliveryOption.
     * 
     * @param deliveryAuthType
     */
    public void setDeliveryAuthType(java.lang.String deliveryAuthType) {
        this.deliveryAuthType = deliveryAuthType;
    }


    /**
     * Gets the password value for this WebDAVDeliveryOption.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this WebDAVDeliveryOption.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the remoteFilePath value for this WebDAVDeliveryOption.
     * 
     * @return remoteFilePath
     */
    public java.lang.String getRemoteFilePath() {
        return remoteFilePath;
    }


    /**
     * Sets the remoteFilePath value for this WebDAVDeliveryOption.
     * 
     * @param remoteFilePath
     */
    public void setRemoteFilePath(java.lang.String remoteFilePath) {
        this.remoteFilePath = remoteFilePath;
    }


    /**
     * Gets the server value for this WebDAVDeliveryOption.
     * 
     * @return server
     */
    public java.lang.String getServer() {
        return server;
    }


    /**
     * Sets the server value for this WebDAVDeliveryOption.
     * 
     * @param server
     */
    public void setServer(java.lang.String server) {
        this.server = server;
    }


    /**
     * Gets the userName value for this WebDAVDeliveryOption.
     * 
     * @return userName
     */
    public java.lang.String getUserName() {
        return userName;
    }


    /**
     * Sets the userName value for this WebDAVDeliveryOption.
     * 
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WebDAVDeliveryOption)) return false;
        WebDAVDeliveryOption other = (WebDAVDeliveryOption) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.deliveryAuthType==null && other.getDeliveryAuthType()==null) || 
             (this.deliveryAuthType!=null &&
              this.deliveryAuthType.equals(other.getDeliveryAuthType()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.remoteFilePath==null && other.getRemoteFilePath()==null) || 
             (this.remoteFilePath!=null &&
              this.remoteFilePath.equals(other.getRemoteFilePath()))) &&
            ((this.server==null && other.getServer()==null) || 
             (this.server!=null &&
              this.server.equals(other.getServer()))) &&
            ((this.userName==null && other.getUserName()==null) || 
             (this.userName!=null &&
              this.userName.equals(other.getUserName())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDeliveryAuthType() != null) {
            _hashCode += getDeliveryAuthType().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getRemoteFilePath() != null) {
            _hashCode += getRemoteFilePath().hashCode();
        }
        if (getServer() != null) {
            _hashCode += getServer().hashCode();
        }
        if (getUserName() != null) {
            _hashCode += getUserName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WebDAVDeliveryOption.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "WebDAVDeliveryOption"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryAuthType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deliveryAuthType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remoteFilePath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "remoteFilePath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("server");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "server"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
