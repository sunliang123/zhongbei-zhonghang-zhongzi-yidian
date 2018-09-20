package com.waben.stock.applayer.tactics.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.BuyRecordBusiness;
import com.waben.stock.applayer.tactics.business.CapitalAccountBusiness;
import com.waben.stock.applayer.tactics.business.ExperienceBusiness;
import com.waben.stock.applayer.tactics.business.HolidayBusiness;
import com.waben.stock.applayer.tactics.business.StockBusiness;
import com.waben.stock.applayer.tactics.dto.buyrecord.BuyRecordWithMarketDto;
import com.waben.stock.applayer.tactics.dto.buyrecord.TradeDynamicDto;
import com.waben.stock.applayer.tactics.security.SecurityUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.enums.BuyRecordState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.BuyRecordQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.util.PasswordCrypt;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 点买记录 Controller
 * 
 * @author luomengan
 *
 */
@RestController("tacticsBuyRecordController")
@RequestMapping("/buyRecord")
@Api(description = "点买交易")
public class BuyRecordController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BuyRecordBusiness buyRecordBusiness;

	@Autowired
	private CapitalAccountBusiness capitalAccountBusiness;

	@Autowired
	private HolidayBusiness holidayBusiness;

	@Autowired
	private StockBusiness stockBusiness;
	
	@Autowired
	private ExperienceBusiness experienceBusiness;

	@GetMapping("/isTradeTime")
	@ApiOperation(value = "是否为交易时间段")
	public Response<Boolean> isTradeTime() {
		return new Response<>(holidayBusiness.isTradeTime());
	}

	@GetMapping("/strategyqualify/{strategyTypeId}")
	@ApiOperation(value = "是否有资格参与某个策略")
	public Response<Boolean> hasStrategyQualify(@PathVariable("strategyTypeId") Long strategyTypeId) {
		return new Response<>(buyRecordBusiness.hasStrategyQualify(SecurityUtil.getUserId(), strategyTypeId));
	}

	@PostMapping("/buy")
	@ApiOperation(value = "点买")
	public Response<BuyRecordWithMarketDto> buy(@RequestParam(required = true) Long strategyTypeId,
			@RequestParam(required = true) BigDecimal applyAmount, @RequestParam(required = true) BigDecimal serviceFee,
			@RequestParam(required = true) BigDecimal reserveFund,
			@RequestParam(required = true) BigDecimal delegatePrice,
			@RequestParam(required = true) BigDecimal profitPoint, @RequestParam(required = true) BigDecimal lossPoint,
			Integer lossMultiple, @RequestParam(required = true) String stockCode,
			@RequestParam(required = true) Boolean deferred, BigDecimal deferredFee,
			@RequestParam(required = true) String paymentPassword) {
		logger.info("APP调用接口发布人{}点买股票{}，申请资金{}!", SecurityUtil.getUserId(), stockCode, applyAmount);
		// 检查交易时间段
		boolean isTradeTime = holidayBusiness.isTradeTime();
		if (!isTradeTime) {
			throw new ServiceException(ExceptionConstant.BUYRECORD_NONTRADINGPERIOD_EXCEPTION);
		}
		// 检查股票是否可以购买，停牌、涨停、跌停不能购买
		stockBusiness.checkStock(stockCode);
		// 判断是否有资格参与该策略
		boolean qualify = buyRecordBusiness.hasStrategyQualify(SecurityUtil.getUserId(), strategyTypeId);
		if (!qualify) {
			throw new ServiceException(ExceptionConstant.STRATEGYQUALIFY_NOTENOUGH_EXCEPTION);
		}
		// 判断该市值是否足够购买一手股票
		BigDecimal temp = applyAmount.divide(delegatePrice, 2, RoundingMode.HALF_DOWN);
		Integer numberOfStrand = temp.divideAndRemainder(BigDecimal.valueOf(100))[0].multiply(BigDecimal.valueOf(100))
				.intValue();
		if (numberOfStrand < 100) {
			throw new ServiceException(ExceptionConstant.APPLYAMOUNT_NOTENOUGH_BUYSTOCK_EXCEPTION);
		}
		// 检查参数是否合理
		if (delegatePrice.compareTo(new BigDecimal(0)) <= 0) {
			throw new ServiceException(ExceptionConstant.ARGUMENT_EXCEPTION);
		}
		if (!(profitPoint.abs().compareTo(new BigDecimal(0)) > 0
				&& profitPoint.abs().compareTo(new BigDecimal(1)) < 0)) {
			throw new ServiceException(ExceptionConstant.ARGUMENT_EXCEPTION);
		}
		if (!(lossPoint.abs().compareTo(new BigDecimal(0)) > 0 && lossPoint.abs().compareTo(new BigDecimal(1)) < 0)) {
			throw new ServiceException(ExceptionConstant.ARGUMENT_EXCEPTION);
		}
		if (deferred && (deferredFee == null || deferredFee.compareTo(new BigDecimal(0)) <= 0)) {
			throw new ServiceException(ExceptionConstant.ARGUMENT_EXCEPTION);
		}
		// 验证支付密码
		CapitalAccountDto capitalAccount = capitalAccountBusiness.findByPublisherId(SecurityUtil.getUserId());
		String storePaymentPassword = capitalAccount.getPaymentPassword();
		if (storePaymentPassword == null || "".equals(storePaymentPassword)) {
			throw new ServiceException(ExceptionConstant.PAYMENTPASSWORD_NOTSET_EXCEPTION);
		}
		if (!PasswordCrypt.match(paymentPassword, storePaymentPassword)) {
			throw new ServiceException(ExceptionConstant.PAYMENTPASSWORD_WRONG_EXCEPTION);
		}
		// 检查余额
		BigDecimal totalFee = new BigDecimal(0);
		if (deferred) {
			totalFee = totalFee.add(serviceFee).add(reserveFund).add(deferredFee);
		} else {
			totalFee = totalFee.add(serviceFee).add(reserveFund);
		}
		if (totalFee.compareTo(capitalAccount.getAvailableBalance()) > 0) {
			throw new ServiceException(ExceptionConstant.AVAILABLE_BALANCE_NOTENOUGH_EXCEPTION);
		}
		if(strategyTypeId.longValue() == 3) {
			experienceBusiness.join();
		}
		// 初始化点买数据
		BuyRecordDto dto = new BuyRecordDto();
		dto.setStrategyTypeId(strategyTypeId);
		dto.setApplyAmount(applyAmount);
		dto.setServiceFee(serviceFee);
		dto.setReserveFund(reserveFund);
		dto.setProfitPoint(profitPoint);
		dto.setLossPoint(lossPoint.abs().multiply(new BigDecimal(-1)));
		dto.setLossMultiple(lossMultiple);
		dto.setStockCode(stockCode);
		dto.setDeferred(deferred);
		dto.setDeferredFee(deferred ? deferredFee : new BigDecimal(0));
		dto.setDelegatePrice(delegatePrice);
		// 设置对应的publisher
		dto.setPublisherId(SecurityUtil.getUserId());
		dto.setPublisherSerialCode(SecurityUtil.getSerialCode());
		BuyRecordDto buyRecordDto = buyRecordBusiness.buy(dto);
		return new Response<>(buyRecordBusiness.wrapMarketInfo(buyRecordDto));
	}

	@GetMapping("/pagesHoldPosition")
	@ApiOperation(value = "持仓中的点买记录列表")
	public Response<PageInfo<BuyRecordWithMarketDto>> pagesHoldPosition(int page, int size) {
		BuyRecordQuery query = new BuyRecordQuery(page, size, SecurityUtil.getUserId(),
				new BuyRecordState[] { BuyRecordState.POSTED, BuyRecordState.BUYLOCK, BuyRecordState.HOLDPOSITION,
						BuyRecordState.SELLAPPLY, BuyRecordState.SELLLOCK });
		PageInfo<BuyRecordDto> pageInfo = buyRecordBusiness.pages(query);
		List<BuyRecordWithMarketDto> content = buyRecordBusiness.wrapMarketInfo(pageInfo.getContent());
		return new Response<>(new PageInfo<>(content, pageInfo.getTotalPages(), pageInfo.getLast(),
				pageInfo.getTotalElements(), pageInfo.getSize(), pageInfo.getNumber(), pageInfo.getFrist()));
	}

	@GetMapping("/pagesUnwind")
	@ApiOperation(value = "结算的点买记录列表")
	public Response<PageInfo<BuyRecordWithMarketDto>> pagesUnwind(int page, int size) {
		BuyRecordQuery query = new BuyRecordQuery(page, size, SecurityUtil.getUserId(),
				new BuyRecordState[] { BuyRecordState.UNWIND, BuyRecordState.REVOKE });
		return new Response<>(buyRecordBusiness.pagesUnwind(query));
	}

	@GetMapping("/tradeDynamic")
	@ApiOperation(value = "交易动态列表")
	public Response<PageInfo<TradeDynamicDto>> tradeDynamic(int page, int size) {
		return new Response<>(buyRecordBusiness.tradeDynamic(page, size));
	}

	@RequestMapping(value = "/sellapply/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "用户申请卖出")
	public Response<BuyRecordDto> sellapply(@PathVariable("id") Long id) {
		// 检查交易时间段
		boolean isTradeTime = holidayBusiness.isTradeTime();
		if (!isTradeTime) {
			throw new ServiceException(ExceptionConstant.BUYRECORD_NONTRADINGPERIOD_EXCEPTION);
		}
		return new Response<>(buyRecordBusiness.sellApply(SecurityUtil.getUserId(), id));
	}

}
