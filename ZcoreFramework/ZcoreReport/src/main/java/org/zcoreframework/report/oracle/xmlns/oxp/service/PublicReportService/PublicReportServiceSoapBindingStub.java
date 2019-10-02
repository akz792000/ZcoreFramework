/**
 * PublicReportServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class PublicReportServiceSoapBindingStub extends org.apache.axis.client.Stub implements org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[58];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
        _initOperationDesc6();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("logout");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "logoutReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("impersonate");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "adminUsername"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "adminPassword"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "username"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "impersonateReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReportParameters");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportRequest"), org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ParamNameValue"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getReportParametersReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePathURL"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateFileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "XLIFFFileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "XLIFFData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "updateFlag"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "createReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validateLogin");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "validateLoginReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("login");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "loginReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getSecurityModel");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getSecurityModelReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getBIPHTTPSessionInterval");
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        oper.setReturnClass(int.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getBIPHTTPSessionIntervalReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("runReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportRequest"), org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportResponse"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "runReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("runReportInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportRequest"), org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportResponse"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "runReportInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReportDefinition");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportDefinition"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getReportDefinitionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReportDefinitionInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportDefinition"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getReportDefinitionInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getReportParametersInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportRequest"), org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ParamNameValue"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getReportParametersInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getFolderContents");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ItemData"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getFolderContentsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getFolderContentsInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ItemData"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getFolderContentsInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("scheduleReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduleRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ScheduleRequest"), org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ScheduleRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduleReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("scheduleReportInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduleRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ScheduleRequest"), org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ScheduleRequest.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduleReportInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("hasReportAccess");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "hasReportAccessReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("hasReportAccessInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "hasReportAccessInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isReportExist");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "isReportExistReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isReportExistInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "isReportExistInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isFolderExist");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "isFolderExistReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("isFolderExistInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "isFolderExistInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getScheduledReportStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobStatus"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportStatusReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getScheduledReportStatusInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobStatus"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportStatusInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getListOfScheduledReportsStatus");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobIDs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobStatus"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getListOfScheduledReportsStatusReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getListOfScheduledReportsStatusInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobIDs"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobStatus"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getListOfScheduledReportsStatusInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getScheduledReportInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "viewByFilter"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobInfo"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportInfoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getScheduledReportInfoInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "viewByFilter"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobInfo"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportInfoInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getScheduledReportHistoryInfo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "viewByFilter"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bDownloadReport"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobHistoryInfo"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportHistoryInfoReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getScheduledReportHistoryInfoInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "viewByFilter"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bDownloadReport"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobHistoryInfo"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportHistoryInfoInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("suspendScheduledReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "suspendScheduledReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("suspendScheduledReportInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "suspendScheduledReportInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("resumeScheduledReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "resumeScheduledReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("resumeScheduledReportInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "resumeScheduledReportInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteScheduledReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteScheduledReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteScheduledReportInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteScheduledReportInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteScheduledReportHistory");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteScheduledReportHistoryReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteScheduledReportHistoryInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduledJobID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteScheduledReportHistoryInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createReportInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePathURL"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateFileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "XLIFFFileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "XLIFFData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "updateFlag"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"), boolean.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "createReportInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateReportDefinition");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsPath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "newReportDefn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportDefinition"), org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "updateReportDefinitionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("updateReportDefinitionInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsPath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "newReportDefn"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportDefinition"), org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "updateReportDefinitionInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createReportFolder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "createReportFolderReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("createReportFolderInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "createReportFolderInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("uploadTemplateForReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateFileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "uploadTemplateForReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("uploadTemplateForReportInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateFileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "uploadTemplateForReportInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[46] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeTemplateForReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateFileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "removeTemplateForReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[47] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("removeTemplateForReportInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "templateFileName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "removeTemplateForReportInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[48] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("uploadReportDataChunk");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fileID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportDataChunk"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportRawDataChunk"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "uploadReportDataChunkReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[49] = oper;

    }

    private static void _initOperationDesc6(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("downloadReportDataChunk");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fileID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "beginIdx"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "size"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportDataChunk"));
        oper.setReturnClass(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDataChunk.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "downloadReportDataChunkReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[50] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("uploadReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePathURL"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportZippedData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "uploadReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[51] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("uploadReportInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePathURL"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportZippedData"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "uploadReportInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[52] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteReportInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteReportInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[53] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteFolder");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteFolderReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[54] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("deleteFolderInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "folderAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        oper.setReturnClass(boolean.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteFolderInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[55] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("downloadReport");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "userID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        oper.setReturnClass(byte[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "downloadReportReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[56] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("downloadReportInSession");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "reportAbsolutePath"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "bipSessionToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        oper.setReturnClass(byte[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "downloadReportInSessionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault1"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException"), 
                      true
                     ));
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "fault2"),
                      "com.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException",
                      new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException"), 
                      true
                     ));
        _operations[57] = oper;

    }

    public PublicReportServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public PublicReportServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public PublicReportServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "AccessDeniedException");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ArrayOfParamNameValue");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ParamNameValue");
            qName2 = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ArrayOfString");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ArrayOfTemplateFormatLabelValue");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatLabelValue[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "TemplateFormatLabelValue");
            qName2 = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ArrayOfTemplateFormatsLabelValues");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatsLabelValues[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "TemplateFormatsLabelValues");
            qName2 = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "DeliveryRequest");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.DeliveryRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "EMailDeliveryOption");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.EMailDeliveryOption.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "FaxDeliveryOption");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.FaxDeliveryOption.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "FTPDeliveryOption");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.FTPDeliveryOption.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "InvalidParametersException");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ItemData");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobHistoryInfo");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobInfo");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "JobStatus");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "LocalDeliveryOption");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.LocalDeliveryOption.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "OperationFailedException");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ParamNameValue");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "PrintDeliveryOption");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PrintDeliveryOption.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportDataChunk");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDataChunk.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportDefinition");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportRequest");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ReportResponse");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "ScheduleRequest");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ScheduleRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "TemplateFormatLabelValue");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatLabelValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "TemplateFormatsLabelValues");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.TemplateFormatsLabelValues.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "WebDAVDeliveryOption");
            cachedSerQNames.add(qName);
            cls = org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.WebDAVDeliveryOption.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public boolean logout(java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "logout"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String impersonate(java.lang.String adminUsername, java.lang.String adminPassword, java.lang.String username) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "impersonate"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {adminUsername, adminPassword, username});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] getReportParameters(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest reportRequest, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getReportParameters"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportRequest, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String createReport(java.lang.String reportName, java.lang.String folderAbsolutePathURL, java.lang.String templateFileName, byte[] templateData, java.lang.String XLIFFFileName, byte[] XLIFFData, boolean updateFlag, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "createReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportName, folderAbsolutePathURL, templateFileName, templateData, XLIFFFileName, XLIFFData, new java.lang.Boolean(updateFlag), userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean deleteReport(java.lang.String reportAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean validateLogin(java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "validateLogin"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String login(java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "login"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String getSecurityModel() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getSecurityModel"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public int getBIPHTTPSessionInterval() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getBIPHTTPSessionInterval"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Integer) _resp).intValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Integer) org.apache.axis.utils.JavaUtils.convert(_resp, int.class)).intValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse runReport(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest reportRequest, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "runReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportRequest, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse runReportInSession(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest reportRequest, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "runReportInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportRequest, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition getReportDefinition(java.lang.String reportAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getReportDefinition"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition getReportDefinitionInSession(java.lang.String reportAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getReportDefinitionInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[] getReportParametersInSession(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportRequest reportRequest, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getReportParametersInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportRequest, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ParamNameValue[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[] getFolderContents(java.lang.String folderAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getFolderContents"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {folderAbsolutePath, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[] getFolderContentsInSession(java.lang.String folderAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getFolderContentsInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {folderAbsolutePath, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ItemData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String scheduleReport(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ScheduleRequest scheduleRequest, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduleReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduleRequest, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String scheduleReportInSession(org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ScheduleRequest scheduleRequest, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "scheduleReportInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduleRequest, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean hasReportAccess(java.lang.String reportAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "hasReportAccess"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean hasReportAccessInSession(java.lang.String reportAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "hasReportAccessInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean isReportExist(java.lang.String reportAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "isReportExist"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean isReportExistInSession(java.lang.String reportAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "isReportExistInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean isFolderExist(java.lang.String folderAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "isFolderExist"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {folderAbsolutePath, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean isFolderExistInSession(java.lang.String folderAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "isFolderExistInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {folderAbsolutePath, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus getScheduledReportStatus(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus getScheduledReportStatusInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportStatusInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[] getListOfScheduledReportsStatus(java.lang.String[] scheduledJobIDs, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getListOfScheduledReportsStatus"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobIDs, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[] getListOfScheduledReportsStatusInSession(java.lang.String[] scheduledJobIDs, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getListOfScheduledReportsStatusInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobIDs, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobStatus[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[] getScheduledReportInfo(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password, java.lang.String viewByFilter) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, userID, password, viewByFilter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[] getScheduledReportInfoInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken, java.lang.String userID, java.lang.String viewByFilter) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportInfoInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, bipSessionToken, userID, viewByFilter});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobInfo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[] getScheduledReportHistoryInfo(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password, java.lang.String viewByFilter, boolean bDownloadReport) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportHistoryInfo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, userID, password, viewByFilter, new java.lang.Boolean(bDownloadReport)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[] getScheduledReportHistoryInfoInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken, java.lang.String userID, java.lang.String viewByFilter, boolean bDownloadReport) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "getScheduledReportHistoryInfoInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, bipSessionToken, userID, viewByFilter, new java.lang.Boolean(bDownloadReport)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.JobHistoryInfo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean suspendScheduledReport(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "suspendScheduledReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean suspendScheduledReportInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "suspendScheduledReportInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean resumeScheduledReport(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "resumeScheduledReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean resumeScheduledReportInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "resumeScheduledReportInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean deleteScheduledReport(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteScheduledReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean deleteScheduledReportInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteScheduledReportInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean deleteScheduledReportHistory(java.lang.String scheduledJobID, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteScheduledReportHistory"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean deleteScheduledReportHistoryInSession(java.lang.String scheduledJobID, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteScheduledReportHistoryInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {scheduledJobID, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String createReportInSession(java.lang.String reportName, java.lang.String folderAbsolutePathURL, java.lang.String templateFileName, byte[] templateData, java.lang.String XLIFFFileName, byte[] XLIFFData, boolean updateFlag, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "createReportInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportName, folderAbsolutePathURL, templateFileName, templateData, XLIFFFileName, XLIFFData, new java.lang.Boolean(updateFlag), bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean updateReportDefinition(java.lang.String reportAbsPath, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition newReportDefn, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "updateReportDefinition"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsPath, newReportDefn, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean updateReportDefinitionInSession(java.lang.String reportAbsPath, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDefinition newReportDefn, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "updateReportDefinitionInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsPath, newReportDefn, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String createReportFolder(java.lang.String folderAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "createReportFolder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {folderAbsolutePath, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String createReportFolderInSession(java.lang.String folderAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "createReportFolderInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {folderAbsolutePath, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean uploadTemplateForReport(java.lang.String reportAbsolutePath, java.lang.String templateFileName, byte[] templateData, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "uploadTemplateForReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, templateFileName, templateData, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean uploadTemplateForReportInSession(java.lang.String reportAbsolutePath, java.lang.String templateFileName, byte[] templateData, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "uploadTemplateForReportInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, templateFileName, templateData, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean removeTemplateForReport(java.lang.String reportAbsolutePath, java.lang.String templateFileName, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[47]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "removeTemplateForReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, templateFileName, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean removeTemplateForReportInSession(java.lang.String reportAbsolutePath, java.lang.String templateFileName, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[48]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "removeTemplateForReportInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, templateFileName, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String uploadReportDataChunk(java.lang.String fileID, byte[] reportDataChunk, java.lang.String reportRawDataChunk) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[49]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "uploadReportDataChunk"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fileID, reportDataChunk, reportRawDataChunk});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDataChunk downloadReportDataChunk(java.lang.String fileID, int beginIdx, int size) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[50]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "downloadReportDataChunk"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {fileID, new java.lang.Integer(beginIdx), new java.lang.Integer(size)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDataChunk) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDataChunk) org.apache.axis.utils.JavaUtils.convert(_resp, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.ReportDataChunk.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String uploadReport(java.lang.String reportName, java.lang.String folderAbsolutePathURL, byte[] reportZippedData, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[51]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "uploadReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportName, folderAbsolutePathURL, reportZippedData, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public java.lang.String uploadReportInSession(java.lang.String reportName, java.lang.String folderAbsolutePathURL, byte[] reportZippedData, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[52]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "uploadReportInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportName, folderAbsolutePathURL, reportZippedData, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean deleteReportInSession(java.lang.String reportAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[53]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteReportInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean deleteFolder(java.lang.String folderAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[54]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteFolder"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {folderAbsolutePath, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public boolean deleteFolderInSession(java.lang.String folderAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[55]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "deleteFolderInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {folderAbsolutePath, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return ((java.lang.Boolean) _resp).booleanValue();
            } catch (java.lang.Exception _exception) {
                return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert(_resp, boolean.class)).booleanValue();
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public byte[] downloadReport(java.lang.String reportAbsolutePath, java.lang.String userID, java.lang.String password) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[56]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "downloadReport"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, userID, password});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (byte[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (byte[]) org.apache.axis.utils.JavaUtils.convert(_resp, byte[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public byte[] downloadReportInSession(java.lang.String reportAbsolutePath, java.lang.String bipSessionToken) throws java.rmi.RemoteException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException, org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[57]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "downloadReportInSession"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {reportAbsolutePath, bipSessionToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (byte[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (byte[]) org.apache.axis.utils.JavaUtils.convert(_resp, byte[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.InvalidParametersException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.AccessDeniedException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) {
              throw (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.OperationFailedException) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
