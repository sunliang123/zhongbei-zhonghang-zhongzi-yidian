package com.waben.stock.applayer.tactics.payapi.caituopay.config;

public class PayConfig {
    /**
     * 应用ID
     */
    public static final String appId = "35237";
    /**
     * 应用秘钥
     */
    public static final String appSecret = "5C506B67CE6A29AE5803";
    /**
     * 当前版本号
     */
    public static final String version = "1.0";
    /**
     * 请求地址
     */
    public static final String qqurl = "http://api.ouhepay.com/V1/qqPay";
    /**
     * 请求地址
     */
    public static final String jdurl = "http://api.ouhepay.com/V1/jingdong/pay";
    /**
     * 回调地址
     */
//    public static final String qq_return_url = "http://r7x2uw.natappfree.cc/tactics/quickpay/qqcallback";
//    public static final String jd_return_url = "http://r7x2uw.natappfree.cc/tactics/quickpay/jdcallback";
    public static final String qq_return_url = "https://m.youguwang.com.cn/tactics/quickpay/qqcallback";
    public static final String jd_return_url = "https://m.youguwang.com.cn/tactics/quickpay/jdcallback";
    /**
     * 应用类型
     */
    public static final String device_info = "AND_WAP";
    /**
     * 应用名
     */
    public static final String mch_app_name = "优股网";
    /**
     * 应用标识
     */
    public static final String mch_app_id = "http://youguwang.com.cn";
    /**
     * 前台支付完成跳转地址
     */
//    public static final String qq_front_skip_url = "http://r7x2uw.natappfree.cc/quickpay/qqpayreturn";
//    public static final String jd_front_skip_url = "http://r7x2uw.natappfree.cc/quickpay/jdpayreturn";
    public static final String qq_front_skip_url = "https://m.youguwang.com.cn/tactics/quickpay/qqpayreturn";
    public static final String jd_front_skip_url = "https://m.youguwang.com.cn/tactics/quickpay/jdpayreturn";
    /**
     * 商品名称
     */
    public static final String subject = "支付";
    /**
     * 商品描述
     */
    public static final String qqbody = "QQ支付";
    public static final String jdbody = "JD支付";
}
