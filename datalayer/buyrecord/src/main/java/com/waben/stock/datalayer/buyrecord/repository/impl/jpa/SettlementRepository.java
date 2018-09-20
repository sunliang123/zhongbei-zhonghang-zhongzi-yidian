package com.waben.stock.datalayer.buyrecord.repository.impl.jpa;

import com.waben.stock.datalayer.buyrecord.entity.Settlement;
import org.springframework.data.jpa.repository.Query;

/**
 * 结算 Jpa
 * 
 * @author luomengan
 *
 */
public interface SettlementRepository extends CustomJpaRepository<Settlement, Long> {
    Settlement findByBuyRecordId(Long id);
}
