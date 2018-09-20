package com.waben.stock.datalayer.message.service.sms;

import java.util.ArrayList;
import java.util.List;

import com.waben.stock.interfaces.util.RandomUtil;

public class VerifyCodeParamProducer implements ParamProducer {

	@Override
	public List<String> produce() {
		List<String> params = new ArrayList<>();
		params.add(RandomUtil.generateRandomCode(4));
		return params;
	}

}
