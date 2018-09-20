package com.waben.stock.interfaces.pojo.query;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class StockOptionTradeQuery extends PageAndSortQuery {
    /**
     * 发布人手机号码
     */
    private Long publisherPhone;
    /**
     * 申购单号
     */
    private String tradeNo;
    /**
     * 申购状态
     */
    private Integer state;
    private Date beginTime;
    private Date endTime;
    /**
     * 是否测试
     */
    private Boolean isTest;

    private String stockCode;
    private BigDecimal nominalAmount;
    private String cycleName;

    private List<Long> publisherIds;
    public Long getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(Long publisherPhone) {
        this.publisherPhone = publisherPhone;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public Boolean getTest() {
        return isTest;
    }

    public void setTest(Boolean test) {
        isTest = test;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public BigDecimal getNominalAmount() {
        return nominalAmount;
    }

    public void setNominalAmount(BigDecimal nominalAmount) {
        this.nominalAmount = nominalAmount;
    }

    public String getCycleName() {
        return cycleName;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    public List<Long> getPublisherIds() {
        return publisherIds;
    }

    public void setPublisherIds(List<Long> publisherIds) {
        this.publisherIds = publisherIds;
    }
}
