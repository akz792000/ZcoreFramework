/**
 * ReportDataChunk.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class ReportDataChunk  implements java.io.Serializable {
    private byte[] reportDataChunk;

    private java.lang.String reportDataFileID;

    private long reportDataOffset;

    public ReportDataChunk() {
    }

    public ReportDataChunk(
           byte[] reportDataChunk,
           java.lang.String reportDataFileID,
           long reportDataOffset) {
           this.reportDataChunk = reportDataChunk;
           this.reportDataFileID = reportDataFileID;
           this.reportDataOffset = reportDataOffset;
    }


    /**
     * Gets the reportDataChunk value for this ReportDataChunk.
     * 
     * @return reportDataChunk
     */
    public byte[] getReportDataChunk() {
        return reportDataChunk;
    }


    /**
     * Sets the reportDataChunk value for this ReportDataChunk.
     * 
     * @param reportDataChunk
     */
    public void setReportDataChunk(byte[] reportDataChunk) {
        this.reportDataChunk = reportDataChunk;
    }


    /**
     * Gets the reportDataFileID value for this ReportDataChunk.
     * 
     * @return reportDataFileID
     */
    public java.lang.String getReportDataFileID() {
        return reportDataFileID;
    }


    /**
     * Sets the reportDataFileID value for this ReportDataChunk.
     * 
     * @param reportDataFileID
     */
    public void setReportDataFileID(java.lang.String reportDataFileID) {
        this.reportDataFileID = reportDataFileID;
    }


    /**
     * Gets the reportDataOffset value for this ReportDataChunk.
     * 
     * @return reportDataOffset
     */
    public long getReportDataOffset() {
        return reportDataOffset;
    }


    /**
     * Sets the reportDataOffset value for this ReportDataChunk.
     * 
     * @param reportDataOffset
     */
    public void setReportDataOffset(long reportDataOffset) {
        this.reportDataOffset = reportDataOffset;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportDataChunk)) return false;
        ReportDataChunk other = (ReportDataChunk) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.reportDataChunk==null && other.getReportDataChunk()==null) || 
             (this.reportDataChunk!=null &&
              java.util.Arrays.equals(this.reportDataChunk, other.getReportDataChunk()))) &&
            ((this.reportDataFileID==null && other.getReportDataFileID()==null) || 
             (this.reportDataFileID!=null &&
              this.reportDataFileID.equals(other.getReportDataFileID()))) &&
            this.reportDataOffset == other.getReportDataOffset();
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
        if (getReportDataChunk() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReportDataChunk());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReportDataChunk(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReportDataFileID() != null) {
            _hashCode += getReportDataFileID().hashCode();
        }
        _hashCode += new Long(getReportDataOffset()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportDataChunk.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportDataChunk"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportDataChunk");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportDataChunk"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportDataFileID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportDataFileID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportDataOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportDataOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
