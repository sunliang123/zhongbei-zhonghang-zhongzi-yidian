package com.waben.stock.interfaces.service.manage;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waben.stock.interfaces.dto.manage.BankInfoDto;
import com.waben.stock.interfaces.dto.manage.CnapsDto;
import com.waben.stock.interfaces.pojo.Response;

/**
 * Canps Controller接口
 * 
 * @author luomengan
 *
 */
public interface CnapsInterface {

	@RequestMapping(value = "/citycode/{cityCode}/clscode/{clsCode}", method = RequestMethod.GET)
	public Response<List<CnapsDto>> fetchByCityCodeAndClsCode(@PathVariable("cityCode") String cityCode,
			@PathVariable("clsCode") String clsCode);

	@RequestMapping(value = "/bankcard/{bankCard}", method = RequestMethod.GET)
	public Response<BankInfoDto> fetchBankInfo(@PathVariable("bankCard") String bankCard);

	@RequestMapping(value = "/bankinfo/lists", method = RequestMethod.GET)
	public Response<List<BankInfoDto>> listBankInfo();

	@RequestMapping(value = "/bankinfo/applists", method = RequestMethod.GET)
	public Response<List<BankInfoDto>> listAppBankInfo();

	@RequestMapping(value = "/bankinfo/pclists", method = RequestMethod.GET)
	public Response<List<BankInfoDto>> listPcBankInfo();

}
