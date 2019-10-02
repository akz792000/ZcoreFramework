/**
 * TemplateFormatsLabelValues.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class TemplateFormatsLabelValues  implements java.io.Serializable {
    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatLabelValue[] listOfTemplateFormatLabelValue;

    private java.lang.String templateID;

    private java.lang.String templateType;

    private java.lang.String templateURL;

    public TemplateFormatsLabelValues() {
    }

    public TemplateFormatsLabelValues(
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatLabelValue[] listOfTemplateFormatLabelValue,
           java.lang.String templateID,
           java.lang.String templateType,
           java.lang.String templateURL) {
           this.listOfTemplateFormatLabelValue = listOfTemplateFormatLabelValue;
           this.templateID = templateID;
           this.templateType = templateType;
           this.templateURL = templateURL;
    }


    /**
     * Gets the listOfTemplateFormatLabelValue value for this TemplateFormatsLabelValues.
     * 
     * @return listOfTemplateFormatLabelValue
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatLabelValue[] getListOfTemplateFormatLabelValue() {
        return listOfTemplateFormatLabelValue;
    }


    /**
     * Sets the listOfTemplateFormatLabelValue value for this TemplateFormatsLabelValues.
     * 
     * @param listOfTemplateFormatLabelValue
     */
    public void setListOfTemplateFormatLabelValue(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatLabelValue[] listOfTemplateFormatLabelValue) {
        this.listOfTemplateFormatLabelValue = listOfTemplateFormatLabelValue;
    }


    /**
     * Gets the templateID value for this TemplateFormatsLabelValues.
     * 
     * @return templateID
     */
    public java.lang.String getTemplateID() {
        return templateID;
    }


    /**
     * Sets the templateID value for this TemplateFormatsLabelValues.
     * 
     * @param templateID
     */
    public void setTemplateID(java.lang.String templateID) {
        this.templateID = templateID;
    }


    /**
     * Gets the templateType value for this TemplateFormatsLabelValues.
     * 
     * @return templateType
     */
    public java.lang.String getTemplateType() {
        return templateType;
    }


    /**
     * Sets the templateType value for this TemplateFormatsLabelValues.
     * 
     * @param templateType
     */
    public void setTemplateType(java.lang.String templateType) {
        this.templateType = templateType;
    }


    /**
     * Gets the templateURL value for this TemplateFormatsLabelValues.
     * 
     * @return templateURL
     */
    public java.lang.String getTemplateURL() {
        return templateURL;
    }


    /**
     * Sets the templateURL value for this TemplateFormatsLabelValues.
     * 
     * @param templateURL
     */
    public void setTemplateURL(java.lang.String templateURL) {
        this.templateURL = templateURL;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateFormatsLabelValues)) return false;
        TemplateFormatsLabelValues other = (TemplateFormatsLabelValues) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listOfTemplateFormatLabelValue==null && other.getListOfTemplateFormatLabelValue()==null) || 
             (this.listOfTemplateFormatLabelValue!=null &&
              java.util.Arrays.equals(this.listOfTemplateFormatLabelValue, other.getListOfTemplateFormatLabelValue()))) &&
            ((this.templateID==null && other.getTemplateID()==null) || 
             (this.templateID!=null &&
              this.templateID.equals(other.getTemplateID()))) &&
            ((this.templateType==null && other.getTemplateType()==null) || 
             (this.templateType!=null &&
              this.templateType.equals(other.getTemplateType()))) &&
            ((this.templateURL==null && other.getTemplateURL()==null) || 
             (this.templateURL!=null &&
              this.templateURL.equals(other.getTemplateURL())));
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
        if (getListOfTemplateFormatLabelValue() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListOfTemplateFormatLabelValue());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListOfTemplateFormatLabelValue(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTemplateID() != null) {
            _hashCode += getTemplateID().hashCode();
        }
        if (getTemplateType() != null) {
            _hashCode += getTemplateType().hashCode();
        }
        if (getTemplateURL() != null) {
            _hashCode += getTemplateURL().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateFormatsLabelValues.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "TemplateFormatsLabelValues"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listOfTemplateFormatLabelValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "listOfTemplateFormatLabelValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "TemplateFormatLabelValue"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateURL"));
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
