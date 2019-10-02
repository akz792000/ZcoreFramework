/**
 * EMailDeliveryOption.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class EMailDeliveryOption  implements java.io.Serializable {
    private java.lang.String emailBCC;

    private java.lang.String emailBody;

    private java.lang.String emailCC;

    private java.lang.String emailFrom;

    private java.lang.String emailReplyTo;

    private java.lang.String emailSubject;

    private java.lang.String emailTo;

    public EMailDeliveryOption() {
    }

    public EMailDeliveryOption(
           java.lang.String emailBCC,
           java.lang.String emailBody,
           java.lang.String emailCC,
           java.lang.String emailFrom,
           java.lang.String emailReplyTo,
           java.lang.String emailSubject,
           java.lang.String emailTo) {
           this.emailBCC = emailBCC;
           this.emailBody = emailBody;
           this.emailCC = emailCC;
           this.emailFrom = emailFrom;
           this.emailReplyTo = emailReplyTo;
           this.emailSubject = emailSubject;
           this.emailTo = emailTo;
    }


    /**
     * Gets the emailBCC value for this EMailDeliveryOption.
     * 
     * @return emailBCC
     */
    public java.lang.String getEmailBCC() {
        return emailBCC;
    }


    /**
     * Sets the emailBCC value for this EMailDeliveryOption.
     * 
     * @param emailBCC
     */
    public void setEmailBCC(java.lang.String emailBCC) {
        this.emailBCC = emailBCC;
    }


    /**
     * Gets the emailBody value for this EMailDeliveryOption.
     * 
     * @return emailBody
     */
    public java.lang.String getEmailBody() {
        return emailBody;
    }


    /**
     * Sets the emailBody value for this EMailDeliveryOption.
     * 
     * @param emailBody
     */
    public void setEmailBody(java.lang.String emailBody) {
        this.emailBody = emailBody;
    }


    /**
     * Gets the emailCC value for this EMailDeliveryOption.
     * 
     * @return emailCC
     */
    public java.lang.String getEmailCC() {
        return emailCC;
    }


    /**
     * Sets the emailCC value for this EMailDeliveryOption.
     * 
     * @param emailCC
     */
    public void setEmailCC(java.lang.String emailCC) {
        this.emailCC = emailCC;
    }


    /**
     * Gets the emailFrom value for this EMailDeliveryOption.
     * 
     * @return emailFrom
     */
    public java.lang.String getEmailFrom() {
        return emailFrom;
    }


    /**
     * Sets the emailFrom value for this EMailDeliveryOption.
     * 
     * @param emailFrom
     */
    public void setEmailFrom(java.lang.String emailFrom) {
        this.emailFrom = emailFrom;
    }


    /**
     * Gets the emailReplyTo value for this EMailDeliveryOption.
     * 
     * @return emailReplyTo
     */
    public java.lang.String getEmailReplyTo() {
        return emailReplyTo;
    }


    /**
     * Sets the emailReplyTo value for this EMailDeliveryOption.
     * 
     * @param emailReplyTo
     */
    public void setEmailReplyTo(java.lang.String emailReplyTo) {
        this.emailReplyTo = emailReplyTo;
    }


    /**
     * Gets the emailSubject value for this EMailDeliveryOption.
     * 
     * @return emailSubject
     */
    public java.lang.String getEmailSubject() {
        return emailSubject;
    }


    /**
     * Sets the emailSubject value for this EMailDeliveryOption.
     * 
     * @param emailSubject
     */
    public void setEmailSubject(java.lang.String emailSubject) {
        this.emailSubject = emailSubject;
    }


    /**
     * Gets the emailTo value for this EMailDeliveryOption.
     * 
     * @return emailTo
     */
    public java.lang.String getEmailTo() {
        return emailTo;
    }


    /**
     * Sets the emailTo value for this EMailDeliveryOption.
     * 
     * @param emailTo
     */
    public void setEmailTo(java.lang.String emailTo) {
        this.emailTo = emailTo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EMailDeliveryOption)) return false;
        EMailDeliveryOption other = (EMailDeliveryOption) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.emailBCC==null && other.getEmailBCC()==null) || 
             (this.emailBCC!=null &&
              this.emailBCC.equals(other.getEmailBCC()))) &&
            ((this.emailBody==null && other.getEmailBody()==null) || 
             (this.emailBody!=null &&
              this.emailBody.equals(other.getEmailBody()))) &&
            ((this.emailCC==null && other.getEmailCC()==null) || 
             (this.emailCC!=null &&
              this.emailCC.equals(other.getEmailCC()))) &&
            ((this.emailFrom==null && other.getEmailFrom()==null) || 
             (this.emailFrom!=null &&
              this.emailFrom.equals(other.getEmailFrom()))) &&
            ((this.emailReplyTo==null && other.getEmailReplyTo()==null) || 
             (this.emailReplyTo!=null &&
              this.emailReplyTo.equals(other.getEmailReplyTo()))) &&
            ((this.emailSubject==null && other.getEmailSubject()==null) || 
             (this.emailSubject!=null &&
              this.emailSubject.equals(other.getEmailSubject()))) &&
            ((this.emailTo==null && other.getEmailTo()==null) || 
             (this.emailTo!=null &&
              this.emailTo.equals(other.getEmailTo())));
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
        if (getEmailBCC() != null) {
            _hashCode += getEmailBCC().hashCode();
        }
        if (getEmailBody() != null) {
            _hashCode += getEmailBody().hashCode();
        }
        if (getEmailCC() != null) {
            _hashCode += getEmailCC().hashCode();
        }
        if (getEmailFrom() != null) {
            _hashCode += getEmailFrom().hashCode();
        }
        if (getEmailReplyTo() != null) {
            _hashCode += getEmailReplyTo().hashCode();
        }
        if (getEmailSubject() != null) {
            _hashCode += getEmailSubject().hashCode();
        }
        if (getEmailTo() != null) {
            _hashCode += getEmailTo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EMailDeliveryOption.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "EMailDeliveryOption"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailBCC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "emailBCC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "emailBody"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailCC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "emailCC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "emailFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailReplyTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "emailReplyTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailSubject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "emailSubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "emailTo"));
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
