package com.telecom.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hoyotech.group.MsgservicePortType;
import com.hoyotech.group.MsgservicePortTypeProxy;
import com.telecom.dao.AuthCodeDao;
import com.telecom.dao.SmsTemplateDao;
import com.telecom.entity.AuthCode;
import com.telecom.entity.SmsTemplate;
import com.telecom.service.AuthCodeService;
import com.telecom.util.CommonUtil;
import com.telecom.util.SettingUtil;
import com.telecom.util.XmlUtil;

@Service("authCodeServiceImpl")
public class AuthCodeServiceImpl extends BaseServiceImpl<AuthCode, String>
		implements AuthCodeService {

	private MsgservicePortType portType = new MsgservicePortTypeProxy();
	
	@Resource(name = "authCodeDaoImpl")
	private AuthCodeDao authCodeDao;
	@Resource(name = "smsTemplateDaoImpl")
	private SmsTemplateDao smsTemplateDao;

	@Resource(name = "authCodeDaoImpl")
	public void setBaseDao(AuthCodeDao authCodeDao) {
		super.setBaseDao(authCodeDao);
	}

	@Override
	public AuthCode getAuthcodeByMobile(String mobile) {
		return authCodeDao.getAuthcode(mobile);
	}

	@Override
	public AuthCode generateAuthCode(String mobile) {
		AuthCode authCode = authCodeDao.getAuthcode(mobile);
		// 生成6位验证码
		Random random = new Random();
		Integer num = Math.abs(random.nextInt(999999));
		String authCodeStr = CommonUtil.fillStr(num, 6);
		if (authCode == null) {
			authCode = new AuthCode();
			authCode.setAuthCode(authCodeStr);
			authCode.setMobile(mobile);
			authCode.setIsEnabled(false);
			authCode.setSendTime(new Date());
			authCodeDao.save(authCode);
		} else {
			authCode.setAuthCode(authCodeStr);
			authCode.setIsEnabled(true);
			authCodeDao.update(authCode);
		}
		return authCode;
	}

	@Override
	public String sendAuthCode(String mobile, String type) {
		String code = "";
		boolean flag = false;
		AuthCode authCode = getAuthcodeByMobile(mobile);
		if (authCode == null) {
			flag = true;
		} else {
			// 为可用时判断是否在2分钟内获取
			if (authCode.getIsEnabled()) {
				// 时间退后2分钟
				Date sendTime = authCode.getSendTime();
				Date now = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(sendTime);
				cal.add(Calendar.MINUTE, +2);
				// 未超过2分钟
				if (cal.getTime().after(now)) {
					return "010";
				} else {
					flag = true;
				}
			} else {
				flag = true;
			}
		}
		if (flag) {
			// 获取短信模版
			authCode = generateAuthCode(mobile);
			String smsTemplateId = "";
			switch (type) {
			case "0": // 登录验证短信模版
				smsTemplateId = "DXMB_6A1257DBCC2B";
				break;
			case "1": // 注册短信模版
				smsTemplateId = "DXMB_5B1F2D93AEC7";
				break;
			case "2": // 找回密码短信模版
				smsTemplateId = "DXMB_ECCCE0B2033B";
				break;
			case "3": // 话费支付短信模版
				smsTemplateId = "DXMB_CB50E6615F20";
				break;
			case "4": // 豆趣订购获取验证码模板
				smsTemplateId = "DXMB_76DFB87F98F3";
				break;
			case "5": // 激活码短信模板
				smsTemplateId = "DXMB_E4ED6D53C386";
				break;
			case "6": // 抢红包短信模板
				smsTemplateId = "DXMB_C61256E8C6FC";
				break;
			default:
				break;
			}
			// 设置短信
			String msg = "";
			SmsTemplate smsTemplate = smsTemplateDao
					.getSmsTemplate(smsTemplateId);
			msg = smsTemplate.getContent().replace("#authCode#",
					authCode.getAuthCode());
			// 发送验证码短信
			try {
				String result = "";
				//发送短信
				String xml = XmlUtil.getSMSXml(authCode.getId(), mobile, msg);
				String xmlDoc = portType.sendMsg(xml, SettingUtil.getSetting().getAccessCode());
				Map<String, String> map = XmlUtil.fluxQureySMSResponse(xmlDoc);
				result = map.get("statusCode");
				if("0".equals(result)){
					// 成功
					code = "000";
					authCode.setIsEnabled(true);
				}
				else{
					// 失败
					code = "001";
				}
				// 成功发送验证码，更新发送时间
				authCode.setSendTime(new Date());
				authCodeDao.update(authCode);
			} catch (Exception e) {
				e.printStackTrace();
				return "012";
			}
		}
		return code;
	}

	@Override
	public String checkAuthCode(String mobile, String authCode) {
		AuthCode authCodeEntity = getAuthcodeByMobile(mobile);
		// 未获取过验证码
		if (authCodeEntity == null) {
			return "001";
		} else {
			// 验证码已被使用过
			if (!authCodeEntity.getIsEnabled()) {
				return "001";
			} else {
				Date sendTime = authCodeEntity.getSendTime();
				Calendar cal = Calendar.getInstance();
				cal.setTime(sendTime);
				cal.add(Calendar.MINUTE, +30);
				// 超过30分钟
				if (cal.getTime().before(new Date())) {
					return "002";
				} else {
					// 验证码是否正确
					if (authCodeEntity.getAuthCode().equals(authCode)) {
						authCodeEntity.setIsEnabled(false);
						authCodeDao.update(authCodeEntity);
						return "000";
					} else {
						return "003";
					}
				}
			}
		}
	}
}
