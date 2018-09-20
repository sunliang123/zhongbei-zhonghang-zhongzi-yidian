package com.waben.stock.risk.hmac;

import com.waben.stock.interfaces.util.JacksonUtil;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * MAC算法工具类
 * 对于HmacMD5、HmacSHA1、HmacSHA256、HmacSHA384、HmacSHA512应用的步骤都是一模一样的。具体看下面的代码
 */
class MACCoder {
    /**
     * 产生HmacMD5摘要算法的密钥
     */
    public static byte[] initHmacMD5Key() throws NoSuchAlgorithmException {
        // 初始化HmacMD5摘要算法的密钥产生器
        KeyGenerator generator = KeyGenerator.getInstance("HmacMD5");
        // 产生密钥
        SecretKey secretKey = generator.generateKey();
        System.out.println(secretKey.getAlgorithm());
        // 获得密钥
        byte[] key = secretKey.getEncoded();
        return key;
    }

    /**
     * HmacMd5摘要算法
     * 对于给定生成的不同密钥，得到的摘要消息会不同，所以在实际应用中，要保存我们的密钥
     */
    public static String encodeHmacMD5(byte[] data, byte[] key) throws Exception {
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        //初始化mac
        mac.init(secretKey);
        //执行消息摘要
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);//转为十六进制的字符串
    }


    /**
     * 产生HmacSHA1摘要算法的密钥
     */
    public static byte[] initHmacSHAKey() throws NoSuchAlgorithmException {
        // 初始化HmacMD5摘要算法的密钥产生器
        KeyGenerator generator = KeyGenerator.getInstance("HmacSHA1");
        // 产生密钥
        SecretKey secretKey = generator.generateKey();
        // 获得密钥
        byte[] key = secretKey.getEncoded();
        return key;
    }

    /**
     * HmacSHA1摘要算法
     * 对于给定生成的不同密钥，得到的摘要消息会不同，所以在实际应用中，要保存我们的密钥
     */
    public static String encodeHmacSHA(byte[] data, byte[] key) throws Exception {
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA1");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        //初始化mac
        mac.init(secretKey);
        //执行消息摘要
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);//转为十六进制的字符串
    }


    /**
     * 产生HmacSHA256摘要算法的密钥
     */
    public static byte[] initHmacSHA256Key() throws NoSuchAlgorithmException {
        // 初始化HmacMD5摘要算法的密钥产生器
        KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
        // 产生密钥
        SecretKey secretKey = generator.generateKey();
        // 获得密钥
        byte[] key = secretKey.getEncoded();
        return key;
    }

    /**
     * HmacSHA1摘要算法
     * 对于给定生成的不同密钥，得到的摘要消息会不同，所以在实际应用中，要保存我们的密钥
     */
    public static String encodeHmacSHA256(byte[] data, byte[] key) throws Exception {
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        //初始化mac
        mac.init(secretKey);
        //执行消息摘要
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);//转为十六进制的字符串
    }


    /**
     * 产生HmacSHA256摘要算法的密钥
     */
    public static byte[] initHmacSHA384Key() throws NoSuchAlgorithmException {
        // 初始化HmacMD5摘要算法的密钥产生器
        KeyGenerator generator = KeyGenerator.getInstance("HmacSHA384");
        // 产生密钥
        SecretKey secretKey = generator.generateKey();
        // 获得密钥
        byte[] key = secretKey.getEncoded();
        return key;
    }

    /**
     * HmacSHA1摘要算法
     * 对于给定生成的不同密钥，得到的摘要消息会不同，所以在实际应用中，要保存我们的密钥
     */
    public static String encodeHmacSHA384(byte[] data, byte[] key) throws Exception {
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA384");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        //初始化mac
        mac.init(secretKey);
        //执行消息摘要
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);//转为十六进制的字符串
    }


    /**
     * 产生HmacSHA256摘要算法的密钥
     */
    public static byte[] initHmacSHA512Key() throws NoSuchAlgorithmException {
        // 初始化HmacMD5摘要算法的密钥产生器
        KeyGenerator generator = KeyGenerator.getInstance("HmacSHA512");
        // 产生密钥
        SecretKey secretKey = generator.generateKey();
        // 获得密钥
        byte[] key = secretKey.getEncoded();
        return key;
    }

    /**
     * HmacSHA1摘要算法
     * 对于给定生成的不同密钥，得到的摘要消息会不同，所以在实际应用中，要保存我们的密钥
     */
    public static String encodeHmacSHA512(byte[] data, byte[] key) throws Exception {
        // 还原密钥
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA512");
        // 实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        //初始化mac
        mac.init(secretKey);
        //执行消息摘要
        byte[] digest = mac.doFinal(data);
        return new HexBinaryAdapter().marshal(digest);//转为十六进制的字符串
    }
}

public class MACTest {
    public static void main(String[] args) throws Exception {
       //sign();
       pay();
//        scanCode();

//		byte[] keyHmacMD5=MACCoder.initHmacMD5Key();
//		System.out.println(new String(keyHmacMD5));
//		System.out.println(MACCoder.encodeHmacMD5(testString.getBytes(),"E1DF87DFCAC7261B5562E57CAED5F6A9".getBytes
// ()));

//		byte[] keyHmacSHA1=MACCoder.initHmacSHAKey();
//		System.out.println(MACCoder.encodeHmacSHA(testString.getBytes(),keyHmacSHA1));
//
//		byte[] keyHmacSHA256=MACCoder.initHmacSHA256Key();
//		System.out.println(MACCoder.encodeHmacSHA256(testString.getBytes(),keyHmacSHA256));
//
//		byte[] keyHmacSHA384=MACCoder.initHmacSHA384Key();
//		System.out.println(MACCoder.encodeHmacSHA384(testString.getBytes(),keyHmacSHA384));
//
//		byte[] keyHmacSHA512=MACCoder.initHmacSHA512Key();
//		System.out.println(MACCoder.encodeHmacSHA512(testString.getBytes(),keyHmacSHA512));
    }

