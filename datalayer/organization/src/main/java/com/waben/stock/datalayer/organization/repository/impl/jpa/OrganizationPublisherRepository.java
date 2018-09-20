package com.waben.stock.datalayer.organization.repository.impl.jpa;

import com.waben.stock.datalayer.organization.entity.OrganizationPublisher;

import java.util.List;

/**
 * 机构推广的发布人 Jpa
 * 
 * @author luomengan
 *
 */
public interface OrganizationPublisherRepository extends CustomJpaRepository<OrganizationPublisher, Long> {

	OrganizationPublisher findByPublisherId(Long publisherId);

	List<OrganizationPublisher> findOrganizationPublishersByOrgCode(String code);
}
