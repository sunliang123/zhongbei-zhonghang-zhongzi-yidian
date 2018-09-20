package com.waben.stock.interfaces.dto.organization;

import java.math.BigDecimal;
import java.util.Date;

import com.waben.stock.interfaces.enums.CapitalFlowType;

public class TradingFowDto {

	/**
	 * 订单ID
	 */
	private Long id;

	/**
	 * 客户姓名
	 */
	private String customerName;

	/**
	 * 交易账号
	 */
	private String tradingNumber;

	/**
	 * 交易编号
	 */
	private String flowNo;

	/**
	 * 交易时间
	 */
	private Date occurrenceTime;

	/**
	 * 业务类型
	 */
	private CapitalFlowType type;

	/**
	 * 交易金额
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
	 * 股票代码
	 */
	private String stockCode;

	/**
	 * 标的股票
	 */
	private String markedStock;

	/**
	 * 所属代理商代码
	 */
	private String agentCode;

	/**
	 * 所属代理商代码名称
	 */
	private String agentCodeName;

/*	*//**
	 * 产生流水的对象类型
	 *//*
	private CapitalFlowExtendType extendType;
	*//**
	 * 产生流水的对象ID
	 *//*
	private Long extendId;*/
	/** 是否测试（0或null为正式，1为测试） **/
	private Boolean isTest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTradingNumber() {
		return tradingNumber;
	}

	public void setTradingNumber(String tradingNumber) {
		this.tradingNumber = tradingNumber;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public Date getOccurrenceTime() {
		return occurrenceTime;
	}

	public void setOccurrenceTime(Date occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
	}

	public CapitalFlowType getType() {
		return type;
	}

	public void setType(CapitalFlowType type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getMarkedStock() {
		return markedStock;
	}

	public void setMarkedStock(String markedStock) {
		this.markedStock = markedStock;
	}

	public String getAgentCodeName() {
		return agentCodeName;
	}

	public void setAgentCodeName(String agentCodeName) {
		this.agentCodeName = agentCodeName;
	}
/*
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
	}*/

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

}
