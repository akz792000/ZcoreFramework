/**
 * ReportRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class ReportRequest  implements java.io.Serializable {
    private java.lang.String attributeFormat;

    private java.lang.String attributeLocale;

    private java.lang.String attributeTemplate;

    private boolean flattenXML;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] parameterNameValues;

    private java.lang.String reportAbsolutePath;

    private byte[] reportData;

    private java.lang.String reportDataFileName;

    private int sizeOfDataChunkDownload;

    public ReportRequest() {
    }

    public ReportRequest(
           java.lang.String attributeFormat,
           java.lang.String attributeLocale,
           java.lang.String attributeTemplate,
           boolean flattenXML,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] parameterNameValues,
           java.lang.String reportAbsolutePath,
           byte[] reportData,
           java.lang.String reportDataFileName,
           int sizeOfDataChunkDownload) {
           this.attributeFormat = attributeFormat;
           this.attributeLocale = attributeLocale;
           this.attributeTemplate = attributeTemplate;
           this.flattenXML = flattenXML;
           this.parameterNameValues = parameterNameValues;
           this.reportAbsolutePath = reportAbsolutePath;
           this.reportData = reportData;
           this.reportDataFileName = reportDataFileName;
           this.sizeOfDataChunkDownload = sizeOfDataChunkDownload;
    }


    /**
     * Gets the attributeFormat value for this ReportRequest.
     * 
     * @return attributeFormat
     */
    public java.lang.String getAttributeFormat() {
        return attributeFormat;
    }


    /**
     * Sets the attributeFormat value for this ReportRequest.
     * 
     * @param attributeFormat
     */
    public void setAttributeFormat(java.lang.String attributeFormat) {
        this.attributeFormat = attributeFormat;
    }


    /**
     * Gets the attributeLocale value for this ReportRequest.
     * 
     * @return attributeLocale
     */
    public java.lang.String getAttributeLocale() {
        return attributeLocale;
    }


    /**
     * Sets the attributeLocale value for this ReportRequest.
     * 
     * @param attributeLocale
     */
    public void setAttributeLocale(java.lang.String attributeLocale) {
        this.attributeLocale = attributeLocale;
    }


    /**
     * Gets the attributeTemplate value for this ReportRequest.
     * 
     * @return attributeTemplate
     */
    public java.lang.String getAttributeTemplate() {
        return attributeTemplate;
    }


    /**
     * Sets the attributeTemplate value for this ReportRequest.
     * 
     * @param attributeTemplate
     */
    public void setAttributeTemplate(java.lang.String attributeTemplate) {
        this.attributeTemplate = attributeTemplate;
    }


    /**
     * Gets the flattenXML value for this ReportRequest.
     * 
     * @return flattenXML
     */
    public boolean isFlattenXML() {
        return flattenXML;
    }


    /**
     * Sets the flattenXML value for this ReportRequest.
     * 
     * @param flattenXML
     */
    public void setFlattenXML(boolean flattenXML) {
        this.flattenXML = flattenXML;
    }


    /**
     * Gets the parameterNameValues value for this ReportRequest.
     * 
     * @return parameterNameValues
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] getParameterNameValues() {
        return parameterNameValues;
    }


    /**
     * Sets the parameterNameValues value for this ReportRequest.
     * 
     * @param parameterNameValues
     */
    public void setParameterNameValues(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] parameterNameValues) {
        this.parameterNameValues = parameterNameValues;
    }


    /**
     * Gets the reportAbsolutePath value for this ReportRequest.
     * 
     * @return reportAbsolutePath
     */
    public java.lang.String getReportAbsolutePath() {
        return reportAbsolutePath;
    }


    /**
     * Sets the reportAbsolutePath value for this ReportRequest.
     * 
     * @param reportAbsolutePath
     */
    public void setReportAbsolutePath(java.lang.String reportAbsolutePath) {
        this.reportAbsolutePath = reportAbsolutePath;
    }


    /**
     * Gets the reportData value for this ReportRequest.
     * 
     * @return reportData
     */
    public byte[] getReportData() {
        return reportData;
    }


    /**
     * Sets the reportData value for this ReportRequest.
     * 
     * @param reportData
     */
    public void setReportData(byte[] reportData) {
        this.reportData = reportData;
    }


    /**
     * Gets the reportDataFileName value for this ReportRequest.
     * 
     * @return reportDataFileName
     */
    public java.lang.String getReportDataFileName() {
        return reportDataFileName;
    }


    /**
     * Sets the reportDataFileName value for this ReportRequest.
     * 
     * @param reportDataFileName
     */
    public void setReportDataFileName(java.lang.String reportDataFileName) {
        this.reportDataFileName = reportDataFileName;
    }


    /**
     * Gets the sizeOfDataChunkDownload value for this ReportRequest.
     * 
     * @return sizeOfDataChunkDownload
     */
    public int getSizeOfDataChunkDownload() {
        return sizeOfDataChunkDownload;
    }


    /**
     * Sets the sizeOfDataChunkDownload value for this ReportRequest.
     * 
     * @param sizeOfDataChunkDownload
     */
    public void setSizeOfDataChunkDownload(int sizeOfDataChunkDownload) {
        this.sizeOfDataChunkDownload = sizeOfDataChunkDownload;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportRequest)) return false;
        ReportRequest other = (ReportRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.attributeFormat==null && other.getAttributeFormat()==null) || 
             (this.attributeFormat!=null &&
              this.attributeFormat.equals(other.getAttributeFormat()))) &&
            ((this.attributeLocale==null && other.getAttributeLocale()==null) || 
             (this.attributeLocale!=null &&
              this.attributeLocale.equals(other.getAttributeLocale()))) &&
            ((this.attributeTemplate==null && other.getAttributeTemplate()==null) || 
             (this.attributeTemplate!=null &&
              this.attributeTemplate.equals(other.getAttributeTemplate()))) &&
            this.flattenXML == other.isFlattenXML() &&
            ((this.parameterNameValues==null && other.getParameterNameValues()==null) || 
             (this.parameterNameValues!=null &&
              java.util.Arrays.equals(this.parameterNameValues, other.getParameterNameValues()))) &&
            ((this.reportAbsolutePath==null && other.getReportAbsolutePath()==null) || 
             (this.reportAbsolutePath!=null &&
              this.reportAbsolutePath.equals(other.getReportAbsolutePath()))) &&
            ((this.reportData==null && other.getReportData()==null) || 
             (this.reportData!=null &&
              java.util.Arrays.equals(this.reportData, other.getReportData()))) &&
            ((this.reportDataFileName==null && other.getReportDataFileName()==null) || 
             (this.reportDataFileName!=null &&
              this.reportDataFileName.equals(other.getReportDataFileName()))) &&
            this.sizeOfDataChunkDownload == other.getSizeOfDataChunkDownload();
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
        if (getAttributeFormat() != null) {
            _hashCode += getAttributeFormat().hashCode();
        }
        if (getAttributeLocale() != null) {
            _hashCode += getAttributeLocale().hashCode();
        }
        if (getAttributeTemplate() != null) {
            _hashCode += getAttributeTemplate().hashCode();
        }
        _hashCode += (isFlattenXML() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getParameterNameValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParameterNameValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParameterNameValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReportAbsolutePath() != null) {
            _hashCode += getReportAbsolutePath().hashCode();
        }
        if (getReportData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReportData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReportData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReportDataFileName() != null) {
            _hashCode += getReportDataFileName().hashCode();
        }
        _hashCode += getSizeOfDataChunkDownload();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributeFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "attributeFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributeLocale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "attributeLocale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributeTemplate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "attributeTemplate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flattenXML");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "flattenXML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterNameValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "parameterNameValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ParamNameValue"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportAbsolutePath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportDataFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportDataFileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sizeOfDataChunkDownload");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "sizeOfDataChunkDownload"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
