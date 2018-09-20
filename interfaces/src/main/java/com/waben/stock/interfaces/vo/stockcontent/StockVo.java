package com.waben.stock.interfaces.vo.stockcontent;

public class StockVo {
    private Long id;
    /**
     * 股票名称
     */
    private String name;
    /**
     * 股票代码
     */
    private String code;
    /**
     * 股票状态(可买可卖状态 非开始闭市状态)
     */
    private Boolean status;

    private StockExponentVo stockExponent;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public StockExponentVo getStockExponent() {
        return stockExponent;
    }

    public void setStockExponent(StockExponentVo stockExponentVo) {
        this.stockExponent = stockExponentVo;
    }

    public void setExponent(StockExponentVo exponent) {
        this.stockExponent = exponent;
    }

}
