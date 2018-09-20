package com.waben.stock.interfaces.dto.publisher;

import java.math.BigDecimal;
import java.util.Date;

import com.waben.stock.interfaces.enums.PaymentState;
import com.waben.stock.interfaces.enums.PaymentType;

public class PaymentOrderDto {

	private Long id;
	/**
	 * 支付单号
	 */
	private String paymentNo;
	/**
	 * 第三方支付单号
	 */
	private String thirdPaymentNo;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 部分支付的金额，支付宝转账方式用户具体转账的金额不可控，因此加入该字段记录
	 */
	private BigDecimal partAmount;
	/**
	 * 支付方式
	 */
	private PaymentType type;
	/**
	 * 支付宝账号
	 */
	private String alipayAccount;
	/**
	 * 支付状态
	 */
	private PaymentState state;
	/**
	 * 发布人ID
	 */
	private Long publisherId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 支付html
	 */
	private String payHtml;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}

	public String getThirdPaymentNo() {
		return thirdPaymentNo;
	}

	public void setThirdPaymentNo(String thirdPaymentNo) {
		this.thirdPaymentNo = thirdPaymentNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public PaymentState getState() {
		return state;
	}

	public void setState(PaymentState state) {
		this.state = state;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getPartAmount() {
		return partAmount;
	}

	public void setPartAmount(BigDecimal partAmount) {
		this.partAmount = partAmount;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getPayHtml() {
		return payHtml;
	}

	public void setPayHtml(String payHtml) {
		this.payHtml = payHtml;
	}

}
