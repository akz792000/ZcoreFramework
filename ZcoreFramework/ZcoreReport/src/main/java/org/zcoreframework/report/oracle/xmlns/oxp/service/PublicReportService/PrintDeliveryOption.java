/**
 * PrintDeliveryOption.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class PrintDeliveryOption  implements java.io.Serializable {
    private java.lang.String printNumberOfCopy;

    private java.lang.String printRange;

    private java.lang.String printSide;

    private java.lang.String printTray;

    private java.lang.String printerName;

    public PrintDeliveryOption() {
    }

    public PrintDeliveryOption(
           java.lang.String printNumberOfCopy,
           java.lang.String printRange,
           java.lang.String printSide,
           java.lang.String printTray,
           java.lang.String printerName) {
           this.printNumberOfCopy = printNumberOfCopy;
           this.printRange = printRange;
           this.printSide = printSide;
           this.printTray = printTray;
           this.printerName = printerName;
    }


    /**
     * Gets the printNumberOfCopy value for this PrintDeliveryOption.
     * 
     * @return printNumberOfCopy
     */
    public java.lang.String getPrintNumberOfCopy() {
        return printNumberOfCopy;
    }


    /**
     * Sets the printNumberOfCopy value for this PrintDeliveryOption.
     * 
     * @param printNumberOfCopy
     */
    public void setPrintNumberOfCopy(java.lang.String printNumberOfCopy) {
        this.printNumberOfCopy = printNumberOfCopy;
    }


    /**
     * Gets the printRange value for this PrintDeliveryOption.
     * 
     * @return printRange
     */
    public java.lang.String getPrintRange() {
        return printRange;
    }


    /**
     * Sets the printRange value for this PrintDeliveryOption.
     * 
     * @param printRange
     */
    public void setPrintRange(java.lang.String printRange) {
        this.printRange = printRange;
    }


    /**
     * Gets the printSide value for this PrintDeliveryOption.
     * 
     * @return printSide
     */
    public java.lang.String getPrintSide() {
        return printSide;
    }


    /**
     * Sets the printSide value for this PrintDeliveryOption.
     * 
     * @param printSide
     */
    public void setPrintSide(java.lang.String printSide) {
        this.printSide = printSide;
    }


    /**
     * Gets the printTray value for this PrintDeliveryOption.
     * 
     * @return printTray
     */
    public java.lang.String getPrintTray() {
        return printTray;
    }


    /**
     * Sets the printTray value for this PrintDeliveryOption.
     * 
     * @param printTray
     */
    public void setPrintTray(java.lang.String printTray) {
        this.printTray = printTray;
    }


    /**
     * Gets the printerName value for this PrintDeliveryOption.
     * 
     * @return printerName
     */
    public java.lang.String getPrinterName() {
        return printerName;
    }


    /**
     * Sets the printerName value for this PrintDeliveryOption.
     * 
     * @param printerName
     */
    public void setPrinterName(java.lang.String printerName) {
        this.printerName = printerName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PrintDeliveryOption)) return false;
        PrintDeliveryOption other = (PrintDeliveryOption) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.printNumberOfCopy==null && other.getPrintNumberOfCopy()==null) || 
             (this.printNumberOfCopy!=null &&
              this.printNumberOfCopy.equals(other.getPrintNumberOfCopy()))) &&
            ((this.printRange==null && other.getPrintRange()==null) || 
             (this.printRange!=null &&
              this.printRange.equals(other.getPrintRange()))) &&
            ((this.printSide==null && other.getPrintSide()==null) || 
             (this.printSide!=null &&
              this.printSide.equals(other.getPrintSide()))) &&
            ((this.printTray==null && other.getPrintTray()==null) || 
             (this.printTray!=null &&
              this.printTray.equals(other.getPrintTray()))) &&
            ((this.printerName==null && other.getPrinterName()==null) || 
             (this.printerName!=null &&
              this.printerName.equals(other.getPrinterName())));
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
        if (getPrintNumberOfCopy() != null) {
            _hashCode += getPrintNumberOfCopy().hashCode();
        }
        if (getPrintRange() != null) {
            _hashCode += getPrintRange().hashCode();
        }
        if (getPrintSide() != null) {
            _hashCode += getPrintSide().hashCode();
        }
        if (getPrintTray() != null) {
            _hashCode += getPrintTray().hashCode();
        }
        if (getPrinterName() != null) {
            _hashCode += getPrinterName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PrintDeliveryOption.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "PrintDeliveryOption"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("printNumberOfCopy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "printNumberOfCopy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("printRange");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "printRange"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("printSide");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "printSide"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("printTray");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "printTray"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("printerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "printerName"));
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
