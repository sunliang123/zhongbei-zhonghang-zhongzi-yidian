package com.waben.stock.datalayer.manage.repository.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.manage.entity.Circulars;
import com.waben.stock.datalayer.manage.repository.CircularsDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.CircularsRepository;

/**
 * 
 * @author luomengan
 *
 */
@Repository
public class CircularsDaoImpl implements CircularsDao {

	@Autowired
	private CircularsRepository repository;

	@Override
	public List<Circulars> retrieveCirculars(Boolean enable) {
		return repository.findAllByEnable(enable);
	}

	@Override
	public Integer updateById(Long id, String title, String content) {
		return repository.updateById(id,title,content);
	}

	@Override
	public List<Circulars> retrieveCircularsWithInExpireTime(Date date) {
		return repository.findByEnableAndExpireTimeGreaterThan(true,date);
	}

	@Override
	public Circulars create(Circulars t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Circulars update(Circulars t) {
		return repository.save(t);
	}

	@Override
	public Circulars retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<Circulars> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<Circulars> page(Specification<Circulars> specification, Pageable pageable) {
		return repository.findAll(specification,new PageRequest(pageable.getPageNumber(),pageable.getPageSize()));
	}

	@Override
	public List<Circulars> list() {
		return repository.findAll();
	}

}
