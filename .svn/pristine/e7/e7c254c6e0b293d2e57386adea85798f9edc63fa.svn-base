package com.telecom.action.admin;

import javax.annotation.Resource;

import com.telecom.bean.Pager.Order;
import com.telecom.entity.SMS;
import com.telecom.entity.SMS.SMSStatus;
import com.telecom.service.SMSService;

public class SmsAction extends BaseAdminAction {

	private static final long serialVersionUID = 6718838800634401257L;
	
	private SMS sms;
	
	@Resource(name = "smsServiceImpl")
	private SMSService smsService;
	
	//列表
	public String list(){
		pager.setOrder(Order.desc);
		pager.setOrderBy("createDate");
		pager = smsService.findPager(pager);
		return LIST;
	}
	
	//查看
	public String view(){
		sms = smsService.load(id);
		return VIEW;
	}
	
	//重发
	public String send(){
		sms = smsService.load(id);
		SMS newSMS = new SMS();
		newSMS.setAccount(sms.getAccount());
		newSMS.setText(sms.getText());
		newSMS.setStatus(SMSStatus.unsend);
		newSMS.setXml(sms.getXml());
		smsService.save(newSMS);
		return ajax(Status.success, "您的操作已成功!");
	}

	
	public SMS getSms() {
		return sms;
	}

	public void setSms(SMS sms) {
		this.sms = sms;
	}
	
	
}
