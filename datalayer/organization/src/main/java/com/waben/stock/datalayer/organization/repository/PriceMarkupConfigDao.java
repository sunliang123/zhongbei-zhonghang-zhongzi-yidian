package com.waben.stock.datalayer.organization.repository;

import java.util.List;

import org.springframework.data.domain.Page;

import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.datalayer.organization.entity.PriceMarkupConfig;

/**
 * 加价配置 Dao
 * 
 * @author luomengan
 *
 */
public interface PriceMarkupConfigDao {

	public PriceMarkupConfig createOptionPriceConfig(PriceMarkupConfig priceMarkupConfig);

	public void deleteOptionPriceConfigById(Long id);

	public PriceMarkupConfig updateOptionPriceConfig(PriceMarkupConfig priceMarkupConfig);

	public PriceMarkupConfig retrieveOptionPriceConfigById(Long id);

	public Page<PriceMarkupConfig> pageOptionPriceConfig(int page, int limit);

	public List<PriceMarkupConfig> listOptionPriceConfig();

	public List<PriceMarkupConfig> retrieveByOrgAndResourceType(Organization org, Integer resourceType);

	public List<PriceMarkupConfig> retrieveByOrgAndResourceTypeAndResourceId(Organization org, Integer resourceType,
			Long resourceId);

}
