package com.waben.stock.applayer.promotion.business.stockoption;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.interfaces.commonapi.retrivestock.RetriveStockOverHttp;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockMarket;
import com.waben.stock.interfaces.dto.promotion.stockoption.StockOptionPromotionDto;
import com.waben.stock.interfaces.dto.promotion.stockoption.StockOptionStaDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.promotion.stockoption.StockOptionPromotionQuery;
import com.waben.stock.interfaces.service.stockoption.StockOptionTradeInterface;

/**
 * 期权交易 Business
 * 
 * @author luomengan
 */
@Service("promotionStockOptionTradeBusiness")
public class StockOptionTradeBusiness {

	@Autowired
	private StockOptionTradeInterface reference;

	@Autowired
	private RestTemplate restTemplate;

	public StockOptionStaDto promotionSta(StockOptionPromotionQuery query) {
		Response<StockOptionStaDto> response = reference.promotionSta(query);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PageInfo<StockOptionPromotionDto> promotionPagesByQuery(StockOptionPromotionQuery query) {
		Response<PageInfo<StockOptionPromotionDto>> response = reference.promotionPagesByQuery(query);
		if ("200".equals(response.getCode())) {
			List<StockOptionPromotionDto> content = response.getResult().getContent();
			// 设置股票的最新价格
			if (content != null && content.size() > 0) {
				List<List<String>> codes = new ArrayList<>();
				int index = 0;
				for (StockOptionPromotionDto trade : content) {
					if(index % 100 == 0) {
						codes.add(new ArrayList<String>());
					}
					codes.get(codes.size()-1).add(trade.getStockCode());
					index++;
				}
				if (codes.size() > 0) {
					List<StockMarket> stockMarketList = new ArrayList<>();
					for(List<String> innerCodes : codes) {
						List<StockMarket> innerList = RetriveStockOverHttp.listStockMarket(restTemplate, innerCodes);
						stockMarketList.addAll(innerList);
					}
					if (stockMarketList != null && stockMarketList.size() > 0) {
						for (int i = 0; i < stockMarketList.size(); i++) {
							StockMarket market = stockMarketList.get(i);
							StockOptionPromotionDto record = content.get(i);
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

}
