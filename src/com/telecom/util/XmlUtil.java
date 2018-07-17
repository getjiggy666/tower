package com.telecom.util;

import java.io.StringWriter;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XmlUtil {
	/**
	 * 发送短信返回信息
	 * 
	 * @param xmlDoc
	 * @return
	 */
	public static HashMap<String, String> sendMsgResponse(String xmlDoc) {
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();
			map.put("returnCode", root.element("returnCode").getText());
			map.put("msgId", root.element("msgId").getText());
		} catch (DocumentException e) {
			System.out.println(xmlDoc);
			e.printStackTrace();
		}

		return map;
	}

	/**
	 * 写入短信发送xml
	 * @param id 短信标识
	 * @param accNbr 号码
	 * @param msgText 短信内容
	 * @return xml
	 */
	public static String getSMSXml(String id, String accNbr, String msgText){
		String xml = "";
		try {
			Document doc = DocumentHelper.createDocument();
			doc.setXMLEncoding("UTF-8");
			Element info = doc.addElement("info");
			XMLWriter xw;
			StringWriter sw = new StringWriter();
			OutputFormat opf;
			
			Element item = info.addElement("item");
			item.addElement("id").setText(id);
			item.addElement("accNbr").setText(accNbr);
			item.addElement("msgText").setText(msgText);
			
			opf = OutputFormat.createCompactFormat();
			opf.setEncoding("utf-8");
			opf.setIndent(true);
			opf.setIndent("    ");
			opf.setNewlines(true);
			xw = new XMLWriter(sw, opf);
			xw.write(doc);
			xw.close();
			xml = sw.toString();
			sw.close();
			return xml;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	/**
	 * 获取短信发送返回信息
	 * @param xmlDoc
	 * @return
	 */
	public static HashMap<String, String> fluxQureySMSResponse(String xmlDoc){
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();
			Element item = root.element("item");
			map.put("id", item.element("id").getText());
			map.put("msgId", item.element("msgId").getText());
			map.put("statusCode", item.element("statusCode").getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 获取短信真实状态
	 * @param xmlDoc 
	 * @return msgId - 短信ID, statusCode - 短信发送状态, errorCode - 错误码
	 */
	public static HashMap<String, String> fluxQureyRealSMSResponse(String xmlDoc){
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc);
			Element root = doc.getRootElement();
			map.put("msgId", root.element("msgId").getText());
			map.put("statusCode", root.element("statusCode").getText());
			map.put("errorCode", root.element("errorCode").getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	

}