package com.waben.stock.interfaces.pojo.query;

import com.waben.stock.interfaces.enums.CapitalFlowExtendType;
import com.waben.stock.interfaces.enums.CapitalFlowType;

public class CapitalFlowExtendQuery extends PageAndSortQuery {

	private CapitalFlowType type;

	private CapitalFlowExtendType extendType;

	private Long extendId;

	private Long publisherId;

	public CapitalFlowType getType() {
		return type;
	}

	public void setType(CapitalFlowType type) {
		this.type = type;
	}

	public CapitalFlowExtendType getExtendType() {
		return extendType;
	}

	public void setExtendType(CapitalFlowExtendType extendType) {
		this.extendType = extendType;
	}

	public Long getExtendId() {
		return extendId;
	}

	public void setExtendId(Long extendId) {
		this.extendId = extendId;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

}
