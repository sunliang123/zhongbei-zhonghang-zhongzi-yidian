package com.waben.stock.datalayer.organization.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.datalayer.organization.business.StockOptionCycleBusiness;
import com.waben.stock.datalayer.organization.business.StrategyTypeBusiness;
import com.waben.stock.datalayer.organization.entity.BenefitConfig;
import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.datalayer.organization.repository.BenefitConfigDao;
import com.waben.stock.datalayer.organization.repository.OrganizationDao;
import com.waben.stock.interfaces.dto.organization.BenefitConfigDto;
import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionCycleDto;
import com.waben.stock.interfaces.enums.BenefitConfigType;
import com.waben.stock.interfaces.pojo.form.organization.BenefitConfigForm;

/**
 * 分成配置 Service
 * 
 * @author luomengan
 *
 */
@Service
public class BenefitConfigService {

	@Autowired
	private BenefitConfigDao benefitConfigDao;

	@Autowired
	private OrganizationDao orgDao;

	@Autowired
	private StrategyTypeBusiness strategyTypeBusiness;

	@Autowired
	private StockOptionCycleBusiness stockOptionCycleBusiness;

	public BenefitConfig getBenefitConfigInfo(Long id) {
		return benefitConfigDao.retrieve(id);
	}

	@Transactional
	public BenefitConfig addBenefitConfig(BenefitConfig benefitConfig) {
		return benefitConfigDao.create(benefitConfig);
	}

	@Transactional
	public BenefitConfig modifyBenefitConfig(BenefitConfig benefitConfig) {
		return benefitConfigDao.update(benefitConfig);
	}

	@Transactional
	public void deleteBenefitConfig(Long id) {
		benefitConfigDao.delete(id);
	}

