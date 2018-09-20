package com.waben.stock.datalayer.stockoption.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.waben.stock.datalayer.stockoption.business.CapitalAccountBusiness;
import com.waben.stock.datalayer.stockoption.business.OrganizationSettlementBusiness;
import com.waben.stock.datalayer.stockoption.business.OutsideMessageBusiness;
import com.waben.stock.datalayer.stockoption.entity.OfflineStockOptionTrade;
import com.waben.stock.datalayer.stockoption.entity.StockOptionOrg;
import com.waben.stock.datalayer.stockoption.entity.StockOptionTrade;
import com.waben.stock.datalayer.stockoption.repository.DynamicQuerySqlDao;
import com.waben.stock.datalayer.stockoption.repository.OfflineStockOptionTradeDao;
import com.waben.stock.datalayer.stockoption.repository.StockOptionOrgDao;
import com.waben.stock.datalayer.stockoption.repository.StockOptionTradeDao;
import com.waben.stock.datalayer.stockoption.repository.impl.MethodDesc;
import com.waben.stock.datalayer.stockoption.web.StockQuotationHttp;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionAdminDto;
import com.waben.stock.interfaces.dto.promotion.stockoption.StockOptionPromotionDto;
import com.waben.stock.interfaces.dto.promotion.stockoption.StockOptionStaDto;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.enums.OfflineStockOptionTradeState;
import com.waben.stock.interfaces.enums.OutsideMessageType;
import com.waben.stock.interfaces.enums.ResourceType;
import com.waben.stock.interfaces.enums.StockOptionTradeState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.message.OutsideMessage;
import com.waben.stock.interfaces.pojo.query.StockOptionTradeQuery;
import com.waben.stock.interfaces.pojo.query.StockOptionTradeUserQuery;
import com.waben.stock.interfaces.pojo.query.admin.stockoption.StockOptionAdminQuery;
import com.waben.stock.interfaces.pojo.query.promotion.stockoption.StockOptionPromotionQuery;
import com.waben.stock.interfaces.pojo.stock.quotation.StockMarket;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.StringUtil;
import com.waben.stock.interfaces.util.UniqueCodeGenerator;

@Service
public class StockOptionTradeService {

	Logger logger = LoggerFactory.getLogger(getClass());

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private StockOptionTradeDao stockOptionTradeDao;

	@Autowired
	private CapitalAccountBusiness accountBusiness;

	@Autowired
	private OutsideMessageBusiness outsideMessageBusiness;

	@Autowired
	private OrganizationSettlementBusiness orgSettlementBusiness;

	@Autowired
	private DynamicQuerySqlDao sqlDao;

	@Autowired
	private OfflineStockOptionTradeDao offlineTradeDao;

	@Autowired
	private StockOptionOrgDao optionOrgDao;

	@Autowired
	private StockQuotationHttp stockQuotationHttp;

