package com.waben.stock.datalayer.publisher.repository.impl.jpa;

import java.util.List;

import com.waben.stock.datalayer.publisher.entity.BindCard;
import com.waben.stock.interfaces.enums.BindCardResourceType;

/**
 * 绑卡 Jpa
 * 
 * @author luomengan
 *
 */
public interface BindCardRepository extends CustomJpaRepository<BindCard, Long> {

	List<BindCard> findByResourceTypeAndResourceId(BindCardResourceType resourceType, Long resourceId);

	BindCard findByResourceTypeAndResourceIdAndBankCard(BindCardResourceType resourceType, Long resourceId,
			String bankCard);

	List<BindCard> findByBankCardAndResourceType(String bankCard, BindCardResourceType resourceType);

	BindCard findBindCardByNameAndResourceType(String name, BindCardResourceType resourceType);
}
