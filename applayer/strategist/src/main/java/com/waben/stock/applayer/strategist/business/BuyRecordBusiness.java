package com.waben.stock.applayer.strategist.business;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.applayer.strategist.dto.buyrecord.BuyRecordWithMarketDto;
import com.waben.stock.applayer.strategist.dto.buyrecord.TradeDynamicDto;
import com.waben.stock.interfaces.commonapi.retrivestock.RetriveStockOverHttp;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockMarket;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.buyrecord.DeferredRecordDto;
import com.waben.stock.interfaces.dto.buyrecord.SettlementDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.enums.BuyRecordState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.BuyRecordQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.SettlementQuery;
import com.waben.stock.interfaces.service.buyrecord.BuyRecordInterface;
import com.waben.stock.interfaces.service.buyrecord.DeferredRecordInterface;
import com.waben.stock.interfaces.service.buyrecord.SettlementInterface;
import com.waben.stock.interfaces.service.publisher.PublisherInterface;
import com.waben.stock.interfaces.service.stockcontent.StockInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

@Service("strategistBuyRecordBusiness")
public class BuyRecordBusiness {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private BuyRecordInterface buyRecordReference;

	@Autowired
	private SettlementInterface settlementReference;

	@Autowired
	private PublisherInterface publisherReference;

	@Autowired
	private StockInterface stockReference;

	@Autowired
	private DeferredRecordInterface deferredRecordReference;
	
	@Autowired
	private StrategyTypeBusiness strategyTypeBusiness;

