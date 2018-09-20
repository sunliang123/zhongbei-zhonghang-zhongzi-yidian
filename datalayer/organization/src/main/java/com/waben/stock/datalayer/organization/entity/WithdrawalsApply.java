package com.waben.stock.datalayer.organization.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.waben.stock.datalayer.organization.entity.enumconverter.WithdrawalsApplyStateConverter;
import com.waben.stock.interfaces.enums.WithdrawalsApplyState;

/**
 * 提现申请
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "p_withdrawals_apply")
public class WithdrawalsApply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 手续费
	 */
	private BigDecimal processFee;
	/**
	 * 申请单号
	 */
	private String applyNo;
	/**
	 * 申请时间
	 */
	private Date applyTime;
	/**
	 * 状态
	 */
	@Convert(converter = WithdrawalsApplyStateConverter.class)
	private WithdrawalsApplyState state;
	/**
	 * 拒绝的原因
	 */
	private String refusedRemark;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 绑卡ID
	 */
	private Long bindCardId;
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
	 * 第三方代扣接口响应代码
	 */
	private String thirdRespCode;
	/**
	 * 第三方代扣接口响应提示
	 */
	private String thirdRespMsg;
	/**
	 * 对应的机构
	 */
	@OneToOne
	@JoinColumn(name = "org_id")
	private Organization org;
	/**
	 * 对应的第三方提现单号
	 */
	private String thirdWithdrawalsNo;
	/**
	 * 机构ID
	 */
	@Transient
	private Long orgId;
	/**
	 * 机构代码
	 */
	@Transient
	private String orgCode;
	/**
	 * 机构名称
	 */
	@Transient
	private String orgName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public WithdrawalsApplyState getState() {
		return state;
	}

	public void setState(WithdrawalsApplyState state) {
		this.state = state;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public Long getOrgId() {
		if (org != null) {
			return org.getId();
		}
		return orgId;
	}

	public String getOrgCode() {
		if (org != null) {
			return org.getCode();
		}
		return orgCode;
	}

	public String getOrgName() {
		if (org != null) {
			return org.getName();
		}
		return orgName;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public Long getBindCardId() {
		return bindCardId;
	}

	public void setBindCardId(Long bindCardId) {
		this.bindCardId = bindCardId;
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

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getThirdRespCode() {
		return thirdRespCode;
	}

	public void setThirdRespCode(String thirdRespCode) {
		this.thirdRespCode = thirdRespCode;
	}

	public String getThirdRespMsg() {
		return thirdRespMsg;
	}

	public void setThirdRespMsg(String thirdRespMsg) {
		this.thirdRespMsg = thirdRespMsg;
	}

	public String getThirdWithdrawalsNo() {
		return thirdWithdrawalsNo;
	}

	public void setThirdWithdrawalsNo(String thirdWithdrawalsNo) {
		this.thirdWithdrawalsNo = thirdWithdrawalsNo;
	}

	public String getRefusedRemark() {
		return refusedRemark;
	}

	public void setRefusedRemark(String refusedRemark) {
		this.refusedRemark = refusedRemark;
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

	public BigDecimal getProcessFee() {
		return processFee;
	}

	public void setProcessFee(BigDecimal processFee) {
		this.processFee = processFee;
	}

}
