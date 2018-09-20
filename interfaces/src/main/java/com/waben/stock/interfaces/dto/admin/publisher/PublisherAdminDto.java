package com.waben.stock.interfaces.dto.admin.publisher;

import java.math.BigDecimal;
import java.util.Date;

public class PublisherAdminDto {

	/**
	 * 用户id
	 */
	private Long id;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 是否实名认证
	 */
	private boolean isRealName;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 账户余额
	 */
	private BigDecimal availableBalance;
	/**
	 * 注册时间
	 */
	private Date createTime;
	/**
	 * 用户使用的终端类型，I表示IOS，A表示Android，PC表示PC，H5表示移动端
	 */
	private String endType;
	/**
	 * 状态
	 * <ul>
	 * <li>1正常，因为该字段为新加的字段，所以数据为空的情况也是表示正常状态</li>
	 * <li>2黑名单，不能正常登陆</li>
	 * </ul>
	 */
	private Integer state;
	/**
	 * 是否为测试用户
	 */
	private Boolean isTest;

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

	public boolean isRealName() {
		if (name != null && !"".equals(name.trim())) {
			this.isRealName = true;
		} else {
			this.isRealName = false;
		}
		return this.isRealName;
	}

	public void setRealName(boolean isRealName) {
		this.isRealName = isRealName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEndType() {
		return endType;
	}

	public void setEndType(String endType) {
		this.endType = endType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Boolean getIsTest() {
		return isTest;
	}

	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

}
