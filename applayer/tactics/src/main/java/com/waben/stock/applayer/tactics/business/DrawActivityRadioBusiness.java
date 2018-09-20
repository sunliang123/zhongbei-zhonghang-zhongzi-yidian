package com.waben.stock.applayer.tactics.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.activity.DrawActivityRadioDto;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.activity.DrawActivityRadioInterface;

@Service("tacticsDrawActivityRadioBusiness")
public class DrawActivityRadioBusiness {

    @Autowired
    private DrawActivityRadioInterface drawActivityRadioReference;


    public List<DrawActivityRadioDto> getDrawActivityRadiosByActivityId(long activityId) {
        Response<List<DrawActivityRadioDto>> response = drawActivityRadioReference.getDrawActivityRadiosByActivityId(activityId);
        if ("200".equals(response.getCode())) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(response.getCode())){
            throw new NetflixCircuitException(response.getCode());
        }
        throw new ServiceException(response.getCode());
    }
}
