package com.waben.stock.datalayer.organization.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.organization.entity.OrganizationPublisher;
import com.waben.stock.datalayer.organization.repository.OrganizationPublisherDao;
import com.waben.stock.datalayer.organization.repository.impl.jpa.OrganizationPublisherRepository;

/**
 * 机构推广的发布人 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class OrganizationPublisherDaoImpl implements OrganizationPublisherDao {

	@Autowired
	private OrganizationPublisherRepository repository;

	@Override
	public OrganizationPublisher create(OrganizationPublisher t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public OrganizationPublisher update(OrganizationPublisher t) {
		return repository.save(t);
	}

	@Override
	public OrganizationPublisher retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<OrganizationPublisher> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<OrganizationPublisher> page(Specification<OrganizationPublisher> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<OrganizationPublisher> list() {
		return repository.findAll();
	}

	@Override
	public List<OrganizationPublisher> retrieveOrganizationPublishersByOrgCode(String code) {
		return repository.findOrganizationPublishersByOrgCode(code);
	}

	@Override
	public OrganizationPublisher retrieveByPublisherId(Long publisherId) {
		return repository.findByPublisherId(publisherId);
	}

}
