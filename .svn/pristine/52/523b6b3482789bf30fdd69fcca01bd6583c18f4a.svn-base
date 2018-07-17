package com.telecom.bean.sms;

import java.util.List;

import com.telecom.entity.SMS;
import com.telecom.entity.SMS.SMSStatus;
import com.telecom.service.SMSService;
import com.telecom.util.SpringUtil;

/**
 * 失败队列
 * 读取发送失败的短信并将它放入正常队列
 */
public class SmsFailFetchThread extends Thread{
	
	private SMSService smsService = (SMSService) SpringUtil.getBean("smsServiceImpl");
	
	public void run(){
		List<SMS> smsList = null;	
		int orderList = 0;
		
		while(true){
			//取得一批待发送短信
			smsList = smsService.getSMSList(SMSStatus.failure, orderList);
			if(smsList.size() > 0){
				orderList = smsList.get(smsList.size()-1).getOrderList().intValue();
				for(SMS sms : smsList){
					try {
						//塞入快队列
						SmsController.failQueue.put(sms);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			// 没有待发待短信，则休息一段时间
			else if(smsList.size() < 20){
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}	
}
