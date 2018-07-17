package com.hoyotech.groupbuying;

import java.rmi.RemoteException;

public class OrderservicePortTypeProxy implements com.hoyotech.groupbuying.OrderservicePortType {
  private String _endpoint = null;
  private com.hoyotech.groupbuying.OrderservicePortType orderservicePortType = null;
  
  public OrderservicePortTypeProxy() {
    _initOrderservicePortTypeProxy();
  }
  
  public OrderservicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initOrderservicePortTypeProxy();
  }
  
  private void _initOrderservicePortTypeProxy() {
    try {
      orderservicePortType = (new com.hoyotech.groupbuying.OrderserviceLocator()).getorderserviceHttpSoap11Endpoint();
      if (orderservicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)orderservicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)orderservicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (orderservicePortType != null)
      ((javax.xml.rpc.Stub)orderservicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.hoyotech.groupbuying.OrderservicePortType getOrderservicePortType() {
    if (orderservicePortType == null)
      _initOrderservicePortTypeProxy();
    return orderservicePortType;
  }

	public String order(String accNbr, String authcode, String accessCode,
			String productCode) throws RemoteException {
	    if (orderservicePortType == null)
		      _initOrderservicePortTypeProxy();
		    return orderservicePortType.order(accNbr, authcode, accessCode, productCode);
	}

	public String getAuthcode(String accNbr, String accessCode)
			throws RemoteException {
	    if (orderservicePortType == null)
	    	_initOrderservicePortTypeProxy();
		    return orderservicePortType.getAuthcode(accNbr, accessCode);
	}
	
	public String queryOrder(String orderId, String accessCode)
			throws RemoteException {
	    if (orderservicePortType == null)
	    	_initOrderservicePortTypeProxy();
		    return orderservicePortType.queryOrder(orderId, accessCode);
	}
	
	public String getUserInfo(String accNbr, String accessCode)
			throws RemoteException {
	    if (orderservicePortType == null)
	    	_initOrderservicePortTypeProxy();
		    return orderservicePortType.getUserInfo(accNbr, accessCode);
	}
  
  
}