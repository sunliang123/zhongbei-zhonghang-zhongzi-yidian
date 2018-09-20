package com.waben.stock.interfaces.vo.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 机构账户
 * 
 * @author luomengan
 *
 */
@ApiModel(value="OrganizationAccountVo",description="代理商资产对象")
public class OrganizationAccountVo {
	@ApiModelProperty(value = "代理商资产id")
	private Long id;
	/**
	 * 账户余额
	 */
	@ApiModelProperty(value = "账户余额")
	private BigDecimal balance;
	/**
	 * 账户可用余额
	 */
	@ApiModelProperty(value = "账户可用余额")
	private BigDecimal availableBalance;
	/**
	 * 冻结资金
	 */
	@ApiModelProperty(value = "冻结资金")
	private BigDecimal frozenCapital;
	/**
	 * 支付密码
	 */
	@ApiModelProperty(value = "支付密码")
	private String paymentPassword;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**
	 * 分润金额
	 */
	@ApiModelProperty(value = "分润金额")
	private BigDecimal distribution;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "代理商代码")
	private String code;

	@ApiModelProperty(value = "代理商名称")
	private String name;

	@ApiModelProperty(value = "代理商层级")
	private Integer level;

	@ApiModelProperty(value = "状态")
	private Integer state;

	@ApiModelProperty(value = "下线代理")
	private Integer childOrgCount;

	@ApiModelProperty(value = "推广客户")
	private Integer popPulisherCount;

	@ApiModelProperty(value = "代理商姓名")
	private String orgName;

	@ApiModelProperty(value = "手机号")
	private String orgPhone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public BigDecimal getFrozenCapital() {
		return frozenCapital;
	}

	public void setFrozenCapital(BigDecimal frozenCapital) {
		this.frozenCapital = frozenCapital;
	}

	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getDistribution() {
		return distribution;
	}

	public void setDistribution(BigDecimal distribution) {
		this.distribution = distribution;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getChildOrgCount() {
		return childOrgCount;
	}

	public void setChildOrgCount(Integer childOrgCount) {
		this.childOrgCount = childOrgCount;
	}

	public Integer getPopPulisherCount() {
		return popPulisherCount;
	}

	public void setPopPulisherCount(Integer popPulisherCount) {
		this.popPulisherCount = popPulisherCount;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgPhone() {
		return orgPhone;
	}

	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}
}
