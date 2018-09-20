package com.waben.stock.interfaces.commonapi.rongpay.config;

/* *
 *功能：设置帐户有关信息及返回路径（基础配置页面）
 *版本：3.1.2
 *日期：2015-08-14
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究融宝支付接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.访问融宝支付商户后台，然后用您的签约融宝支付账号登陆(注册邮箱号).
 *2.点击导航栏中的“商家服务”，即可查看

 * */

public class ReapalH5Config {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 需要更换的信息
	// 商户ID，由纯数字组成的字符串
	public static String merchant_id = "100000000000147";
	// 交易安全检验码，由数字和字母组成的64位字符串
	public static String key = "g0be2385657fa355af68b74e9913a1320af82gb7ae5f580g79bffd04a402ba8f";
	// 签约融宝支付账号或卖家收款融宝支付帐户
	public static String seller_email = "820061154@qq.com";
	// 通知地址，由商户提供
	public static String notify_url = "http://1108ab82.nat123.cc:17654/quickpay/rongh5callback";
	// 返回地址，由商户提供
	public static String return_url = "http://localhost:8080/reapal-h5-api/return.jsp";
	// 商户私钥
	public static String privateKey = "D:\\cert\\itrus001.pfx";
	// 商户私钥密码
	public static String password = "123456";
	// 测试环境地址
	public static String rongpay_api = "http://testapi.reapal.com";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	// 版本号
	public static String version = "3.1.2";
	// 融宝公钥 正式环境不用修改
	public static String pubKeyUrl = "D:\\cert\\itrus001.cer";
	// 字符编码格式 目前支持 utf-8
	public static String charset = "utf-8";
	// 签名方式 不需修改
	public static String sign_type = "MD5";
	// 访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
	public static String transport = "http";

}
