package com.waben.stock.applayer.tactics.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
import org.springframework.web.client.RestTemplate;

import com.waben.stock.applayer.tactics.business.CapitalAccountBusiness;
import com.waben.stock.applayer.tactics.business.OrganizationPublisherBusiness;
import com.waben.stock.applayer.tactics.business.PublisherBusiness;
import com.waben.stock.applayer.tactics.business.RealNameBusiness;
import com.waben.stock.applayer.tactics.business.StockBusiness;
import com.waben.stock.applayer.tactics.business.StockOptionCycleBusiness;
import com.waben.stock.applayer.tactics.business.StockOptionQuoteBusiness;
import com.waben.stock.applayer.tactics.business.StockOptionTradeBusiness;
import com.waben.stock.applayer.tactics.dto.stockoption.StockOptionQuoteWithBalanceDto;
import com.waben.stock.applayer.tactics.dto.stockoption.StockOptionTradeDynamicDto;
import com.waben.stock.applayer.tactics.dto.stockoption.StockOptionTradeWithMarketDto;
import com.waben.stock.applayer.tactics.security.SecurityUtil;
import com.waben.stock.interfaces.commonapi.retrivestock.RetriveStockOverHttp;
import com.waben.stock.interfaces.commonapi.retrivestock.bean.StockMarket;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.organization.OrganizationPublisherDto;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionCycleDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionQuoteDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;
import com.waben.stock.interfaces.enums.StockOptionBuyingType;
import com.waben.stock.interfaces.enums.StockOptionTradeState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StockOptionTradeUserQuery;
import com.waben.stock.interfaces.util.PasswordCrypt;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 期权交易 Controller
 * 
 * @author luomengan
 *
 */
