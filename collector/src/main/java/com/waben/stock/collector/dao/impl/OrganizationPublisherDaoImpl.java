package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.OrganizationPublisherDao;
import com.waben.stock.collector.dao.impl.jpa.OrganizationPublisherRepository;
import com.waben.stock.collector.entity.OrganizationPublisher;

/**
 * 机构推广的发布人 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class OrganizationPublisherDaoImpl implements OrganizationPublisherDao {

	@Autowired
	private OrganizationPublisherRepository organizationPublisherRepository;

	@Override
	public OrganizationPublisher createOrganizationPublisher(OrganizationPublisher organizationPublisher) {
		return organizationPublisherRepository.save(organizationPublisher);
	}

	@Override
	public void deleteOrganizationPublisherById(Long id) {
		organizationPublisherRepository.delete(id);
	}

	@Override
	public OrganizationPublisher updateOrganizationPublisher(OrganizationPublisher organizationPublisher) {
		return organizationPublisherRepository.save(organizationPublisher);
	}

	@Override
	public OrganizationPublisher retrieveOrganizationPublisherById(Long id) {
		return organizationPublisherRepository.findById(id);
	}

	@Override
	public Page<OrganizationPublisher> pageOrganizationPublisher(int page, int limit) {
		return organizationPublisherRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<OrganizationPublisher> listOrganizationPublisher() {
		return organizationPublisherRepository.findAll();
	}

	@Override
	public OrganizationPublisher getByDomainAndDataId(String domain, Long dataId) {
		return organizationPublisherRepository.findByDomainAndDataId(domain, dataId);
	}

}
