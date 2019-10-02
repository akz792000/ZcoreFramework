/**
 * ReportDefinition.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class ReportDefinition  implements java.io.Serializable {
    private boolean autoRun;

    private boolean cacheDocument;

    private java.lang.String defaultOutputFormat;

    private java.lang.String defaultTemplateId;

    private boolean diagnostics;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatsLabelValues[] listOfTemplateFormatsLabelValues;

    private boolean onLine;

    private boolean openLinkInNewWindow;

    private int parameterColumns;

    private java.lang.String[] parameterNames;

    private java.lang.String reportDefnTitle;

    private java.lang.String reportDescription;

    private java.lang.String reportName;

    private org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] reportParameterNameValues;

    private java.lang.String reportType;

    private boolean showControls;

    private boolean showReportLinks;

    private java.lang.String[] templateIds;

    private boolean useExcelProcessor;

    public ReportDefinition() {
    }

    public ReportDefinition(
           boolean autoRun,
           boolean cacheDocument,
           java.lang.String defaultOutputFormat,
           java.lang.String defaultTemplateId,
           boolean diagnostics,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatsLabelValues[] listOfTemplateFormatsLabelValues,
           boolean onLine,
           boolean openLinkInNewWindow,
           int parameterColumns,
           java.lang.String[] parameterNames,
           java.lang.String reportDefnTitle,
           java.lang.String reportDescription,
           java.lang.String reportName,
           org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] reportParameterNameValues,
           java.lang.String reportType,
           boolean showControls,
           boolean showReportLinks,
           java.lang.String[] templateIds,
           boolean useExcelProcessor) {
           this.autoRun = autoRun;
           this.cacheDocument = cacheDocument;
           this.defaultOutputFormat = defaultOutputFormat;
           this.defaultTemplateId = defaultTemplateId;
           this.diagnostics = diagnostics;
           this.listOfTemplateFormatsLabelValues = listOfTemplateFormatsLabelValues;
           this.onLine = onLine;
           this.openLinkInNewWindow = openLinkInNewWindow;
           this.parameterColumns = parameterColumns;
           this.parameterNames = parameterNames;
           this.reportDefnTitle = reportDefnTitle;
           this.reportDescription = reportDescription;
           this.reportName = reportName;
           this.reportParameterNameValues = reportParameterNameValues;
           this.reportType = reportType;
           this.showControls = showControls;
           this.showReportLinks = showReportLinks;
           this.templateIds = templateIds;
           this.useExcelProcessor = useExcelProcessor;
    }


    /**
     * Gets the autoRun value for this ReportDefinition.
     * 
     * @return autoRun
     */
    public boolean isAutoRun() {
        return autoRun;
    }


    /**
     * Sets the autoRun value for this ReportDefinition.
     * 
     * @param autoRun
     */
    public void setAutoRun(boolean autoRun) {
        this.autoRun = autoRun;
    }


    /**
     * Gets the cacheDocument value for this ReportDefinition.
     * 
     * @return cacheDocument
     */
    public boolean isCacheDocument() {
        return cacheDocument;
    }


    /**
     * Sets the cacheDocument value for this ReportDefinition.
     * 
     * @param cacheDocument
     */
    public void setCacheDocument(boolean cacheDocument) {
        this.cacheDocument = cacheDocument;
    }


    /**
     * Gets the defaultOutputFormat value for this ReportDefinition.
     * 
     * @return defaultOutputFormat
     */
    public java.lang.String getDefaultOutputFormat() {
        return defaultOutputFormat;
    }


    /**
     * Sets the defaultOutputFormat value for this ReportDefinition.
     * 
     * @param defaultOutputFormat
     */
    public void setDefaultOutputFormat(java.lang.String defaultOutputFormat) {
        this.defaultOutputFormat = defaultOutputFormat;
    }


    /**
     * Gets the defaultTemplateId value for this ReportDefinition.
     * 
     * @return defaultTemplateId
     */
    public java.lang.String getDefaultTemplateId() {
        return defaultTemplateId;
    }


    /**
     * Sets the defaultTemplateId value for this ReportDefinition.
     * 
     * @param defaultTemplateId
     */
    public void setDefaultTemplateId(java.lang.String defaultTemplateId) {
        this.defaultTemplateId = defaultTemplateId;
    }


    /**
     * Gets the diagnostics value for this ReportDefinition.
     * 
     * @return diagnostics
     */
    public boolean isDiagnostics() {
        return diagnostics;
    }


    /**
     * Sets the diagnostics value for this ReportDefinition.
     * 
     * @param diagnostics
     */
    public void setDiagnostics(boolean diagnostics) {
        this.diagnostics = diagnostics;
    }


    /**
     * Gets the listOfTemplateFormatsLabelValues value for this ReportDefinition.
     * 
     * @return listOfTemplateFormatsLabelValues
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatsLabelValues[] getListOfTemplateFormatsLabelValues() {
        return listOfTemplateFormatsLabelValues;
    }


    /**
     * Sets the listOfTemplateFormatsLabelValues value for this ReportDefinition.
     * 
     * @param listOfTemplateFormatsLabelValues
     */
    public void setListOfTemplateFormatsLabelValues(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatsLabelValues[] listOfTemplateFormatsLabelValues) {
        this.listOfTemplateFormatsLabelValues = listOfTemplateFormatsLabelValues;
    }


    /**
     * Gets the onLine value for this ReportDefinition.
     * 
     * @return onLine
     */
    public boolean isOnLine() {
        return onLine;
    }


    /**
     * Sets the onLine value for this ReportDefinition.
     * 
     * @param onLine
     */
    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }


    /**
     * Gets the openLinkInNewWindow value for this ReportDefinition.
     * 
     * @return openLinkInNewWindow
     */
    public boolean isOpenLinkInNewWindow() {
        return openLinkInNewWindow;
    }


    /**
     * Sets the openLinkInNewWindow value for this ReportDefinition.
     * 
     * @param openLinkInNewWindow
     */
    public void setOpenLinkInNewWindow(boolean openLinkInNewWindow) {
        this.openLinkInNewWindow = openLinkInNewWindow;
    }


    /**
     * Gets the parameterColumns value for this ReportDefinition.
     * 
     * @return parameterColumns
     */
    public int getParameterColumns() {
        return parameterColumns;
    }


    /**
     * Sets the parameterColumns value for this ReportDefinition.
     * 
     * @param parameterColumns
     */
    public void setParameterColumns(int parameterColumns) {
        this.parameterColumns = parameterColumns;
    }


    /**
     * Gets the parameterNames value for this ReportDefinition.
     * 
     * @return parameterNames
     */
    public java.lang.String[] getParameterNames() {
        return parameterNames;
    }


    /**
     * Sets the parameterNames value for this ReportDefinition.
     * 
     * @param parameterNames
     */
    public void setParameterNames(java.lang.String[] parameterNames) {
        this.parameterNames = parameterNames;
    }


    /**
     * Gets the reportDefnTitle value for this ReportDefinition.
     * 
     * @return reportDefnTitle
     */
    public java.lang.String getReportDefnTitle() {
        return reportDefnTitle;
    }


    /**
     * Sets the reportDefnTitle value for this ReportDefinition.
     * 
     * @param reportDefnTitle
     */
    public void setReportDefnTitle(java.lang.String reportDefnTitle) {
        this.reportDefnTitle = reportDefnTitle;
    }


    /**
     * Gets the reportDescription value for this ReportDefinition.
     * 
     * @return reportDescription
     */
    public java.lang.String getReportDescription() {
        return reportDescription;
    }


    /**
     * Sets the reportDescription value for this ReportDefinition.
     * 
     * @param reportDescription
     */
    public void setReportDescription(java.lang.String reportDescription) {
        this.reportDescription = reportDescription;
    }


    /**
     * Gets the reportName value for this ReportDefinition.
     * 
     * @return reportName
     */
    public java.lang.String getReportName() {
        return reportName;
    }


    /**
     * Sets the reportName value for this ReportDefinition.
     * 
     * @param reportName
     */
    public void setReportName(java.lang.String reportName) {
        this.reportName = reportName;
    }


    /**
     * Gets the reportParameterNameValues value for this ReportDefinition.
     * 
     * @return reportParameterNameValues
     */
    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] getReportParameterNameValues() {
        return reportParameterNameValues;
    }


    /**
     * Sets the reportParameterNameValues value for this ReportDefinition.
     * 
     * @param reportParameterNameValues
     */
    public void setReportParameterNameValues(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] reportParameterNameValues) {
        this.reportParameterNameValues = reportParameterNameValues;
    }


    /**
     * Gets the reportType value for this ReportDefinition.
     * 
     * @return reportType
     */
    public java.lang.String getReportType() {
        return reportType;
    }


    /**
     * Sets the reportType value for this ReportDefinition.
     * 
     * @param reportType
     */
    public void setReportType(java.lang.String reportType) {
        this.reportType = reportType;
    }


    /**
     * Gets the showControls value for this ReportDefinition.
     * 
     * @return showControls
     */
    public boolean isShowControls() {
        return showControls;
    }


    /**
     * Sets the showControls value for this ReportDefinition.
     * 
     * @param showControls
     */
    public void setShowControls(boolean showControls) {
        this.showControls = showControls;
    }


    /**
     * Gets the showReportLinks value for this ReportDefinition.
     * 
     * @return showReportLinks
     */
    public boolean isShowReportLinks() {
        return showReportLinks;
    }


    /**
     * Sets the showReportLinks value for this ReportDefinition.
     * 
     * @param showReportLinks
     */
    public void setShowReportLinks(boolean showReportLinks) {
        this.showReportLinks = showReportLinks;
    }


    /**
     * Gets the templateIds value for this ReportDefinition.
     * 
     * @return templateIds
     */
    public java.lang.String[] getTemplateIds() {
        return templateIds;
    }


    /**
     * Sets the templateIds value for this ReportDefinition.
     * 
     * @param templateIds
     */
    public void setTemplateIds(java.lang.String[] templateIds) {
        this.templateIds = templateIds;
    }


    /**
     * Gets the useExcelProcessor value for this ReportDefinition.
     * 
     * @return useExcelProcessor
     */
    public boolean isUseExcelProcessor() {
        return useExcelProcessor;
    }


    /**
     * Sets the useExcelProcessor value for this ReportDefinition.
     * 
     * @param useExcelProcessor
     */
    public void setUseExcelProcessor(boolean useExcelProcessor) {
        this.useExcelProcessor = useExcelProcessor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReportDefinition)) return false;
        ReportDefinition other = (ReportDefinition) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.autoRun == other.isAutoRun() &&
            this.cacheDocument == other.isCacheDocument() &&
            ((this.defaultOutputFormat==null && other.getDefaultOutputFormat()==null) || 
             (this.defaultOutputFormat!=null &&
              this.defaultOutputFormat.equals(other.getDefaultOutputFormat()))) &&
            ((this.defaultTemplateId==null && other.getDefaultTemplateId()==null) || 
             (this.defaultTemplateId!=null &&
              this.defaultTemplateId.equals(other.getDefaultTemplateId()))) &&
            this.diagnostics == other.isDiagnostics() &&
            ((this.listOfTemplateFormatsLabelValues==null && other.getListOfTemplateFormatsLabelValues()==null) || 
             (this.listOfTemplateFormatsLabelValues!=null &&
              java.util.Arrays.equals(this.listOfTemplateFormatsLabelValues, other.getListOfTemplateFormatsLabelValues()))) &&
            this.onLine == other.isOnLine() &&
            this.openLinkInNewWindow == other.isOpenLinkInNewWindow() &&
            this.parameterColumns == other.getParameterColumns() &&
            ((this.parameterNames==null && other.getParameterNames()==null) || 
             (this.parameterNames!=null &&
              java.util.Arrays.equals(this.parameterNames, other.getParameterNames()))) &&
            ((this.reportDefnTitle==null && other.getReportDefnTitle()==null) || 
             (this.reportDefnTitle!=null &&
              this.reportDefnTitle.equals(other.getReportDefnTitle()))) &&
            ((this.reportDescription==null && other.getReportDescription()==null) || 
             (this.reportDescription!=null &&
              this.reportDescription.equals(other.getReportDescription()))) &&
            ((this.reportName==null && other.getReportName()==null) || 
             (this.reportName!=null &&
              this.reportName.equals(other.getReportName()))) &&
            ((this.reportParameterNameValues==null && other.getReportParameterNameValues()==null) || 
             (this.reportParameterNameValues!=null &&
              java.util.Arrays.equals(this.reportParameterNameValues, other.getReportParameterNameValues()))) &&
            ((this.reportType==null && other.getReportType()==null) || 
             (this.reportType!=null &&
              this.reportType.equals(other.getReportType()))) &&
            this.showControls == other.isShowControls() &&
            this.showReportLinks == other.isShowReportLinks() &&
            ((this.templateIds==null && other.getTemplateIds()==null) || 
             (this.templateIds!=null &&
              java.util.Arrays.equals(this.templateIds, other.getTemplateIds()))) &&
            this.useExcelProcessor == other.isUseExcelProcessor();
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
        _hashCode += (isAutoRun() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isCacheDocument() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDefaultOutputFormat() != null) {
            _hashCode += getDefaultOutputFormat().hashCode();
        }
        if (getDefaultTemplateId() != null) {
            _hashCode += getDefaultTemplateId().hashCode();
        }
        _hashCode += (isDiagnostics() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getListOfTemplateFormatsLabelValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListOfTemplateFormatsLabelValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListOfTemplateFormatsLabelValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += (isOnLine() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isOpenLinkInNewWindow() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getParameterColumns();
        if (getParameterNames() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParameterNames());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParameterNames(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReportDefnTitle() != null) {
            _hashCode += getReportDefnTitle().hashCode();
        }
        if (getReportDescription() != null) {
            _hashCode += getReportDescription().hashCode();
        }
        if (getReportName() != null) {
            _hashCode += getReportName().hashCode();
        }
        if (getReportParameterNameValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReportParameterNameValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReportParameterNameValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReportType() != null) {
            _hashCode += getReportType().hashCode();
        }
        _hashCode += (isShowControls() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isShowReportLinks() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTemplateIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTemplateIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTemplateIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += (isUseExcelProcessor() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReportDefinition.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportDefinition"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoRun");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "autoRun"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cacheDocument");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "cacheDocument"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultOutputFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "defaultOutputFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultTemplateId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "defaultTemplateId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diagnostics");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "diagnostics"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listOfTemplateFormatsLabelValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "listOfTemplateFormatsLabelValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "TemplateFormatsLabelValues"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("onLine");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "onLine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("openLinkInNewWindow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "openLinkInNewWindow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterColumns");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "parameterColumns"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parameterNames");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "parameterNames"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportDefnTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportDefnTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportDescription"));
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
        elemField.setFieldName("reportParameterNameValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportParameterNameValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ParamNameValue"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("showControls");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "showControls"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("showReportLinks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "showReportLinks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("templateIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useExcelProcessor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "useExcelProcessor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
