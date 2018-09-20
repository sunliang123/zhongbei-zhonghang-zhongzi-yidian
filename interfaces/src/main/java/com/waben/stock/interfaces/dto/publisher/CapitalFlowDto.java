package com.waben.stock.interfaces.dto.publisher;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waben.stock.interfaces.enums.CapitalFlowExtendType;
import com.waben.stock.interfaces.enums.CapitalFlowType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CapitalFlowDto {

	private Long id;
	/**
	 * 流水号
	 */
	private String flowNo;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 当前可用余额
	 * <p>
	 * 产生该流水之后的可用余额
	 * </p>
	 */
	private BigDecimal availableBalance;
	/**
	 * 流水类型
	 */
	private CapitalFlowType type;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 产生时间
	 */
	private Date occurrenceTime;
	/**
	 * 发布人ID
	 */
	private Long publisherId;
	/**
	 * 发布人手机号码
	 */
	private String publisherPhone;
	/**
	 * 发布人系列号
	 */
	private String publisherSerialCode;
	/**
	 * 产生流水的对象类型
	 */
	private CapitalFlowExtendType extendType;
	/**
	 * 产生流水的对象ID
	 */
	private Long extendId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public CapitalFlowType getType() {
		return type;
	}

	public void setType(CapitalFlowType type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getOccurrenceTime() {
		return occurrenceTime;
	}

	public void setOccurrenceTime(Date occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public CapitalFlowExtendType getExtendType() {
		return extendType;
	}

	public void setExtendType(CapitalFlowExtendType extendType) {
		this.extendType = extendType;
	}

	public Long getExtendId() {
		return extendId;
	}

	public void setExtendId(Long extendId) {
		this.extendId = extendId;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public String getCapitalFlowType() {
		String capitalFlowType = null;
		if (type != null) {
			capitalFlowType = type.getType();
		}
		return capitalFlowType;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public String getPublisherSerialCode() {
		return publisherSerialCode;
	}

	public void setPublisherSerialCode(String publisherSerialCode) {
		this.publisherSerialCode = publisherSerialCode;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

}
