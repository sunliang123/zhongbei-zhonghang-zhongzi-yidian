package com.waben.stock.interfaces.pojo.query;

import com.waben.stock.interfaces.enums.BannerForwardCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BannerQuery",description = "轮播图查询对象")
public class BannerQuery extends PageAndSortQuery {
	@ApiModelProperty(value = "轮播图跳转类别")
	BannerForwardCategory category;
	@ApiModelProperty(value = "轮播图描述")
	private String description;
	@ApiModelProperty(value = "是否可用")
	private Integer enable;

	public BannerForwardCategory getCategory() {
		return category;
	}

	public void setCategory(BannerForwardCategory category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}
}
