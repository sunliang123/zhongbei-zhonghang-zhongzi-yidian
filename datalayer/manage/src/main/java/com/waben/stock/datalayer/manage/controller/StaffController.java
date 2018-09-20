package com.waben.stock.datalayer.manage.controller;

import com.waben.stock.datalayer.manage.entity.Role;
import com.waben.stock.datalayer.manage.entity.Staff;
import com.waben.stock.datalayer.manage.service.StaffService;
import com.waben.stock.interfaces.dto.investor.SecurityAccountDto;
import com.waben.stock.interfaces.dto.manage.RoleDto;
import com.waben.stock.interfaces.dto.manage.StaffDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StaffQuery;
import com.waben.stock.interfaces.service.manage.StaffInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
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

/**
 * @author Created by yuyidi on 2017/11/15.
 * @desc
 */
// @RestController
// @RequestMapping("/staff")
@Component
public class StaffController implements StaffInterface {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StaffService staffService;

    @Override
    public Response<StaffDto> fetchByUserName(@PathVariable String username) {
        Staff staff = staffService.findByUserName(username);
        StaffDto staffDto = CopyBeanUtils.copyBeanProperties(staff, new StaffDto(), false);
        staffDto.setRoleDto(CopyBeanUtils.copyBeanProperties(staff.getRole(), new RoleDto(), false));
        return new Response<>(staffDto);
    }

    @Override
    public Response<PageInfo<StaffDto>> pagesByQuery(@RequestBody StaffQuery staffQuery) {
        Page<Staff> page = staffService.pagesByQuery(staffQuery);
        PageInfo<StaffDto> result = PageToPageInfo.pageToPageInfo(page, StaffDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<StaffDto> saveStaff(@RequestBody StaffDto staffDto) {
        Staff staff = CopyBeanUtils.copyBeanProperties(staffDto, new Staff(), false);
        staff.setRole(CopyBeanUtils.copyBeanProperties(Role.class, staffDto.getRoleDto(), false));
        Staff result = staffService.saveStaff(staff);
        StaffDto staffDtoResult = CopyBeanUtils.copyBeanProperties(result, new StaffDto(), false);
        return new Response<>(staffDtoResult);
    }

    @Override
    public Response<StaffDto> fetchById(@PathVariable Long id) {
        Staff staff = staffService.fetchById(id);
        StaffDto staffDto = CopyBeanUtils.copyBeanProperties(staff, new StaffDto(), false);
        staffDto.setRoleDto(CopyBeanUtils.copyBeanProperties(staff.getRole(), new RoleDto(), false));
        return new Response<>(staffDto);
    }

    @Override
    public Response<StaffDto> modify(@RequestBody StaffDto staffDto) {
        Staff staff = CopyBeanUtils.copyBeanProperties(Staff.class, staffDto, false);
        Staff result = staffService.revision(staff);
        StaffDto response = CopyBeanUtils.copyBeanProperties(StaffDto.class, result, false);
        return new Response<>(response);
    }

    @Override
    public void delete(@PathVariable Long id) {
        staffService.delete(id);
    }


}
