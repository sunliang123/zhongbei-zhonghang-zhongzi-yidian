package com.waben.stock.datalayer.investors.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/***
* @author yuyidi 2017-11-13 23:17:41
* @class com.waben.stock.datalayer.investors.entity.SecurityAccount
* @description 投资人证券资金账户
*/
@Entity
@Table(name = "security_account")
public class SecurityAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 账户名称
     */
    @Column
    private String name;
    /**
     * 账户ID
     */
    @Column
    private String identity;
    /**
     * 股票账户
     */
    @Column
    private String account;
    /**
     * 股票账户密码
     */
    @Column
    private String password;
    /**
     * 账户余额
     */
    @Column
    private BigDecimal amount;
    /**
     * 可用余额
     */
    @Column
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
