package com.waben.stock.applayer.tactics.payapi.czpay.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Des3Encryption {

	/** 编码字符集 **/
	private static String CHAR_ENCODING = "UTF-8";

	public static void test(String[] args) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
			BadPaddingException, IllegalBlockSizeException, IllegalStateException {
		// 加密KEY
		String desKey = "bsVtL4hnkksTanKcOt3iVRVv";

		String payerName = Des3Encryption.encode(desKey, "张三峰");
		String idCardNo = Des3Encryption.encode(desKey, "410521199310128511");
		String cardNo = Des3Encryption.encode(desKey, "6226220282587115");
		System.out.println("payerName=" + payerName);
		System.out.println("idCardNo=" + idCardNo);
		System.out.println("cardNo=" + cardNo);
	}

	/**
	 * Base64编码
	 * 
	 * @param key
	 * @param data
	 * @return
	 */
	public static String encode(String key, String data) {
		try {
			byte[] keyByte = key.getBytes(CHAR_ENCODING);
			byte[] dataByte = data.getBytes(CHAR_ENCODING);
			byte[] valueByte = Des3Encryption.des3Encryption(keyByte, dataByte);
			String value = new String(Base64.encode(valueByte));
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * des3Encryption加密
	 * 
	 * @param key
	 * @param data
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws IllegalStateException
	 */
	public static byte[] des3Encryption(byte[] key, byte[] data)
			throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException, IllegalStateException {
		final String Algorithm = "DESede";

		SecretKey deskey = new SecretKeySpec(key, Algorithm);

		Cipher c1 = Cipher.getInstance(Algorithm);
		c1.init(Cipher.ENCRYPT_MODE, deskey);
		return c1.doFinal(data);
	}
}
