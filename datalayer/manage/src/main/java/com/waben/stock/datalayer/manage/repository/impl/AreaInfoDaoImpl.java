package com.waben.stock.datalayer.manage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.manage.entity.AreaInfo;
import com.waben.stock.datalayer.manage.repository.AreaInfoDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.AreaInfoRepository;

/**
 * 区域 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class AreaInfoDaoImpl implements AreaInfoDao {

	@Autowired
	private AreaInfoRepository repository;

	@Override
	public AreaInfo create(AreaInfo t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public AreaInfo update(AreaInfo t) {
		return repository.save(t);
	}

	@Override
	public AreaInfo retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<AreaInfo> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<AreaInfo> page(Specification<AreaInfo> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<AreaInfo> list() {
		return repository.findAll();
	}

	@Override
	public List<AreaInfo> retrieveByParentCode(String parentCode) {
		return repository.findByParentCode(parentCode);
	}

}
