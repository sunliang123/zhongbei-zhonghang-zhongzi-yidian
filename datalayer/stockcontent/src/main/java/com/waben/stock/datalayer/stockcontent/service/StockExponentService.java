package com.waben.stock.datalayer.stockcontent.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.stockcontent.entity.Stock;
import com.waben.stock.datalayer.stockcontent.entity.StockExponent;
import com.waben.stock.datalayer.stockcontent.repository.StockExponentDao;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc
 */
@Service
public class StockExponentService {

	@Autowired
	private StockExponentDao stockExponentDao;

	public List<StockExponent> findStockExponts() {
		return stockExponentDao.list();
	}

	public StockExponent findStockExponentOnly(String exponeneCode) {
		return stockExponentDao.retrieveWithExponeneCode(exponeneCode);
	}

	public StockExponent findStockExponent(String exponeneCode) {
		StockExponent exponent = stockExponentDao.retrieveWithExponeneCode(exponeneCode);
		StringBuilder str = new StringBuilder();
		Set<Stock> stocks = exponent.getStocks();

		int i = 0;
		for (Stock stock : stocks) {
			str.append(stock.getCode() + ",");
			i++;
			if (i == 500 || i == 1000) {
				System.out.println("stockStr:" + str.toString() + ":stockStr");
				str = new StringBuilder();
			}
		}
		System.out.println("stockStr:" + str.toString() + ":stockStr");
		return exponent;
	}

}
