package com.waben.stock.interfaces.service.manage;

import com.waben.stock.interfaces.dto.manage.CircularsDto;
import com.waben.stock.interfaces.dto.manage.PermissionDto;
import com.waben.stock.interfaces.dto.manage.RoleDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.PermissionQuery;
import com.waben.stock.interfaces.pojo.query.RoleQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/12/11.
 * @desc
 */
public interface PermissionInterface {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Response<PermissionDto> permission(@PathVariable("id") Long id);

    @RequestMapping(value = "/pages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<PageInfo<PermissionDto>> pages(@RequestBody PermissionQuery query);

    @RequestMapping(value = "/modify", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<PermissionDto> modify(@RequestBody PermissionDto requestDto);

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    void delete(@PathVariable("id") Long id);

    @RequestMapping(value = "/save", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<PermissionDto> add(PermissionDto requestDto);

    @RequestMapping(value = "/variety/{variety}")
    Response<List<PermissionDto>> fetchPermissionsByVariety(@PathVariable("variety") Long variety);

    @RequestMapping(value = "/role/{role}", method = RequestMethod.GET)
    Response<List<PermissionDto>> fetchByRole(@PathVariable("role") Long role);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    Response<List<PermissionDto>> fetchPermissions();
}
