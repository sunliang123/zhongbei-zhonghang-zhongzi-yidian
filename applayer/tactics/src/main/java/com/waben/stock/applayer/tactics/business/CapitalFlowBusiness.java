package com.waben.stock.applayer.tactics.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.tactics.dto.publisher.CapitalFlowWithExtendDto;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.publisher.CapitalFlowDto;
import com.waben.stock.interfaces.dto.publisher.PaymentOrderDto;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;
import com.waben.stock.interfaces.enums.CapitalFlowExtendType;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.CapitalFlowQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.publisher.CapitalFlowInterface;
import com.waben.stock.interfaces.service.stockcontent.StrategyTypeInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * 资金流水 Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsCapitalFlowBusiness")
public class CapitalFlowBusiness {

	@Autowired
	private CapitalFlowInterface service;

	@Autowired
	private BuyRecordBusiness buyRecordBusiness;

	@Autowired
	private StockBusiness stockBusiness;

	@Autowired
	private StrategyTypeInterface strategyTypeReference;

	@Autowired
	private StockOptionTradeBusiness tradeBusiness;

	@Autowired
	private PaymentOrderBusiness paymentOrderBusiness;

	public PageInfo<CapitalFlowWithExtendDto> pages(CapitalFlowQuery query) {
		Response<PageInfo<CapitalFlowDto>> response = service.pagesByQuery(query);
		if ("200".equals(response.getCode())) {
			List<CapitalFlowWithExtendDto> content = CopyBeanUtils
					.copyListBeanPropertiesToList(response.getResult().getContent(), CapitalFlowWithExtendDto.class);
			PageInfo<CapitalFlowWithExtendDto> result = new PageInfo<>(content, response.getResult().getTotalPages(),
					response.getResult().getLast(), response.getResult().getTotalElements(),
					response.getResult().getSize(), response.getResult().getNumber(), response.getResult().getFrist());
			// 获取策略类型列表
			Response<List<StrategyTypeDto>> strategyTypeResponse = strategyTypeReference.lists(true);
			if (!"200".equals(strategyTypeResponse.getCode())) {
				throw new ServiceException(strategyTypeResponse.getCode());
			}
			// 获取股票代码和股票名称
			for (int i = 0; i < response.getResult().getContent().size(); i++) {
				CapitalFlowDto flow = response.getResult().getContent().get(i);
				CapitalFlowWithExtendDto flowWithExtend = content.get(i);
				if (flow.getExtendType() == CapitalFlowExtendType.BUYRECORD) {
					BuyRecordDto buyRecord = buyRecordBusiness.findById(flow.getExtendId());
					if (buyRecord != null) {
						// 设置股票代码和股票名称
						flowWithExtend.setStockCode(buyRecord.getStockCode());
						StockDto stock = stockBusiness.findByCode(buyRecord.getStockCode());
						if (stock != null) {
							flowWithExtend.setStockName(stock.getName());
						}
						// 设置策略类型ID和策略类型名称
						flowWithExtend.setStrategyTypeId(buyRecord.getStrategyTypeId());
						for (StrategyTypeDto strategyType : strategyTypeResponse.getResult()) {
							if (strategyType.getId() == buyRecord.getStrategyTypeId()) {
								flowWithExtend.setStrategyTypeName(strategyType.getName());
							}
						}
					}
				} else if (flow.getExtendType() == CapitalFlowExtendType.STOCKOPTIONTRADE) {
					StockOptionTradeDto stockOption = tradeBusiness.findById(flow.getExtendId());
					if (stockOption != null) {
						// 设置股票代码和股票名称
						flowWithExtend.setStockCode(stockOption.getStockCode());
						StockDto stock = stockBusiness.findByCode(stockOption.getStockCode());
						if (stock != null) {
							flowWithExtend.setStockName(stock.getName());
						}
					}
				} else if (flow.getExtendType() == CapitalFlowExtendType.PAYMENTORDER) {
					PaymentOrderDto paymentOrder = paymentOrderBusiness.findById(flow.getExtendId());
					if (paymentOrder != null && paymentOrder.getType() != null) {
						flowWithExtend.setPaymentType(paymentOrder.getType().getType());
					}
				}
			}
			return result;
		}
		throw new ServiceException(response.getCode());
	}

}
