/**
 * ParamNameValue.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class ParamNameValue  implements java.io.Serializable {
    private boolean multiValuesAllowed;

    private java.lang.String name;

    private java.lang.String[] values;

    public ParamNameValue() {
    }

    public ParamNameValue(
           boolean multiValuesAllowed,
           java.lang.String name,
           java.lang.String[] values) {
           this.multiValuesAllowed = multiValuesAllowed;
           this.name = name;
           this.values = values;
    }


    /**
     * Gets the multiValuesAllowed value for this ParamNameValue.
     * 
     * @return multiValuesAllowed
     */
    public boolean isMultiValuesAllowed() {
        return multiValuesAllowed;
    }


    /**
     * Sets the multiValuesAllowed value for this ParamNameValue.
     * 
     * @param multiValuesAllowed
     */
    public void setMultiValuesAllowed(boolean multiValuesAllowed) {
        this.multiValuesAllowed = multiValuesAllowed;
    }


    /**
     * Gets the name value for this ParamNameValue.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this ParamNameValue.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the values value for this ParamNameValue.
     * 
     * @return values
     */
    public java.lang.String[] getValues() {
        return values;
    }


    /**
     * Sets the values value for this ParamNameValue.
     * 
     * @param values
     */
    public void setValues(java.lang.String[] values) {
        this.values = values;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamNameValue)) return false;
        ParamNameValue other = (ParamNameValue) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.multiValuesAllowed == other.isMultiValuesAllowed() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.values==null && other.getValues()==null) || 
             (this.values!=null &&
              java.util.Arrays.equals(this.values, other.getValues())));
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
        _hashCode += (isMultiValuesAllowed() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamNameValue.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ParamNameValue"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("multiValuesAllowed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "multiValuesAllowed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("values");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "values"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item"));
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
