package com.waben.stock.datalayer.organization.repository;

import java.util.List;

import com.waben.stock.datalayer.organization.entity.OrganizationAccountFlow;
import com.waben.stock.interfaces.enums.OrganizationAccountFlowType;
import com.waben.stock.interfaces.enums.ResourceType;

/**
 * 机构账户流水 Dao
 * 
 * @author luomengan
 *
 */
public interface OrganizationAccountFlowDao extends BaseDao<OrganizationAccountFlow, Long> {

	List<OrganizationAccountFlow> retrieveByTypeAndResourceTypeAndResourceId(OrganizationAccountFlowType flowType,
			ResourceType resourceType, Long resourceId);

}
