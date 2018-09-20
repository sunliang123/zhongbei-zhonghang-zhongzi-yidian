package com.waben.stock.interfaces.dto.admin.stockoption;

public class StockOptionBlacklistAdminDto {

	private Long id;
	/**
	 * 股票名称
	 */
	private String name;
	/**
	 * 股票代码
	 */
	private String code;
	/**
	 * 缩写
	 */
	private String abbr;
	/**
	 * 股票状态
	 * <p>
	 * 是否停牌，非开始闭市状态
	 * </p>
	 */
	private Boolean status;
	/**
	 * 期权交易状态
	 * <ul>
	 * <li>1正常，因为该字段为新加的字段，所以数据为空的情况也是表示正常状态</li>
	 * <li>2黑名单，不能正常交易</li>
	 * </ul>
	 */
	private Integer stockOptionState;
	/**
	 * 将该股票加入期权黑名单的备注
	 */
	private String stockOptionBlackRemark;
	/**
	 * 是否有接口费率，0表示无，1表示有
	 */
	private Integer hasInterfaceRatio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getStockOptionState() {
		return stockOptionState;
	}

	public void setStockOptionState(Integer stockOptionState) {
		this.stockOptionState = stockOptionState;
	}

	public String getStockOptionBlackRemark() {
		return stockOptionBlackRemark;
	}

	public void setStockOptionBlackRemark(String stockOptionBlackRemark) {
		this.stockOptionBlackRemark = stockOptionBlackRemark;
	}

	public Integer getHasInterfaceRatio() {
		return hasInterfaceRatio;
	}

	public void setHasInterfaceRatio(Integer hasInterfaceRatio) {
		this.hasInterfaceRatio = hasInterfaceRatio;
	}

}
