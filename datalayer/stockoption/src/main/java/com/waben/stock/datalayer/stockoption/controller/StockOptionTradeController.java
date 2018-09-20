package com.waben.stock.datalayer.stockoption.controller;

import java.math.BigDecimal;
import java.util.List;

import com.waben.stock.interfaces.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.stockoption.entity.StockOptionAmountLimit;
import com.waben.stock.datalayer.stockoption.entity.StockOptionQuote;
import com.waben.stock.datalayer.stockoption.entity.StockOptionTrade;
import com.waben.stock.datalayer.stockoption.repository.StockOptionTradeDao;
import com.waben.stock.datalayer.stockoption.service.StockOptionRiskService;
import com.waben.stock.datalayer.stockoption.service.StockOptionTradeService;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionAdminDto;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionBlacklistAdminDto;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionRiskAdminDto;
import com.waben.stock.interfaces.dto.promotion.stockoption.StockOptionPromotionDto;
import com.waben.stock.interfaces.dto.promotion.stockoption.StockOptionStaDto;
import com.waben.stock.interfaces.dto.stockoption.OfflineStockOptionTradeDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionAmountLimitDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionOrgDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionQuoteDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;
import com.waben.stock.interfaces.enums.StockOptionTradeState;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StockOptionTradeQuery;
import com.waben.stock.interfaces.pojo.query.StockOptionTradeUserQuery;
import com.waben.stock.interfaces.pojo.query.admin.stockoption.StockOptionAdminQuery;
import com.waben.stock.interfaces.pojo.query.admin.stockoption.StockOptionRiskAdminQuery;
import com.waben.stock.interfaces.pojo.query.promotion.stockoption.StockOptionPromotionQuery;
import com.waben.stock.interfaces.service.stockoption.StockOptionTradeInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.PageToPageInfo;

