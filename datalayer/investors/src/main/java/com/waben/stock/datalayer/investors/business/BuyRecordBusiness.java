package com.waben.stock.datalayer.investors.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.investors.entity.Investor;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.enums.BuyRecordState;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.service.buyrecord.BuyRecordInterface;
import com.waben.stock.interfaces.util.JacksonUtil;

/**
 * @author Created by yuyidi on 2017/12/2.
 * @desc
 */
@Service("investorsBuyRecordBusiness")
public class BuyRecordBusiness {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BuyRecordInterface buyRecordReference;

    /***
     * @author yuyidi 2017-12-02 16:25:47
     * @method entrust
     * @param entrust
     * @return com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto
     * @description 点买交易订单委托买入成功后向点买服务发起买入锁定请求
     */
    public BuyRecordDto buyRecordApplyBuyIn(Investor investor, SecuritiesStockEntrust securitiesStockEntrust, String
            entrust) {
//        securitiesStockEntrust.setEntrustNumber(entrust);
        Response<BuyRecordDto> response = buyRecordReference.buyLock(investor.getId(), securitiesStockEntrust
                .getBuyRecordId(), entrust);
        if ("200".equals(response.getCode())) {
            BuyRecordDto result = response.getResult();
            result.setDelegateNumber(entrust);
            if (result.getState().equals(BuyRecordState.BUYLOCK)) {
                logger.info("点买记录买入锁定成功:{}", result.getTradeNo());
                return result;
            }
        }
        throw new ServiceException(response.getCode());
    }

    /**
     *
     * @param investor 投资人id
     * @param securitiesStockEntrust
     * @param entrust 委托编号
     * @return
     */
    public BuyRecordDto entrustApplySellOut(Investor investor, SecuritiesStockEntrust securitiesStockEntrust, String
            entrust,String windControlType) {
//        securitiesStockEntrust.setEntrustNumber(entrust);
        //卖出锁定
        logger.info("id:{},bId:{},entrust:{},w:{}",investor.getId(),securitiesStockEntrust.getBuyRecordId(),entrust,windControlType);
        Response<BuyRecordDto> response = buyRecordReference.sellLock(investor.getId(), securitiesStockEntrust
                .getBuyRecordId(), entrust, windControlType);
        logger.info("result:{}", JacksonUtil.encode(response));
        if ("200".equals(response.getCode())) {
            BuyRecordDto result = response.getResult();
            result.setDelegateNumber(entrust);
            if (result.getState().equals(BuyRecordState.SELLLOCK)) {
                return result;
            }
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(response.getCode())){
            throw new NetflixCircuitException(response.getCode());
        }
        throw new ServiceException(response.getCode());
    }

    public BuyRecordDto entrustApplyWithdraw(String entrustNo, Long id) {
        Response<BuyRecordDto> response = buyRecordReference.withdrawLock(entrustNo,id);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public BuyRecordDto findById(Long buyrecord) {
        Response<BuyRecordDto> response = buyRecordReference.fetchBuyRecord(buyrecord);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public List<BuyRecordDto> buyRecordsWithBuyIn() {
        Response<List<BuyRecordDto>> response = buyRecordReference.buyRecordsWithStatus(1);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public List<BuyRecordDto> buyRecordsWithSellOut() {
        Response<List<BuyRecordDto>> response = buyRecordReference.buyRecordsWithStatus(4);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public BuyRecordDto revisionState(BuyRecordDto buyRecordDto) {
        Response<BuyRecordDto> response = buyRecordReference.updateState(buyRecordDto);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }
}
