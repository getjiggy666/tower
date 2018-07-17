package com.telecom.service;

import com.telecom.entity.AuthCode;

public interface AuthCodeService extends BaseService<AuthCode, String> {

	/**
	 * 根据号码查询验证码
	 * 
	 * @param mobile
	 *            号码
	 * @return
	 */
	public AuthCode getAuthcodeByMobile(String mobile);

	public AuthCode generateAuthCode(String mobile);

	/**
	 * 发送验证码
	 * 
	 * @param mobile
	 *            号码
	 * @param type
	 *            类型(0:登录、1:注册、2:找回密码，传入其他值未默认短信模版)
	 * @return 返回码(000:成功，010:五分钟内不能重复获取，011:传入类型不正确，012:接口调用失败，其他情况:参数错误)
	 */
	public String sendAuthCode(String mobile, String type);
	
	/**
	 * 检查验证码
	 * @param mobile 号码
	 * @param authCode 验证码
	 * @return 返回码(000:成功，001:未获取验证码，002:验证码已超时，003:验证码错误)
	 */
	public String checkAuthCode(String mobile, String authCode);
}
