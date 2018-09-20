package com.waben.stock.applayer.promotion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.promotion.business.MenuBusiness;
import com.waben.stock.applayer.promotion.business.OrganizationBusiness;
import com.waben.stock.applayer.promotion.business.RoleBusiness;
import com.waben.stock.applayer.promotion.business.UserBusiness;
import com.waben.stock.applayer.promotion.security.SecurityUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.manage.MenuDto;
import com.waben.stock.interfaces.dto.manage.RoleDto;
import com.waben.stock.interfaces.dto.organization.OrganizationDetailDto;
import com.waben.stock.interfaces.dto.organization.UserDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.RoleQuery;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.vo.manage.MenuVo;
import com.waben.stock.interfaces.vo.manage.RoleVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController("promotionRoleController")
@RequestMapping("/role")
@Api(description = "角色")
public class RoleController {

	@Autowired
	private RoleBusiness roleBusiness;

	@Autowired
	private OrganizationBusiness organizationBusiness;

	@Autowired
	private MenuBusiness menuBusiness;

	@Autowired
	private UserBusiness userBusiness;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", dataType = "String", name = "name", value = "角色名称", required = true),
			@ApiImplicitParam(paramType = "query", dataType = "String", name = "menuIds", value = "菜单id数组", required = true) })
	@ApiOperation(value = "添加角色")
	public Response<RoleVo> add(String name, String menuIds) {
		RoleDto roleDto = roleBusiness.save(name, menuIds);
		RoleVo roleVo = CopyBeanUtils.copyBeanProperties(RoleVo.class, roleDto, false);
		return new Response<>(roleVo);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", dataType = "Long", name = "id", value = "角色id", required = true),
			@ApiImplicitParam(paramType = "query", dataType = "String", name = "name", value = "角色名称", required = true),
			@ApiImplicitParam(paramType = "query", dataType = "String", name = "menuIds", value = "菜单id数组", required = true) })
	@ApiOperation(value = "修改角色")
	public Response<RoleVo> modify(@RequestParam Long id, @RequestParam String name, @RequestParam String menuIds) {
		RoleDto roleDto = roleBusiness.revision(id, name, menuIds);
		RoleVo roleVo = CopyBeanUtils.copyBeanProperties(RoleVo.class, roleDto, false);
		return new Response<>(roleVo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "角色id", required = true)
	@ApiOperation(value = "删除角色")
	public Response<Long> drop(@PathVariable Long id) {
		Response<List<UserDto>> response = userBusiness.findUserAll();
		List<UserDto> list = response.getResult();
		if (list != null && list.size() > 0) {
			for (UserDto userDto : list) {
				if (userDto.getRole().equals(id)) {
					throw new ServiceException(ExceptionConstant.USER_ROLE_EXCEPITON);
				}
			}
		}
		roleBusiness.remove(id);
		return new Response<>(id);
	}

	@RequestMapping(value = "/pages", method = RequestMethod.GET)
	@ApiImplicitParam(paramType = "query", dataType = "RoleQuery", name = "query", value = "角色查询对象", required = false)
	@ApiOperation(value = "角色分页")
	public Response<PageInfo<RoleVo>> pages(RoleQuery query) {
		PageInfo<RoleDto> pageInfo = roleBusiness.pages(query);
		List<RoleVo> roleVoContent = CopyBeanUtils.copyListBeanPropertiesToList(pageInfo.getContent(), RoleVo.class);
		PageInfo<RoleVo> response = new PageInfo<>(roleVoContent, pageInfo.getTotalPages(), pageInfo.getLast(),
				pageInfo.getTotalElements(), pageInfo.getSize(), pageInfo.getNumber(), pageInfo.getFrist());
		for (RoleVo role : response.getContent()) {
			if (role.getOrganization() != null) {
				OrganizationDetailDto detail = organizationBusiness.detail(role.getOrganization());
				role.setOrganizationName(detail.getName());
			}
		}
		return new Response<>(response);
	}

	// @RequestMapping(value = "/permissions",method = RequestMethod.GET)
	// @ApiOperation(value = "获取权限")
	// public Response<List<PermissionVo>> permissions() {
	//// List<PermissionDto> permissions =
	// SecurityAccount.current().getPermissions();
	//// @RequestMapping(value = "/permissions",method = RequestMethod.GET)
	//// @ApiOperation(value = "获取权限")
	//// public Response<List<PermissionVo>> permissions() {
	////// List<PermissionDto> permissions =
	// SecurityAccount.current().getPermissions();
	//// List<PermissionDto> permissions =
	// permissionBusiness.findPermissionsByVariety();
	//// List<PermissionVo> permissionVos =
	// CopyBeanUtils.copyListBeanPropertiesToList(permissions,
	// PermissionVo.class);
	//// for(PermissionVo permissionVo : permissionVos) {
	//// if(permissionVo.getPid()==0) {
	//// List<PermissionVo> childPermissions = new ArrayList();
	//// for(PermissionVo permission : permissionVos) {
	//// if(permission.getPid()==permissionVo.getId()) {
	//// childPermissions.add(permission);
	//// permissionVos.remove(permission);
	//// }
	//// }
	//// permissionVo.setChildPermissions(childPermissions);
	//// }
	//// }
	//// return new Response<>(permissionVos);
	// return null;
	// }

	@RequestMapping(value = "/menus", method = RequestMethod.GET)
	@ApiOperation(value = "获取菜单")
	public Response<List<MenuVo>> menus() {
		List<MenuDto> menuDtos = menuBusiness.menus(SecurityUtil.getUserDetails().getRoleId());
		List<MenuVo> menuVos = CopyBeanUtils.copyListBeanPropertiesToList(menuDtos, MenuVo.class);
		return new Response<>(menuVos);
	}

}
