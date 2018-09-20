package com.waben.stock.applayer.tactics.dto.publisher;

import java.math.BigDecimal;

public class PromotionBaseDto {

	/**
	 * 推广用户总数
	 */
	private int promotionCount;
	/**
	 * 推广总收益
	 */
	private BigDecimal promotionProfit;
	/**
	 * 推广码
	 */
	private String promotionCode;

	public int getPromotionCount() {
		return promotionCount;
	}

	public void setPromotionCount(int promotionCount) {
		this.promotionCount = promotionCount;
	}

	public BigDecimal getPromotionProfit() {
		return promotionProfit != null ? promotionProfit : new BigDecimal(0);
	}

	public void setPromotionProfit(BigDecimal promotionProfit) {
		this.promotionProfit = promotionProfit;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

}
