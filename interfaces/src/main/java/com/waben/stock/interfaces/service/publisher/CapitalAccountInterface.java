package com.waben.stock.interfaces.service.publisher;

import java.math.BigDecimal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.admin.publisher.CapitalAccountAdminDto;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.dto.publisher.FrozenCapitalDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.CapitalAccountQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.admin.publisher.CapitalAccountAdminQuery;

public interface CapitalAccountInterface {

	/**
	 * 分页查询资金账户（管理后台）
	 * 
	 * @param query
	 *            查询条件
	 * @return 资金账户分页数据
	 */
	@RequestMapping(value = "/adminpages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<CapitalAccountAdminDto>> adminPagesByQuery(@RequestBody CapitalAccountAdminQuery query);

	@RequestMapping(value = "/pages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<CapitalAccountDto>> pages(@RequestBody CapitalAccountQuery publisherQuery);

	@RequestMapping(value = "/publisherSerialCode/{serialCode}", method = RequestMethod.GET)
	Response<CapitalAccountDto> fetchByPublisherSerialCode(@PathVariable("serialCode") String serialCode);

	@RequestMapping(value = "/{capitalAccountId}/view", method = RequestMethod.GET)
	Response<CapitalAccountDto> fetchById(@PathVariable("capitalAccountId") Long capitalAccountId);

	@RequestMapping(value = "/publisherId/{publisherId}", method = RequestMethod.GET)
	Response<CapitalAccountDto> fetchByPublisherId(@PathVariable("publisherId") Long publisherId);

	@RequestMapping(value = "/{publisherId}/recharge/{amount}", method = RequestMethod.POST)
	Response<CapitalAccountDto> recharge(@PathVariable("publisherId") Long publisherId,
			@PathVariable("amount") BigDecimal amount, @RequestParam("rechargeId") Long rechargeId);

	@RequestMapping(value = "/{publisherId}/withdrawals", method = RequestMethod.POST)
	Response<CapitalAccountDto> withdrawals(@PathVariable("publisherId") Long publisherId,
			@RequestParam("withdrawalsId") Long withdrawalsId,
			@RequestParam(name = "withdrawalsStateIndex") String withdrawalsStateIndex);

	@RequestMapping(value = "/{publisherId}/csa/{amount}", method = RequestMethod.POST)
	Response<CapitalAccountDto> csa(@PathVariable("publisherId") Long publisherId,
			@PathVariable("amount") BigDecimal amount, @RequestParam("withdrawalsId") Long withdrawalsId);

	@RequestMapping(value = "/{publisherId}/{buyRecordId}/serviceFee/{serviceFee}/reserveFund/{reserveFund}", method = RequestMethod.POST)
	Response<CapitalAccountDto> serviceFeeAndReserveFund(@PathVariable("publisherId") Long publisherId,
			@PathVariable("buyRecordId") Long buyRecordId, @PathVariable("serviceFee") BigDecimal serviceFee,
			@PathVariable("reserveFund") BigDecimal reserveFund, @RequestParam("deferredFee") BigDecimal deferredFee);

	@RequestMapping(value = "/{publisherId}/revoke/{buyRecordId}/serviceFee/{serviceFee}", method = RequestMethod.POST)
	Response<CapitalAccountDto> revoke(@PathVariable("publisherId") Long publisherId,
			@PathVariable("buyRecordId") Long buyRecordId, @PathVariable("serviceFee") BigDecimal serviceFee,
			@RequestParam("deferredFee") BigDecimal deferredFee);

	@RequestMapping(value = "/frozenCapital/{publisherId}/{buyRecordId}/", method = RequestMethod.GET)
	Response<FrozenCapitalDto> fetchFrozenCapital(@PathVariable("publisherId") Long publisherId,
			@PathVariable("buyRecordId") Long buyRecordId);

	@RequestMapping(value = "/{publisherId}/{buyRecordId}/deferredCharges/{deferredCharges}", method = RequestMethod.POST)
	Response<CapitalAccountDto> deferredCharges(@PathVariable("publisherId") Long publisherId,
			@PathVariable("buyRecordId") Long buyRecordId, @PathVariable("deferredCharges") BigDecimal deferredCharges);

	@RequestMapping(value = "/{publisherId}/{buyRecordId}/returnCompensate/{profitOrLoss}", method = RequestMethod.POST)
	Response<CapitalAccountDto> returnReserveFund(@PathVariable("publisherId") Long publisherId,
			@PathVariable("buyRecordId") Long buyRecordId,
			@RequestParam(name = "buyRecordSerialCode") String buyRecordSerialCode,
			@PathVariable("profitOrLoss") BigDecimal profitOrLoss);

	@RequestMapping(value = "/{publisherId}/{buyRecordId}/returnDeferredFee/{deferredFee}", method = RequestMethod.POST)
	Response<CapitalAccountDto> returnDeferredFee(@PathVariable("publisherId") Long publisherId,
			@PathVariable("buyRecordId") Long buyRecordId, @PathVariable("deferredFee") BigDecimal deferredFee);

	@RequestMapping(value = "/{publisherId}/modifyPaymentPassword", method = RequestMethod.PUT)
	Response<Void> modifyPaymentPassword(@PathVariable("publisherId") Long publisherId,
			@RequestParam(name = "paymentPassword") String paymentPassword);

	@RequestMapping(value = "/modify", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<CapitalAccountDto> modifyCapitalAccount(@RequestBody CapitalAccountDto capitalAccountDto);

	/**************************************** 期权相关 ***************************************/
	@RequestMapping(value = "/{publisherId}/{optionTradeId}/rightMoney/{rightMoney}", method = RequestMethod.POST)
	Response<CapitalAccountDto> rightMoney(@PathVariable("publisherId") Long publisherId,
			@PathVariable("optionTradeId") Long optionTradeId, @PathVariable("rightMoney") BigDecimal rightMoney);

	@RequestMapping(value = "/{publisherId}/{optionTradeId}/returnRightMoney/{rightMoney}", method = RequestMethod.POST)
	Response<CapitalAccountDto> returnRightMoney(@PathVariable("publisherId") Long publisherId,
			@PathVariable("optionTradeId") Long optionTradeId, @PathVariable("rightMoney") BigDecimal rightMoney);

	@RequestMapping(value = "/{publisherId}/{optionTradeId}/profit/{profit}", method = RequestMethod.POST)
	Response<CapitalAccountDto> optionProfit(@PathVariable("publisherId") Long publisherId,
			@PathVariable("optionTradeId") Long optionTradeId, @PathVariable("profit") BigDecimal profit);

	@RequestMapping(value = "/state/{id}/{state}", method = RequestMethod.PUT)
	Response<CapitalAccountDto> modifyState(@PathVariable("id") Long id,@PathVariable("state") Integer state);

	@RequestMapping(value = "/account/{staff}/{id}/{availableBalance}", method = RequestMethod.PUT)
	Response<CapitalAccountDto> modifyAccount(@PathVariable("staff") Long staff,@PathVariable("id") Long id,@PathVariable("availableBalance") BigDecimal availableBalance);

}