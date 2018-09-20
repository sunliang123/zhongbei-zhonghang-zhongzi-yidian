package com.waben.stock.applayer.strategist.dto.publisher;

import com.waben.stock.applayer.strategist.business.BindCardBusiness;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;

public class BindCardFullDto extends BindCardDto {

	@SuppressWarnings("unused")
	private String bankIconLink;

	public String getBankIconLink() {
		String result = BindCardBusiness.bankIconMap.get(getBankName());
		if (result == null) {
			result = BindCardBusiness.bankIconMap.get("默认银行");
		}
		return result;
	}

}
