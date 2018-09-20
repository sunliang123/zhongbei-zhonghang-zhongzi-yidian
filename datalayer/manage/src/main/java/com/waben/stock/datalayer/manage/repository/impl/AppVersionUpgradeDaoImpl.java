package com.waben.stock.datalayer.manage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.manage.entity.AppVersionUpgrade;
import com.waben.stock.datalayer.manage.repository.AppVersionUpgradeDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.AppVersionUpgradeRepository;

/**
 * app版本升级 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class AppVersionUpgradeDaoImpl implements AppVersionUpgradeDao {

	@Autowired
	private AppVersionUpgradeRepository repository;

	@Override
	public AppVersionUpgrade create(AppVersionUpgrade t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public AppVersionUpgrade update(AppVersionUpgrade t) {
		return repository.save(t);
	}

	@Override
	public AppVersionUpgrade retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<AppVersionUpgrade> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<AppVersionUpgrade> page(Specification<AppVersionUpgrade> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<AppVersionUpgrade> list() {
		return repository.findAll();
	}

	@Override
	public AppVersionUpgrade getGreaterThanCurrentVersion(Integer versionCode, Integer deviceType, Integer shellIndex) {
		return repository.findByIsCurrentVersionAndDeviceTypeAndShellIndexAndVersionCodeGreaterThan(true, deviceType, shellIndex, versionCode);
	}

}
