package com.telecom.util;

import java.util.UUID;

import com.telecom.entity.Serial;
import com.telecom.service.AccessStrategyService;
import com.telecom.service.MessageTemplateService;
import com.telecom.service.SerialService;
import com.telecom.service.SmsTemplateService;

/**
 * 工具类 - 编号生成
 */

public class SerialNumberUtil {

	public static final String ORDER_SN_PREFIX = "OS_";// 赠送单编号前缀
	public static final String SMS_TEMPLATE_SN_PREFIX = "SMS_";// 短信模版编号前缀
	public static final String MESSAGE_TEMPLATE_SN_PREFIX = "MESSAGE_";// 通知模版编号前缀
	public static final String ACCESS_STRATEGY_SN_PREFIX = "AS_";// IP过滤编号前缀
	
	public static final long ORDER_SN_FIRST = 100000L;// 订单编号起始数
	public static final long ORDER_SN_STEP = 1L;// 订单编号步长
	
	public static Long lastOrderSnNumber;
	
//  // 通过步长的方式进行自增
//	static {
//		// 订单编号
//		OrderService orderService = (OrderService) SpringUtil.getBean("orderServiceImpl");
//		String lastOrderSn = orderService.getLastOrderSn();
//		if (StringUtils.isNotEmpty(lastOrderSn)) {
//			lastOrderSnNumber = Long.parseLong(StringUtils.removeStartIgnoreCase(lastOrderSn, ORDER_SN_PREFIX));
//		} else {
//			lastOrderSnNumber = ORDER_SN_FIRST;
//		}
//	}
	
//	/**
//	 * 生成订单编号
//	 * 
//	 * @return 订单编号
//	 */
//	public synchronized static String buildOrderSn() {
//		lastOrderSnNumber += ORDER_SN_STEP;
//		return ORDER_SN_PREFIX + lastOrderSnNumber;
//	}
	
	
	/**
	 * 生成订单编号
	 * 
	 * @return 订单编号
	 */
	public synchronized static String buildOrderSn() {
		SerialService serialService = (SerialService) SpringUtil.getBean("serialServiceImpl");
		Serial serial = serialService.getSerialBySign(ORDER_SN_PREFIX);
		String orderSn = serialService.toSign(serial);
		if(serial!=null){
			serialService.nextSign(serial);
		}
		return orderSn;
	}

	/**
	 * 生成短信模版编号
	 * 
	 * @return 短信模版编号
	 */
	public static String buildSmsTemplateSn() {
		SmsTemplateService smsTemplateService = (SmsTemplateService) SpringUtil
				.getBean("smsTemplateServiceImpl");
		String smsTemplateSn;
		do {
			String uuid = UUID.randomUUID().toString();
			smsTemplateSn = SMS_TEMPLATE_SN_PREFIX
					+ (uuid.substring(0, 8) + uuid.substring(9, 13))
							.toUpperCase();
		} while (smsTemplateService.isExistBySign(smsTemplateSn));
		return smsTemplateSn;
	}
	
	/**
	 * 生成通知模版编号
	 * 
	 * @return 通知模版编号
	 */
	public static String buildMessageTemplateSn() {
		MessageTemplateService messageTemplateService = (MessageTemplateService) SpringUtil
				.getBean("messageTemplateServiceImpl");
		String messageTemplateSn;
		do {
			String uuid = UUID.randomUUID().toString();
			messageTemplateSn = MESSAGE_TEMPLATE_SN_PREFIX
					+ (uuid.substring(0, 8) + uuid.substring(9, 13))
							.toUpperCase();
		} while (messageTemplateService.isExistBySign(messageTemplateSn));
		return messageTemplateSn;
	}

	/**
	 * 生成IP过滤编号
	 * 
	 * @return IP过滤编号
	 */
	public static String buildAccessStrategySn() {
		AccessStrategyService accessStrategyService = (AccessStrategyService) SpringUtil
				.getBean("accessStrategyServiceImpl");
		String accessStrategySn;
		do {
			String uuid = UUID.randomUUID().toString();
			accessStrategySn = ACCESS_STRATEGY_SN_PREFIX
					+ (uuid.substring(0, 8) + uuid.substring(9, 13)
							.toUpperCase());
		} while (accessStrategyService.isExistBySign(accessStrategySn));
		return accessStrategySn;
	}
	
}