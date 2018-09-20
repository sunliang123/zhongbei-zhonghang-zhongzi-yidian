package com.waben.stock.datalayer.stockoption.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.stockoption.entity.StockOptionAmountLimit;
import com.waben.stock.datalayer.stockoption.entity.StockOptionQuote;
import com.waben.stock.datalayer.stockoption.repository.DynamicQuerySqlDao;
import com.waben.stock.datalayer.stockoption.repository.StockOptionAmountLimitDao;
import com.waben.stock.datalayer.stockoption.repository.StockOptionQuoteDao;
import com.waben.stock.datalayer.stockoption.repository.impl.MethodDesc;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionBlacklistAdminDto;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionRiskAdminDto;
import com.waben.stock.interfaces.pojo.query.admin.stockoption.StockOptionRiskAdminQuery;
import com.waben.stock.interfaces.util.StringUtil;

/**
 * 期权风控 Service
 * 
 * @author luomengan
 *
 */
@Service
public class StockOptionRiskService {

	private SimpleDateFormat sdfJustDay = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private DynamicQuerySqlDao sqlDao;

	@Autowired
	private StockOptionAmountLimitDao limitDao;

	@Autowired
	private StockOptionQuoteDao quoteDao;

	public StockOptionAmountLimit modifyStockOptionLimit(String stockCode, String stockName, Boolean isGlobal,
			BigDecimal amountLimit) {
		if (isGlobal) {
			StockOptionAmountLimit limit = limitDao.retrieveGlobal();
			if (limit != null) {
				limit.setAmountLimit(amountLimit);
				limit.setUpdateTime(new Date());
				return limitDao.update(limit);
			} else {
				limit = new StockOptionAmountLimit();
				limit.setAmountLimit(amountLimit);
				limit.setIsGlobal(true);
				limit.setUpdateTime(new Date());
				return limitDao.create(limit);
			}
		} else {
			StockOptionAmountLimit limit = limitDao.retrieveByStockCode(stockCode);
			if (limit != null) {
				limit.setAmountLimit(amountLimit);
				limit.setIsGlobal(false);
				limit.setUpdateTime(new Date());
				return limitDao.update(limit);
			} else {
				limit = new StockOptionAmountLimit();
				limit.setAmountLimit(amountLimit);
				limit.setIsGlobal(false);
				limit.setStockCode(stockCode);
				limit.setStockName(stockName);
				limit.setUpdateTime(new Date());
				return limitDao.create(limit);
			}
		}
	}

	public StockOptionQuote modifyStockOptionQuote(String stockCode, String stockName, Integer cycle,
			BigDecimal rightMoneyRatio) {
		StockOptionQuote quote = quoteDao.retrieveByStockCodeAndCycle(stockCode, cycle);
		if (quote != null) {
			quote.setRightMoneyRatio(rightMoneyRatio);
			quote.setUpdateTime(new Date());
			quoteDao.update(quote);
		} else {
			quote = new StockOptionQuote();
			quote.setCycle(cycle);
			quote.setRightMoneyRatio(rightMoneyRatio);
			quote.setStockCode(stockCode);
			quote.setStockName(stockName);
			quote.setUpdateTime(new Date());
			quoteDao.create(quote);
		}
		return quote;
	}

