/**
 * OrderserviceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.hoyotech.groupbuying;

public class OrderserviceLocator extends org.apache.axis.client.Service implements com.hoyotech.groupbuying.Orderservice {

    public OrderserviceLocator() {
    }


    public OrderserviceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public OrderserviceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for orderserviceHttpSoap11Endpoint
    private java.lang.String orderserviceHttpSoap11Endpoint_address = "http://58.53.197.139/services/orderservice.orderserviceHttpSoap11Endpoint/";

    public java.lang.String getorderserviceHttpSoap11EndpointAddress() {
        return orderserviceHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String orderserviceHttpSoap11EndpointWSDDServiceName = "orderserviceHttpSoap11Endpoint";

    public java.lang.String getorderserviceHttpSoap11EndpointWSDDServiceName() {
        return orderserviceHttpSoap11EndpointWSDDServiceName;
    }

    public void setorderserviceHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        orderserviceHttpSoap11EndpointWSDDServiceName = name;
    }

    public com.hoyotech.groupbuying.OrderservicePortType getorderserviceHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(orderserviceHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getorderserviceHttpSoap11Endpoint(endpoint);
    }

    public com.hoyotech.groupbuying.OrderservicePortType getorderserviceHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.hoyotech.groupbuying.OrderserviceSoap11BindingStub _stub = new com.hoyotech.groupbuying.OrderserviceSoap11BindingStub(portAddress, this);
            _stub.setPortName(getorderserviceHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setorderserviceHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        orderserviceHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.hoyotech.groupbuying.OrderservicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.hoyotech.groupbuying.OrderserviceSoap11BindingStub _stub = new com.hoyotech.groupbuying.OrderserviceSoap11BindingStub(new java.net.URL(orderserviceHttpSoap11Endpoint_address), this);
                _stub.setPortName(getorderserviceHttpSoap11EndpointWSDDServiceName());
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
        if ("orderserviceHttpSoap11Endpoint".equals(inputPortName)) {
            return getorderserviceHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://groupbuying.hoyotech.com", "orderservice");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://groupbuying.hoyotech.com", "orderserviceHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("orderserviceHttpSoap11Endpoint".equals(portName)) {
            setorderserviceHttpSoap11EndpointEndpointAddress(address);
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