// @RestController
// @RequestMapping("/stockoptiontrade")
@Component
public class StockOptionTradeController implements StockOptionTradeInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private StockOptionTradeService stockOptionTradeService;

	@Autowired
	private StockOptionRiskService riskService;

	@Autowired
	private StockOptionTradeDao stockOptionTradeDao;

	@Override
	public Response<PageInfo<StockOptionTradeDto>> pagesByQuery(@RequestBody StockOptionTradeQuery query) {
		Page<StockOptionTrade> page = stockOptionTradeService.pagesByQuery(query);
		PageInfo<StockOptionTradeDto> result = PageToPageInfo.pageToPageInfo(page, StockOptionTradeDto.class);
		for (int i = 0; i < page.getContent().size(); i++) {
			OfflineStockOptionTradeDto offlineStockOptionTradeDto = CopyBeanUtils.copyBeanProperties(
					OfflineStockOptionTradeDto.class, page.getContent().get(i).getOfflineTrade(), false);
			result.getContent().get(i).setOfflineTradeDto(offlineStockOptionTradeDto);
		}
		return new Response<>(result);
	}

	@Override
	public Response<StockOptionTradeDto> fetchById(@PathVariable Long id) {
		StockOptionTrade stockOptionTrade = stockOptionTradeService.findById(id);
		StockOptionTradeDto result = CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class, stockOptionTrade,
				false);
		if (stockOptionTrade.getOfflineTrade() != null) {
			OfflineStockOptionTradeDto offlineStockOptionTradeDto = CopyBeanUtils
					.copyBeanProperties(OfflineStockOptionTradeDto.class, stockOptionTrade.getOfflineTrade(), false);
			StockOptionOrgDto stockOptionOrgDto = CopyBeanUtils.copyBeanProperties(StockOptionOrgDto.class,
					stockOptionTrade.getOfflineTrade().getOrg(), false);
			offlineStockOptionTradeDto.setOrg(stockOptionOrgDto);
			result.setOfflineTradeDto(offlineStockOptionTradeDto);
		}
		return new Response<>(result);
	}

	@Override
	public Response<StockOptionTradeDto> modify(@PathVariable Long id) {
		StockOptionTrade stockOptionTrade = stockOptionTradeService.modify(id);
		StockOptionTradeDto result = CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class, stockOptionTrade,
				false);
		return new Response<>(result);
	}

	@Override
	public Response<StockOptionTradeDto> settlement(@PathVariable Long id) {
		StockOptionTradeDto result = CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class,
				stockOptionTradeService.settlement(id), false);
		return new Response<>(result);
	}

	@Override
	public Response<StockOptionTradeDto> success(@PathVariable Long id) {
		StockOptionTrade result = stockOptionTradeService.success(id);
		StockOptionTradeDto stockOptionTradeDto = CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class, result,
				false);
		return new Response<>(stockOptionTradeDto);
	}

	@Override
	public Response<StockOptionTradeDto> fail(@PathVariable Long id) {
		StockOptionTrade result = stockOptionTradeService.fail(id);
		StockOptionTradeDto stockOptionTradeDto = CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class, result,
				false);
		return new Response<>(stockOptionTradeDto);
	}

	@Override
	public Response<StockOptionTradeDto> exercise(@PathVariable Long id) {
		StockOptionTrade result = stockOptionTradeService.exercise(id);
		StockOptionTradeDto stockOptionTradeDto = CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class, result,
				false);
		return new Response<>(stockOptionTradeDto);
	}

	@Override
	public Response<List<StockOptionTradeDto>> stockOptionsWithState(@PathVariable Integer state) {
		StockOptionTradeState stockOptionTradeState = StockOptionTradeState.getByIndex(String.valueOf(state));
		List<StockOptionTrade> stockOptionTrades = stockOptionTradeService.fetchByState(stockOptionTradeState);
		List<StockOptionTradeDto> result = CopyBeanUtils.copyListBeanPropertiesToList(stockOptionTrades,
				StockOptionTradeDto.class);
		for (int i = 0; i < stockOptionTrades.size(); i++) {
			if (stockOptionTrades.get(i).getOfflineTrade() != null) {
				OfflineStockOptionTradeDto offlineStockOptionTradeDto = CopyBeanUtils.copyBeanProperties(
						OfflineStockOptionTradeDto.class, stockOptionTrades.get(i).getOfflineTrade(), false);
				result.get(i).setOfflineTradeDto(offlineStockOptionTradeDto);
			}
		}
		return new Response<>(result);
	}

	@Override
	public Response<StockOptionTradeDto> dueTreatmentExercise(@PathVariable Long id) {
		StockOptionTrade stockOptionTrades = stockOptionTradeService.dueTreatmentExercise(id);
		StockOptionTradeDto result = CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class, stockOptionTrades,
				false);
		return new Response<>(result);
	}

	@Override
	public Response<StockOptionTradeDto> add(@RequestBody StockOptionTradeDto stockOptionTradeDto) {
		logger.info("发布人{}申购期权{}，名义本金 {}!", stockOptionTradeDto.getPublisherId(), stockOptionTradeDto.getStockCode(),
				stockOptionTradeDto.getNominalAmount());
        logger.info("申购:{}", JacksonUtil.encode(stockOptionTradeDto));
		StockOptionTrade stockOptionTrade = CopyBeanUtils.copyBeanProperties(StockOptionTrade.class,
				stockOptionTradeDto, false);
		return new Response<>(CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class,
				stockOptionTradeService.save(stockOptionTrade), false));
	}

	@Override
	public Response<PageInfo<StockOptionTradeDto>> pagesByUserQuery(@RequestBody StockOptionTradeUserQuery query) {
		Page<StockOptionTrade> page = stockOptionTradeService.pagesByUserQuery(query);
		PageInfo<StockOptionTradeDto> result = PageToPageInfo.pageToPageInfo(page, StockOptionTradeDto.class);
		return new Response<>(result);
	}
	
	@Override
	public Response<PageInfo<StockOptionTradeDto>> tradeDynamicQuery(@RequestBody StockOptionTradeUserQuery query) {
		Page<StockOptionTrade> page = stockOptionTradeService.tradeDynamicQuery(query);
		PageInfo<StockOptionTradeDto> result = PageToPageInfo.pageToPageInfo(page, StockOptionTradeDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<PageInfo<StockOptionAdminDto>> adminPagesByQuery(@RequestBody StockOptionAdminQuery query) {
		Page<StockOptionAdminDto> page = stockOptionTradeService.adminPagesByQuery(query);
		PageInfo<StockOptionAdminDto> result = PageToPageInfo.pageToPageInfo(page, StockOptionAdminDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<StockOptionTradeDto> userRight(@PathVariable Long publisherId, @PathVariable Long id) {
			logger.info("发布人{}申请行权期权交易{}!", publisherId, id);
		StockOptionTrade trade = stockOptionTradeService.autoSettlement(publisherId, id);
		return new Response<>(CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class, trade, false));
	}

	@Override
	public Response<StockOptionTradeDto> turnover(@PathVariable Long id, @PathVariable Long orgid,
			BigDecimal orgRightMoneyRatio, BigDecimal buyingPrice) {
		logger.info("正式成交权期权交易{}_{}，机构权利金比例{}!", id, buyingPrice, orgRightMoneyRatio);
		StockOptionTrade trade = stockOptionTradeService.turnover(id, orgid, orgRightMoneyRatio, buyingPrice);
		return new Response<>(CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class, trade, false));
	}

	@Override
	public Response<StockOptionTradeDto> mark(@PathVariable Long id, Boolean isMark) {
		logger.info("标记权期权交易{}_{}!", id, isMark);
		StockOptionTrade trade = stockOptionTradeService.mark(id, isMark);
		return new Response<>(CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class, trade, false));
	}

	@Override
	public Response<StockOptionTradeDto> insettlement(@PathVariable Long id, BigDecimal sellingPrice) {
		logger.info("行权结算中权期权交易{}_{}!", id, sellingPrice);
		StockOptionTrade trade = stockOptionTradeService.insettlement(id, sellingPrice);
		return new Response<>(CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class, trade, false));
	}

	@Override
	public Response<StockOptionTradeDto> dosettlement(@PathVariable Long id) {
		logger.info("给用户结算权期权交易{}_{}!", id);
		StockOptionTrade trade = stockOptionTradeService.dosettlement(id);
		return new Response<>(CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class, trade, false));
	}

	@Override
	public Response<PageInfo<StockOptionRiskAdminDto>> adminNormalRiskPagesByQuery(
			@RequestBody StockOptionRiskAdminQuery query) {
		Page<StockOptionRiskAdminDto> page = riskService.adminNormalPagesByQuery(query);
		PageInfo<StockOptionRiskAdminDto> result = PageToPageInfo.pageToPageInfo(page, StockOptionRiskAdminDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<PageInfo<StockOptionRiskAdminDto>> adminAbnormalRiskPagesByQuery(
			@RequestBody StockOptionRiskAdminQuery query) {
		Page<StockOptionRiskAdminDto> page = riskService.adminAbnormalPagesByQuery(query);
		PageInfo<StockOptionRiskAdminDto> result = PageToPageInfo.pageToPageInfo(page, StockOptionRiskAdminDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<PageInfo<StockOptionBlacklistAdminDto>> adminBlackRiskPagesByQuery(
			@RequestBody StockOptionRiskAdminQuery query) {
		Page<StockOptionBlacklistAdminDto> page = riskService.adminBlackPagesByQuery(query);
		PageInfo<StockOptionBlacklistAdminDto> result = PageToPageInfo.pageToPageInfo(page,
				StockOptionBlacklistAdminDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<StockOptionAmountLimitDto> modifyStockOptionLimit(String stockCode, String stockName,
			Boolean isGlobal, BigDecimal amountLimit) {
		StockOptionAmountLimit limit = riskService.modifyStockOptionLimit(stockCode, stockName, isGlobal, amountLimit);
		return new Response<>(CopyBeanUtils.copyBeanProperties(StockOptionAmountLimitDto.class, limit, false));
	}

	@Override
	public Response<StockOptionQuoteDto> modifyStockOptionQuote(String stockCode, String stockName, Integer cycle,
			BigDecimal rightMoneyRatio) {
		StockOptionQuote quote = riskService.modifyStockOptionQuote(stockCode, stockName, cycle, rightMoneyRatio);
		return new Response<>(CopyBeanUtils.copyBeanProperties(StockOptionQuoteDto.class, quote, false));
	}

	@Override
	public Response<String> deleteStockOptionLimit(String stockCode) {
		riskService.deleteStockOptionLimit(stockCode);
		Response<String> result = new Response<>();
		result.setResult(stockCode);
		return result;
	}

	@Override
	public Response<StockOptionAmountLimitDto> fetchGlobalStockOptionLimit() {
		StockOptionAmountLimit quote = riskService.findStockOptionQuote();
		return new Response<>(CopyBeanUtils.copyBeanProperties(StockOptionAmountLimitDto.class, quote, false));
	}

	@Override
	public Response<PageInfo<StockOptionPromotionDto>> promotionPagesByQuery(
			@RequestBody StockOptionPromotionQuery query) {
		Page<StockOptionPromotionDto> page = stockOptionTradeService.promotionPagesByQuery(query);
		PageInfo<StockOptionPromotionDto> result = PageToPageInfo.pageToPageInfo(page, StockOptionPromotionDto.class);
		return new Response<>(result);
	}
	
	@Override
	public Response<StockOptionStaDto> promotionSta(@RequestBody StockOptionPromotionQuery query) {
		return new Response<>(stockOptionTradeService.promotionSta(query));
	}

	@Override
	public Response<Integer> countStockOptionTradeState(Long publisherId) {
		return new Response<>(stockOptionTradeDao.countStockOptionTradeState(publisherId));
	}

	@Override
	public Response<StockOptionTradeDto> update(@RequestBody StockOptionTradeDto dto) {
		StockOptionTrade stockOptionTrade = CopyBeanUtils.copyBeanProperties(StockOptionTrade.class,
				dto, false);
		StockOptionTrade result = stockOptionTradeDao.update(stockOptionTrade);
		StockOptionTradeDto stockOptionTradeDto = CopyBeanUtils.copyBeanProperties(StockOptionTradeDto.class,
				result, false);
		return new Response<>(stockOptionTradeDto);
	}

}
