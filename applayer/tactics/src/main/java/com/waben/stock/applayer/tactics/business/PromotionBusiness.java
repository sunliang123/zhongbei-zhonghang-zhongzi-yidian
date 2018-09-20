package com.waben.stock.applayer.tactics.business;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.tactics.dto.publisher.PromotionBaseDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.publisher.CapitalFlowInterface;
import com.waben.stock.interfaces.service.publisher.PublisherInterface;

/**
 * 推广 Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsPromotionBusiness")
public class PromotionBusiness {

	@Autowired
	private PublisherInterface publisherReference;

	@Autowired
	private CapitalFlowInterface capitalFlowReference;

	public PromotionBaseDto getPromotionBase(Long publisherId) {
		PromotionBaseDto result = new PromotionBaseDto();
		result.setPromotionCode(findById(publisherId).getPromotionCode());
		result.setPromotionCount(promotionCount(publisherId));
		result.setPromotionProfit(promotionTotalAmount(publisherId));
		return result;
	}

	public PublisherDto findById(Long publisherId) {
		Response<PublisherDto> publisherResp = publisherReference.fetchById(publisherId);
		if ("200".equals(publisherResp.getCode())) {
			return publisherResp.getResult();
		}
		throw new ServiceException(publisherResp.getCode());
	}

	public Integer promotionCount(Long publisherId) {
		Response<Integer> promotionCountResp = publisherReference.promotionCount(publisherId);
		if ("200".equals(promotionCountResp.getCode())) {
			return promotionCountResp.getResult();
		}
		throw new ServiceException(promotionCountResp.getCode());
	}

	public BigDecimal promotionTotalAmount(Long publisherId) {
		Response<BigDecimal> promotionTotalAmountResp = capitalFlowReference.promotionTotalAmount(publisherId);
		if ("200".equals(promotionTotalAmountResp.getCode())) {
			return promotionTotalAmountResp.getResult();
		}
		throw new ServiceException(promotionTotalAmountResp.getCode());
	}

	public PageInfo<PublisherDto> pagePromotionUser(Long publisherId, int page, int size) {
		Response<PageInfo<PublisherDto>> pagePromotionUserResp = publisherReference.pagePromotionUser(publisherId, page,
				size);
		if ("200".equals(pagePromotionUserResp.getCode())) {
			return pagePromotionUserResp.getResult();
		}
		throw new ServiceException(pagePromotionUserResp.getCode());
	}

}
