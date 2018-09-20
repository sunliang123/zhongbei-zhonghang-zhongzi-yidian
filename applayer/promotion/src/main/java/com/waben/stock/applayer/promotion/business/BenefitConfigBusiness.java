package com.waben.stock.applayer.promotion.business;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.organization.BenefitConfigDto;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.dto.organization.SettlementMethodDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.form.organization.BenefitConfigForm;
import com.waben.stock.interfaces.service.organization.BenefitConfigInterface;

/**
 * 分成配置 Business
 * 
 * @author luomengan
 *
 */
@Service("promotionBenefitConfigBusiness")
public class BenefitConfigBusiness {

	@Autowired
	private BenefitConfigInterface reference;

	@Autowired
	private OrganizationBusiness orgBusiness;

	public List<BenefitConfigDto> benefitConfigList(Long orgId, Integer resourceType) {
		Response<List<BenefitConfigDto>> response = reference.benefitConfigList(orgId, resourceType);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public String strategyBenefitConfig(List<BenefitConfigForm> configFormList) {
		Response<String> response = reference.strategyBenefitConfig(configFormList);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public SettlementMethodDto getSettlement() {
		Response<SettlementMethodDto> resp = reference.getSettlement();
		if ("200".equals(resp.getCode())) {
			return resp.getResult();
		}
		throw new ServiceException(resp.getCode());
	}

	public String stockoptionBenefitConfig(List<BenefitConfigForm> configFormList) {
		SettlementMethodDto method = this.getSettlement();
		if (method.getSettlementType() == 2) {
			for (BenefitConfigForm benefitConfigForm : configFormList) {
				if (benefitConfigForm.getOrgId() != null) {
					OrganizationDto dto = orgBusiness.findByOrgId(benefitConfigForm.getOrgId());
					// 如果是第三级或者更低层级的代理商，需要判断上级的比例不能为空，且当前的表单的比例不能大于上级的比例
					if (dto != null && dto.getLevel() != null && dto.getLevel() >= 3) {
						List<BenefitConfigDto> parentConfigList = this.benefitConfigList(dto.getParentId(), 2);
						for (int i = 0; i < configFormList.size(); i++) {
							BigDecimal formRatio = configFormList.get(i).getRightMoneyRatio();
							BigDecimal parentRatio = parentConfigList.get(i).getRightMoneyRatio();
							if (parentRatio == null) {
								throw new ServiceException(ExceptionConstant.FORM_RATIO_EXCEPITON);
							}
							if (formRatio != null && formRatio.compareTo(parentRatio) > 0) {
								throw new ServiceException(ExceptionConstant.RAKEBACK_RATIO_WRONG_EXCEPTION);
							}
						}
					}
				}
			}
		}

		Response<String> response = reference.stockoptionBenefitConfig(configFormList);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