	public BuyRecordDto findById(Long id) {
		Response<BuyRecordDto> response = buyRecordReference.fetchBuyRecord(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public BuyRecordDto buy(BuyRecordDto buyRecordDto) {
		// 获取股票名称
		Response<StockDto> stock = stockReference.fetchWithExponentByCode(buyRecordDto.getStockCode());
		if ("200".equals(stock.getCode())) {
			buyRecordDto.setStockName(stock.getResult().getName());
		} else {
			throw new ServiceException(stock.getCode());
		}
		// 获取策略发布人
		Response<PublisherDto> publisher = publisherReference.fetchById(buyRecordDto.getPublisherId());
		if ("200".equals(publisher.getCode())) {
			buyRecordDto.setPublisherPhone(publisher.getResult().getPhone());
		} else {
			throw new ServiceException(stock.getCode());
		}
		// 请求点买
		Response<BuyRecordDto> response = buyRecordReference.addBuyRecord(buyRecordDto);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PageInfo<BuyRecordDto> pages(BuyRecordQuery buyRecordQuery) {

		Response<PageInfo<BuyRecordDto>> response = buyRecordReference.pagesByQuery(buyRecordQuery);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PageInfo<BuyRecordWithMarketDto> pagesUnwind(BuyRecordQuery buyRecordQuery) {
		PageInfo<BuyRecordDto> pageInfo = this.pages(buyRecordQuery);
		List<BuyRecordWithMarketDto> content = this.wrapMarketInfo(pageInfo.getContent());
		for (BuyRecordWithMarketDto market : content) {
			List<DeferredRecordDto> deferredRecordList = deferredRecordReference
					.fetchByPublisherIdAndBuyRecordId(market.getPublisherId(), market.getId()).getResult();
			Integer deferredDays = 0;
			BigDecimal deferredCharges = new BigDecimal(0);
			if (deferredRecordList != null && deferredRecordList.size() > 0) {
				for (DeferredRecordDto deferredRecord : deferredRecordList) {
					deferredDays += deferredRecord.getCycle();
					deferredCharges = deferredCharges.add(deferredRecord.getFee().abs());
				}
			}
			market.setDeferredDays(deferredDays);
			market.setDeferredCharges(deferredCharges);
		}
		return new PageInfo<>(content, pageInfo.getTotalPages(), pageInfo.getLast(), pageInfo.getTotalElements(),
				pageInfo.getSize(), pageInfo.getNumber(), pageInfo.getFrist());
	}

	public BuyRecordWithMarketDto wrapMarketInfo(BuyRecordDto buyRecord) {
		List<BuyRecordDto> list = new ArrayList<>();
		list.add(buyRecord);
		List<BuyRecordWithMarketDto> marketList = wrapMarketInfo(list);
		return marketList.get(0);
	}

	public List<BuyRecordWithMarketDto> wrapMarketInfo(List<BuyRecordDto> list) {
		Map<Long, Integer> strategyTypeMap = strategyTypeBusiness.strategyTypeMap();
		List<BuyRecordWithMarketDto> result = CopyBeanUtils.copyListBeanPropertiesToList(list,
				BuyRecordWithMarketDto.class);
		List<String> codes = new ArrayList<>();
		for (BuyRecordWithMarketDto record : result) {
			if (record.getStrategyTypeId() != null) {
				record.setCycle(strategyTypeMap.get(record.getStrategyTypeId()));
			}
			codes.add(record.getStockCode());
		}
		if (codes.size() > 0) {
			List<StockMarket> stockMarketList = RetriveStockOverHttp.listStockMarket(restTemplate, codes);
			if (stockMarketList != null && stockMarketList.size() > 0) {
				for (int i = 0; i < stockMarketList.size(); i++) {
					StockMarket market = stockMarketList.get(i);
					BuyRecordWithMarketDto record = result.get(i);
					record.setStockName(market.getName());
					record.setLastPrice(market.getLastPrice());
					record.setUpDropPrice(market.getUpDropPrice());
					record.setUpDropSpeed(market.getUpDropSpeed());
					record.setStockStatus(market.getStatus());
				}
			}
		}
		return result;
	}

	public BuyRecordDto sellApply(Long userId, Long id) {
		BuyRecordDto buyRecord = this.findById(id);
		// 持仓中的才能申请卖出
		if (buyRecord.getState() == BuyRecordState.HOLDPOSITION && buyRecord.getBuyingTime() != null
				&& !sdf.format(new Date()).equals(sdf.format(buyRecord.getBuyingTime()))) {
			Response<BuyRecordDto> response = buyRecordReference.sellApply(userId, id);
			if ("200".equals(response.getCode())) {
				return response.getResult();
			}
			throw new ServiceException(response.getCode());
		} else {
			throw new ServiceException(ExceptionConstant.USERSELLAPPLY_NOTMATCH_EXCEPTION);
		}
	}

	public PageInfo<TradeDynamicDto> tradeDynamic(int page, int size) {
		SettlementQuery sQuery = new SettlementQuery(page, size / 2);
		sQuery.setOnlyProfit(true);
		Response<PageInfo<SettlementDto>> sResponse = settlementReference.pagesByQuery(sQuery);
		if ("200".equals(sResponse.getCode())) {
			BuyRecordQuery bQuery = new BuyRecordQuery(page, size - sResponse.getResult().getContent().size(), null,
					new BuyRecordState[] { BuyRecordState.HOLDPOSITION, BuyRecordState.SELLAPPLY,
							BuyRecordState.SELLLOCK });
			PageInfo<BuyRecordDto> pageInfo = pages(bQuery);
			int total = sResponse.getResult().getContent().size() + pageInfo.getContent().size();
			List<TradeDynamicDto> content = new ArrayList<>();
			boolean isSettlement = true;
			for (int n = 0; n < total; n++) {
				if (isSettlement && sResponse.getResult().getContent().size() > 0) {
					SettlementDto settlement = sResponse.getResult().getContent().remove(0);
					TradeDynamicDto inner = new TradeDynamicDto();
					inner.setTradeType(2);
					inner.setPublisherId(settlement.getBuyRecord().getPublisherId());
					inner.setNumberOfStrand(settlement.getBuyRecord().getNumberOfStrand());
					inner.setStockCode(settlement.getBuyRecord().getStockCode());
					inner.setStockName(settlement.getBuyRecord().getStockName());
					inner.setPhone(settlement.getBuyRecord().getPublisherPhone());
					inner.setProfit(settlement.getPublisherProfitOrLoss());
					inner.setTradePrice(settlement.getBuyRecord().getSellingPrice());
					inner.setTradeTime(settlement.getBuyRecord().getSellingTime());
					content.add(inner);
					isSettlement = false;
				} else {
					if (pageInfo.getContent().size() > 0) {
						BuyRecordDto buyRecord = pageInfo.getContent().remove(0);
						TradeDynamicDto inner = new TradeDynamicDto();
						inner.setTradeType(1);
						inner.setPublisherId(buyRecord.getPublisherId());
						inner.setStockCode(buyRecord.getStockCode());
						inner.setNumberOfStrand(buyRecord.getNumberOfStrand());
						inner.setStockName(buyRecord.getStockName());
						inner.setPhone(buyRecord.getPublisherPhone());
						inner.setTradePrice(buyRecord.getBuyingPrice());
						inner.setTradeTime(buyRecord.getBuyingTime());
						content.add(inner);
						isSettlement = true;
					} else {
						isSettlement = true;
						total++;
					}
				}

			}
			return new PageInfo<TradeDynamicDto>(content, 0, false, 0L, size, page, false);
		}
		throw new ServiceException(sResponse.getCode());
	}

	public Integer strategyJoinCount(Long publisherId, Long strategyTypeId) {
		Response<Integer> response = buyRecordReference.strategyJoinCount(publisherId, strategyTypeId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public boolean hasStrategyQualify(Long publisherId, Long strategyTypeId) {
		if (new Long(3).equals(strategyTypeId)) {
			// 策略类型为3，表示2000元体验金活动
			Integer count = this.strategyJoinCount(publisherId, strategyTypeId);
			if (count != null && count > 0) {
				return false;
			}
		}
		return true;
	}

}
