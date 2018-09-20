package com.waben.stock.interfaces.vo.stockoption;

import com.waben.stock.interfaces.dto.stockoption.StockOptionOrgDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;

import java.math.BigDecimal;

public class InquiryResultVo implements Comparable<StockOptionTradeDto> {
    private Long id;
    /**
     * 权利金报价比例
     */
    private BigDecimal rightMoneyRatio;
    /**
     * 对应的用户股票期权交易信息
     */

    private StockOptionTradeVo trade;
    /**
     * 对应的期权第三方机构
     */
    private StockOptionOrgVo org;

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

    public StockOptionTradeVo getTrade() {
        return trade;
    }

    public void setTrade(StockOptionTradeVo trade) {
        this.trade = trade;
    }

    public StockOptionOrgVo getOrg() {
        return org;
    }

    public void setOrg(StockOptionOrgVo org) {
        this.org = org;
    }

    @Override
    public int compareTo(StockOptionTradeDto o) {
        return 0;
    }
}
