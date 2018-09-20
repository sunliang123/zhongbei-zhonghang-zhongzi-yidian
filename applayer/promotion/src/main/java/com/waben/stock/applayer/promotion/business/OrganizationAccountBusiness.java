package com.waben.stock.applayer.promotion.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.organization.OrganizationAccountDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.OrganizationAccountQuery;
import com.waben.stock.interfaces.service.organization.OrganizationAccountInterface;

/**
 * 机构账户 Business
 * 
 * @author luomengan
 *
 */
@Service("promotionOrganizationAccountBusiness")
public class OrganizationAccountBusiness {

	@Autowired
	private OrganizationAccountInterface reference;

	@Autowired
	private OrganizationBusiness organizationBusiness;

	public OrganizationAccountDto fetchByOrgId(Long orgId) {
		Response<OrganizationAccountDto> response = reference.fetchByOrgId(orgId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public void modifyPaymentPassword(Long orgId, String oldPaymentPassword, String paymentPassword) {
		Response<Void> response = reference.modifyPaymentPassword(orgId, oldPaymentPassword, paymentPassword);
		if ("200".equals(response.getCode())) {
			return;
		}
		throw new ServiceException(response.getCode());
	}

	public PageInfo<OrganizationAccountDto> pages(OrganizationAccountQuery query) {
		Response<PageInfo<OrganizationAccountDto>> response = reference.pages(query);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public OrganizationAccountDto revisionState(Long id, Integer state) {
		Response<OrganizationAccountDto> response = reference.modifyState(id, state);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

    public OrganizationAccountDto recover(Long id) {
		Response<OrganizationAccountDto> response = reference.recover(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
    }

	public OrganizationAccountDto freeze(OrganizationAccountDto accountDto) {
		Response<OrganizationAccountDto> response = reference.freeze(accountDto);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}
}
