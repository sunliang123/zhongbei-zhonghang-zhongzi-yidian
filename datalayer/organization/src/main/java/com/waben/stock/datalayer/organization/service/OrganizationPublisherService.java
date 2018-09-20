package com.waben.stock.datalayer.organization.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.organization.business.StockOptionTradeBusiness;
import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.datalayer.organization.entity.OrganizationPublisher;
import com.waben.stock.datalayer.organization.repository.OrganizationDao;
import com.waben.stock.datalayer.organization.repository.OrganizationPublisherDao;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.ServiceException;

/**
 * 机构推广的发布人 Service
 *
 * @author luomengan
 */
@Service
public class OrganizationPublisherService {

	@Autowired
	private OrganizationPublisherDao dao;

	@Autowired
	private OrganizationDao orgDao;

	@Autowired
	private StockOptionTradeBusiness tradeBusiness;

	@Transactional
	public OrganizationPublisher addOrgPublisher(String orgCode, Long publisherId) {
		if (orgCode == null || "".equals(orgCode.trim())) {
			return null;
		}
		Organization org = orgDao.retrieveByCode(orgCode);
		if (org == null) {
			throw new ServiceException(ExceptionConstant.ORGCODE_NOTEXIST_EXCEPTION);
		}
		Integer count = tradeBusiness.countStockOptionTradeState(publisherId);
		if (count > 0) {
			throw new ServiceException(ExceptionConstant.MODIFY_DISABLED_EXCEPITON);
		}

		OrganizationPublisher orgPublisher = dao.retrieveByPublisherId(publisherId);
		if (orgPublisher != null) {
			if (orgPublisher.getOrgCode().equals(orgCode)) {
				return orgPublisher;
			} else {
				dao.delete(orgPublisher.getId());
			}
		}
		orgPublisher = new OrganizationPublisher();
		orgPublisher.setOrgCode(orgCode);
		orgPublisher.setTreeCode(org.getTreeCode());
		orgPublisher.setOrgId(org.getId());
		orgPublisher.setPublisherId(publisherId);
		orgPublisher.setCreateTime(new Date());
		return dao.create(orgPublisher);
	}

	public List<OrganizationPublisher> findOrganizationPublishersByCode(String code) {
		return dao.retrieveOrganizationPublishersByOrgCode(code);
	}

	public OrganizationPublisher findOrgPulisher(Long publisherId) {
		return dao.retrieveByPublisherId(publisherId);
	}

	public List<OrganizationPublisher> findAll() {
		return dao.list();
	}



	public List<Organization> listByLevel(Integer level) {
		return orgDao.listByLevel(level);
	}

	public OrganizationPublisher addOrgPublisherAdmin(String orgCode, Long publisherId) {
		if (orgCode == null || "".equals(orgCode.trim())) {
			return null;
		}
		Organization org = orgDao.retrieveByCode(orgCode);
		if (org == null) {
			throw new ServiceException(ExceptionConstant.ORGCODE_NOTEXIST_EXCEPTION);
		}

		OrganizationPublisher orgPublisher = dao.retrieveByPublisherId(publisherId);
		if (orgPublisher != null) {
			if (orgPublisher.getOrgCode().equals(orgCode)) {
				return orgPublisher;
			} else {
				dao.delete(orgPublisher.getId());
			}
		}
		orgPublisher = new OrganizationPublisher();
		orgPublisher.setOrgCode(orgCode);
		orgPublisher.setTreeCode(org.getTreeCode());
		orgPublisher.setOrgId(org.getId());
		orgPublisher.setPublisherId(publisherId);
		orgPublisher.setCreateTime(new Date());
		return dao.create(orgPublisher);
	}
}
