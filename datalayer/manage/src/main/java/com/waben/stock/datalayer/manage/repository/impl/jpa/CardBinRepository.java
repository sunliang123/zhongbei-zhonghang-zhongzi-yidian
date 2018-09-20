package com.waben.stock.datalayer.manage.repository.impl.jpa;

import org.springframework.data.jpa.repository.Query;

import com.waben.stock.datalayer.manage.entity.CardBin;

/**
 * 银行卡片 Jpa
 * 
 * @author luomengan
 *
 */
public interface CardBinRepository extends CustomJpaRepository<CardBin, Long> {

	@Query(value = "select c.* from card_bin c where c.card_length = length(?1) AND c.verify_code = substr(?2, 1, c.verify_length)", nativeQuery = true)
	CardBin findByBankCard(String bankCard, String bankCardRepeat);

}
