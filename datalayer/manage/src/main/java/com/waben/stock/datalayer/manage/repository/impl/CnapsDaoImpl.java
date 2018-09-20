package com.waben.stock.datalayer.manage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.manage.entity.Cnaps;
import com.waben.stock.datalayer.manage.repository.CnapsDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.CnapsRepository;

/**
 * Cnaps Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class CnapsDaoImpl implements CnapsDao {

	@Autowired
	private CnapsRepository repository;

	@Override
	public Cnaps create(Cnaps t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Cnaps update(Cnaps t) {
		return repository.save(t);
	}

	@Override
	public Cnaps retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<Cnaps> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<Cnaps> page(Specification<Cnaps> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<Cnaps> list() {
		return repository.findAll();
	}

	@Override
	public List<Cnaps> retrieveByCityCodeAndClsCode(String cityCode, String clsCode) {
		return repository.findByCityCodeAndClsCode(cityCode, clsCode);
	}

}
