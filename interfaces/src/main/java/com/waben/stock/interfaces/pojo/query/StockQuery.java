package com.waben.stock.interfaces.pojo.query;

/**
 * @author Created by yuyidi on 2017/11/22.
 * @desc
 */
public class StockQuery extends PageAndSortQuery {

    private String stockName;
    private String stockCode;
    private Integer status;
    private String keyword;
    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
