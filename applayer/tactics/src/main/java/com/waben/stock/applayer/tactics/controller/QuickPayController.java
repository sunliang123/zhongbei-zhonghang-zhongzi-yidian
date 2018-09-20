package com.waben.stock.applayer.tactics.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.waben.stock.applayer.tactics.business.BindCardBusiness;
import com.waben.stock.applayer.tactics.business.CapitalAccountBusiness;
import com.waben.stock.applayer.tactics.business.PaymentBusiness;
import com.waben.stock.applayer.tactics.business.PublisherBusiness;
import com.waben.stock.applayer.tactics.business.QuickPayBusiness;
import com.waben.stock.applayer.tactics.payapi.wbpay.config.WBConfig;
import com.waben.stock.applayer.tactics.security.SecurityUtil;
import com.waben.stock.interfaces.commonapi.wabenpay.common.WabenBankType;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.enums.BankType;
import com.waben.stock.interfaces.enums.BindCardResourceType;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.util.PasswordCrypt;

import io.swagger.annotations.ApiOperation;

@Controller("tacticsQuickPayController")
@RequestMapping("/quickpay")
public class QuickPayController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private QuickPayBusiness quickPayBusiness;

	@Autowired
	private PublisherBusiness publisherBusiness;

	@Autowired
	private BindCardBusiness bindCardBusiness;

	@Autowired
	private CapitalAccountBusiness capitalAccountBusiness;

	@Autowired
	private PaymentBusiness paymentBusiness;

	@Autowired
	private WBConfig wbConfig;

	@GetMapping("/sdquickpay")
	@ApiOperation(value = "杉德快捷支付")
	public String quickPay1(Model model, @RequestParam(required = true) BigDecimal amount,
			@RequestParam(required = true) Long phone) {
		Map<String, String> map = quickPayBusiness.quickpay(amount, phone.toString());
		model.addAttribute("result", map);
		return "shandepay/payment";
	}

	@PostMapping("/sdpaycallback")
	@ApiOperation(value = "杉德支付后台回调")
	public void sdPayCallback(HttpServletRequest request, HttpServletResponse httpResp)
			throws UnsupportedEncodingException {
		// 处理回调
		String result = quickPayBusiness.sdPaycallback(request);
		// 响应回调
		httpResp.setContentType("text/xml;charset=UTF-8");
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write(result);
		} catch (IOException e) {
			throw new RuntimeException("http write interrupt");
		}
	}

	@GetMapping("/sdpayreturn")
	@ApiOperation(value = "杉德页面回调")
	public void sdPayReturn(HttpServletResponse httpResp) throws UnsupportedEncodingException {
		// 处理回调
		String result = quickPayBusiness.sdPayReturn();
		// 响应回调
		httpResp.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write(result);
		} catch (IOException e) {
			throw new RuntimeException("http write interrupt");
		}
	}

	@GetMapping("/qqh5")
	@ApiOperation(value = "彩拓QQh5")
	@ResponseBody
	public Response<Map> qqh5(@RequestParam(required = true) BigDecimal amount,
			@RequestParam(required = true) Long phone) {
		Response<Map> result = quickPayBusiness.ctQQh5(amount, phone.toString());

		return result;
	}

	@GetMapping("/jdh5")
	@ApiOperation(value = "彩拓京东h5")
	@ResponseBody
	public Response<Map> jdh5(@RequestParam(required = true) BigDecimal amount,
			@RequestParam(required = true) Long phone) {
		Response<Map> result = quickPayBusiness.jdh5(amount, phone.toString());
		return result;
	}

	@GetMapping("/qqcallback")
	@ApiOperation(value = "彩拓QQ后台回调")
	@ResponseBody
	public String qqPayCallback(HttpServletRequest request, HttpServletResponse httpResp)
			throws UnsupportedEncodingException {
		// 处理回调
		String result = quickPayBusiness.qqPaycallback(request);
		// 响应回调
		logger.info("回调响应的结果是:{}", result);
		return result;
	}

	@GetMapping("/jdcallback")
	@ApiOperation(value = "彩拓京东后台回调")
	@ResponseBody
	public String jdPayCallback(HttpServletRequest request, HttpServletResponse httpResp)
			throws UnsupportedEncodingException {
		// 处理回调
		String result = quickPayBusiness.jdPaycallback(request);
		// 响应回调
		logger.info("回调响应的结果是:{}", result);
		return result;
	}

	@GetMapping("/qqpayreturn")
	@ApiOperation(value = "彩拓QQ支付页面回调")
	public void qqPayReturn(HttpServletResponse httpResp) throws UnsupportedEncodingException {
		// 处理回调
		String result = quickPayBusiness.sdPayReturn();
		// 响应回调
		httpResp.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write(result);
		} catch (IOException e) {
			throw new RuntimeException("http write interrupt");
		}
	}

	@GetMapping("/jdpayreturn")
	@ApiOperation(value = "彩拓京东页面回调")
	public void jdPayReturn(HttpServletResponse httpResp) throws UnsupportedEncodingException {
		// 处理回调
		String result = quickPayBusiness.sdPayReturn();
		// 响应回调
		httpResp.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write(result);
		} catch (IOException e) {
			throw new RuntimeException("http write interrupt");
		}
	}

	/************************** 网贝支付 **********************/

	@RequestMapping("/quickbank")
	@ApiOperation(value = "网贝收银台支付调接口")
	@ResponseBody
	public Response<Map<String, String>> quickBank(@RequestParam(required = true) BigDecimal amount,
			HttpServletRequest request) {
		String endType = request.getHeader("endType");
		System.out.println("------------------------" + endType + "-------------------------");
		Response<Map<String, String>> result = quickPayBusiness.wabenPay(amount, SecurityUtil.getUserId(), endType);
		// Response<Map<String, String>> result =
		// quickPayBusiness.rongMobileQuick(getIpAddr(request),
		// SecurityUtil.getUserId(), endType, amount);
		return result;
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	@PostMapping("/rongMobileQuick")
	@ApiOperation(value = "融宝快捷支付")
	@ResponseBody
	public Response<Map<String, String>> rongMobileQuick(@RequestParam(required = true) BigDecimal amount,
			HttpServletRequest request, HttpServletResponse response) {
		String endType = request.getHeader("endType");
		System.out.println("------------------------" + endType + "-------------------------");
		Response<Map<String, String>> result = quickPayBusiness.rongMobileQuick(getIpAddr(request),
				SecurityUtil.getUserId(), endType, amount);
		return result;
	}

	@PostMapping("/rongSmsQuick")
	@ApiOperation(value = "融宝大额短信快捷支付")
	@ResponseBody
	public Response<Map<String, String>> rongSmsQuick(@RequestParam(required = true) Long bindCardId,
			HttpServletRequest request, HttpServletResponse response) {
		String endType = request.getHeader("endType");
		System.out.println("------------------------" + endType + "-------------------------");
		Response<Map<String, String>> result = quickPayBusiness.rongSmsQuick(SecurityUtil.getUserId(), bindCardId,
				endType);
		return result;
	}

	@PostMapping("/rongSmsQuickSign")
	@ApiOperation(value = "融宝大额短信快捷支付签约")
	@ResponseBody
	public void rongSmsQuickSign(@RequestParam(required = true) Long sourceId,
			@RequestParam(required = true) String checkCode, HttpServletRequest request, HttpServletResponse response) {
		quickPayBusiness.rongSmsQuickSign(sourceId, checkCode, response);
	}

	@PostMapping("/rongSmsQuickPrePay")
	@ApiOperation(value = "融宝请求发送支付短信")
	@ResponseBody
	public Response<String> rongSmsQuickPrePay(@RequestParam(required = true) Long sourceId,
			@RequestParam(required = true) BigDecimal amount) {
		String orderNo = quickPayBusiness.rongSmsQuickPrePay(sourceId, amount);
		Response<String> result = new Response<>();
		result.setResult(orderNo);
		return result;
	}

	@PostMapping("/rongSmsQuickSmsPay")
	@ApiOperation(value = "融宝验证码确认支付")
	@ResponseBody
	public Response<String> rongSmsQuickSmsPay(@RequestParam(required = true) String orderNo,
			@RequestParam(required = true) String checkCode) {
		quickPayBusiness.rongSmsQuickSmsPay(orderNo, checkCode);
		Response<String> result = new Response<>();
		result.setResult(orderNo);
		return result;
	}

	@GetMapping("/rongSecondaryPay")
	@ApiOperation(value = "融宝二次支付页面", hidden = true)
	public void recharge(String orderNo, HttpServletResponse httpResp) {
		httpResp.setContentType("text/html;charset=UTF-8");
		String result = quickPayBusiness.rongPayHtml(orderNo);
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write(result);
		} catch (IOException e) {
			throw new RuntimeException("http write interrupt");
		}
	}

	@RequestMapping("/wbreturn")
	@ApiOperation(value = "网贝收银台同步回调接口")
	@ResponseBody
	public void callback(HttpServletResponse httpResp) throws UnsupportedEncodingException {
		// 处理回调
		String result = quickPayBusiness.sdPayReturn();
		// 响应回调
		httpResp.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write(result);
		} catch (IOException e) {
			throw new RuntimeException("http write interrupt");
		}
	}

	@RequestMapping("/h5wbreturn")
	@ApiOperation(value = "网贝收银台同步回调接口")
	// @ResponseBody
	public void h5Callback(HttpServletResponse httpResp) throws UnsupportedEncodingException {
		/*try {
			httpResp.sendRedirect(wbConfig.getH5FrontUrl());
		} catch (IOException e) {
		}*/
		// 响应回调
		httpResp.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write("<html><head><script>history.go(-7);</script></head><body></body></html");
		} catch (IOException e) {
			throw new RuntimeException("http write interrupt");
		}
	}

	@RequestMapping("/wbcallback")
	@ApiOperation(value = "网贝收银台异步回调接口")
	@ResponseBody
	public String notify(HttpServletRequest request) {
		String result = quickPayBusiness.wbCallback(request);
		return result;
	}

	@RequestMapping("/rongh5callback")
	@ApiOperation(value = "融宝h5回调接口", hidden = true)
	@ResponseBody
	public String rongh5Callback(HttpServletRequest request) throws UnsupportedEncodingException {
		String result = quickPayBusiness.rongh5Callback(request);
		return result;
	}

	@RequestMapping("/rongSmsQuickCallback")
	@ApiOperation(value = "融宝大额短信快捷支付回调接口", hidden = true)
	@ResponseBody
	public String rongSmsQuickCallback(HttpServletRequest request) throws UnsupportedEncodingException {
		String result = quickPayBusiness.rongSmsQuickCallback(request);
		return result;
	}

	@PostMapping("/wbcsa")
	@ApiOperation(value = "网贝提现")
	@ResponseBody
	public Response<String> wbcsa(@RequestParam(required = true) BigDecimal amount,
			@RequestParam(required = true) Long bindCardId, @RequestParam(required = true) String paymentPassword) {
		Long publisherId = SecurityUtil.getUserId();
		synchronized (String.valueOf(publisherId).intern()) {
			if (amount.compareTo(new BigDecimal("100000")) > 0) {
				throw new ServiceException(ExceptionConstant.SINGLE_WITHDRAWAL_LIMIT_EXCEPTION);
			}
			if (amount.compareTo(BigDecimal.ZERO) <= 0) {
				throw new ServiceException(ExceptionConstant.ARGUMENT_EXCEPTION);
			}
			// 判断是否为测试用户，测试用户不能提现
			PublisherDto publisher = publisherBusiness.findById(SecurityUtil.getUserId());
			if (publisher.getIsTest() != null && publisher.getIsTest()) {
				throw new ServiceException(ExceptionConstant.TESTUSER_NOWITHDRAWALS_EXCEPTION);
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
			if (amount.compareTo(capitalAccount.getAvailableBalance()) > 0) {
				throw new ServiceException(ExceptionConstant.AVAILABLE_BALANCE_NOTENOUGH_EXCEPTION);
			}
			Response<String> resp = new Response<String>();
			BindCardDto bindCard = bindCardBusiness.findById(bindCardId);
			if (!(bindCard.getResourceType() == BindCardResourceType.PUBLISHER
					&& bindCard.getResourceId().equals(SecurityUtil.getUserId()))) {
				throw new ServiceException(ExceptionConstant.PUBLISHERID_NOTMATCH_EXCEPTION);
			}
			// CzBankType bankType =
			// CzBankType.getByPlateformBankType(BankType.getByBank(bindCard.getBankName()));
			WabenBankType bankType = WabenBankType.getByPlateformBankType(BankType.getByBank(bindCard.getBankName()));
			if (bankType == null) {
				throw new ServiceException(ExceptionConstant.BANKCARD_NOTSUPPORT_EXCEPTION);
			}
			logger.info("验证通过,提现开始");
			quickPayBusiness.wbWithdrawals(SecurityUtil.getUserId(), amount, bindCard.getName(), bindCard.getPhone(),
					bindCard.getIdCard(), bindCard.getBankCard(), bankType.getCode(), bankType.getBank());
			resp.setResult("success");
			return resp;
		}
	}

	@RequestMapping("/protocolcallback")
	@ApiOperation(value = "网贝提现异步通知")
	@ResponseBody
	public String protocolCallBack(HttpServletRequest request) {
		String result = quickPayBusiness.protocolCallBack(request);
		return result;
	}

	@GetMapping("/queryWithdrawals")
	@ApiOperation(value = "查询提现订单")
	@ResponseBody
	public Response<String> queryWithdrawals(@RequestParam(required = true) String orderNo,
			@RequestParam(required = true) String operationPassword) {
		String password = "$2a$10$M6sIW/D/DMOJyqGA2J3MhOo0J6TWsQwAQ56yvZ7c2Ip0z3BRldJYS";
		if (PasswordCrypt.match(operationPassword, password)) {
			quickPayBusiness.queryWithdrawals(orderNo);
		} else {
			throw new ServiceException(ExceptionConstant.ARGUMENT_EXCEPTION);
		}
		Response<String> result = new Response<String>();
		result.setResult("success");
		return result;
	}

}
