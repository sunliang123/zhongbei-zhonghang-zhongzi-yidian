package com.waben.stock.interfaces.dto.organization;

import java.math.BigDecimal;
import java.util.Date;

import com.waben.stock.interfaces.enums.WithdrawalsApplyState;

/**
 * 提现申请
 * 
 * @author luomengan
 *
 */
public class WithdrawalsApplyDto {

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
	private WithdrawalsApplyState state;
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
	 * 机构ID
	 */
	private Long orgId;
	/**
	 * 机构代码
	 */
	private String orgCode;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 对应的第三方提现单号
	 */
	private String thirdWithdrawalsNo;
	/**
	 * 拒绝的原因
	 */
	private String refusedRemark;

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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getRefusedRemark() {
		return refusedRemark;
	}

	public void setRefusedRemark(String refusedRemark) {
		this.refusedRemark = refusedRemark;
	}

	public BigDecimal getProcessFee() {
		return processFee;
	}

	public void setProcessFee(BigDecimal processFee) {
		this.processFee = processFee;
	}

}