    public static void sign() {
        Map<String, String> sign = new HashMap<>();
        sign.put("sendTime", "20171220141915");
        sign.put("sendSeqId", "201801171703");
        sign.put("transType", "A001");
        sign.put("organizationId", "283673885");
        String json = JacksonUtil.encode(sign);
        System.out.println(json);
    }

    public static void pay() throws Exception {

        Map<String, String> pay = new HashMap<>();

//        pay.put("body", "testscancode");
//        pay.put("cardNo", "611301061018010026580");
//        pay.put("idNum", "211324196502020031");
//        pay.put("mobile", "13274220935");
//        pay.put("name", "刘国忠");
//        pay.put("notifyUrl", "http://www.baidu.com/notify");
//        pay.put("organizationId", "283683895");
//        pay.put("payPass", "5");
//        pay.put("sendSeqId", "testScanCode20180119094823");
//        pay.put("sendTime", "20180119094825");
//        pay.put("subject", "MD5");
//        pay.put("transAmt", "1000");
//        pay.put("transType", "Y001");




        pay.put("bankCode", "ICBC");
        pay.put("body", "chongzhi");
        pay.put("callBackUrl", "http://vzz5yu.natappfree.cc/tfb/callback1");
        pay.put("cardType", "0");
        pay.put("mobile", "13525565487");
        pay.put("name", "BankPay");
        pay.put("notifyUrl", "http://vzz5yu.natappfree.cc/tfb/callback");
        pay.put("organizationId", "283673885");
        pay.put("payType", "T1");
        pay.put("sendSeqId", "201801181341034879");
        pay.put("sendTime", "201801181341335");
        pay.put("transAmt", "300");
        pay.put("transType", "BP02");
        pay.put("subject", "MD5");

//        pay.put("fee","390");
//        pay.put("orgSendSeqId","2018012215234607");
//        pay.put("organizationId","283683895");
//        pay.put("payDesc","支付成功");
//        pay.put("payResult","00");
//        pay.put("transAmt","65040");
        //BDBEA61420BAEA211846DEB12FC99EC5   283673885

        //14CE14C92727EBEAD73F533A0EBF503A   283683895
        String mac = MakeMacUtil.md5MakeMac(JacksonUtil.encode(pay), "BDBEA61420BAEA211846DEB12FC99EC5");
        pay.put("mac", mac);
        System.out.println(JacksonUtil.encode(pay));

    }


    public static void scanCode() {
        Map<String, String> pay = new HashMap<>();
        pay.put("sendTime", "20171221171639");
        pay.put("sendSeqId", "201712211716112");
        pay.put("transType", "Y001");
        pay.put("organizationId", " ");
        pay.put("payPass", "5");
        pay.put("transAmt", "2");
//        pay.put("cardNo", "6217007200027128555");
//        pay.put("name", "余乙迪");
//        pay.put("idNum", "421126199206302514");
        pay.put("cardNo", "123456");
        pay.put("name", "余乙迪");
        pay.put("idNum", "243549367");
        pay.put("body", "充值");
        pay.put("notifyUrl", "http://jzbi5a.natappfree.cc/api/callback");
        pay.put("subject", "MAC");
        pay.put("mobile", "18322325897");
        String mac = MakeMacUtil.makeMac(JacksonUtil.encode(pay), "E1DF87DFCAC7261B5562E57CAED5F6A9");
        pay.put("mac", mac);
        System.out.println(JacksonUtil.encode(pay));
//        Map<String, String> condition = new HashMap<String, String>();
//        DefaultHttpClient httpclient = new DefaultHttpClient();
//        String url = "http://60.205.113.133:8020/payform/organization";
//        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssms");
//        String sendTime = sf.format(new Date());
//        JSONObject json = new JSONObject();
//
//        json.put("sendTime", sendTime);
//        json.put("sendSeqId", "Testcallback"+ sendTime);
//        json.put("transType", "B002");// B002 Z002 S002
//        json.put("organizationId", "243463836");// 代理商编号
//        json.put("payPass", "1");// 1.wx 2.zfb 4.sn
//        json.put("transAmt", "10");// 分为单位
//        json.put("cardNo", "6216610200016723185");// 结算卡号
//        json.put("name", "测试人员");//结算卡人姓名
//        json.put("idNum", "230227199405262119");// 持卡人身份证
//        json.put("body", "动态码测试");
//        json.put("mobile", "18322325897");// 商编关联手机号
//        json.put("notifyUrl", "http://60.205.113.133:8030/middlepaytrx/scanCode/TestCallback");	//上送糊掉地址
//
//        String makeMac = makeMac(json.toString(), "32029ED76A36F701B887A1BDF730612E");
//        //String makeMac = makeMac(json.toString(), "签到后的密钥");
//        json.put("mac", makeMac);//报文鉴别码
//        System.out.println(json.toString());
//        condition.put("data", json.toString());

    }


}