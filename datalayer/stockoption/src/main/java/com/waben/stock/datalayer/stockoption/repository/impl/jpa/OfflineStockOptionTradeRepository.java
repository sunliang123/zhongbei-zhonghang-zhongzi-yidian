package com.waben.stock.datalayer.stockoption.repository.impl.jpa;

import com.waben.stock.datalayer.stockoption.entity.OfflineStockOptionTrade;
import com.waben.stock.interfaces.enums.OfflineStockOptionTradeState;

import java.util.Date;
import java.util.List;


public interface OfflineStockOptionTradeRepository extends CustomJpaRepository<OfflineStockOptionTrade,Long>{

    List<OfflineStockOptionTrade> findOfflineStockOptionTradesByStateAndSellingTimeBetween(OfflineStockOptionTradeState state, Date start,Date end);

}
