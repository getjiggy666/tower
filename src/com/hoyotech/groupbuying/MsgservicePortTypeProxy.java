package com.hoyotech.groupbuying;

public class MsgservicePortTypeProxy implements com.hoyotech.groupbuying.MsgservicePortType {
  private String _endpoint = null;
  private com.hoyotech.groupbuying.MsgservicePortType msgservicePortType = null;
  
  public MsgservicePortTypeProxy() {
    _initMsgservicePortTypeProxy();
  }
  
  public MsgservicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initMsgservicePortTypeProxy();
  }
  
  private void _initMsgservicePortTypeProxy() {
    try {
      msgservicePortType = (new com.hoyotech.groupbuying.MsgserviceLocator()).getmsgserviceHttpSoap11Endpoint();
      if (msgservicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)msgservicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)msgservicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (msgservicePortType != null)
      ((javax.xml.rpc.Stub)msgservicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.hoyotech.groupbuying.MsgservicePortType getMsgservicePortType() {
    if (msgservicePortType == null)
      _initMsgservicePortTypeProxy();
    return msgservicePortType;
  }
  
  public java.lang.String queryMsgStatus(java.lang.String msgId, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (msgservicePortType == null)
      _initMsgservicePortTypeProxy();
    return msgservicePortType.queryMsgStatus(msgId, accessCode);
  }
  
  public java.lang.String sendMsg(java.lang.String xmlDoc, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (msgservicePortType == null)
      _initMsgservicePortTypeProxy();
    return msgservicePortType.sendMsg(xmlDoc, accessCode);
  }
  
  public java.lang.String checkNumberPrefix(java.lang.String xmlDoc, java.lang.String accessCode) throws java.rmi.RemoteException{
    if (msgservicePortType == null)
      _initMsgservicePortTypeProxy();
    return msgservicePortType.checkNumberPrefix(xmlDoc, accessCode);
  }
  
  
}