package com.waben.stock.interfaces.pojo.stock.stockjy;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.SecuritiesStockException;
import com.waben.stock.interfaces.exception.ServiceException;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/12/5.
 * @desc
 */
public class StockResponseHander {

    protected  <T> List<T> handlerResult(StockResponse<T> stockResponse, String code) {
        List<StockResult<T>> stockDataResults = stockResponse.getResult();
        if (stockDataResults != null) {
            if (stockDataResults.size() > 0) {
                StockResult stockMsgResult = stockDataResults.get(1);
                if (stockMsgResult == null) {
                    throw new ServiceException(code);
                }
                StockMsg stockMsg = stockMsgResult.getMsg();
                String errorNo= stockMsg.getErrorNo();
                if ("3000".equals(errorNo)) {
                    StockResult<T> stockDataResult = stockDataResults.get(0);
                    return stockDataResult.getData();
                }else{
//                    if ("10200".equals(errorNo)) {
//                        throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION,stockMsg.getErrorInfo());
//                    }
                    throw new SecuritiesStockException(stockMsg.getErrorInfo());
                }
            }
        }
        throw new ServiceException(code);
    }
}
