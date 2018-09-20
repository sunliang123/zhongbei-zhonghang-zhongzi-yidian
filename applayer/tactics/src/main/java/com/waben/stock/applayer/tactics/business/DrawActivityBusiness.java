package com.waben.stock.applayer.tactics.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.activity.DrawActivityDto;
import com.waben.stock.interfaces.dto.activity.TicketAmountDto;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.activity.DrawActivityInterface;

@Service("tacticsDrawActivityBusiness")
public class DrawActivityBusiness {

    @Autowired
    private DrawActivityInterface drawActivityReference;

    public TicketAmountDto draw(long activityId,long publisherId) {
        Response<TicketAmountDto> response = drawActivityReference.draw(activityId, publisherId);
        Response<DrawActivityDto> drawActicity = drawActivityReference.getDrawActicity(activityId, publisherId);
        String code = response.getCode();
        if ("200".equals(code)) {
            response.getResult().setResidueDegree(drawActicity.getResult().getRemaintime());
            response.getResult().setResidueDegree(drawActicity.getResult().getLuckyDrawCount());
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

}
