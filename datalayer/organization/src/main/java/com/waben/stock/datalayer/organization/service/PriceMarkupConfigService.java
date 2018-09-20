package com.waben.stock.datalayer.organization.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.datalayer.organization.business.StockOptionCycleBusiness;
import com.waben.stock.datalayer.organization.business.StrategyTypeBusiness;
import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.datalayer.organization.entity.OrganizationPublisher;
import com.waben.stock.datalayer.organization.entity.PriceMarkupConfig;
import com.waben.stock.datalayer.organization.repository.OrganizationDao;
import com.waben.stock.datalayer.organization.repository.OrganizationPublisherDao;
import com.waben.stock.datalayer.organization.repository.PriceMarkupConfigDao;
import com.waben.stock.interfaces.dto.organization.PriceMarkupConfigDto;
import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionCycleDto;
import com.waben.stock.interfaces.pojo.form.organization.PriceMarkupForm;

/**
 * 加价配置 Service
 * 
 * @author luomengan
 *
 */
@Service
public class PriceMarkupConfigService {

	@Autowired
	private PriceMarkupConfigDao priceMarkupConfigDao;

	@Autowired
	private OrganizationDao orgDao;

	@Autowired
	private StrategyTypeBusiness strategyTypeBusiness;

	@Autowired
	private StockOptionCycleBusiness stockOptionCycleBusiness;

	@Autowired
	private OrganizationPublisherDao orgPublisherDao;

	public PriceMarkupConfig getOptionPriceConfigInfo(Long id) {
		return priceMarkupConfigDao.retrieveOptionPriceConfigById(id);
	}

	@Transactional
	public PriceMarkupConfig addOptionPriceConfig(PriceMarkupConfig priceMarkupConfig) {
		return priceMarkupConfigDao.createOptionPriceConfig(priceMarkupConfig);
	}

	@Transactional
	public PriceMarkupConfig modifyOptionPriceConfig(PriceMarkupConfig priceMarkupConfig) {
		return priceMarkupConfigDao.updateOptionPriceConfig(priceMarkupConfig);
	}

	@Transactional
	public void deleteOptionPriceConfig(Long id) {
		priceMarkupConfigDao.deleteOptionPriceConfigById(id);
	}

