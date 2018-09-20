package com.waben.stock.interfaces.commonapi.rongpay.util;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class SignUtil {


	public static String getMapOrderStr(Map<String,String> request){
		List<String> fieldNames = new ArrayList<String>(request.keySet());
		Collections.sort(fieldNames);
		StringBuffer buf = new StringBuffer();
		Iterator<String> itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = String.valueOf(request.get(fieldName));
			if ((fieldValue != null) && (fieldValue.length() > 0)){
                if(!"sign".equals(fieldName) && !"sign_type".equals(fieldName) ){
                    buf.append(fieldName+"="+fieldValue+"&");
                }
			}
		}
		if(buf.length()>1) {
            buf.setLength(buf.length() - 1);
        }
		return buf.toString(); //去掉最后&

	}

	public static String resultSign(String json) throws Exception{
		TreeMap<String, String> map = JSON.parseObject(json,
				new TypeReference<TreeMap<String, String>>() {
				});
		return getMapOrderStr(map) ;
	}

	public static String sign(String content, PrivateKey privateKey)  throws Exception{
		String charset = "UTF-8";

		Signature signature = Signature.getInstance("SHA256WithRSA");

		signature.initSign(privateKey);
		signature.update(content.getBytes(charset));

		byte[] signed = signature.sign();

		return new String(Base64.encodeBase64(signed));

	}

}
