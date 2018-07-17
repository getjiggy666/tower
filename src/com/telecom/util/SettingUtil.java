package com.telecom.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.ClassPathResource;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import com.telecom.bean.Setting;

/**
 * 工具类 - 系统配置
 */

public class SettingUtil {

    private static final String CACHE_MANAGER_BEAN_NAME = "cacheManager";// cacheManager
									 // Bean名称
    private static final String DEMOPROJECT_XML_FILE_NAME = "config.xml";// config.xml配置文件名称
    private static final String SETTING_CACHE_KEY = "DEMOPROJECT_SETTING";// Setting缓存Key

    /**
     * 读取系统配置信息
     * 
     * @return Setting
     * 
     * @throws URISyntaxException
     * 
     * @throws DocumentException
     * 
     * @throws IOException
     */
    public static Setting readSetting() throws URISyntaxException,
	    DocumentException, IOException {
	File demoprojectXmlFile = new ClassPathResource(
		DEMOPROJECT_XML_FILE_NAME).getFile();
	SAXReader saxReader = new SAXReader();
	Document document = saxReader.read(demoprojectXmlFile);
	Node contextPathNode = document
		.selectSingleNode("/demoproject/setting/contextPath");
	Node adminLoginUrlNode = document
		.selectSingleNode("/demoproject/setting/adminLoginUrl");
	Node adminLoginProcessingUrlNode = document
		.selectSingleNode("/demoproject/setting/adminLoginProcessingUrl");
	Node imageUploadPathNode = document
		.selectSingleNode("/demoproject/setting/imageUploadPath");
	Node systemNameNode = document
		.selectSingleNode("/demoproject/setting/systemName");
	Node systemUrlNode = document
		.selectSingleNode("/demoproject/setting/systemUrl");
	Node currencySignNode = document
		.selectSingleNode("/demoproject/setting/currencySign");
	Node currencyUnitNode = document
		.selectSingleNode("/demoproject/setting/currencyUnit");
	Node isLoginFailureLockNode = document
		.selectSingleNode("/demoproject/setting/isLoginFailureLock");
	Node loginFailureLockCountNode = document
		.selectSingleNode("/demoproject/setting/loginFailureLockCount");
	Node loginFailureLockTimeNode = document
		.selectSingleNode("/demoproject/setting/loginFailureLockTime");
	Node smtpFromMailNode = document
		.selectSingleNode("/demoproject/setting/smtpFromMail");
	Node smtpFromMailNickNode = document
		.selectSingleNode("/demoproject/setting/smtpFromMailNick");
	Node smtpHostNode = document
		.selectSingleNode("/demoproject/setting/smtpHost");
	Node smtpPortNode = document
		.selectSingleNode("/demoproject/setting/smtpPort");
	Node smtpUsernameNode = document
		.selectSingleNode("/demoproject/setting/smtpUsername");
	Node smtpPasswordNode = document
		.selectSingleNode("/demoproject/setting/smtpPassword");
	Node isGzipEnabledNode = document
		.selectSingleNode("/demoproject/setting/isGzipEnabled");
	Node buildHtmlDelayTimeNode = document
		.selectSingleNode("/demoproject/setting/buildHtmlDelayTime");
	Node accessCodeNode = document
		.selectSingleNode("/demoproject/setting/accessCode");
	Node smsUrlNode = document
		.selectSingleNode("/demoproject/setting/smsUrl");
	Node authenCodeUrlNode = document
		.selectSingleNode("/demoproject/setting/authenCodeUrl");
	Node isUpdatingNode = document
		.selectSingleNode("/demoproject/setting/isUpdating");
	Node secretKeyNode = document
		.selectSingleNode("/demoproject/setting/secretKey");
	Node vectorNode = document
		.selectSingleNode("/demoproject/setting/vector");
	Node upFilePathNode = document
		.selectSingleNode("/demoproject/setting/upFilePath");

	Setting setting = new Setting();
	setting.setContextPath(contextPathNode.getText());
	setting.setAdminLoginUrl(adminLoginUrlNode.getText());
	setting.setAdminLoginProcessingUrl(adminLoginProcessingUrlNode
		.getText());
	setting.setImageUploadPath(imageUploadPathNode.getText());
	setting.setSystemName(systemNameNode.getText());
	setting.setSystemUrl(systemUrlNode.getText());
	setting.setCurrencySign(currencySignNode.getText());
	setting.setCurrencyUnit(currencyUnitNode.getText());
	setting.setIsLoginFailureLock(Boolean.valueOf(isLoginFailureLockNode
		.getText()));
	setting.setLoginFailureLockCount(Integer
		.valueOf(loginFailureLockCountNode.getText()));
	setting.setLoginFailureLockTime(Integer
		.valueOf(loginFailureLockTimeNode.getText()));
	setting.setSmtpFromMail(smtpFromMailNode.getText());
	setting.setSmtpFromMailNick(smtpFromMailNickNode.getText());
	setting.setSmtpHost(smtpHostNode.getText());
	setting.setSmtpPort(Integer.valueOf(smtpPortNode.getText()));
	setting.setSmtpUsername(smtpUsernameNode.getText());
	setting.setSmtpPassword(smtpPasswordNode.getText());
	setting.setIsGzipEnabled(Boolean.valueOf(isGzipEnabledNode.getText()));
	setting.setIsUpdating(Boolean.valueOf(isUpdatingNode.getText()));
	setting.setBuildHtmlDelayTime(Integer.parseInt(buildHtmlDelayTimeNode
		.getText()));
	setting.setAccessCode(accessCodeNode.getText());
	setting.setSmsUrl(smsUrlNode.getText());
	setting.setAuthenCodeUrl(authenCodeUrlNode.getText());
	setting.setSecretKey(secretKeyNode.getText());
	setting.setVector(vectorNode.getText());
	setting.setUpFilePath(upFilePathNode.getText());

	return setting;
    }

