package com.waben.stock.datalayer.publisher.repository.impl.jpa;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.waben.stock.datalayer.publisher.entity.CapitalFlow;
import com.waben.stock.datalayer.publisher.entity.Publisher;
import com.waben.stock.interfaces.enums.CapitalFlowType;

/**
 * 资金流水 Jpa
 * 
 * @author luomengan
 *
 */
public interface CapitalFlowRepository extends CustomJpaRepository<CapitalFlow, Long> {

	List<CapitalFlow> findByPublisherAndType(Publisher publisher, CapitalFlowType type);

	@Query("select sum(amount) from CapitalFlow where publisherId=?1 and type=?2")
	BigDecimal promotionTotalAmount(Long publisherId, CapitalFlowType type);

}
