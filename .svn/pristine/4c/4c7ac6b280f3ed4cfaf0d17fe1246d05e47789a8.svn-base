package com.telecom.bean.sms;

import java.util.HashMap;
import java.util.Map;

import com.hoyotech.group.MsgservicePortType;
import com.hoyotech.group.MsgservicePortTypeProxy;
import com.telecom.entity.SMS;
import com.telecom.entity.SMS.SMSStatus;
import com.telecom.service.SMSService;
import com.telecom.util.SettingUtil;
import com.telecom.util.SpringUtil;
import com.telecom.util.XmlUtil;

public class SmsQueryStatus extends Thread {
	
	private SMSService smsService = (SMSService) SpringUtil.getBean("smsServiceImpl");
	private MsgservicePortType portType = new MsgservicePortTypeProxy();
	
	private SMS sms;

	public SmsQueryStatus(SMS sms){
		this.sms = sms;
	}
	
	public void run() {
		queryStatus();
	}
	
	public void queryStatus(){
		try {
			Map<String, String> map = new HashMap<String, String>();
			//发送短信
			String xml = portType.queryMsgStatus(sms.getMsgId(), SettingUtil.getSetting().getAccessCode());
			map = XmlUtil.fluxQureyRealSMSResponse(xml);
			sms.setGatewayStatus(map.get("statusCode"));
			Integer code = Integer.parseInt(map.get("statusCode"));
			switch (code) {
			case 1://待发送
				sms.setGatewayDesc("待发送");
				break;
			case 0://发送成功
				sms.setStatus(SMSStatus.success);
				sms.setGatewayDesc("发送成功");
				break;
			case -1://发送失败
				sms.setStatus(SMSStatus.failure);
				sms.setGatewayDesc("发送失败");
				break;
			case -2://短信参数错误
				sms.setStatus(SMSStatus.invalid);
				sms.setGatewayDesc("短信参数错误");
				break;
			case -3://黑名单用户
				sms.setStatus(SMSStatus.invalid);
				sms.setGatewayDesc("黑名单用户");
				break;
			}
			smsService.update(sms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
