package com.waben.stock.datalayer.message.service;

import com.waben.stock.interfaces.enums.SmsType;

public interface SmsService {

	/**
	 * 发送短信
	 * 
	 * @param smsType
	 *            短信类型
	 * @param phone
	 *            手机号码
	 */
	public void sendMessage(SmsType smsType, String phone);

}
