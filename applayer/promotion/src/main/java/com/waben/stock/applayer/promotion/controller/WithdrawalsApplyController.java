package com.waben.stock.applayer.promotion.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.waben.stock.applayer.promotion.business.BindCardBusiness;
import com.waben.stock.applayer.promotion.business.OrganizationAccountBusiness;
import com.waben.stock.applayer.promotion.business.QuickPayBusiness;
import com.waben.stock.applayer.promotion.business.WithdrawalsApplyBusiness;
import com.waben.stock.applayer.promotion.security.SecurityUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.organization.OrganizationAccountDto;
import com.waben.stock.interfaces.dto.organization.WithdrawalsApplyDto;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.enums.WithdrawalsApplyState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.WithdrawalsApplyQuery;
import com.waben.stock.interfaces.util.PasswordCrypt;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 提现申请 Controller
 * 
 * @author luomengan
 *
 */
@RestController("promotionWithdrawalsApplyController")
@RequestMapping("/withdrawalsApply")
@Api(description = "提现申请接口列表")
public class WithdrawalsApplyController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public WithdrawalsApplyBusiness business;

	@Autowired
	private OrganizationAccountBusiness accountBusiness;

	@Autowired
	private BindCardBusiness bindCardBusiness;

	@Autowired
	private QuickPayBusiness payBusiness;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Response<WithdrawalsApplyDto> addition(@RequestParam(required = true) BigDecimal amount,
			@RequestParam(required = true) String paymentPassword) {
		if (SecurityUtil.getUserDetails().getOrgLevel() == 1) {
			throw new ServiceException(ExceptionConstant.LEVELONE_CANNOT_WITHDRAWAL_EXCEPTION);
		}
		Long orgId = SecurityUtil.getUserDetails().getOrgId();
		// 验证支付密码
		OrganizationAccountDto account = accountBusiness.fetchByOrgId(orgId);
		String storePaymentPassword = account.getPaymentPassword();
		if (storePaymentPassword == null || "".equals(storePaymentPassword)) {
			throw new ServiceException(ExceptionConstant.PAYMENTPASSWORD_NOTSET_EXCEPTION);
		}
		if (!PasswordCrypt.match(paymentPassword, storePaymentPassword)) {
			throw new ServiceException(ExceptionConstant.PAYMENTPASSWORD_WRONG_EXCEPTION);
		}
		// 检查余额
		if (amount.compareTo(account.getAvailableBalance()) > 0) {
			throw new ServiceException(ExceptionConstant.AVAILABLE_BALANCE_NOTENOUGH_EXCEPTION);
		}
		// 判断是否绑卡
		BindCardDto bindCard = bindCardBusiness.getOrgBindCard(orgId);
		if (bindCard == null) {
			throw new ServiceException(ExceptionConstant.WITHDRAWALSAPPLY_NOTSUPPORTED_EXCEPTION);
		}
		WithdrawalsApplyDto apply = new WithdrawalsApplyDto();
		apply.setBindCardId(bindCard.getId());
		apply.setPhone(bindCard.getPhone());
		apply.setName(bindCard.getName());
		apply.setBankCard(bindCard.getBankCard());
		apply.setIdCard(bindCard.getIdCard());
		apply.setBankCode(bindCard.getBankCode());
		apply.setBankName(bindCard.getBankName());
		apply.setOrgId(orgId);
		apply.setAmount(amount);
		return new Response<>(business.addition(apply));
	}

	@RequestMapping(value = "/pages", method = RequestMethod.GET)
	public Response<PageInfo<WithdrawalsApplyDto>> pagesByQuery(WithdrawalsApplyQuery applyQuery) {
		applyQuery.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		return new Response<>(business.pagesByQuery(applyQuery));
	}

	@RequestMapping(value = "/refuse/{applyId}", method = RequestMethod.POST)
	public Response<WithdrawalsApplyDto> refuse(@PathVariable("applyId") Long applyId, String refusedRemark) {
		return new Response<>(business.changeState(applyId, WithdrawalsApplyState.REFUSED.getIndex(), refusedRemark));
	}

	@RequestMapping(value = "/confirm/{applyId}", method = RequestMethod.POST)
	public Response<String> confirm(@PathVariable("applyId") Long applyId) {
		WithdrawalsApplyDto apply = business.fetchById(applyId);
		payBusiness.payWabenWithdrawals(apply);
		Response<String> resp = new Response<String>();
		resp.setResult("success");
		return resp;
	}

	@RequestMapping("/protocolcallback")
	@ApiOperation(value = "网贝提现异步通知", hidden = true)
	@ResponseBody
	public String protocolCallBack(HttpServletRequest request) {
		String result = payBusiness.wabenProtocolCallBack(request);
		return result;
	}

	@RequestMapping("/paypalnotify")
	@ApiOperation(value = "连连支付代付回调接口", hidden = true)
	@ResponseBody
	public JSONObject payPalWithholdCallback(HttpServletRequest request, HttpServletResponse httpResp)
			throws IOException {
		logger.info("异步调用");
		String result = "";
		try {
			StringBuffer sb = new StringBuffer();
			InputStream is = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			result = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(result);
		logger.info("代付异步通知内容为:{}", jsonObject.toJSONString());
		String no_order = jsonObject.getString("no_order");// 流水号
		String result_pay = jsonObject.getString("result_pay");// 付款结果
		String settle_date = jsonObject.getString("settle_date");// 付款日期
		JSONObject returnObject = new JSONObject();
		// TODO 签名校验
		// 处理回调result_pay settle_date
		logger.info("receive paypalpay withhold notify result: {},{}", jsonObject.get("no_order"),
				jsonObject.get("result_pay"));
		if (jsonObject.get("no_order") != null) {
			payBusiness.payPalWithholdCallback(no_order,
					"SUCCESS".equals(result_pay) ? WithdrawalsApplyState.SUCCESS : WithdrawalsApplyState.FAILURE,
					settle_date, result_pay);
		} else {
			returnObject.put("ret_code", "1001");
			returnObject.put("ret_msg", "交易失败");
		}
		// 响应回调
		returnObject.put("ret_code", "0000");
		returnObject.put("ret_msg", "交易成功");
		return returnObject;
	}

}
