package com.waben.stock.datalayer.publisher.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.publisher.entity.RealName;
import com.waben.stock.datalayer.publisher.repository.RealNameDao;
import com.waben.stock.datalayer.publisher.repository.impl.jpa.RealNameRepository;
import com.waben.stock.interfaces.enums.ResourceType;

/**
 * 实名认证 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class RealNameDaoImpl implements RealNameDao {

	@Autowired
	private RealNameRepository repository;

	@Override
	public RealName create(RealName t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public RealName update(RealName t) {
		return repository.save(t);
	}

	@Override
	public RealName retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<RealName> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<RealName> page(Specification<RealName> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<RealName> list() {
		return repository.findAll();
	}

	@Override
	public RealName retriveByResourceTypeAndResourceId(ResourceType resourceType, Long resourceId) {
		return repository.findByResourceTypeAndResourceId(resourceType, resourceId);
	}

	@Override
	public List<RealName> retrieveByNameAndIdCard(String name, String idCard) {
		return repository.findByNameAndIdCard(name, idCard);
	}

}
