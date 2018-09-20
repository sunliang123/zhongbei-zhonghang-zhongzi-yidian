package com.waben.stock.applayer.strategist.business;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.stockoption.StockOptionCycleDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionQuoteDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.stockoption.StockOptionCycleInterface;

/**
 * 期权周期 Business
 * 
 * @author luomengan
 *
 */
@Service("strategistStockOptionCycleBusiness")
public class StockOptionCycleBusiness {

	@Autowired
	private StockOptionCycleInterface stockOptionCycleReference;
	
	@Autowired
	private StockOptionQuoteBusiness quoteBusiness;

	public StockOptionCycleDto fetchByCycle(Integer cycle) {
		Response<StockOptionCycleDto> response = stockOptionCycleReference.fetchByCycle(cycle);
		if (response.getCode().equals("200")) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}
	
	public List<StockOptionCycleDto> lists(String stockCode) {
		// 获取最大限额名义本金
		BigDecimal limitNominalAmount = new BigDecimal(-1);
		if (stockCode != null) {
			StockOptionQuoteDto quote = quoteBusiness.quote(0L, stockCode, 14, BigDecimal.ZERO);
			if (quote != null && quote.getLimitNominalAmount() != null) {
				limitNominalAmount = quote.getLimitNominalAmount();
			}
		}
		// 获取期权周期
		Response<List<StockOptionCycleDto>> response = stockOptionCycleReference.lists();
		if (response.getCode().equals("200")) {
			List<StockOptionCycleDto> list = response.getResult();
			for (int i = list.size() - 1; i >= 0; i--) {
				list.get(i).setLimitNominalAmount(limitNominalAmount);
				if (list.get(i).getEnable() != null && !list.get(i).getEnable()) {
					list.remove(i);
				}
			}
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockOptionCycleDto fetchById(Long id) {
		Response<StockOptionCycleDto> response = stockOptionCycleReference.fetchById(id);
		if (response.getCode().equals("200")) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
