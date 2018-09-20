package com.waben.stock.applayer.tactics.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.organization.OrganizationPublisherDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.organization.OrganizationPublisherInterface;

/**
 * 机构推广的发布人 Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsOrganizationPublisherBusiness")
public class OrganizationPublisherBusiness {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrganizationPublisherInterface reference;

	public OrganizationPublisherDto addOrgPublisher(Long publisherId, String orgCode) {
		OrganizationPublisherDto orgPublisher = new OrganizationPublisherDto();
		orgPublisher.setOrgCode(orgCode);
		orgPublisher.setPublisherId(publisherId);
		Response<OrganizationPublisherDto> response = reference.addOrgPublisher(orgPublisher);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public OrganizationPublisherDto fetchOrgPublisher(Long publisherId) {
		Response<OrganizationPublisherDto> response = reference.fetchOrgPublisher(publisherId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
