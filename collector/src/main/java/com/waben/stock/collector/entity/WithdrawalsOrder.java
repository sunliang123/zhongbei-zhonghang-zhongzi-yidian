package com.waben.stock.collector.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 提现订单
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "collector_withdrawals_order")
public class WithdrawalsOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long dataId;
	private String domain;
	/**
	 * 提现单号
	 */
	@Column(name = "withdrawals_no")
	private String withdrawalsNo;
	/**
	 * 第三方单号
	 */
	@Column(name = "third_withdrawals_no")
	private String thirdWithdrawalsNo;
	/**
	 * 请求第三方代扣响应码
	 */
	@Column(name = "third_resp_code")
	private String thirdRespCode;
	/**
	 * 请求第三方代扣响应提示
	 */
	@Column(name = "third_resp_msg")
	private String thirdRespMsg;
	/**
	 * 金额
	 */
	@Column(name = "amount")
	private BigDecimal amount;
	/**
	 * 提现状态
	 */
	@Column(name = "state")
	private Integer state;
	/**
	 * 姓名
	 */
	@Column(name = "name")
	private String name;
	/**
	 * 身份证号
	 */
	@Column(name = "id_card")
	private String idCard;
	/**
	 * 银行卡号
	 */
	@Column(name = "bank_card")
	private String bankCard;
	/**
	 * 发布人ID
	 */
	@Column(name = "publisher_id")
	private Long publisherId;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWithdrawalsNo() {
		return withdrawalsNo;
	}

	public void setWithdrawalsNo(String withdrawalsNo) {
		this.withdrawalsNo = withdrawalsNo;
	}

	public String getThirdWithdrawalsNo() {
		return thirdWithdrawalsNo;
	}

	public void setThirdWithdrawalsNo(String thirdWithdrawalsNo) {
		this.thirdWithdrawalsNo = thirdWithdrawalsNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

}
