package com.waben.stock.interfaces.dto.admin.publisher;

import com.waben.stock.interfaces.dto.publisher.CapitalFlowDto;

public class CapitalFlowAdminDto extends CapitalFlowDto {

	/**
	 * 发布人姓名
	 * <p>
	 * 实名认证的姓名
	 * </p>
	 */
	private String publisherName;
	/**
	 * 发布人手机号
	 */
	private String publisherPhone;
	/**
	 * 股票代码
	 */
	private String stockCode;
	/**
	 * 股票名称
	 */
	private String stockName;
	/**
	 * 股票代码（点买记录）
	 */
	private String bStockCode;
	/**
	 * 股票名称（点买记录）
	 */
	private String bStockName;
	/**
	 * 股票代码（期权交易）
	 */
	private String sStockCode;
	/**
	 * 股票名称（期权交易）
	 */
	private String sStockName;
	/**
	 * 充值方式
	 */
	private Integer paymentType;
	/**
	 * 银行卡号
	 */
	private String bankCard;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 是否测试
	 */
	private Boolean isTest;

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	public String getStockCode() {
		if (stockCode != null) {
			return stockCode;
		}
		return bStockCode != null ? bStockCode : sStockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		if (stockName != null) {
			return stockName;
		}
		return bStockName != null ? bStockName : sStockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getbStockCode() {
		return bStockCode;
	}

	public void setbStockCode(String bStockCode) {
		this.bStockCode = bStockCode;
	}

	public String getbStockName() {
		return bStockName;
	}

	public void setbStockName(String bStockName) {
		this.bStockName = bStockName;
	}

	public String getsStockCode() {
		return sStockCode;
	}

	public void setsStockCode(String sStockCode) {
		this.sStockCode = sStockCode;
	}

	public String getsStockName() {
		return sStockName;
	}

	public void setsStockName(String sStockName) {
		this.sStockName = sStockName;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

}