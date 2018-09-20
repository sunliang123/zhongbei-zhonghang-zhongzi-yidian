package com.waben.stock.datalayer.publisher.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.datalayer.publisher.entity.BindCard;
import com.waben.stock.datalayer.publisher.service.BindCardService;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.enums.BindCardResourceType;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.publisher.BindCardInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

/**
 * 绑卡 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/bindCard")
@Component
public class BindCardController implements BindCardInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BindCardService bindCardService;

	@Override
	public Response<BindCardDto> addBankCard(@RequestBody BindCardDto bindCardDto) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(BindCardDto.class,
				bindCardService.save(CopyBeanUtils.copyBeanProperties(BindCard.class, bindCardDto, false)), false));
	}

	@Override
	public Response<List<BindCardDto>> listsByResourceTypeAndResourceId(@PathVariable String resourceType,
			@PathVariable Long resourceId) {
		List<BindCard> bindCards = bindCardService.list(BindCardResourceType.getByIndex(resourceType), resourceId);
		List<BindCardDto> result = CopyBeanUtils.copyListBeanPropertiesToList(bindCards, BindCardDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<BindCardDto> fetchOrgBindCardByName(@PathVariable String name) {
		BindCard result = bindCardService.findOrgBindCardByName(name);
		BindCardDto response = CopyBeanUtils.copyBeanProperties(BindCardDto.class,result,false);
		return new Response<>(response);
	}

	@Override
	public Response<BindCardDto> fetchById(@PathVariable Long id) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(BindCardDto.class, bindCardService.findById(id), false));
	}

	@Override
	public Response<BindCardDto> modifyBankCard(@RequestBody BindCardDto bindCardDto) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(BindCardDto.class,
				bindCardService.revision(CopyBeanUtils.copyBeanProperties(BindCard.class, bindCardDto, false)), false));
	}

	@Override
	public Response<Long> dropBankCard(@PathVariable Long id) {
		return new Response<>(bindCardService.remove(id));
	}

}
