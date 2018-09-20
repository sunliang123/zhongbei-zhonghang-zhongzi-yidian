package com.waben.stock.interfaces.dto.publisher;

import java.math.BigDecimal;
import java.util.Date;

import com.waben.stock.interfaces.enums.WithdrawalsState;

/**
 * 提现订单
 * 
 * @author luomengan
 *
 */
public class WithdrawalsOrderDto {

	private Long id;
	/**
	 * 提现单号
	 */
	private String withdrawalsNo;
	/**
	 * 第三方单号
	 */
	private String thirdWithdrawalsNo;
	/**
	 * 请求第三方代扣响应码
	 */
	private String thirdRespCode;
	/**
	 * 请求第三方代扣响应提示
	 */
	private String thirdRespMsg;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 提现状态
	 */
	private WithdrawalsState state;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份证号
	 */
	private String idCard;
	/**
	 * 银行卡号
	 */
	private String bankCard;
	/**
	 * 发布人ID
	 */
	private Long publisherId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
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

	public WithdrawalsState getState() {
		return state;
	}

	public void setState(WithdrawalsState state) {
		this.state = state;
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

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
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

}
