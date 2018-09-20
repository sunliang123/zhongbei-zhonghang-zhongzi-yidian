package com.waben.stock.applayer.strategist.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.strategist.payapi.wabenpay.WabenPayOverHttp;
import com.waben.stock.applayer.strategist.payapi.wabenpay.bean.BindRequestBean;
import com.waben.stock.applayer.strategist.payapi.wabenpay.bean.BindResponseBean;
import com.waben.stock.applayer.strategist.payapi.wabenpay.config.WaBenBankType;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.manage.BankInfoDto;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.enums.BankType;
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
@Service("strategistBindCardBusiness")
public class BindCardBusiness {

	Logger logger = LoggerFactory.getLogger(getClass());

	public static Map<String, String> bankIconMap = new HashMap<>();

	@Autowired
	private BindCardInterface bindCardReference;

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
		Response<BindCardDto> response = bindCardReference.fetchById(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public String getWabenContractNo(BindCardDto bindCard) {
		// 获取对应的支付平台编号
		BindRequestBean request = new BindRequestBean();
		request.setMember(String.valueOf(bindCard.getResourceId()));
		request.setIdNo(bindCard.getIdCard());
		request.setName(bindCard.getName());
		request.setPhone(bindCard.getPhone());
		request.setCardNo(bindCard.getBankCard());
		WaBenBankType wabenBankType = WaBenBankType.getByPlateformBankType(BankType.getByCode(bindCard.getBankCode()));
		if (wabenBankType == null) {
			throw new ServiceException(ExceptionConstant.BANKCARD_NOTSUPPORT_EXCEPTION);
		}
		request.setBankCode(wabenBankType.getCode());
		try {
			BindResponseBean bindResponse = WabenPayOverHttp.bind(request);
			return bindResponse.getContractNo();
		} catch (Exception ex) {
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, ex.getMessage());
		}
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
		bindCard.setBankName(bankInfoDto.getBankName() != null ? bankInfoDto.getBankName().split("\\|")[0] : null);
		bindCard.setBankCode(bankInfoDto.getBankCode());
		// bindCard.setContractNo(this.getWabenContractNo(bindCard));
		// 执行绑卡操作
		Response<BindCardDto> response = bindCardReference.addBankCard(bindCard);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public Long remove(Long id) {
		Response<Long> response = bindCardReference.dropBankCard(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public BindCardDto revision(BindCardDto bindCard) {
		Response<BindCardDto> response = bindCardReference.modifyBankCard(bindCard);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public List<BindCardDto> listsByPublisherId(Long publisherId) {
		if (bankIconMap.size() == 0) {
			init();
		}
		Response<List<BindCardDto>> response = bindCardReference
				.listsByResourceTypeAndResourceId(BindCardResourceType.PUBLISHER.getIndex(), publisherId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