	public Page<StockOptionTrade> tradeDynamicQuery(final StockOptionTradeUserQuery query) {
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<StockOptionTrade> pages = stockOptionTradeDao.page(new Specification<StockOptionTrade>() {
			@Override
			public Predicate toPredicate(Root<StockOptionTrade> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (query.getStates() != null && query.getStates().length > 0) {
					predicateList.add(root.get("state").in(query.getStates()));
				}
				if (query.getPublisherId() != null && query.getPublisherId() > 0) {
					predicateList
							.add(criteriaBuilder.equal(root.get("publisherId").as(Long.class), query.getPublisherId()));
				}
				if (query.isOnlyProfit()) {
					predicateList.add(criteriaBuilder.gt(root.get("profit").as(BigDecimal.class), new BigDecimal(5000)));
				}
				if (query.getStartApplyTime() != null) {
					predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("applyTime").as(Date.class),
							query.getStartApplyTime()));
				}
				if (query.getEndApplyTime() != null) {
					predicateList.add(
							criteriaBuilder.lessThan(root.get("applyTime").as(Date.class), query.getEndApplyTime()));
				}
				if (predicateList.size() > 0) {
					criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
				}
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get("sellingTime").as(Date.class)),
						criteriaBuilder.desc(root.get("buyingTime").as(Date.class)));
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}

	public Page<StockOptionTrade> pagesByUserQuery(final StockOptionTradeUserQuery query) {
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<StockOptionTrade> pages = stockOptionTradeDao.page(new Specification<StockOptionTrade>() {
			@Override
			public Predicate toPredicate(Root<StockOptionTrade> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (query.getStates() != null && query.getStates().length > 0) {
					predicateList.add(root.get("state").in(query.getStates()));
				}
				if (query.getPublisherId() != null && query.getPublisherId() > 0) {
					predicateList
							.add(criteriaBuilder.equal(root.get("publisherId").as(Long.class), query.getPublisherId()));
				}
				if (query.isOnlyProfit()) {
					predicateList.add(criteriaBuilder.gt(root.get("profit").as(BigDecimal.class), new BigDecimal(0)));
				}
				if (query.getStartApplyTime() != null) {
					predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("applyTime").as(Date.class),
							query.getStartApplyTime()));
				}
				if (query.getEndApplyTime() != null) {
					predicateList.add(
							criteriaBuilder.lessThan(root.get("applyTime").as(Date.class), query.getEndApplyTime()));
				}
				if (predicateList.size() > 0) {
					criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
				}
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get("sellingTime").as(Date.class)),
						criteriaBuilder.desc(root.get("rightTime").as(Date.class)),
						criteriaBuilder.desc(root.get("buyingTime").as(Date.class)),
						criteriaBuilder.desc(root.get("applyTime").as(Date.class)));
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}

	@Transactional
	public StockOptionTrade save(StockOptionTrade stockOptionTrade) {
		// 再检查一余额是否充足
		CapitalAccountDto account = accountBusiness.fetchByPublisherId(stockOptionTrade.getPublisherId());
		if (account.getAvailableBalance().compareTo(stockOptionTrade.getRightMoney()) < 0) {
			throw new ServiceException(ExceptionConstant.AVAILABLE_BALANCE_NOTENOUGH_EXCEPTION);
		}
		stockOptionTrade.setTradeNo(UniqueCodeGenerator.generateTradeNo());
		stockOptionTrade.setState(StockOptionTradeState.WAITCONFIRMED);
		Date date = new Date();
		stockOptionTrade.setApplyTime(date);
		stockOptionTrade.setUpdateTime(date);
		stockOptionTradeDao.create(stockOptionTrade);
		// 扣去权利金
		try {
			accountBusiness.rightMoney(stockOptionTrade.getPublisherId(), stockOptionTrade.getId(),
					stockOptionTrade.getRightMoney());
		} catch (ServiceException ex) {
			if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(ex.getType())) {
				try {
					// TODO 再一次确认是否已经扣款
				} catch (ServiceException frozenEx) {
					throw ex;
				}
			} else {
				throw ex;
			}
		}
		// 站外消息推送
		sendOutsideMessage(stockOptionTrade);
		return stockOptionTrade;
	}

	@Transactional
	public StockOptionTrade userRight(Long publisherId, Long id) {
		StockOptionTrade trade = findById(id);
		if (trade.getState() != StockOptionTradeState.TURNOVER) {
			throw new ServiceException(ExceptionConstant.STOCKOPTION_STATE_NOTMATCH_OPERATION_NOTSUPPORT_EXCEPTION);
		}
		if (!trade.getPublisherId().equals(publisherId)) {
			throw new ServiceException(ExceptionConstant.STOCKOPTION_PUBLISHERID_NOTMATCH_EXCEPTION);
		}
		trade.setRightTime(new Date());
		trade.setState(StockOptionTradeState.APPLYRIGHT);
		stockOptionTradeDao.update(trade);
		// 站外消息推送
		sendOutsideMessage(trade);
		return trade;
	}

	public Page<StockOptionTrade> pagesByQuery(final StockOptionTradeQuery query) {
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<StockOptionTrade> pages = stockOptionTradeDao.page(new Specification<StockOptionTrade>() {
			@Override
			public Predicate toPredicate(Root<StockOptionTrade> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicatesList = new ArrayList<>();
				if (query.getBeginTime() != null) {
					predicatesList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("updateTime").as(Date.class),
							query.getBeginTime()));
				}
				if (query.getEndTime() != null) {
					predicatesList
							.add(criteriaBuilder.lessThan(root.get("updateTime").as(Date.class), query.getEndTime()));
				}
				if (!StringUtils.isEmpty(query.getPublisherPhone())) {
					Predicate publisherPhoneQuery = criteriaBuilder.equal(root.get("publisherPhone").as(Long.class),
							query.getPublisherPhone());
					predicatesList.add(publisherPhoneQuery);
				}
				if (!StringUtils.isEmpty(query.getStockCode())) {
					Predicate stockCodeQuery = criteriaBuilder.equal(root.get("stockCode").as(String.class),
							query.getStockCode());
					predicatesList.add(stockCodeQuery);
				}
				if (!StringUtils.isEmpty(query.getPublisherIds())) {
					predicatesList.add(root.get("publisherId").in(query.getPublisherIds()));
				}
				if (!StringUtils.isEmpty(query.getCycleName())) {
					Predicate cycleNameQuery = criteriaBuilder.equal(root.get("cycleName").as(String.class),
							query.getCycleName());
					predicatesList.add(cycleNameQuery);
				}
				if (!StringUtils.isEmpty(query.getNominalAmount())) {
					Predicate nominalAmountQuery = criteriaBuilder.equal(root.get("nominalAmount").as(BigDecimal.class),
							query.getNominalAmount());
					predicatesList.add(nominalAmountQuery);
				}
				if (!StringUtils.isEmpty(query.getTradeNo())) {
					Predicate applyNoQuery = criteriaBuilder.equal(root.get("tradeNo").as(String.class),
							query.getTradeNo());
					predicatesList.add(applyNoQuery);
				}
				if (!StringUtils.isEmpty(query.getState())) {
					Predicate stateQuery = criteriaBuilder.equal(root.get("state").as(Integer.class), query.getState());
					predicatesList.add(stateQuery);
				}
				criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
				criteriaQuery.orderBy(criteriaBuilder.desc(root.<Date>get("updateTime").as(Date.class)));
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}

	public StockOptionTrade findById(Long id) {
		return stockOptionTradeDao.retrieve(id);
	}

	@Transactional
	@Deprecated
	public StockOptionTrade settlement(Long id) {
		StockOptionTrade trade = stockOptionTradeDao.retrieve(id);
		if (StockOptionTradeState.SETTLEMENTED == trade.getState()) {
			throw new ServiceException(ExceptionConstant.STOCKOPTION_STATE_NOTMATCH_OPERATION_NOTSUPPORT_EXCEPTION);
		}
		BigDecimal sellingPrice = trade.getOfflineTrade().getSellingPrice();
		trade.setSellingPrice(sellingPrice);
		trade.setSellingTime(new Date());
		trade.setState(StockOptionTradeState.SETTLEMENTED);
		BigDecimal profit = BigDecimal.ZERO;
		if (sellingPrice.compareTo(trade.getBuyingPrice()) > 0) {
			profit = sellingPrice.subtract(trade.getBuyingPrice()).divide(trade.getBuyingPrice(), 10, RoundingMode.DOWN)
					.multiply(trade.getNominalAmount()).setScale(2, RoundingMode.HALF_EVEN);
		}
		trade.setProfit(profit);
		trade.setUpdateTime(new Date());
		stockOptionTradeDao.update(trade);
		if (profit.compareTo(BigDecimal.ZERO) > 0) {
			// 用户收益
			accountBusiness.optionProfit(trade.getPublisherId(), trade.getId(), profit);
		}
		// 给机构结算
		if (trade.getOfflineTrade().getRightMoney() != null) {
			BigDecimal rightMoneyProfit = trade.getRightMoney().subtract(trade.getOfflineTrade().getRightMoney());
			orgSettlementBusiness.stockoptionSettlement(trade.getPublisherId(), trade.getId(), trade.getTradeNo(),
					trade.getCycleId(), rightMoneyProfit, trade.getRightMoney());
		}
		// 站外消息推送
		sendOutsideMessage(trade);
		return trade;
	}

	@Deprecated
	@Transactional
	public StockOptionTrade success(Long id) {
		StockOptionTrade trade = stockOptionTradeDao.retrieve(id);
		BigDecimal buyingPrice = trade.getOfflineTrade().getBuyingPrice();
		if (StockOptionTradeState.WAITCONFIRMED != trade.getState()) {
			throw new ServiceException(ExceptionConstant.STOCKOPTION_STATE_NOTMATCH_OPERATION_NOTSUPPORT_EXCEPTION);
		}
		trade.setState(StockOptionTradeState.TURNOVER);
		trade.setBuyingPrice(buyingPrice);
		Date date = new Date();
		trade.setBuyingTime(date);
		try {
			// 计算到期日期
			Date beginTime = sdf.parse(sdf.format(date));
			long after = 24 * 60 * 60 * 1000;
			after *= (trade.getCycle() - 1);
			Date expireTime = new Date(beginTime.getTime() + after);
			trade.setExpireTime(expireTime);
		} catch (ParseException e) {
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION);
		}
		trade.setUpdateTime(new Date());
		StockOptionTrade result = stockOptionTradeDao.update(trade);
		// 站外消息推送
		sendOutsideMessage(result);
		return result;
	}

	@Transactional
	public StockOptionTrade modify(Long id) {
		StockOptionTrade stockOptionTrade = stockOptionTradeDao.retrieve(id);
		OfflineStockOptionTradeState next = OfflineStockOptionTradeState.INQUIRY;
		if (stockOptionTrade.getStatus() == null) {
			next = OfflineStockOptionTradeState.INQUIRY;
		}
		if (stockOptionTrade.getStatus() == OfflineStockOptionTradeState.INQUIRY) {
			next = OfflineStockOptionTradeState.PURCHASE;
		}
		if (stockOptionTrade.getStatus() == OfflineStockOptionTradeState.PURCHASE) {
			next = OfflineStockOptionTradeState.TURNOVER;
		}
		if (stockOptionTrade.getStatus() == OfflineStockOptionTradeState.TURNOVER) {
			next = OfflineStockOptionTradeState.APPLYRIGHT;
		}
		if (stockOptionTrade.getStatus() == OfflineStockOptionTradeState.APPLYRIGHT) {
			next = OfflineStockOptionTradeState.INSETTLEMENT;
		}
		if (stockOptionTrade.getStatus() == OfflineStockOptionTradeState.INSETTLEMENT) {
			next = OfflineStockOptionTradeState.SETTLEMENTED;
		}
		stockOptionTrade.setStatus(next);
		StockOptionTrade result = stockOptionTradeDao.update(stockOptionTrade);
		return result;
	}

	@Transactional
	public StockOptionTrade fail(Long id) {
		StockOptionTrade trade = stockOptionTradeDao.retrieve(id);
		if (StockOptionTradeState.WAITCONFIRMED != trade.getState()) {
			throw new ServiceException(ExceptionConstant.STOCKOPTION_STATE_NOTMATCH_OPERATION_NOTSUPPORT_EXCEPTION);
		}
		trade.setState(StockOptionTradeState.FAILURE);
		stockOptionTradeDao.update(trade);
		// 退回权利金
		accountBusiness.returnRightMoney(trade.getPublisherId(), trade.getId(), trade.getRightMoney());
		// 站外消息推送
		sendOutsideMessage(trade);
		return trade;
	}

	private void sendOutsideMessage(final StockOptionTrade trade) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					StockOptionTradeState state = trade.getState();
					Map<String, String> extras = new HashMap<>();
					OutsideMessage message = new OutsideMessage();
					message.setPublisherId(trade.getPublisherId());
					message.setTitle("申购通知");
					extras.put("title", message.getTitle());
					extras.put("publisherId", String.valueOf(trade.getPublisherId()));
					extras.put("resourceType", ResourceType.STOCKOPTIONTRADE.getIndex());
					extras.put("resourceId", String.valueOf(trade.getId()));
					message.setExtras(extras);
					switch (state) {
					case WAITCONFIRMED:
						message.setContent(String.format("您申购的“%s %s”已进入“待确认”状态", trade.getStockName(), trade.getStockCode()));
						extras.put("content", String.format("您申购的“<span id=\"stock\">%s %s</span>”已进入“待确认”状态",
								trade.getStockName(), trade.getStockCode()));
						extras.put("type", OutsideMessageType.OPTION_WAITCONFIRMED.getIndex());
						break;
					case FAILURE:
						message.setContent(String.format("您申购的“%s %s”申购失败，权利金已退回至您的账户，请留意账户余额", trade.getStockName(),
								trade.getStockCode()));
						extras.put("content", String.format("您申购的“<span id=\"stock\">%s %s</span>”申购失败，策略风控服务费已退回至您的账户，请留意账户余额",
								trade.getStockName(), trade.getStockCode()));
						extras.put("type", OutsideMessageType.OPTION_FAILURE.getIndex());
						break;
					case TURNOVER:
						message.setContent(String.format("您申购的“%s %s”已进入“持仓中”状态", trade.getStockName(), trade.getStockCode()));
						extras.put("content", String.format("您申购的“<span id=\"stock\">%s %s</span>”已进入“持仓中”状态",
								trade.getStockName(), trade.getStockCode()));
						extras.put("type", OutsideMessageType.OPTION_TURNOVER.getIndex());
						break;
					case INSETTLEMENT:
						message.setContent(String.format("您申购的“%s %s”已进入“结算中”状态", trade.getStockName(), trade.getStockCode()));
						extras.put("content", String.format("您申购的“<span id=\"stock\">%s %s</span>”已进入“结算中”状态",
								trade.getStockName(), trade.getStockCode()));
						extras.put("type", OutsideMessageType.OPTION_INSETTLEMENT.getIndex());
						break;
					case SETTLEMENTED:
						message.setContent(String.format("您申购的“%s %s”已进入“已结算”状态", trade.getStockName(), trade.getStockCode()));
						extras.put("content", String.format("您申购的“<span id=\"stock\">%s %s</span>”已进入“已结算”状态",
								trade.getStockName(), trade.getStockCode()));
						extras.put("type", OutsideMessageType.OPTION_SETTLEMENTED.getIndex());
						break;
					default:
						break;
					}
					if (message.getContent() != null) {
						System.out.println(JacksonUtil.encode(message));
						outsideMessageBusiness.send(message);
					}
				} catch (Exception ex) {
					logger.error("发送期权申购通知失败，{}_{}_{}", trade.getId(), trade.getState().getState(), ex.getMessage());
				}
			}
		}).start();
	}

	public List<StockOptionTrade> fetchByState(StockOptionTradeState stockOptionTradeState) {
		List<StockOptionTrade> result = stockOptionTradeDao.retieveByState(stockOptionTradeState);
		return result;
	}

	public StockOptionTrade dueTreatmentExercise(Long id) {
		StockOptionTrade stockOptionTrade = stockOptionTradeDao.retrieve(id);
		stockOptionTrade.setRightTime(new Date());
		return stockOptionTradeDao.update(stockOptionTrade);
	}

	public Page<StockOptionAdminDto> adminPagesByQuery(StockOptionAdminQuery query) {
		String publisherNameCondition = "";
		if (!StringUtil.isEmpty(query.getPublisherName())) {
			publisherNameCondition = " and t4.name like '%" + query.getPublisherName() + "%' ";
		}
		String publisherPhoneCondition = "";
		if (!StringUtil.isEmpty(query.getPublisherPhone())) {
			publisherPhoneCondition = " and t3.phone like '%" + query.getPublisherPhone() + "%' ";
		}
		String stockCodeCondition = "";
		if (!StringUtil.isEmpty(query.getStockCode())) {
			stockCodeCondition = " and t1.stock_code like '%" + query.getStockCode() + "%' ";
		}
		String nominalAmountCondition = "";
		if (query.getNominalAmount() != null) {
			stockCodeCondition = " and t1.nominal_amount='" + query.getNominalAmount() + "' ";
		}
		String cycleNameCondition = "";
		if (!StringUtil.isEmpty(query.getCycleId())) {
			cycleNameCondition = " and t1.cycle_id='" + query.getCycleId() + "' ";
		}
		String stateCondition = "";
		if (!StringUtil.isEmpty(query.getState()) && !"0".equals(query.getState().trim())) {
			stateCondition = " and t1.state in(" + query.getState() + ") ";
		}
		String isTestCondition = "";
		if (query.getIsTest() != null) {
			if (query.getIsTest()) {
				isTestCondition = " and t1.is_test=1 ";
			} else {
				isTestCondition = " and (t1.is_test is null or t1.is_test=0) ";
			}
		}
		String isMarkCondition = "";
		if (query.getIsMark() != null) {
			if (query.getIsMark()) {
				isMarkCondition = " and t1.is_mark=1 ";
			} else {
				isMarkCondition = " and (t1.is_mark is null or t1.is_mark=0) ";
			}
		}
		String startTimeCondition = "";
		if (query.getStartTime() != null) {
			startTimeCondition = " and t1.apply_time>='" + sdf.format(query.getStartTime()) + "' ";
		}
		String endTimeCondition = "";
		if (query.getEndTime() != null) {
			endTimeCondition = " and t1.apply_time<'" + sdf.format(query.getEndTime()) + "' ";
		}
		String startRatioCondition = "";
		if (query.getStartRatio() != null) {
			startRatioCondition = " and t1.right_money_ratio>='"
					+ query.getStartRatio().divide(new BigDecimal("100")).toString() + "' ";
		}
		String endRatioCondition = "";
		if (query.getEndRatio() != null) {
			endRatioCondition = " and t1.right_money_ratio<='"
					+ query.getEndRatio().divide(new BigDecimal("100")).toString() + "' ";
		}

		String orderBy = "t1.apply_time";
		if (query.getState() != null && query.getState().equals("5,6")) {
			orderBy = "t1.selling_time";
		}
		String sql = String.format(
				"select t1.id, t1.trade_no, t4.name, t3.phone, t1.stock_code, t1.stock_name, t1.cycle_name, t1.nominal_amount, t1.right_money_ratio, "
						+ "t1.right_money, t2.right_money_ratio as org_right_money_ratio, t2.right_money as org_right_money, t1.apply_time, t1.buying_time, t1.buying_price, t1.selling_time, t1.selling_price, "
						+ "t1.profit, t1.is_test, t1.is_mark, t1.state, t1.right_time, t1.number_of_strand,t1.buying_last_price from stock_option_trade t1 "
						+ "LEFT JOIN offline_stock_option_trade t2 on t1.offline_trade=t2.id "
						+ "LEFT JOIN publisher t3 on t1.publisher_id=t3.id "
						+ "LEFT JOIN real_name t4 on t4.resource_type=2 and t1.publisher_id=t4.resource_id "
						+ "where 1=1 %s %s %s %s %s %s %s %s %s %s %s %s order by " + orderBy + " desc limit "
						+ query.getPage() * query.getSize() + "," + query.getSize(),
				publisherNameCondition, publisherPhoneCondition, stockCodeCondition, nominalAmountCondition,
				cycleNameCondition, stateCondition, isTestCondition, isMarkCondition, startTimeCondition,
				endTimeCondition, startRatioCondition, endRatioCondition);
		String countSql = "select count(*) " + sql.substring(sql.indexOf("from"), sql.indexOf("limit"));
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setTradeNo", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setPublisherName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setPublisherPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setStockCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setStockName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setCycleName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setNominalAmount", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setRightMoneyRatio", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setRightMoney", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(10), new MethodDesc("setOrgRightMoneyRatio", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(11), new MethodDesc("setOrgRightMoney", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(12), new MethodDesc("setApplyTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(13), new MethodDesc("setBuyingTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(14), new MethodDesc("setBuyingPrice", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(15), new MethodDesc("setSellingTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(16), new MethodDesc("setSellingPrice", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(17), new MethodDesc("setProfit", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(18), new MethodDesc("setIsTest", new Class<?>[] { Boolean.class }));
		setMethodMap.put(new Integer(19), new MethodDesc("setIsMark", new Class<?>[] { Boolean.class }));
		setMethodMap.put(new Integer(20), new MethodDesc("setState", new Class<?>[] { StockOptionTradeState.class }));
		setMethodMap.put(new Integer(21), new MethodDesc("setRightTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(22), new MethodDesc("setNumberOfStrand", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(23), new MethodDesc("setBuyingLastPrice", new Class<?>[] { BigDecimal.class }));
		List<StockOptionAdminDto> content = sqlDao.execute(StockOptionAdminDto.class, sql, setMethodMap);
		BigInteger totalElements = sqlDao.executeComputeSql(countSql);
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()),
				totalElements != null ? totalElements.longValue() : 0);
	}

	public StockOptionStaDto promotionSta(StockOptionPromotionQuery query) {
		String publisherNameCondition = "";
		if (!StringUtil.isEmpty(query.getPublisherName())) {
			publisherNameCondition = " and t4.name like '%" + query.getPublisherName() + "%' ";
		}
		String publisherPhoneCondition = "";
		if (!StringUtil.isEmpty(query.getPublisherPhone())) {
			publisherPhoneCondition = " and t3.phone like '%" + query.getPublisherPhone() + "%' ";
		}
		String stockCodeOrNameCondition = "";
		if (!StringUtil.isEmpty(query.getStockCodeOrName())) {
			stockCodeOrNameCondition = " and (t1.stock_code like '%" + query.getStockCodeOrName()
					+ "%' or t1.stock_name like '%" + query.getStockCodeOrName() + "%') ";
		}
		String nominalAmountCondition = "";
		if (query.getNominalAmount() != null) {
			stockCodeOrNameCondition = " and t1.nominal_amount='" + query.getNominalAmount() + "' ";
		}
		String cycleNameCondition = "";
		if (!StringUtil.isEmpty(query.getCycleId())) {
			cycleNameCondition = " and t1.cycle_id='" + query.getCycleId() + "' ";
		}
		String stateCondition = "";
		if (!StringUtil.isEmpty(query.getState()) && !"0".equals(query.getState().trim())) {
			stateCondition = " and t1.state in(" + query.getState() + ") ";
		}
		String isTestCondition = "";
		if (query.getIsTest() != null) {
			if (query.getIsTest()) {
				isTestCondition = " and t1.is_test=1 ";
			} else {
				isTestCondition = " and (t1.is_test is null or t1.is_test=0) ";
			}
		}
		String isMarkCondition = "";
		if (query.getIsMark() != null) {
			if (query.getIsMark()) {
				isMarkCondition = " and t1.is_mark=1 ";
			} else {
				isMarkCondition = " and (t1.is_mark is null or t1.is_mark=0) ";
			}
		}
		String startTimeCondition = "";
		if (query.getStartTime() != null) {
			startTimeCondition = " and t1.apply_time>='" + sdf.format(query.getStartTime()) + "' ";
		}
		String endTimeCondition = "";
		if (query.getEndTime() != null) {
			endTimeCondition = " and t1.apply_time<'" + sdf.format(query.getEndTime()) + "' ";
		}
		String startRatioCondition = "";
		if (query.getStartRatio() != null) {
			startRatioCondition = " and t1.right_money_ratio>='"
					+ query.getStartRatio().divide(new BigDecimal("100")).toString() + "' ";
		}
		String endRatioCondition = "";
		if (query.getEndRatio() != null) {
			endRatioCondition = " and t1.right_money_ratio<='"
					+ query.getEndRatio().divide(new BigDecimal("100")).toString() + "' ";
		}
		String orgCodeOrNameConditon = "";
		if (!StringUtil.isEmpty(query.getOrgCodeOrName())) {
			orgCodeOrNameConditon = " and (t5.code like '%" + query.getOrgCodeOrName() + "%' or t5.name like '%"
					+ query.getOrgCodeOrName() + "%') ";
		}
		String treeCodeQuery = "";
		if (!StringUtil.isEmpty(query.getTreeCode())) {
			treeCodeQuery = " and t5.tree_code like '" + query.getTreeCode() + "%'";
		}

		String sql = String
				.format("select t1.id, t1.trade_no, t4.name, t3.phone, t1.stock_code, t1.stock_name, t1.cycle_name, t1.nominal_amount, t1.right_money_ratio, "
						+ "t1.right_money, t2.right_money_ratio as org_right_money_ratio, t2.right_money as org_right_money, t1.apply_time, t1.buying_time, t1.buying_price, t1.selling_time, t1.selling_price, "
						+ "t1.profit, t1.is_test, t1.is_mark, t1.state, t1.right_time, t5.id as org_id, t5.code as org_code, t5.name as org_name from stock_option_trade t1 "
						+ "LEFT JOIN offline_stock_option_trade t2 on t1.offline_trade=t2.id "
						+ "LEFT JOIN publisher t3 on t1.publisher_id=t3.id "
						+ "LEFT JOIN real_name t4 on t4.resource_type=2 and t1.publisher_id=t4.resource_id "
						+ "LEFT JOIN p_organization t5 on t5.id=t1.promotion_org_id "
						// + "LEFT JOIN p_organization t6 on t6.tree_code like
						// '%%" + query.getTreeCode() + "%%' "
						+ "where 1=1 %s %s %s %s %s %s %s %s %s %s %s %s %s %s order by t1.apply_time desc limit "
						+ query.getPage() * query.getSize() + "," + query.getSize(), publisherNameCondition,
						publisherPhoneCondition, stockCodeOrNameCondition, nominalAmountCondition, cycleNameCondition,
						stateCondition, isTestCondition, isMarkCondition, startTimeCondition, endTimeCondition,
						startRatioCondition, endRatioCondition, orgCodeOrNameConditon, treeCodeQuery);
		String sumSql1 = "select sum(x.nominal_amount) from (" + sql.substring(0, sql.indexOf("limit")) + ") x";
		String sumSql2 = "select sum(x.right_money) from (" + sql.substring(0, sql.indexOf("limit")) + ") x";
		BigDecimal totalNominalAmount = sqlDao.executeComputeSql(sumSql1);
		BigDecimal totalRightMoney = sqlDao.executeComputeSql(sumSql2);
		StockOptionStaDto result = new StockOptionStaDto();
		result.setTotalNominalAmount(totalNominalAmount != null ? totalNominalAmount : BigDecimal.ZERO);
		result.setTotalRightMoney(totalRightMoney != null ? totalRightMoney : BigDecimal.ZERO);
		return result;
	}

	public Page<StockOptionPromotionDto> promotionPagesByQuery(StockOptionPromotionQuery query) {
		String publisherNameCondition = "";
		if (!StringUtil.isEmpty(query.getPublisherName())) {
			publisherNameCondition = " and t4.name like '%" + query.getPublisherName() + "%' ";
		}
		String publisherPhoneCondition = "";
		if (!StringUtil.isEmpty(query.getPublisherPhone())) {
			publisherPhoneCondition = " and t3.phone like '%" + query.getPublisherPhone() + "%' ";
		}
		String stockCodeOrNameCondition = "";
		if (!StringUtil.isEmpty(query.getStockCodeOrName())) {
			stockCodeOrNameCondition = " and (t1.stock_code like '%" + query.getStockCodeOrName()
					+ "%' or t1.stock_name like '%" + query.getStockCodeOrName() + "%') ";
		}
		String nominalAmountCondition = "";
		if (query.getNominalAmount() != null) {
			stockCodeOrNameCondition = " and t1.nominal_amount='" + query.getNominalAmount() + "' ";
		}
		String cycleNameCondition = "";
		if (!StringUtil.isEmpty(query.getCycleId())) {
			cycleNameCondition = " and t1.cycle_id='" + query.getCycleId() + "' ";
		}
		String stateCondition = "";
		if (!StringUtil.isEmpty(query.getState()) && !"0".equals(query.getState().trim())) {
			stateCondition = " and t1.state in(" + query.getState() + ") ";
		}
		String isTestCondition = "";
		if (query.getIsTest() != null) {
			if (query.getIsTest()) {
				isTestCondition = " and t1.is_test=1 ";
			} else {
				isTestCondition = " and (t1.is_test is null or t1.is_test=0) ";
			}
		}
		String isMarkCondition = "";
		if (query.getIsMark() != null) {
			if (query.getIsMark()) {
				isMarkCondition = " and t1.is_mark=1 ";
			} else {
				isMarkCondition = " and (t1.is_mark is null or t1.is_mark=0) ";
			}
		}
		String startTimeCondition = "";
		if (query.getStartTime() != null) {
			startTimeCondition = " and t1.apply_time>='" + sdf.format(query.getStartTime()) + "' ";
		}
		String endTimeCondition = "";
		if (query.getEndTime() != null) {
			endTimeCondition = " and t1.apply_time<'" + sdf.format(query.getEndTime()) + "' ";
		}
		String startRatioCondition = "";
		if (query.getStartRatio() != null) {
			startRatioCondition = " and t1.right_money_ratio>='"
					+ query.getStartRatio().divide(new BigDecimal("100")).toString() + "' ";
		}
		String endRatioCondition = "";
		if (query.getEndRatio() != null) {
			endRatioCondition = " and t1.right_money_ratio<='"
					+ query.getEndRatio().divide(new BigDecimal("100")).toString() + "' ";
		}
		String orgCodeOrNameConditon = "";
		if (!StringUtil.isEmpty(query.getOrgCodeOrName())) {
			orgCodeOrNameConditon = " and (t5.code like '%" + query.getOrgCodeOrName() + "%' or t5.name like '%"
					+ query.getOrgCodeOrName() + "%') ";
		}
		String treeCodeQuery = "";
		if (!StringUtil.isEmpty(query.getTreeCode())) {
			treeCodeQuery = " and t5.tree_code like '" + query.getTreeCode() + "%'";
		}

		String sql = String.format(
				"select t1.id, t1.trade_no, t4.name, t3.phone, t1.stock_code, t1.stock_name, t1.cycle_name, t1.nominal_amount, t1.right_money_ratio, "
						+ "t1.right_money, t2.right_money_ratio as org_right_money_ratio, t2.right_money as org_right_money, t1.apply_time, t1.buying_time, t1.buying_price, t1.selling_time, t1.selling_price, "
						+ "t1.profit, t1.is_test, t1.is_mark, t1.state, t1.right_time, t5.id as org_id, t5.code as org_code, t5.name as org_name, t1.buying_last_price "
						+ "from stock_option_trade t1 "
						+ "LEFT JOIN offline_stock_option_trade t2 on t1.offline_trade=t2.id "
						+ "LEFT JOIN publisher t3 on t1.publisher_id=t3.id "
						+ "LEFT JOIN real_name t4 on t4.resource_type=2 and t1.publisher_id=t4.resource_id "
						+ "LEFT JOIN p_organization t5 on t5.id=t1.promotion_org_id "
						+ "where 1=1 %s %s %s %s %s %s %s %s %s %s %s %s %s %s  order by t1.apply_time desc limit "
						+ query.getPage() * query.getSize() + "," + query.getSize(),
				publisherNameCondition, publisherPhoneCondition, stockCodeOrNameCondition, nominalAmountCondition,
				cycleNameCondition, stateCondition, isTestCondition, isMarkCondition, startTimeCondition,
				endTimeCondition, startRatioCondition, endRatioCondition, orgCodeOrNameConditon, treeCodeQuery);
		String countSql = "select count(*) " + sql.substring(sql.indexOf("from"), sql.indexOf("limit"));
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setTradeNo", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setPublisherName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setPublisherPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setStockCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setStockName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setCycleName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setNominalAmount", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setRightMoneyRatio", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setRightMoney", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(10), new MethodDesc("setOrgRightMoneyRatio", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(11), new MethodDesc("setOrgRightMoney", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(12), new MethodDesc("setApplyTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(13), new MethodDesc("setBuyingTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(14), new MethodDesc("setBuyingPrice", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(15), new MethodDesc("setSellingTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(16), new MethodDesc("setSellingPrice", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(17), new MethodDesc("setProfit", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(18), new MethodDesc("setIsTest", new Class<?>[] { Boolean.class }));
		setMethodMap.put(new Integer(19), new MethodDesc("setIsMark", new Class<?>[] { Boolean.class }));
		setMethodMap.put(new Integer(20), new MethodDesc("setState", new Class<?>[] { StockOptionTradeState.class }));
		setMethodMap.put(new Integer(21), new MethodDesc("setRightTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(22), new MethodDesc("setOrgId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(23), new MethodDesc("setOrgCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(24), new MethodDesc("setOrgName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(25), new MethodDesc("setBuyingLastPrice", new Class<?>[] { BigDecimal.class }));
		List<StockOptionPromotionDto> content = sqlDao.execute(StockOptionPromotionDto.class, sql, setMethodMap);
		BigInteger totalElements = sqlDao.executeComputeSql(countSql);
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()),
				totalElements != null ? totalElements.longValue() : 0);
	}

	private OfflineStockOptionTrade saveOfflineTrade(StockOptionTrade trade, Long orgId,
			BigDecimal orgRightMoneyRatio) {
		StockOptionOrg org = optionOrgDao.retrieve(orgId);
		if (org == null) {
			throw new ServiceException(ExceptionConstant.DATANOTFOUND_EXCEPTION);
		}
		OfflineStockOptionTrade offlineTrade = new OfflineStockOptionTrade();
		BigDecimal orgRightMoney = trade.getNominalAmount().multiply(orgRightMoneyRatio).setScale(2,
				RoundingMode.HALF_EVEN);
		offlineTrade.setBuyingPrice(trade.getBuyingPrice());
		offlineTrade.setBuyingTime(trade.getBuyingTime());
		offlineTrade.setCycle(trade.getCycle());
		offlineTrade.setExpireTime(trade.getExpireTime());
		offlineTrade.setNominalAmount(trade.getNominalAmount());
		offlineTrade.setOrg(org);
		offlineTrade.setRightMoneyRatio(orgRightMoneyRatio);
		offlineTrade.setRightMoney(orgRightMoney);
		offlineTrade.setState(OfflineStockOptionTradeState.TURNOVER);
		offlineTrade.setStockCode(trade.getStockCode());
		offlineTrade.setStockName(trade.getStockName());
		return offlineTradeDao.create(offlineTrade);
	}

	private OfflineStockOptionTrade settlementOfflineTrade(StockOptionTrade trade) {
		logger.info("trade:{}", JacksonUtil.encode(trade));
		OfflineStockOptionTrade offlineTrade = trade.getOfflineTrade();
		offlineTrade.setSellingTime(trade.getSellingTime());
		offlineTrade.setSellingPrice(trade.getSellingPrice());
		offlineTrade.setProfit(trade.getProfit());
		offlineTrade.setState(OfflineStockOptionTradeState.SETTLEMENTED);
		return offlineTradeDao.update(offlineTrade);
	}

	private Date expireTime(Date date, Integer cycle) {
		try {
			// 计算到期日期
			Date beginTime = sdf.parse(sdf.format(date));
			long after = 24 * 60 * 60 * 1000;
			after *= (cycle - 1);
			return new Date(beginTime.getTime() + after);
		} catch (ParseException e) {
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION);
		}
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public StockOptionTrade turnover(Long id, Long orgId, BigDecimal orgRightMoneyRatio, BigDecimal buyingPrice) {
		synchronized (String.valueOf(id).intern()) {
			StockOptionTrade trade = stockOptionTradeDao.retrieve(id);
			if (StockOptionTradeState.WAITCONFIRMED != trade.getState()) {
				throw new ServiceException(ExceptionConstant.STOCKOPTION_STATE_NOTMATCH_OPERATION_NOTSUPPORT_EXCEPTION);
			}
			// 保存线下机构期权交易
			trade.setState(StockOptionTradeState.TURNOVER);
			trade.setBuyingPrice(buyingPrice);
			Date date = new Date();
			trade.setBuyingTime(date);
			trade.setExpireTime(expireTime(date, trade.getCycle()));
			trade.setUpdateTime(date);
			OfflineStockOptionTrade offlineTrade = saveOfflineTrade(trade, orgId, orgRightMoneyRatio);
			trade.setOfflineTrade(offlineTrade);
			logger.info("申购结果：{}_{}", offlineTrade.getId(), JacksonUtil.encode(trade));
			StockOptionTrade result = stockOptionTradeDao.update(trade);
			// 给渠道推广机构结算
			if (trade.getOfflineTrade().getRightMoney() != null
					&& (trade.getIsTest() == null || trade.getIsTest() == false)) {
				BigDecimal rightMoneyProfit = trade.getRightMoney().subtract(trade.getOfflineTrade().getRightMoney());
				orgSettlementBusiness.stockoptionSettlement(trade.getPublisherId(), trade.getId(), trade.getTradeNo(),
						trade.getCycleId(), rightMoneyProfit, trade.getRightMoney());
			}
			// 站外消息推送
			sendOutsideMessage(result);
			return result;
		}
	}

	@Transactional
	public StockOptionTrade mark(Long id, Boolean isMark) {
		StockOptionTrade trade = stockOptionTradeDao.retrieve(id);
		trade.setIsMark(isMark);
		return stockOptionTradeDao.update(trade);
	}

	@Transactional
	public StockOptionTrade insettlement(Long id, BigDecimal sellingPrice) {
		StockOptionTrade trade = stockOptionTradeDao.retrieve(id);
		if (!(StockOptionTradeState.APPLYRIGHT == trade.getState()
				|| StockOptionTradeState.AUTOEXPIRE == trade.getState())) {
			throw new ServiceException(ExceptionConstant.STOCKOPTION_STATE_NOTMATCH_OPERATION_NOTSUPPORT_EXCEPTION);
		}
		trade.setSellingPrice(sellingPrice);
		Date date = new Date();
		trade.setSellingTime(date);
		trade.setState(StockOptionTradeState.INSETTLEMENT);
		BigDecimal profit = BigDecimal.ZERO;
		if (sellingPrice.compareTo(trade.getBuyingPrice()) > 0) {
			profit = sellingPrice.subtract(trade.getBuyingPrice()).divide(trade.getBuyingPrice(), 10, RoundingMode.DOWN)
					.multiply(trade.getNominalAmount()).setScale(2, RoundingMode.HALF_EVEN);
		}
		trade.setProfit(profit);
		trade.setUpdateTime(date);
		stockOptionTradeDao.update(trade);
		// 线下期权交易结算
		settlementOfflineTrade(trade);
		// 站外消息推送
		sendOutsideMessage(trade);
		return trade;
	}

	@Transactional
	public synchronized StockOptionTrade dosettlement(Long id) {
		StockOptionTrade trade = stockOptionTradeDao.retrieve(id);
		if (StockOptionTradeState.INSETTLEMENT != trade.getState()) {
			throw new ServiceException(ExceptionConstant.STOCKOPTION_STATE_NOTMATCH_OPERATION_NOTSUPPORT_EXCEPTION);
		}
		trade.setState(StockOptionTradeState.SETTLEMENTED);
		trade.setUpdateTime(new Date());
		stockOptionTradeDao.update(trade);
		BigDecimal profit = trade.getProfit();
		if (profit.compareTo(BigDecimal.ZERO) > 0) {
			// 用户收益
			accountBusiness.optionProfit(trade.getPublisherId(), trade.getId(), profit);
		}
		// 站外消息推送
		sendOutsideMessage(trade);
		return trade;
	}

	@Transactional
	public StockOptionTrade autoSettlement(Long publisherId, Long id) {
		StockOptionTrade trade = stockOptionTradeDao.retrieve(id);
		if (trade.getState() != StockOptionTradeState.TURNOVER) {
			throw new ServiceException(ExceptionConstant.STOCKOPTION_STATE_NOTMATCH_OPERATION_NOTSUPPORT_EXCEPTION);
		}
		if (!trade.getPublisherId().equals(publisherId)) {
			throw new ServiceException(ExceptionConstant.STOCKOPTION_PUBLISHERID_NOTMATCH_EXCEPTION);
		}
		StockMarket stockMarket = stockQuotationHttp.fetQuotationByCode(trade.getStockCode());
		BigDecimal sellingPrice = stockMarket.getLastPrice();
		logger.info("stockMarket:{}", JacksonUtil.encode(stockMarket));
		trade.setState(StockOptionTradeState.SETTLEMENTED);
		trade.setUpdateTime(new Date());
		trade.setSellingPrice(sellingPrice);
		trade.setSellingTime(new Date());
		trade.setRightTime(new Date());
		BigDecimal profit = BigDecimal.ZERO;
		if (sellingPrice.compareTo(trade.getBuyingPrice()) > 0) {
			profit = sellingPrice.subtract(trade.getBuyingPrice()).divide(trade.getBuyingPrice(), 10, RoundingMode.DOWN)
					.multiply(trade.getNominalAmount()).setScale(2, RoundingMode.HALF_EVEN);
		}
		trade.setProfit(profit);
		stockOptionTradeDao.update(trade);
		// 线下期权交易结算
		settlementOfflineTrade(trade);
		if (profit.compareTo(BigDecimal.ZERO) > 0) {
			// 用户收益
			accountBusiness.optionProfit(trade.getPublisherId(), trade.getId(), profit);
		}
		// 站外消息推送
		sendOutsideMessage(trade);
		return trade;
	}

	/**
	 * 监控到自动到期，状态变成为“自动到期”
	 *
	 * @param id
	 *            期权交易ID
	 * @return 期权交易
	 */
	@Transactional
	public StockOptionTrade exercise(Long id) {
		StockOptionTrade trade = stockOptionTradeDao.retrieve(id);
		if (trade.getState() != StockOptionTradeState.TURNOVER) {
			throw new ServiceException(ExceptionConstant.STOCKOPTION_STATE_NOTMATCH_OPERATION_NOTSUPPORT_EXCEPTION);
		}
		StockMarket stockMarket = stockQuotationHttp.fetQuotationByCode(trade.getStockCode());
		logger.info("stockMarket:{}", JacksonUtil.encode(stockMarket));
		BigDecimal sellingPrice = stockMarket.getLastPrice();
		trade.setState(StockOptionTradeState.SETTLEMENTED);
		trade.setUpdateTime(new Date());
		trade.setSellingPrice(sellingPrice);
		trade.setSellingTime(new Date());
		BigDecimal profit = BigDecimal.ZERO;
		if (sellingPrice.compareTo(trade.getBuyingPrice()) > 0) {
			profit = sellingPrice.subtract(trade.getBuyingPrice()).divide(trade.getBuyingPrice(), 10, RoundingMode.DOWN)
					.multiply(trade.getNominalAmount()).setScale(2, RoundingMode.HALF_EVEN);
		}
		trade.setProfit(profit);
		StockOptionTrade result = stockOptionTradeDao.update(trade);
		// 线下期权交易结算
		settlementOfflineTrade(trade);
		if (profit.compareTo(BigDecimal.ZERO) > 0) {
			// 用户收益
			accountBusiness.optionProfit(trade.getPublisherId(), trade.getId(), profit);
		}
		// 站外消息推送
		sendOutsideMessage(result);
		return result;
	}

}
