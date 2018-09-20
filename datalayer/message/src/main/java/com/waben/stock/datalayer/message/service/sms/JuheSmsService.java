package com.waben.stock.datalayer.message.service.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waben.stock.datalayer.message.service.SmsCache;
import com.waben.stock.datalayer.message.service.SmsService;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.enums.SmsType;
import com.waben.stock.interfaces.exception.ServiceException;

@Service("juheSmsService")
public class JuheSmsService implements SmsService {

	private static final String ServerUrl = "http://v.juhe.cn/sms/send";

	private static final String DataType = "json";

	private static final String Key = "819d835f0fbad7b273fe993e55028599";

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SmsCache smsCache;
	
	@Value("${sms.templatecode:0000}")
	private String templateCode;

	public void sendMessage(SmsType smsType, String phone) {
		try {
			// 检查发送条件是否满足
			smsCache.checkSendCondition(smsType, phone);
			// 组装请求参数
			Map<String, Object> params = new HashMap<String, Object>();
			JuheSmsTemplate template = JuheSmsTemplate.getBySmsType(smsType);
			params.put("dtype", DataType);
			params.put("mobile", phone);
			// params.put("tpl_id", template.getTemplateCode());
			// 现系统统一使用的都是验证码的短信，使用同一个模板即可
			params.put("tpl_id", templateCode);
			StringBuilder tplValue = new StringBuilder();
			Map<String, String> innerParams = template.getParamMap();
			if (innerParams.size() > 0) {
				int paramSize = innerParams.size() - 1;
				for (Map.Entry<String, String> entry : innerParams.entrySet()) {
					tplValue.append("#" + entry.getKey() + "#=" + entry.getValue());
					if (paramSize != 0) {
						tplValue.append("&");
					}
					paramSize--;
				}
			}
			params.put("tpl_value", URLEncoder.encode(tplValue.toString(), "UTF-8"));
			params.put("key", Key);
			StringBuilder urlParam = new StringBuilder("?");
			int j = 0;
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				urlParam.append(entry.getKey() + "=" + entry.getValue());
				if (j != params.size() - 1) {
					urlParam.append("&");
				}
				j++;
			}
			// 请求发送短信
			logger.info("request juhe sms url: {}", ServerUrl + urlParam.toString());
			String response = restTemplate.getForObject(ServerUrl + urlParam.toString(), String.class);
			logger.info("juhe sms response: {}", response);
			// 处理发送结果
			JuheResponseBean responseBean = objectMapper.readValue(response, JuheResponseBean.class);
			if (responseBean.getError_code() == 0) {
				if (template.getCacheNames() != null && template.getCacheNames().length > 0) {
					smsCache.cache(smsType, phone, innerParams, template.getCacheNames());
				}
			} else if (responseBean.getError_code() == 205401) {
				throw new ServiceException(ExceptionConstant.PHONE_WRONG_EXCEPTION);
			} else {
				throw new ServiceException(ExceptionConstant.SENDMESSAGE_FAILED_EXCEPTION);
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(ExceptionConstant.SENDMESSAGE_FAILED_EXCEPTION);
		} catch (JsonParseException | JsonMappingException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(ExceptionConstant.SENDMESSAGE_FAILED_EXCEPTION);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(ExceptionConstant.SENDMESSAGE_FAILED_EXCEPTION);
		}
	}

	public static class JuheResponseBean {

		private int error_code;

		private String reason;

		public int getError_code() {
			return error_code;
		}

		public void setError_code(int error_code) {
			this.error_code = error_code;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

	}

}
