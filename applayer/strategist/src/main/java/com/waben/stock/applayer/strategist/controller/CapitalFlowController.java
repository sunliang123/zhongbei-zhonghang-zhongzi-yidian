package com.waben.stock.applayer.strategist.controller;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.strategist.business.CapitalFlowBusiness;
import com.waben.stock.applayer.strategist.dto.publisher.CapitalFlowWithExtendDto;
import com.waben.stock.applayer.strategist.security.SecurityUtil;
import com.waben.stock.interfaces.enums.CapitalFlowType;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.CapitalFlowQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资金流水 Controller
 * 
 * @author luomengan
 *
 */
@RestController("strategistCapitalFlowController")
@RequestMapping("/capitalFlow")
@Api(description = "资金流水")
public class CapitalFlowController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CapitalFlowBusiness capitalFlowBusiness;

	@GetMapping("/pages")
	@ApiOperation(value = "用户资金流水", notes = "range表示统计范围，0全部，1最近一周，2最近一个月，3最近半年, 4最近一年, 5今天;flowType表示流水类型，0全部，1充值，2提现，3服务费, 4冻结履约保证金, 5递延费, 6退回履约保证金, 7卖出结算, 8推广佣金, 9期权权利金, 10申购失败退回期权权利金, 11期权盈利;")
	public Response<PageInfo<CapitalFlowWithExtendDto>> publisherCapitalFlow(int page, int size,
			@RequestParam(defaultValue = "0") int range, @RequestParam(defaultValue = "0") int flowType) {
		CapitalFlowQuery query = new CapitalFlowQuery(page, size);
		Date startTime = null;
		if (range == 1) {
			startTime = new DateTime(new Date()).minusHours(7 * 24).toDate();
		} else if (range == 2) {
			startTime = new DateTime(new Date()).minusHours(30 * 24).toDate();
		} else if (range == 3) {
			startTime = new DateTime(new Date()).minusHours(180 * 24).toDate();
		} else if (range == 4) {
			startTime = new DateTime(new Date()).minusHours(365 * 24).toDate();
		} else if (range == 5) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			startTime = cal.getTime();
		}
		CapitalFlowType[] types = null;
		if (flowType == 1) {
			types = new CapitalFlowType[] { CapitalFlowType.Recharge };
		} else if (flowType == 2) {
			types = new CapitalFlowType[] { CapitalFlowType.Withdrawals };
		} else if (flowType == 3) {
			types = new CapitalFlowType[] { CapitalFlowType.ServiceFee };
		} else if (flowType == 4) {
			types = new CapitalFlowType[] { CapitalFlowType.ReserveFund };
		} else if (flowType == 5) {
			types = new CapitalFlowType[] { CapitalFlowType.DeferredCharges };
		} else if (flowType == 6) {
			types = new CapitalFlowType[] { CapitalFlowType.ReturnReserveFund };
		} else if (flowType == 7) {
			types = new CapitalFlowType[] { CapitalFlowType.Loss, CapitalFlowType.Profit };
		} else if (flowType == 8) {
			types = new CapitalFlowType[] { CapitalFlowType.Promotion };
		} else if (flowType == 9) {
			types = new CapitalFlowType[] { CapitalFlowType.RightMoney };
		} else if (flowType == 10) {
			types = new CapitalFlowType[] { CapitalFlowType.ReturnRightMoney };
		} else if (flowType == 11) {
			types = new CapitalFlowType[] { CapitalFlowType.StockOptionProfit };
		}
		query.setTypes(types);
		query.setStartTime(startTime);
		query.setPublisherId(SecurityUtil.getUserId());
		return new Response<>(capitalFlowBusiness.pages(query));
	}

}
