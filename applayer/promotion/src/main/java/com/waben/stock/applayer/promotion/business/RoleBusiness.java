package com.waben.stock.applayer.promotion.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.promotion.security.SecurityUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.manage.MenuDto;
import com.waben.stock.interfaces.dto.manage.RoleDto;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.exception.NetflixCircuitException;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.RoleQuery;
import com.waben.stock.interfaces.service.manage.RoleInterface;

@Service("promotionRoleBusiness")
public class RoleBusiness {
    @Autowired
    private RoleInterface roleReference;

    @Autowired
    private MenuBusiness menuBusiness;

    public RoleDto saveRoleMenu(Long id, Long[] menuIds) {
        Response<RoleDto> response = roleReference.addRoleMenu(id,menuIds);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public RoleDto saveRolePermission(Long id, Long[] permissionIds) {
        Response<RoleDto> response = roleReference.addRolePermission(id,permissionIds);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public PageInfo<RoleDto> pages(RoleQuery roleQuery) {
        roleQuery.setOrganization(SecurityUtil.getUserDetails().getOrgId());
        roleQuery.setType(4);
        Response<PageInfo<RoleDto>> response = roleReference.pages(roleQuery);
        String code = response.getCode();
        if ("200".equals(code)) {
            for (RoleDto roleDto : response.getResult().getContent()) {
               menuBusiness.childsMenu(roleDto.getMenusDtos());
            }
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public RoleDto findById(Long id) {
        Response<RoleDto> response = roleReference.fetchById(id);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public RoleDto findByOrganization(OrganizationDto organizationDto) {
        Response<RoleDto> response = roleReference.fetchByOrganizationAdmin(organizationDto.getId());
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public RoleDto bindAdminRoleWithPermissionAndMenu(Long id) {
        Response<RoleDto> response = roleReference.bindAdminRoleWithPermissionAndMenu(id, 4L);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public List<RoleDto> findByOrganization(Long id) {
        Response<List<RoleDto>> response = roleReference.fetchRolesByOrganization(id);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        } else if (ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)) {
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }


    public RoleDto save(String name, String menuIds) {
        RoleDto roleDto = new RoleDto();
        roleDto.setName(name);
        roleDto.setOrganization(SecurityUtil.getUserDetails().getOrgId());
        roleDto.setCreateTime(new Date());
        roleDto.setType(4);
        roleDto.setMenusDtos(menuDtos(parentMenuIds(getMenusId(menuIds))));
        Response<RoleDto> response = roleReference.add(roleDto);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public List<Long> getMenusId(String menuIds) {
        String[] split = menuIds.split(",");
        List<Long> ids = new ArrayList<>();
        for(String id : split) {
            ids.add(Long.parseLong(id));
        }
        return ids;
    }

    public RoleDto revision(Long id, String name, String menuIds) {
        RoleDto roleDto = roleReference.role(id).getResult();
        roleDto.setMenusDtos(menuDtos(parentMenuIds(getMenusId(menuIds))));
        roleDto.setName(name);
        Response<RoleDto> response = roleReference.add(roleDto);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }

    public Set<MenuDto> menuDtos(List<Long> menuIds) {
        Set<MenuDto> menuDtos = new HashSet();
        for(Long menuId : menuIds) {
            MenuDto menuDto = new MenuDto();
            menuDto.setId(menuId);
            menuDtos.add(menuDto);
        }
        return menuDtos;
    }

    public List<Long> parentMenuIds(List<Long> menuIds) {
        Set<Long> menus = new HashSet<>();
        for(Long menuId : menuIds) {
            MenuDto menu = menuBusiness.findById(menuId);
            menus.add(menu.getPid());
        }
        menuIds.addAll(menus);
        return menuIds;
    }

    public void remove(Long id) {
        roleReference.delete(id);
    }

    public RoleDto save(RoleDto roleDto) {
        Response<RoleDto> response = roleReference.add(roleDto);
        String code = response.getCode();
        if ("200".equals(code)) {
            return response.getResult();
        }else if(ExceptionConstant.NETFLIX_CIRCUIT_EXCEPTION.equals(code)){
            throw new NetflixCircuitException(code);
        }
        throw new ServiceException(response.getCode());
    }
}
