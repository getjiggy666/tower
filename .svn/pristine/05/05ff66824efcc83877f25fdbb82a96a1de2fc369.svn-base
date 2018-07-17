package com.telecom.service;

import java.util.Map;

/**
 * Service接口 - 邮件服务
 */

public interface MailService {
	
	/**
	 * 根据主题、Freemarker模板文件路径、接收邮箱地址、Map数据发送邮件(异步发送)
	 * 
	 * @param subject
	 *            主题
	 * 
	 * @param templatePath
	 *            Freemarker模板文件路径
	 * 
	 * @param data
	 *            Map数据
	 *            
	 * @param toMail
	 *            收件人邮箱
	 * 
	 */
	public void sendMail(String subject, String templatePath, Map<String, Object> data,  String toMail);
	
	/**
	 * 发送SMTP邮箱配置测试(同步发送)
	 * 
	 * @param smtpFromMail
	 *            发件人邮箱
	 * 
	 * @param nick
	 * 			      发件人昵称
	 * 
	 * @param smtpHost
	 *            SMTP服务器地址
	 *            
	 * @param smtpPort
	 *            SMTP服务器端口
	 *            
	 * @param smtpUsername
	 *            SMTP用户名
	 *            
	 * @param smtpPassword
	 *            SMTP密码
	 *            
	 * @param toMail
	 *            收件人邮箱
	 * 
	 */
	public void sendSmtpTestMail(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword, String nick, String toMail);
	
	/**
	 * 发送示例邮件
	 * 
	 * @param email
	 *            邮箱
	 * 
	 */
	public void sendTestMail(String email);
	
	
}