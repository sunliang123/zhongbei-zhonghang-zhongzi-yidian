package com.waben.stock.datalayer.organization.repository.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.waben.stock.datalayer.organization.entity.BenefitConfig;
import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.interfaces.enums.BenefitConfigType;

/**
 * 分成配置 Jpa
 * 
 * @author luomengan
 *
 */
public interface BenefitConfigRepository extends CustomJpaRepository<BenefitConfig, Long> {

	List<BenefitConfig> findByOrgAndResourceType(Organization org, Integer resourceType, Sort sort);

	List<BenefitConfig> findByOrgAndTypeAndResourceTypeAndResourceId(Organization org, BenefitConfigType type,
			Integer resourceType, Long resourceId);

}
