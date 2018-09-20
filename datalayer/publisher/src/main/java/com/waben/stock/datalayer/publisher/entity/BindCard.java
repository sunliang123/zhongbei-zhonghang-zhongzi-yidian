package com.waben.stock.datalayer.publisher.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.waben.stock.datalayer.publisher.entity.enumconverter.BindCardResourceTypeConverter;
import com.waben.stock.interfaces.enums.BindCardResourceType;

/**
 * @author Created by yuyidi on 2017/11/10.
 * @desc
 */
@Entity
@Table(name = "bind_card")
public class BindCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 姓名
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 身份证号
	 */
	@Column(name = "idCard")
	private String idCard;
	/**
	 * 手机号
	 */
	@Column(name = "phone")
	private String phone;
	/**
	 * 银行卡号
	 */
	@Column(name = "bank_card")
	private String bankCard;
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
	 * 支行名称
	 */
	@Column(name = "branch_name")
	private String branchName;
	/**
	 * 支行cnaps code
	 */
	@Column(name = "branch_code")
	private String branchCode;
	/**
	 * 绑卡对象的资源类型
	 */
	@Convert(converter = BindCardResourceTypeConverter.class)
	@Column(name = "resource_type")
	private BindCardResourceType resourceType;
	/**
	 * 绑卡对象的ID
	 */
	@Column(name = "resource_id")
	private Long resourceId;
	/**
	 * 对应的支付平台编号
	 */
	@Column(name = "contract_no")
	private String contractNo;
	/**
	 * 对应的支付平台签约订单号
	 */
	@Column(name = "sign_order_no")
	private String signOrderNo;
	/**
	 * 绑卡时间
	 */
	private Date createTime;

	@Transient
	private boolean needCheckCard;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
