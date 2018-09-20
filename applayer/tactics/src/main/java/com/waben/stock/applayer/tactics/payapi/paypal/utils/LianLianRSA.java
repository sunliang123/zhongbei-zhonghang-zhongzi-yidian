package com.waben.stock.applayer.tactics.payapi.paypal.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;

public class LianLianRSA {

    public static String encrypt(String plaintext, String public_key) throws Exception {
        String hmack_key = genLetterDigitRandom(32);
        String version = "lianpay1_0_1";
        String aes_key = genLetterDigitRandom(32);
        String nonce = genLetterDigitRandom(8);
        return lianlianpayEncrypt(plaintext, public_key, hmack_key, version, aes_key, nonce);
    }

    public static String genLetterDigitRandom(int size) {
        StringBuffer allLetterDigit = new StringBuffer("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Random random = new Random();
        StringBuffer randomSb = new StringBuffer("");

        for(int i = 0; i < size; ++i) {
            randomSb.append(allLetterDigit.charAt(random.nextInt(allLetterDigit.length())));
        }

        return randomSb.toString();
    }

    public static String lianlianpayEncrypt(String req, String public_key, String hmack_key, String version, String aes_key, String nonce) {
        try {
            BASE64Encoder encoder = new BASE64Encoder();
            String B64hmack_key = rsaEncrypt(hmack_key, public_key);
            String B64aes_key = rsaEncrypt(aes_key, public_key);
            String B64nonce = encoder.encode(nonce.getBytes());
            String encry = aesEncrypt(req.getBytes("UTF-8"), aes_key.getBytes(), nonce.getBytes());
            String message = B64nonce + "$" + encry;
            byte[] sign = encodeHmacSHA256(message.getBytes(), hmack_key.getBytes());
            String B64sign = encoder.encode(sign);
            String encrpt_str = version + "$" + B64hmack_key + "$" + B64aes_key + "$" + B64nonce + "$" + encry + "$" + B64sign;
            return encrpt_str;
        } catch (Exception var15) {
            var15.printStackTrace();
            return null;
        }
    }

    public static String rsaEncrypt(String source, String public_key) throws Exception {
        BASE64Decoder b64d = new BASE64Decoder();
        byte[] keyByte = b64d.decodeBuffer(public_key);
        X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509ek);
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(1, publicKey);
        byte[] sbt = source.getBytes("UTF-8");
        byte[] epByte = cipher.doFinal(sbt);
        BASE64Encoder encoder = new BASE64Encoder();
        String epStr = encoder.encode(epByte);
        return epStr;
    }

    public static String aesEncrypt(byte[] msgbt, byte[] aesKey, byte[] nonce) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        IvParameterSpec ips = createCtrIv(nonce);
        cipher.init(1, secretKeySpec, ips);
        byte[] epByte = cipher.doFinal(msgbt);
        BASE64Encoder encoder = new BASE64Encoder();
        String epStr = encoder.encode(epByte);
        return epStr;
    }

    private static IvParameterSpec createCtrIv(byte[] nonce) {
        byte[] counter = new byte[]{0, 0, 0, 0, 0, 0, 0, 1};
        byte[] output = new byte[nonce.length + counter.length];

        int i;
        for(i = 0; i < nonce.length; ++i) {
            output[i] = nonce[i];
        }

        for(i = 0; i < counter.length; ++i) {
            output[i + nonce.length] = counter[i];
        }

        return new IvParameterSpec(output);
    }

    public static byte[] encodeHmacSHA256(byte[] data, byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        byte[] digest = mac.doFinal(data);
        return digest;
    }
}
