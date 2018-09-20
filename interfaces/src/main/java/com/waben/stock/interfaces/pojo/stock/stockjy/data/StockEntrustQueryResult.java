package com.waben.stock.interfaces.pojo.stock.stockjy.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Created by yuyidi on 2017/12/1.
 * @desc  股票委托查询结果
 */
public class StockEntrustQueryResult extends CommonErrorInfo{
    /**
     * 市场信息
     */
    @JsonProperty("exchange_type")
    private String exchangeType;
    /**
     * 委托编号
     */
    @JsonProperty("entrust_no")
    private String entrustNo;
    /**
     * 可撤单标识
     */
    @JsonProperty("withdraw_flag")
    private String withdrawFlag;
    /**
     * 股东账号
     */
    @JsonProperty("stock_account")
    private String stockAccount;
    /**
     * 股票代码
     */
    @JsonProperty("stock_code")
    private String stockCode;
    /**
     * 股票名称
     */
    @JsonProperty("stock_name")
    private String stockName;
    /**
     * 买卖方向
     */
    @JsonProperty("entrust_bs")
    private String entrustBs;
    /**
     *价格
     */
    @JsonProperty("entrust_price")
    private String entrustPrice;
    /**
     * 数量
     */
    @JsonProperty("entrust_amount")
    private String entrustAmount;
    /**
     * 成交数量
     */
    @JsonProperty("business_amount")
    private String businessAmount;
    /**
     * 成交价格
     */
    @JsonProperty("business_price")
    private String businessPrice;

    /**
     * 委托状态
     */
    @JsonProperty("entrust_status")
    private String entrustStatus;
    /**
     * 委托时间
     */
    @JsonProperty("entrust_time")
    private String entrustTime;
    /**
     * 委托日期
     */
    @JsonProperty("entrust_date")
    private String entrustDate;
    /**
     * 定位串
     */
    @JsonProperty("position_str")
    private String positionStr;

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getEntrustNo() {
        return entrustNo;
    }

    public void setEntrustNo(String entrustNo) {
        this.entrustNo = entrustNo;
    }

    public String getWithdrawFlag() {
        return withdrawFlag;
    }

    public void setWithdrawFlag(String withdrawFlag) {
        this.withdrawFlag = withdrawFlag;
    }

    public String getStockAccount() {
        return stockAccount;
    }

    public void setStockAccount(String stockAccount) {
        this.stockAccount = stockAccount;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getEntrustBs() {
        return entrustBs;
    }

    public void setEntrustBs(String entrustBs) {
        this.entrustBs = entrustBs;
    }

    public String getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(String entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public String getEntrustAmount() {
        return entrustAmount;
    }

    public void setEntrustAmount(String entrustAmount) {
        this.entrustAmount = entrustAmount;
    }

    public String getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(String businessPrice) {
        this.businessPrice = businessPrice;
    }

    public String getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(String businessAmount) {
        this.businessAmount = businessAmount;
    }

    public String getEntrustStatus() {
        return entrustStatus;
    }

    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus;
    }

    public String getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }

    public String getEntrustDate() {
        return entrustDate;
    }

    public void setEntrustDate(String entrustDate) {
        this.entrustDate = entrustDate;
    }

    @Override
    public String getPositionStr() {
        return positionStr;
    }

    @Override
    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }
}
