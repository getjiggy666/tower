package com.telecom.bean.sms;

import java.util.List;

import com.telecom.entity.SMS;
import com.telecom.entity.SMS.SMSStatus;
import com.telecom.service.SMSService;
import com.telecom.util.SpringUtil;

/**
 * 正常队列
 * 短信读取类，从数据库读取没发送的短信，并塞入队列
 */
public class SmsFetchThread extends Thread{
	
	private SMSService smsService = (SMSService) SpringUtil.getBean("smsServiceImpl");
	
	public void run(){
		List<SMS> smsList = null;	
		int orderList = 0;
		
		while(true){
			//取得一批待发送短信
			smsList = smsService.getSMSList(SMSStatus.unsend, orderList);
			if(smsList.size() > 0){
				orderList = smsList.get(smsList.size()-1).getOrderList().intValue();
				for(SMS sms : smsList){
					try {
						//塞入快队列
						SmsController.dataQueue.put(sms);
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
