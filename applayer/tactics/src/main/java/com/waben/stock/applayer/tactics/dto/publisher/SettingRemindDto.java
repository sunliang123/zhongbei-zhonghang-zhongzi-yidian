package com.waben.stock.applayer.tactics.dto.publisher;

/**
 * 个人设置提醒
 * 
 * @author luomengan
 *
 */
public class SettingRemindDto {

	private String phone;

	private boolean settingPaymentPassword;

	private boolean settingBindCard;

	private boolean settingRealName;

	private Integer bindCardCount = 0;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isSettingPaymentPassword() {
		return settingPaymentPassword;
	}

	public void setSettingPaymentPassword(boolean settingPaymentPassword) {
		this.settingPaymentPassword = settingPaymentPassword;
	}

	public boolean isSettingBindCard() {
		return settingBindCard;
	}

	public void setSettingBindCard(boolean settingBindCard) {
		this.settingBindCard = settingBindCard;
	}

	public Integer getBindCardCount() {
		return bindCardCount;
	}

	public void setBindCardCount(Integer bindCardCount) {
		this.bindCardCount = bindCardCount;
	}

	public boolean isSettingRealName() {
		return settingRealName;
	}

	public void setSettingRealName(boolean settingRealName) {
		this.settingRealName = settingRealName;
	}

}
