package com.telecom.job;

import java.util.Date;

import com.telecom.bean.CommonConstant;

// 示例
public class TestJob extends Thread{
	public void run() {
		// TestService testService = (TestService) SpringUtil.getBean("testServiceImpl");
		while(true){
			try {
				// 更新线程监测
				CommonConstant.threadMonitoring.put("TestJob", new Date());
				// 执行处理
				// ---------------------------
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
