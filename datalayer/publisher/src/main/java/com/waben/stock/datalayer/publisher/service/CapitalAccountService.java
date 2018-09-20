package com.waben.stock.datalayer.publisher.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.waben.stock.datalayer.publisher.entity.*;
import com.waben.stock.datalayer.publisher.repository.*;
import com.waben.stock.interfaces.dto.admin.publisher.PublisherAdminDto;
import com.waben.stock.interfaces.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.publisher.business.OutsideMessageBusiness;
import com.waben.stock.datalayer.publisher.repository.impl.MethodDesc;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.admin.publisher.CapitalAccountAdminDto;
import com.waben.stock.interfaces.enums.CapitalFlowExtendType;
import com.waben.stock.interfaces.enums.CapitalFlowType;
import com.waben.stock.interfaces.enums.FrozenCapitalStatus;
import com.waben.stock.interfaces.enums.FrozenCapitalType;
import com.waben.stock.interfaces.enums.OutsideMessageType;
import com.waben.stock.interfaces.enums.ResourceType;
import com.waben.stock.interfaces.enums.WithdrawalsState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.message.OutsideMessage;
import com.waben.stock.interfaces.pojo.query.CapitalAccountQuery;
import com.waben.stock.interfaces.pojo.query.admin.publisher.CapitalAccountAdminQuery;
import com.waben.stock.interfaces.util.PasswordCrypt;
import com.waben.stock.interfaces.util.StringUtil;

@Service
public class CapitalAccountService {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CapitalAccountDao capitalAccountDao;

	@Autowired
	private CapitalAccountRecordDao recordDao;

	@Autowired
	private CapitalFlowDao flowDao;

	@Autowired
	private FrozenCapitalDao frozenCapitalDao;

	@Autowired
	private PublisherDao publisherDao;

	@Autowired
	private WithdrawalsOrderDao withdrawalsOrderDao;

	@Autowired
	private OutsideMessageBusiness outsideMessageBusiness;

	@Autowired
	private DynamicQuerySqlDao sqlDao;

	public void delete(Long accId){
		CapitalAccount account = capitalAccountDao.retrieve(accId);
		List<CapitalAccountRecord> list =  recordDao.findByCapitalAccount(account);
		for(CapitalAccountRecord record : list){
			recordDao.delete(record.getId());
		}
		capitalAccountDao.delete(accId);
	}

	/**
	 * 根据发布人系列号获取资金账户
	 */
	public CapitalAccount findByPublisherSerialCode(String publisherSerialCode) {
		return capitalAccountDao.retriveByPublisherSerialCode(publisherSerialCode);
	}

	public CapitalAccount findById(Long id) {
		return capitalAccountDao.retrieve(id);
	}

	/**
	 * 根据发布人ID获取资金账户
	 */
	public CapitalAccount findByPublisherId(Long publisherId) {
		return capitalAccountDao.retriveByPublisherId(publisherId);
	}

	/**
	 * 修改支付密码
	 */
	public void modifyPaymentPassword(Long publisherId, String paymentPassword) {
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		account.setPaymentPassword(PasswordCrypt.crypt(paymentPassword));
		capitalAccountDao.update(account);
	}

