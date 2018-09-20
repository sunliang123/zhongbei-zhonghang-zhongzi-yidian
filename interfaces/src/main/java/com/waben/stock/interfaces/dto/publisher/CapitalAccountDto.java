package com.waben.stock.interfaces.dto.publisher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "CapitalAccountDto",description = "资金账户对象")
public class CapitalAccountDto {
	@ApiModelProperty(value = "员工id")
	private Long id;
	/**
	 * 账户余额
	 */
	@ApiModelProperty(value = "账户余额")
	private BigDecimal balance;
	/**
	 * 账户可用余额
	 */
	@ApiModelProperty(value = "账户可用余额")
	private BigDecimal availableBalance;
	/**
	 * 冻结资金
	 */
	@ApiModelProperty(value = "员工id")
	private BigDecimal frozenCapital;
	/**
	 * 支付密码
	 */
	@ApiModelProperty(value = "支付密码")
	private String paymentPassword;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**
	 * 状态
	 * <ul>
	 * <li>1正常，因为该字段为新加的字段，所以数据为空的情况也是表示正常状态</li>
	 * <li>2冻结，不能提现、不能交易</li>
	 * </ul>
	 */
	@ApiModelProperty(value = "状态（1正常，2冻结）")
	private Integer state;
	/**
	 * 发布人ID
	 */
	@ApiModelProperty(value = "会员id")
	private Long publisherId;
	/**
	 * 发布人序列号
	 */
	@ApiModelProperty(value = "会员序列号")
	private String publisherSerialCode;
	@ApiModelProperty(value = "会员手机号码")
	private String publisherPhone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public BigDecimal getFrozenCapital() {
		return frozenCapital;
	}

	public void setFrozenCapital(BigDecimal frozenCapital) {
		this.frozenCapital = frozenCapital;
	}

	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherSerialCode() {
		return publisherSerialCode;
	}

	public void setPublisherSerialCode(String publisherSerialCode) {
		this.publisherSerialCode = publisherSerialCode;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
