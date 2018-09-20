package com.waben.stock.datalayer.stockcontent.entity;

import javax.persistence.*;

/**
 * 股票
 *
 * @author luomengan
 */
@Entity
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 股票名称
	 */
	@Column
	private String name;
	/**
	 * 股票代码
	 */
	@Column(unique = true, length = 10)
	private String code;
	/**
	 * 缩写
	 */
	@Column
	private String abbr;
	/**
	 * 股票状态
	 * <p>
	 * 是否停牌，非开始闭市状态
	 * </p>
	 */
	@Column
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
	 * 所属交易指数
	 */
	@JoinColumn(name = "exponent", referencedColumnName = "exponent_code")
	@ManyToOne(targetEntity = StockExponent.class)
	private StockExponent exponent;

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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public StockExponent getExponent() {
		return exponent;
	}

	public void setExponent(StockExponent exponent) {
		this.exponent = exponent;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
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

}
