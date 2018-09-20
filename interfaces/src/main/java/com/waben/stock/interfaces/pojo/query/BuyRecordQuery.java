package com.waben.stock.interfaces.pojo.query;

import java.util.Date;

import com.waben.stock.interfaces.enums.BuyRecordState;

/**
 * 点买记录 查询条件
 * 
 * @author luomengan
 *
 */
public class BuyRecordQuery extends PageAndSortQuery {

	private Long publisherId;

	private BuyRecordState[] states;
	private Integer state;
	private Long investorId;
	private Date startCreateTime;
	private Date endCreateTime;

	public BuyRecordQuery() {
		super();
	}

	public BuyRecordQuery(int page, int size, Long publisherId,BuyRecordState[] states) {
		super();
		super.setPage(page);
		super.setSize(size);
		this.publisherId = publisherId;
		this.states = states;
	}

	public Long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	public BuyRecordState[] getStates() {
		return states;
	}

	public void setStates(BuyRecordState[] states) {
		this.states = states;
	}

	public Date getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(Date startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public Long getInvestorId() {
		return investorId;
	}

	public void setInvestorId(Long investor) {
		this.investorId = investor;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
