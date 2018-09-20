package com.waben.stock.datalayer.organization.repository;

import com.waben.stock.datalayer.organization.entity.OrganizationPublisher;

import java.util.List;

/**
 * 机构推广的发布人 Dao
 * 
 * @author luomengan
 *
 */
public interface OrganizationPublisherDao extends BaseDao<OrganizationPublisher, Long> {

	OrganizationPublisher retrieveByPublisherId(Long publisherId);

	List<OrganizationPublisher> retrieveOrganizationPublishersByOrgCode(String code);

}
