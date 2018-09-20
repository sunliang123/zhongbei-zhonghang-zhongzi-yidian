package com.waben.stock.datalayer.organization.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.datalayer.organization.entity.Organization;
import com.waben.stock.datalayer.organization.repository.OrganizationDao;
import com.waben.stock.datalayer.organization.service.OrganizationService;
import com.waben.stock.interfaces.dto.organization.OrganizationAccountDto;
import com.waben.stock.interfaces.dto.organization.OrganizationDetailDto;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.dto.organization.OrganizationStaDto;
import com.waben.stock.interfaces.dto.organization.TradingFowDto;
import com.waben.stock.interfaces.dto.organization.TreeNode;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.form.organization.OrganizationForm;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.OrganizationQuery;
import com.waben.stock.interfaces.pojo.query.organization.OrganizationStaQuery;
import com.waben.stock.interfaces.pojo.query.organization.TradingFowQuery;
import com.waben.stock.interfaces.service.organization.OrganizationInterface;
import com.waben.stock.interfaces.service.publisher.BindCardInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.PageToPageInfo;

import io.swagger.annotations.ApiOperation;

/**
 * 机构 Controller
 *
 * @author luomengan
 */
// @RestController
// @RequestMapping("/organization")
// @Api(description = "机构接口列表")
@Component
public class OrganizationController implements OrganizationInterface {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public OrganizationService organizationService;

	@Autowired
	private BindCardInterface bindCardReference;

	@Autowired
	private OrganizationDao organizationDao;

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取机构")
	public Response<Organization> fetchById(@PathVariable Long id) {
		return new Response<>(organizationService.getOrganizationInfo(id));
	}

	@GetMapping("/page")
	@ApiOperation(value = "获取机构分页数据")
	public Response<Page<Organization>> organizations(int page, int limit) {
		return new Response<>((Page<Organization>) organizationService.organizations(page, limit));
	}

	@GetMapping("/list")
	@ApiOperation(value = "获取机构列表")
	public Response<List<Organization>> list() {
		return new Response<>(organizationService.list());
	}

	/******************************** 后台管理 **********************************/

