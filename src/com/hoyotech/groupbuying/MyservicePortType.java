/**
 * MyservicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.hoyotech.groupbuying;

public interface MyservicePortType extends java.rmi.Remote {
    public java.lang.String order(java.lang.String accNbr, java.lang.String authcode, java.lang.String accessCode, java.lang.String productCode) throws java.rmi.RemoteException;
    public java.lang.String getAuthcode(java.lang.String accNbr, java.lang.String accessCode) throws java.rmi.RemoteException;
    public java.lang.String sendMsg(java.lang.String accNbr, java.lang.String msgText, java.lang.String accessCode) throws java.rmi.RemoteException;
    public java.lang.String checkAuthcode(java.lang.String accNbr, java.lang.String authcode, java.lang.String accessCode) throws java.rmi.RemoteException;
    public java.lang.String orderGroup(java.lang.String accNbr, java.lang.String authcode, java.lang.String accessCode, java.lang.String groupCode, java.lang.String msg) throws java.rmi.RemoteException;
    public java.lang.String checkOrder(java.lang.String accNbr, java.lang.String productCode, java.lang.String accessCode) throws java.rmi.RemoteException;
    public java.lang.String share(java.lang.String fromNbr, java.lang.String authcode, java.lang.String toNbr, java.lang.String accessCode, java.lang.String productCode) throws java.rmi.RemoteException;
}
