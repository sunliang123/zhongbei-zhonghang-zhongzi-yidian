package com.waben.stock.datalayer.organization.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.datalayer.organization.entity.PriceMarkupConfig;
import com.waben.stock.datalayer.organization.repository.PriceMarkupConfigDao;
import com.waben.stock.datalayer.organization.repository.impl.jpa.PriceMarkupConfigRepository;

/**
 * 加价配置 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class PriceMarkupConfigDaoImpl implements PriceMarkupConfigDao {

	@Autowired
	private PriceMarkupConfigRepository priceMarkupConfigRepository;

	@Override
	public PriceMarkupConfig createOptionPriceConfig(PriceMarkupConfig priceMarkupConfig) {
		return priceMarkupConfigRepository.save(priceMarkupConfig);
	}

	@Override
	public void deleteOptionPriceConfigById(Long id) {
		priceMarkupConfigRepository.delete(id);
	}

	@Override
	public PriceMarkupConfig updateOptionPriceConfig(PriceMarkupConfig priceMarkupConfig) {
		return priceMarkupConfigRepository.save(priceMarkupConfig);
	}

	@Override
	public PriceMarkupConfig retrieveOptionPriceConfigById(Long id) {
		return priceMarkupConfigRepository.findById(id);
	}

	@Override
	public Page<PriceMarkupConfig> pageOptionPriceConfig(int page, int limit) {
		return priceMarkupConfigRepository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<PriceMarkupConfig> listOptionPriceConfig() {
		return priceMarkupConfigRepository.findAll();
	}

	@Override
	public List<PriceMarkupConfig> retrieveByOrgAndResourceType(Organization org, Integer resourceType) {
		return priceMarkupConfigRepository.findByOrgAndResourceType(org, resourceType);
	}

	@Override
	public List<PriceMarkupConfig> retrieveByOrgAndResourceTypeAndResourceId(Organization org, Integer resourceType,
			Long resourceId) {
		return priceMarkupConfigRepository.findByOrgAndResourceTypeAndResourceId(org, resourceType, resourceId);
	}

}
