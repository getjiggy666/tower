package com.telecom.bean.sms;

import java.util.List;

import com.telecom.entity.SMS;
import com.telecom.entity.SMS.SMSStatus;
import com.telecom.service.SMSService;
import com.telecom.util.SpringUtil;

public class SmsQueryStatusFetchThread extends Thread {
	
	private SMSService smsService = (SMSService) SpringUtil.getBean("smsServiceImpl");
	
	public void run(){
		List<SMS> smsList = null;	
		int orderList = 0;
		
		while(true){
			//取得一批待确认短信
			smsList = smsService.getSMSList(SMSStatus.sending, orderList);
			if(smsList.size() > 0){
				orderList = smsList.get(smsList.size()-1).getOrderList().intValue();
				for(SMS sms : smsList){
					try {
						//塞入快队列
						SmsController.realQueue.put(sms);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			// 没有待发待短信，则休息一段时间
			else if(smsList.size() < 20){
				try {
					orderList = 0;
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
