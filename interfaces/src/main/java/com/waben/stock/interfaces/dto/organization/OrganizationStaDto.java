package com.waben.stock.interfaces.dto.organization;

import java.math.BigDecimal;

public class OrganizationStaDto extends OrganizationDto {

	/**
	 * 推广客户数
	 */
	private Integer promotionCount;
	/**
	 * 下线代理
	 */
	private Integer childrenCount;
	/**
	 * 账户余额
	 */
	private BigDecimal availableBalance;
	/**
	 * 绑卡姓名
	 */
	private String bindName;
	/**
	 * 绑卡手机号
	 */
	private String bingPhone;

	/**
	 * 结算方式
	 * 
	 */
	private Integer wsType;

	public Integer getPromotionCount() {
		return promotionCount;
	}

	public void setPromotionCount(Integer promotionCount) {
		this.promotionCount = promotionCount;
	}

	public Integer getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getBindName() {
		return bindName;
	}

	public void setBindName(String bindName) {
		this.bindName = bindName;
	}

	public String getBingPhone() {
		return bingPhone;
	}

	public void setBingPhone(String bingPhone) {
		this.bingPhone = bingPhone;
	}

	public Integer getWsType() {
		return wsType;
	}

	public void setWsType(Integer wsType) {
		this.wsType = wsType;
	}

}
