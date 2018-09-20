package com.waben.stock.risk.hmac;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class MD5Util {

	public static String MD5Encode(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
        	e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return result;
    }
	
	public static String mac(String macStr, String mackey) throws Exception {
		String s = MD5Encode(macStr+mackey);
		System.out.println("MD5Encode:"+s);
		BASE64Encoder base64en = new BASE64Encoder();
        String newstr=base64en.encode(s.getBytes("utf-8"));
        System.out.println("BASE64Encoder:"+newstr);
        return newstr;
	}
	
	public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes(Charset.forName("UTF-8"));
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str).toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
	
	/*public static void main(String[] args) {
		try {
			JSONObject json = new JSONObject();

			json.put("transType", "B001");
			json.put("payPass", "1");
			json.put("transAmt", "100");
			json.put("userId", "15901101057");
			json.put("sysType", "ios");
			Map<String, String> contentData = (Map<String, String>) Tools.parserToMap(json.toString());
			String macStr="";
			Object[] key_arr = contentData.keySet().toArray();
			Arrays.sort(key_arr);
			for (Object key : key_arr) {
				Object value = contentData.get(key);
				if (value != null ) {
					if (!key.equals("mac") && !key.equals("signPicBuffer")&& !key.equals("imageData1") && !key.equals("imageData2")&& !key.equals("imageData3")) {
						macStr+= value.toString();
					}
				}
			}
			System.out.println(macStr);
			String s = mac(macStr, "32029ED76A36F701B887A1BDF730612E");
			System.out.println(s);
//			BASE64Encoder base64en = new BASE64Encoder();
//	        String newstr=base64en.encode(s.getBytes("utf-8"));
//			System.out.println("md5===="+newstr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
