package com.waben.stock.applayer.strategist.payapi.shande.config;

public class SandPayConfig {
    /**
     * 秘钥
     */
    public static final String  key = "0f7acd3d53920783c61ed1a99ed58c89";
    /**
     * 商户号
     */
    public static final String  mchNo = "MER1000157";
    /**
     * 商户类型
     */
    public static final String mchType = "1";
    /**
     * 支付渠道
     */
    public static final String payChannel = "1_jhdr";
    /**
     * 支付渠道支付类型编号
     */
    public static final String payChannelTypeNo = "7";
    /**
     * 商品名称
     */
    public static final String goodsName = "支付";
    /**
     * 商品描述
     */
    public static final String goodsDesc = "快捷";
    /**
     * 回调地址
     */
    public static final String notifyUrl = "https://m.youguwang.com.cn/strategist/quickpay/sdpaycallback";
    /**
     * 页面通知地址
     */
    public static final String fontUrl = "https://m.youguwang.com.cn/strategist/quickpay/sdpayreturn";

    //提现类型  0对私 1对公
    public static final String bankType = "0";
    public static final String csaUrl = "http://211.149.180.207/gateWay/service/another/pay";


}
