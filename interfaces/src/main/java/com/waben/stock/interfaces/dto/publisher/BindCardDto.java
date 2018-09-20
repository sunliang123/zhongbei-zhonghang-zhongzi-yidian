package com.waben.stock.interfaces.dto.publisher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.waben.stock.interfaces.enums.BindCardResourceType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BindCardDto {

	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份证号
	 */
	private String idCard;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 银行卡号
	 */
	private String bankCard;
	/**
	 * 银行代码
	 */
	private String bankCode;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 支行cnaps code
	 */
	private String branchCode;
	/**
	 * 支行名称
	 */
	private String branchName;
	/**
	 * 绑卡对象的资源类型
	 */
	private BindCardResourceType resourceType;
	/**
	 * 绑卡对象的ID
	 */
	private Long resourceId;
	/**
	 * 对应的支付平台编号
	 */
	private String contractNo;
	/**
	 * 对应的支付平台签约订单号
	 */
	private String signOrderNo;
	/**
	 * 是否需要验证卡信息
	 */
	private boolean needCheckCard = true;

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

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public BindCardResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(BindCardResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getSignOrderNo() {
		return signOrderNo;
	}

	public void setSignOrderNo(String signOrderNo) {
		this.signOrderNo = signOrderNo;
	}

	public boolean isNeedCheckCard() {
		return needCheckCard;
	}

	public void setNeedCheckCard(boolean needCheckCard) {
		this.needCheckCard = needCheckCard;
	}

}
