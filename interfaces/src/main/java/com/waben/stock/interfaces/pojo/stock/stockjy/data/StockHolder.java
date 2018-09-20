package com.waben.stock.interfaces.pojo.stock.stockjy.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc 股东账户信息
 */
public class StockHolder extends CommonErrorInfo{
    @JsonProperty("exchange_type")
    private String exchangeType;
    @JsonProperty("stock_account")
    private String stockAccount;

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getStockAccount() {
        return stockAccount;
    }

    public void setStockAccount(String stockAccount) {
        this.stockAccount = stockAccount;
    }
}
