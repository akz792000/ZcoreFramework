/**
 * JobHistoryInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class JobHistoryInfo  implements java.io.Serializable {
    private java.util.Calendar createdDate;

    private boolean documentDataAvailable;

    private java.lang.String documentDataContentType;

    private byte[] documentOutput;

    private int jobID;

    private java.lang.String jobMessage;

    private java.lang.String jobName;

    private int outputID;

    private byte[] reportData;

    private boolean reportDataAvailable;

    private java.lang.String reportDataContentType;

    private java.lang.String reportName;

    private java.lang.String reportURL;

    private java.lang.String status;

    private java.lang.String statusDetail;

    private java.lang.String username;

    public JobHistoryInfo() {
    }

    public JobHistoryInfo(
           java.util.Calendar createdDate,
           boolean documentDataAvailable,
           java.lang.String documentDataContentType,
           byte[] documentOutput,
           int jobID,
           java.lang.String jobMessage,
           java.lang.String jobName,
           int outputID,
           byte[] reportData,
           boolean reportDataAvailable,
           java.lang.String reportDataContentType,
           java.lang.String reportName,
           java.lang.String reportURL,
           java.lang.String status,
           java.lang.String statusDetail,
           java.lang.String username) {
           this.createdDate = createdDate;
           this.documentDataAvailable = documentDataAvailable;
           this.documentDataContentType = documentDataContentType;
           this.documentOutput = documentOutput;
           this.jobID = jobID;
           this.jobMessage = jobMessage;
           this.jobName = jobName;
           this.outputID = outputID;
           this.reportData = reportData;
           this.reportDataAvailable = reportDataAvailable;
           this.reportDataContentType = reportDataContentType;
           this.reportName = reportName;
           this.reportURL = reportURL;
           this.status = status;
           this.statusDetail = statusDetail;
           this.username = username;
    }


    /**
     * Gets the createdDate value for this JobHistoryInfo.
     * 
     * @return createdDate
     */
    public java.util.Calendar getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this JobHistoryInfo.
     * 
     * @param createdDate
     */
    public void setCreatedDate(java.util.Calendar createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the documentDataAvailable value for this JobHistoryInfo.
     * 
     * @return documentDataAvailable
     */
    public boolean isDocumentDataAvailable() {
        return documentDataAvailable;
    }


    /**
     * Sets the documentDataAvailable value for this JobHistoryInfo.
     * 
     * @param documentDataAvailable
     */
    public void setDocumentDataAvailable(boolean documentDataAvailable) {
        this.documentDataAvailable = documentDataAvailable;
    }


    /**
     * Gets the documentDataContentType value for this JobHistoryInfo.
     * 
     * @return documentDataContentType
     */
    public java.lang.String getDocumentDataContentType() {
        return documentDataContentType;
    }


    /**
     * Sets the documentDataContentType value for this JobHistoryInfo.
     * 
     * @param documentDataContentType
     */
    public void setDocumentDataContentType(java.lang.String documentDataContentType) {
        this.documentDataContentType = documentDataContentType;
    }


    /**
     * Gets the documentOutput value for this JobHistoryInfo.
     * 
     * @return documentOutput
     */
    public byte[] getDocumentOutput() {
        return documentOutput;
    }


    /**
     * Sets the documentOutput value for this JobHistoryInfo.
     * 
     * @param documentOutput
     */
    public void setDocumentOutput(byte[] documentOutput) {
        this.documentOutput = documentOutput;
    }


    /**
     * Gets the jobID value for this JobHistoryInfo.
     * 
     * @return jobID
     */
    public int getJobID() {
        return jobID;
    }


    /**
     * Sets the jobID value for this JobHistoryInfo.
     * 
     * @param jobID
     */
    public void setJobID(int jobID) {
        this.jobID = jobID;
    }


    /**
     * Gets the jobMessage value for this JobHistoryInfo.
     * 
     * @return jobMessage
     */
    public java.lang.String getJobMessage() {
        return jobMessage;
    }


    /**
     * Sets the jobMessage value for this JobHistoryInfo.
     * 
     * @param jobMessage
     */
    public void setJobMessage(java.lang.String jobMessage) {
        this.jobMessage = jobMessage;
    }


    /**
     * Gets the jobName value for this JobHistoryInfo.
     * 
     * @return jobName
     */
    public java.lang.String getJobName() {
        return jobName;
    }


    /**
     * Sets the jobName value for this JobHistoryInfo.
     * 
     * @param jobName
     */
    public void setJobName(java.lang.String jobName) {
        this.jobName = jobName;
    }


    /**
     * Gets the outputID value for this JobHistoryInfo.
     * 
     * @return outputID
     */
    public int getOutputID() {
        return outputID;
    }


    /**
     * Sets the outputID value for this JobHistoryInfo.
     * 
     * @param outputID
     */
    public void setOutputID(int outputID) {
        this.outputID = outputID;
    }


    /**
     * Gets the reportData value for this JobHistoryInfo.
     * 
     * @return reportData
     */
    public byte[] getReportData() {
        return reportData;
    }


    /**
     * Sets the reportData value for this JobHistoryInfo.
     * 
     * @param reportData
     */
    public void setReportData(byte[] reportData) {
        this.reportData = reportData;
    }


    /**
     * Gets the reportDataAvailable value for this JobHistoryInfo.
     * 
     * @return reportDataAvailable
     */
    public boolean isReportDataAvailable() {
        return reportDataAvailable;
    }


    /**
     * Sets the reportDataAvailable value for this JobHistoryInfo.
     * 
     * @param reportDataAvailable
     */
    public void setReportDataAvailable(boolean reportDataAvailable) {
        this.reportDataAvailable = reportDataAvailable;
    }


    /**
     * Gets the reportDataContentType value for this JobHistoryInfo.
     * 
     * @return reportDataContentType
     */
    public java.lang.String getReportDataContentType() {
        return reportDataContentType;
    }


    /**
     * Sets the reportDataContentType value for this JobHistoryInfo.
     * 
     * @param reportDataContentType
     */
    public void setReportDataContentType(java.lang.String reportDataContentType) {
        this.reportDataContentType = reportDataContentType;
    }


    /**
     * Gets the reportName value for this JobHistoryInfo.
     * 
     * @return reportName
     */
    public java.lang.String getReportName() {
        return reportName;
    }


    /**
     * Sets the reportName value for this JobHistoryInfo.
     * 
     * @param reportName
     */
    public void setReportName(java.lang.String reportName) {
        this.reportName = reportName;
    }


    /**
     * Gets the reportURL value for this JobHistoryInfo.
     * 
     * @return reportURL
     */
    public java.lang.String getReportURL() {
        return reportURL;
    }


    /**
     * Sets the reportURL value for this JobHistoryInfo.
     * 
     * @param reportURL
     */
    public void setReportURL(java.lang.String reportURL) {
        this.reportURL = reportURL;
    }


    /**
     * Gets the status value for this JobHistoryInfo.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this JobHistoryInfo.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the statusDetail value for this JobHistoryInfo.
     * 
     * @return statusDetail
     */
    public java.lang.String getStatusDetail() {
        return statusDetail;
    }


    /**
     * Sets the statusDetail value for this JobHistoryInfo.
     * 
     * @param statusDetail
     */
    public void setStatusDetail(java.lang.String statusDetail) {
        this.statusDetail = statusDetail;
    }


    /**
     * Gets the username value for this JobHistoryInfo.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this JobHistoryInfo.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof JobHistoryInfo)) return false;
        JobHistoryInfo other = (JobHistoryInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.createdDate==null && other.getCreatedDate()==null) || 
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            this.documentDataAvailable == other.isDocumentDataAvailable() &&
            ((this.documentDataContentType==null && other.getDocumentDataContentType()==null) || 
             (this.documentDataContentType!=null &&
              this.documentDataContentType.equals(other.getDocumentDataContentType()))) &&
            ((this.documentOutput==null && other.getDocumentOutput()==null) || 
             (this.documentOutput!=null &&
              java.util.Arrays.equals(this.documentOutput, other.getDocumentOutput()))) &&
            this.jobID == other.getJobID() &&
            ((this.jobMessage==null && other.getJobMessage()==null) || 
             (this.jobMessage!=null &&
              this.jobMessage.equals(other.getJobMessage()))) &&
            ((this.jobName==null && other.getJobName()==null) || 
             (this.jobName!=null &&
              this.jobName.equals(other.getJobName()))) &&
            this.outputID == other.getOutputID() &&
            ((this.reportData==null && other.getReportData()==null) || 
             (this.reportData!=null &&
              java.util.Arrays.equals(this.reportData, other.getReportData()))) &&
            this.reportDataAvailable == other.isReportDataAvailable() &&
            ((this.reportDataContentType==null && other.getReportDataContentType()==null) || 
             (this.reportDataContentType!=null &&
              this.reportDataContentType.equals(other.getReportDataContentType()))) &&
            ((this.reportName==null && other.getReportName()==null) || 
             (this.reportName!=null &&
              this.reportName.equals(other.getReportName()))) &&
            ((this.reportURL==null && other.getReportURL()==null) || 
             (this.reportURL!=null &&
              this.reportURL.equals(other.getReportURL()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.statusDetail==null && other.getStatusDetail()==null) || 
             (this.statusDetail!=null &&
              this.statusDetail.equals(other.getStatusDetail()))) &&
            ((this.username==null && other.getUsername()==null) || 
             (this.username!=null &&
              this.username.equals(other.getUsername())));
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
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        _hashCode += (isDocumentDataAvailable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDocumentDataContentType() != null) {
            _hashCode += getDocumentDataContentType().hashCode();
        }
        if (getDocumentOutput() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumentOutput());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumentOutput(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getJobID();
        if (getJobMessage() != null) {
            _hashCode += getJobMessage().hashCode();
        }
        if (getJobName() != null) {
            _hashCode += getJobName().hashCode();
        }
        _hashCode += getOutputID();
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
        _hashCode += (isReportDataAvailable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getReportDataContentType() != null) {
            _hashCode += getReportDataContentType().hashCode();
        }
        if (getReportName() != null) {
            _hashCode += getReportName().hashCode();
        }
        if (getReportURL() != null) {
            _hashCode += getReportURL().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getStatusDetail() != null) {
            _hashCode += getStatusDetail().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(JobHistoryInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobHistoryInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "createdDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentDataAvailable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "documentDataAvailable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentDataContentType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "documentDataContentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentOutput");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "documentOutput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "jobID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "jobMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "jobName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outputID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "outputID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportDataAvailable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportDataAvailable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportDataContentType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportDataContentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "statusDetail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "username"));
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
