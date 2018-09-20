package com.waben.stock.interfaces.vo.investor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Created by hujian on 2018年1月10日
 */
public class SecurityAccountVo {
	private Long id;
	private String name;
    private String account;
    private BigDecimal amount;
	private BigDecimal availability;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if(amount==null) {
 			this.amount = new BigDecimal("0.00");
 			return;
		}
		this.amount = amount;
	}

	public BigDecimal getAvailability() {
		return availability;
	}

	public void setAvailability(BigDecimal availability) {
		if(availability==null) {
			this.availability = new BigDecimal("0.00");
			return;
		}
		this.availability = availability;
	}
}