	private void sendRechargeOutsideMessage(final Long publisherId, final BigDecimal amount) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					OutsideMessage message = new OutsideMessage();
					message.setPublisherId(publisherId);
					message.setTitle("资金通知");
					message.setContent(String.format("您的资金账户已充值成功+%s元", amount.toString()));
					Map<String, String> extras = new HashMap<>();
					extras.put("title", message.getTitle());
					extras.put("content", String.format("您的资金账户已充值成功<span id=\"money\">+%s元</span>", amount.toString()));
					extras.put("publisherId", String.valueOf(publisherId));
					extras.put("resourceType", ResourceType.PUBLISHER.getIndex());
					extras.put("resourceId", String.valueOf(publisherId));
					extras.put("type", OutsideMessageType.ACCOUNT_RECHARGESUCCESS.getIndex());
					message.setExtras(extras);
					outsideMessageBusiness.send(message);
				} catch (Exception ex) {
					logger.error("发送资金通知失败，{}充值成功{}_{}", publisherId, amount.toString(), ex.getMessage());
				}
			}
		}).start();
	}

	private void sendWithdrawalsOutsideMessage(final Long publisherId, final BigDecimal amount, final boolean isSuccessful) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					OutsideMessage message = new OutsideMessage();
					message.setPublisherId(publisherId);
					message.setTitle("资金通知");
					if (isSuccessful) {
						message.setContent(String.format("您的资金账户已成功提现-%s元", amount.toString()));
					} else {
						message.setContent(String.format("您的资金账户提现失败%s元", amount.toString()));
					}
					Map<String, String> extras = new HashMap<>();
					extras.put("title", message.getTitle());
					if (isSuccessful) {
						extras.put("content", String.format("您的资金账户已成功提现<span id=\"money\">-%s元</span>", amount.toString()));
					} else {
						extras.put("content", String.format("您的资金账户提现失败<span id=\"money\">%s元</span>", amount.toString()));
					}
					extras.put("publisherId", String.valueOf(publisherId));
					extras.put("resourceType", ResourceType.PUBLISHER.getIndex());
					extras.put("resourceId", String.valueOf(publisherId));
					if (isSuccessful) {
						extras.put("type", OutsideMessageType.ACCOUNT_WITHDRAWALSSUCCESS.getIndex());
					} else {
						extras.put("type", OutsideMessageType.ACCOUNT_WITHDRAWALFAILED.getIndex());
					}
					message.setExtras(extras);
					outsideMessageBusiness.send(message);
				} catch (Exception ex) {
					logger.error("发送资金通知失败，{}充值成功{}_{}", publisherId, amount.toString(), ex.getMessage());
				}
			}
		}).start();
	}

	/**
	 * 充值
	 */
	@Transactional
	public synchronized CapitalAccount recharge(Long publisherId, BigDecimal amount, Long rechargeId) {
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		Date date = new Date();
		increaseAmount(account, amount, date);
		flowDao.create(account.getPublisher(), CapitalFlowType.Recharge, amount.abs(), date,
				CapitalFlowExtendType.PAYMENTORDER, rechargeId, account.getAvailableBalance());
		sendRechargeOutsideMessage(publisherId, amount);
		return findByPublisherId(publisherId);
	}

	/**
	 * 提现改变资金
	 */
	@Transactional
	public synchronized CapitalAccount csa(Long publisherId, BigDecimal amount, Long withdrawalsId) {
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		Date date = new Date();
		reduceAmount(account, amount, date);
		flowDao.create(account.getPublisher(), CapitalFlowType.Withdrawals, amount.abs().multiply(new BigDecimal(-1)),
				date, CapitalFlowExtendType.WITHDRAWALSORDER, withdrawalsId, account.getAvailableBalance());
		sendWithdrawalsOutsideMessage(publisherId, amount, true);
		return findByPublisherId(publisherId);
	}

	/**
	 * 提现
	 */
	@Transactional
	public synchronized CapitalAccount withdrawals(Long publisherId, Long withdrawalsId,
			WithdrawalsState withdrawalsState) {
		WithdrawalsOrder withdrawalsOrder = withdrawalsOrderDao.retrieve(withdrawalsId);
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		if (withdrawalsOrder.getState() == WithdrawalsState.PROCESSING
				&& withdrawalsState != WithdrawalsState.PROCESSING) {
			BigDecimal amount = withdrawalsOrder.getAmount();
			Date date = new Date();
			if (withdrawalsState == WithdrawalsState.PROCESSED) {
				// 修改提现订单的状态
				withdrawalsOrder.setState(withdrawalsState);
				withdrawalsOrder.setUpdateTime(new Date());
				withdrawalsOrderDao.update(withdrawalsOrder);
				// 解冻
				FrozenCapital frozen = frozenCapitalDao.retriveByPublisherIdAndWithdrawalsNo(publisherId,
						withdrawalsOrder.getWithdrawalsNo());
				frozen.setStatus(FrozenCapitalStatus.Thaw);
				frozen.setThawTime(date);
				frozenCapitalDao.update(frozen);
				// 修改账户的总金额、冻结金额
				account.setBalance(account.getBalance().subtract(amount));
				account.setFrozenCapital(account.getFrozenCapital().subtract(amount));
				capitalAccountDao.update(account);
				// 产生资金流水
				flowDao.create(account.getPublisher(), CapitalFlowType.Withdrawals,
						amount.abs().multiply(new BigDecimal(-1)), date, CapitalFlowExtendType.WITHDRAWALSORDER,
						withdrawalsId, account.getAvailableBalance());
				sendWithdrawalsOutsideMessage(publisherId, amount.abs(), true);
				return findByPublisherId(publisherId);
			} else if (withdrawalsState == WithdrawalsState.FAILURE) {
				// 修改提现订单的状态
				withdrawalsOrder.setState(WithdrawalsState.RETREAT);
				withdrawalsOrder.setUpdateTime(new Date());
				withdrawalsOrderDao.update(withdrawalsOrder);
				// 解冻
				FrozenCapital frozen = frozenCapitalDao.retriveByPublisherIdAndWithdrawalsNo(publisherId,
						withdrawalsOrder.getWithdrawalsNo());
				frozen.setStatus(FrozenCapitalStatus.Thaw);
				frozen.setThawTime(date);
				frozenCapitalDao.update(frozen);
				// 修改账户的总金额、冻结金额
				account.setFrozenCapital(account.getFrozenCapital().subtract(amount));
				account.setAvailableBalance(account.getAvailableBalance().add(amount));
				capitalAccountDao.update(account);
				sendWithdrawalsOutsideMessage(publisherId, amount.abs(), false);
				return findByPublisherId(publisherId);
			}
		}
		return account;
	}

	/**
	 * 信息服务费和赔付保证金
	 */
	@Transactional
	public synchronized CapitalAccount serviceFeeAndReserveFund(Long publisherId, Long buyRecordId,
			BigDecimal serviceFee, BigDecimal reserveFund, BigDecimal deferredFee) {
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		Date date = new Date();
		// 扣减服务费
		if (serviceFee != null && serviceFee.compareTo(new BigDecimal(0)) > 0) {
			reduceAmount(account, serviceFee, date);
			flowDao.create(account.getPublisher(), CapitalFlowType.ServiceFee,
					serviceFee.abs().multiply(new BigDecimal(-1)), date, CapitalFlowExtendType.BUYRECORD, buyRecordId,
					account.getAvailableBalance());
		}
		// 扣减递延费
		if (deferredFee != null && deferredFee.compareTo(new BigDecimal(0)) > 0) {
			reduceAmount(account, deferredFee, date);
			flowDao.create(account.getPublisher(), CapitalFlowType.DeferredCharges,
					deferredFee.abs().multiply(new BigDecimal(-1)), date, CapitalFlowExtendType.BUYRECORD, buyRecordId,
					account.getAvailableBalance());
		}
		// 冻结履约保证金
		if (reserveFund != null && reserveFund.abs().compareTo(new BigDecimal(0)) > 0) {
			frozenAmount(account, reserveFund, date);
			flowDao.create(account.getPublisher(), CapitalFlowType.ReserveFund,
					reserveFund.abs().multiply(new BigDecimal(-1)), date, CapitalFlowExtendType.BUYRECORD, buyRecordId,
					account.getAvailableBalance());
		}
		// 保存冻结资金记录
		FrozenCapital frozen = new FrozenCapital();
		frozen.setAmount(reserveFund.abs());
		frozen.setBuyRecordId(buyRecordId);
		frozen.setFrozenTime(date);
		frozen.setPublisherId(publisherId);
		frozen.setStatus(FrozenCapitalStatus.Frozen);
		frozen.setType(FrozenCapitalType.ReserveFund);
		frozenCapitalDao.create(frozen);
		return findByPublisherId(publisherId);
	}

	/**
	 * 递延费
	 */
	@Transactional
	public synchronized CapitalAccount deferredCharges(Long publisherId, Long buyRecordId, BigDecimal amount) {
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		Date date = new Date();
		reduceAmount(account, amount, date);
		flowDao.create(account.getPublisher(), CapitalFlowType.DeferredCharges,
				amount.abs().multiply(new BigDecimal(-1)), date, CapitalFlowExtendType.BUYRECORD, buyRecordId,
				account.getAvailableBalance());
		return findByPublisherId(publisherId);
	}

	/**
	 * 退回赔付保证金和盈亏
	 */
	@Transactional
	public synchronized CapitalAccount returnReserveFund(Long publisherId, Long buyRecordId, String buyRecordSerialCode,
			BigDecimal profitOrLoss) {
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		Date date = new Date();
		// 获取冻结资金记录
		FrozenCapital frozen = frozenCapitalDao.retriveByPublisherIdAndBuyRecordId(publisherId, buyRecordId);
		if (frozen.getStatus() == FrozenCapitalStatus.Thaw) {
			return account;
		}
		BigDecimal frozenAmount = frozen.getAmount();
		// 退回全部冻结资金
		thawAmount(account, frozenAmount, frozenAmount, date);
		account.setFrozenCapital(account.getFrozenCapital().subtract(frozenAmount.abs()));
		capitalAccountDao.update(account);
		flowDao.create(account.getPublisher(), CapitalFlowType.ReturnReserveFund, frozenAmount.abs(), date,
				CapitalFlowExtendType.BUYRECORD, buyRecordId, account.getAvailableBalance());
		// 盈亏
		if (profitOrLoss.compareTo(new BigDecimal(0)) > 0) {
			// 盈利
			increaseAmount(account, profitOrLoss, date);
			flowDao.create(account.getPublisher(), CapitalFlowType.Profit, profitOrLoss.abs(), date,
					CapitalFlowExtendType.BUYRECORD, buyRecordId, account.getAvailableBalance());
		} else if (profitOrLoss.compareTo(new BigDecimal(0)) < 0) {
			// 亏损
			BigDecimal lossAmountAbs = profitOrLoss.abs();
			if (lossAmountAbs.compareTo(frozenAmount) > 0) {
				// 最多亏损保证金
				lossAmountAbs = frozenAmount;
			}
			reduceAmount(account, lossAmountAbs, date);
			flowDao.create(account.getPublisher(), CapitalFlowType.Loss,
					lossAmountAbs.abs().multiply(new BigDecimal(-1)), date, CapitalFlowExtendType.BUYRECORD,
					buyRecordId, account.getAvailableBalance());
		}
		// 修改冻结记录为解冻状态
		frozen.setStatus(FrozenCapitalStatus.Thaw);
		frozen.setThawTime(new Date());
		frozenCapitalDao.update(frozen);
		// promoterShareBenefit(publisherId, buyRecordId, date);
		return findByPublisherId(publisherId);
	}

	/**
	 * 给推广人提成
	 */
	@SuppressWarnings("unused")
	private void promoterShareBenefit(Long publisherId, Long buyRecordId, Date date) {
		// 是否为被推广人的第一笔单，如果是，推广人赚取10%的服务费
		Publisher publisher = publisherDao.retrieve(publisherId);
		if (publisher.getPromoter() != null && !"".equals(publisher.getPromoter().trim())) {
			Publisher promoter = publisherDao.retrieveByPromotionCode(publisher.getPromoter().trim());
			if (promoter != null) {
				// CapitalFlowExtendQuery query = new CapitalFlowExtendQuery();
				// query.setPage(0);
				// query.setSize(10);
				// query.setType(CapitalFlowType.Promotion);
				// query.setPublisherId(promoter.getId());
				// query.setExtendType(CapitalFlowExtendType.PUBLISHER);
				// query.setExtendId(publisher.getId());
				// Page<CapitalFlowExtend> flowPage =
				// extendService.pagesByQuery(query);
				// if (flowPage.getContent().size() == 0) {
				// // 获取当前点买记录的服务费
				// query.setPage(0);
				// query.setSize(10);
				// query.setType(CapitalFlowType.ServiceFee);
				// query.setPublisherId(publisherId);
				// query.setExtendType(CapitalFlowExtendType.BUYRECORD);
				// query.setExtendId(buyRecordId);
				// Page<CapitalFlowExtend> serviceFeePage =
				// extendService.pagesByQuery(query);
				// if (serviceFeePage.getContent() != null &&
				// serviceFeePage.getContent().size() > 0) {
				// BigDecimal serviceFee =
				// serviceFeePage.getContent().get(0).getFlow().getAmount().abs();
				// if (serviceFee.compareTo(new BigDecimal(0)) > 0) {
				// BigDecimal promotionAmount = serviceFee.multiply(new
				// BigDecimal(0.1));
				// CapitalAccount promoterAccount =
				// capitalAccountDao.retriveByPublisherId(promoter.getId());
				// increaseAmount(promoterAccount, promotionAmount, date);
				// CapitalFlow promotionFlow = flowDao.create(promoter.getId(),
				// promoter.getSerialCode(),
				// CapitalFlowType.Promotion, promotionAmount, date);
				// CapitalFlowExtend promotionExtend = new
				// CapitalFlowExtend(promotionFlow,
				// CapitalFlowExtendType.PUBLISHER, publisherId);
				// flowExtendDao.create(promotionExtend);
				// }
				// }
				// }
			}
		}
	}

	@Transactional
	public synchronized CapitalAccount returnDeferredFee(Long publisherId, Long buyRecordId, BigDecimal deferredFee) {
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		Date date = new Date();
		increaseAmount(account, deferredFee, date);
		// 保存流水
		flowDao.create(account.getPublisher(), CapitalFlowType.ReturnDeferredCharges, deferredFee.abs(), date,
				CapitalFlowExtendType.BUYRECORD, buyRecordId, account.getAvailableBalance());
		return findByPublisherId(publisherId);
	}

	@Transactional
	public synchronized CapitalAccount revoke(Long publisherId, Long buyRecordId, BigDecimal serviceFee,
			BigDecimal deferredFee) {
		Date date = new Date();
		// 解冻保证金
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		FrozenCapital frozenCapital = findFrozenCapital(publisherId, buyRecordId);
		frozenCapital.setStatus(FrozenCapitalStatus.Thaw);
		frozenCapital.setThawTime(date);
		thawAmount(account, frozenCapital.getAmount(), frozenCapital.getAmount(), date);
		account.setFrozenCapital(account.getFrozenCapital().subtract(frozenCapital.getAmount()));
		flowDao.create(account.getPublisher(), CapitalFlowType.ReturnReserveFund, frozenCapital.getAmount().abs(), date,
				CapitalFlowExtendType.BUYRECORD, buyRecordId, account.getAvailableBalance());
		// 退回服务费
		increaseAmount(account, serviceFee, date);
		flowDao.create(account.getPublisher(), CapitalFlowType.Revoke, serviceFee.abs(), date,
				CapitalFlowExtendType.BUYRECORD, buyRecordId, account.getAvailableBalance());
		// 退回递延费
		if (deferredFee != null && deferredFee.compareTo(new BigDecimal(0)) > 0) {
			increaseAmount(account, deferredFee, date);
			flowDao.create(account.getPublisher(), CapitalFlowType.ReturnDeferredCharges, deferredFee.abs(), date,
					CapitalFlowExtendType.BUYRECORD, buyRecordId, account.getAvailableBalance());
		}
		capitalAccountDao.update(account);
		return findByPublisherId(publisherId);
	}

	public FrozenCapital findFrozenCapital(Long publisherId, Long buyRecordId) {
		return frozenCapitalDao.retriveByPublisherIdAndBuyRecordId(publisherId, buyRecordId);
	}

	/**
	 * 解冻金额
	 * 
	 * @param account
	 *            资金账户
	 * @param amount
	 *            退回金额
	 * @param frozenAmount
	 *            原先冻结资金
	 * 
	 */
	private synchronized void thawAmount(CapitalAccount account, BigDecimal amount, BigDecimal frozenAmount,
			Date date) {
		if (account.getState() != null && account.getState() == 2) {
			throw new ServiceException(ExceptionConstant.CAPITALACCOUNT_FROZEN_EXCEPTION);
		}
		account.setBalance(account.getBalance().subtract(frozenAmount.subtract(amount)));
		account.setAvailableBalance(account.getAvailableBalance().add(amount));
		account.setUpdateTime(date);
		capitalAccountDao.update(account);
	}

	/**
	 * 冻结金额
	 * 
	 * @param account
	 *            资金账户
	 * @param frozenAmount
	 *            冻结金额
	 * 
	 */
	private synchronized void frozenAmount(CapitalAccount account, BigDecimal frozenAmount, Date date) {
		if (account.getState() != null && account.getState() == 2) {
			throw new ServiceException(ExceptionConstant.CAPITALACCOUNT_FROZEN_EXCEPTION);
		}
		BigDecimal amountAbs = frozenAmount.abs();
		// 判断账余额是否足够
		if (account.getAvailableBalance().compareTo(amountAbs) < 0) {
			throw new ServiceException(ExceptionConstant.AVAILABLE_BALANCE_NOTENOUGH_EXCEPTION);
		}
		account.setAvailableBalance(account.getAvailableBalance().subtract(amountAbs));
		account.setFrozenCapital(account.getFrozenCapital().add(amountAbs));
		account.setUpdateTime(date);
		capitalAccountDao.update(account);
	}

	/**
	 * 账户增加金额
	 * 
	 * @param account
	 *            资金账户
	 * @param amount
	 *            金额
	 */
	private synchronized void increaseAmount(CapitalAccount account, BigDecimal amount, Date date) {
		if (account.getState() != null && account.getState() == 2) {
			throw new ServiceException(ExceptionConstant.CAPITALACCOUNT_FROZEN_EXCEPTION);
		}
		account.setBalance(account.getBalance().add(amount));
		account.setAvailableBalance(account.getAvailableBalance().add(amount));
		account.setUpdateTime(date);
		capitalAccountDao.update(account);
	}

	/**
	 * 账户减少金额
	 * 
	 * @param account
	 *            资金账户
	 * @param amount
	 *            金额
	 */
	private synchronized void reduceAmount(CapitalAccount account, BigDecimal amount, Date date) {
		if (account.getState() != null && account.getState() == 2) {
			throw new ServiceException(ExceptionConstant.CAPITALACCOUNT_FROZEN_EXCEPTION);
		}
		BigDecimal amountAbs = amount.abs();
		// 判断账余额是否足够
		if (account.getAvailableBalance().compareTo(amountAbs) < 0) {
			throw new ServiceException(ExceptionConstant.AVAILABLE_BALANCE_NOTENOUGH_EXCEPTION);
		}
		account.setBalance(account.getBalance().subtract(amount));
		account.setAvailableBalance(account.getAvailableBalance().subtract(amount));
		account.setUpdateTime(date);
		capitalAccountDao.update(account);
	}

	public Page<CapitalAccount> pages(final CapitalAccountQuery query) {
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<CapitalAccount> pages = capitalAccountDao.page(new Specification<CapitalAccount>() {
			@Override
			public Predicate toPredicate(Root<CapitalAccount> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicatesList = new ArrayList<Predicate>();
				if (query.getPublisherId() != null && query.getPublisherId() > 0) {
					Publisher publisher = new Publisher();
					publisher.setId(query.getPublisherId());
					Predicate publisherIdQuery = criteriaBuilder.equal(root.get("publisher").as(Publisher.class),
							publisher);
					predicatesList.add(criteriaBuilder.and(publisherIdQuery));
				}
				if (query.getBeginTime() != null) {
					Predicate updateTimeQuery = criteriaBuilder.between(root.<Date>get("updateTime").as(Date.class),
							query.getBeginTime(), query.getEndTime());
					predicatesList.add(criteriaBuilder.and(updateTimeQuery));
				}
				criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
				criteriaQuery.orderBy(criteriaBuilder.desc(root.<Date>get("updateTime").as(Date.class)));
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}

	public CapitalAccount revision(CapitalAccount capitalAccount) {
		CapitalAccount response = capitalAccountDao.update(capitalAccount);
		return response;
	}

	public CapitalAccount rightMoney(Long publisherId, Long optionTradeId, BigDecimal rightMoney) {
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		Date date = new Date();
		reduceAmount(account, rightMoney, date);
		// 保存流水
		flowDao.create(account.getPublisher(), CapitalFlowType.RightMoney,
				rightMoney.abs().multiply(new BigDecimal(-1)), date, CapitalFlowExtendType.STOCKOPTIONTRADE,
				optionTradeId, account.getAvailableBalance());
		return findByPublisherId(publisherId);
	}

	public CapitalAccount returnRightMoney(Long publisherId, Long optionTradeId, BigDecimal rightMoney) {
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		Date date = new Date();
		increaseAmount(account, rightMoney, date);
		// 保存流水
		flowDao.create(account.getPublisher(), CapitalFlowType.ReturnRightMoney, rightMoney.abs(), date,
				CapitalFlowExtendType.STOCKOPTIONTRADE, optionTradeId, account.getAvailableBalance());
		return findByPublisherId(publisherId);
	}

	public synchronized CapitalAccount optionProfit(Long publisherId, Long optionTradeId, BigDecimal profit) {
		CapitalAccount account = capitalAccountDao.retriveByPublisherId(publisherId);
		Date date = new Date();
		increaseAmount(account, profit, date);
		// 保存流水
		flowDao.create(account.getPublisher(), CapitalFlowType.StockOptionProfit, profit.abs(), date,
				CapitalFlowExtendType.STOCKOPTIONTRADE, optionTradeId, account.getAvailableBalance());
		return findByPublisherId(publisherId);
	}

	public Page<CapitalAccountAdminDto> adminPagesByQuery(CapitalAccountAdminQuery query) {
		String publisherIdCondition = "";
		if (query.getPublisherId() != null && query.getPublisherId() > 0) {
			publisherIdCondition = " and t2.id=" + query.getPublisherId() + " ";
		}
		String nameCondition = "";
		if (!StringUtil.isEmpty(query.getName())) {
			nameCondition = " and t3.name like '%" + query.getName() + "%' ";
		}
		String phoneCondition = "";
		if (!StringUtil.isEmpty(query.getPhone())) {
			phoneCondition = " and t2.phone like '%" + query.getPhone() + "%' ";
		}
		String stateCondition = "";
		if (query.getState() != null && query.getState() == 1) {
			stateCondition = " and (t1.state is null or t1.state=1) ";
		}
		if (query.getState() != null && query.getState() == 2) {
			stateCondition = " and t1.state=2 ";
		}
		String isTestCondition = "";
		if (query.getIsTest() != null) {
			if (query.getIsTest()) {
				isTestCondition = " and t2.is_test=1 ";
			} else {
				isTestCondition = " and (t2.is_test is null or t2.is_test=0) ";
			}
		}
		String sql = String.format(
				"select t1.id, t2.id as publisher_id, t2.head_portrait, t3.name, t3.id_card, t2.phone, t1.frozen_capital, t1.available_balance, t1.update_time, t2.create_time, t1.state, IFNULL(t4.recharge, 0), IFNULL(t5.withdraw, 0), t2.is_test from capital_account t1 "
						+ "LEFT JOIN publisher t2 on t2.id =t1.publisher_id "
						+ "LEFT JOIN real_name t3 on t3.resource_type=2 and t3.resource_id=t1.publisher_id "
						+ "LEFT JOIN (select publisher_id, SUM(amount) as recharge from capital_flow where type=1 GROUP BY publisher_id) t4 on t4.publisher_id=t1.publisher_id "
						+ "LEFT JOIN (select publisher_id, SUM(amount) as withdraw from capital_flow where type=2 GROUP BY publisher_id) t5 on t5.publisher_id=t1.publisher_id "
						+ "where 1=1 %s %s %s %s %s order by t2.create_time desc limit "
						+ query.getPage() * query.getSize() + "," + query.getSize(),
				publisherIdCondition, nameCondition, phoneCondition, stateCondition, isTestCondition);
		String countSql = "select count(*) " + sql.substring(sql.indexOf("from"), sql.indexOf("limit"));
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setPublisherId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setHeadPortrait", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setIdCard", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setFrozenCapital", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setAvailableBalance", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setUpdateTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setRegistTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(10), new MethodDesc("setState", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(11), new MethodDesc("setTotalRecharge", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(12), new MethodDesc("setTotalWithdraw", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(13), new MethodDesc("setIsTest", new Class<?>[] { Boolean.class }));
		List<CapitalAccountAdminDto> content = sqlDao.execute(CapitalAccountAdminDto.class, sql, setMethodMap);
		BigInteger totalElements = sqlDao.executeComputeSql(countSql);
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()),
				totalElements != null ? totalElements.longValue() : 0);
	}

	@Transactional
	public CapitalAccount revisionState(Long id, Integer state) {
		CapitalAccount capitalAccount = capitalAccountDao.retrieve(id);
		capitalAccount.setState(state);
		capitalAccount.setUpdateTime(new Date());
		return capitalAccountDao.update(capitalAccount);
	}

	@Transactional
	public CapitalAccount revisionAccount(Long staff, Long id, BigDecimal availableBalance) {
		CapitalAccount capitalAccount = capitalAccountDao.retrieve(id);
		if (capitalAccount == null) {
			throw new DataNotFoundException();
		}
		// 保存修改之前的账户数据
		CapitalAccountRecord record = new CapitalAccountRecord();
		record.setCapitalAccount(capitalAccount);
		record.setCreateTime(new Date());
		record.setStaff(staff);
		record.setPublisherPhone(capitalAccount.getPublisher().getPhone());
		// 修改之前的数据
		record.setUpdateBeforebalance(capitalAccount.getBalance());
		record.setUpdateBeforeAvailableBalance(capitalAccount.getAvailableBalance());
		record.setUpdateBeforeFrozenCapital(capitalAccount.getFrozenCapital());

		capitalAccount.setAvailableBalance(availableBalance);
		capitalAccount.setBalance(availableBalance.add(capitalAccount.getFrozenCapital()));
		capitalAccount.setUpdateTime(new Date());

		// 修改之后的数据
		record.setUpdateAfterBalance(capitalAccount.getBalance());
		record.setUpdateAfterAvailableBalance(capitalAccount.getAvailableBalance());
		record.setUpdateAfterFrozenCapital(capitalAccount.getFrozenCapital());
		recordDao.create(record);
		return capitalAccountDao.update(capitalAccount);
	}

	/**模拟账户*/

	/**
	 * 修改虚拟账号金额
	 * @return
	 */
	public CapitalAccount midifyDum(PublisherAdminDto dto){
		CapitalAccount acc = capitalAccountDao.retriveByPublisherId(dto.getId());
		if(acc==null){
			Publisher pu = publisherDao.retrieve(dto.getId());
			acc.setBalance(dto.getAvailableBalance());
			acc.setAvailableBalance(dto.getAvailableBalance());
			acc.setFrozenCapital(new BigDecimal(0.00));
			acc.setPublisherSerialCode(pu.getSerialCode());
			acc.setPublisherId(pu.getId());
			acc.setPublisher(pu);
			acc.setUpdateTime(new Date());
			CapitalAccount result = capitalAccountDao.create(acc);
			return result;
		}else{
			acc.setAvailableBalance(dto.getAvailableBalance());
			acc.setBalance(dto.getAvailableBalance().add(acc.getFrozenCapital()));
			return capitalAccountDao.update(acc);
		}

	}
}
