package com.waben.stock.applayer.tactics.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.tactics.business.CnapsBusiness;
import com.waben.stock.interfaces.dto.manage.BankInfoDto;
import com.waben.stock.interfaces.dto.manage.CnapsDto;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Cnaps Controller
 * 
 * @author luomengan
 *
 */
@RestController("tacticsCnapsController")
@RequestMapping("/cnaps")
@Api(description = "银行支行")
public class CnapsController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CnapsBusiness cnapsBusiness;

	@GetMapping("/lists/{cityCode}")
	@ApiOperation(value = "获取支行列表")
	public Response<List<CnapsDto>> fetchByCityCode(@PathVariable("cityCode") String cityCode, String bankCard) {
		BankInfoDto bankInfo = cnapsBusiness.findBankInfo(bankCard);
		return new Response<>(cnapsBusiness.findByCityCodeAndClsCode(cityCode, bankInfo.getBankCode()));
	}
	
	@GetMapping("/bankinfo/applists")
	@ApiOperation(value = "获取APP银联支付支持的银行列表")
	public Response<List<BankInfoDto>> appBankinfo() {
		return new Response<>(cnapsBusiness.appBankinfo());
	}

}
