package com.waben.stock.datalayer.manage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.manage.entity.Cnaps;
import com.waben.stock.datalayer.manage.service.CnapsService;
import com.waben.stock.interfaces.dto.manage.BankInfoDto;
import com.waben.stock.interfaces.dto.manage.CnapsDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.manage.CnapsInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * Cnaps Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/cnaps")
@Component
public class CnapsController implements CnapsInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CnapsService cnapsService;

	@Override
	public Response<List<CnapsDto>> fetchByCityCodeAndClsCode(@PathVariable String cityCode,
			@PathVariable String clsCode) {
		List<Cnaps> cnapss = cnapsService.findByCityCodeAndClsCode(cityCode, clsCode);
		List<CnapsDto> cnapsDtoDtos = CopyBeanUtils.copyListBeanPropertiesToList(cnapss, CnapsDto.class);
		return new Response<>(cnapsDtoDtos);
	}

	@Override
	public Response<BankInfoDto> fetchBankInfo(@PathVariable String bankCard) {
		return new Response<>(
				CopyBeanUtils.copyBeanProperties(BankInfoDto.class, cnapsService.findBankInfo(bankCard), false));
	}

	@Override
	public Response<List<BankInfoDto>> listBankInfo() {
		return new Response<>(
				CopyBeanUtils.copyListBeanPropertiesToList(cnapsService.listBankInfo(), BankInfoDto.class));
	}

	@Override
	public Response<List<BankInfoDto>> listAppBankInfo() {
		return new Response<>(
				CopyBeanUtils.copyListBeanPropertiesToList(cnapsService.listAppBankInfo(), BankInfoDto.class));
	}

	@Override
	public Response<List<BankInfoDto>> listPcBankInfo() {
		return new Response<>(
				CopyBeanUtils.copyListBeanPropertiesToList(cnapsService.listPcBankInfo(), BankInfoDto.class));
	}

}
