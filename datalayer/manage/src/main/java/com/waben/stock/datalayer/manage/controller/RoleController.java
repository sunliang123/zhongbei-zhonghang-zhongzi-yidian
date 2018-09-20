package com.waben.stock.datalayer.manage.controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.waben.stock.datalayer.manage.entity.Menu;
import com.waben.stock.datalayer.manage.entity.Permission;
import com.waben.stock.datalayer.manage.entity.Role;
import com.waben.stock.datalayer.manage.entity.Staff;
import com.waben.stock.datalayer.manage.service.RoleService;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.manage.MenuDto;
import com.waben.stock.interfaces.dto.manage.PermissionDto;
import com.waben.stock.interfaces.dto.manage.RoleDto;
import com.waben.stock.interfaces.dto.manage.StaffDto;
import com.waben.stock.interfaces.dto.stockcontent.StockExponentDto;
import com.waben.stock.interfaces.dto.stockcontent.StrategyTypeDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.RoleQuery;
import com.waben.stock.interfaces.service.manage.RoleInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.PageToPageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
// @RestController
// @RequestMapping("/role")
@Component
public class RoleController implements RoleInterface {

	@Autowired
	private RoleService roleService;
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Response<RoleDto> role(@PathVariable Long id) {
		Role role = roleService.findById(id);
		RoleDto result = CopyBeanUtils.copyBeanProperties(role, new RoleDto(), false);
		return new Response<>(result);
	}

	@Override
	public Response<PageInfo<RoleDto>> pages(@RequestBody RoleQuery query) {
		Page<Role> page = roleService.pagesByQuery(query);
		PageInfo<RoleDto> result = PageToPageInfo.pageToPageInfo(page, RoleDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<RoleDto> fetchById(@PathVariable Long id) {
		Role role = roleService.fetchById(id);
		RoleDto roleDto = CopyBeanUtils.copyBeanProperties(role, new RoleDto(), false);
		return new Response<>(roleDto);
	}

	@Override
	public Response<RoleDto> modify(@RequestBody RoleDto roleDto) {
		Role role = CopyBeanUtils.copyBeanProperties(Role.class, roleDto, false);
		RoleDto result = CopyBeanUtils.copyBeanProperties(RoleDto.class, roleService.revision(role), false);
		return new Response<>(result);
	}

	@Override
	public void delete(@PathVariable Long id) {
		roleService.delete(id);
	}

	@Override
	public Response<RoleDto> add(@RequestBody RoleDto roleDto) {
//		List<Role> roleList = roleService.findByRoleName(roleDto.getName());
//		if (roleList != null && roleList.size() > 0) {
//			throw new ServiceException(ExceptionConstant.ROLE_EXISTENCE_EXCEPITON);
//		}
		Role role = CopyBeanUtils.copyBeanProperties(Role.class, roleDto, false);
		List<MenuDto> menuDtos = new ArrayList<>();
		menuDtos.addAll(roleDto.getMenusDtos());
		List<Menu> menus = CopyBeanUtils.copyListBeanPropertiesToList(menuDtos, Menu.class);
		Set<Menu> menu = new HashSet<>();
		menu.addAll(menus);
		role.setMenus(menu);
		Role result = roleService.save(role);
		RoleDto response = CopyBeanUtils.copyBeanProperties(RoleDto.class, result, false);
		return new Response<>(response);
	}

	@Override
	public Response<List<RoleDto>> fetchRoles() {
		List<Role> roles = roleService.findRoles();
		List<RoleDto> roleDtos = CopyBeanUtils.copyListBeanPropertiesToList(roles, RoleDto.class);
		return new Response<>(roleDtos);
	}

	@Override
	public Response<RoleDto> addRoleMenu(@PathVariable Long id, @RequestBody Long[] menuIds) {
		RoleDto result = CopyBeanUtils.copyBeanProperties(RoleDto.class, roleService.saveRoleMenu(id, menuIds), false);
		return new Response<>(result);
	}

	@Override
	public Response<RoleDto> addRolePermission(@PathVariable Long id, @RequestBody Long[] permissionIds) {
		RoleDto result = CopyBeanUtils.copyBeanProperties(RoleDto.class,
				roleService.saveRolePermission(id, permissionIds), false);
		return new Response<>(result);
	}

	@Override
	public Response<RoleDto> bindAdminRoleWithPermissionAndMenu(Long id, Long variety) {
		Role result = roleService.bindRoleWithPermissionAndMenu(id, variety);
		RoleDto roleDto = CopyBeanUtils.copyBeanProperties(RoleDto.class, result, false);
		return new Response<>(roleDto);
	}

	@Override
	public Response<RoleDto> fetchByOrganizationAdmin(@PathVariable Long organization) {
		Role result = roleService.findByOrganizationAdmin(organization);
		RoleDto roleDto = CopyBeanUtils.copyBeanProperties(RoleDto.class, result, false);
		return new Response<>(roleDto);
	}

	@Override
	public Response<List<RoleDto>> fetchRolesByOrganization(@PathVariable Long org) {
		List<Role> roles = roleService.findRolesByOrganization(org);
		List<RoleDto> roleDtos = CopyBeanUtils.copyListBeanPropertiesToList(roles, RoleDto.class);
		return new Response<>(roleDtos);
	}
}
