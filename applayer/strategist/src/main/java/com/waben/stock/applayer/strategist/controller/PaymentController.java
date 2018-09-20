package com.waben.stock.applayer.strategist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.strategist.business.BindCardBusiness;
import com.waben.stock.applayer.strategist.business.CapitalAccountBusiness;
import com.waben.stock.applayer.strategist.business.PaymentBusiness;
import com.waben.stock.applayer.strategist.business.PublisherBusiness;
import com.waben.stock.applayer.strategist.payapi.czpay.config.CzBankType;
import com.waben.stock.applayer.strategist.payapi.wabenpay.config.WBConfig;
import com.waben.stock.applayer.strategist.security.SecurityUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.enums.BankType;
import com.waben.stock.interfaces.enums.PaymentState;
import com.waben.stock.interfaces.enums.WithdrawalsState;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.util.Md5Util;
import com.waben.stock.interfaces.util.PasswordCrypt;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 支付 Controller
 * 
 * @author luomengan
 *
 */
@RestController("strategistPaymentController")
@RequestMapping("/payment")
@Api(description = "支付")
public class PaymentController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PaymentBusiness paymentBusiness;

	@Autowired
	private BindCardBusiness bindCardBusiness;

	@Autowired
	private CapitalAccountBusiness capitalAccountBusiness;

	@Autowired
	private PublisherBusiness publisherBusiness;
	
	@Autowired
    private WBConfig wbConfig;

	@GetMapping("/recharge")
	@ApiOperation(value = "充值", notes = "paymentType:1银联支付")
	public void recharge(Long publisherId, Integer paymentType, BigDecimal amount, HttpServletResponse httpResp) {
		/*
		 * PaymentType payment =
		 * PaymentType.getByIndex(String.valueOf(paymentType)); PayRequest
		 * payReq = null; if (payment == PaymentType.UnionPay) { UnionPayRequest
		 * union = new UnionPayRequest(); union.setAmount(amount); CzBankType
		 * bankType =
		 * CzBankType.getByPlateformBankType(BankType.getByCode(bankCode)); if
		 * (bankType == null) { bankType = CzBankType.JSYH; }
		 * union.setBankCode(bankType.getCode()); payReq = union; } else { throw
		 * new RuntimeException("not supported paymentType " + paymentType); }
		 * 
		 * httpResp.setContentType("text/html;charset=UTF-8"); String result =
		 * paymentBusiness.recharge(publisherId, payReq); try { PrintWriter
		 * writer = httpResp.getWriter(); writer.write(result); } catch
		 * (IOException e) { throw new RuntimeException("http write interrupt");
		 * }
		 */
		try {
			logger.info("请求网贝网银支付:{}_{}", publisherId, amount);
			String redirectURL = paymentBusiness.wabenNetbankPay(publisherId, amount);
			httpResp.sendRedirect(redirectURL);
		} catch (Exception ex) {
			ex.printStackTrace();
			httpResp.setContentType("text/html;charset=UTF-8");
			try {
				PrintWriter writer = httpResp.getWriter();
				writer.write("请求支付发生异常，请稍后再试，或者联系客服!");
			} catch (IOException e) {
			}
		}
	}

	@RequestMapping("/wabennetbankpaynotify")
	@ApiOperation(value = "网贝网银支付通知")
	@ResponseBody
	public String wabenNetbankPayNotify(HttpServletRequest request) {
//		String amount = request.getParameter("amount");
//		String merchantNo = request.getParameter("merchantNo");
//		String outTradeNo = request.getParameter("outTradeNo");
//		String transactNo = request.getParameter("transactNo");
//		String transactTime = request.getParameter("transactTime");
//		String tradeType = request.getParameter("tradeType");
//		String sign = request.getParameter("sign");
//		logger.info("接收网贝网银支付:outTradeNo_{}_transactNo_{}_amount_{}", outTradeNo, transactNo, amount);
//		// TODO 验签
//		// 完成支付
//		paymentBusiness.payCallback(outTradeNo, PaymentState.Paid);
//		return "SUCCESS";
		Map<String, String> result = paramter2Map(request);
        logger.info("网贝支付回调的结果是:{}", result);
        String paymentNo = result.get("out_order_no");
        String orderNo = result.get("order_no");
        String sign = result.get("sign");
        String code = result.get("code");
        // 验证签名
        String checkSign = Md5Util.md5(wbConfig.getMerchantNo() + orderNo + wbConfig.getKey()).toUpperCase();
        if(sign.equalsIgnoreCase(checkSign)) {
        	if("1".equals(code)) {
        		paymentBusiness.payCallback(paymentNo, PaymentState.Paid);
        	}
        	return "success";
        } else {
        	logger.error("网贝支付回调验证签名失败");
        	return "failed";
        }
	}

	@RequestMapping("/unionpaytempfronturl")
	@ApiOperation(value = "网贝网银支付H5跳转临时地址")
	// @ResponseBody
	public void unionpayTempFrontUrl(HttpServletResponse httpResp) {
		/*try {
			httpResp.sendRedirect(wbConfig.getUnionpayFrontUrl());
		} catch (IOException e) {
		}*/
		// 响应回调
		httpResp.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write("<html><head><script>location.href='http://zhongbeicl.com';</script></head><body></body></html");
		} catch (IOException e) {
			throw new RuntimeException("http write interrupt");
		}
	}
	
	@RequestMapping("/wabennetbankpay/h5wbreturn")
	@ApiOperation(value = "网贝收银台同步回调接口")
	@ResponseBody
	public void wabenNetbankpayH5wbreturn(HttpServletResponse httpResp) throws UnsupportedEncodingException {
		StringBuilder html = new StringBuilder();
		html.append(
				"<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title></title><script type=\"text/javascript\">");
		html.append(
				"if (navigator.userAgent.indexOf(\"MSIE\") > 0) { if (navigator.userAgent.indexOf(\"MSIE 6.0\") > 0) {");
		html.append("window.opener = null;window.close();} else {window.open('', '_top');window.top.close();}");
		html.append(
				"} else if (navigator.userAgent.indexOf(\"Firefox\") > 0) {window.location.href = 'about:blank ';} else {");
		html.append("window.opener = null;window.open('', '_self', '');window.close();}");
		html.append("</script></head><body></body></html>");
		httpResp.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write(html.toString());
		} catch (IOException e) {
		}
	}

	@PostMapping("/quickpaymessage")
	@ApiOperation(value = "快捷支付发送短信验证码")
	public Response<String> quickPayMessage(@RequestParam(required = true) BigDecimal amount,
			@RequestParam(required = true) Long bindCardId) {
		Response<String> result = new Response<String>();
		result.setResult(paymentBusiness.quickPayMessage(amount, bindCardId, SecurityUtil.getUserId()));
		return result;
	}

	@PostMapping("/quickpay")
	@ApiOperation(value = "快捷支付")
	public Response<String> quickPay(@RequestParam(required = true) String paymentNo,
			@RequestParam(required = true) Long bindCardId, @RequestParam(required = true) String validaCode) {
		Response<String> result = new Response<String>();
		result.setResult(paymentBusiness.quickPay(paymentNo, bindCardId, validaCode, SecurityUtil.getUserId()));
		return result;
	}

	@PostMapping("/quickpaynotify")
	@ApiOperation(value = "快捷支付")
	public String quickPayNotify(HttpServletRequest request, HttpServletResponse httpResp) {
		String amount = request.getParameter("amount");
		String merchantNo = request.getParameter("merchantNo");
		String outTradeNo = request.getParameter("outTradeNo");
		String transactNo = request.getParameter("transactNo");
		String transactTime = request.getParameter("transactTime");
		String tradeType = request.getParameter("tradeType");
		String sign = request.getParameter("sign");
		// TODO 验签
		// 完成支付
		paymentBusiness.payCallback(outTradeNo, PaymentState.Paid);
		return "SUCCESS";
	}

	@PostMapping("/withdrawals")
	@ApiOperation(value = "提现", notes = "paymentType:1银联支付")
	public Response<String> withdrawals(@RequestParam(required = true) BigDecimal amount,
			@RequestParam(required = true) Long bindCardId, @RequestParam(required = true) String paymentPassword) {
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
		CzBankType bankType = CzBankType.getByPlateformBankType(BankType.getByBank(bindCard.getBankName()));
		if (bankType == null) {
			bankType = CzBankType.JSYH;
		}
		paymentBusiness.withdrawals(SecurityUtil.getUserId(), amount, bindCard.getName(), bindCard.getPhone(),
				bindCard.getIdCard(), bindCard.getBankCard(), bankType.getCode());
		resp.setResult("success");
		return resp;
	}

	@PostMapping("/czpaycallback")
	@ApiOperation(value = "橙子支付后台回调")
	public void czPayCallback(HttpServletRequest request, HttpServletResponse httpResp)
			throws UnsupportedEncodingException {
		String data = request.getParameter("data");
		logger.info("receive czpay pay notify result: {}" + data);
		// 处理回调
		String result = paymentBusiness.czPaycallback(data);
		// 响应回调
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write(result);
		} catch (IOException e) {
			throw new RuntimeException("http write interrupt");
		}
	}

	@SuppressWarnings("unused")
	@PostMapping("/czwithholdcallback")
	@ApiOperation(value = "橙子代扣后台回调")
	public void czWithholdCallback(HttpServletRequest request, HttpServletResponse httpResp) throws IOException {
		String dfRetCode = request.getParameter("dfRetCode");
		String token = request.getParameter("token");
		String dfRetMsg = request.getParameter("dfRetMsg");
		String md5 = request.getParameter("MD5");
		String type = request.getParameter("type");
		String withdrawalsNo = request.getParameter("orderId");
		// TODO 校验md5
		// 处理回调
		logger.info("receive czpay withhold notify result: {},{},{}", dfRetCode, withdrawalsNo, dfRetMsg);
		if (withdrawalsNo != null) {
			paymentBusiness.czWithholdCallback(withdrawalsNo,
					"00".equals(dfRetCode) ? WithdrawalsState.PROCESSED : WithdrawalsState.FAILURE, dfRetCode,
					dfRetMsg);
		}
		// 响应回调
		PrintWriter writer = httpResp.getWriter();
		writer.write("success");
	}

	@GetMapping("/czpayreturn")
	@ApiOperation(value = "橙子支付页面回调")
	public void czPayReturn(HttpServletRequest request, HttpServletResponse httpResp)
			throws UnsupportedEncodingException {
		String data = request.getParameter("data");
		logger.info("receive czpay pay return result: {}" + data);
		// 处理回调
		String result = paymentBusiness.czPayReturn(data);
		// 响应回调
		httpResp.setContentType("text/html;charset=UTF-8");
		try {
			httpResp.sendRedirect(result);
		} catch (IOException e) {
			throw new RuntimeException("http redirect exception");
		}
	}

	@GetMapping("/tbfpaycallback")
	@ApiOperation(value = "天下付支付后台回调")
	public void tbfPayCallback(HttpServletRequest request, HttpServletResponse httpResp)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("GBK");
		String cipherData = request.getParameter("cipher_data");
		// 处理回调
		String result = paymentBusiness.tbfPaycallback(cipherData);
		// 响应回调
		httpResp.setContentType("text/xml;charset=UTF-8");
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write(result);
		} catch (IOException e) {
			throw new RuntimeException("http write interrupt");
		}
	}

	@GetMapping("/tbfpayreturn")
	@ApiOperation(value = "天下付支付页面回调")
	public void tbfPayReturn(HttpServletRequest request, HttpServletResponse httpResp)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("GBK");
		String cipherData = request.getParameter("cipher_data");
		// 处理回调
		String result = paymentBusiness.tbfPayReturn(cipherData);
		// 响应回调
		httpResp.setContentType("text/html;charset=UTF-8");
		try {
			PrintWriter writer = httpResp.getWriter();
			writer.write(result);
		} catch (IOException e) {
			throw new RuntimeException("http write interrupt");
		}
	}
	
	private Map<String, String> paramter2Map(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"),
            // "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }

}
