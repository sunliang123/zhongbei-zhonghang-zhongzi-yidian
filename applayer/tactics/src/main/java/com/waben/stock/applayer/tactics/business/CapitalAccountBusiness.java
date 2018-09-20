package com.waben.stock.applayer.tactics.business;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.tactics.dto.buyrecord.BuyRecordWithMarketDto;
import com.waben.stock.applayer.tactics.dto.stockoption.StockOptionTradeWithMarketDto;
import com.waben.stock.applayer.tactics.security.SecurityUtil;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;
import com.waben.stock.interfaces.enums.BuyRecordState;
import com.waben.stock.interfaces.enums.StockOptionTradeState;
import com.waben.stock.interfaces.enums.WithdrawalsState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.BuyRecordQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StockOptionTradeUserQuery;
import com.waben.stock.interfaces.service.publisher.CapitalAccountInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * 资金账户 Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsCapitalAccountBusiness")
public class CapitalAccountBusiness {

	@Autowired
	private CapitalAccountInterface service;

	@Autowired
	private BuyRecordBusiness buyRecordBusiness;

	@Autowired
	private StockOptionTradeBusiness optionTradeBusiness;

	public CapitalAccountDto findByPublisherId(Long publisherId) {
		Response<CapitalAccountDto> response = service.fetchByPublisherId(publisherId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public void modifyPaymentPassword(Long publisherId, String paymentPassword) {
		Response<Void> response = service.modifyPaymentPassword(publisherId, paymentPassword);
		if (!"200".equals(response.getCode())) {
			throw new ServiceException(response.getCode());
		}
	}

	public CapitalAccountDto recharge(Long publisherId, BigDecimal amount, Long rechargeId) {
		Response<CapitalAccountDto> response = service.recharge(publisherId, amount, rechargeId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto csa(Long publisherId, BigDecimal amount, Long rechargeId) {
		Response<CapitalAccountDto> response = service.csa(publisherId, amount, rechargeId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto withdrawals(Long publisherId, Long withdrawalsId, WithdrawalsState state) {
		Response<CapitalAccountDto> response = service.withdrawals(publisherId, withdrawalsId, state.getIndex());
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	// 快捷提现
	public CapitalAccountDto csa(Long publisherId, Long withdrawalsId, WithdrawalsState state) {
		Response<CapitalAccountDto> response = service.withdrawals(publisherId, withdrawalsId, state.getIndex());
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public BigDecimal getHoldProfitOrLoss(Long publisherId) {
		return getStrategyHoldProfitOrLoss(publisherId).add(getStockOptionHoldProfitOrLoss(publisherId));
	}

	private BigDecimal getStockOptionHoldProfitOrLoss(Long publisherId) {
		BigDecimal result = BigDecimal.ZERO;
		StockOptionTradeUserQuery query = new StockOptionTradeUserQuery(0, Integer.MAX_VALUE, SecurityUtil.getUserId(),
				new StockOptionTradeState[] { StockOptionTradeState.TURNOVER, StockOptionTradeState.APPLYRIGHT,
						StockOptionTradeState.INSETTLEMENT });
		PageInfo<StockOptionTradeDto> pageInfo = optionTradeBusiness.pagesByUserQuery(query);
		List<StockOptionTradeWithMarketDto> content = CopyBeanUtils.copyListBeanPropertiesToList(pageInfo.getContent(),
				StockOptionTradeWithMarketDto.class);
		if (content != null && content.size() > 0) {
			for (StockOptionTradeWithMarketDto market : content) {
				result = result.add(market.getProfit());
			}
		}
		return result;
	}

	private BigDecimal getStrategyHoldProfitOrLoss(Long publisherId) {
		BigDecimal result = BigDecimal.ZERO;
		BuyRecordQuery query = new BuyRecordQuery(0, Integer.MAX_VALUE, SecurityUtil.getUserId(),
				new BuyRecordState[] { BuyRecordState.POSTED, BuyRecordState.BUYLOCK, BuyRecordState.HOLDPOSITION,
						BuyRecordState.SELLAPPLY, BuyRecordState.SELLLOCK });
		PageInfo<BuyRecordDto> response = buyRecordBusiness.pages(query);
		List<BuyRecordDto> content = response.getContent();
		if (content != null && content.size() > 0) {
			List<BuyRecordWithMarketDto> marketContent = buyRecordBusiness.wrapMarketInfo(content);
			for (BuyRecordWithMarketDto market : marketContent) {
				result = result.add(market.getProfitOrLoss());
			}
		}
		return result;
	}

	public BigDecimal getTotalApplyAmount(Long publisherId) {
		BigDecimal result = BigDecimal.valueOf(0);

		BuyRecordQuery query = new BuyRecordQuery(0, Integer.MAX_VALUE, SecurityUtil.getUserId(),
				new BuyRecordState[] { BuyRecordState.POSTED, BuyRecordState.BUYLOCK, BuyRecordState.HOLDPOSITION,
						BuyRecordState.SELLAPPLY, BuyRecordState.SELLLOCK, BuyRecordState.UNWIND });
		PageInfo<BuyRecordDto> response = buyRecordBusiness.pages(query);
		List<BuyRecordDto> content = response.getContent();
		if (content != null && content.size() > 0) {
			for (BuyRecordDto buyRecord : content) {
				result = result.add(buyRecord.getApplyAmount());
			}
		}

		return result;
	}

	public BigDecimal getTodayApplyAmount(Long publisherId) {
		BigDecimal result = BigDecimal.valueOf(0);

		BuyRecordQuery query = new BuyRecordQuery(0, Integer.MAX_VALUE, SecurityUtil.getUserId(),
				new BuyRecordState[] { BuyRecordState.POSTED, BuyRecordState.BUYLOCK, BuyRecordState.HOLDPOSITION,
						BuyRecordState.SELLAPPLY, BuyRecordState.SELLLOCK, BuyRecordState.UNWIND });
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		query.setStartCreateTime(cal.getTime());
		PageInfo<BuyRecordDto> response = buyRecordBusiness.pages(query);
		List<BuyRecordDto> content = response.getContent();
		if (content != null && content.size() > 0) {
			for (BuyRecordDto buyRecord : content) {
				result = result.add(buyRecord.getApplyAmount());
			}
		}

		return result;
	}

	public BigDecimal getHoldDeferredAmount(Long publisherId) {
		BigDecimal result = BigDecimal.ZERO;
		BuyRecordQuery query = new BuyRecordQuery(0, Integer.MAX_VALUE, SecurityUtil.getUserId(),
				new BuyRecordState[] { BuyRecordState.POSTED, BuyRecordState.BUYLOCK, BuyRecordState.HOLDPOSITION,
						BuyRecordState.SELLAPPLY, BuyRecordState.SELLLOCK });
		PageInfo<BuyRecordDto> response = buyRecordBusiness.pages(query);
		List<BuyRecordDto> content = response.getContent();
		if (content != null && content.size() > 0) {
			for (BuyRecordDto buyRecord : content) {
				if (buyRecord.getDeferred() && buyRecord.getDeferredFee() != null
						&& buyRecord.getDeferredFee().compareTo(BigDecimal.ZERO) > 0)
					result = result.add(buyRecord.getDeferredFee());
			}
		}
		return result;
	}

	public BigDecimal getTotalNominalAmount(Long publisherId) {
		BigDecimal result = BigDecimal.ZERO;
		StockOptionTradeUserQuery query = new StockOptionTradeUserQuery(0, Integer.MAX_VALUE, SecurityUtil.getUserId(),
				new StockOptionTradeState[] { StockOptionTradeState.WAITCONFIRMED, StockOptionTradeState.TURNOVER,
						StockOptionTradeState.APPLYRIGHT, StockOptionTradeState.INSETTLEMENT,
						StockOptionTradeState.SETTLEMENTED });
		PageInfo<StockOptionTradeDto> pageInfo = optionTradeBusiness.pagesByUserQuery(query);
		List<StockOptionTradeDto> content = pageInfo.getContent();
		if (content != null && content.size() > 0) {
			for (StockOptionTradeDto trade : content) {
				result = result.add(trade.getNominalAmount());
			}
		}
		return result;
	}

	public BigDecimal getTodayNominalAmount(Long publisherId) {
		BigDecimal result = BigDecimal.ZERO;
		StockOptionTradeUserQuery query = new StockOptionTradeUserQuery(0, Integer.MAX_VALUE, SecurityUtil.getUserId(),
				new StockOptionTradeState[] { StockOptionTradeState.WAITCONFIRMED, StockOptionTradeState.TURNOVER,
						StockOptionTradeState.APPLYRIGHT, StockOptionTradeState.INSETTLEMENT,
						StockOptionTradeState.SETTLEMENTED });
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		query.setStartApplyTime(cal.getTime());
		PageInfo<StockOptionTradeDto> pageInfo = optionTradeBusiness.pagesByUserQuery(query);
		List<StockOptionTradeDto> content = pageInfo.getContent();
		if (content != null && content.size() > 0) {
			for (StockOptionTradeDto trade : content) {
				result = result.add(trade.getNominalAmount());
			}
		}
		return result;
	}

	public BigDecimal getTotalRightMoney(Long publisherId) {
		BigDecimal result = BigDecimal.ZERO;
		StockOptionTradeUserQuery query = new StockOptionTradeUserQuery(0, Integer.MAX_VALUE, SecurityUtil.getUserId(),
				new StockOptionTradeState[] { StockOptionTradeState.WAITCONFIRMED, StockOptionTradeState.TURNOVER,
						StockOptionTradeState.APPLYRIGHT, StockOptionTradeState.INSETTLEMENT,
						StockOptionTradeState.SETTLEMENTED });
		PageInfo<StockOptionTradeDto> pageInfo = optionTradeBusiness.pagesByUserQuery(query);
		List<StockOptionTradeDto> content = pageInfo.getContent();
		if (content != null && content.size() > 0) {
			for (StockOptionTradeDto trade : content) {
				result = result.add(trade.getRightMoney());
			}
		}
		return result;
	}

	public BigDecimal getTodayRightMoney(Long publisherId) {
		BigDecimal result = BigDecimal.ZERO;
		StockOptionTradeUserQuery query = new StockOptionTradeUserQuery(0, Integer.MAX_VALUE, SecurityUtil.getUserId(),
				new StockOptionTradeState[] { StockOptionTradeState.WAITCONFIRMED, StockOptionTradeState.TURNOVER,
						StockOptionTradeState.APPLYRIGHT, StockOptionTradeState.INSETTLEMENT,
						StockOptionTradeState.SETTLEMENTED });
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		query.setStartApplyTime(cal.getTime());
		PageInfo<StockOptionTradeDto> pageInfo = optionTradeBusiness.pagesByUserQuery(query);
		List<StockOptionTradeDto> content = pageInfo.getContent();
		if (content != null && content.size() > 0) {
			for (StockOptionTradeDto trade : content) {
				result = result.add(trade.getRightMoney());
			}
		}
		return result;
	}

}
