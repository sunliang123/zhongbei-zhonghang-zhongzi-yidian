package com.waben.stock.collector.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.collector.entity.OrganizationPublisher;

/**
 * 机构推广的发布人 Dao
 * 
 * @author luomengan
 *
 */
public interface OrganizationPublisherDao {

	public OrganizationPublisher createOrganizationPublisher(OrganizationPublisher organizationPublisher);

	public void deleteOrganizationPublisherById(Long id);

	public OrganizationPublisher updateOrganizationPublisher(OrganizationPublisher organizationPublisher);

	public OrganizationPublisher retrieveOrganizationPublisherById(Long id);

	public Page<OrganizationPublisher> pageOrganizationPublisher(int page, int limit);
	
	public List<OrganizationPublisher> listOrganizationPublisher();
	
	public OrganizationPublisher getByDomainAndDataId(String domain, Long dataId);

}
