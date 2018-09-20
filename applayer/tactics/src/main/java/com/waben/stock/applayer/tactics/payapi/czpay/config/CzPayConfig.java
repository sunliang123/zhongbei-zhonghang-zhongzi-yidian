package com.waben.stock.applayer.tactics.payapi.czpay.config;

public class CzPayConfig {
	
	/**
	 * 测试：http://60.205.113.133:8030
	 * 生产：http://sittrx.daxtech.com.cn
	 */
	public static String hostName = "http://sittrx.daxtech.com.cn";
	/**
	 * 交易类型码
	 */
	public static String transType = "BP02";
	/**
	 * 机构号
	 * 测试：243463836
	 * 生产：283673885
	 */
	public static String organizationId = "283673885";
	/**
	 * 商户手机号
	 * 测试：18322325897
	 * 生产：13145890658
	 */
	public static String merchantPhone = "13145890658";
	/**
	 * 开通的支付类型 T1
	 */
	public static String payType = "T1";
	/**
	 * 页面返回地址
	 * 测试：http://1108ab82.nat123.cc:53623/tactics/payment/czpayreturn
	 * 生产：http://waben.cn/tactics/payment/czpayreturn
	 */
    public static String returnUrl = "https://m.youguwang.com.cn/tactics/payment/czpayreturn";
    /**
     * 异步通知地址
     * 测试：http://1108ab82.nat123.cc:53623/tactics/payment/czpaycallback
	 * 生产：http://waben.cn/tactics/payment/czpaycallback
     */
    public static String notifyUrl = "https://m.youguwang.com.cn/tactics/payment/czpaycallback";
    /**
     * mac签名key
     * 测试：E1DF87DFCAC7261B5562E57CAED5F6A9
	 * 生产：BDBEA61420BAEA211846DEB12FC99EC5
     */
    public static String macKey = "BDBEA61420BAEA211846DEB12FC99EC5";
    /**
     * 网银支付url
     */
    public static String bankPaymentUrl = hostName + "/middlepaytrx/B2C/bankPayment";
	
}
