package com.waben.stock.datalayer.stockoption.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.organization.PriceMarkupConfigInterface;

/**
 * 加价配置 Business
 * 
 * @author luomengan
 *
 */
@Service("stockoptionPriceMarkupConfigBusiness")
public class PriceMarkupConfigBusiness {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PriceMarkupConfigInterface service;

	public List<BigDecimal> priceMarkupRatioList(Integer resourceType, Long resourceId, Long publisherId) {
		try {
			Response<List<BigDecimal>> response = service.priceMarkupRatioList(resourceType, resourceId, publisherId);
			String code = response.getCode();
			if ("200".equals(code)) {
				return response.getResult();
			} else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
				throw new NetflixCircuitException(code);
			}
			throw new ServiceException(response.getCode());
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("获取加价配置发生异常!publisher_{}", publisherId);
			return new ArrayList<>();
		}
	}

}
