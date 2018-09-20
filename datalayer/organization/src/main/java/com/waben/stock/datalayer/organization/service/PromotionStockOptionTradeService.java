package com.waben.stock.datalayer.organization.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.datalayer.organization.repository.DynamicQuerySqlDao;
import com.waben.stock.datalayer.organization.repository.impl.MethodDesc;
import com.waben.stock.interfaces.commonapi.retrivestock.RetriveStockOverHttp;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockMarket;
import com.waben.stock.interfaces.dto.organization.PromotionStockOptionTradeDto;
import com.waben.stock.interfaces.pojo.query.organization.PromotionStockOptionTradeQuery;

/**
 * 推广渠道产生的期权交易 Service
 * 
 * @author luomengan
 *
 */
@Service
public class PromotionStockOptionTradeService {

	@Autowired
	private DynamicQuerySqlDao dynamicQuerySqlDao;

	@Autowired
	private RestTemplate restTemplate;

	public Page<PromotionStockOptionTradeDto> pagesByQuery(PromotionStockOptionTradeQuery query) {
		String tradeIdCondition = "";
		if (query.getTradeId() != null && !"".equals(query.getTradeId().trim())) {
			tradeIdCondition = " and t1.id='" + query.getTradeId().trim() + "' ";
		}
		String stateCondition = "";
		if (query.getState() != null && !"".equals(query.getState()) && !"0".equals(query.getState())) {
			if ("4-5".equals(query.getState())) {
				stateCondition = " and (t1.state='4' or t1.state='5')";
			} else {
				stateCondition = " and t1.state='" + query.getState() + "' ";
			}
		}
		String publisherPhoneCondition = "";
		if (query.getPublisherPhone() != null && !"".equals(query.getPublisherPhone().trim())) {
			publisherPhoneCondition = " and t1.publisher_phone like '%" + query.getPublisherPhone() + "%' ";
		}
		String orgCodeCondition = "";
		if (query.getOrgCode() != null && !"".equals(query.getOrgCode())) {
			orgCodeCondition = " and t3.code like '%" + query.getOrgCode() + "%' ";
		}
		String isTestCondition = "";
		if (query.getUserType() != null && query.getUserType() == 1) {
			isTestCondition = " and (t4.is_test is null or t4.is_test=0) ";
		}
		if (query.getUserType() != null && query.getUserType() == 2) {
			isTestCondition = " and t4.is_test=1 ";
		}
		String sql = String.format(
				"select t1.id, t1.publisher_id, t1.publisher_phone, t1.stock_code, t1.stock_name, t1.cycle_name, "
						+ "t1.state, t1.nominal_amount, t1.right_money, t1.buying_time, t1.buying_price, t1.selling_price, "
						+ "t1.selling_time, t1.profit, t3.code as org_code, t3.name as org_name, t1.trade_no, t4.is_test from stock_option_trade t1 "
						+ "INNER JOIN p_organization_publisher t2 on t1.publisher_id=t2.publisher_id and t2.org_code like '%s%%' "
						+ "LEFT JOIN publisher t4 on t1.publisher_id=t4.id "
						+ "LEFT JOIN p_organization t3 on t3.code=t2.org_code where 1=1 %s %s %s %s %s order by t1.buying_time desc limit "
						+ query.getPage() * query.getSize() + "," + query.getSize(),
				query.getCurrentOrgCode(), tradeIdCondition, stateCondition, publisherPhoneCondition, orgCodeCondition,
				isTestCondition);
		String countSql = "select count(*) " + sql.substring(sql.indexOf("from"), sql.indexOf("limit"));
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setTradeId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setPublisherId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setPublisherPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setStockCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setStockName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setCycleName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setState", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setNominalAmount", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setRightMoney", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setBuyingTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(10), new MethodDesc("setBuyingPrice", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(11), new MethodDesc("setSellingPrice", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(12), new MethodDesc("setSellingTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(13), new MethodDesc("setProfit", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(14), new MethodDesc("setOrgCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(15), new MethodDesc("setOrgName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(16), new MethodDesc("setTradeNo", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(17), new MethodDesc("setIsTest", new Class<?>[] { Boolean.class }));
		List<PromotionStockOptionTradeDto> content = dynamicQuerySqlDao.execute(PromotionStockOptionTradeDto.class, sql,
				setMethodMap);
		BigInteger totalElements = dynamicQuerySqlDao.executeComputeSql(countSql);
		// 获取股票的最新价
		List<String> codes = new ArrayList<>();
		for (PromotionStockOptionTradeDto trade : content) {
			codes.add(trade.getStockCode());
		}
		if (codes.size() > 0) {
			List<StockMarket> stockMarketList = RetriveStockOverHttp.listStockMarket(restTemplate, codes);
			if (stockMarketList != null && stockMarketList.size() > 0) {
				for (int i = 0; i < stockMarketList.size(); i++) {
					StockMarket market = stockMarketList.get(i);
					PromotionStockOptionTradeDto record = content.get(i);
					record.setLastPrice(market.getLastPrice());
				}
			}
		}
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()),
				totalElements != null ? totalElements.longValue() : 0);
	}

}
