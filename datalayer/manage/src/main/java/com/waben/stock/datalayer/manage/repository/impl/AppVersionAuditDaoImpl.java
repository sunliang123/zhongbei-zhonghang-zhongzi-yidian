package com.waben.stock.datalayer.manage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.manage.entity.AppVersionAudit;
import com.waben.stock.datalayer.manage.repository.AppVersionAuditDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.AppVersionAuditRepository;

/**
 * app版本 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class AppVersionAuditDaoImpl implements AppVersionAuditDao {

	@Autowired
	private AppVersionAuditRepository repository;

	@Override
	public AppVersionAudit create(AppVersionAudit t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public AppVersionAudit update(AppVersionAudit t) {
		return repository.save(t);
	}

	@Override
	public AppVersionAudit retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<AppVersionAudit> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<AppVersionAudit> page(Specification<AppVersionAudit> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<AppVersionAudit> list() {
		return repository.findAll();
	}

	@Override
	public AppVersionAudit findByDeviceTypeAndShellIndex(Integer deviceType, Integer shellIndex) {
		return repository.findByDeviceTypeAndShellIndex(deviceType, shellIndex);
	}

}
