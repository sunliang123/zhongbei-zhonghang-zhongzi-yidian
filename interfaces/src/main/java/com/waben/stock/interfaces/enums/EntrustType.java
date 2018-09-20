package com.waben.stock.interfaces.enums;

/**
 * @author Created by yuyidi on 2017/12/1.
 * @desc 股票委托类型
 */
public enum EntrustType {

    BUY("1", "买入"),
    SELL("2", "卖出");

    private String type;
    private String value;

    EntrustType(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
