package com.waben.stock.interfaces.dto.manage;

/**
 * 支付支行信息
 * 
 * @author luomengan
 *
 */
public class CnapsDto {

	private Long id;
	/**
	 * cnaps
	 */
	private String cnaps;
	/**
	 * clscode
	 */
	private String clsCode;
	/**
	 * 城市代码
	 */
	private String cityCode;
	/**
	 * 支行名称
	 */
	private String bnkName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnaps() {
		return cnaps;
	}

	public void setCnaps(String cnaps) {
		this.cnaps = cnaps;
	}

	public String getClsCode() {
		return clsCode;
	}

	public void setClsCode(String clsCode) {
		this.clsCode = clsCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getBnkName() {
		return bnkName;
	}

	public void setBnkName(String bnkName) {
		this.bnkName = bnkName;
	}

}