	@Transactional
	public void deleteBenefitConfigs(String ids) {
		if (ids != null) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!"".equals(id.trim())) {
					benefitConfigDao.delete(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<BenefitConfig> benefitConfigs(int page, int limit) {
		return benefitConfigDao.page(page, limit);
	}

	public List<BenefitConfig> list() {
		return benefitConfigDao.list();
	}

	public List<BenefitConfigDto> benefitConfigList(Long orgId, Integer resourceType) {
		List<BenefitConfigDto> result = new ArrayList<>();
		Organization org = orgDao.retrieve(orgId);
		if (resourceType == 1) {
			List<StrategyTypeDto> strategyTypeList = strategyTypeBusiness.lists();
			if (strategyTypeList != null && strategyTypeList.size() > 0) {
				List<BenefitConfig> configList = benefitConfigDao.retrieveByOrgAndResourceType(org, resourceType);
				for (StrategyTypeDto strategyType : strategyTypeList) {
					BenefitConfigDto configBean = new BenefitConfigDto();
					configBean.setOrgCode(org.getCode());
					configBean.setOrgId(org.getId());
					configBean.setOrgName(org.getName());
					configBean.setResourceId(strategyType.getId());
					configBean.setResourceName(strategyType.getName());
					configBean.setResourceType(1);
					// 设置分成比例
					if (configList != null && configList.size() > 0) {
						for (BenefitConfig config : configList) {
							BenefitConfigType type = config.getType();
							if (config.getResourceId().longValue() == strategyType.getId().longValue()) {
								if (type == BenefitConfigType.ServiceFee) {
									configBean.setServiceFeeRatio(config.getRatio());
								} else if (type == BenefitConfigType.DeferredFee) {
									configBean.setDeferredFeeRatio(config.getRatio());
								}
							}
						}
					}
					result.add(configBean);
				}
			}
		} else {
			List<StockOptionCycleDto> stockOptionCycelList = stockOptionCycleBusiness.lists();
			if (stockOptionCycelList != null && stockOptionCycelList.size() > 0) {
				List<BenefitConfig> configList = benefitConfigDao.retrieveByOrgAndResourceType(org, Integer.valueOf(2));
				for (StockOptionCycleDto stockOptionCyce : stockOptionCycelList) {
					BenefitConfigDto configBean = new BenefitConfigDto();
					configBean.setOrgCode(org.getCode());
					configBean.setOrgId(org.getId());
					configBean.setOrgName(org.getName());
					configBean.setResourceId(stockOptionCyce.getId());
					configBean.setResourceName(stockOptionCyce.getName());
					configBean.setResourceType(2);
					// 设置分成比例
					if (configList != null && configList.size() > 0) {
						for (BenefitConfig config : configList) {
							BenefitConfigType type = config.getType();
							if (config.getResourceId().longValue() == stockOptionCyce.getId().longValue()) {
								if (type == BenefitConfigType.RightMoney) {
									configBean.setRightMoneyRatio(config.getRatio());
								}
							}
						}
					}
					/*if (configBean.getRightMoneyRatio() == null) {
						configBean.setRightMoneyRatio(BigDecimal.ZERO);
					}*/
					result.add(configBean);
				}
			}
		}
		return result;
	}

	public void strategyBenefitConfig(List<BenefitConfigForm> configFormList) {
		Organization org = null;
		if (configFormList != null && configFormList.size() > 0) {
			org = orgDao.retrieve(configFormList.get(0).getOrgId());
		}
		for (BenefitConfigForm configForm : configFormList) {
			// 保存信息服务费分成比例
			List<BenefitConfig> serviceFeeList = benefitConfigDao.retrieveByOrgAndTypeAndResourceTypeAndResourceId(org,
					BenefitConfigType.ServiceFee, Integer.valueOf(1), configForm.getResourceId());
			if (serviceFeeList != null && serviceFeeList.size() > 0) {
				BenefitConfig config = serviceFeeList.get(0);
				config.setRatio(configForm.getServiceFeeRatio());
				benefitConfigDao.update(config);
				for (int i = 1; i < serviceFeeList.size(); i++) {
					benefitConfigDao.delete(serviceFeeList.get(i).getId());
				}
			} else {
				BenefitConfig config = new BenefitConfig();
				config.setOrg(org);
				config.setRatio(configForm.getServiceFeeRatio());
				config.setResourceId(configForm.getResourceId());
				config.setResourceType(1);
				config.setType(BenefitConfigType.ServiceFee);
				benefitConfigDao.create(config);
			}
			// 保存递延费分成比例
			List<BenefitConfig> deferredFeeList = benefitConfigDao.retrieveByOrgAndTypeAndResourceTypeAndResourceId(org,
					BenefitConfigType.DeferredFee, Integer.valueOf(1), configForm.getResourceId());
			if (deferredFeeList != null && deferredFeeList.size() > 0) {
				BenefitConfig config = deferredFeeList.get(0);
				config.setRatio(configForm.getDeferredFeeRatio());
				benefitConfigDao.update(config);
				for (int i = 1; i < deferredFeeList.size(); i++) {
					benefitConfigDao.delete(deferredFeeList.get(i).getId());
				}
			} else {
				BenefitConfig config = new BenefitConfig();
				config.setOrg(org);
				config.setRatio(configForm.getDeferredFeeRatio());
				config.setResourceId(configForm.getResourceId());
				config.setResourceType(1);
				config.setType(BenefitConfigType.DeferredFee);
				benefitConfigDao.create(config);
			}
		}
	}

	public void stockoptionBenefitConfig(List<BenefitConfigForm> configFormList) {
		Organization org = null;
		if (configFormList != null && configFormList.size() > 0) {
			org = orgDao.retrieve(configFormList.get(0).getOrgId());
		}
		for (BenefitConfigForm configForm : configFormList) {
			// 保存权利金分成比例
			List<BenefitConfig> serviceFeeList = benefitConfigDao.retrieveByOrgAndTypeAndResourceTypeAndResourceId(org,
					BenefitConfigType.RightMoney, Integer.valueOf(2), configForm.getResourceId());
			if (serviceFeeList != null && serviceFeeList.size() > 0) {
				BenefitConfig config = serviceFeeList.get(0);
				config.setRatio(configForm.getRightMoneyRatio());
				benefitConfigDao.update(config);
				for (int i = 1; i < serviceFeeList.size(); i++) {
					benefitConfigDao.delete(serviceFeeList.get(i).getId());
				}
			} else {
				BenefitConfig config = new BenefitConfig();
				config.setOrg(org);
				config.setRatio(configForm.getRightMoneyRatio());
				config.setResourceId(configForm.getResourceId());
				config.setResourceType(2);
				config.setType(BenefitConfigType.RightMoney);
				benefitConfigDao.create(config);
			}
		}
	}

}
