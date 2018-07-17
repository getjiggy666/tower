package com.hoyotech.groupbuying;

public class MyservicePortTypeProxy implements com.hoyotech.groupbuying.MyservicePortType {
  private String _endpoint = null;
  private com.hoyotech.groupbuying.MyservicePortType myservicePortType = null;
  
  public MyservicePortTypeProxy() {
    _initMyservicePortTypeProxy();
  }
  
  public MyservicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initMyservicePortTypeProxy();
  }
  
  private void _initMyservicePortTypeProxy() {
    try {
      myservicePortType = (new com.hoyotech.groupbuying.MyserviceLocator()).getmyserviceHttpSoap11Endpoint();
      if (myservicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)myservicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)myservicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (myservicePortType != null)
      ((javax.xml.rpc.Stub)myservicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.hoyotech.groupbuying.MyservicePortType getMyservicePortType() {
    if (myservicePortType == null)
      _initMyservicePortTypeProxy();
    return myservicePortType;
  }
  
  public java.lang.String order(java.lang.String accNbr, java.lang.String authcode, java.lang.String accessCode, java.lang.String productCode) throws java.rmi.RemoteException{
    if (myservicePortType == null)
      _initMyservicePortTypeProxy();
    return myservicePortType.order(accNbr, authcode, accessCode, productCode);
  }
  
  public java.lang.String getAuthcode(java.lang.String accNbr, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (myservicePortType == null)
      _initMyservicePortTypeProxy();
    return myservicePortType.getAuthcode(accNbr, accessCode);
  }
  
  public java.lang.String sendMsg(java.lang.String accNbr, java.lang.String msgText, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (myservicePortType == null)
      _initMyservicePortTypeProxy();
    return myservicePortType.sendMsg(accNbr, msgText, accessCode);
  }
  
  public java.lang.String checkAuthcode(java.lang.String accNbr, java.lang.String authcode, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (myservicePortType == null)
      _initMyservicePortTypeProxy();
    return myservicePortType.checkAuthcode(accNbr, authcode, accessCode);
  }
  
  public java.lang.String orderGroup(java.lang.String accNbr, java.lang.String authcode, java.lang.String accessCode, java.lang.String groupCode, java.lang.String msg) throws java.rmi.RemoteException{
    if (myservicePortType == null)
      _initMyservicePortTypeProxy();
    return myservicePortType.orderGroup(accNbr, authcode, accessCode, groupCode, msg);
  }
  
  public java.lang.String checkOrder(java.lang.String accNbr, java.lang.String productCode, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (myservicePortType == null)
      _initMyservicePortTypeProxy();
    return myservicePortType.checkOrder(accNbr, productCode, accessCode);
  }
  
  public java.lang.String share(java.lang.String fromNbr, java.lang.String authcode, java.lang.String toNbr, java.lang.String accessCode, java.lang.String productCode) throws java.rmi.RemoteException{
    if (myservicePortType == null)
      _initMyservicePortTypeProxy();
    return myservicePortType.share(fromNbr, authcode, toNbr, accessCode, productCode);
  }
  
  
}