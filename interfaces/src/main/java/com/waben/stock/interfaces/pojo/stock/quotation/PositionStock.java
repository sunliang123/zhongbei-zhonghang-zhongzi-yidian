package com.waben.stock.interfaces.pojo.stock.quotation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PositionStock implements Serializable{
    private Long buyRecordId;
    private BigDecimal entrustPrice;
    private String stockCode;
    private String stockName;
    private BigDecimal profitPosition;
    private BigDecimal lossPosition;
    private Long investorId;
    private Boolean deferred;
    private Date buyingTime;
    private String tradeSession;
    private String windControlType;
    private Date expireTime;
    private String tradeNo;
    private Integer entrustNumber;
    public String getWindControlType() {
        return windControlType;
    }

    public void setWindControlType(String windControlType) {
        this.windControlType = windControlType;
    }
    public Long getBuyRecordId() {
        return buyRecordId;
    }

    public void setBuyRecordId(Long buyRecordId) {
        this.buyRecordId = buyRecordId;
    }
    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public Boolean getDeferred() {
        return deferred;
    }

    public void setDeferred(Boolean deferred) {
        this.deferred = deferred;
    }

    public Date getBuyingTime() {
        return buyingTime;
    }

    public void setBuyingTime(Date buyingTime) {
        this.buyingTime = buyingTime;
    }

    public String getTradeSession() {
        return tradeSession;
    }

    public void setTradeSession(String tradeSession) {
        this.tradeSession = tradeSession;
    }

    public BigDecimal getProfitPosition() {
        return profitPosition;
    }

    public void setProfitPosition(BigDecimal profitPosition) {
        this.profitPosition = profitPosition;
    }

    public BigDecimal getLossPosition() {
        return lossPosition;
    }

    public void setLossPosition(BigDecimal lossPosition) {
        this.lossPosition = lossPosition;
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public String getStockCode() {
        return stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Integer getEntrustNumber() {
        return entrustNumber;
    }

    public void setEntrustNumber(Integer entrustNumber) {
        this.entrustNumber = entrustNumber;
    }

    @Override
    public String toString() {
        return "PositionStock{" +
                "buyRecordId='" + buyRecordId + '\'' +
                ", entrustPrice=" + entrustPrice +
                ", stockCode='" + stockCode + '\'' +
                ", stockName='" + stockName + '\'' +
                ", profitPosition=" + profitPosition +
                ", lossPosition=" + lossPosition +
                ", investorId=" + investorId +
                ", deferred=" + deferred +
                ", buyingTime=" + buyingTime +
                ", tradeSession='" + tradeSession + '\'' +
                ", windControlType='" + windControlType + '\'' +
                '}';
    }
}
