package com.waben.stock.interfaces.vo.stockcontent;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc
 */
public class StockExponentVo {

    private Long id;
    /**
     * 股票指数名称
     */
    private String name;
    /**
     * 行情代码
     */
    private String quotation;
    /**
     * 交易所代码
     */
    private String exponentCode;

    private String openMarketTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(String quotation) {
        this.quotation = quotation;
    }

    public String getExponentCode() {
        return exponentCode;
    }

    public void setExponentCode(String exponentCode) {
        this.exponentCode = exponentCode;
    }

    public String getOpenMarketTime() {
        return openMarketTime;
    }

    public void setOpenMarketTime(String openMarketTime) {
        this.openMarketTime = openMarketTime;
    }

}
