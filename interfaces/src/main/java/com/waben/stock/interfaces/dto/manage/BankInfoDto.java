package com.waben.stock.interfaces.dto.manage;

/**
 * 银行信息
 * 
 * @author luomengan
 *
 */
public class BankInfoDto {

	private Long id;
	/**
	 * 银行代码
	 */
	private String bankCode;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 图标链接
	 */
	private String iconLink;
	/**
	 * 是否支持app支付
	 */
	private Boolean appSupport;
	/**
	 * 是否支持pc支付
	 */
	private Boolean pcSupport;
	/**
	 * 是否可用
	 */
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