	@Transactional
	public void deleteOptionPriceConfigs(String ids) {
		if (ids != null) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!"".equals(id.trim())) {
					priceMarkupConfigDao.deleteOptionPriceConfigById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<PriceMarkupConfig> priceMarkupConfigs(int page, int limit) {
		return priceMarkupConfigDao.pageOptionPriceConfig(page, limit);
	}

	public List<PriceMarkupConfig> list() {
		return priceMarkupConfigDao.listOptionPriceConfig();
	}

	public List<PriceMarkupConfigDto> priceMarkupConfigList(Long orgId, Integer resourceType) {
		List<PriceMarkupConfigDto> result = new ArrayList<>();
		Organization org = orgDao.retrieve(orgId);
		if (resourceType == 1) {
			List<StrategyTypeDto> strategyTypeList = strategyTypeBusiness.lists();
			if (strategyTypeList != null && strategyTypeList.size() > 0) {
				List<PriceMarkupConfig> configList = priceMarkupConfigDao.retrieveByOrgAndResourceType(org,
						resourceType);
				for (StrategyTypeDto strategyType : strategyTypeList) {
					PriceMarkupConfigDto configBean = new PriceMarkupConfigDto();
					configBean.setOrgCode(org.getCode());
					configBean.setOrgId(org.getId());
					configBean.setOrgName(org.getName());
					configBean.setResourceId(strategyType.getId());
					configBean.setResourceName(strategyType.getName());
					configBean.setResourceType(1);
					// 设置分成比例
					if (configList != null && configList.size() > 0) {
						for (PriceMarkupConfig config : configList) {
							if (config.getResourceId().longValue() == strategyType.getId().longValue()) {
								configBean.setRatio(config.getRatio());
							}
						}
					}
					if (configBean.getRatio() == null) {
						configBean.setRatio(BigDecimal.ZERO);
					}
					result.add(configBean);
				}
			}
		} else {
			List<StockOptionCycleDto> stockOptionCycelList = stockOptionCycleBusiness.lists();
			if (stockOptionCycelList != null && stockOptionCycelList.size() > 0) {
				List<PriceMarkupConfig> configList = priceMarkupConfigDao.retrieveByOrgAndResourceType(org,
						Integer.valueOf(2));
				for (StockOptionCycleDto stockOptionCyce : stockOptionCycelList) {
					PriceMarkupConfigDto configBean = new PriceMarkupConfigDto();
					configBean.setOrgCode(org.getCode());
					configBean.setOrgId(org.getId());
					configBean.setOrgName(org.getName());
					configBean.setResourceId(stockOptionCyce.getId());
					configBean.setResourceName(stockOptionCyce.getName());
					configBean.setResourceType(2);
					// 设置分成比例
					if (configList != null && configList.size() > 0) {
						for (PriceMarkupConfig config : configList) {
							if (config.getResourceId().longValue() == stockOptionCyce.getId().longValue()) {
								configBean.setRatio(config.getRatio());
							}
						}
					}
					if (configBean.getRatio() == null) {
						configBean.setRatio(BigDecimal.ZERO);
					}
					result.add(configBean);
				}
			}
		}
		return result;
	}

	public void priceMarkupConfig(List<PriceMarkupForm> configFormList) {
		Organization org = null;
		if (configFormList != null && configFormList.size() > 0) {
			org = orgDao.retrieve(configFormList.get(0).getOrgId());
		}
		for (PriceMarkupForm configForm : configFormList) {
			List<PriceMarkupConfig> configList = priceMarkupConfigDao.retrieveByOrgAndResourceTypeAndResourceId(org,
					Integer.valueOf(2), configForm.getResourceId());
			if (configList != null && configList.size() > 0) {
				PriceMarkupConfig config = configList.get(0);
				config.setRatio(configForm.getRatio());
				priceMarkupConfigDao.updateOptionPriceConfig(config);
				for (int i = 1; i < configList.size(); i++) {
					priceMarkupConfigDao.deleteOptionPriceConfigById(configList.get(i).getId());
				}
			} else {
				PriceMarkupConfig config = new PriceMarkupConfig();
				config.setOrg(org);
				config.setRatio(configForm.getRatio());
				config.setResourceId(configForm.getResourceId());
				config.setResourceType(2);
				priceMarkupConfigDao.createOptionPriceConfig(config);
			}
		}
	}

	public List<BigDecimal> priceMarkupRatioList(Integer resourceType, Long resourceId, Long publisherId) {
		List<BigDecimal> result = new ArrayList<>();
		BigDecimal topRatio = getTopLevelPriceMarkupRatio(resourceType, resourceId);
		if (topRatio == null || topRatio.compareTo(BigDecimal.ZERO) <= 0) {
			return result;
		}
		// List<Organization> orgTree = getPublisherOrgTreeList(publisherId);
		// if (orgTree != null && orgTree.size() > 0) {
		// for (Organization org : orgTree) {
		// List<PriceMarkupConfig> configList =
		// priceMarkupConfigDao.retrieveByOrgAndResourceTypeAndResourceId(org,
		// resourceType, resourceId);
		// if (configList != null && configList.size() > 0) {
		// if (configList.get(0).getRatio() != null &&
		// topRatio.compareTo(BigDecimal.ZERO) > 0) {
		// result.add(configList.get(0).getRatio());
		// }
		// }
		// }
		// } else {
		// // 使用一级机构的加价比例
		// result.add(topRatio);
		// }

		// 获取该用户关联的代理商，如果该代理商设置了加价比例，则使用该代理商的，没有则使用一级代理商的
		boolean isUseTopRatio = true;
		OrganizationPublisher orgPublisher = orgPublisherDao.retrieveByPublisherId(publisherId);
		if (orgPublisher != null) {
			Organization org = orgDao.retrieveByCode(orgPublisher.getOrgCode());
			List<PriceMarkupConfig> configList = priceMarkupConfigDao.retrieveByOrgAndResourceTypeAndResourceId(org,
					resourceType, resourceId);
			if (configList != null && configList.size() > 0 && configList.get(0).getRatio() != null
					&& configList.get(0).getRatio().compareTo(BigDecimal.ZERO) > 0) {
				isUseTopRatio = false;
				result.add(configList.get(0).getRatio());
			}
		}
		// 使用一级机构的加价比例
		if (isUseTopRatio) {
			result.add(topRatio);
		}
		return result;
	}

	private List<Organization> getPublisherOrgTreeList(Long publisherId) {
		OrganizationPublisher orgPublisher = orgPublisherDao.retrieveByPublisherId(publisherId);
		if (orgPublisher != null) {
			Organization org = orgDao.retrieveByCode(orgPublisher.getOrgCode());
			if (org != null) {
				List<Organization> orgList = new ArrayList<>();
				orgList.add(org);
				while (org.getParent() != null) {
					orgList.add(org.getParent());
					org = org.getParent();
				}
				if (orgList.size() > 0 && orgList.get(orgList.size() - 1).getLevel() == 1) {
					Collections.reverse(orgList);
					return orgList;
				}
			}
		}
		return null;
	}

	/**
	 * 获取一级机构的加价比例
	 */
	private BigDecimal getTopLevelPriceMarkupRatio(Integer resourceType, Long resourceId) {
		List<Organization> topOrg = orgDao.listByLevel(Integer.valueOf(1));
		if (topOrg != null && topOrg.size() > 0) {
			List<PriceMarkupConfig> configList = priceMarkupConfigDao
					.retrieveByOrgAndResourceTypeAndResourceId(topOrg.get(0), resourceType, resourceId);
			if (configList != null && configList.size() > 0) {
				return configList.get(0).getRatio();
			}
		}
		return null;
	}

}
