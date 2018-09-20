package com.waben.stock.interfaces.dto.stockoption;

import java.math.BigDecimal;

public class InquiryResultDto implements Comparable<StockOptionTradeDto> {
    private Long id;
    /**
     * 权利金报价比例
     */
    private BigDecimal rightMoneyRatio;
    /**
     * 对应的用户股票期权交易信息
     */

    private StockOptionTradeDto trade;
    /**
     * 对应的期权第三方机构
     */
    private StockOptionOrgDto org;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRightMoneyRatio() {
        return rightMoneyRatio;
    }

    public void setRightMoneyRatio(BigDecimal rightMoneyRatio) {
        this.rightMoneyRatio = rightMoneyRatio;
    }

    public StockOptionTradeDto getTrade() {
        return trade;
    }

    public void setTrade(StockOptionTradeDto trade) {
        this.trade = trade;
    }

    public StockOptionOrgDto getOrg() {
        return org;
    }

    public void setOrg(StockOptionOrgDto org) {
        this.org = org;
    }

    @Override
    public int compareTo(StockOptionTradeDto o) {
        return 0;
    }
}
