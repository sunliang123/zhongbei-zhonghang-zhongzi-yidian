package com.waben.stock.datalayer.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 支付支行信息
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "cnaps")
public class Cnaps {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * cnaps
	 */
	@Column(name = "")
	private String cnaps;
	/**
	 * clscode
	 */
	@Column(name = "cls_code")
	private String clsCode;
	/**
	 * 城市代码
	 */
	@Column(name = "city_code")
	private String cityCode;
	/**
	 * 支行名称
	 */
	@Column(name = "bnk_name")
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
