package com.waben.stock.interfaces.pojo.mq;

import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.enums.BuyRecordState;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Created by yuyidi on 2017/12/1.
 * @desc
 */
public class BuyRecordMessage implements Serializable{

    private Long buyRecordId;
    /**
     * 系列号
     */
    private String serialCode;
    /**
     * 交易编号
     */
    private String tradeNo;
    /**
     * 是否递延费
     */
    private Boolean deferred;
    /**
     * 止盈点
     */
    private BigDecimal profitPoint;
    /**
     * 止盈预警点位
     */
    private BigDecimal profitWarnPosition;
    /**
     * 止盈点位
     */
    private BigDecimal profitPosition;
    /**
     * 止损点
     */
    private BigDecimal lossPoint;
    /**
     * 止损预警点位
     */
    private BigDecimal lossWarnPosition;
    /**
     * 止损点位
     */
    private BigDecimal lossPosition;
    /**
     * 状态
     */
    private BuyRecordState state;
    /**
     * 持股数
     */
    private Integer numberOfStrand;
    /**
     * 委托编号，证券账号购买股票后的交易编号
     */
    private String delegateNumber;
    /**
     * 点买记录创建时间
     */
    private Date createTime;
    /**
     * 点买时间
     */
    private Date buyingTime;
    /**
     * 买入价格
     */
    private BigDecimal buyingPrice;

    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 策略类型
     */
    private StrategyTypeDto strategyTypeDto;


}
