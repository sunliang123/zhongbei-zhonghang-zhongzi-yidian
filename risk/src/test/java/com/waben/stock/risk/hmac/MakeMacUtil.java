package com.waben.stock.risk.hmac;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;






public class MakeMacUtil {


	/**
	 * 计算macKey值
	 * 
	 * @param
	 *
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static String makeMac(String json, String macKey) {
		Map<String, String> contentData = (Map<String, String>) Tools.parserToMap(json);
		String macStr = "";
		Object[] key_arr = contentData.keySet().toArray();
		Arrays.sort(key_arr);
		for (Object key : key_arr) {
			Object value = contentData.get(key);
			if (value != null) {
				if (!key.equals("mac")) {
					macStr += value.toString();
				}
			}
		}
		String rMac = DESUtil.mac(macStr, macKey);
		return rMac;
	}
	
	 public static String md5MakeMac(String json, String macKey) throws Exception{
	    	Map<String, String> contentData = (Map<String, String>) Tools.parserToMap(json);
			String macStr="";
			Object[] key_arr = contentData.keySet().toArray();
			Arrays.sort(key_arr);
			for (Object key : key_arr) {
				Object value = contentData.get(key);
				if (value != null ) {
					if (!key.equals("mac")) {
						macStr+= value.toString();
					}
				}
			}
		 System.out.println("值："+macStr);
			String rMac = MD5Util.mac(macStr, macKey);
			return rMac;
	    }

}
