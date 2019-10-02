/**
 * ItemData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class ItemData  implements java.io.Serializable {
    private java.lang.String absolutePath;

    private java.util.Calendar creationDate;

    private java.lang.String displayName;

    private java.lang.String fileName;

    private java.util.Calendar lastModified;

    private java.lang.String lastModifier;

    private java.lang.String owner;

    private java.lang.String parentAbsolutePath;

    private java.lang.String type;

    public ItemData() {
    }

    public ItemData(
           java.lang.String absolutePath,
           java.util.Calendar creationDate,
           java.lang.String displayName,
           java.lang.String fileName,
           java.util.Calendar lastModified,
           java.lang.String lastModifier,
           java.lang.String owner,
           java.lang.String parentAbsolutePath,
           java.lang.String type) {
           this.absolutePath = absolutePath;
           this.creationDate = creationDate;
           this.displayName = displayName;
           this.fileName = fileName;
           this.lastModified = lastModified;
           this.lastModifier = lastModifier;
           this.owner = owner;
           this.parentAbsolutePath = parentAbsolutePath;
           this.type = type;
    }


    /**
     * Gets the absolutePath value for this ItemData.
     * 
     * @return absolutePath
     */
    public java.lang.String getAbsolutePath() {
        return absolutePath;
    }


    /**
     * Sets the absolutePath value for this ItemData.
     * 
     * @param absolutePath
     */
    public void setAbsolutePath(java.lang.String absolutePath) {
        this.absolutePath = absolutePath;
    }


    /**
     * Gets the creationDate value for this ItemData.
     * 
     * @return creationDate
     */
    public java.util.Calendar getCreationDate() {
        return creationDate;
    }


    /**
     * Sets the creationDate value for this ItemData.
     * 
     * @param creationDate
     */
    public void setCreationDate(java.util.Calendar creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Gets the displayName value for this ItemData.
     * 
     * @return displayName
     */
    public java.lang.String getDisplayName() {
        return displayName;
    }


    /**
     * Sets the displayName value for this ItemData.
     * 
     * @param displayName
     */
    public void setDisplayName(java.lang.String displayName) {
        this.displayName = displayName;
    }


    /**
     * Gets the fileName value for this ItemData.
     * 
     * @return fileName
     */
    public java.lang.String getFileName() {
        return fileName;
    }


    /**
     * Sets the fileName value for this ItemData.
     * 
     * @param fileName
     */
    public void setFileName(java.lang.String fileName) {
        this.fileName = fileName;
    }


    /**
     * Gets the lastModified value for this ItemData.
     * 
     * @return lastModified
     */
    public java.util.Calendar getLastModified() {
        return lastModified;
    }


    /**
     * Sets the lastModified value for this ItemData.
     * 
     * @param lastModified
     */
    public void setLastModified(java.util.Calendar lastModified) {
        this.lastModified = lastModified;
    }


    /**
     * Gets the lastModifier value for this ItemData.
     * 
     * @return lastModifier
     */
    public java.lang.String getLastModifier() {
        return lastModifier;
    }


    /**
     * Sets the lastModifier value for this ItemData.
     * 
     * @param lastModifier
     */
    public void setLastModifier(java.lang.String lastModifier) {
        this.lastModifier = lastModifier;
    }


    /**
     * Gets the owner value for this ItemData.
     * 
     * @return owner
     */
    public java.lang.String getOwner() {
        return owner;
    }


    /**
     * Sets the owner value for this ItemData.
     * 
     * @param owner
     */
    public void setOwner(java.lang.String owner) {
        this.owner = owner;
    }


    /**
     * Gets the parentAbsolutePath value for this ItemData.
     * 
     * @return parentAbsolutePath
     */
    public java.lang.String getParentAbsolutePath() {
        return parentAbsolutePath;
    }


    /**
     * Sets the parentAbsolutePath value for this ItemData.
     * 
     * @param parentAbsolutePath
     */
    public void setParentAbsolutePath(java.lang.String parentAbsolutePath) {
        this.parentAbsolutePath = parentAbsolutePath;
    }


    /**
     * Gets the type value for this ItemData.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this ItemData.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ItemData)) return false;
        ItemData other = (ItemData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.absolutePath==null && other.getAbsolutePath()==null) || 
             (this.absolutePath!=null &&
              this.absolutePath.equals(other.getAbsolutePath()))) &&
            ((this.creationDate==null && other.getCreationDate()==null) || 
             (this.creationDate!=null &&
              this.creationDate.equals(other.getCreationDate()))) &&
            ((this.displayName==null && other.getDisplayName()==null) || 
             (this.displayName!=null &&
              this.displayName.equals(other.getDisplayName()))) &&
            ((this.fileName==null && other.getFileName()==null) || 
             (this.fileName!=null &&
              this.fileName.equals(other.getFileName()))) &&
            ((this.lastModified==null && other.getLastModified()==null) || 
             (this.lastModified!=null &&
              this.lastModified.equals(other.getLastModified()))) &&
            ((this.lastModifier==null && other.getLastModifier()==null) || 
             (this.lastModifier!=null &&
              this.lastModifier.equals(other.getLastModifier()))) &&
            ((this.owner==null && other.getOwner()==null) || 
             (this.owner!=null &&
              this.owner.equals(other.getOwner()))) &&
            ((this.parentAbsolutePath==null && other.getParentAbsolutePath()==null) || 
             (this.parentAbsolutePath!=null &&
              this.parentAbsolutePath.equals(other.getParentAbsolutePath()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType())));
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
        if (getAbsolutePath() != null) {
            _hashCode += getAbsolutePath().hashCode();
        }
        if (getCreationDate() != null) {
            _hashCode += getCreationDate().hashCode();
        }
        if (getDisplayName() != null) {
            _hashCode += getDisplayName().hashCode();
        }
        if (getFileName() != null) {
            _hashCode += getFileName().hashCode();
        }
        if (getLastModified() != null) {
            _hashCode += getLastModified().hashCode();
        }
        if (getLastModifier() != null) {
            _hashCode += getLastModifier().hashCode();
        }
        if (getOwner() != null) {
            _hashCode += getOwner().hashCode();
        }
        if (getParentAbsolutePath() != null) {
            _hashCode += getParentAbsolutePath().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ItemData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ItemData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("absolutePath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "absolutePath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "creationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("displayName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "displayName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModified");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "lastModified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastModifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "lastModifier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("owner");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "owner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentAbsolutePath");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "parentAbsolutePath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "type"));
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
