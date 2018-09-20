package com.waben.stock.datalayer.publisher.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.waben.stock.datalayer.publisher.entity.enumconverter.CapitalFlowExtendTypeConverter;
import com.waben.stock.datalayer.publisher.entity.enumconverter.CapitalFlowTypeConverter;
import com.waben.stock.interfaces.enums.CapitalFlowExtendType;
import com.waben.stock.interfaces.enums.CapitalFlowType;

/**
 * @author Created by yuyidi on 2017/11/10.
 * @desc 资金流水
 */
@Entity
@Table(name = "capital_flow")
public class CapitalFlow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 流水号
	 */
	@Column(name = "flow_no")
	private String flowNo;
	/**
	 * 金额
	 */
	@Column(name = "amount")
	private BigDecimal amount;
	/**
	 * 当前可用余额
	 * <p>
	 * 产生该流水之后的可用余额
	 * </p>
	 */
	@Column(name = "available_balance")
	private BigDecimal availableBalance;
	/**
	 * 流水类型
	 */
	@Column(name = "type")
	@Convert(converter = CapitalFlowTypeConverter.class)
	private CapitalFlowType type;
	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;
	/**
	 * 产生时间
	 */
	@Column(name = "occurrence_time")
	private Date occurrenceTime;
	/**
	 * 发布人ID
	 */
	@JoinColumn(name = "publisher_id")
	@ManyToOne
	private Publisher publisher;
	/**
	 * 产生流水的对象类型
	 */
	@Convert(converter = CapitalFlowExtendTypeConverter.class)
	@Column(name = " extend_type")
	private CapitalFlowExtendType extendType;
	/**
	 * 产生流水的对象ID
	 */
	@Column(name = "extend_id")
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

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getCapitalFlowType() {
		String capitalFlowType = null;
		if (type != null) {
			capitalFlowType = type.getType();
		}
		return capitalFlowType;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
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

	public Long getPublisherId() {
		if (publisher != null) {
			return publisher.getId();
		}
		return null;
	}

	public String getPublisherPhone() {
		if (publisher != null) {
			return publisher.getPhone();
		}
		return null;
	}

	public String getPublisherSerialCode() {
		if (publisher != null) {
			return publisher.getSerialCode();
		}
		return null;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

}
