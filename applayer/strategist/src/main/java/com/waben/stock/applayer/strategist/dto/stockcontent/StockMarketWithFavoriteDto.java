package com.waben.stock.applayer.strategist.dto.stockcontent;

import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockMarket;

/**
 * 股票行情和是否收藏
 * 
 * @author luomengan
 *
 */
public class StockMarketWithFavoriteDto extends StockMarket {

	private boolean favorite;
	/**
	 * 是否交易时间段
	 */
	private boolean isTradeTime;

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public boolean getIsTradeTime() {
		return isTradeTime;
	}

	public void setIsTradeTime(boolean isTradeTime) {
		this.isTradeTime = isTradeTime;
	}

}
