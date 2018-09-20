package com.waben.stock.interfaces.vo.stockoption;

import com.waben.stock.interfaces.enums.OfflineStockOptionTradeState;

import java.math.BigDecimal;
import java.util.Date;

public class OfflineStockOptionTradeVo{
    private Long id;
    /**
     * 对应的第三方交易单号
     */
    private String thirdTradeNo;
    /**
     * 状态
     */
    private OfflineStockOptionTradeState state;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 股票名称
     */
    private String stockName;
    /**
     * 名义本金
     */
    private BigDecimal nominalAmount;
    /**
     * 权利金比例
     */
    private BigDecimal rightMoneyRatio;
    /**
     * 权利金
     */
    private BigDecimal rightMoney;
    /**
     * 周期
     */
    private Integer cycle;
    /**
     * 到期时间
     */
    private BigDecimal expireTime;
    /**
     * 成交时间
     */
    private Date buyingTime;
    /**
     * 成交价格
     */
    private BigDecimal buyingPrice;
    /**
     * 卖出时间
     */
    private Date sellingTime;
    /**
     * 卖出价格
     */
    private BigDecimal sellingPrice;
    /**
     * 行权时间
     */
    private BigDecimal rightTime;
    /**
     * 盈利
     */
    private BigDecimal profit;
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

    public String getThirdTradeNo() {
        return thirdTradeNo;
    }

    public void setThirdTradeNo(String thirdTradeNo) {
        this.thirdTradeNo = thirdTradeNo;
    }

    public OfflineStockOptionTradeState getState() {
        return state;
    }

    public void setState(OfflineStockOptionTradeState state) {
        this.state = state;
    }

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

    public BigDecimal getNominalAmount() {
        return nominalAmount;
    }

    public void setNominalAmount(BigDecimal nominalAmount) {
        this.nominalAmount = nominalAmount;
    }

    public BigDecimal getRightMoneyRatio() {
        return rightMoneyRatio;
    }

    public void setRightMoneyRatio(BigDecimal rightMoneyRatio) {
        this.rightMoneyRatio = rightMoneyRatio;
    }

    public BigDecimal getRightMoney() {
        return rightMoney;
    }

    public void setRightMoney(BigDecimal rightMoney) {
        this.rightMoney = rightMoney;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public BigDecimal getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(BigDecimal expireTime) {
        this.expireTime = expireTime;
    }

    public Date getBuyingTime() {
        return buyingTime;
    }

    public void setBuyingTime(Date buyingTime) {
        this.buyingTime = buyingTime;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Date getSellingTime() {
        return sellingTime;
    }

    public void setSellingTime(Date sellingTime) {
        this.sellingTime = sellingTime;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getRightTime() {
        return rightTime;
    }

    public void setRightTime(BigDecimal rightTime) {
        this.rightTime = rightTime;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public StockOptionOrgVo getOrg() {
        return org;
    }

    public void setOrg(StockOptionOrgVo org) {
        this.org = org;
    }
}
