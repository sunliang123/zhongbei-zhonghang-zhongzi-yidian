package com.waben.stock.datalayer.publisher.repository.impl.jpa;

import java.util.List;

import com.waben.stock.datalayer.publisher.entity.RealName;
import com.waben.stock.interfaces.enums.ResourceType;

/**
 * 实名认证 Jpa
 * 
 * @author luomengan
 *
 */
public interface RealNameRepository extends CustomJpaRepository<RealName, Long> {

	RealName findByResourceTypeAndResourceId(ResourceType resourceType, Long resourceId);

	List<RealName> findByNameAndIdCard(String name, String idCard);

}
