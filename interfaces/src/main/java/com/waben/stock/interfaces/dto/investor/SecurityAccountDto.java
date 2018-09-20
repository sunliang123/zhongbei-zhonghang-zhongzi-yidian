package com.waben.stock.interfaces.dto.investor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Created by yuyidi on 2017/11/29.
 * @desc
 */
public class SecurityAccountDto implements Serializable{

    private Long id;
    private String name;
    /**
     * 股票账户
     */
    private String account;
    private String password;
    private String identity;
    /**
     * 账户余额
     */
    private BigDecimal amount;
    /**
     * 可用余额
     */
    private BigDecimal availability;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.amount = amount;
    }

    public BigDecimal getAvailability() {
        return availability;
    }

    public void setAvailability(BigDecimal availability) {
        this.availability = availability;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
