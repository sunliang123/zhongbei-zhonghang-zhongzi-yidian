package com.waben.stock.datalayer.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 银行信息
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "bank_info")
public class BankInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 银行代码
	 */
	@Column(name = "bank_code")
	private String bankCode;
	/**
	 * 银行名称
	 */
	@Column(name = "bank_name")
	private String bankName;
	/**
	 * 图标链接
	 */
	@Column(name = "icon_link")
	private String iconLink;
	/**
	 * 是否支持app支付
	 */
	@Column(name = "app_support")
	private Boolean appSupport;
	/**
	 * 是否支持pc支付
	 */
	@Column(name = "pc_support")
	private Boolean pcSupport;
	/**
	 * 是否可用
	 */
	@Column(name = "enable")
	private Boolean enable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIconLink() {
		return iconLink;
	}

	public void setIconLink(String iconLink) {
		this.iconLink = iconLink;
	}

	public Boolean getAppSupport() {
		return appSupport;
	}

	public void setAppSupport(Boolean appSupport) {
		this.appSupport = appSupport;
	}

	public Boolean getPcSupport() {
		return pcSupport;
	}

	public void setPcSupport(Boolean pcSupport) {
		this.pcSupport = pcSupport;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
