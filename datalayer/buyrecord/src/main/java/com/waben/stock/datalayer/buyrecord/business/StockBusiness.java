package com.waben.stock.datalayer.buyrecord.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.stockcontent.StockDto;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.stockcontent.StockInterface;

/**
 * @author Created by yuyidi on 2017/12/2.
 * @desc
 */
@Service("buyrecordStockBusiness")
public class StockBusiness {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StockInterface stockReference;

    public StockDto fetchWithExponentByCode(String stockCode) {
        Response<StockDto> response = stockReference.fetchWithExponentByCode(stockCode);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }


}
