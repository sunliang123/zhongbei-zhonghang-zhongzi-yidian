package com.waben.stock.applayer.strategist.crawler.util.http;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.waben.stock.applayer.strategist.crawler.util.base.exception.BusinessException;
import com.waben.stock.applayer.strategist.crawler.util.mvc.ResponseCode;

/**
 * Created by yhj on 17/6/23.
 */
public class InnerHttpTools {

    private static Logger logger = LoggerFactory.getLogger(InnerHttpTools.class);

    public static String post(String url, Map<String, String> param) throws BusinessException {


        logger.info("request [{}] user param<{}>",url,param);

        String result = HttpTools.doPost(url, param);

        JSONObject resultJson = null;
        Integer code = null;
        try {
            resultJson = JSONObject.parseObject(result);

            logger.info("request [{}] and result<{}>",url,result);

            if(resultJson == null){
                throw new BusinessException(ResponseCode.INNER_RESULT_CODE_ERROR, "返回解析异常");
            }

            code = resultJson.getInteger("code");

            if (code == null) {
                throw new BusinessException(ResponseCode.INNER_RESULT_CODE_ERROR, "返回解析异常");
            }
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.INNER_RESULT_JSON_ERROR, "返回解析异常", e);
        }

        String data = resultJson.getString("data");
        String msg = resultJson.getString("msg");


        if (ResponseCode.SUCCESS != code) {
            throw new BusinessException(code, msg);
        }


        return data;
    }
}
