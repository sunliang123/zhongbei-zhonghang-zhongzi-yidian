package com.waben.stock.datalayer.stockoption.repository;

import com.waben.stock.datalayer.stockoption.entity.OfflineStockOptionTrade;
import com.waben.stock.interfaces.enums.OfflineStockOptionTradeState;

import java.util.Date;
import java.util.List;


public interface OfflineStockOptionTradeDao extends BaseDao<OfflineStockOptionTrade, Long>  {
    List<OfflineStockOptionTrade> retrieveByStateAndSellingTimeBetween(OfflineStockOptionTradeState state, Date start,Date end);
}
