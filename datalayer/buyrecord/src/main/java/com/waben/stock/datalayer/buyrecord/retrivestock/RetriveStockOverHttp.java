package com.waben.stock.datalayer.buyrecord.retrivestock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.waben.stock.datalayer.buyrecord.retrivestock.bean.StockMarket;
import com.waben.stock.interfaces.util.JacksonUtil;

public class RetriveStockOverHttp {

	public static StockMarket stockMarket(RestTemplate restTemplate, String code) {
		String url = "http://lemi.esongbai.com/stk/stk/list.do?codes=" + code;
		String response = restTemplate.getForObject(url, String.class);
		try {
			JsonNode dataNode = JacksonUtil.objectMapper.readValue(response, JsonNode.class).get("data");
			JavaType javaType = JacksonUtil.objectMapper.getTypeFactory().constructParametricType(ArrayList.class,
					StockMarket.class);
			List<StockMarket> list = JacksonUtil.objectMapper.readValue(dataNode.toString(), javaType);
			return list.get(0);
		} catch (IOException e) {
			throw new RuntimeException("http获取股票行情异常!", e);
		}
	}

}
