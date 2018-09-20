package com.waben.stock.applayer.tactics.crawler.model.crawler;

import java.io.Serializable;

/**
 * Created by yhj on 17/5/3.
 */
public class StockBaseMessage implements Serializable {

    private static final long serialVersionUID = 5325955439279924149L;

    private String stockCode;
    private String stockName;
    private String typeName;
    // 信息来源，比如 sina
    private String infoFrom;

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
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getInfoFrom() {
        return infoFrom;
    }
    public void setInfoFrom(String infoFrom) {
        this.infoFrom = infoFrom;
    }

}
