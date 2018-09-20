package com.waben.stock.datalayer.publisher.repository;

import java.util.List;

import com.waben.stock.datalayer.publisher.entity.RealName;
import com.waben.stock.interfaces.enums.ResourceType;

/**
 * 实名认证 Dao
 * 
 * @author luomengan
 *
 */
public interface RealNameDao extends BaseDao<RealName, Long> {

	RealName retriveByResourceTypeAndResourceId(ResourceType resourceType, Long resourceId);

	List<RealName> retrieveByNameAndIdCard(String name, String idCard);

}
