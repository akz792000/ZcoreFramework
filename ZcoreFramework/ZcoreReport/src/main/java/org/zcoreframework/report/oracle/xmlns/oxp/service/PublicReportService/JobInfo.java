/**
 * JobInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class JobInfo  implements java.io.Serializable {
    private boolean burstingOption;

    private java.util.Calendar createdDate;

    private java.lang.String deliveryDescription;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.DeliveryRequest deliveryParameters;

    private java.util.Calendar endDate;

    private java.lang.String jobGroup;

    private int jobID;

    private java.lang.String jobName;

    private java.lang.String locale;

    private java.lang.String reportName;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] reportParameters;

    private boolean reportSet;

    private java.lang.String reportURL;

    private java.lang.String runType;

    private java.lang.String scheduleDescription;

    private java.lang.String scheduleParameters;

    private java.util.Calendar startDate;

    private java.lang.String status;

    private java.lang.String username;

    public JobInfo() {
    }

    public JobInfo(
           boolean burstingOption,
           java.util.Calendar createdDate,
           java.lang.String deliveryDescription,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.DeliveryRequest deliveryParameters,
           java.util.Calendar endDate,
           java.lang.String jobGroup,
           int jobID,
           java.lang.String jobName,
           java.lang.String locale,
           java.lang.String reportName,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] reportParameters,
           boolean reportSet,
           java.lang.String reportURL,
           java.lang.String runType,
           java.lang.String scheduleDescription,
           java.lang.String scheduleParameters,
           java.util.Calendar startDate,
           java.lang.String status,
           java.lang.String username) {
           this.burstingOption = burstingOption;
           this.createdDate = createdDate;
           this.deliveryDescription = deliveryDescription;
           this.deliveryParameters = deliveryParameters;
           this.endDate = endDate;
           this.jobGroup = jobGroup;
           this.jobID = jobID;
           this.jobName = jobName;
           this.locale = locale;
           this.reportName = reportName;
           this.reportParameters = reportParameters;
           this.reportSet = reportSet;
           this.reportURL = reportURL;
           this.runType = runType;
           this.scheduleDescription = scheduleDescription;
           this.scheduleParameters = scheduleParameters;
           this.startDate = startDate;
           this.status = status;
           this.username = username;
    }


    /**
     * Gets the burstingOption value for this JobInfo.
     * 
     * @return burstingOption
     */
    public boolean isBurstingOption() {
        return burstingOption;
    }


    /**
     * Sets the burstingOption value for this JobInfo.
     * 
     * @param burstingOption
     */
    public void setBurstingOption(boolean burstingOption) {
        this.burstingOption = burstingOption;
    }


    /**
     * Gets the createdDate value for this JobInfo.
     * 
     * @return createdDate
     */
    public java.util.Calendar getCreatedDate() {
        return createdDate;
    }


    /**
     * Sets the createdDate value for this JobInfo.
     * 
     * @param createdDate
     */
    public void setCreatedDate(java.util.Calendar createdDate) {
        this.createdDate = createdDate;
    }


    /**
     * Gets the deliveryDescription value for this JobInfo.
     * 
     * @return deliveryDescription
     */
    public java.lang.String getDeliveryDescription() {
        return deliveryDescription;
    }


    /**
     * Sets the deliveryDescription value for this JobInfo.
     * 
     * @param deliveryDescription
     */
    public void setDeliveryDescription(java.lang.String deliveryDescription) {
        this.deliveryDescription = deliveryDescription;
    }


    /**
     * Gets the deliveryParameters value for this JobInfo.
     * 
     * @return deliveryParameters
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.DeliveryRequest getDeliveryParameters() {
        return deliveryParameters;
    }


    /**
     * Sets the deliveryParameters value for this JobInfo.
     * 
     * @param deliveryParameters
     */
    public void setDeliveryParameters(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.DeliveryRequest deliveryParameters) {
        this.deliveryParameters = deliveryParameters;
    }


    /**
     * Gets the endDate value for this JobInfo.
     * 
     * @return endDate
     */
    public java.util.Calendar getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this JobInfo.
     * 
     * @param endDate
     */
    public void setEndDate(java.util.Calendar endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the jobGroup value for this JobInfo.
     * 
     * @return jobGroup
     */
    public java.lang.String getJobGroup() {
        return jobGroup;
    }


    /**
     * Sets the jobGroup value for this JobInfo.
     * 
     * @param jobGroup
     */
    public void setJobGroup(java.lang.String jobGroup) {
        this.jobGroup = jobGroup;
    }


    /**
     * Gets the jobID value for this JobInfo.
     * 
     * @return jobID
     */
    public int getJobID() {
        return jobID;
    }


    /**
     * Sets the jobID value for this JobInfo.
     * 
     * @param jobID
     */
    public void setJobID(int jobID) {
        this.jobID = jobID;
    }


    /**
     * Gets the jobName value for this JobInfo.
     * 
     * @return jobName
     */
    public java.lang.String getJobName() {
        return jobName;
    }


    /**
     * Sets the jobName value for this JobInfo.
     * 
     * @param jobName
     */
    public void setJobName(java.lang.String jobName) {
        this.jobName = jobName;
    }


    /**
     * Gets the locale value for this JobInfo.
     * 
     * @return locale
     */
    public java.lang.String getLocale() {
        return locale;
    }


    /**
     * Sets the locale value for this JobInfo.
     * 
     * @param locale
     */
    public void setLocale(java.lang.String locale) {
        this.locale = locale;
    }


    /**
     * Gets the reportName value for this JobInfo.
     * 
     * @return reportName
     */
    public java.lang.String getReportName() {
        return reportName;
    }


    /**
     * Sets the reportName value for this JobInfo.
     * 
     * @param reportName
     */
    public void setReportName(java.lang.String reportName) {
        this.reportName = reportName;
    }


    /**
     * Gets the reportParameters value for this JobInfo.
     * 
     * @return reportParameters
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] getReportParameters() {
        return reportParameters;
    }


    /**
     * Sets the reportParameters value for this JobInfo.
     * 
     * @param reportParameters
     */
    public void setReportParameters(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] reportParameters) {
        this.reportParameters = reportParameters;
    }


    /**
     * Gets the reportSet value for this JobInfo.
     * 
     * @return reportSet
     */
    public boolean isReportSet() {
        return reportSet;
    }


    /**
     * Sets the reportSet value for this JobInfo.
     * 
     * @param reportSet
     */
    public void setReportSet(boolean reportSet) {
        this.reportSet = reportSet;
    }


    /**
     * Gets the reportURL value for this JobInfo.
     * 
     * @return reportURL
     */
    public java.lang.String getReportURL() {
        return reportURL;
    }


    /**
     * Sets the reportURL value for this JobInfo.
     * 
     * @param reportURL
     */
    public void setReportURL(java.lang.String reportURL) {
        this.reportURL = reportURL;
    }


    /**
     * Gets the runType value for this JobInfo.
     * 
     * @return runType
     */
    public java.lang.String getRunType() {
        return runType;
    }


    /**
     * Sets the runType value for this JobInfo.
     * 
     * @param runType
     */
    public void setRunType(java.lang.String runType) {
        this.runType = runType;
    }


    /**
     * Gets the scheduleDescription value for this JobInfo.
     * 
     * @return scheduleDescription
     */
    public java.lang.String getScheduleDescription() {
        return scheduleDescription;
    }


    /**
     * Sets the scheduleDescription value for this JobInfo.
     * 
     * @param scheduleDescription
     */
    public void setScheduleDescription(java.lang.String scheduleDescription) {
        this.scheduleDescription = scheduleDescription;
    }


    /**
     * Gets the scheduleParameters value for this JobInfo.
     * 
     * @return scheduleParameters
     */
    public java.lang.String getScheduleParameters() {
        return scheduleParameters;
    }


    /**
     * Sets the scheduleParameters value for this JobInfo.
     * 
     * @param scheduleParameters
     */
    public void setScheduleParameters(java.lang.String scheduleParameters) {
        this.scheduleParameters = scheduleParameters;
    }


    /**
     * Gets the startDate value for this JobInfo.
     * 
     * @return startDate
     */
    public java.util.Calendar getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this JobInfo.
     * 
     * @param startDate
     */
    public void setStartDate(java.util.Calendar startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the status value for this JobInfo.
     * 
     * @return status
     */
    public java.lang.String getStatus() {
        return status;
    }


    /**
     * Sets the status value for this JobInfo.
     * 
     * @param status
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }


    /**
     * Gets the username value for this JobInfo.
     * 
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this JobInfo.
     * 
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof JobInfo)) return false;
        JobInfo other = (JobInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.burstingOption == other.isBurstingOption() &&
            ((this.createdDate==null && other.getCreatedDate()==null) || 
             (this.createdDate!=null &&
              this.createdDate.equals(other.getCreatedDate()))) &&
            ((this.deliveryDescription==null && other.getDeliveryDescription()==null) || 
             (this.deliveryDescription!=null &&
              this.deliveryDescription.equals(other.getDeliveryDescription()))) &&
            ((this.deliveryParameters==null && other.getDeliveryParameters()==null) || 
             (this.deliveryParameters!=null &&
              this.deliveryParameters.equals(other.getDeliveryParameters()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.jobGroup==null && other.getJobGroup()==null) || 
             (this.jobGroup!=null &&
              this.jobGroup.equals(other.getJobGroup()))) &&
            this.jobID == other.getJobID() &&
            ((this.jobName==null && other.getJobName()==null) || 
             (this.jobName!=null &&
              this.jobName.equals(other.getJobName()))) &&
            ((this.locale==null && other.getLocale()==null) || 
             (this.locale!=null &&
              this.locale.equals(other.getLocale()))) &&
            ((this.reportName==null && other.getReportName()==null) || 
             (this.reportName!=null &&
              this.reportName.equals(other.getReportName()))) &&
            ((this.reportParameters==null && other.getReportParameters()==null) || 
             (this.reportParameters!=null &&
              java.util.Arrays.equals(this.reportParameters, other.getReportParameters()))) &&
            this.reportSet == other.isReportSet() &&
            ((this.reportURL==null && other.getReportURL()==null) || 
             (this.reportURL!=null &&
              this.reportURL.equals(other.getReportURL()))) &&
            ((this.runType==null && other.getRunType()==null) || 
             (this.runType!=null &&
              this.runType.equals(other.getRunType()))) &&
            ((this.scheduleDescription==null && other.getScheduleDescription()==null) || 
             (this.scheduleDescription!=null &&
              this.scheduleDescription.equals(other.getScheduleDescription()))) &&
            ((this.scheduleParameters==null && other.getScheduleParameters()==null) || 
             (this.scheduleParameters!=null &&
              this.scheduleParameters.equals(other.getScheduleParameters()))) &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
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
        _hashCode += (isBurstingOption() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCreatedDate() != null) {
            _hashCode += getCreatedDate().hashCode();
        }
        if (getDeliveryDescription() != null) {
            _hashCode += getDeliveryDescription().hashCode();
        }
        if (getDeliveryParameters() != null) {
            _hashCode += getDeliveryParameters().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getJobGroup() != null) {
            _hashCode += getJobGroup().hashCode();
        }
        _hashCode += getJobID();
        if (getJobName() != null) {
            _hashCode += getJobName().hashCode();
        }
        if (getLocale() != null) {
            _hashCode += getLocale().hashCode();
        }
        if (getReportName() != null) {
            _hashCode += getReportName().hashCode();
        }
        if (getReportParameters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReportParameters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReportParameters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += (isReportSet() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getReportURL() != null) {
            _hashCode += getReportURL().hashCode();
        }
        if (getRunType() != null) {
            _hashCode += getRunType().hashCode();
        }
        if (getScheduleDescription() != null) {
            _hashCode += getScheduleDescription().hashCode();
        }
        if (getScheduleParameters() != null) {
            _hashCode += getScheduleParameters().hashCode();
        }
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(JobInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("burstingOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "burstingOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "createdDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deliveryDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deliveryParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "DeliveryRequest"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "endDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "jobGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "jobID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "jobName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("locale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "locale"));
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
        elemField.setFieldName("reportParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ParamNameValue"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportSet");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportSet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("runType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "runType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduleDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduleDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduleParameters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduleParameters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "status"));
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
