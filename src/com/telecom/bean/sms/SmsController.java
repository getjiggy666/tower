package com.telecom.bean.sms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import com.telecom.entity.SMS;

public class SmsController extends Thread {

	// 发送短信失败反馈计数。
	public static AtomicInteger failCount = new AtomicInteger();
	// 待发送短信队列
	public static BlockingQueue<SMS> dataQueue = new LinkedBlockingQueue<SMS>(200);
	// 发送失败的待发送短信队列
	public static BlockingQueue<SMS> failQueue = new LinkedBlockingQueue<SMS>(200);
	// 待确认是否真正发送成功短信队列
	public static BlockingQueue<SMS> realQueue = new LinkedBlockingQueue<SMS>(200);
	
	// 每次发送短信间隔时间　毫秒
	private static int sleepTime = 100;
	// 每个失败标志平均睡眠时间　毫秒
	private static int failSleepTime = 10;
	
	// 循环发送短信
	public void run() {
		while (true) {
			try {
				// 如果有发送失败的计数则睡眠相应的时间
				if (failCount.get() > 0){
					Thread.sleep(failCount.getAndDecrement() * failSleepTime);
				}

				SMS data = null;
				SMS smsFail = null;
				SMS smsReal = null;
				// 正常队列
				if (dataQueue.size() > 0) {
					data = dataQueue.take();
					SmsSend sendThread = new SmsSend(data);
					sendThread.start();
				}
				// 失败队列
				else if (failQueue.size() > 0) {
					smsFail = failQueue.take();
					SmsFailSend sendThread = new SmsFailSend(smsFail);
					sendThread.start();
				}
				// 待确认队列
				else if (realQueue.size() > 0){
					smsReal = realQueue.take();
					SmsQueryStatus smsQueryStatus = new SmsQueryStatus(smsReal);
					smsQueryStatus.start();
				}
				// 队列都为空，则表明没有压力，则休息一段时间
				else {
					Thread.sleep(1000);
				}
				
				int msgCount = 0;
				if(data != null){
					msgCount = getMsgCount(data.getText());
				}else if(smsFail != null){
					msgCount = getMsgCount(smsFail.getText());
				}
				Thread.sleep(sleepTime * msgCount);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * 获取短信发送次数
	 * 如果短信长度大于70个字符，则为长短信
	 * @return
	 */
	public int getMsgCount(String msgText){
		int msgLength = msgText.length();
		if(msgLength > 70){
			//return (msgLength*2)/(140-6)+1;
			return msgLength/67 + 1;
		}else{
			return 1;
		}
	}

}