    /**
     * 获取系统配置信息
     * 
     * @return Setting
     */
    public static Setting getSetting() {
	Setting setting = null;
	GeneralCacheAdministrator generalCacheAdministrator = (GeneralCacheAdministrator) SpringUtil
		.getBean(CACHE_MANAGER_BEAN_NAME);
	try {
	    setting = (Setting) generalCacheAdministrator
		    .getFromCache(SETTING_CACHE_KEY);
	} catch (NeedsRefreshException needsRefreshException) {
	    boolean updateSucceeded = false;
	    try {
		setting = readSetting();
		generalCacheAdministrator
			.putInCache(SETTING_CACHE_KEY, setting);
		updateSucceeded = true;
	    } catch (Exception e) {
		e.printStackTrace();
	    } finally {
		if (!updateSucceeded) {
		    generalCacheAdministrator.cancelUpdate(SETTING_CACHE_KEY);
		}
	    }
	}
	return setting;
    }

    /**
     * 写入系统配置信息
     * 
     * @return Setting
     */
    public static void writeSetting(Setting setting) {
	File demoprojectXmlFile = null;
	Document document = null;
	try {
	    demoprojectXmlFile = new ClassPathResource(
		    DEMOPROJECT_XML_FILE_NAME).getFile();
	    SAXReader saxReader = new SAXReader();
	    document = saxReader.read(demoprojectXmlFile);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	Element rootElement = document.getRootElement();
	Element settingElement = rootElement.element("setting");
	Node systemNameNode = document
		.selectSingleNode("/demoproject/setting/systemName");
	Node systemUrlNode = document
		.selectSingleNode("/demoproject/setting/systemUrl");
	Node currencySignNode = document
		.selectSingleNode("/demoproject/setting/currencySign");
	Node currencyUnitNode = document
		.selectSingleNode("/demoproject/setting/currencyUnit");
	Node isLoginFailureLockNode = document
		.selectSingleNode("/demoproject/setting/isLoginFailureLock");
	Node loginFailureLockCountNode = document
		.selectSingleNode("/demoproject/setting/loginFailureLockCount");
	Node loginFailureLockTimeNode = document
		.selectSingleNode("/demoproject/setting/loginFailureLockTime");
	Node smtpFromMailNode = document
		.selectSingleNode("/demoproject/setting/smtpFromMail");
	Node smtpFromMailNickNode = document
		.selectSingleNode("/demoproject/setting/smtpFromMailNick");
	Node smtpHostNode = document
		.selectSingleNode("/demoproject/setting/smtpHost");
	Node smtpPortNode = document
		.selectSingleNode("/demoproject/setting/smtpPort");
	Node smtpUsernameNode = document
		.selectSingleNode("/demoproject/setting/smtpUsername");
	// Node smtpPasswordNode =
	// document.selectSingleNode("/demoproject/setting/smtpPassword");
	Node isGzipEnabledNode = document
		.selectSingleNode("/demoproject/setting/isGzipEnabled");
	Node isUpdatingNode = document
		.selectSingleNode("/demoproject/setting/isUpdating");

	if (systemNameNode == null) {
	    systemNameNode = settingElement.addElement("systemName");
	}
	if (systemUrlNode == null) {
	    systemUrlNode = settingElement.addElement("systemUrl");
	}
	if (currencySignNode == null) {
	    currencySignNode = settingElement.addElement("currencySign");
	}
	if (currencyUnitNode == null) {
	    currencyUnitNode = settingElement.addElement("currencyUnit");
	}
	if (isLoginFailureLockNode == null) {
	    isLoginFailureLockNode = settingElement
		    .addElement("isLoginFailureLock");
	}
	if (loginFailureLockCountNode == null) {
	    loginFailureLockCountNode = settingElement
		    .addElement("loginFailureLockCount");
	}
	if (loginFailureLockTimeNode == null) {
	    loginFailureLockTimeNode = settingElement
		    .addElement("loginFailureLockTime");
	}
	if (smtpFromMailNode == null) {
	    smtpFromMailNode = settingElement.addElement("smtpFromMail");
	}
	if (smtpFromMailNickNode == null) {
	    smtpFromMailNickNode = settingElement
		    .addElement("smtpFromMailNick");
	}
	if (smtpHostNode == null) {
	    smtpHostNode = settingElement.addElement("smtpHost");
	}
	if (smtpPortNode == null) {
	    smtpPortNode = settingElement.addElement("smtpPort");
	}
	if (smtpUsernameNode == null) {
	    smtpUsernameNode = settingElement.addElement("smtpUsername");
	}
	/*
	 * if(smtpPasswordNode == null){ smtpPasswordNode =
	 * settingElement.addElement("smtpPassword"); }
	 */
	if (isGzipEnabledNode == null) {
	    isGzipEnabledNode = settingElement.addElement("isGzipEnabled");
	}
	if (isUpdatingNode == null) {
	    isUpdatingNode = settingElement.addElement("isUpdating");
	}

	systemNameNode.setText(setting.getSystemName());
	systemUrlNode.setText(setting.getSystemUrl());
	currencySignNode.setText(setting.getCurrencySign());
	currencyUnitNode.setText(setting.getCurrencyUnit());
	isLoginFailureLockNode.setText(String.valueOf(setting
		.getIsLoginFailureLock()));
	loginFailureLockCountNode.setText(String.valueOf(setting
		.getLoginFailureLockCount()));
	loginFailureLockTimeNode.setText(String.valueOf(setting
		.getLoginFailureLockTime()));
	smtpFromMailNode.setText(setting.getSmtpFromMail());
	smtpFromMailNickNode.setText(setting.getSmtpFromMailNick());
	smtpHostNode.setText(setting.getSmtpHost());
	smtpPortNode.setText(String.valueOf(setting.getSmtpPort()));
	smtpUsernameNode.setText(setting.getSmtpUsername());
	// smtpPasswordNode.setText(setting.getSmtpPassword());
	isGzipEnabledNode.setText(setting.getIsGzipEnabled().toString());
	isUpdatingNode.setText(setting.getIsUpdating().toString());

	try {
	    OutputFormat outputFormat = OutputFormat.createPrettyPrint();// 设置XML文档输出格式
	    outputFormat.setEncoding("UTF-8");// 设置XML文档的编码类型
	    outputFormat.setIndent(true);// 设置是否缩进
	    outputFormat.setIndent("	");// 以TAB方式实现缩进
	    outputFormat.setNewlines(true);// 设置是否换行
	    XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(
		    demoprojectXmlFile), outputFormat);
	    xmlWriter.write(document);
	    xmlWriter.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * 更新系统配置信息
     * 
     * @param setting
     */
    public static void updateSetting(Setting setting) {
	writeSetting(setting);
	GeneralCacheAdministrator generalCacheAdministrator = (GeneralCacheAdministrator) SpringUtil
		.getBean(CACHE_MANAGER_BEAN_NAME);
	generalCacheAdministrator.flushEntry(SETTING_CACHE_KEY);
    }

    /**
     * 刷新系统配置信息
     * 
     */
    public void flush() {
	GeneralCacheAdministrator generalCacheAdministrator = (GeneralCacheAdministrator) SpringUtil
		.getBean(CACHE_MANAGER_BEAN_NAME);
	generalCacheAdministrator.flushEntry(SETTING_CACHE_KEY);
    }

    /**
     * 获取货币格式字符串
     * 
     */
    public static String getCurrencyFormat() {
	String currencySign = getSetting().getCurrencySign();
	String currencyUnit = getSetting().getCurrencyUnit();
	return "'" + currencySign + "'#0.00'" + currencyUnit + "'";
    }

    /**
     * 获取货币格式化
     * 
     */
    public static String currencyFormat(BigDecimal price) {
	String currencyFormat = getCurrencyFormat();
	NumberFormat numberFormat = new DecimalFormat(currencyFormat);
	return numberFormat.format(price);
    }

}