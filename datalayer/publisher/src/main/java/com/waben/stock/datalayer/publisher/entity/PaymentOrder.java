package com.waben.stock.datalayer.publisher.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.waben.stock.datalayer.publisher.entity.enumconverter.PaymentStateConverter;
import com.waben.stock.datalayer.publisher.entity.enumconverter.PaymentTypeConverter;
import com.waben.stock.interfaces.enums.PaymentState;
import com.waben.stock.interfaces.enums.PaymentType;

/**
 * 支付订单
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "payment_order")
public class PaymentOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 支付单号
	 */
	@Column(name = "payment_no")
	private String paymentNo;
	/**
	 * 第三方支付单号
	 */
	@Column(name = "third_payment_no")
	private String thirdPaymentNo;
	/**
	 * 金额
	 */
	@Column(name = "amount")
	private BigDecimal amount;
	/**
	 * 部分支付的金额，支付宝转账方式用户具体转账的金额不可控，因此加入该字段记录
	 */
	@Column(name = "part_amount")
	private BigDecimal partAmount;
	/**
	 * 支付方式
	 */
	@Column(name = "type")
	@Convert(converter = PaymentTypeConverter.class)
	private PaymentType type;
	/**
	 * 支付宝账号
	 */
	@Column(name = "alipay_account")
	private String alipayAccount;
	/**
	 * 支付状态
	 */
	@Column(name = "state")
	@Convert(converter = PaymentStateConverter.class)
	private PaymentState state;
	/**
	 * 发布人ID
	 */
	@Column(name = "publisher_id")
	private Long publisherId;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;
	/**
	 * 支付html
	 */
	@Type(type = "text")
	@Column(name = "pay_html")
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public String getThirdPaymentNo() {
		return thirdPaymentNo;
	}

	public void setThirdPaymentNo(String thirdPaymentNo) {
		this.thirdPaymentNo = thirdPaymentNo;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
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
