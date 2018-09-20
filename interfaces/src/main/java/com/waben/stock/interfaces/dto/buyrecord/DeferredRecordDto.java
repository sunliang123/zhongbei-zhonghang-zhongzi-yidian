package com.waben.stock.interfaces.dto.buyrecord;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 递延费
 * 
 * @author luomengan
 *
 */
public class DeferredRecordDto {

	private Long id;
	/**
	 * 费用
	 */
	private BigDecimal fee;
	/**
	 * 递延时间
	 */
	private Date deferredTime;
	/**
	 * 策略类型ID
	 */
	private Long strategyTypeId;
	/**
	 * 策略类型名称
	 */
	private String strategyTypeName;
	/**
	 * 周期
	 */
	private Integer cycle;
	/**
	 * 点买记录ID
	 */
	private Long buyRecordId;
	/**
	 * 发布人ID
	 */
	private Long publisherId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Date getDeferredTime() {
		return deferredTime;
	}

	public void setDeferredTime(Date deferredTime) {
		this.deferredTime = deferredTime;
	}

	public Long getStrategyTypeId() {
		return strategyTypeId;
	}

	public void setStrategyTypeId(Long strategyTypeId) {
		this.strategyTypeId = strategyTypeId;
	}

	public String getStrategyTypeName() {
		return strategyTypeName;
	}

	public void setStrategyTypeName(String strategyTypeName) {
		this.strategyTypeName = strategyTypeName;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Long getBuyRecordId() {
		return buyRecordId;
	}

	public void setBuyRecordId(Long buyRecordId) {
		this.buyRecordId = buyRecordId;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

}
