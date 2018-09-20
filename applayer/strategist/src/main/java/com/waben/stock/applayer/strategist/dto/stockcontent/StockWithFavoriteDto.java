package com.waben.stock.applayer.strategist.dto.stockcontent;

import com.waben.stock.interfaces.dto.stockcontent.StockDto;

/**
 * 股票
 *
 * @author luomengan
 */
public class StockWithFavoriteDto extends StockDto {

	private boolean favorite;

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

}
