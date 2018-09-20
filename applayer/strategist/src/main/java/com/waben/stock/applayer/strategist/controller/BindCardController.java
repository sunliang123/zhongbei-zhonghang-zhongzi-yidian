package com.waben.stock.applayer.strategist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.strategist.business.BindCardBusiness;
import com.waben.stock.applayer.strategist.dto.publisher.BindCardFullDto;
import com.waben.stock.applayer.strategist.security.SecurityUtil;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.enums.BindCardResourceType;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.util.CopyBeanUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 绑卡 Controller
 * 
 * @author luomengan
 *
 */
@RestController("strategistBindCardController")
@RequestMapping("/bindCard")
@Api(description = "绑卡")
public class BindCardController {

	@Autowired
	private BindCardBusiness bindCardBusiness;

	@PostMapping("/bindBankCard")
	@ApiOperation(value = "绑定银行卡")
	public Response<BindCardFullDto> bindBankCard(@RequestParam(required = true) String name,
			@RequestParam(required = true) String idCard, @RequestParam(required = true) String phone,
			@RequestParam(required = true) String bankCard, String branchName, String branchCode) {
		// 绑定银行卡
		BindCardDto bindCardDto = new BindCardDto();
		bindCardDto.setBankCard(bankCard);
		bindCardDto.setIdCard(idCard);
		bindCardDto.setName(name);
		bindCardDto.setPhone(phone);
		bindCardDto.setBranchCode(branchCode);
		bindCardDto.setBranchName(branchName);
		bindCardDto.setResourceId(SecurityUtil.getUserId());
		bindCardDto.setResourceType(BindCardResourceType.PUBLISHER);
		return new Response<>(
				CopyBeanUtils.copyBeanProperties(BindCardFullDto.class, bindCardBusiness.save(bindCardDto), false));
	}

	@PostMapping("/fillBranch/{id}")
	@ApiOperation(value = "完善支行信息")
	public Response<BindCardFullDto> fillBranch(@PathVariable("id") Long id,
			@RequestParam(required = true) String branchName, String branchCode) {
		BindCardDto bindCard = bindCardBusiness.findById(id);
		bindCard.setBranchName(branchName);
		bindCard.setBranchCode(branchCode);
		BindCardDto result = bindCardBusiness.revision(bindCard);
		return new Response<>(CopyBeanUtils.copyBeanProperties(BindCardFullDto.class, result, false));
	}

	@GetMapping("/myBankCardList")
	@ApiOperation(value = "我的已绑定银行卡列表")
	public Response<List<BindCardFullDto>> myBankCardList() {
		List<BindCardDto> list = bindCardBusiness.listsByPublisherId(SecurityUtil.getUserId());
		return new Response<>(CopyBeanUtils.copyListBeanPropertiesToList(list, BindCardFullDto.class));
	}
	
	@PostMapping("/unbundling/{id}")
	@ApiOperation(value = "解绑银行卡")
	public Response<Long> unbundling(@PathVariable("id") Long id) {
		return new Response<>(bindCardBusiness.remove(id));
	}

}
