package com.telecom.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.opensymphony.oscache.util.StringUtil;

public class CallServiceUtil {
	/**
	 * 调用WebService通用方法
	 * @param uploadStr 
	 * 				输入参数(XML类型)
	 * @param urlStr 
	 * 				调用地址Url
	 * @param methodName
	 * 				调用方法名Name 
	 * @return strReturn
	 * 				返回结果(XML类型)
	 */
	public static String callService(String uploadStr, String urlStr,
			String methodName , String namespaceName) {
		// 定义返回结果
		String strReturn = null;
		try {
			// 创建url，service，call等对象以便调用
			URL url = new URL(urlStr);
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName(namespaceName,methodName));
			// 调用指定方法名的webservice，并将返回值赋值于返回结果str中
			strReturn = (String) call.invoke(new Object[] { uploadStr });
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//strReturn = e.getMessage();
		} catch (RemoteException e) {
			e.printStackTrace();
			//strReturn = e.getMessage();
		} catch (ServiceException e) {
			e.printStackTrace();
			//strReturn = e.getMessage();
		}
		return strReturn;
	}
	
	/**
	 * 调用WebService通用方法(多参数重载)
	 * @param param 
	 * 				多参数obj数组---与调用的方法所需参数顺序依次对应
	 * @param urlStr 
	 * 				调用地址Url
	 * @param methodName
	 * 				调用方法名Name 
	 * @return strReturn
	 * 				返回结果(XML类型)
	 */
	public static String callService(Object[] param, String urlStr,
			String methodName, String namespaceName) {
		// 定义返回结果
		String strReturn = null;
		try {
			// 创建url，service，call等对象以便调用
			URL url = new URL(urlStr);
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			if(StringUtil.isEmpty(namespaceName)){
				call.setOperationName(methodName);
			}
			else{
				call.setOperationName(new QName(namespaceName,methodName));
			}
			// 调用指定方法名的webservice，并将返回值赋值于返回结果str中
			strReturn = (String) call.invoke(param);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			//strReturn = e.getMessage();
		} catch (RemoteException e) {
			e.printStackTrace();
			//strReturn = e.getMessage();
		} catch (ServiceException e) {
			e.printStackTrace();
			//strReturn = e.getMessage();
		}
		return strReturn;
	}
	
}
