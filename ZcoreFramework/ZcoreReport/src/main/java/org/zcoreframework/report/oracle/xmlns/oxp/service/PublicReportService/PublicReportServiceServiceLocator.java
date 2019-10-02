/**
 * PublicReportServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService;

public class PublicReportServiceServiceLocator extends org.apache.axis.client.Service implements org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportServiceService {

	public PublicReportServiceServiceLocator() {
		
	}
	
    public PublicReportServiceServiceLocator(String PublicReportService_address) {
    	this.PublicReportService_address = PublicReportService_address;
    }

    public PublicReportServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PublicReportServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PublicReportService
    private java.lang.String PublicReportService_address;

    public java.lang.String getPublicReportServiceAddress() {
        return PublicReportService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PublicReportServiceWSDDServiceName = "PublicReportService";

    public java.lang.String getPublicReportServiceWSDDServiceName() {
        return PublicReportServiceWSDDServiceName;
    }

    public void setPublicReportServiceWSDDServiceName(java.lang.String name) {
        PublicReportServiceWSDDServiceName = name;
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportService getPublicReportService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PublicReportService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPublicReportService(endpoint);
    }

    public org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportService getPublicReportService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportServiceSoapBindingStub _stub = new org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getPublicReportServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPublicReportServiceEndpointAddress(java.lang.String address) {
        PublicReportService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportService.class.isAssignableFrom(serviceEndpointInterface)) {
                org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportServiceSoapBindingStub _stub = new org.zcoreframework.report.oracle.xmlns.oxp.service.PublicReportService.PublicReportServiceSoapBindingStub(new java.net.URL(PublicReportService_address), this);
                _stub.setPortName(getPublicReportServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PublicReportService".equals(inputPortName)) {
            return getPublicReportService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "PublicReportServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://xmlns.oracle.com/oxp/service/PublicReportService", "PublicReportService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PublicReportService".equals(portName)) {
            setPublicReportServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
