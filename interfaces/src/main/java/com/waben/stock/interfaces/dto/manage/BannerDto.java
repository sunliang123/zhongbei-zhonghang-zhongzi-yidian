package com.waben.stock.interfaces.dto.manage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 轮播 Dto
 * 
 * @author luomengan
 *
 */
@ApiModel(value = "BannerDto", description = "轮播图对象")
public class BannerDto {
	@ApiModelProperty(value = "轮播图id")
	private Long id;
	@ApiModelProperty(value = "轮播图链接")
	private String link;
	@ApiModelProperty(value = "轮播图描述")
	private String description;
	@ApiModelProperty(value = "轮播图顺序")
	private Integer sort;
	@ApiModelProperty(value = "是否可用")
	private Boolean enable;
	@ApiModelProperty(value = "创建时间")
	private Date createTime = new Date();
	@ApiModelProperty(value = "跳转链接对象Id")
	private Long bannerForwardId;
	/**
	 * 跳转类型
	 */
	private String forward;

	public Long getId() {
		return id;
	}

	private BannerForwardDto bannerForwardDto;

	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getBannerForwardId() {
		return bannerForwardId;
	}

	public void setBannerForwardId(Long bannerForwardId) {
		this.bannerForwardId = bannerForwardId;
	}

	public BannerForwardDto getBannerForwardDto() {
		return bannerForwardDto;
	}

	public void setBannerForwardDto(BannerForwardDto bannerForwardDto) {
		this.bannerForwardDto = bannerForwardDto;
	}

	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

}
