/**
 * ReportResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class ReportResponse  implements java.io.Serializable {
    private byte[] reportBytes;

    private java.lang.String reportContentType;

    private java.lang.String reportFileID;

    private java.lang.String reportLocale;

    public ReportResponse() {
    }

    public ReportResponse(
           byte[] reportBytes,
           java.lang.String reportContentType,
           java.lang.String reportFileID,
           java.lang.String reportLocale) {
           this.reportBytes = reportBytes;
           this.reportContentType = reportContentType;
           this.reportFileID = reportFileID;
           this.reportLocale = reportLocale;
    }


    /**
     * Gets the reportBytes value for this ReportResponse.
     * 
     * @return reportBytes
     */
    public byte[] getReportBytes() {
        return reportBytes;
    }


    /**
     * Sets the reportBytes value for this ReportResponse.
     * 
     * @param reportBytes
     */
    public void setReportBytes(byte[] reportBytes) {
        this.reportBytes = reportBytes;
    }


    /**
     * Gets the reportContentType value for this ReportResponse.
     * 
     * @return reportContentType
     */
    public java.lang.String getReportContentType() {
        return reportContentType;
    }


    /**
     * Sets the reportContentType value for this ReportResponse.
     * 
     * @param reportContentType
     */
    public void setReportContentType(java.lang.String reportContentType) {
        this.reportContentType = reportContentType;
    }


    /**
     * Gets the reportFileID value for this ReportResponse.
     * 
     * @return reportFileID
     */
    public java.lang.String getReportFileID() {
        return reportFileID;
    }


    /**
     * Sets the reportFileID value for this ReportResponse.
     * 
     * @param reportFileID
     */
    public void setReportFileID(java.lang.String reportFileID) {
        this.reportFileID = reportFileID;
    }


    /**
     * Gets the reportLocale value for this ReportResponse.
     * 
     * @return reportLocale
     */
    public java.lang.String getReportLocale() {
        return reportLocale;
    }


    /**
     * Sets the reportLocale value for this ReportResponse.
     * 
     * @param reportLocale
     */
    public void setReportLocale(java.lang.String reportLocale) {
        this.reportLocale = reportLocale;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportResponse)) return false;
        ReportResponse other = (ReportResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.reportBytes==null && other.getReportBytes()==null) || 
             (this.reportBytes!=null &&
              java.util.Arrays.equals(this.reportBytes, other.getReportBytes()))) &&
            ((this.reportContentType==null && other.getReportContentType()==null) || 
             (this.reportContentType!=null &&
              this.reportContentType.equals(other.getReportContentType()))) &&
            ((this.reportFileID==null && other.getReportFileID()==null) || 
             (this.reportFileID!=null &&
              this.reportFileID.equals(other.getReportFileID()))) &&
            ((this.reportLocale==null && other.getReportLocale()==null) || 
             (this.reportLocale!=null &&
              this.reportLocale.equals(other.getReportLocale())));
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
        if (getReportBytes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReportBytes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReportBytes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReportContentType() != null) {
            _hashCode += getReportContentType().hashCode();
        }
        if (getReportFileID() != null) {
            _hashCode += getReportFileID().hashCode();
        }
        if (getReportLocale() != null) {
            _hashCode += getReportLocale().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportBytes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportBytes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportContentType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportContentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportFileID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportFileID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportLocale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportLocale"));
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
