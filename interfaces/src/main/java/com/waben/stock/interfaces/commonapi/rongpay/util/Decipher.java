package com.waben.stock.interfaces.commonapi.rongpay.util;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.waben.stock.interfaces.util.JacksonUtil;

public class Decipher {
	
    /**
     * 解密
     *
     * @param merchant_id
     * @param data
     * @param encryptkey
     * @return
     * @throws com.reapal.common.exception.ServiceException
     */
    public static String decryptData(String post, String publicKeyUrl, String privateKeyUrl,
			String privateKeyPwd) throws Exception {
        // 将返回的json串转换为map
        TreeMap<String, String> map = JacksonUtil.decode(post, new TypeReference<TreeMap<String, String>>() {
        });
        String encryptkey = map.get("encryptkey");
        String data = map.get("data");
        String merchantId = map.get("merchant_id");
        // System.out.println("使用的证书为为------>" + privateKeyUrl);
        // System.out.println("接收到的data为------>" + data);
        // System.out.println("接收到的encryptkey------>" + encryptkey);
        //获取自己私钥解密
        PrivateKey pvkformPfx = RSA.getPvkformPfx(privateKeyUrl, privateKeyPwd);
        String decryptData = RSA.decrypt(encryptkey, pvkformPfx);
        post = AES.decryptFromBase64(data, decryptData);
        // System.out.println("接收到的post------>" + post);
//        JsonNode jsonNode = JacksonUtil.objectMapper.readTree(post);
//        //验签
//        if("0000".equals(jsonNode.get("result_code").asText())) {
//        	boolean checkSignFlag = checkSignRSAClient(post, publicKeyUrl, jsonNode.get("sign").asText());
//            System.out.println("验签结果------>" + checkSignFlag);
//        }
        return post;
    }
    
    /**
     * 解密
     *
     * @param merchant_id
     * @param data
     * @param encryptkey
     * @return
     * @throws com.reapal.common.exception.ServiceException
     */
    public static String decryptData(String merchantId, String encryptkey, String data , String publicKeyUrl, String privateKeyUrl,
			String privateKeyPwd) throws Exception {
        // System.out.println("使用的证书为为------>" + privateKeyUrl);
        // System.out.println("接收到的data为------>" + data);
        // System.out.println("接收到的encryptkey------>" + encryptkey);
        //获取自己私钥解密
        PrivateKey pvkformPfx = RSA.getPvkformPfx(privateKeyUrl, privateKeyPwd);
        String decryptData = RSA.decrypt(encryptkey, pvkformPfx);
        String post = AES.decryptFromBase64(data, decryptData);
        // System.out.println("接收到的post------>" + post);
        return post;
    }


    /**
     * 加密
     *
     * @param merchant_id
     * @param data
     * @param encryptkey
     * @return
     * @throws com.reapal.common.exception.ServiceException
     */
    public static Map<String, String> encryptData(String json,String merchantId, String publicKeyUrl) throws Exception {
//		System.out.println("json数据=============>" + json);
//	      Map<String, String> map = new HashMap<String, String>();
//	      map=JSON.parseObject(json, HashMap.class);
//        String publicKeyUrl = Util.getpubKeyUrlPath(merchantId);
        //商户获取融宝公钥
        PublicKey pubKeyFromCrt = RSA.getPubKeyFromCRT(publicKeyUrl);
        //随机生成16数字
        String key = getRandom(16);

        // 使用RSA算法将商户自己随机生成的AESkey加密
        String encryptKey = RSA.encrypt(key, pubKeyFromCrt);
        // 使用AES算法用随机生成的AESkey，对json串进行加密
        String encryData = AES.encryptToBase64(json, key);

//        System.out.println("密文key============>" + encryptKey);
//        System.out.println("密文数据===========>" + encryData);

        Map<String, String> map = new HashMap<String, String>();
        map.put("data", encryData);
        map.put("encryptkey", encryptKey);

        return map;
    }


    public static Random random = new Random();

    public static String getRandom(int length) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < length; i++) {
            boolean isChar = (random.nextInt(2) % 2 == 0);// 输出字母还是数字
            if (isChar) { // 字符串
                int choice = (random.nextInt(2) % 2 == 0) ? 65 : 97; // 取得大写字母还是小写字母
                ret.append((char) (choice + random.nextInt(26)));
            } else { // 数字
                ret.append(Integer.toString(random.nextInt(10)));
            }
        }
        return ret.toString();
    }

    /**
     * 解密
     *
     * @param merchant_id
     * @param data
     * @param encryptkey
     * @return
     * @throws com.reapal.common.exception.ServiceException
     */
    public static String decryptData(String encryptkey, String data, String privateKeyUrl,
			String privateKeyPwd, boolean flag) throws Exception {
        //获取自己私钥解密
        PrivateKey pvkformPfx = RSA.getPvkformPfx(privateKeyUrl, privateKeyPwd);
        String decryptData = RSA.decrypt(encryptkey, pvkformPfx);
        return AES.decryptFromBase64(data, decryptData);
    }
    
    
    public static boolean checkSignRSAClient(String data,String pubKeyPath,String sign) {
        boolean flag = false;
        try {
            String data0 = SignUtil.resultSign(data);
            PublicKey pubKeyFromCrt = RSA.getPubKeyFromCRT(pubKeyPath);
            flag = checkSign(data0, sign, pubKeyFromCrt);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
    
    public static boolean checkSign(String content, String sign,PublicKey pubKeyFromCrt) throws Exception
	{
		Signature signature = Signature
				.getInstance("SHA256WithRSA");

		signature.initVerify(pubKeyFromCrt);
		signature.update( content.getBytes("utf-8") );

		boolean bverify = signature.verify( Base64.decode2(sign) );
		return bverify;

	}

}
