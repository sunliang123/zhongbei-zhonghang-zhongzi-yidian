package com.waben.stock.applayer.promotion.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.promotion.business.OrganizationBusiness;
import com.waben.stock.applayer.promotion.business.RoleBusiness;
import com.waben.stock.applayer.promotion.business.UserBusiness;
import com.waben.stock.applayer.promotion.security.SecurityUtil;
import com.waben.stock.interfaces.dto.manage.RoleDto;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.dto.organization.UserDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.UserQuery;
import com.waben.stock.interfaces.request.organization.UserRequest;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.vo.manage.RoleVo;
import com.waben.stock.interfaces.vo.organization.OrganizationVo;
import com.waben.stock.interfaces.vo.organization.UserVo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController("promotionUserController")
@RequestMapping("/user")
//@Api(description = "管理员")
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserBusiness userBusiness;

    @Autowired
    private RoleBusiness roleBusiness;

    @Autowired
    private OrganizationBusiness organizationBusiness;

    @Value("${onlystockoption:false}")
    private boolean onlyStockoption;

    @RequestMapping(value = "/role",method = RequestMethod.GET)
    @ApiOperation(value = "获取角色")
    public Response<List<RoleVo>> fetchRoleByOrganization(){
        List<RoleDto> roleDtos = roleBusiness.findByOrganization(SecurityUtil.getUserDetails().getOrgId());
        List<RoleVo> roleVos = CopyBeanUtils.copyListBeanPropertiesToList(roleDtos,RoleVo.class);
        return new Response<>(roleVos);
    }

    @RequestMapping(value = "/org",method = RequestMethod.GET)
    @ApiOperation(value = "获取机构")
    public Response<List<OrganizationDto>> fetchOrgByParentOrg(){
        List<OrganizationDto> organizationDtos = organizationBusiness.listByParentId(SecurityUtil.getUserDetails().getOrgId());
        OrganizationDto organizationDto = organizationBusiness.findByCode(SecurityUtil.getUserDetails().getOrgCode());
        organizationDtos.add(organizationDto);
        return new Response<>(organizationDtos);
    }

    @ApiOperation(value = "添加管理员")
    @ApiImplicitParam(paramType = "query", dataType = "UserRequest", name = "request", value = "管理员对象", required = true)
    @RequestMapping(value = "/",method = RequestMethod.POST)
     public Response<UserVo> add(UserRequest request){
        UserDto requestDto = CopyBeanUtils.copyBeanProperties(UserDto.class, request, false);
        UserDto userDto = userBusiness.save(requestDto);
        UserVo userVo = CopyBeanUtils.copyBeanProperties(UserVo.class,userDto , false);
        return new Response<>(userVo);
    }

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    @ApiOperation(value = "修改管理员")
    @ApiImplicitParam(paramType = "query", dataType = "UserRequest", name = "request", value = "管理员对象", required = true)
    public Response<UserVo> modify(UserRequest request){
        UserDto requestDto = CopyBeanUtils.copyBeanProperties(UserDto.class, request, false);
        UserDto userDto = userBusiness.revision(requestDto);
        UserVo userVo = CopyBeanUtils.copyBeanProperties(UserVo.class,userDto , false);
        return new Response<>(userVo);
    }

    @RequestMapping(value = "/state/{id}",method = RequestMethod.PUT)
    @ApiOperation(value = "冻结/恢复 管理员")
    @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "管理员id", required = true)
    public Response<UserVo> modifyState(@PathVariable Long id){
        UserDto userDto = userBusiness.revisionState(id);
        UserVo userVo = CopyBeanUtils.copyBeanProperties(UserVo.class,userDto , false);
        return new Response<>(userVo);
    }

    @RequestMapping(value = "/pages",method = RequestMethod.GET)
    @ApiImplicitParam(paramType = "query", dataType = "UserQuery", name = "query", value = "管理员查询对象", required = false)
    @ApiOperation(value = "管理员分页")
    public Response<PageInfo<UserVo>> pages(UserQuery query) {
        PageInfo<UserDto> pageInfo = userBusiness.pages(query);
        List<UserVo> userVoContent = CopyBeanUtils.copyListBeanPropertiesToList(pageInfo.getContent(), UserVo.class);
        PageInfo<UserVo> response = new PageInfo<>(userVoContent, pageInfo.getTotalPages(), pageInfo.getLast(), pageInfo.getTotalElements(), pageInfo.getSize(), pageInfo.getNumber(), pageInfo.getFrist());
        for (int i = 0; i < pageInfo.getContent().size(); i++) {
            OrganizationVo organizationVo = CopyBeanUtils.copyBeanProperties(
                    OrganizationVo.class, pageInfo.getContent().get(i).getOrg(), false);
            userVoContent.get(i).setOrgName(organizationVo.getName());
            userVoContent.get(i).setOrgId(organizationVo.getId());
            Long role = pageInfo.getContent().get(i).getRole();
            if(role!=null) {
                RoleDto roleDto = roleBusiness.findById(role);
//                logger.info("结果：{}", JacksonUtil.encode(roleDto));
//                logger.info("role：{}", role);
                userVoContent.get(i).setRoleName(roleDto.getName());
                userVoContent.get(i).setRole(roleDto.getId());
            }
        }
        return new Response<>(response);
    }
    
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
	public Response<Void> modifyPassword(String oldPassword, String password) {
	  	userBusiness.modifyPassword(SecurityUtil.getUserId(), oldPassword, password);
		return new Response<>();
	}

}
