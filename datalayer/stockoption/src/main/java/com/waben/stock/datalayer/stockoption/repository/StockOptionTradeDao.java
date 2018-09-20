package com.waben.stock.datalayer.stockoption.repository;

import java.util.List;

import com.waben.stock.datalayer.stockoption.entity.StockOptionTrade;
import com.waben.stock.interfaces.enums.StockOptionTradeState;

/**

 * @author  zengzhiwei

 * @create  2018/3/3 15:29

 * @desc

 **/
public interface StockOptionTradeDao extends BaseDao<StockOptionTrade, Long> {

    List<StockOptionTrade> retieveByState(StockOptionTradeState stockOptionTradeState);
    
    Integer countStockOptionTradeState(Long publisherId);

}
