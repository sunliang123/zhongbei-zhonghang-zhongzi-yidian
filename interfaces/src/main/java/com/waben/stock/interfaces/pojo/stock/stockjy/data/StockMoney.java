package com.waben.stock.interfaces.pojo.stock.stockjy.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Created by yuyidi on 2017/12/1.
 * @desc 资金账户资金信息
 */
public class StockMoney extends CommonErrorInfo{

    @JsonProperty("asset_balance")
    private Double assetBalance;
    @JsonProperty("current_balance")
    private Double currentBalance;
    @JsonProperty("enable_balance")
    private Double enableBalance;
    @JsonProperty("fetch_balance")
    private Double fetchBalance;
    @JsonProperty("market_value")
    private Double marketValue;
    @JsonProperty("money_type")
    private String moneyType;

    public Double getAssetBalance() {
        return assetBalance;
    }

    public void setAssetBalance(Double assetBalance) {
        this.assetBalance = assetBalance;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Double getEnableBalance() {
        return enableBalance;
    }

    public void setEnableBalance(Double enableBalance) {
        this.enableBalance = enableBalance;
    }

    public Double getFetchBalance() {
        return fetchBalance;
    }

    public void setFetchBalance(Double fetchBalance) {
        this.fetchBalance = fetchBalance;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }
}
