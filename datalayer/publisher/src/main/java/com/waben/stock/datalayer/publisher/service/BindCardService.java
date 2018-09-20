package com.waben.stock.datalayer.publisher.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.datalayer.publisher.entity.BindCard;
import com.waben.stock.datalayer.publisher.repository.BindCardDao;
import com.waben.stock.interfaces.commonapi.juhe.BankCardInfoVerifier;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.enums.BindCardResourceType;
import com.waben.stock.interfaces.exception.ServiceException;

/**
 * 绑卡 Service
 * 
 * @author luomengan
 *
 */
@Service
public class BindCardService {

	@Autowired
	private BindCardDao bindCardDao;
	
	@Autowired
	private RestTemplate restTemplate;

	public synchronized BindCard save(BindCard bindCard) {
		BindCard check = bindCardDao.retriveByResourceTypeAndResourceIdAndBankCard(bindCard.getResourceType(),
				bindCard.getResourceId(), bindCard.getBankCard());
		if (check != null) {
			throw new ServiceException(ExceptionConstant.BANKCARD_ALREADY_BIND_EXCEPTION);
		}
		List<BindCard> checkList = bindCardDao.retrieveByBankCardAndResourceType(bindCard.getBankCard(),
				bindCard.getResourceType());
		if (checkList != null && checkList.size() > 0) {
			throw new ServiceException(ExceptionConstant.BANKCARD_ALREADY_USERED_EXCEPTION);
		}
		// 判断四要素
		boolean isValid = BankCardInfoVerifier.verify(restTemplate, bindCard.getName(), bindCard.getIdCard(), bindCard.getPhone(),
				bindCard.getBankCard());
		if (!isValid) {
			throw new ServiceException(ExceptionConstant.BANKCARDINFO_NOTMATCH_EXCEPTION);
		}
		bindCard.setCreateTime(new Date());
		bindCardDao.create(bindCard);
		return bindCard;
	}

	public List<BindCard> list(BindCardResourceType resourceType, Long resourceId) {
		return bindCardDao.listByResourceTypeAndResourceId(resourceType, resourceId);
	}

	public BindCard findById(Long id) {
		return bindCardDao.retrieve(id);
	}

	public BindCard revision(BindCard bindCard) {
		if(bindCard.isNeedCheckCard()) {
			// 判断四要素
			boolean isValid = BankCardInfoVerifier.verify(restTemplate, bindCard.getName(), bindCard.getIdCard(), bindCard.getPhone(),
					bindCard.getBankCard());
			if (!isValid) {
				throw new ServiceException(ExceptionConstant.BANKCARDINFO_NOTMATCH_EXCEPTION);
			}
		}
		return bindCardDao.update(bindCard);
	}
	
	public Long remove(Long id) {
		bindCardDao.delete(id);
		return id;
	}

	public BindCard findOrgBindCardByName(String name) {
		BindCardResourceType resourceType = BindCardResourceType.ORGANIZATION;
		return bindCardDao.retrieveBindCardByNameAndResourceType(name, resourceType);
	}
}
