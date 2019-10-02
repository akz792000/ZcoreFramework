/**
 * ScheduleRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class ScheduleRequest  implements java.io.Serializable {
    private java.lang.String cronExpression;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.DeliveryRequest deliveryRequest;

    private java.util.Calendar endDate;

    private java.lang.String jobCalendar;

    private java.lang.String jobLocale;

    private java.lang.String jobTZ;

    private java.lang.String notificationTo;

    private boolean notifyWhenFailed;

    private boolean notifyWhenSuccess;

    private boolean notifyWhenWarning;

    private int repeatCount;

    private int repeatInterval;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest reportRequest;

    private boolean saveDataOption;

    private boolean saveOutputOption;

    private boolean scheduleBurstringOption;

    private boolean schedulePublicOption;

    private java.util.Calendar startDate;

    private boolean useUTF8Option;

    private java.lang.String userJobName;

    public ScheduleRequest() {
    }

    public ScheduleRequest(
           java.lang.String cronExpression,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.DeliveryRequest deliveryRequest,
           java.util.Calendar endDate,
           java.lang.String jobCalendar,
           java.lang.String jobLocale,
           java.lang.String jobTZ,
           java.lang.String notificationTo,
           boolean notifyWhenFailed,
           boolean notifyWhenSuccess,
           boolean notifyWhenWarning,
           int repeatCount,
           int repeatInterval,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest reportRequest,
           boolean saveDataOption,
           boolean saveOutputOption,
           boolean scheduleBurstringOption,
           boolean schedulePublicOption,
           java.util.Calendar startDate,
           boolean useUTF8Option,
           java.lang.String userJobName) {
           this.cronExpression = cronExpression;
           this.deliveryRequest = deliveryRequest;
           this.endDate = endDate;
           this.jobCalendar = jobCalendar;
           this.jobLocale = jobLocale;
           this.jobTZ = jobTZ;
           this.notificationTo = notificationTo;
           this.notifyWhenFailed = notifyWhenFailed;
           this.notifyWhenSuccess = notifyWhenSuccess;
           this.notifyWhenWarning = notifyWhenWarning;
           this.repeatCount = repeatCount;
           this.repeatInterval = repeatInterval;
           this.reportRequest = reportRequest;
           this.saveDataOption = saveDataOption;
           this.saveOutputOption = saveOutputOption;
           this.scheduleBurstringOption = scheduleBurstringOption;
           this.schedulePublicOption = schedulePublicOption;
           this.startDate = startDate;
           this.useUTF8Option = useUTF8Option;
           this.userJobName = userJobName;
    }


    /**
     * Gets the cronExpression value for this ScheduleRequest.
     * 
     * @return cronExpression
     */
    public java.lang.String getCronExpression() {
        return cronExpression;
    }


    /**
     * Sets the cronExpression value for this ScheduleRequest.
     * 
     * @param cronExpression
     */
    public void setCronExpression(java.lang.String cronExpression) {
        this.cronExpression = cronExpression;
    }


    /**
     * Gets the deliveryRequest value for this ScheduleRequest.
     * 
     * @return deliveryRequest
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.DeliveryRequest getDeliveryRequest() {
        return deliveryRequest;
    }


    /**
     * Sets the deliveryRequest value for this ScheduleRequest.
     * 
     * @param deliveryRequest
     */
    public void setDeliveryRequest(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.DeliveryRequest deliveryRequest) {
        this.deliveryRequest = deliveryRequest;
    }


    /**
     * Gets the endDate value for this ScheduleRequest.
     * 
     * @return endDate
     */
    public java.util.Calendar getEndDate() {
        return endDate;
    }


    /**
     * Sets the endDate value for this ScheduleRequest.
     * 
     * @param endDate
     */
    public void setEndDate(java.util.Calendar endDate) {
        this.endDate = endDate;
    }


    /**
     * Gets the jobCalendar value for this ScheduleRequest.
     * 
     * @return jobCalendar
     */
    public java.lang.String getJobCalendar() {
        return jobCalendar;
    }


    /**
     * Sets the jobCalendar value for this ScheduleRequest.
     * 
     * @param jobCalendar
     */
    public void setJobCalendar(java.lang.String jobCalendar) {
        this.jobCalendar = jobCalendar;
    }


    /**
     * Gets the jobLocale value for this ScheduleRequest.
     * 
     * @return jobLocale
     */
    public java.lang.String getJobLocale() {
        return jobLocale;
    }


    /**
     * Sets the jobLocale value for this ScheduleRequest.
     * 
     * @param jobLocale
     */
    public void setJobLocale(java.lang.String jobLocale) {
        this.jobLocale = jobLocale;
    }


    /**
     * Gets the jobTZ value for this ScheduleRequest.
     * 
     * @return jobTZ
     */
    public java.lang.String getJobTZ() {
        return jobTZ;
    }


    /**
     * Sets the jobTZ value for this ScheduleRequest.
     * 
     * @param jobTZ
     */
    public void setJobTZ(java.lang.String jobTZ) {
        this.jobTZ = jobTZ;
    }


    /**
     * Gets the notificationTo value for this ScheduleRequest.
     * 
     * @return notificationTo
     */
    public java.lang.String getNotificationTo() {
        return notificationTo;
    }


    /**
     * Sets the notificationTo value for this ScheduleRequest.
     * 
     * @param notificationTo
     */
    public void setNotificationTo(java.lang.String notificationTo) {
        this.notificationTo = notificationTo;
    }


    /**
     * Gets the notifyWhenFailed value for this ScheduleRequest.
     * 
     * @return notifyWhenFailed
     */
    public boolean isNotifyWhenFailed() {
        return notifyWhenFailed;
    }


    /**
     * Sets the notifyWhenFailed value for this ScheduleRequest.
     * 
     * @param notifyWhenFailed
     */
    public void setNotifyWhenFailed(boolean notifyWhenFailed) {
        this.notifyWhenFailed = notifyWhenFailed;
    }


    /**
     * Gets the notifyWhenSuccess value for this ScheduleRequest.
     * 
     * @return notifyWhenSuccess
     */
    public boolean isNotifyWhenSuccess() {
        return notifyWhenSuccess;
    }


    /**
     * Sets the notifyWhenSuccess value for this ScheduleRequest.
     * 
     * @param notifyWhenSuccess
     */
    public void setNotifyWhenSuccess(boolean notifyWhenSuccess) {
        this.notifyWhenSuccess = notifyWhenSuccess;
    }


    /**
     * Gets the notifyWhenWarning value for this ScheduleRequest.
     * 
     * @return notifyWhenWarning
     */
    public boolean isNotifyWhenWarning() {
        return notifyWhenWarning;
    }


    /**
     * Sets the notifyWhenWarning value for this ScheduleRequest.
     * 
     * @param notifyWhenWarning
     */
    public void setNotifyWhenWarning(boolean notifyWhenWarning) {
        this.notifyWhenWarning = notifyWhenWarning;
    }


    /**
     * Gets the repeatCount value for this ScheduleRequest.
     * 
     * @return repeatCount
     */
    public int getRepeatCount() {
        return repeatCount;
    }


    /**
     * Sets the repeatCount value for this ScheduleRequest.
     * 
     * @param repeatCount
     */
    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }


    /**
     * Gets the repeatInterval value for this ScheduleRequest.
     * 
     * @return repeatInterval
     */
    public int getRepeatInterval() {
        return repeatInterval;
    }


    /**
     * Sets the repeatInterval value for this ScheduleRequest.
     * 
     * @param repeatInterval
     */
    public void setRepeatInterval(int repeatInterval) {
        this.repeatInterval = repeatInterval;
    }


    /**
     * Gets the reportRequest value for this ScheduleRequest.
     * 
     * @return reportRequest
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest getReportRequest() {
        return reportRequest;
    }


    /**
     * Sets the reportRequest value for this ScheduleRequest.
     * 
     * @param reportRequest
     */
    public void setReportRequest(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest reportRequest) {
        this.reportRequest = reportRequest;
    }


    /**
     * Gets the saveDataOption value for this ScheduleRequest.
     * 
     * @return saveDataOption
     */
    public boolean isSaveDataOption() {
        return saveDataOption;
    }


    /**
     * Sets the saveDataOption value for this ScheduleRequest.
     * 
     * @param saveDataOption
     */
    public void setSaveDataOption(boolean saveDataOption) {
        this.saveDataOption = saveDataOption;
    }


    /**
     * Gets the saveOutputOption value for this ScheduleRequest.
     * 
     * @return saveOutputOption
     */
    public boolean isSaveOutputOption() {
        return saveOutputOption;
    }


    /**
     * Sets the saveOutputOption value for this ScheduleRequest.
     * 
     * @param saveOutputOption
     */
    public void setSaveOutputOption(boolean saveOutputOption) {
        this.saveOutputOption = saveOutputOption;
    }


    /**
     * Gets the scheduleBurstringOption value for this ScheduleRequest.
     * 
     * @return scheduleBurstringOption
     */
    public boolean isScheduleBurstringOption() {
        return scheduleBurstringOption;
    }


    /**
     * Sets the scheduleBurstringOption value for this ScheduleRequest.
     * 
     * @param scheduleBurstringOption
     */
    public void setScheduleBurstringOption(boolean scheduleBurstringOption) {
        this.scheduleBurstringOption = scheduleBurstringOption;
    }


    /**
     * Gets the schedulePublicOption value for this ScheduleRequest.
     * 
     * @return schedulePublicOption
     */
    public boolean isSchedulePublicOption() {
        return schedulePublicOption;
    }


    /**
     * Sets the schedulePublicOption value for this ScheduleRequest.
     * 
     * @param schedulePublicOption
     */
    public void setSchedulePublicOption(boolean schedulePublicOption) {
        this.schedulePublicOption = schedulePublicOption;
    }


    /**
     * Gets the startDate value for this ScheduleRequest.
     * 
     * @return startDate
     */
    public java.util.Calendar getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this ScheduleRequest.
     * 
     * @param startDate
     */
    public void setStartDate(java.util.Calendar startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the useUTF8Option value for this ScheduleRequest.
     * 
     * @return useUTF8Option
     */
    public boolean isUseUTF8Option() {
        return useUTF8Option;
    }


    /**
     * Sets the useUTF8Option value for this ScheduleRequest.
     * 
     * @param useUTF8Option
     */
    public void setUseUTF8Option(boolean useUTF8Option) {
        this.useUTF8Option = useUTF8Option;
    }


    /**
     * Gets the userJobName value for this ScheduleRequest.
     * 
     * @return userJobName
     */
    public java.lang.String getUserJobName() {
        return userJobName;
    }


    /**
     * Sets the userJobName value for this ScheduleRequest.
     * 
     * @param userJobName
     */
    public void setUserJobName(java.lang.String userJobName) {
        this.userJobName = userJobName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ScheduleRequest)) return false;
        ScheduleRequest other = (ScheduleRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cronExpression==null && other.getCronExpression()==null) || 
             (this.cronExpression!=null &&
              this.cronExpression.equals(other.getCronExpression()))) &&
            ((this.deliveryRequest==null && other.getDeliveryRequest()==null) || 
             (this.deliveryRequest!=null &&
              this.deliveryRequest.equals(other.getDeliveryRequest()))) &&
            ((this.endDate==null && other.getEndDate()==null) || 
             (this.endDate!=null &&
              this.endDate.equals(other.getEndDate()))) &&
            ((this.jobCalendar==null && other.getJobCalendar()==null) || 
             (this.jobCalendar!=null &&
              this.jobCalendar.equals(other.getJobCalendar()))) &&
            ((this.jobLocale==null && other.getJobLocale()==null) || 
             (this.jobLocale!=null &&
              this.jobLocale.equals(other.getJobLocale()))) &&
            ((this.jobTZ==null && other.getJobTZ()==null) || 
             (this.jobTZ!=null &&
              this.jobTZ.equals(other.getJobTZ()))) &&
            ((this.notificationTo==null && other.getNotificationTo()==null) || 
             (this.notificationTo!=null &&
              this.notificationTo.equals(other.getNotificationTo()))) &&
            this.notifyWhenFailed == other.isNotifyWhenFailed() &&
            this.notifyWhenSuccess == other.isNotifyWhenSuccess() &&
            this.notifyWhenWarning == other.isNotifyWhenWarning() &&
            this.repeatCount == other.getRepeatCount() &&
            this.repeatInterval == other.getRepeatInterval() &&
            ((this.reportRequest==null && other.getReportRequest()==null) || 
             (this.reportRequest!=null &&
              this.reportRequest.equals(other.getReportRequest()))) &&
            this.saveDataOption == other.isSaveDataOption() &&
            this.saveOutputOption == other.isSaveOutputOption() &&
            this.scheduleBurstringOption == other.isScheduleBurstringOption() &&
            this.schedulePublicOption == other.isSchedulePublicOption() &&
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            this.useUTF8Option == other.isUseUTF8Option() &&
            ((this.userJobName==null && other.getUserJobName()==null) || 
             (this.userJobName!=null &&
              this.userJobName.equals(other.getUserJobName())));
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
        if (getCronExpression() != null) {
            _hashCode += getCronExpression().hashCode();
        }
        if (getDeliveryRequest() != null) {
            _hashCode += getDeliveryRequest().hashCode();
        }
        if (getEndDate() != null) {
            _hashCode += getEndDate().hashCode();
        }
        if (getJobCalendar() != null) {
            _hashCode += getJobCalendar().hashCode();
        }
        if (getJobLocale() != null) {
            _hashCode += getJobLocale().hashCode();
        }
        if (getJobTZ() != null) {
            _hashCode += getJobTZ().hashCode();
        }
        if (getNotificationTo() != null) {
            _hashCode += getNotificationTo().hashCode();
        }
        _hashCode += (isNotifyWhenFailed() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isNotifyWhenSuccess() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isNotifyWhenWarning() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getRepeatCount();
        _hashCode += getRepeatInterval();
        if (getReportRequest() != null) {
            _hashCode += getReportRequest().hashCode();
        }
        _hashCode += (isSaveDataOption() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSaveOutputOption() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isScheduleBurstringOption() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSchedulePublicOption() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        _hashCode += (isUseUTF8Option() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getUserJobName() != null) {
            _hashCode += getUserJobName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ScheduleRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ScheduleRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cronExpression");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "cronExpression"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deliveryRequest"));
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
        elemField.setFieldName("jobCalendar");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "jobCalendar"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobLocale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "jobLocale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jobTZ");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "jobTZ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notificationTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "notificationTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notifyWhenFailed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "notifyWhenFailed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notifyWhenSuccess");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "notifyWhenSuccess"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notifyWhenWarning");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "notifyWhenWarning"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repeatCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "repeatCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repeatInterval");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "repeatInterval"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportRequest"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saveDataOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "saveDataOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saveOutputOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "saveOutputOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scheduleBurstringOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduleBurstringOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedulePublicOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "schedulePublicOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "startDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useUTF8Option");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "useUTF8Option"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userJobName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userJobName"));
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
