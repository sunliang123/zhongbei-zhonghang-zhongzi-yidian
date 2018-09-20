package com.waben.stock.datalayer.publisher.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.datalayer.publisher.entity.RealName;
import com.waben.stock.datalayer.publisher.repository.RealNameDao;
import com.waben.stock.interfaces.commonapi.juhe.RealNameInfoVerifier;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.enums.ResourceType;
import com.waben.stock.interfaces.exception.ServiceException;

/**
 * 实名认证 Service
 * 
 * @author luomengan
 *
 */
@Service
public class RealNameService {

	@Autowired
	private RealNameDao realNameDao;
	
	@Autowired
	private RestTemplate restTemplate;

	public synchronized RealName save(RealName realName) {
		RealName check = realNameDao.retriveByResourceTypeAndResourceId(realName.getResourceType(),
				realName.getResourceId());
		if (check != null) {
			throw new ServiceException(ExceptionConstant.REALNAME_EXIST_EXCEPTION);
		}
		List<RealName> checkList = realNameDao.retrieveByNameAndIdCard(realName.getName(), realName.getIdCard());
		if (checkList != null && checkList.size() > 0) {
			throw new ServiceException(ExceptionConstant.REALNAME_ALREADY_USERED_EXCEPTION);
		}
		// 验证实名信息
		boolean isValid = RealNameInfoVerifier.verify(restTemplate, realName.getName(), realName.getIdCard());
		if (!isValid) {
			throw new ServiceException(ExceptionConstant.REALNAME_WRONG_EXCEPTION);
		}
		realName.setCreateTime(new Date());
		return realNameDao.create(realName);
	}

	public RealName findByResourceTypeAndResourceId(ResourceType resourceType, Long resourceId) {
		return realNameDao.retriveByResourceTypeAndResourceId(resourceType, resourceId);
	}

}
