package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.OrganizationPublisher;

/**
 * 机构推广的发布人 Repository
 * 
 * @author luomengan
 *
 */
public interface OrganizationPublisherRepository extends Repository<OrganizationPublisher, Long> {

	OrganizationPublisher save(OrganizationPublisher organizationPublisher);

	void delete(Long id);

	Page<OrganizationPublisher> findAll(Pageable pageable);
	
	List<OrganizationPublisher> findAll();

	OrganizationPublisher findById(Long id);

	OrganizationPublisher findByDomainAndDataId(String domain, Long dataId);
	
}
