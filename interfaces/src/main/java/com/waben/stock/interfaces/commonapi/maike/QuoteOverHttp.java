package com.waben.stock.interfaces.commonapi.maike;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.interfaces.commonapi.maike.bean.QuoteResponse;
import com.waben.stock.interfaces.commonapi.maike.bean.QuoteRet;
import com.waben.stock.interfaces.util.JacksonUtil;

@Component
public class QuoteOverHttp {

	private static RestTemplate restTemplate = new RestTemplate();

	private static Logger logger = LoggerFactory.getLogger(QuoteOverHttp.class);

	public static QuoteResponse<QuoteRet> quote(int pageIndex) {
		try {
			// 页面从1开始，0表示查询所有数据
			String requestUrl = "http://120.79.59.39:9091/mkquote?page=" + pageIndex;
			// 设置请求头
			logger.info("请求迈科询价接口数据页码:" + pageIndex);
			// 发送请求
			String resultJson = restTemplate.getForObject(requestUrl, String.class);
			QuoteResponse<QuoteRet> responseObj = JacksonUtil.decode(resultJson,
					JacksonUtil.getGenericType(QuoteResponse.class, QuoteRet.class));
			logger.info(
					"请求迈科询价接口响应成功，返回数据条数:" + (responseObj.getResult() != null ? responseObj.getResult().size() : 0));
			return responseObj;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("请求迈科询价接口异常!" + ex.getMessage());
		}
	}

	public static void testMain(String[] args) {
		System.out.println(quote(16).getResult().size());
	}

}
