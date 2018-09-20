package com.waben.stock.applayer.admin.business.stockoption;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.interfaces.commonapi.retrivestock.RetriveStockOverHttp;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockMarket;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionAdminDto;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionBlacklistAdminDto;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionRiskAdminDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionAmountLimitDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionOrgDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionQuoteDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;
import com.waben.stock.interfaces.enums.StockOptionTradeState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.admin.stockoption.StockOptionAdminQuery;
import com.waben.stock.interfaces.pojo.query.admin.stockoption.StockOptionRiskAdminQuery;
import com.waben.stock.interfaces.service.stockoption.StockOptionOrgInterface;
import com.waben.stock.interfaces.service.stockoption.StockOptionTradeInterface;

/**
 * 期权交易 Business
 * 
 * @author luomengan
 */
@Service("adminStockOptionTradeBusiness")
public class StockOptionTradeBusiness {

	@Autowired
	private StockOptionTradeInterface reference;

	@Autowired
	private StockOptionOrgInterface orgReference;

	@Autowired
	private HolidayBusiness holidayBusiness;

	@Autowired
	private RestTemplate restTemplate;

	public List<StockOptionOrgDto> orgList() {
		Response<List<StockOptionOrgDto>> response = orgReference.lists();
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PageInfo<StockOptionAdminDto> adminPagesByQuery(StockOptionAdminQuery query) {
		Response<PageInfo<StockOptionAdminDto>> response = reference.adminPagesByQuery(query);
		if ("200".equals(response.getCode())) {
			List<StockOptionAdminDto> content = response.getResult().getContent();
			// 设置股票的最新价格
			if (content != null && content.size() > 0) {
				List<String> codes = new ArrayList<>();
				for (StockOptionAdminDto trade : content) {
					codes.add(trade.getStockCode());
				}
				if (codes.size() > 0) {
					List<StockMarket> stockMarketList = RetriveStockOverHttp.listStockMarket(restTemplate, codes);
					if (stockMarketList != null && stockMarketList.size() > 0) {
						for (int i = 0; i < stockMarketList.size(); i++) {
							StockMarket market = stockMarketList.get(i);
							StockOptionAdminDto record = content.get(i);
							record.setStockName(market.getName());
							record.setLastPrice(market.getLastPrice());
							BigDecimal profit = record.getProfit();
							if (profit == null) {
								if (record.getLastPrice() != null && record.getBuyingPrice() != null
										&& record.getLastPrice().compareTo(record.getBuyingPrice()) > 0) {
									record.setProfit(record.getLastPrice().subtract(record.getBuyingPrice())
											.divide(record.getBuyingPrice(), 10, RoundingMode.DOWN)
											.multiply(record.getNominalAmount()).setScale(2, RoundingMode.HALF_EVEN));
								} else {
									record.setProfit(BigDecimal.ZERO);
								}
							}
						}
					}
				}
			}
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockOptionTradeDto fail(Long id) {
		Response<StockOptionTradeDto> response = reference.fail(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockOptionTradeDto turnover(Long id, Long orgid, BigDecimal orgRightMoneyRatio, BigDecimal buyingPrice) {
		Response<StockOptionTradeDto> response = reference.turnover(id, orgid, orgRightMoneyRatio, buyingPrice);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockOptionTradeDto mark(Long id, Boolean isMark) {
		Response<StockOptionTradeDto> response = reference.mark(id, isMark);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockOptionTradeDto findById(Long id) {
		Response<StockOptionTradeDto> response = reference.fetchById(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}
	
	public StockOptionTradeDto dosettlement(Long id) {
		Response<StockOptionTradeDto> response = reference.dosettlement(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockOptionTradeDto insettlement(Long id, BigDecimal sellingPrice) {
		// T+3才能行权卖出
		StockOptionTradeDto trade = this.findById(id);
		Date buyingTime = trade.getBuyingTime();
		// 计算最近的能申请行权的时间，T+3
		Date now = new Date();
		Date date = holidayBusiness.getAfterTradeDate(buyingTime, 3);
		if ((trade.getState() == StockOptionTradeState.TURNOVER || trade.getState() == StockOptionTradeState.APPLYRIGHT
				|| trade.getState() == StockOptionTradeState.AUTOEXPIRE) && now.getTime() > date.getTime()) {
			Response<StockOptionTradeDto> response = reference.insettlement(id, sellingPrice);
			if ("200".equals(response.getCode())) {
				return response.getResult();
			}
			throw new ServiceException(response.getCode());
		} else {
			throw new ServiceException(ExceptionConstant.USERRIGHT_NOTMATCH_EXCEPTION);
		}
	}

	public PageInfo<StockOptionRiskAdminDto> adminNormalRiskPagesByQuery(StockOptionRiskAdminQuery query) {
		Response<PageInfo<StockOptionRiskAdminDto>> response = reference.adminNormalRiskPagesByQuery(query);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PageInfo<StockOptionRiskAdminDto> adminAbnormalRiskPagesByQuery(StockOptionRiskAdminQuery query) {
		Response<PageInfo<StockOptionRiskAdminDto>> response = reference.adminAbnormalRiskPagesByQuery(query);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PageInfo<StockOptionBlacklistAdminDto> adminBlackRiskPagesByQuery(StockOptionRiskAdminQuery query) {
		Response<PageInfo<StockOptionBlacklistAdminDto>> response = reference.adminBlackRiskPagesByQuery(query);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockOptionAmountLimitDto modifyStockOptionLimit(String stockCode, String stockName, Boolean isGlobal,
			BigDecimal amountLimit) {
		Response<StockOptionAmountLimitDto> response = reference.modifyStockOptionLimit(stockCode, stockName, isGlobal,
				amountLimit);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockOptionQuoteDto modifyStockOptionQuote(String stockCode, String stockName, Integer cycle,
			BigDecimal rightMoneyRatio) {
		Response<StockOptionQuoteDto> response = reference.modifyStockOptionQuote(stockCode, stockName, cycle,
				rightMoneyRatio);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public String deleteStockOptionLimit(String stockCode) {
		Response<String> response = reference.deleteStockOptionLimit(stockCode);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockOptionAmountLimitDto fetchGlobalStockOptionLimit() {
		Response<StockOptionAmountLimitDto> response = reference.fetchGlobalStockOptionLimit();
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockOptionTradeDto updateBuyingPrice(Long id,BigDecimal buyingPrice) {
		Response<StockOptionTradeDto> stockOptionTradeDtoResponse = reference.fetchById(id);
		StockOptionTradeDto result = stockOptionTradeDtoResponse.getResult();
		result.setBuyingPrice(buyingPrice);
		Response<StockOptionTradeDto> response = reference.update(result);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());	}
}
