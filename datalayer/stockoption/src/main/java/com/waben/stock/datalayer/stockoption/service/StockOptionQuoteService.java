package com.waben.stock.datalayer.stockoption.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.stockoption.business.PriceMarkupConfigBusiness;
import com.waben.stock.datalayer.stockoption.entity.StockOptionAmountLimit;
import com.waben.stock.datalayer.stockoption.entity.StockOptionCycle;
import com.waben.stock.datalayer.stockoption.entity.StockOptionOrgQuote;
import com.waben.stock.datalayer.stockoption.entity.StockOptionQuote;
import com.waben.stock.datalayer.stockoption.repository.StockOptionAmountLimitDao;
import com.waben.stock.datalayer.stockoption.repository.StockOptionCycleDao;
import com.waben.stock.datalayer.stockoption.repository.StockOptionOrgQuoteDao;
import com.waben.stock.datalayer.stockoption.repository.StockOptionQuoteDao;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * 期权报价 Service
 * 
 * @author luomengan
 *
 */
@Service
public class StockOptionQuoteService {

	@Autowired
	private StockOptionOrgQuoteDao orgQuoteDao;

	@Autowired
	private StockOptionQuoteDao quoteDao;

	@Autowired
	private StockOptionCycleDao cycleDao;

	@Autowired
	private PriceMarkupConfigBusiness priceMarkupBusiness;

	@Autowired
	private StockOptionAmountLimitDao limitDao;

	@Value("${price.markup:0.1}")
	private String priceMarkup;

	// TODO 特殊需求，两周且20W的此处暂时做特殊处理，上调20% ，后续要调整为更通用的方式
	@Value("${price.iscycle14w20handle:false}")
	private boolean isCycle14W20Handle;

	/**
	 * 获取机构报价
	 * <p>
	 * 如果存在多个机构的报价，取报价高的
	 * </p>
	 * 
	 * @param stockCode
	 *            股票代码
	 * @param cycle
	 *            周期
	 * @return 机构报价
	 */
	private StockOptionOrgQuote orgQuote(String stockCode, Integer cycle) {
		List<StockOptionOrgQuote> orgQuoteList = orgQuoteDao.findByStockCodeAndCycle(stockCode, cycle);
		if (orgQuoteList != null && orgQuoteList.size() > 0) {
			Collections.sort(orgQuoteList, new Comparator<StockOptionOrgQuote>() {
				@Override
				public int compare(StockOptionOrgQuote o1, StockOptionOrgQuote o2) {
					BigDecimal ratio1 = o1.getRightMoneyRatio();
					BigDecimal ratio2 = o2.getRightMoneyRatio();
					if (ratio1 != null && ratio2 != null) {
						return o2.getRightMoneyRatio().compareTo(o1.getRightMoneyRatio());
					} else if (ratio1 != null && ratio2 == null) {
						return -1;
					} else if (ratio1 == null && ratio2 != null) {
						return 1;
					} else {
						return 0;
					}
				}
			});
			return orgQuoteList.get(0);
		}
		return null;
	}

	private Long getTodayMillseconds() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime().getTime();
	}

	/**
	 * 客户询价
	 * 
	 * @param publisherId
	 *            用户ID
	 * @param stockCode
	 *            股票代码
	 * @param cycle
	 *            周期
	 * @param nominalAmount
	 *            名义本金
	 * @return 给客户的报价
	 */
	public StockOptionQuote quote(Long publisherId, String stockCode, Integer cycle, BigDecimal nominalAmount) {
		// step 1 : 获取机构报价
		StockOptionOrgQuote orgQuote = orgQuote(stockCode, cycle);
		if (orgQuote != null) {
			StockOptionCycle cycleObj = cycleDao.retrieveByCycle(cycle);
			StockOptionQuote result = null;
			StockOptionQuote quote = quoteDao.retrieveByStockCodeAndCycle(stockCode, cycle);
			if (quote != null && quote.getUpdateTime().getTime() >= getTodayMillseconds()) {
				// 如果手动设置了接口费率，使用手动设置的接口费率
				result = CopyBeanUtils.copyBeanProperties(StockOptionQuote.class, quote, false);
			} else {
				result = CopyBeanUtils.copyBeanProperties(StockOptionQuote.class, orgQuote, false);
			}
			if (cycleObj == null) {
				// 使用spring cloud config中配置的加价比例
				BigDecimal rightMoneyRatio = result.getRightMoneyRatio();
				result.setRightMoneyRatio(rightMoneyRatio.add(rightMoneyRatio.multiply(new BigDecimal(priceMarkup)))
						.setScale(4, RoundingMode.HALF_EVEN));
				// TODO 特殊需求，两周且20W的此处暂时做特殊处理，上调20% ，后续要调整为更通用的方式
				if (isCycle14W20Handle && cycle == 14 && nominalAmount != null
						&& nominalAmount.compareTo(new BigDecimal("200000")) == 0) {
					result.setRightMoneyRatio(result.getRightMoneyRatio().multiply(new BigDecimal("1.2")).setScale(4,
							RoundingMode.HALF_EVEN));
				}
			} else if (publisherId != null && publisherId > 0) {
				List<BigDecimal> ratioList = priceMarkupBusiness.priceMarkupRatioList(Integer.valueOf(2),
						cycleObj.getId(), publisherId);
				if (ratioList != null && ratioList.size() > 0) {
					// 使用机构设置的加价比例
					BigDecimal orgRightMoneyRatio = result.getRightMoneyRatio();
					BigDecimal rightMoneyRatio = orgRightMoneyRatio.abs();
					for (BigDecimal ratio : ratioList) {
						rightMoneyRatio = rightMoneyRatio
								.add(rightMoneyRatio.multiply(ratio.divide(new BigDecimal("100"))))
								.setScale(4, RoundingMode.HALF_EVEN);
					}
					result.setRightMoneyRatio(rightMoneyRatio);
				} else {
					// 使用spring cloud config中配置的加价比例
					BigDecimal rightMoneyRatio = result.getRightMoneyRatio();
					result.setRightMoneyRatio(rightMoneyRatio.add(rightMoneyRatio.multiply(new BigDecimal(priceMarkup)))
							.setScale(4, RoundingMode.HALF_EVEN));
				}
				// TODO 特殊需求，两周且20W的此处暂时做特殊处理，上调20% ，后续要调整为更通用的方式
				if (isCycle14W20Handle && cycle == 14 && nominalAmount != null
						&& nominalAmount.compareTo(new BigDecimal("200000")) == 0) {
					result.setRightMoneyRatio(result.getRightMoneyRatio().multiply(new BigDecimal("1.2")).setScale(4,
							RoundingMode.HALF_EVEN));
				}
			}

			// 获取最大限额
			StockOptionAmountLimit globalLimit = limitDao.retrieveGlobal();
			StockOptionAmountLimit stockLimit = limitDao.retrieveByStockCode(stockCode);
			BigDecimal limitNominalAmount = null;
			if (globalLimit != null) {
				limitNominalAmount = globalLimit.getAmountLimit();
			}
			if (stockLimit != null) {
				limitNominalAmount = stockLimit.getAmountLimit();
			}
			result.setLimitNominalAmount(limitNominalAmount);
			return result;
		}
		return null;
	}

}
