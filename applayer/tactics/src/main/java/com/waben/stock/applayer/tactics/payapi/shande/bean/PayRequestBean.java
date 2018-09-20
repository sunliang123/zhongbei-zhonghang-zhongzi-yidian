package com.waben.stock.applayer.tactics.payapi.shande.bean;

import com.waben.stock.applayer.tactics.payapi.shande.config.SandPayConfig;

public class PayRequestBean {
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 商户号
     */
    private String mchNo = SandPayConfig.mchNo;
    /**
     * 商户类型
     */
    private String mchType = SandPayConfig.mchType;
    /**
     * 商户类型
     */
    private String payChannel = SandPayConfig.payChannel;
    /**
     * 支付渠道支付类型编号
     */
    private String payChannelTypeNo = SandPayConfig.payChannelTypeNo;
    /**
     * 同步通知url
     */
    private String frontUrl;
    /**
     * 异步通知url
     */
    private String notifyUrl = SandPayConfig.notifyUrl;
    /**
     * 商户订单号
     */
    private String orderNo;
    /**
     * 订单金额
     */
    private String amount;
    /**
     * 商品名称
     */
    private String goodsName = SandPayConfig.goodsName;
    /**
     * 商品描述
     */
    private String goodsDesc = SandPayConfig.goodsDesc;
    /**
     * 时间戳
     */
    private String timeStamp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMchNo() {
        return mchNo;
    }

    public void setMchNo(String mchNo) {
        this.mchNo = mchNo;
    }

    public String getMchType() {
        return mchType;
    }

    public void setMchType(String mchType) {
        this.mchType = mchType;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayChannelTypeNo() {
        return payChannelTypeNo;
    }

    public void setPayChannelTypeNo(String payChannelTypeNo) {
        this.payChannelTypeNo = payChannelTypeNo;
    }

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


}
