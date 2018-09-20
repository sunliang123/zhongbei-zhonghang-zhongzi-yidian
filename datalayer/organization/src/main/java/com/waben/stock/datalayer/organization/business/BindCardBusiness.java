package com.waben.stock.datalayer.organization.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.manage.BankInfoDto;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.enums.BindCardResourceType;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.publisher.BindCardInterface;

/**
 * 绑卡 Business
 * 
 * @author luomengan
 *
 */
@Service("organizationBindCardBusiness")
public class BindCardBusiness {

	Logger logger = LoggerFactory.getLogger(getClass());

	public static Map<String, String> bankIconMap = new HashMap<>();

	@Autowired
	private BindCardInterface service;

	@Autowired
	private CnapsBusiness cnapsBusiness;

	@PostConstruct
	public void init() {
		try {
			List<BankInfoDto> list = cnapsBusiness.listBankInfo();
			for (BankInfoDto bankInfo : list) {
				bankIconMap.put(bankInfo.getBankName(), bankInfo.getIconLink());
			}
		} catch (Exception ex) {
			logger.error("缓存银行信息发生异常! {}", ex.getMessage());
		}
	}

	public BindCardDto findById(Long id) {
		Response<BindCardDto> response = service.fetchById(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public BindCardDto save(BindCardDto bindCard) {
		if (bankIconMap.size() == 0) {
			init();
		}
		// 判断是哪个银行
		BankInfoDto bankInfoDto = cnapsBusiness.findBankInfo(bindCard.getBankCard());
		if (bankInfoDto == null) {
			throw new ServiceException(ExceptionConstant.BANKCARD_NOTRECOGNITION_EXCEPTION);
		}
		bindCard.setBankName(bankInfoDto.getBankName());
		bindCard.setBankCode(bankInfoDto.getBankCode());
		// 执行绑卡操作
		Response<BindCardDto> response = service.addBankCard(bindCard);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public Long remove(Long id) {
		Response<Long> response = service.dropBankCard(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public BindCardDto revision(BindCardDto bindCard) {
		Response<BindCardDto> response = service.modifyBankCard(bindCard);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public List<BindCardDto> listsByOrgId(Long orgId) {
		if (bankIconMap.size() == 0) {
			init();
		}
		Response<List<BindCardDto>> response = service
				.listsByResourceTypeAndResourceId(BindCardResourceType.ORGANIZATION.getIndex(), orgId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}
	
}
