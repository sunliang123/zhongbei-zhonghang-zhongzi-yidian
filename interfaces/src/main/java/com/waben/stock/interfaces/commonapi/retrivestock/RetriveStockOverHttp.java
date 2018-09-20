package com.waben.stock.interfaces.commonapi.retrivestock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockExponentVariety;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockKLine;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockMarket;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockTimeLine;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockVariety;
import com.waben.stock.interfaces.util.JacksonUtil;

public class RetriveStockOverHttp {

	public static List<StockVariety> listStockVariety(RestTemplate restTemplate, int page, int pageSize) {
		String url = "http://lemi.esongbai.com/order/order/getStockVariety.do?page=" + page + "&pageSize=" + pageSize;
		String response = restTemplate.getForObject(url, String.class);
		try {
			JsonNode dataNode = JacksonUtil.objectMapper.readValue(response, JsonNode.class).get("data");
			JavaType javaType = JacksonUtil.objectMapper.getTypeFactory().constructParametricType(ArrayList.class,
					StockVariety.class);
			List<StockVariety> list = JacksonUtil.objectMapper.readValue(dataNode.toString(), javaType);
			return list;
		} catch (IOException e) {
			throw new RuntimeException("http获取股票行情异常!", e);
		}
	}

	public static List<StockMarket> listStockMarket(RestTemplate restTemplate, List<String> codes) {
		String url = "http://lemi.esongbai.com/stk/stk/list.do?codes="
				+ codes.toString().substring(1, codes.toString().length() - 1).replaceAll(" ", "");
		String response = restTemplate.getForObject(url, String.class);
		try {
			JsonNode dataNode = JacksonUtil.objectMapper.readValue(response, JsonNode.class).get("data");
			JavaType javaType = JacksonUtil.objectMapper.getTypeFactory().constructParametricType(ArrayList.class,
					StockMarket.class);
			List<StockMarket> list = JacksonUtil.objectMapper.readValue(dataNode.toString(), javaType);
			return list;
		} catch (IOException e) {
			throw new RuntimeException("http获取股票行情异常!", e);
		}
	}

	public static List<StockExponentVariety> listStockExponentVariety(RestTemplate restTemplate) {
		String url = "http://lemi.esongbai.com/order/order/getStockExponentVariety.do";
		String response = restTemplate.getForObject(url, String.class);
		try {
			JsonNode dataNode = JacksonUtil.objectMapper.readValue(response, JsonNode.class).get("data");
			JavaType javaType = JacksonUtil.objectMapper.getTypeFactory().constructParametricType(ArrayList.class,
					StockExponentVariety.class);
			List<StockExponentVariety> list = JacksonUtil.objectMapper.readValue(dataNode.toString(), javaType);
			return list;
		} catch (IOException e) {
			throw new RuntimeException("http获取指数品种列表异常!", e);
		}
	}

	public static List<StockKLine> listKLine(RestTemplate restTemplate, String stockCode, Integer type,
			String startTime, String endTime, Integer limit) {
		StringBuilder url = new StringBuilder("http://lemi.esongbai.com/stk/stk/kline.do?code=" + stockCode);
		if (type == 1) {
			url.append("&type=day");
		} else if (type == 2) {
			url.append("&type=week");
		} else if (type == 3) {
			url.append("&type=month");
		} else {
			url.append("&type=day");
		}
		if (startTime != null && !"".equals(startTime)) {
			url.append("&startTime=" + startTime);
		}
		if (endTime != null && !"".equals(endTime)) {
			url.append("&endTime=" + endTime);
		}
		if (limit != null && limit > 0) {
			url.append("&limit=" + limit);
		}

		String response = restTemplate.getForObject(url.toString(), String.class);
		try {
			JsonNode dataNode = JacksonUtil.objectMapper.readValue(response, JsonNode.class).get("data");
			JavaType javaType = JacksonUtil.objectMapper.getTypeFactory().constructParametricType(ArrayList.class,
					StockKLine.class);
			List<StockKLine> list = JacksonUtil.objectMapper.readValue(dataNode.toString(), javaType);
			return list;
		} catch (IOException e) {
			throw new RuntimeException("http获取K线图数据异常!", e);
		}
	}

	public static List<StockTimeLine> listTimeLine(RestTemplate restTemplate, String stockCode) {
		StringBuilder url = new StringBuilder("http://lemi.esongbai.com/stk/stk/trend.do?code=" + stockCode);
		String response = restTemplate.getForObject(url.toString(), String.class);
		try {
			JsonNode dataNode = JacksonUtil.objectMapper.readValue(response, JsonNode.class).get("data");
			JavaType javaType = JacksonUtil.objectMapper.getTypeFactory().constructParametricType(ArrayList.class,
					StockTimeLine.class);
			List<StockTimeLine> list = JacksonUtil.objectMapper.readValue(dataNode.toString(), javaType);
			if (list != null && list.size() > 0) {
				for (int i = list.size() - 1; i >= 0; i--) {
					StockTimeLine inner = list.get(i);
					if (inner.getTime() != null && inner.getTime().split(" ").length == 2) {
						String time = inner.getTime().split(" ")[1];
						if ((time.compareTo("11:31:00") >= 0 && time.compareTo("13:00:00") < 0)
								|| (time.compareTo("15:01:00") >= 0) || (time.compareTo("09:00:00") < 0)) {
							list.remove(inner);
						}
					}
				}
			}
			return list;
		} catch (IOException e) {
			throw new RuntimeException("http获取分时图数据异常!", e);
		}
	}

}
