package com.waben.stock.applayer.tactics.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.activity.TicketAmountDto;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.activity.TicketMngInterface;

@Service("tacticsTicketAmountBusiness")
public class TicketAmountBusiness {

    @Autowired
    private TicketMngInterface ticketAmountReference;

    public TicketAmountDto findTicketAmountById(long ticketAmountId) {
        Response<TicketAmountDto> response = ticketAmountReference.getTicketAmount(ticketAmountId);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

}
