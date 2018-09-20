package com.waben.stock.interfaces.service.manage;

import com.waben.stock.interfaces.dto.manage.CircularsDto;
import com.waben.stock.interfaces.dto.manage.RoleDto;
import com.waben.stock.interfaces.dto.manage.StaffDto;
import com.waben.stock.interfaces.dto.stockcontent.StockExponentDto;
import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.RoleQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
public interface RoleInterface {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Response<RoleDto> role(@PathVariable("id") Long id);

    @RequestMapping(value = "/pages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<PageInfo<RoleDto>> pages(@RequestBody RoleQuery query);

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    Response<RoleDto> fetchById(@PathVariable("id") Long id);

    @RequestMapping(value = "/modify", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<RoleDto> modify(@RequestBody RoleDto roleDto);

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    void delete(@PathVariable("id") Long id);

    @RequestMapping(value = "/save", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<RoleDto> add(RoleDto roleDto);

    @RequestMapping(value = "/")
    Response<List<RoleDto>> fetchRoles();

    @RequestMapping(value = "/menu/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<RoleDto> addRoleMenu(@PathVariable("id") Long id, @RequestBody Long[] menuIds);

    @RequestMapping(value = "/permission/{id}", method = RequestMethod.POST, consumes = MediaType
            .APPLICATION_JSON_VALUE)
    Response<RoleDto> addRolePermission(@PathVariable("id") Long id, @RequestBody Long[] permissionIds);

    @RequestMapping(value = "/{role}/bind/{variety}", method = RequestMethod.GET)
    Response<RoleDto> bindAdminRoleWithPermissionAndMenu(@PathVariable("id") Long id, @PathVariable("variety") Long
            variety);

    @RequestMapping(value = "/organization/{organization}", method = RequestMethod.GET)
    Response<RoleDto> fetchByOrganizationAdmin(@PathVariable("organization") Long organization);


    @RequestMapping(value = "/org/{org}", method = RequestMethod.GET)
    Response<List<RoleDto>> fetchRolesByOrganization(@PathVariable("org") Long org);

}
