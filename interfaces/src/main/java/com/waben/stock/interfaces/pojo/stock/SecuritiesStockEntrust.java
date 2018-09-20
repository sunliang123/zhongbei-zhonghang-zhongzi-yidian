package com.waben.stock.interfaces.pojo.stock;

import com.waben.stock.interfaces.enums.BuyRecordState;
import com.waben.stock.interfaces.enums.EntrustState;
import com.waben.stock.interfaces.enums.EntrustType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Created by yuyidi on 2017/12/2.
 * @desc 券商股票委托买入卖出传输对象
 */
public class SecuritiesStockEntrust implements Serializable {

    private Long buyRecordId;
    private String serialCode;
    private String tradeNo;
    private String stockName;
    private String stockCode;
    private BigDecimal profitPosition;
    private BigDecimal lossPosition;

    /**
     * 证券股票类型(上证|深证|创业板)
     */
    private String exponent;
    /**
     * 状态
     */
    private BuyRecordState buyRecordState;
    /**
     * 委托价格
     */
    private BigDecimal entrustPrice;
    /**
     * 委托数量
     */
    private Integer entrustNumber;

    /**
     * 交易类型(买入或卖出)
     */
    private EntrustType entrustType;

    /**
     * 委托状态
     */
    private EntrustState entrustState;

    /**
     * 委托编号，证券账号购买股票后的交易编号
     */
    private String entrustNo;
    /**
     * 委托时间
     */
    private Date entrustTime;

    private Long investor;

    private String tradeSession;
    private String windControlType;
    public BigDecimal getProfitPosition() {
        return profitPosition;
    }

    public void setProfitPosition(BigDecimal profitPosition) {
        this.profitPosition = profitPosition;
    }

    public BigDecimal getLossPosition() {
        return lossPosition;
    }

    public void setLossPosition(BigDecimal lossPosition) {
        this.lossPosition = lossPosition;
    }

    public Long getBuyRecordId() {
        return buyRecordId;
    }

    public void setBuyRecordId(Long buyRecordId) {
        this.buyRecordId = buyRecordId;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

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

    public String getExponent() {
        return exponent;
    }

    public void setExponent(String exponent) {
        this.exponent = exponent;
    }

    public BuyRecordState getBuyRecordState() {
        return buyRecordState;
    }

    public void setBuyRecordState(BuyRecordState buyRecordState) {
        this.buyRecordState = buyRecordState;
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public Integer getEntrustNumber() {
        return entrustNumber;
    }

    public void setEntrustNumber(Integer entrustNumber) {
        this.entrustNumber = entrustNumber;
    }

    public EntrustType getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(EntrustType entrustType) {
        this.entrustType = entrustType;
    }

    public EntrustState getEntrustState() {
        return entrustState;
    }

    public void setEntrustState(EntrustState entrustState) {
        this.entrustState = entrustState;
    }

    public String getEntrustNo() {
        return entrustNo;
    }

    public void setEntrustNo(String entrustNo) {
        this.entrustNo = entrustNo;
    }

    public Date getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Date entrustTime) {
        this.entrustTime = entrustTime;
    }

    public String getTradeSession() {
        return tradeSession;
    }

    public void setTradeSession(String tradeSession) {
        this.tradeSession = tradeSession;
    }

    public Long getInvestor() {
        return investor;
    }

    public void setInvestor(Long investor) {
        this.investor = investor;
    }

    public String getWindControlType() {
        return windControlType;
    }

    public void setWindControlType(String windControlType) {
        this.windControlType = windControlType;
    }
}
