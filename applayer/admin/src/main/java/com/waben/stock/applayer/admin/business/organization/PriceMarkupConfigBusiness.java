package com.waben.stock.applayer.admin.business.organization;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.organization.PriceMarkupConfigDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.form.organization.PriceMarkupForm;
import com.waben.stock.interfaces.service.organization.PriceMarkupConfigInterface;

/**
 * 加价配置 Business
 * 
 * @author luomengan
 *
 */
@Service("adminPriceMarkupConfigBusiness")
public class PriceMarkupConfigBusiness {

	@Autowired
	private PriceMarkupConfigInterface reference;

	public List<PriceMarkupConfigDto> priceMarkupConfigList(Long orgId, Integer resourceType) {
		Response<List<PriceMarkupConfigDto>> response = reference.priceMarkupConfigList(orgId, resourceType);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public String priceMarkupConfig(List<PriceMarkupForm> configFormList) {
		Response<String> response = reference.priceMarkupConfig(configFormList);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
