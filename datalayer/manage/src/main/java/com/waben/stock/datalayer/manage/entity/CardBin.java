package com.waben.stock.datalayer.manage.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 银行卡片
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "card_bin")
public class CardBin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 银行名称
	 */
	@Column(name = "bank_name")
	private String bankName;
	/**
	 * 卡名称
	 */
	@Column(name = "card_name")
	private String cardName;
	/**
	 * 卡号长度
	 */
	@Column(name = "card_length")
	private Integer cardLength;
	/**
	 * 卡类别
	 */
	@Column(name = "card_type")
	private String cardType;
	/**
	 * 卡标识符长度
	 */
	@Column(name = "verify_length")
	private Integer verifyLength;
	/**
	 * 卡标识号取值
	 */
	@Column(name = "verify_code")
	private String verifyCode;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
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
