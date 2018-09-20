package com.waben.stock.datalayer.organization.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.organization.entity.SettlementMethod;
import com.waben.stock.datalayer.organization.repository.SettlementMethodDao;
import com.waben.stock.datalayer.organization.repository.impl.jpa.SettlementMethodRepository;

@Repository
public class SettlementMethodDaoImpl implements SettlementMethodDao {

	@Autowired
	private SettlementMethodRepository repository;

	@Override
	public SettlementMethod create(SettlementMethod t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public SettlementMethod update(SettlementMethod t) {
		return repository.save(t);
	}

	@Override
	public SettlementMethod retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<SettlementMethod> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<SettlementMethod> page(Specification<SettlementMethod> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<SettlementMethod> list() {
		return repository.findAll();
	}

	@Override
	public SettlementMethod getMethodOne() {
		return repository.getMethodOne();
	}

}
