package com.waben.stock.datalayer.organization.controller;

import java.util.List;

import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.OrganizationAccountQuery;
import com.waben.stock.interfaces.util.PageToPageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.waben.stock.datalayer.organization.entity.OrganizationAccount;
import com.waben.stock.datalayer.organization.service.OrganizationAccountService;
import com.waben.stock.interfaces.dto.organization.OrganizationAccountDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.organization.OrganizationAccountInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 机构账户 Controller
 * 
 * @author luomengan
 *
 */
// @RestController
// @RequestMapping("/organizationAccount")
// @Api(description = "机构账户接口列表")
@Component
public class OrganizationAccountController implements OrganizationAccountInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public OrganizationAccountService organizationAccountService;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取机构账户")
	public Response<OrganizationAccount> fetchById(@PathVariable Long id) {
		return new Response<>(organizationAccountService.getOrganizationAccountInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取机构账户分页数据")
	public Response<Page<OrganizationAccount>> organizationAccounts(int page, int limit) {
		return new Response<>((Page<OrganizationAccount>) organizationAccountService.organizationAccounts(page, limit));
	}

	@Override
	public Response<PageInfo<OrganizationAccountDto>> pages(@RequestBody OrganizationAccountQuery query) {
		Page<OrganizationAccount> page = organizationAccountService.pagesByQuery(query);
		PageInfo<OrganizationAccountDto> result = PageToPageInfo.pageToPageInfo(page, OrganizationAccountDto.class);
		for (int i=0; i<page.getContent().size(); i++) {
			Organization org = page.getContent().get(i).getOrg();
			OrganizationDto organizationDto = CopyBeanUtils.copyBeanProperties(OrganizationDto.class, org, false);
			result.getContent().get(i).setOrg(organizationDto);
		}
		return new Response<>(result);
	}

	@Override
	public Response<OrganizationAccountDto> modifyState(@PathVariable Long id,@PathVariable Integer state) {
		OrganizationAccount result = organizationAccountService.revisionState(id,state);
		OrganizationAccountDto response = CopyBeanUtils.copyBeanProperties(OrganizationAccountDto.class, result, false);
		return new Response<>(response);
	}

	@Override
	public Response<OrganizationAccountDto> recover(@PathVariable Long id) {
		OrganizationAccount result = organizationAccountService.recover(id);
		OrganizationAccountDto response = CopyBeanUtils.copyBeanProperties(OrganizationAccountDto.class, result, false);
		return new Response<>(response);	}

	@Override
	public Response<OrganizationAccountDto> freeze(@RequestBody OrganizationAccountDto accountDto) {
		OrganizationAccount organizationAccount = CopyBeanUtils.copyBeanProperties(OrganizationAccount.class,
				accountDto, false);
		OrganizationAccount result = organizationAccountService.freeze(organizationAccount);
		OrganizationAccountDto response = CopyBeanUtils.copyBeanProperties(OrganizationAccountDto.class, result, false);
		return new Response<>(response);
	}


	@Override
	public Response<OrganizationAccountDto> fetchByOrgId(@PathVariable Long orgId) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(OrganizationAccountDto.class,
				organizationAccountService.getByOrgId(orgId), false));
	}
	@Override
	public Response<List<OrganizationAccountDto>> list() {
		return new Response<>(CopyBeanUtils.copyListBeanPropertiesToList(organizationAccountService.list(),
				OrganizationAccountDto.class));
	}

	/******************************** 后台管理 **********************************/

	@PostMapping("/")
	@ApiOperation(value = "添加机构账户", hidden = true)
	public Response<OrganizationAccount> addition(OrganizationAccount organizationAccount) {
		return new Response<>(organizationAccountService.addOrganizationAccount(organizationAccount));
	}

	@PutMapping("/")
	@ApiOperation(value = "修改机构账户", hidden = true)
	public Response<OrganizationAccount> modification(OrganizationAccount organizationAccount) {
		return new Response<>(organizationAccountService.modifyOrganizationAccount(organizationAccount));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除机构账户", hidden = true)
	public Response<Long> delete(@PathVariable Long id) {
		organizationAccountService.deleteOrganizationAccount(id);
		return new Response<Long>(id);
	}

	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除机构账户（多个id以逗号分割）", hidden = true)
	public Response<Boolean> deletes(String ids) {
		organizationAccountService.deleteOrganizationAccounts(ids);
		return new Response<Boolean>(true);
	}

	@GetMapping("/adminList")
	@ApiOperation(value = "获取机构账户列表(后台管理)", hidden = true)
	public Response<List<OrganizationAccount>> adminList() {
		return new Response<>(organizationAccountService.list());
	}

	@Override
	public Response<Void> modifyPaymentPassword(@PathVariable Long orgId, String oldPaymentPassword, String paymentPassword) {
		organizationAccountService.modifyPaymentPassword(orgId, oldPaymentPassword, paymentPassword);
		return new Response<>();
	}

}
