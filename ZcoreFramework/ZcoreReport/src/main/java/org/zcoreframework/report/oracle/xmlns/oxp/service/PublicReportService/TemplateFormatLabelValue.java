/**
 * TemplateFormatLabelValue.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class TemplateFormatLabelValue  implements java.io.Serializable {
    private java.lang.String templateFormatLabel;

    private java.lang.String templateFormatValue;

    public TemplateFormatLabelValue() {
    }

    public TemplateFormatLabelValue(
           java.lang.String templateFormatLabel,
           java.lang.String templateFormatValue) {
           this.templateFormatLabel = templateFormatLabel;
           this.templateFormatValue = templateFormatValue;
    }


    /**
     * Gets the templateFormatLabel value for this TemplateFormatLabelValue.
     * 
     * @return templateFormatLabel
     */
    public java.lang.String getTemplateFormatLabel() {
        return templateFormatLabel;
    }


    /**
     * Sets the templateFormatLabel value for this TemplateFormatLabelValue.
     * 
     * @param templateFormatLabel
     */
    public void setTemplateFormatLabel(java.lang.String templateFormatLabel) {
        this.templateFormatLabel = templateFormatLabel;
    }


    /**
     * Gets the templateFormatValue value for this TemplateFormatLabelValue.
     * 
     * @return templateFormatValue
     */
    public java.lang.String getTemplateFormatValue() {
        return templateFormatValue;
    }


    /**
     * Sets the templateFormatValue value for this TemplateFormatLabelValue.
     * 
     * @param templateFormatValue
     */
    public void setTemplateFormatValue(java.lang.String templateFormatValue) {
        this.templateFormatValue = templateFormatValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TemplateFormatLabelValue)) return false;
        TemplateFormatLabelValue other = (TemplateFormatLabelValue) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.templateFormatLabel==null && other.getTemplateFormatLabel()==null) || 
             (this.templateFormatLabel!=null &&
              this.templateFormatLabel.equals(other.getTemplateFormatLabel()))) &&
            ((this.templateFormatValue==null && other.getTemplateFormatValue()==null) || 
             (this.templateFormatValue!=null &&
              this.templateFormatValue.equals(other.getTemplateFormatValue())));
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
        if (getTemplateFormatLabel() != null) {
            _hashCode += getTemplateFormatLabel().hashCode();
        }
        if (getTemplateFormatValue() != null) {
            _hashCode += getTemplateFormatValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TemplateFormatLabelValue.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "TemplateFormatLabelValue"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateFormatLabel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateFormatLabel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateFormatValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateFormatValue"));
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
