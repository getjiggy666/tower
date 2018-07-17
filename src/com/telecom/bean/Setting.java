package com.telecom.bean;

import org.apache.commons.lang.StringUtils;

import com.telecom.util.CommonUtil;

/**
 * Bean类 - 系统设置
 */

public class Setting {

    // 货币种类(人民币、美元、欧元、英磅、加拿大元、澳元、卢布、港币、新台币、韩元、新加坡元、新西兰元、日元、马元、瑞士法郎、瑞典克朗、丹麦克朗、兹罗提、挪威克朗、福林、捷克克朗、葡币)
    public enum CurrencyType {
	CNY, USD, EUR, GBP, CAD, AUD, RUB, HKD, TWD, KRW, SGD, NZD, JPY, MYR, CHF, SEK, DKK, PLZ, NOK, HUF, CSK, MOP
    };

    // 小数位精确方式(四舍五入、向上取整、向下取整)
    public enum RoundType {
	roundHalfUp, roundUp, roundDown
    }

    public static final String HOT_SEARCH_SEPARATOR = ",";// 热门搜索分隔符

    private String adminLoginUrl;// 后台登录URL
    private String adminLoginProcessingUrl;// 后台登录处理URL
    private String contextPath;// 虚拟路径
    private String imageUploadPath;// 图片上传路径
    private String imageBrowsePath;// 图片浏览路径
    private String systemName;// 系统名称
    private String systemUrl;// 系统网址
    private Integer distributeUserCount;// 赠送用户数上限
    private String currencySign;// 货币符号
    private String currencyUnit;// 货币单位
    private Boolean isLoginFailureLock; // 是否开启登录失败锁定账号功能
    private Integer loginFailureLockCount;// 同一账号允许连续登录失败的最大次数,超出次数后将锁定其账号
    private Integer loginFailureLockTime;// 账号锁定时间(单位: 分钟,0表示永久锁定)
    private String smtpFromMail;// 发件人邮箱
    private String smtpFromMailNick; // 发件人昵称
    private String smtpHost;// SMTP服务器地址
    private Integer smtpPort;// SMTP服务器端口
    private String smtpUsername;// SMTP用户名
    private String smtpPassword;// SMTP密码
    private Boolean isGzipEnabled;// 是否开启GZIP功能
    private Boolean isUpdating; // 是否正在升级
    private Integer buildHtmlDelayTime;// 生成HTML任务延时(单位: 秒)

    private String upFilePath; // 文件存储路径

    private String accessCode;// 中间服务器的AccessCode
    private String smsUrl;// 短信URL
    private String authenCodeUrl;// 验证码URL

    private String secretKey;
    private String vector;

    public String getAdminLoginUrl() {
	return adminLoginUrl;
    }

    public void setAdminLoginUrl(String adminLoginUrl) {
	this.adminLoginUrl = adminLoginUrl;
    }

    public String getAdminLoginProcessingUrl() {
	return adminLoginProcessingUrl;
    }

    public void setAdminLoginProcessingUrl(String adminLoginProcessingUrl) {
	this.adminLoginProcessingUrl = adminLoginProcessingUrl;
    }

    public String getContextPath() {
	return contextPath;
    }

    public void setContextPath(String contextPath) {
	this.contextPath = contextPath;
    }

    public String getImageUploadPath() {
	return imageUploadPath;
    }

    public void setImageUploadPath(String imageUploadPath) {
	this.imageUploadPath = StringUtils.removeEnd(imageUploadPath, "/");
    }

    public String getImageBrowsePath() {
	return imageBrowsePath;
    }

    public void setImageBrowsePath(String imageBrowsePath) {
	this.imageBrowsePath = StringUtils.removeEnd(imageBrowsePath, "/");
    }

    public String getSystemName() {
	return systemName;
    }

    public void setSystemName(String systemName) {
	this.systemName = systemName;
    }

    public String getSystemUrl() {
	return systemUrl;
    }

