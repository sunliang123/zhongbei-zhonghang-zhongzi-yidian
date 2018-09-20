package com.waben.stock.applayer.promotion.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.manage.PermissionDto;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.manage.PermissionInterface;

@Service("promotionPermissionBusiness")
public class PermissionBusiness {
    @Autowired
    private PermissionInterface permissionReference;

    public List<PermissionDto> findPermissionsByVariety() {
        Response<List<PermissionDto>> response = permissionReference.fetchPermissionsByVariety(4L);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        } else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public List<PermissionDto> fetchPermissionsByRole(Long role) {
        Response<List<PermissionDto>> response = permissionReference.fetchByRole(role);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        } else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }
}
