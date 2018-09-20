package com.waben.stock.interfaces.commonapi.rongpay.util;

import java.util.Map;

import com.waben.stock.interfaces.commonapi.rongpay.config.ReapalH5Config;
import com.waben.stock.interfaces.util.JacksonUtil;

public class ReapalSubmit {
	/**
     * 发送请求
     * @return 返回结果
     */
	public static String buildSubmit(Map<String, String> para, String url) throws Exception {
        //生成签名
		String mysign = Md5Utils.BuildMysign(para, ReapalH5Config.key);
        para.put("sign", mysign);
        //将map转化为json 串
		String json = JacksonUtil.encode(para);
		//加密数据
		Map<String, String> maps = DecipherH5.encryptData(json, ReapalH5Config.pubKeyUrl);
		maps.put("merchant_id", ReapalH5Config.merchant_id);
		//发送请求 并接收
		String post = HttpClientUtil.post(ReapalH5Config.rongpay_api+ url, maps);
		return post;
	}
	
	
}
