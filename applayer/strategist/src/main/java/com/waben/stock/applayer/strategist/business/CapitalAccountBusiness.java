package com.waben.stock.applayer.strategist.business;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.strategist.dto.buyrecord.BuyRecordWithMarketDto;
import com.waben.stock.applayer.strategist.security.SecurityUtil;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.enums.BuyRecordState;
import com.waben.stock.interfaces.enums.WithdrawalsState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.BuyRecordQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.publisher.CapitalAccountInterface;

/**
 * 资金账户 Business
 * 
 * @author luomengan
 *
 */
@Service("strategistCapitalAccountBusiness")
public class CapitalAccountBusiness {

	@Autowired
	private CapitalAccountInterface capitalAccountReference;

	@Autowired
	private BuyRecordBusiness buyRecordBusiness;

	public CapitalAccountDto findByPublisherId(Long publisherId) {
		Response<CapitalAccountDto> response = capitalAccountReference.fetchByPublisherId(publisherId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public void modifyPaymentPassword(Long publisherId, String paymentPassword) {
		Response<Void> response = capitalAccountReference.modifyPaymentPassword(publisherId, paymentPassword);
		if (!"200".equals(response.getCode())) {
			throw new ServiceException(response.getCode());
		}
	}

	public CapitalAccountDto recharge(Long publisherId, BigDecimal amount, Long rechargeId) {
		Response<CapitalAccountDto> response = capitalAccountReference.recharge(publisherId, amount, rechargeId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto csa(Long publisherId, BigDecimal amount, Long withdrawalsId) {
		Response<CapitalAccountDto> response = capitalAccountReference.csa(publisherId, amount, withdrawalsId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public CapitalAccountDto withdrawals(Long publisherId, Long withdrawalsId, WithdrawalsState state) {
		Response<CapitalAccountDto> response = capitalAccountReference.withdrawals(publisherId, withdrawalsId,
				state.getIndex());
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public BigDecimal getHoldProfitOrLoss(Long publisherId) {
		BigDecimal result = BigDecimal.valueOf(0);

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

	public BigDecimal getDeferredAmount(Long publisherId) {
		// TODO 获取递延费用
		return new BigDecimal(0);
	}

}
