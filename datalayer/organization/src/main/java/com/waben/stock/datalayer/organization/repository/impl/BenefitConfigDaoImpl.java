package com.waben.stock.datalayer.organization.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.organization.entity.BenefitConfig;
import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.datalayer.organization.repository.BenefitConfigDao;
import com.waben.stock.datalayer.organization.repository.impl.jpa.BenefitConfigRepository;
import com.waben.stock.interfaces.enums.BenefitConfigType;

/**
 * 分成配置 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BenefitConfigDaoImpl implements BenefitConfigDao {

	@Autowired
	private BenefitConfigRepository repository;

	@Override
	public BenefitConfig create(BenefitConfig t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public BenefitConfig update(BenefitConfig t) {
		return repository.save(t);
	}

	@Override
	public BenefitConfig retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<BenefitConfig> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<BenefitConfig> page(Specification<BenefitConfig> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<BenefitConfig> list() {
		return repository.findAll();
	}

	@Override
	public List<BenefitConfig> retrieveByOrgAndResourceType(Organization org, Integer resourceType) {
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "resourceId"));
		return repository.findByOrgAndResourceType(org, resourceType, sort);
	}

	@Override
	public List<BenefitConfig> retrieveByOrgAndTypeAndResourceTypeAndResourceId(Organization org,
			BenefitConfigType type, Integer resourceType, Long resourceId) {
		return repository.findByOrgAndTypeAndResourceTypeAndResourceId(org, type, resourceType, resourceId);
	}

}
