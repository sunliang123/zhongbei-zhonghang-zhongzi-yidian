package com.waben.stock.interfaces.pojo.query;

import java.util.Date;

public class CapitalAccountQuery extends PageAndSortQuery{

    private Long id;
    
    private Long publisherId;
    
    private Date beginTime;

	private Date endTime; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
