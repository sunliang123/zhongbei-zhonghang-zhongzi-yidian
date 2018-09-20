package com.waben.stock.datalayer.stockoption.repository.impl.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.waben.stock.datalayer.stockoption.entity.StockOptionTrade;
import com.waben.stock.interfaces.enums.StockOptionTradeState;

public interface StockOptionTradeRepository extends CustomJpaRepository<StockOptionTrade, Long> {

	List<StockOptionTrade> findByState(StockOptionTradeState stockOptionTradeState);

	@Query(value = "select count(s.id) as counts from stock_option_trade s where s.state in( 1,3,4,5,7 ) and s.publisher_id = ?1", nativeQuery = true)
	Integer countStockOptionTradeState(Long publisherId);
}
