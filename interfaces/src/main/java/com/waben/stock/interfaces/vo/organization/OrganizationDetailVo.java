package com.waben.stock.interfaces.vo.organization;

import com.waben.stock.interfaces.dto.organization.OrganizationDto;

public class OrganizationDetailVo extends OrganizationVo {

	private Integer childOrgCount;

	private Integer publisherCount;

	public Integer getChildOrgCount() {
		return childOrgCount;
	}

	public void setChildOrgCount(Integer childOrgCount) {
		this.childOrgCount = childOrgCount;
	}

	public Integer getPublisherCount() {
		return publisherCount;
	}

	public void setPublisherCount(Integer publisherCount) {
		this.publisherCount = publisherCount;
	}

}
