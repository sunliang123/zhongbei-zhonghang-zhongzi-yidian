package com.waben.stock.collector.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 策略发布人
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "collector_publisher")
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long dataId;
	private String domain;
	/**
	 * 序列码
	 */
	private String serialCode;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 密码
	 */
	@Column
	private String password;
	/**
	 * 推广码
	 */
	private String promotionCode;
	/**
	 * 推广人
	 */
	@Column
	private String promoter;
	/**
	 * 注册时间
	 */
	private Date createTime;
	/**
	 * 是否为测试用户，测试用户不能提现
	 */
	@Column(name = "is_test")
	private Boolean isTest;
	/**
	 * 用户使用的终端类型，I表示IOS，A表示Android，PC表示PC，H5表示移动端
	 */
	@Column(name = "end_type")
	private String endType;
	/**
	 * 头像
	 */
	@Column(name = "head_portrait")
	private String headPortrait;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSerialCode() {
		return serialCode;
	}

	public void setSerialCode(String serialCode) {
		this.serialCode = serialCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getPromoter() {
		return promoter;
	}

	public void setPromoter(String promoter) {
		this.promoter = promoter;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	public String getEndType() {
		return endType;
	}

	public void setEndType(String endType) {
		this.endType = endType;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
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
