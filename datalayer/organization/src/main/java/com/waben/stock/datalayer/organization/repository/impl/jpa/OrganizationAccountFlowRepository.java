package com.waben.stock.datalayer.organization.repository.impl.jpa;

import java.util.List;

import com.waben.stock.datalayer.organization.entity.OrganizationAccountFlow;
import com.waben.stock.interfaces.enums.OrganizationAccountFlowType;
import com.waben.stock.interfaces.enums.ResourceType;

/**
 * 机构账户流水 Jpa
 * 
 * @author luomengan
 *
 */
public interface OrganizationAccountFlowRepository extends CustomJpaRepository<OrganizationAccountFlow, Long> {

	List<OrganizationAccountFlow> findByTypeAndResourceTypeAndResourceId(OrganizationAccountFlowType flowType,
			ResourceType resourceType, Long resourceId);

}
