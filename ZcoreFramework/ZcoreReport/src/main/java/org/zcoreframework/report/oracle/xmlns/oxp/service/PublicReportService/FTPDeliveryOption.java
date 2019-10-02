/**
 * FTPDeliveryOption.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class FTPDeliveryOption  implements java.io.Serializable {
    private java.lang.String ftpServerName;

    private java.lang.String ftpUserName;

    private java.lang.String ftpUserPassword;

    private java.lang.String remoteFile;

    private boolean sftpOption;

    public FTPDeliveryOption() {
    }

    public FTPDeliveryOption(
           java.lang.String ftpServerName,
           java.lang.String ftpUserName,
           java.lang.String ftpUserPassword,
           java.lang.String remoteFile,
           boolean sftpOption) {
           this.ftpServerName = ftpServerName;
           this.ftpUserName = ftpUserName;
           this.ftpUserPassword = ftpUserPassword;
           this.remoteFile = remoteFile;
           this.sftpOption = sftpOption;
    }


    /**
     * Gets the ftpServerName value for this FTPDeliveryOption.
     * 
     * @return ftpServerName
     */
    public java.lang.String getFtpServerName() {
        return ftpServerName;
    }


    /**
     * Sets the ftpServerName value for this FTPDeliveryOption.
     * 
     * @param ftpServerName
     */
    public void setFtpServerName(java.lang.String ftpServerName) {
        this.ftpServerName = ftpServerName;
    }


    /**
     * Gets the ftpUserName value for this FTPDeliveryOption.
     * 
     * @return ftpUserName
     */
    public java.lang.String getFtpUserName() {
        return ftpUserName;
    }


    /**
     * Sets the ftpUserName value for this FTPDeliveryOption.
     * 
     * @param ftpUserName
     */
    public void setFtpUserName(java.lang.String ftpUserName) {
        this.ftpUserName = ftpUserName;
    }


    /**
     * Gets the ftpUserPassword value for this FTPDeliveryOption.
     * 
     * @return ftpUserPassword
     */
    public java.lang.String getFtpUserPassword() {
        return ftpUserPassword;
    }


    /**
     * Sets the ftpUserPassword value for this FTPDeliveryOption.
     * 
     * @param ftpUserPassword
     */
    public void setFtpUserPassword(java.lang.String ftpUserPassword) {
        this.ftpUserPassword = ftpUserPassword;
    }


    /**
     * Gets the remoteFile value for this FTPDeliveryOption.
     * 
     * @return remoteFile
     */
    public java.lang.String getRemoteFile() {
        return remoteFile;
    }


    /**
     * Sets the remoteFile value for this FTPDeliveryOption.
     * 
     * @param remoteFile
     */
    public void setRemoteFile(java.lang.String remoteFile) {
        this.remoteFile = remoteFile;
    }


    /**
     * Gets the sftpOption value for this FTPDeliveryOption.
     * 
     * @return sftpOption
     */
    public boolean isSftpOption() {
        return sftpOption;
    }


    /**
     * Sets the sftpOption value for this FTPDeliveryOption.
     * 
     * @param sftpOption
     */
    public void setSftpOption(boolean sftpOption) {
        this.sftpOption = sftpOption;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FTPDeliveryOption)) return false;
        FTPDeliveryOption other = (FTPDeliveryOption) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ftpServerName==null && other.getFtpServerName()==null) || 
             (this.ftpServerName!=null &&
              this.ftpServerName.equals(other.getFtpServerName()))) &&
            ((this.ftpUserName==null && other.getFtpUserName()==null) || 
             (this.ftpUserName!=null &&
              this.ftpUserName.equals(other.getFtpUserName()))) &&
            ((this.ftpUserPassword==null && other.getFtpUserPassword()==null) || 
             (this.ftpUserPassword!=null &&
              this.ftpUserPassword.equals(other.getFtpUserPassword()))) &&
            ((this.remoteFile==null && other.getRemoteFile()==null) || 
             (this.remoteFile!=null &&
              this.remoteFile.equals(other.getRemoteFile()))) &&
            this.sftpOption == other.isSftpOption();
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
        if (getFtpServerName() != null) {
            _hashCode += getFtpServerName().hashCode();
        }
        if (getFtpUserName() != null) {
            _hashCode += getFtpUserName().hashCode();
        }
        if (getFtpUserPassword() != null) {
            _hashCode += getFtpUserPassword().hashCode();
        }
        if (getRemoteFile() != null) {
            _hashCode += getRemoteFile().hashCode();
        }
        _hashCode += (isSftpOption() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FTPDeliveryOption.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "FTPDeliveryOption"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ftpServerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ftpServerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ftpUserName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ftpUserName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ftpUserPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ftpUserPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remoteFile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "remoteFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sftpOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "sftpOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
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
