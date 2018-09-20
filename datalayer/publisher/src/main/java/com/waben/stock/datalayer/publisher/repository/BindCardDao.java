package com.waben.stock.datalayer.publisher.repository;

import java.util.List;

import com.waben.stock.datalayer.publisher.entity.BindCard;
import com.waben.stock.interfaces.enums.BindCardResourceType;

/**
 * 绑卡 Dao
 * 
 * @author luomengan
 *
 */
public interface BindCardDao extends BaseDao<BindCard, Long> {

	List<BindCard> listByResourceTypeAndResourceId(BindCardResourceType resourceType, Long resourceId);

	BindCard retriveByResourceTypeAndResourceIdAndBankCard(BindCardResourceType resourceType, Long resourceId,
			String bankCard);

	List<BindCard> retrieveByBankCardAndResourceType(String bankCard, BindCardResourceType resourceType);

	BindCard retrieveBindCardByNameAndResourceType(String name, BindCardResourceType resourceType);

}
