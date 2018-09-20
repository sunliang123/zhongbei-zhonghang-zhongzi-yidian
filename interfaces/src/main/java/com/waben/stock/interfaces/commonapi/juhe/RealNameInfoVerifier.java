package com.waben.stock.interfaces.commonapi.juhe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.waben.stock.interfaces.commonapi.juhe.bean.JuheResponse;
import com.waben.stock.interfaces.commonapi.juhe.bean.RealNameVerifyBean;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.util.JacksonUtil;

/**
 * 实名认证校验器
 * 
 * @author luomengan
 *
 */
public class RealNameInfoVerifier {

	public static final String key = "c6bc0c65196f61849b8e68b792249a14";

	private static Logger logger = LoggerFactory.getLogger(RealNameInfoVerifier.class);

	public static boolean verify(RestTemplate restTemplate, String name, String idCard) {
		String requestUrl = String.format("http://op.juhe.cn/idcard/query?key=%s&idcard=%s&realname=%s", key, idCard,
				name);
		// 请求验证四要素
		logger.info("请求聚合实名验证: {}", requestUrl);
		String response = restTemplate.getForObject(requestUrl, String.class);
		logger.info("聚合实名验证响应: {}", response);
		JuheResponse<RealNameVerifyBean> responseObj = JacksonUtil.decode(response,
				JacksonUtil.getGenericType(JuheResponse.class, RealNameVerifyBean.class));
		if (responseObj.getError_code() == 0) {
			if (responseObj.getResult() != null && "1".equals(responseObj.getResult().getRes())) {
				return true;
			} else {
				return false;
			}
		} else if (responseObj.getError_code() == 10012) {
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "实名验证接口可用次数不足!");
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		// System.out.println(verify("陈建", "331081198408159433"));
	}

}
