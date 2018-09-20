package com.waben.stock.applayer.promotion.payapi.paypal.config;

public class PayPalConfig {
    /**
     * 商户号
     */
    public static final String oid_partner = "201803080001607401";
    /**
     * 私钥
     */
    public static final String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALTuZX3CL5k6jUb3F1y0QlUFMnemxnaMBTsWl4RUV6Z+nPB/Gbr6tYkvsgUfLG+nc0ZU8mXh4ctynvNrpVh52N7fHu8L4Sb4Le0c0kizXKaDwGTXUiMVHjigFl9b9i2c90B8QcmAqvCcOaL+S3svQVh0J3i/QDarjb+EOlnbdpAJAgMBAAECgYEAlYwKlUShBRzRUxu+0qAnx5BRI3bUyX14gVtuBzGTdQln/JyBjvvUerzSfstCdlNIw5RI+o8Wi4uE+Bw8gYHNDxxphHPu04MclbmNMDmvAsCcsPsIhWLQ6mltzx/x5Lg7CxVsTNFhjXhKr/GStmT3RX3My6uAsyxGOfDwJozoIAECQQDuVOmaRCtpO9uNNspndFTqUjuiJ3FDRxvVGioKXpZJ+Ksil7OFVo3Vnp/cuKbhIu57eGrvRUKKMoagdgPkBsQBAkEAwlghmBrRhEFJJtQpzBCQGNqg0DagKqr2KU8z6pD8XoDWq43JJhVjvs/KYEAv0LP/YJUcagj6IK27SFqHZ4msCQJAJrnjb26uQx430Z1ajC7p8V1FVVblX5I5Yooq1J6D+ycN38QYpozm5RQuU7YpX6HGcIejkJeHdV86Yu5qOW4kAQJAcxZxvnuTO3jV8xKNNfVRAAboWKUgsGPRpqDqo6v8Z5JwjeNHDTB5gxPWSq14I2WUhAOJchmi1htL3wsNSHk00QJBAJDyQuReirroaJ9byv8qKiV/sNxBnQ4ISR4zKMxKZtIwjVML3l0QG5ToqZdblSJ4v+/j+34TEhWyalb7M7/tA8o=";
    /**
     * 公钥
     */
    public static final String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSS/DiwdCf/aZsxxcacDnooGph3d2JOj5GXWi+q3gznZauZjkNP8SKl3J2liP0O6rU/Y/29+IUe+GTMhMOFJuZm1htAtKiu5ekW0GlBMWxf4FPkYlQkPE0FtaoMP3gYfh+OwI+fIRrpW3ySn3mScnc6Z700nU/VYrRkfcSCbSnRwIDAQAB";
    /**
     * 版本号
     */
    public static final String version = "1.1";
    /**
     * 请求应用标识 1-Android  2-ios  3-WAP
     */
    public static final String app_request = "3";
    /**
     * 加密类型
     */
    public static final String sign_type = "RSA";
    /**
     * 商户业务类型 虚拟商品销售：101001 实物商品销售：109001
     */
    public static final String busi_partner = "101001";
    /**
     * 商品名称
     */
    public static final String name_goods = "快捷支付";
    /**
     * 回调地址
     */
    public static final String notify_url = "https://m.youguwang.com.cn/tactics/quickpay/paypalcallback";
    /**
     * 支付结束回显
     */
    public static final String url_return = "https://m.youguwang.com.cn/tactics/quickpay/paypalreturn";
    /**
     * 证件类型
     */
    public static final String id_type = "0";
    /**
     * 商户号 商品类目传2026
     */
    public static final String frms_ware_category = "2026";
    /**
     * 支付请求地址
     */
    public static final String url = "https://wap.lianlianpay.com/authpay.htm";
    /**
     * 代付请求地址
     */
    public static final String csa_url = "https://instantpay.lianlianpay.com/paymentapi/payment.htm";
    /**
     * 代付确认地址
     */
    public static final String csa_confirm_url = "https://instantpay.lianlianpay.com/paymentapi/confirmPayment.htm";
    /**
     * 代付回调地址
     */
    public static final String prod_csa_notifyurl = "http://www.youguwang.com.cn/promotion/withdrawalsApply/paypalnotify";
    public static final String test_csa_notifyurl = "http://33034446.nat123.net/promotion/withdrawalsApply/paypalnotify";
    /**
     * 代付版本号
     */
    public static final String csa_version = "1.0";
    /**
     * 代付结算对象
     */
    public static final String flag_card = "0"; //0对私  1对公

    public static final String info_order="出金";

    public  static final String memo="实时出金";
    /**
     * 用户是否实名认证 是传 1，否传 0
     */
    public static final String user_info_identify_state = "1";
    /**
     * 实名认证方式 银行卡认证传 1 现场认证传2 身份证传3 远程认传4
     */
    public static final String user_info_identify_type = "4";
}
