package com.waben.stock.datalayer.organization.controller;

import java.util.Date;
import java.util.List;

import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.interfaces.dto.manage.RoleDto;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.dto.organization.UserDto;
import com.waben.stock.interfaces.dto.stockoption.OfflineStockOptionTradeDto;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.UserQuery;
import com.waben.stock.interfaces.service.organization.UserInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.PageToPageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.waben.stock.datalayer.organization.entity.User;
import com.waben.stock.datalayer.organization.service.UserService;
import com.waben.stock.interfaces.pojo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.management.relation.Role;

/**
 * 机构管理用户 Controller
 *
 * @author luomengan
 */
// @RestController
// @RequestMapping("/user")
// @Api(description = "机构管理用户接口列表")
@Component
public class UserController implements UserInterface {


    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public UserService userService;


    @Override
    @ApiOperation(value = "根据id获取机构管理用户")
    public Response<UserDto> fetchById(@PathVariable Long id) {
        User result = userService.getUserInfo(id);
        UserDto resposne = CopyBeanUtils.copyBeanProperties(UserDto.class, result, false);
        return new Response<>(resposne);
    }

    @Override
    @ApiOperation(value = "获取机构管理用户分页数据")
    public Response<PageInfo<UserDto>> users(int page, int limit) {
        Page<User> result = userService.users(page, limit);
        PageInfo<UserDto> response = PageToPageInfo.pageToPageInfo(result, UserDto.class);
        return new Response<>(response);
    }

    @Override
    @ApiOperation(value = "获取机构管理用户列表")
    public Response<List<UserDto>> list() {
        List<User> result = userService.list();
        List<UserDto> response = CopyBeanUtils.copyListBeanPropertiesToList(result, UserDto.class);
        return new Response<>(response);
    }

    @Override
    public Response<PageInfo<UserDto>> pages(@RequestBody UserQuery query) {
        Page<User> page = userService.pagesByQuery(query);
        PageInfo<UserDto> result = PageToPageInfo.pageToPageInfo(page, UserDto.class);
        for (int i = 0; i < page.getContent().size(); i++) {
            OrganizationDto organizationDto = CopyBeanUtils.copyBeanProperties(
                    OrganizationDto.class, page.getContent().get(i).getOrg(), false);
            result.getContent().get(i).setOrg(organizationDto);
        }
        return new Response<>(result);
    }

    /******************************** 后台管理 **********************************/
    @Override
//    @ApiOperation(value = "添加机构管理用户", hidden = true)
    public Response<UserDto> addition(@RequestBody UserDto user) {
        User request = CopyBeanUtils.copyBeanProperties(user, new User(), false);
        request.setOrg(CopyBeanUtils.copyBeanProperties(Organization.class,user.getOrg(),false));
        request.setCreateTime(new Date());
        User result = userService.addUser(request, user.getOrgId());
        UserDto response = CopyBeanUtils.copyBeanProperties(UserDto.class, result, false);
        return new Response<>(response);
    }

    @Override
    @ApiOperation(value = "修改机构管理用户", hidden = true)
    public Response<UserDto> modification(@RequestBody UserDto user) {
        User result = userService.addUser(CopyBeanUtils.copyBeanProperties(User.class, user, false), user.getOrgId());
        UserDto response = CopyBeanUtils.copyBeanProperties(result, new UserDto(), false);
        return new Response<>(response);
    }

    @Override
    @ApiOperation(value = "删除机构管理用户", hidden = true)
    public Response<Long> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return new Response<>(id);
    }

    @Override
    @ApiOperation(value = "批量删除机构管理用户（多个id以逗号分割）", hidden = true)
    public Response<Boolean> deletes(String ids) {
        userService.deleteUsers(ids);
        return new Response<>(true);
    }

    @Override
    @ApiOperation(value = "获取机构管理用户列表(后台管理)", hidden = true)
    public Response<List<UserDto>> adminList() {
        List<User> result = userService.list();
        List<UserDto> response = CopyBeanUtils.copyListBeanPropertiesToList(result, UserDto.class);
        return new Response<>(response);
    }

    @Override
    public Response<UserDto> fetchByUserName(String userName) {
        User user = userService.findByUserName(userName);
        UserDto response = CopyBeanUtils.copyBeanProperties(user, new UserDto(), false);
        response.setOrg(CopyBeanUtils.copyBeanProperties(OrganizationDto.class,user.getOrg(),false));
        return new Response<>(response);
    }
    
    @Override
    public Response<UserDto> getByUsername(String userName) {
        User user = userService.getByUserName(userName);
        if(user != null) {
        	UserDto response = CopyBeanUtils.copyBeanProperties(user, new UserDto(), false);
            response.setOrg(CopyBeanUtils.copyBeanProperties(OrganizationDto.class,user.getOrg(),false));
            return new Response<>(response);
        } else {
        	return new Response<>();
        }
    }

    @Override
    public Response<UserDto> bindRole(@PathVariable Long user, @PathVariable Long role) {
        User result = userService.bindRole(user,role);
        UserDto response = CopyBeanUtils.copyBeanProperties(result, new UserDto(), false);
        response.setOrg(CopyBeanUtils.copyBeanProperties(OrganizationDto.class,result.getOrg(),false));
        return new Response<>(response);
    }

	@Override
	public Response<Void> modifyPassword(@PathVariable Long userId, String oldPassword, String password) {
		userService.modifyPassword(userId, oldPassword, password);
		return new Response<>();
	}

    @Override
    public Response<UserDto> modifyState(@PathVariable Long id) {
        User result = userService.revisionState(id);
        UserDto response = CopyBeanUtils.copyBeanProperties(result, new UserDto(), false);
        return new Response<>(response);
    }
}
