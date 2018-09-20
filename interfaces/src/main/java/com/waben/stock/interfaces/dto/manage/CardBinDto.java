package com.waben.stock.interfaces.dto.manage;

import java.util.Date;

/**
 * 银行卡片
 * 
 * @author luomengan
 *
 */
public class CardBinDto {

	private Long id;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 卡名称
	 */
	private String cardName;
	/**
	 * 卡号长度
	 */
	private Integer cardLength;
	/**
	 * 卡类别
	 */
	private String cardType;
	/**
	 * 卡标识符长度
	 */
	private Integer verifyLength;
	/**
	 * 卡标识号取值
	 */
	private String verifyCode;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public Integer getCardLength() {
		return cardLength;
	}

	public void setCardLength(Integer cardLength) {
		this.cardLength = cardLength;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Integer getVerifyLength() {
		return verifyLength;
	}

	public void setVerifyLength(Integer verifyLength) {
		this.verifyLength = verifyLength;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
