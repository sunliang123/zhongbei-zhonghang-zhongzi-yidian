package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.Organization;

/**
 * 机构 Repository
 * 
 * @author luomengan
 *
 */
public interface OrganizationRepository extends Repository<Organization, Long> {

	Organization save(Organization organization);

	void delete(Long id);

	Page<Organization> findAll(Pageable pageable);
	
	List<Organization> findAll();

	Organization findById(Long id);

	Organization findByDomainAndDataId(String domain, Long dataId);
	
}
