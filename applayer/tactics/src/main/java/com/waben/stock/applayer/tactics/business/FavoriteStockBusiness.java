package com.waben.stock.applayer.tactics.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.tactics.dto.publisher.FavoriteStockWithMarketDto;
import com.waben.stock.applayer.tactics.service.StockMarketService;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockMarket;
import com.waben.stock.interfaces.dto.publisher.FavoriteStockDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.publisher.FavoriteStockInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * 收藏股票 Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsFavoriteStockBusiness")
public class FavoriteStockBusiness {

	@Autowired
	private FavoriteStockInterface favoriteStockReference;

	@Autowired
	private StockMarketService stockMarketService;

	public List<String> listsStockCode(Long publisherId) {
		Response<List<String>> response = favoriteStockReference.listsStockCode(publisherId);
		if (response.getCode().equals("200")) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public FavoriteStockDto save(FavoriteStockDto favoriteStockDto) {
		Response<FavoriteStockDto> response = favoriteStockReference.add(favoriteStockDto);
		if (response.getCode().equals("200")) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public void remove(Long publisherId, String stockCodes) {
		Response<Void> response = favoriteStockReference.drop(publisherId, stockCodes);
		if (!response.getCode().equals("200")) {
			throw new ServiceException(response.getCode());
		}

	}

	public void top(Long publisherId, String stockCodes) {
		Response<Void> response = favoriteStockReference.top(publisherId, stockCodes);
		if (!response.getCode().equals("200")) {
			throw new ServiceException(response.getCode());
		}
	}

	public List<FavoriteStockWithMarketDto> listsByPublisherId(Long publisherId) {
		Response<List<FavoriteStockDto>> response = favoriteStockReference.listsByPublisherId(publisherId);
		if (response.getCode().equals("200")) {
			if (response.getResult() != null && response.getResult().size() > 0) {
				List<FavoriteStockWithMarketDto> result = CopyBeanUtils
						.copyListBeanPropertiesToList(response.getResult(), FavoriteStockWithMarketDto.class);
				List<String> codes = new ArrayList<>();
				for (FavoriteStockWithMarketDto favorite : result) {
					codes.add(favorite.getCode());
				}
				List<StockMarket> marketList = stockMarketService.listStockMarket(codes);
				for (int i = 0; i < result.size(); i++) {
					FavoriteStockWithMarketDto favorite = result.get(i);
					StockMarket market = marketList.get(i);
					favorite.setLastPrice(market.getLastPrice());
					favorite.setUpDropPrice(market.getUpDropPrice());
					favorite.setUpDropSpeed(market.getUpDropSpeed());
				}
				return result;
			} else {
				return new ArrayList<>();
			}
		}
		throw new ServiceException(response.getCode());
	}

}
