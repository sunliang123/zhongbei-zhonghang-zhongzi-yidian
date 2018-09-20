package com.waben.stock.applayer.admin.business.stockcontent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.stockcontent.StockInterface;

/**
 * 股票 Business
 * 
 * @author luomengan
 *
 */
@Service("adminStockBusiness")
public class StockBusiness {

	@Autowired
	private StockInterface stockReference;

	public StockDto downline(String code, String stockOptionBlackRemark) {
		Response<StockDto> response = stockReference.downline(code, stockOptionBlackRemark);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockDto online(String code) {
		Response<StockDto> response = stockReference.online(code);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public StockDto fetchByCode(String code) {
		Response<StockDto> response = stockReference.fetchWithExponentByCode(code);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
