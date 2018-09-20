package com.waben.stock.interfaces.service.organization;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.organization.UserDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.UserQuery;

/**
 * @author Created by yuyidi on 2018/3/12.
 * @desc
 */
public interface UserInterface {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Response<UserDto> fetchById(@PathVariable("id") Long id);

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    Response<PageInfo<UserDto>> users(@RequestParam("page") int page, @RequestParam("limit") int limit);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    Response<List<UserDto>> list();

    @RequestMapping(value = "/pages", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<PageInfo<UserDto>> pages(@RequestBody UserQuery query);

    /******************************** 后台管理 **********************************/

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<UserDto> addition(@RequestBody UserDto user);

    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    Response<UserDto> modification(@RequestBody UserDto user);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    Response<Long> delete(@PathVariable("id") Long id);

    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    Response<Boolean> deletes(@RequestParam("ids") String ids);

    @RequestMapping(value = "/adminList", method = RequestMethod.GET)
    Response<List<UserDto>> adminList();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    Response<UserDto> fetchByUserName(@RequestParam("userName") String userName);
    
    @RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
    Response<UserDto> getByUsername(@RequestParam("userName") String userName);

    @RequestMapping(value = "/user/{user}/role/{role}", method = RequestMethod.PUT)
    Response<UserDto> bindRole(@PathVariable("user") Long user, @PathVariable("role") Long role);
    
    @RequestMapping(value = "/{userId}/password", method = RequestMethod.PUT)
	Response<Void> modifyPassword(@PathVariable("userId") Long userId, @RequestParam(name = "oldPassword") String oldPassword,
			@RequestParam(name = "password") String password);


    @RequestMapping(value = "/state/{id}", method = RequestMethod.PUT)
    Response<UserDto> modifyState(@PathVariable("id") Long id);
}
