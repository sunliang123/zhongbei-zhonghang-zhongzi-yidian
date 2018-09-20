package com.waben.stock.interfaces.vo.buyrecord;

import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;

import java.math.BigDecimal;
import java.util.Date;

public class SettlementVo {
    private Long id;
    /**
     * 对应的点买记录
     */
    private BuyRecordDto buyRecord;
    /**
     * 盈亏
     */
    private BigDecimal profitOrLoss;
    /**
     * 发布人盈亏
     */
    private BigDecimal publisherProfitOrLoss;
    /**
     * 投资人盈亏
     */
    private BigDecimal investorProfitOrLoss;
    /**
     * 结算时间
     */
    private Date settlementTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BuyRecordDto getBuyRecord() {
        return buyRecord;
    }

    public void setBuyRecord(BuyRecordDto buyRecord) {
        this.buyRecord = buyRecord;
    }

    public BigDecimal getProfitOrLoss() {
        return profitOrLoss;
    }

    public void setProfitOrLoss(BigDecimal profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }

    public BigDecimal getPublisherProfitOrLoss() {
        return publisherProfitOrLoss;
    }

    public void setPublisherProfitOrLoss(BigDecimal publisherProfitOrLoss) {
        this.publisherProfitOrLoss = publisherProfitOrLoss;
    }

    public BigDecimal getInvestorProfitOrLoss() {
        return investorProfitOrLoss;
    }

    public void setInvestorProfitOrLoss(BigDecimal investorProfitOrLoss) {
        this.investorProfitOrLoss = investorProfitOrLoss;
    }

    public Date getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(Date settlementTime) {
        this.settlementTime = settlementTime;
    }
}
