package com.waben.stock.interfaces.dto.publisher;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Created by yuyidi on 2017/11/12.
 * @desc
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "PublisherDto",description = "会员对象")
public class PublisherDto {
	@ApiModelProperty(value = "会员id")
	private Long id;
	/**
	 * 序列码
	 */
	@ApiModelProperty(value = "序列码")
	private String serialCode;
	/**
	 * 电话
	 */
	@ApiModelProperty(value = "手机号码")
	private String phone;
	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码")
	private String password;
	/**
	 * 推广码
	 */
	@ApiModelProperty(value = "推广码")
	private String promotionCode;
	/**
	 * 推广人
	 */
	@ApiModelProperty(value = "推广人")
	private String promoter;
	/**
	 * 注册时间
	 */
	// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "注册时间")
	private Date createTime;
	/**
	 * 角色
	 */
	@ApiModelProperty(value = "角色")
	private Long role;
	/**
	 * 是否为测试用户
	 */
	@ApiModelProperty(value = "是否为测试用户")
	private Boolean isTest;
	/**
	 * 用户使用的终端类型，I表示IOS，A表示Android，H表示PC
	 */
	@ApiModelProperty(value = "用户使用的终端类型，I表示IOS，A表示Android，H表示PC")
	private String endType;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private String headPortrait;
	/**
	 * 状态
	 * <ul>
	 * <li>1正常，因为该字段为新加的字段，所以数据为空的情况也是表示正常状态</li>
	 * <li>2黑名单，不能正常登陆</li>
	 * </ul>
	 */
	@ApiModelProperty(value = "状态（1正常，2黑名单）")
	private Integer state;
	/**
	 * 发布人信息统计
	 */
	@ApiModelProperty(value = "会员信息统计")
	private PublisherInformationStatisticsDto publisherInformationStatisticsDto;

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

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
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

	public PublisherInformationStatisticsDto getPublisherInformationStatisticsDto() {
		return publisherInformationStatisticsDto;
	}

	public void setPublisherInformationStatisticsDto(
			PublisherInformationStatisticsDto publisherInformationStatisticsDto) {
		this.publisherInformationStatisticsDto = publisherInformationStatisticsDto;
	}

	public Long getStrategyCount() {
		if (publisherInformationStatisticsDto != null) {
			return publisherInformationStatisticsDto.getStrategyCount();
		}
		return null;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}
