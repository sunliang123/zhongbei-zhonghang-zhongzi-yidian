package com.waben.stock.datalayer.message.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.message.entity.OutsidePushConfig;
import com.waben.stock.datalayer.message.repository.OutsidePushConfigDao;
import com.waben.stock.datalayer.message.repository.impl.jpa.OutsidePushConfigRepository;

/**
 * 站外推送配置 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class OutsidePushConfigDaoImpl implements OutsidePushConfigDao {

	@Autowired
	private OutsidePushConfigRepository repository;

	@Override
	public OutsidePushConfig create(OutsidePushConfig t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public OutsidePushConfig update(OutsidePushConfig t) {
		return repository.save(t);
	}

	@Override
	public OutsidePushConfig retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<OutsidePushConfig> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<OutsidePushConfig> page(Specification<OutsidePushConfig> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<OutsidePushConfig> list() {
		return repository.findAll();
	}

	@Override
	public OutsidePushConfig getDefaultConfig() {
		List<OutsidePushConfig> list = repository.findByIsDefault(new Boolean(true));
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public OutsidePushConfig retrieveByDeviceTypeAndShellIndex(Integer deviceType, Integer shellIndex) {
		List<OutsidePushConfig> list = repository.findByDeviceTypeAndShellIndex(deviceType, shellIndex);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
