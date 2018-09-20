package com.waben.stock.interfaces.dto.admin.stockoption;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StockOptionRiskAdminDto {
	/**
	 * 股票代码
	 */
	private String stockCode;
	/**
	 * 股票名称
	 */
	private String stockName;
	/**
	 * 周期ID
	 */
	private Long cycleId;
	/**
	 * 周期名称
	 */
	private String cycleName;
	/**
	 * 期权交易限额
	 * <p>
	 * 个股设置优先于全局设置
	 * </p>
	 */
	private BigDecimal amountLimitFinal;
	/**
	 * 已使用的额度
	 * <p>
	 * 当天有效
	 * </p>
	 */
	private BigDecimal amountLimitUsed;
	/**
	 * 剩余的额度
	 */
	private BigDecimal amountLimitLeft;
	/**
	 * 机构接口报价的权利金比例
	 */
	private BigDecimal interfaceRatio;
	/**
	 * 后台设置的权利金比例
	 * <p>
	 * 当天有效，设置的权利金比例 优先于 机构接口报价的权利金比例
	 * </p>
	 */
	private BigDecimal interfaceRatioReset;
	/**
	 * 最终的平台权利金比例
	 */
	private BigDecimal interfaceRatioFinal;
	/**
	 * 加价比例
	 */
	private BigDecimal markupRatio;
	/**
	 * 用户询价权利金比例
	 */
	private BigDecimal platformRatio;
	/**
	 * 机构真实成交的权利金比例
	 * <p>
	 * 当天有效，以当前最新成交的机构权利金比例为准
	 * </p>
	 */
	private BigDecimal orgRatio;

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Long getCycleId() {
		return cycleId;
	}

	public void setCycleId(Long cycleId) {
		this.cycleId = cycleId;
	}

	public String getCycleName() {
		return cycleName;
	}

	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}

	public BigDecimal getAmountLimitFinal() {
		return amountLimitFinal;
	}

	public void setAmountLimitFinal(BigDecimal amountLimitFinal) {
		this.amountLimitFinal = amountLimitFinal;
	}

	public BigDecimal getAmountLimitUsed() {
		return amountLimitUsed;
	}

	public void setAmountLimitUsed(BigDecimal amountLimitUsed) {
		this.amountLimitUsed = amountLimitUsed;
	}

	public BigDecimal getAmountLimitLeft() {
		if (amountLimitFinal != null) {
			if (amountLimitUsed != null) {
				if (amountLimitFinal.compareTo(amountLimitUsed) > 0) {
					return amountLimitFinal.subtract(amountLimitUsed);
				} else {
					return BigDecimal.ZERO;
				}
			} else {
				return amountLimitFinal;
			}
		}
		return amountLimitLeft;
	}

	public void setAmountLimitLeft(BigDecimal amountLimitLeft) {
		this.amountLimitLeft = amountLimitLeft;
	}

	public BigDecimal getInterfaceRatio() {
		return interfaceRatio;
	}

	public void setInterfaceRatio(BigDecimal interfaceRatio) {
		this.interfaceRatio = interfaceRatio;
	}

	public BigDecimal getInterfaceRatioReset() {
		return interfaceRatioReset;
	}

	public void setInterfaceRatioReset(BigDecimal interfaceRatioReset) {
		this.interfaceRatioReset = interfaceRatioReset;
	}

	public BigDecimal getMarkupRatio() {
		return markupRatio;
	}

	public void setMarkupRatio(BigDecimal markupRatio) {
		this.markupRatio = markupRatio;
	}

	public BigDecimal getOrgRatio() {
		return orgRatio;
	}

	public void setOrgRatio(BigDecimal orgRatio) {
		this.orgRatio = orgRatio;
	}

	public BigDecimal getInterfaceRatioFinal() {
		return interfaceRatioFinal;
	}

	public void setInterfaceRatioFinal(BigDecimal interfaceRatioFinal) {
		this.interfaceRatioFinal = interfaceRatioFinal;
	}

	public BigDecimal getPlatformRatio() {
		if (interfaceRatioFinal != null && markupRatio != null) {
			return interfaceRatioFinal.add(interfaceRatioFinal.multiply(markupRatio.divide(new BigDecimal("100"))))
					.setScale(4, RoundingMode.HALF_EVEN);
		}
		return platformRatio;
	}

	public void setPlatformRatio(BigDecimal platformRatio) {
		this.platformRatio = platformRatio;
	}

}
