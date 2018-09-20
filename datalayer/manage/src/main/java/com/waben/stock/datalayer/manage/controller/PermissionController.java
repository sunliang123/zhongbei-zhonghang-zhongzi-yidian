package com.waben.stock.datalayer.manage.controller;

import com.waben.stock.datalayer.manage.entity.Permission;
import com.waben.stock.datalayer.manage.entity.Role;
import com.waben.stock.datalayer.manage.service.PermissionService;
import com.waben.stock.interfaces.dto.manage.BannerDto;
import com.waben.stock.interfaces.dto.manage.PermissionDto;
import com.waben.stock.interfaces.dto.manage.RoleDto;
import com.waben.stock.interfaces.dto.stockcontent.StockExponentDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.PermissionQuery;
import com.waben.stock.interfaces.service.manage.PermissionInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.PageToPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/12/11.
 * @desc
 */
// @RestController
// @RequestMapping("/permission")
@Component
public class PermissionController implements PermissionInterface {

    @Autowired
    private PermissionService permissionService;

    @Override
    public Response<PermissionDto> permission(@PathVariable Long id) {
        Permission permission = permissionService.findById(id);
        PermissionDto result = CopyBeanUtils.copyBeanProperties(permission, new PermissionDto(), false);
        return new Response<>(result);
    }

    @Override
    public Response<PageInfo<PermissionDto>> pages(@RequestBody PermissionQuery query) {
        Page<Permission> page = permissionService.pagesByQuery(query);
        PageInfo<PermissionDto> result = PageToPageInfo.pageToPageInfo(page, PermissionDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<PermissionDto> modify(@RequestBody PermissionDto requestDto) {
        Permission permission = CopyBeanUtils.copyBeanProperties(Permission.class, requestDto, false);
        PermissionDto result = CopyBeanUtils.copyBeanProperties(PermissionDto.class,permissionService.revision(permission),false);
        return new Response<>(result);
    }

    @Override
    public void delete(@PathVariable Long id) {
        permissionService.delete(id);
    }

    @Override
    public Response<PermissionDto> add(@RequestBody PermissionDto requestDto) {
        Permission permission = CopyBeanUtils.copyBeanProperties(Permission.class, requestDto, false);
        PermissionDto result = CopyBeanUtils.copyBeanProperties(PermissionDto.class,permissionService.save(permission),false);
        return new Response<>(result);
    }

    @Override
    public Response<List<PermissionDto>> fetchPermissionsByVariety(@PathVariable Long variety) {
        List<Permission> permissions = permissionService.findPermissionsByVariety(variety);
        List<PermissionDto> permissionDtos = CopyBeanUtils.copyListBeanPropertiesToList(permissions,
                PermissionDto.class);
        return new Response<>(permissionDtos);
    }

    @Override
    public Response<List<PermissionDto>> fetchByRole(@PathVariable Long role) {
        List<Permission> permissions = permissionService.findPermissionsByRole(role);
        List<PermissionDto> permissionDtos = CopyBeanUtils.copyListBeanPropertiesToList(permissions,
                PermissionDto.class);
        System.out.println(JacksonUtil.encode(permissionDtos));
        return new Response<>(permissionDtos);
    }

    @Override
    public Response<List<PermissionDto>> fetchPermissions() {
        List<Permission> permissions = permissionService.findPermissions();
        List<PermissionDto> permissionDtos = CopyBeanUtils.copyListBeanPropertiesToList(permissions,
                PermissionDto.class);
        System.out.println(JacksonUtil.encode(permissionDtos));
        return new Response<>(permissionDtos);
    }
}
