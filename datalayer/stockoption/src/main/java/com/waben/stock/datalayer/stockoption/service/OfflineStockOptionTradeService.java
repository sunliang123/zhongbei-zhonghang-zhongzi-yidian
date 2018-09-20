package com.waben.stock.datalayer.stockoption.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.datalayer.stockoption.entity.OfflineStockOptionTrade;
import com.waben.stock.datalayer.stockoption.entity.StockOptionTrade;
import com.waben.stock.datalayer.stockoption.repository.OfflineStockOptionTradeDao;
import com.waben.stock.datalayer.stockoption.repository.StockOptionTradeDao;
import com.waben.stock.interfaces.enums.OfflineStockOptionTradeState;
import com.waben.stock.interfaces.exception.DataNotFoundException;
import com.waben.stock.interfaces.util.JacksonUtil;

@Service
public class OfflineStockOptionTradeService {
	@Autowired
	private OfflineStockOptionTradeDao offlineStockOptionTradeDao;
	@Autowired
	private StockOptionTradeDao stockOptionTradeDao;
	Logger logger = LoggerFactory.getLogger(getClass());

	@Transactional
	public OfflineStockOptionTrade save(OfflineStockOptionTrade offlineStockOptionTrade) {
		StockOptionTrade stockOptionTrade = stockOptionTradeDao.retrieve(offlineStockOptionTrade.getId());
		logger.info("用户申购信息：{}", JacksonUtil.encode(stockOptionTrade));
		// 组装线下交易信息
		offlineStockOptionTrade.setId(null);
		offlineStockOptionTrade.setState(OfflineStockOptionTradeState.TURNOVER);
		offlineStockOptionTrade.setStockCode(stockOptionTrade.getStockCode());
		offlineStockOptionTrade.setStockName(stockOptionTrade.getStockName());
		offlineStockOptionTrade.setNominalAmount(stockOptionTrade.getNominalAmount());
		offlineStockOptionTrade.setRightMoney(
				stockOptionTrade.getNominalAmount().multiply(offlineStockOptionTrade.getRightMoneyRatio()));
		offlineStockOptionTrade.setCycle(stockOptionTrade.getCycle());
		offlineStockOptionTrade.setBuyingTime(new Date());
		// 到期时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, stockOptionTrade.getCycle());
		offlineStockOptionTrade.setExpireTime(calendar.getTime());
		// 将线下交易信息添加到数据库
		OfflineStockOptionTrade result = offlineStockOptionTradeDao.create(offlineStockOptionTrade);
		logger.info("添加线下交易信息结果：{}", JacksonUtil.encode(result));
		// 修改申购交易信息
		stockOptionTrade.setOfflineTrade(result);
		stockOptionTradeDao.update(stockOptionTrade);
		return result;
	}

	@Transactional
	public OfflineStockOptionTrade settlement(Long id, BigDecimal sellingPrice) {
		// 机构结算
		StockOptionTrade stockOptionTrade = stockOptionTradeDao.retrieve(id);
		OfflineStockOptionTrade offlineStockOptionTrade = stockOptionTrade.getOfflineTrade();
		offlineStockOptionTrade.setSellingPrice(sellingPrice);
		offlineStockOptionTrade.setSellingTime(new Date());
		offlineStockOptionTrade.setState(OfflineStockOptionTradeState.SETTLEMENTED);
		offlineStockOptionTrade
				.setProfit(stockOptionTrade.getRightMoney().subtract(offlineStockOptionTrade.getRightMoney()));
		return offlineStockOptionTradeDao.update(offlineStockOptionTrade);
	}

	@Transactional
	public OfflineStockOptionTrade exercise(Long id) {
		OfflineStockOptionTrade offlineStockOptionTrade = offlineStockOptionTradeDao.retrieve(id);
		offlineStockOptionTrade.setState(OfflineStockOptionTradeState.APPLYRIGHT);
		offlineStockOptionTrade.setRightTime(new Date());
		OfflineStockOptionTrade result = offlineStockOptionTradeDao.update(offlineStockOptionTrade);
		return result;
	}

	public OfflineStockOptionTrade findById(Long id) {
		OfflineStockOptionTrade result = offlineStockOptionTradeDao.retrieve(id);
		if (result == null) {
			throw new DataNotFoundException("机构期权信息未找到");
		}
		return result;
	}

	public List<OfflineStockOptionTrade> findByStateAndSellingTimeBetween(OfflineStockOptionTradeState state,
			String year) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = null;
		Date end = null;
		try {
			start = sdf.parse(year + "-01-01 00:00:00");
			end = sdf.parse(year + "-12-31 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return offlineStockOptionTradeDao.retrieveByStateAndSellingTimeBetween(state, start, end);
	}

}
