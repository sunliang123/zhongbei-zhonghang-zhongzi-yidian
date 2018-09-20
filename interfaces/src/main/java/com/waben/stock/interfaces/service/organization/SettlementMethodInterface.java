package com.waben.stock.interfaces.service.organization;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waben.stock.interfaces.dto.organization.SettlementMethodDto;
import com.waben.stock.interfaces.pojo.Response;

public interface SettlementMethodInterface {

	@RequestMapping(value = "/edit/settlement", method = RequestMethod.POST)
	Response<SettlementMethodDto> edit(SettlementMethodDto method);
}
