package com.waben.stock.interfaces.pojo.stock.stockjy.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
public class StockLoginInfo extends CommonErrorInfo{

    @JsonProperty("client_id")
    private String clientId;
    @JsonProperty("client_name")
    private String clientName;
    @JsonProperty("fund_account")
    private String fundAccount;
    @JsonProperty("token")
    private String tradeSession;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getFundAccount() {
        return fundAccount;
    }
    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }

    public String getTradeSession() {
        return tradeSession;
    }
    public void setTradeSession(String tradeSession) {
        this.tradeSession = tradeSession;
    }
}