	@PutMapping("/")
	@ApiOperation(value = "修改机构", hidden = true)
	public Response<Organization> modification(Organization organization) {
		return new Response<>(organizationService.modifyOrganization(organization));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除机构", hidden = true)
	public Response<Long> delete(@PathVariable Long id) {
		organizationService.deleteOrganization(id);
		return new Response<Long>(id);
	}

	@PostMapping("/deletes")
	@ApiOperation(value = "批量删除机构（多个id以逗号分割）", hidden = true)
	public Response<Boolean> deletes(String ids) {
		organizationService.deleteOrganizations(ids);
		return new Response<Boolean>(true);
	}

	@GetMapping("/adminList")
	@ApiOperation(value = "获取机构列表(后台管理)", hidden = true)
	public Response<List<Organization>> adminList() {
		return new Response<>(organizationService.list());
	}

	@Override
	public Response<OrganizationDto> addition(@RequestBody OrganizationForm orgForm) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(OrganizationDto.class,
				organizationService.addOrganization(orgForm), false));
	}

	@Override
	public Response<PageInfo<OrganizationDto>> adminPage(@RequestBody OrganizationQuery query) {
		Page<Organization> page = organizationService.pagesByQuery(query);
		List<Organization> organizations = page.getContent();
		List<Organization> content = new ArrayList<>();
		for (Organization organization : organizations) {
			OrganizationAccountDto organizationAccountDto = CopyBeanUtils
					.copyBeanProperties(OrganizationAccountDto.class, organization.getAccount(), false);
			organization.setAccountDto(organizationAccountDto);
			content.add(organization);
		}
		List<OrganizationDto> organizationDtos = CopyBeanUtils.copyListBeanPropertiesToList(content,
				OrganizationDto.class);
		PageInfo<OrganizationDto> result = PageToPageInfo.pageToPageInfo(organizationDtos, page.getTotalPages(),
				page.isLast(), page.getTotalElements(), page.getSize(), page.getNumber(), page.isFirst());
		System.out.println(JacksonUtil.encode(result));
		return new Response<>(result);
	}

	@Override
	public Response<PageInfo<OrganizationDto>> pages(@RequestBody OrganizationQuery query) {
		Page<Organization> page = organizationService.pagesByOrgQuery(query);
		List<Organization> organizations = page.getContent();
		List<Organization> content = new ArrayList<>();
		for (Organization organization : organizations) {
			OrganizationAccountDto organizationAccountDto = CopyBeanUtils
					.copyBeanProperties(OrganizationAccountDto.class, organization.getAccount(), false);
			organization.setAccountDto(organizationAccountDto);
			content.add(organization);
		}
		List<OrganizationDto> organizationDtos = CopyBeanUtils.copyListBeanPropertiesToList(content,
				OrganizationDto.class);
		PageInfo<OrganizationDto> result = PageToPageInfo.pageToPageInfo(organizationDtos, page.getTotalPages(),
				page.isLast(), page.getTotalElements(), page.getSize(), page.getNumber(), page.isFirst());
		return new Response<>(result);
	}

	@Override
	public List<TreeNode> adminTree(@RequestParam(required = true) Long orgId) {
		return organizationService.adminTree(orgId);
	}

	@Override
	public Response<List<OrganizationDto>> listByParentId(
			@RequestParam(required = true, defaultValue = "0") Long parentId) {
		return new Response<>(CopyBeanUtils.copyListBeanPropertiesToList(organizationService.listByParentId(parentId),
				OrganizationDto.class));
	}

	@Override
	public Response<List<OrganizationDto>> fetchAll() {
		return new Response<>(
				CopyBeanUtils.copyListBeanPropertiesToList(organizationService.findAll(), OrganizationDto.class));
	}

	@Override
	public Response<OrganizationDetailDto> detail(Long orgId) {
		return new Response<>(organizationService.detail(orgId));
	}

	@Override
	public Response<OrganizationDto> modifyName(Long id, String name, BigDecimal billCharge, Integer settlementType) {
		return new Response<>(CopyBeanUtils.copyBeanProperties(OrganizationDto.class,
				organizationService.modifyName(id, name, billCharge, settlementType), false));
	}

	@Override
	public Response<BindCardDto> fetchBindCard(@PathVariable Long orgId) {
		return new Response<>(organizationService.getBindCard(orgId));
	}

	@Override
	public Response<BindCardDto> saveBindCard(@PathVariable Long orgId, @RequestBody BindCardDto bindCardDto) {
		return new Response<>(organizationService.bindCard(orgId, bindCardDto));
	}

	@Override
	public Response<OrganizationDto> fetchByCode(@PathVariable String code) {
		return new Response<>(
				CopyBeanUtils.copyBeanProperties(OrganizationDto.class, organizationService.findByCode(code), false));
	}

	@Override
	public Response<PageInfo<OrganizationDetailDto>> adminAgentPageByQuery(@RequestBody OrganizationQuery query) {
		Page<OrganizationDetailDto> page = organizationService.adminPagesByQuery(query);
		PageInfo<OrganizationDetailDto> result = PageToPageInfo.pageToPageInfo(page, OrganizationDetailDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<PageInfo<OrganizationStaDto>> adminStaPageByQuery(@RequestBody OrganizationStaQuery query) {
		Page<OrganizationStaDto> page = organizationService.adminStaPagesByQuery(query);
		PageInfo<OrganizationStaDto> result = PageToPageInfo.pageToPageInfo(page, OrganizationStaDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<PageInfo<TradingFowDto>> tradingFowPageByQuery(@RequestBody TradingFowQuery query) {
		Page<TradingFowDto> page = organizationService.tradingFowPageByQuery(query);
		PageInfo<TradingFowDto> result = PageToPageInfo.pageToPageInfo(page, TradingFowDto.class);
		return new Response<>(result);
	}

	@Override
	public Response<OrganizationDto> fetchByOrgId(Long id) {
		return new Response<>(
				CopyBeanUtils.copyBeanProperties(OrganizationDto.class, organizationDao.findByOrgId(id), false));
	}

}
