package com.waben.stock.datalayer.publisher.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.publisher.entity.CapitalAccount;
import com.waben.stock.datalayer.publisher.entity.FrozenCapital;
import com.waben.stock.datalayer.publisher.entity.WithdrawalsOrder;
import com.waben.stock.datalayer.publisher.repository.CapitalAccountDao;
import com.waben.stock.datalayer.publisher.repository.FrozenCapitalDao;
import com.waben.stock.datalayer.publisher.repository.WithdrawalsOrderDao;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.enums.FrozenCapitalStatus;
import com.waben.stock.interfaces.enums.FrozenCapitalType;
import com.waben.stock.interfaces.enums.WithdrawalsState;
import com.waben.stock.interfaces.exception.ServiceException;

/**
 * 提现订单 Service
 * 
 * @author luomengan
 *
 */
@Service
public class WithdrawalsOrderService {

	@Autowired
	private WithdrawalsOrderDao withdrawalsOrderDao;

	@Autowired
	private CapitalAccountDao accountDao;

	@Autowired
	private FrozenCapitalDao frozenDao;

	public WithdrawalsOrder save(WithdrawalsOrder withdrawalsOrder) {
		withdrawalsOrder.setCreateTime(new Date());
		if (withdrawalsOrder.getState() == WithdrawalsState.PROCESSING) {
			if(withdrawalsOrder.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
				throw new ServiceException(ExceptionConstant.ARGUMENT_EXCEPTION);
			}
			// 修改账户上的可用金额和冻结金额
			CapitalAccount account = accountDao.retriveByPublisherId(withdrawalsOrder.getPublisherId());
			if (withdrawalsOrder.getAmount().compareTo(account.getAvailableBalance()) > 0) {
				throw new ServiceException(ExceptionConstant.AVAILABLE_BALANCE_NOTENOUGH_EXCEPTION);
			}
			account.setFrozenCapital(account.getFrozenCapital().add(withdrawalsOrder.getAmount()));
			account.setAvailableBalance(account.getAvailableBalance().subtract(withdrawalsOrder.getAmount()));
			// 添加冻结记录
			FrozenCapital frozen = new FrozenCapital();
			frozen.setAmount(withdrawalsOrder.getAmount());
			frozen.setFrozenTime(new Date());
			frozen.setPublisherId(withdrawalsOrder.getPublisherId());
			frozen.setStatus(FrozenCapitalStatus.Frozen);
			frozen.setType(FrozenCapitalType.Withdrawals);
			frozen.setWithdrawalsNo(withdrawalsOrder.getWithdrawalsNo());
			frozenDao.create(frozen);
		}
		return withdrawalsOrderDao.create(withdrawalsOrder);
	}

	public WithdrawalsOrder add(WithdrawalsOrder withdrawalsOrder) {
		withdrawalsOrder.setCreateTime(new Date());
		return withdrawalsOrderDao.create(withdrawalsOrder);
	}

	public WithdrawalsOrder changeState(String withdrawalsNo, WithdrawalsState state) {
		WithdrawalsOrder withdrawalsOrder = withdrawalsOrderDao.retrieveByWithdrawalsNo(withdrawalsNo);
		withdrawalsOrder.setState(state);
		withdrawalsOrder.setUpdateTime(new Date());
		return withdrawalsOrderDao.update(withdrawalsOrder);
	}

	public WithdrawalsOrder findByWithdrawalsNo(String withdrawalsNo) {
		return withdrawalsOrderDao.retrieveByWithdrawalsNo(withdrawalsNo);
	}

	public WithdrawalsOrder revision(WithdrawalsOrder withdrawalsOrder) {
		withdrawalsOrder.setUpdateTime(new Date());
		return withdrawalsOrderDao.update(withdrawalsOrder);
	}

}
