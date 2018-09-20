package com.waben.stock.applayer.promotion.business;

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
@Service("promotionBindCardBusiness")
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
		try {
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
		} catch (ServiceException ex) {
			String type = ex.getType();
			if (type.equals("2014") || type.equals("1003")) {
				throw new ServiceException(ExceptionConstant.BANKCARDINFO_WRONG_EXCEPTION);
			}
			throw ex;
		}
	}

	public Long remove(Long id) {
		Response<Long> response = service.dropBankCard(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public BindCardDto revision(BindCardDto bindCard) {
		if (bankIconMap.size() == 0) {
			init();
		}
		// 判断是哪个银行
		try {
			BankInfoDto bankInfoDto = cnapsBusiness.findBankInfo(bindCard.getBankCard());
			if (bankInfoDto == null) {
				throw new ServiceException(ExceptionConstant.BANKCARD_NOTRECOGNITION_EXCEPTION);
			}
			bindCard.setBankName(bankInfoDto.getBankName());
			bindCard.setBankCode(bankInfoDto.getBankCode());
			// 执行绑卡操作
			Response<BindCardDto> response = service.modifyBankCard(bindCard);
			if ("200".equals(response.getCode())) {
				return response.getResult();
			}
			throw new ServiceException(response.getCode());
		} catch (ServiceException ex) {
			String type = ex.getType();
			if (type.equals("2014") || type.equals("1003")) {
				throw new ServiceException(ExceptionConstant.BANKCARDINFO_WRONG_EXCEPTION);
			}
			throw ex;
		}
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

	public BindCardDto getOrgBindCard(Long orgId) {
		List<BindCardDto> bindCardList = this.listsByOrgId(orgId);
		if (bindCardList != null && bindCardList.size() > 0) {
			return bindCardList.get(bindCardList.size() - 1);
		}
		return null;
	}

	public BindCardDto findOrgBindCardByName(String name) {
		Response<BindCardDto> response = service.fetchOrgBindCardByName(name);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public BindCardDto orgBindCard(Long orgId, BindCardDto bindCardDto) {
		if (bindCardDto.getId() != null && bindCardDto.getId() > 0) {
			BindCardDto oldBindCardDto = getOrgBindCard(orgId);
			oldBindCardDto.setBankCard(bindCardDto.getBankCard());
			oldBindCardDto.setIdCard(bindCardDto.getIdCard());
			oldBindCardDto.setName(bindCardDto.getName());
			oldBindCardDto.setPhone(bindCardDto.getPhone());
			return this.revision(oldBindCardDto);
		} else {
			bindCardDto.setResourceType(BindCardResourceType.ORGANIZATION);
			bindCardDto.setResourceId(orgId);
			return this.save(bindCardDto);
		}
	}

}
