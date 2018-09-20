package com.waben.stock.interfaces.vo.publisher;

import java.math.BigDecimal;
import java.util.Date;

public class CapitalAccountVo {

    private Long id;
    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 账户可用余额
     */
    private BigDecimal availableBalance;
    /**
     * 冻结资金
     */
    private BigDecimal frozenCapital;
    /**
     * 支付密码
     */
    private String paymentPassword;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 发布人ID
     */
    private Long publisherId;
    /**
     * 发布人序列号
     */
    private String publisherSerialCode;

    private String publisherPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getFrozenCapital() {
        return frozenCapital;
    }

    public void setFrozenCapital(BigDecimal frozenCapital) {
        this.frozenCapital = frozenCapital;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherSerialCode() {
        return publisherSerialCode;
    }

    public void setPublisherSerialCode(String publisherSerialCode) {
        this.publisherSerialCode = publisherSerialCode;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }
}
