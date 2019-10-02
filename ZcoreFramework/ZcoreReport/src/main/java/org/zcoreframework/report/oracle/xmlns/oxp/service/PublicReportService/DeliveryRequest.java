/**
 * DeliveryRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class DeliveryRequest  implements java.io.Serializable {
    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.EMailDeliveryOption emailOption;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.FaxDeliveryOption faxOption;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.FTPDeliveryOption ftpOption;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.LocalDeliveryOption localOption;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PrintDeliveryOption printOption;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.WebDAVDeliveryOption webDAVOption;

    public DeliveryRequest() {
    }

    public DeliveryRequest(
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.EMailDeliveryOption emailOption,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.FaxDeliveryOption faxOption,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.FTPDeliveryOption ftpOption,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.LocalDeliveryOption localOption,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PrintDeliveryOption printOption,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.WebDAVDeliveryOption webDAVOption) {
           this.emailOption = emailOption;
           this.faxOption = faxOption;
           this.ftpOption = ftpOption;
           this.localOption = localOption;
           this.printOption = printOption;
           this.webDAVOption = webDAVOption;
    }


    /**
     * Gets the emailOption value for this DeliveryRequest.
     * 
     * @return emailOption
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.EMailDeliveryOption getEmailOption() {
        return emailOption;
    }


    /**
     * Sets the emailOption value for this DeliveryRequest.
     * 
     * @param emailOption
     */
    public void setEmailOption(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.EMailDeliveryOption emailOption) {
        this.emailOption = emailOption;
    }


    /**
     * Gets the faxOption value for this DeliveryRequest.
     * 
     * @return faxOption
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.FaxDeliveryOption getFaxOption() {
        return faxOption;
    }


    /**
     * Sets the faxOption value for this DeliveryRequest.
     * 
     * @param faxOption
     */
    public void setFaxOption(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.FaxDeliveryOption faxOption) {
        this.faxOption = faxOption;
    }


    /**
     * Gets the ftpOption value for this DeliveryRequest.
     * 
     * @return ftpOption
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.FTPDeliveryOption getFtpOption() {
        return ftpOption;
    }


    /**
     * Sets the ftpOption value for this DeliveryRequest.
     * 
     * @param ftpOption
     */
    public void setFtpOption(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.FTPDeliveryOption ftpOption) {
        this.ftpOption = ftpOption;
    }


    /**
     * Gets the localOption value for this DeliveryRequest.
     * 
     * @return localOption
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.LocalDeliveryOption getLocalOption() {
        return localOption;
    }


    /**
     * Sets the localOption value for this DeliveryRequest.
     * 
     * @param localOption
     */
    public void setLocalOption(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.LocalDeliveryOption localOption) {
        this.localOption = localOption;
    }


    /**
     * Gets the printOption value for this DeliveryRequest.
     * 
     * @return printOption
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PrintDeliveryOption getPrintOption() {
        return printOption;
    }


    /**
     * Sets the printOption value for this DeliveryRequest.
     * 
     * @param printOption
     */
    public void setPrintOption(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PrintDeliveryOption printOption) {
        this.printOption = printOption;
    }


    /**
     * Gets the webDAVOption value for this DeliveryRequest.
     * 
     * @return webDAVOption
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.WebDAVDeliveryOption getWebDAVOption() {
        return webDAVOption;
    }


    /**
     * Sets the webDAVOption value for this DeliveryRequest.
     * 
     * @param webDAVOption
     */
    public void setWebDAVOption(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.WebDAVDeliveryOption webDAVOption) {
        this.webDAVOption = webDAVOption;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DeliveryRequest)) return false;
        DeliveryRequest other = (DeliveryRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.emailOption==null && other.getEmailOption()==null) || 
             (this.emailOption!=null &&
              this.emailOption.equals(other.getEmailOption()))) &&
            ((this.faxOption==null && other.getFaxOption()==null) || 
             (this.faxOption!=null &&
              this.faxOption.equals(other.getFaxOption()))) &&
            ((this.ftpOption==null && other.getFtpOption()==null) || 
             (this.ftpOption!=null &&
              this.ftpOption.equals(other.getFtpOption()))) &&
            ((this.localOption==null && other.getLocalOption()==null) || 
             (this.localOption!=null &&
              this.localOption.equals(other.getLocalOption()))) &&
            ((this.printOption==null && other.getPrintOption()==null) || 
             (this.printOption!=null &&
              this.printOption.equals(other.getPrintOption()))) &&
            ((this.webDAVOption==null && other.getWebDAVOption()==null) || 
             (this.webDAVOption!=null &&
              this.webDAVOption.equals(other.getWebDAVOption())));
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
        if (getEmailOption() != null) {
            _hashCode += getEmailOption().hashCode();
        }
        if (getFaxOption() != null) {
            _hashCode += getFaxOption().hashCode();
        }
        if (getFtpOption() != null) {
            _hashCode += getFtpOption().hashCode();
        }
        if (getLocalOption() != null) {
            _hashCode += getLocalOption().hashCode();
        }
        if (getPrintOption() != null) {
            _hashCode += getPrintOption().hashCode();
        }
        if (getWebDAVOption() != null) {
            _hashCode += getWebDAVOption().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DeliveryRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "DeliveryRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "emailOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "EMailDeliveryOption"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faxOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "faxOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "FaxDeliveryOption"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ftpOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ftpOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "FTPDeliveryOption"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "localOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "LocalDeliveryOption"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("printOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "printOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "PrintDeliveryOption"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("webDAVOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "webDAVOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "WebDAVDeliveryOption"));
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