@RestController("tacticsStockOptionTradeController")
@RequestMapping("/stockoptiontrade")
@Api(description = "期权交易")
public class StockOptionTradeController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private StockOptionCycleBusiness cycleBusiness;

	@Autowired
	private StockOptionQuoteBusiness quoteBusiness;

	@Autowired
	private CapitalAccountBusiness accountBusiness;

	@Autowired
	private StockOptionTradeBusiness tradeBusiness;

	@Autowired
	private CapitalAccountBusiness capitalAccountBusiness;

	@Autowired
	private StockBusiness stockBusiness;

	@Autowired
	private RealNameBusiness realNameBusiness;

	@Autowired
	private PublisherBusiness publisherBusiness;

	@Autowired
	private OrganizationPublisherBusiness orgPublisherBusiness;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/cyclelists")
	@ApiOperation(value = "期权周期列表")
	public Response<List<StockOptionCycleDto>> lists(@RequestParam(required = false) String stockCode) {
		return new Response<>(cycleBusiness.lists(stockCode));
	}

	@GetMapping("/{stockCode}/{cycle}/quote")
	@ApiOperation(value = "询价")
	public Response<StockOptionQuoteWithBalanceDto> quote(@PathVariable("stockCode") String stockCode,
			@PathVariable("cycle") Integer cycle, @RequestParam(required = false) BigDecimal nominalAmount) {
		StockOptionCycleDto cycleDto = cycleBusiness.fetchByCycle(cycle);
		stockBusiness.checkStockOpton(stockCode, cycleDto.getId(), nominalAmount);
		StockOptionQuoteDto quote = quoteBusiness.quote(SecurityUtil.getUserId(), stockCode, cycle, nominalAmount);
		if (quote == null) {
			throw new ServiceException(ExceptionConstant.STOCKOPTION_QUOTENOTFOUND_EXCEPTION);
		}
		StockOptionQuoteWithBalanceDto resultQuote = new StockOptionQuoteWithBalanceDto(quote);
		resultQuote
				.setAvailableBalance(accountBusiness.findByPublisherId(SecurityUtil.getUserId()).getAvailableBalance());
		return new Response<>(resultQuote);
	}

	@PostMapping("/buy")
	@ApiOperation(value = "申购", notes = "buyingType买入方式:1市价买入")
	public Response<StockOptionTradeWithMarketDto> buy(@RequestParam(required = true) Integer buyingType,
			@RequestParam(required = true) Long cycleId, @RequestParam(required = true) BigDecimal nominalAmount,
			@RequestParam(required = true) String stockCode, @RequestParam(required = true) String paymentPassword) {
		Long publisherId = SecurityUtil.getUserId();
    	synchronized (String.valueOf(publisherId).intern()) {
    		logger.info("APP调用接口发布人{}申购期权{}，名义本金{}!", SecurityUtil.getUserId(), stockCode, nominalAmount);
    		// 检查股票是否可以购买，停牌、涨停、跌停不能购买
    		stockBusiness.checkStockOpton(stockCode, cycleId, nominalAmount);
    		// 判断是否连续两个涨停
    		stockBusiness.check2LimitUp(stockCode);
    		// 判断名义本金是否大于5万，且是否是5万的整数倍
    		if (nominalAmount.compareTo(new BigDecimal("50000")) < 0) {
    			throw new ServiceException(ExceptionConstant.STOCKOPTION_AMOUNTMUSTGT20WAN_EXCEPTION);
    		}
    		BigDecimal[] checkNominal = nominalAmount.divideAndRemainder(new BigDecimal("50000"));
    		if (checkNominal[1].compareTo(new BigDecimal("0")) != 0) {
    			throw new ServiceException(ExceptionConstant.STOCKOPTION_AMOUNTMUSTGT20WAN_EXCEPTION);
    		}
    		// 获取股票、期权周期、报价
    		StockDto stock = stockBusiness.findByCode(stockCode);
    		StockOptionCycleDto cycle = cycleBusiness.fetchById(cycleId);
    		StockOptionQuoteDto quote = quoteBusiness.quote(SecurityUtil.getUserId(), stockCode, cycle.getCycle(),
    				nominalAmount);
    		if (quote == null) {
    			throw new ServiceException(ExceptionConstant.STOCKOPTION_QUOTENOTFOUND_EXCEPTION);
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
    		BigDecimal rightMoney = nominalAmount.multiply(quote.getRightMoneyRatio()).setScale(2, RoundingMode.HALF_EVEN);
    		if (rightMoney.compareTo(capitalAccount.getAvailableBalance()) > 0) {
    			throw new ServiceException(ExceptionConstant.AVAILABLE_BALANCE_NOTENOUGH_EXCEPTION);
    		}
    		// 初始化点买数据
    		StockOptionTradeDto dto = new StockOptionTradeDto();
    		dto.setBuyingType(StockOptionBuyingType.getByIndex(String.valueOf(buyingType)));
    		dto.setNominalAmount(nominalAmount);
    		dto.setPublisherId(SecurityUtil.getUserId());
    		dto.setPublisherPhone(SecurityUtil.getUsername());
    		dto.setRightMoney(rightMoney);
    		dto.setCycleId(cycleId);
    		dto.setCycleMonth(cycle.getCycleMonth());
    		dto.setCycle(cycle.getCycle());
    		dto.setCycleName(cycle.getName());
    		dto.setRightMoneyRatio(quote.getRightMoneyRatio());
    		dto.setStockCode(stockCode);
    		dto.setStockName(stock.getName());
    		// 根据市场价格计算持股数（手数）
    		List<String> codes = new ArrayList<>();
    		codes.add(stockCode);
    		StockMarket market = RetriveStockOverHttp.listStockMarket(restTemplate, codes).get(0);
    		BigDecimal numberOfStrand = nominalAmount.divide(market.getLastPrice().multiply(new BigDecimal("100.5")), 0, RoundingMode.DOWN);
    		dto.setNumberOfStrand(numberOfStrand.intValue());
    		// 获取当前用户所属的推广代理商
    		OrganizationPublisherDto orgPublisher = orgPublisherBusiness.fetchOrgPublisher(SecurityUtil.getUserId());
    		if (orgPublisher != null) {
    			dto.setPromotionOrgId(orgPublisher.getOrgId());
    		}
    		// 获取是否为测试单
    		PublisherDto publisher = publisherBusiness.findById(SecurityUtil.getUserId());
    		dto.setIsTest(publisher.getIsTest());
    		//买入时最新价格
    		dto.setBuyingLastPrice(market.getLastPrice());
    		StockOptionTradeDto tradeDto = tradeBusiness.add(dto);
    		return new Response<>(tradeBusiness.wrapMarketInfo(tradeDto));
    	}
	}

	@GetMapping("/pagesHoldPosition")
	@ApiOperation(value = "持仓中的期权记录列表")
	public Response<PageInfo<StockOptionTradeWithMarketDto>> pagesHoldPosition(int page, int size) {
		StockOptionTradeUserQuery query = new StockOptionTradeUserQuery(page, size, SecurityUtil.getUserId(),
				new StockOptionTradeState[] { StockOptionTradeState.WAITCONFIRMED, StockOptionTradeState.TURNOVER,
						StockOptionTradeState.APPLYRIGHT, StockOptionTradeState.INSETTLEMENT,
						StockOptionTradeState.AUTOEXPIRE });
		PageInfo<StockOptionTradeDto> pageInfo = tradeBusiness.pagesByUserQuery(query);
		List<StockOptionTradeWithMarketDto> content = tradeBusiness.wrapMarketInfo(pageInfo.getContent());
		return new Response<>(new PageInfo<>(content, pageInfo.getTotalPages(), pageInfo.getLast(),
				pageInfo.getTotalElements(), pageInfo.getSize(), pageInfo.getNumber(), pageInfo.getFrist()));
	}

	@GetMapping("/pagesUnwind")
	@ApiOperation(value = "结算的期权记录列表")
	public Response<PageInfo<StockOptionTradeWithMarketDto>> pagesUnwind(int page, int size) {
		StockOptionTradeUserQuery query = new StockOptionTradeUserQuery(page, size, SecurityUtil.getUserId(),
				new StockOptionTradeState[] { StockOptionTradeState.FAILURE, StockOptionTradeState.SETTLEMENTED });
		PageInfo<StockOptionTradeDto> pageInfo = tradeBusiness.pagesByUserQuery(query);
		List<StockOptionTradeWithMarketDto> content = tradeBusiness.wrapMarketInfo(pageInfo.getContent());
		return new Response<>(new PageInfo<>(content, pageInfo.getTotalPages(), pageInfo.getLast(),
				pageInfo.getTotalElements(), pageInfo.getSize(), pageInfo.getNumber(), pageInfo.getFrist()));
	}

	@RequestMapping(value = "/userright/{id}", method = RequestMethod.POST)
	@ApiOperation(value = "用户主动行权")
	public Response<StockOptionTradeDto> userRight(@PathVariable("id") Long id) {
		return new Response<>(tradeBusiness.userRight(SecurityUtil.getUserId(), id));
	}

	@GetMapping("/tradeDynamic")
	@ApiOperation(value = "交易动态列表")
	public Response<PageInfo<StockOptionTradeDynamicDto>> tradeDynamic(int page, int size) {
		PageInfo<StockOptionTradeDynamicDto> result = tradeBusiness.tradeDynamic(page, size); 
		if(result != null && result.getContent() != null && result.getContent().size() > 0) {
			for(StockOptionTradeDynamicDto dynamic : result.getContent()) {
				if(dynamic.getNominalAmount().compareTo(new BigDecimal("500000")) <= 0) {
					dynamic.setNominalAmount(dynamic.getNominalAmount().multiply(new BigDecimal(5)));
				}
				if(dynamic.getProfit() != null && dynamic.getProfit().compareTo(new BigDecimal("10000")) <= 0) {
					dynamic.setProfit(dynamic.getProfit().subtract(new BigDecimal("1.1")).multiply(new BigDecimal(5)));
				}
				if(dynamic.retriveOriginPhone() != null) {
					dynamic.setPhone(randomPhone(dynamic.retriveOriginPhone()));
				}
			}
		}
		return new Response<>(result);
	}
	
	private String randomPhone(String phone) {
		StringBuilder builder = new StringBuilder(phone.substring(0, 2) + phone.charAt(Integer.parseInt(phone.substring(2, 3))) + "0000");
		builder.append(phone.charAt(Integer.parseInt(phone.substring(3, 4))));
		builder.append(phone.charAt(Integer.parseInt(phone.substring(4, 5))));
		builder.append(phone.charAt(Integer.parseInt(phone.substring(5, 6))));
		builder.append(phone.charAt(Integer.parseInt(phone.substring(6, 7))));
		return builder.toString();
	}

}
