/**
 * FaxDeliveryOption.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class FaxDeliveryOption  implements java.io.Serializable {
    private java.lang.String faxNumber;

    private java.lang.String faxServer;

    public FaxDeliveryOption() {
    }

    public FaxDeliveryOption(
           java.lang.String faxNumber,
           java.lang.String faxServer) {
           this.faxNumber = faxNumber;
           this.faxServer = faxServer;
    }


    /**
     * Gets the faxNumber value for this FaxDeliveryOption.
     * 
     * @return faxNumber
     */
    public java.lang.String getFaxNumber() {
        return faxNumber;
    }


    /**
     * Sets the faxNumber value for this FaxDeliveryOption.
     * 
     * @param faxNumber
     */
    public void setFaxNumber(java.lang.String faxNumber) {
        this.faxNumber = faxNumber;
    }


    /**
     * Gets the faxServer value for this FaxDeliveryOption.
     * 
     * @return faxServer
     */
    public java.lang.String getFaxServer() {
        return faxServer;
    }


    /**
     * Sets the faxServer value for this FaxDeliveryOption.
     * 
     * @param faxServer
     */
    public void setFaxServer(java.lang.String faxServer) {
        this.faxServer = faxServer;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FaxDeliveryOption)) return false;
        FaxDeliveryOption other = (FaxDeliveryOption) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.faxNumber==null && other.getFaxNumber()==null) || 
             (this.faxNumber!=null &&
              this.faxNumber.equals(other.getFaxNumber()))) &&
            ((this.faxServer==null && other.getFaxServer()==null) || 
             (this.faxServer!=null &&
              this.faxServer.equals(other.getFaxServer())));
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
        if (getFaxNumber() != null) {
            _hashCode += getFaxNumber().hashCode();
        }
        if (getFaxServer() != null) {
            _hashCode += getFaxServer().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FaxDeliveryOption.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "FaxDeliveryOption"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faxNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "faxNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faxServer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "faxServer"));
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
