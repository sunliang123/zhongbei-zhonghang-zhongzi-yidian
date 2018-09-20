package com.waben.stock.risk.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.SecuritiesStockException;
import com.waben.stock.interfaces.pojo.stock.SecuritiesInterface;
import com.waben.stock.interfaces.pojo.stock.stockjy.StockResponse;
import com.waben.stock.interfaces.pojo.stock.stockjy.StockResponseHander;
import com.waben.stock.interfaces.pojo.stock.stockjy.data.StockEntrustQueryResult;
import com.waben.stock.interfaces.pojo.stock.stockjy.data.StockEntrustResult;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.web.HttpRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @author yuyidi 2017-12-05 11:13:57
 * @class com.waben.stock.risk.web.SecuritiesEntrust
 * @description 券商委托单http请求
 */
@Component
public class SecuritiesEntrustHttp extends StockResponseHander implements SecuritiesInterface {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${securities.context}")
    private String context;
    //券商委托单查询
    private String queryEntrustPath = "/qryentrust";


    private HttpHeaders headers = new HttpHeaders();
    {
        headers.add("broker_id","1001");
    }

    public StockEntrustQueryResult queryEntrust(String tradeSession, String entrustNo,String stockCode) {
        String queryEntrusUrl = context+ queryEntrustPath + "?token={token}&entrust_no={entrust_no}&stock_code={stock_code}";
        Map<String, String> params = new HashMap<>();
        params.put("token", tradeSession);
        params.put("entrust_no", entrustNo);
        params.put("stock_code",stockCode);
        String result = null;
        try {
            result = HttpRest.get(queryEntrusUrl, String.class, params,headers);
        } catch (Exception ex) {
            logger.info("委托单查询异常:{}", ex.getMessage());
        }
        logger.info("券商委托单查询,请求地址:{},请求结果:{}", queryEntrusUrl, result);
        StockResponse<StockEntrustQueryResult> stockResponse = JacksonUtil.decode(result, new
                TypeReference<StockResponse<StockEntrustQueryResult>>() {
                });
        List<StockEntrustQueryResult> stockEntrustQueryResult = handlerResult(stockResponse, ExceptionConstant.INVESTOR_SECURITIES_LOGIN_EXCEPTION);
        if (stockEntrustQueryResult.size() == 0) {
//            throw new SecuritiesStockException("点买记录暂未委托下单");
            logger.info("点买记录暂未委托下单。{}",entrustNo);
            return null;
        }
        return stockEntrustQueryResult.get(0);
    }

}
