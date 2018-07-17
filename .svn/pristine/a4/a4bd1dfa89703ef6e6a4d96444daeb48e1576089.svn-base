package com.telecom.bean.sms;

import java.util.HashMap;
import java.util.Map;

import com.telecom.entity.SMS;
import com.telecom.entity.SMS.SMSStatus;
import com.telecom.service.SMSService;
import com.telecom.util.SettingUtil;
import com.telecom.util.SpringUtil;
import com.telecom.util.XmlUtil;
import com.hoyotech.group.MsgservicePortType;
import com.hoyotech.group.MsgservicePortTypeProxy;

public class SmsSend extends Thread {

	private SMSService smsService = (SMSService) SpringUtil.getBean("smsServiceImpl");
	private MsgservicePortType portType = new MsgservicePortTypeProxy();

	private SMS sms;

	public SmsSend(SMS sms) {
		this.sms = sms;
	}

	public void run() {
		sendSMS();
	}

	private void sendSMS() {
		// 发送短信
		// 如果失败则最多重新发送三次
		// 每次失败都将失败状态计数器加1，且休息1秒

		SMSStatus status = SMSStatus.success; // 短信发送状态，默认成功
		int count = 0;
		Map<String, String> map = new HashMap<String, String>();
		String result = null;

		do {
			try {
				//发送短信
				String xml = XmlUtil.getSMSXml(sms.getId(), sms.getAccount(), sms.getText());
				String xmlDoc = portType.sendMsg(xml, SettingUtil.getSetting().getAccessCode());
				map = XmlUtil.fluxQureySMSResponse(xmlDoc);
				sms.setMiddleStatus(map.get("statusCode"));
				result = map.get("statusCode");
				Integer code = Integer.parseInt(map.get("statusCode"));
				switch (code) {
				case 0:
					sms.setMiddleDesc("提交成功");
					sms.setMsgId(map.get("msgId"));
					break;
				case -1:
					sms.setMiddleDesc("非法IP");
					break;
				case -2:
					sms.setMiddleDesc("参数不正确");
					break;
				case -3:
					sms.setMiddleDesc("标识码不正确");
					break;
				case -4:
					sms.setMiddleDesc("非湖北电信号码");
					break;
				}
				count++;
				
				if (!"0".equals(result)) {
					SmsController.failCount.getAndIncrement();// 每次失败都将失败状态计数器加1
					Thread.sleep(1000);// 失败则暂停1s
				}
			} catch (Exception e) {
				break;
			}
		} while (!(result).equals("0") && count < 3);
		
		if (!"0".equals(result)) {
			status = SMSStatus.invalid;
		}
		if ("0".equals(result)){
			status = SMSStatus.sending;
		}
		// 更新SMS
		sms.setStatus(status);
		smsService.update(sms);
	}

}
