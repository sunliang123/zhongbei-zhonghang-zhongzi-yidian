package com.waben.stock.datalayer.organization.repository;

import java.util.List;

import com.waben.stock.datalayer.organization.entity.BenefitConfig;
import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.interfaces.enums.BenefitConfigType;

/**
 * 分成配置 Dao
 * 
 * @author luomengan
 *
 */
public interface BenefitConfigDao extends BaseDao<BenefitConfig, Long> {

	List<BenefitConfig> retrieveByOrgAndResourceType(Organization org, Integer resourceType);

	List<BenefitConfig> retrieveByOrgAndTypeAndResourceTypeAndResourceId(Organization org, BenefitConfigType type,
			Integer resourceType, Long resourceId);

}
