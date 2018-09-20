package com.waben.stock.datalayer.organization.repository.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.datalayer.organization.entity.PriceMarkupConfig;

/**
 * 加价配置 Repository
 * 
 * @author luomengan
 *
 */
public interface PriceMarkupConfigRepository extends Repository<PriceMarkupConfig, Long> {

	PriceMarkupConfig save(PriceMarkupConfig priceMarkupConfig);

	void delete(Long id);

	Page<PriceMarkupConfig> findAll(Pageable pageable);
	
	List<PriceMarkupConfig> findAll();

	PriceMarkupConfig findById(Long id);

	List<PriceMarkupConfig> findByOrgAndResourceType(Organization org, Integer resourceType);

	List<PriceMarkupConfig> findByOrgAndResourceTypeAndResourceId(Organization org, Integer resourceType,
			Long resourceId);
	
}
