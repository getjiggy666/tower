package com.telecom.bean.sms;

import java.rmi.RemoteException;
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

public class SmsFailSend extends Thread {

	private SMSService smsService = (SMSService) SpringUtil.getBean("smsServiceImpl");
	private MsgservicePortType portType = new MsgservicePortTypeProxy();
	
	private SMS sms;

	public SmsFailSend(SMS sms) {
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
		int sendNum = sms.getErrorCount().intValue(); // 发送次数
		Map<String, String> map = new HashMap<String, String>();

		String result = null;
		try {
			String xml = XmlUtil.getSMSXml("1", sms.getAccount(), sms.getText());
			String xmlDoc = portType.sendMsg(xml, SettingUtil.getSetting().getAccessCode());
			map = XmlUtil.fluxQureySMSResponse(xmlDoc);
			result = map.get("statusCode");
			Integer code = Integer.parseInt(map.get("statusCode"));
			switch (code) {
			case 0:
				sms.setMiddleDesc("提交成功");
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
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		if ("0".equals(result)) {
			// 成功
			status = SMSStatus.sending;
		} else {
			// 发送失败
			// 每次失败都将失败状态计数器加1
			SmsController.failCount.getAndIncrement();
			if (!"0".equals(result)) {
				status = SMSStatus.invalid;
			}
			
			sendNum++;// 发送次数加1
			if (sendNum >= 3) {
				// 如果发送了3次，还是失败，则不再发送
				status = SMSStatus.invalid;
			}

		}
		sms.setStatus(status);
		smsService.update(sms);
	}
}
