package com.waben.stock.applayer.admin.controller.manage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.admin.business.manage.RoleBusiness;
import com.waben.stock.applayer.admin.business.manage.StaffBusiness;
import com.waben.stock.interfaces.dto.manage.StaffDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.StaffQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Author zengzhiwei
 * @Create 2018/4/23 14:57
 */
@RestController("adminStaffController")
@RequestMapping("/staff")
@Api(description="员工")
public class StaffController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private RoleBusiness roleBusiness;
    @Autowired
    private StaffBusiness staffBusiness;

    @GetMapping("/pages")
    @ApiImplicitParam(paramType = "query", dataType = "StaffQuery", name = "query", value = "查询对象", required = true)
    @ApiOperation(value = "员工分页")
    public Response<PageInfo<StaffDto>> pages(StaffQuery query) {
        PageInfo<StaffDto> response = staffBusiness.staffs(query);
        return new Response<>(response);
    }

    @PutMapping("/modify")
    @ApiImplicitParam(paramType = "query", dataType = "StaffDto", name = "query", value = "员工对象", required = true)
    @ApiOperation(value = "修改员工")
    public Response<StaffDto> modify(StaffDto staffDto){
        StaffDto result = staffBusiness.revision(staffDto);
        return new Response<>(result);
    }

    @DeleteMapping("/delete/{id}")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "员工id", required = true)
    @ApiOperation(value = "删除员工")
    public Response<Integer> delete(Long id){
        staffBusiness.delete(id);
        return new Response<>(1);
    }

    @PostMapping("/save")
    @ApiImplicitParam(paramType = "query", dataType = "StaffDto", name = "query", value = "员工对象", required = true)
    @ApiOperation(value = "添加员工")
    public Response<StaffDto> add(StaffDto staffDto){
        StaffDto response = staffBusiness.save(staffDto);
        return new Response<>(response);
    }

    @PutMapping("/{originalPassword}/{password}")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", dataType = "String", name = "originalPassword", value = "员工原密码", required = true),@ApiImplicitParam(paramType = "path", dataType = "String", name = "password", value = "员工密码", required = true)})
    @ApiOperation(value = "修改密码")
    public Response<StaffDto> password(@PathVariable String originalPassword,@PathVariable String password) {
        StaffDto result = staffBusiness.modif(originalPassword,password);
        return new Response<>(result);
    }

    @GetMapping("/{id}")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "员工id", required = true)
    @ApiOperation(value = "通过员工id获取员工")
    public Response<StaffDto> fetchById(@PathVariable Long id) {
        StaffDto respone = staffBusiness.findById(id);
        return new Response<>(respone);
    }
}
