package com.waben.stock.datalayer.stockoption.web;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.stock.quotation.Resonse;
import com.waben.stock.interfaces.pojo.stock.quotation.StockMarket;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.web.HttpRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/12/22.
 * @desc
 */
@Component
public class StockQuotationHttp {

    Logger logger = LoggerFactory.getLogger(getClass());

    private String context = "http://lemi.esongbai.com/stk/stk";

    public StockMarket fetQuotationByCode(String code) {

        String url = context + "/list.do?codes=" + code;
        String resonse = HttpRest.get(url, String.class);
        Resonse<StockMarket> result = JacksonUtil.decode(resonse, Resonse.class, StockMarket.class);
        List<StockMarket> list = null;
        if ("200".equals(result.getCode())) {
            list = result.getData();
        }
        if (list != null) {
            return list.get(0);
        }
        logger.error("获取股票行情信息异常:{}", result.getMsg());
        throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION);
    }
}