    public void setSystemUrl(String systemUrl) {
	this.systemUrl = systemUrl;
    }

    public Integer getDistributeUserCount() {
	return distributeUserCount;
    }

    public void setDistributeUserCount(Integer distributeUserCount) {
	this.distributeUserCount = distributeUserCount;
    }

    public String getCurrencySign() {
	return currencySign;
    }

    public void setCurrencySign(String currencySign) {
	this.currencySign = currencySign;
    }

    public String getCurrencyUnit() {
	return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
	this.currencyUnit = currencyUnit;
    }

    public Boolean getIsLoginFailureLock() {
	return isLoginFailureLock;
    }

    public void setIsLoginFailureLock(Boolean isLoginFailureLock) {
	this.isLoginFailureLock = isLoginFailureLock;
    }

    public Integer getLoginFailureLockCount() {
	return loginFailureLockCount;
    }

    public void setLoginFailureLockCount(Integer loginFailureLockCount) {
	this.loginFailureLockCount = loginFailureLockCount;
    }

    public Integer getLoginFailureLockTime() {
	return loginFailureLockTime;
    }

    public void setLoginFailureLockTime(Integer loginFailureLockTime) {
	this.loginFailureLockTime = loginFailureLockTime;
    }

    public String getSmtpFromMail() {
	return smtpFromMail;
    }

    public void setSmtpFromMail(String smtpFromMail) {
	this.smtpFromMail = smtpFromMail;
    }

    public String getSmtpFromMailNick() {
	return smtpFromMailNick;
    }

    public void setSmtpFromMailNick(String smtpFromMailNick) {
	this.smtpFromMailNick = smtpFromMailNick;
    }

    public String getSmtpHost() {
	return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
	this.smtpHost = smtpHost;
    }

    public Integer getSmtpPort() {
	return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
	this.smtpPort = smtpPort;
    }

    public String getSmtpUsername() {
	return smtpUsername;
    }

    public void setSmtpUsername(String smtpUsername) {
	this.smtpUsername = smtpUsername;
    }

    public String getSmtpPassword() {
	return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
	this.smtpPassword = smtpPassword;
    }

    public Boolean getIsGzipEnabled() {
	return isGzipEnabled;
    }

    public void setIsGzipEnabled(Boolean isGzipEnabled) {
	this.isGzipEnabled = isGzipEnabled;
    }

    public Integer getBuildHtmlDelayTime() {
	return buildHtmlDelayTime;
    }

    public void setBuildHtmlDelayTime(Integer buildHtmlDelayTime) {
	this.buildHtmlDelayTime = buildHtmlDelayTime;
    }

    public String getAccessCode() {
	return accessCode;
    }

    public void setAccessCode(String accessCode) {
	this.accessCode = accessCode;
    }

    public String getSmsUrl() {
	return smsUrl;
    }

    public void setSmsUrl(String smsUrl) {
	this.smsUrl = smsUrl;
    }

    public String getAuthenCodeUrl() {
	return authenCodeUrl;
    }

    public void setAuthenCodeUrl(String authenCodeUrl) {
	this.authenCodeUrl = authenCodeUrl;
    }

    public Boolean getIsUpdating() {
	return isUpdating;
    }

    public void setIsUpdating(Boolean isUpdating) {
	this.isUpdating = isUpdating;
    }

    public String getSecretKey() {
	return secretKey;
    }

    public void setSecretKey(String secretKey) {
	this.secretKey = secretKey;
    }

    public String getVector() {
	return vector;
    }

    public void setVector(String vector) {
	this.vector = vector;
    }

    // 获取图片上传真实路径
    public String getImageUploadRealPath() {
	return CommonUtil.getPreparedStatementPath(imageUploadPath);
    }

    public String getUpFilePath() {
	return upFilePath;
    }

    public void setUpFilePath(String upFilePath) {
	this.upFilePath = upFilePath;
    }

}