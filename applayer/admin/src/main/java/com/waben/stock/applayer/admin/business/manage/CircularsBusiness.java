package com.waben.stock.applayer.admin.business.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.admin.security.CustomUserDetails;
import com.waben.stock.applayer.admin.security.SecurityUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.manage.CircularsDto;
import com.waben.stock.interfaces.dto.manage.StaffDto;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.CircularsQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.manage.CircularsInterface;


/**
 * @author Created by yuyidi on 2017/12/11.
 * @desc
 */
@Service("adminCircularsBusiness")
public class CircularsBusiness {

    @Autowired
    private CircularsInterface circularsReference;

    public PageInfo<CircularsDto> pages(CircularsQuery query) {
        Response<PageInfo<CircularsDto>> response = circularsReference.pages(query);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public CircularsDto findById(Long id) {
        Response<CircularsDto> response = circularsReference.fetchById(id);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public void delete(Long id) {
        circularsReference.delete(id);
    }

    public CircularsDto revision(CircularsDto requestDto) {
    	CustomUserDetails userDetails = SecurityUtil.getUserDetails();
        StaffDto staffDto = new StaffDto();
        staffDto.setId(userDetails.getUserId());
        requestDto.setStaffDto(staffDto);
        Response<CircularsDto> response = circularsReference.modify(requestDto);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public CircularsDto save(CircularsDto requestDto) {
    	CustomUserDetails userDetails = SecurityUtil.getUserDetails();
        StaffDto staffDto = new StaffDto();
        staffDto.setId(userDetails.getUserId());
        requestDto.setStaffDto(staffDto);
        Response<CircularsDto> response = circularsReference.add(requestDto);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }
}