	public Page<StockOptionRiskAdminDto> adminNormalPagesByQuery(StockOptionRiskAdminQuery query) {
		String cycleIdCondition = "";
		if (query.getCycleId() != null && query.getCycleId() > 0) {
			cycleIdCondition = " and t3.id=" + query.getCycleId() + " ";
		}
		String stockCodeCondition = "";
		if (!StringUtil.isEmpty(query.getStockCode())) {
			stockCodeCondition = " and t1.stock_code like '%" + query.getStockCode() + "%' ";
		}
		String stockNameCondition = "";
		if (!StringUtil.isEmpty(query.getStockName())) {
			stockCodeCondition = " and t2.name like '%" + query.getStockName() + "%' ";
		}
		String today = sdfJustDay.format(new Date()) + " 00:00:00";
		String sql = String
				.format("select t2.code, t2.name, t3.id as cycle_id, t3.name as cycle_name, IFNULL(t5.amount_limit, t6.amount_limit) as amount_limit_final, IFNULL(t7.nominal_amount, 0) as amount_limit_used, "
						+ "t1.right_money_ratio as interface_ratio, t9.right_money_ratio as interface_ratio_reset, IFNULL(t9.right_money_ratio, t1.right_money_ratio) as interface_ratio_final, t4.ratio as markup_ratio, t8.right_money_ratio as org_ratio "
						+ "from (select * from (select * from stock_option_org_quote order by stock_code asc, cycle asc, right_money_ratio desc) innerquote group by stock_code, cycle) t1 "
						+ "LEFT JOIN stock t2 on t1.stock_code=t2.code "
						+ "LEFT JOIN stock_option_cycle t3 on t1.cycle=t3.cycle "
						+ "LEFT JOIN p_price_markup_config t4 on t4.org_id=1 and t4.resource_type=2 and t4.resource_id=t3.id "
						+ "LEFT JOIN stock_option_amount_limit t5 on (t5.is_global=0 or t5.is_global is null) and t5.stock_code=t2.code "
						+ "LEFT JOIN stock_option_amount_limit t6 on t6.is_global=1 "
						+ "LEFT JOIN (select stock_code, sum(nominal_amount) as nominal_amount from stock_option_trade where (is_test is null or is_test=0) and ((state=1 and apply_time>='"
						+ today + "') or (state=3 and buying_time>='" + today
						+ "')) group by stock_code) t7 on t7.stock_code=t2.code "
						+ "LEFT JOIN (select * from (select stock_code, right_money_ratio, buying_time, cycle from offline_stock_option_trade order by buying_time desc) b where buying_time>='"
						+ today + "' GROUP BY stock_code, cycle) t8 on t2.code=t8.stock_code and t8.cycle=t1.cycle "
						+ "LEFT JOIN (select stock_code, right_money_ratio, cycle from stock_option_quote where update_time>='"
						+ today
						+ "' group by stock_code, cycle order by stock_code asc, cycle asc, right_money_ratio desc) t9 on t9.stock_code=t2.code and t9.cycle=t1.cycle "
						+ "where 1=1 %s %s %s and (t2.stock_option_state is null or t2.stock_option_state=1) and t2.status=1 and t2.name not like 'ST%%' and t2.name not like '*ST%%' having amount_limit_final>0 and amount_limit_final>amount_limit_used and (org_ratio is null or org_ratio<=interface_ratio_final) "
						+ "order by code asc, cycle_id asc limit " + query.getPage() * query.getSize() + ","
						+ query.getSize(), cycleIdCondition, stockCodeCondition, stockNameCondition);
		String countSql = "select count(*) from (" + sql.substring(0, sql.lastIndexOf("limit")) + ") c";
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setStockCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setStockName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setCycleId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setCycleName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setAmountLimitFinal", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setAmountLimitUsed", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setInterfaceRatio", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setInterfaceRatioReset", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setInterfaceRatioFinal", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setMarkupRatio", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(10), new MethodDesc("setOrgRatio", new Class<?>[] { BigDecimal.class }));
		List<StockOptionRiskAdminDto> content = sqlDao.execute(StockOptionRiskAdminDto.class, sql, setMethodMap);
		BigInteger totalElements = sqlDao.executeComputeSql(countSql);
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()),
				totalElements != null ? totalElements.longValue() : 0);
	}

	public Page<StockOptionRiskAdminDto> adminAbnormalPagesByQuery(StockOptionRiskAdminQuery query) {
		String cycleIdCondition = "";
		if (query.getCycleId() != null && query.getCycleId() > 0) {
			cycleIdCondition = " and t3.id=" + query.getCycleId() + " ";
		}
		String stockCodeCondition = "";
		if (!StringUtil.isEmpty(query.getStockCode())) {
			stockCodeCondition = " and t1.stock_code like '%" + query.getStockCode() + "%' ";
		}
		String stockNameCondition = "";
		if (!StringUtil.isEmpty(query.getStockName())) {
			stockCodeCondition = " and t2.name like '%" + query.getStockName() + "%' ";
		}
		String today = sdfJustDay.format(new Date()) + " 00:00:00";
		String sql = String
				.format("select t2.code, t2.name, t3.id as cycle_id, t3.name as cycle_name, IFNULL(t5.amount_limit, t6.amount_limit) as amount_limit_final, IFNULL(t7.nominal_amount, 0) as amount_limit_used, "
						+ "t1.right_money_ratio as interface_ratio, t9.right_money_ratio as interface_ratio_reset, IFNULL(t9.right_money_ratio, t1.right_money_ratio) as interface_ratio_final, t4.ratio as markup_ratio, t8.right_money_ratio as org_ratio "
						+ "from (select * from (select * from stock_option_org_quote order by stock_code asc, cycle asc, right_money_ratio desc) innerquote group by stock_code, cycle) t1 "
						+ "LEFT JOIN stock t2 on t1.stock_code=t2.code "
						+ "LEFT JOIN stock_option_cycle t3 on t1.cycle=t3.cycle "
						+ "LEFT JOIN p_price_markup_config t4 on t4.org_id=1 and t4.resource_type=2 and t4.resource_id=t3.id "
						+ "LEFT JOIN stock_option_amount_limit t5 on (t5.is_global=0 or t5.is_global is null) and t5.stock_code=t2.code "
						+ "LEFT JOIN stock_option_amount_limit t6 on t6.is_global=1 "
						+ "LEFT JOIN (select stock_code, sum(nominal_amount) as nominal_amount from stock_option_trade where (is_test is null or is_test=0) and ((state=1 and apply_time>='"
						+ today + "') or (state=3 and buying_time>='" + today
						+ "')) group by stock_code) t7 on t7.stock_code=t2.code "
						+ "LEFT JOIN (select * from (select stock_code, right_money_ratio, buying_time, cycle from offline_stock_option_trade order by buying_time desc) b where buying_time>='"
						+ today + "' GROUP BY stock_code, cycle) t8 on t2.code=t8.stock_code and t8.cycle=t1.cycle "
						+ "LEFT JOIN (select stock_code, right_money_ratio, cycle from stock_option_quote where update_time>='"
						+ today
						+ "' group by stock_code, cycle order by stock_code asc, cycle asc, right_money_ratio desc) t9 on t9.stock_code=t2.code and t9.cycle=t1.cycle "
						+ "where 1=1 %s %s %s and (t2.stock_option_state is null or t2.stock_option_state=1) and t2.status=1 and t2.name not like 'ST%%' and t2.name not like '*ST%%' having amount_limit_final<=0 or amount_limit_final<=amount_limit_used or (org_ratio is not null and org_ratio>interface_ratio_final) "
						+ "order by code asc, cycle_id asc limit " + query.getPage() * query.getSize() + ","
						+ query.getSize(), cycleIdCondition, stockCodeCondition, stockNameCondition);
		String countSql = "select count(*) from (" + sql.substring(0, sql.lastIndexOf("limit")) + ") c";
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setStockCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setStockName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setCycleId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setCycleName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setAmountLimitFinal", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setAmountLimitUsed", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setInterfaceRatio", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setInterfaceRatioReset", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setInterfaceRatioFinal", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setMarkupRatio", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(10), new MethodDesc("setOrgRatio", new Class<?>[] { BigDecimal.class }));
		List<StockOptionRiskAdminDto> content = sqlDao.execute(StockOptionRiskAdminDto.class, sql, setMethodMap);
		BigInteger totalElements = sqlDao.executeComputeSql(countSql);
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()),
				totalElements != null ? totalElements.longValue() : 0);
	}

	public Page<StockOptionBlacklistAdminDto> adminBlackPagesByQuery(StockOptionRiskAdminQuery query) {
		String stockCodeCondition = "";
		if (!StringUtil.isEmpty(query.getStockCode())) {
			stockCodeCondition = " and z.code like '%" + query.getStockCode() + "%' ";
		}
		String stockNameCondition = "";
		if (!StringUtil.isEmpty(query.getStockName())) {
			stockNameCondition = " and z.name like '%" + query.getStockName() + "%' ";
		}
		String statusCondition = "";
		if (query.getStatus() != null) {
			statusCondition = " and z.status=" + (query.getStatus() ? "1" : "0") + " ";
		}
		String sql = String
				.format("select * from ((select t1.id, t1.code, t1.name, t1.abbr, t1.status, t1.stock_option_state, t1.stock_option_black_remark, false as has_interface_ratio from stock t1 "
						+ "where not exists (select 1 from stock_option_org_quote t2 where t2.stock_code=t1.code) and (t1.stock_option_state is null or t1.stock_option_state=1) and t1.status=1 and t1.name not like 'ST%%' and t1.name not like '*ST%%') "
						+ "union all (select id, code, name, abbr, status, stock_option_state, stock_option_black_remark, exists (select 1 from stock_option_org_quote where stock_code=code) as has_interface_ratio from stock where stock_option_state=2 or status=0 or name like 'ST%%' or name like '*ST%%')) z "
						+ "where 1=1 %s %s %s order by z.code asc limit " + query.getPage() * query.getSize() + ","
						+ query.getSize(), stockCodeCondition, stockNameCondition, statusCondition);
		String countSql = "select count(*) from (" + sql.substring(0, sql.lastIndexOf("limit")) + ") c";
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setAbbr", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setStatus", new Class<?>[] { Boolean.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setStockOptionState", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setStockOptionBlackRemark", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setHasInterfaceRatio", new Class<?>[] { Integer.class }));
		List<StockOptionBlacklistAdminDto> content = sqlDao.execute(StockOptionBlacklistAdminDto.class, sql,
				setMethodMap);
		BigInteger totalElements = sqlDao.executeComputeSql(countSql);
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()),
				totalElements != null ? totalElements.longValue() : 0);
	}

	public void deleteStockOptionLimit(String stockCode) {
		StockOptionAmountLimit stockLimit = limitDao.retrieveByStockCode(stockCode);
		if (stockLimit != null) {
			limitDao.delete(stockLimit.getId());
		}
	}

	public StockOptionAmountLimit findStockOptionQuote() {
		return limitDao.retrieveGlobal();
	}

}
