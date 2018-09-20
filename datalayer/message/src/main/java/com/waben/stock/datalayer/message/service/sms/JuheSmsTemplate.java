package com.waben.stock.datalayer.message.service.sms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.waben.stock.interfaces.enums.CommonalityEnum;
import com.waben.stock.interfaces.enums.SmsType;

public enum JuheSmsTemplate implements CommonalityEnum {

	RegistVerificationCode("1", "注册验证码", "91406", new String[] { "code" }, new String[] { "code" },
			new VerifyCodeParamProducer()),

	ModifyPasswordCode("2", "修改密码验证码", "91406", new String[] { "code" }, new String[] { "code" },
			new VerifyCodeParamProducer()),

	BindCardCode("3", "绑定银行卡验证码", "91406", new String[] { "code" }, new String[] { "code" },
			new VerifyCodeParamProducer()),

	StrategyWarning("4", "点买持仓提醒", "50597", new String[] { "stgyName", "delayDay" }, null, null),

	ModifyPaymentPwdCode("5", "修改支付密码验证码", "91406", new String[] { "code" }, new String[] { "code" },
			new VerifyCodeParamProducer());

	private String index;

	private String templateCode;

	private String name;

	private String[] paramNames;

	private String[] cacheNames;

	private ParamProducer producer;

	private JuheSmsTemplate(String index, String name, String templateCode, String[] paramNames, String cacheNames[],
			ParamProducer producer) {
		this.index = index;
		this.name = name;
		this.templateCode = templateCode;
		this.paramNames = paramNames;
		this.cacheNames = cacheNames;
		this.producer = producer;
	}

	private static Map<String, JuheSmsTemplate> valueMap = new HashMap<String, JuheSmsTemplate>();

	static {
		for (JuheSmsTemplate _enum : JuheSmsTemplate.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public Map<String, String> getParamMap() {
		Map<String, String> result = new HashMap<>();
		if (paramNames != null && paramNames.length > 0 && producer != null) {
			List<String> paramValues = producer.produce();
			for (int i = 0; i < paramNames.length; i++) {
				result.put(paramNames[i], paramValues.get(i));
			}
		}
		return result;
	}

	public static JuheSmsTemplate getByIndex(Integer index) {
		JuheSmsTemplate result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}

	public static JuheSmsTemplate getBySmsType(SmsType smsType) {
		JuheSmsTemplate result = valueMap.get(smsType.getIndex());
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + smsType.getIndex());
		}
		return result;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getParamNames() {
		return paramNames;
	}

	public void setParamNames(String[] paramNames) {
		this.paramNames = paramNames;
	}

	public void setProducer(ParamProducer producer) {
		this.producer = producer;
	}

	public String[] getCacheNames() {
		return cacheNames;
	}

	public void setCacheNames(String[] cacheNames) {
		this.cacheNames = cacheNames;
	}

}
