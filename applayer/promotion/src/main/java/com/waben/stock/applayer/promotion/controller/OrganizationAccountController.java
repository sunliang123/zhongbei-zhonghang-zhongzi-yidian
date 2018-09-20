package com.waben.stock.applayer.promotion.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.promotion.business.BindCardBusiness;
import com.waben.stock.applayer.promotion.business.OrganizationAccountBusiness;
import com.waben.stock.applayer.promotion.business.OrganizationBusiness;
import com.waben.stock.applayer.promotion.business.OrganizationPublisherBusiness;
import com.waben.stock.applayer.promotion.security.SecurityUtil;
import com.waben.stock.applayer.promotion.util.PoiUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.organization.OrganizationAccountDto;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.dto.organization.OrganizationPublisherDto;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.OrganizationAccountQuery;
import com.waben.stock.interfaces.request.organization.OrganizationAccountRequest;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.vo.organization.OrganizationAccountVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 机构账户 Controller
 * 
 * @author luomengan
 *
 */
@RestController("promotionOrganizationAccountController")
@RequestMapping("/organizationAccount")
@Api(description = "代理商资产接口列表")
public class OrganizationAccountController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public OrganizationAccountBusiness accountBusiness;

	@Autowired
	private OrganizationBusiness organizationBusiness;

	@Autowired
	private OrganizationPublisherBusiness publisherBusiness;

	@Autowired
	private BindCardBusiness bindCardBusiness;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/modifyPaymentPassword", method = RequestMethod.PUT)
	public Response<Void> modifyPaymentPassword(String oldPaymentPassword, String paymentPassword) {
		accountBusiness.modifyPaymentPassword(SecurityUtil.getUserDetails().getOrgId(), oldPaymentPassword,
				paymentPassword);
		return new Response<>();
	}

	@RequestMapping(value = "/orgId/{orgId}", method = RequestMethod.GET)
	public Response<OrganizationAccountDto> fetchByOrgId(@PathVariable("orgId") Long orgId) {
		return new Response<>(accountBusiness.fetchByOrgId(orgId));
	}

	@RequestMapping(value = "/pages", method = RequestMethod.GET)
	@ApiImplicitParam(paramType = "query", dataType = "OrganizationAccountQuery", name = "query", value = "代理商资产查询对象", required = false)
	@ApiOperation(value = "代理商资产分页")
	public Response<PageInfo<OrganizationAccountVo>> pages(OrganizationAccountQuery query) {
		long start = System.currentTimeMillis();
		// if (!StringUtil.isEmpty(query.getOrgName())) {
		// BindCardDto orgBindCard =
		// bindCardBusiness.findOrgBindCardByName(query.getOrgName());
		// if (orgBindCard != null) {
		// query.setOrgId(orgBindCard.getResourceId());
		// }
		// }
		PageInfo<OrganizationAccountDto> pageInfo = accountBusiness.pages(query);
		List<OrganizationAccountVo> roleVoContent = CopyBeanUtils.copyListBeanPropertiesToList(pageInfo.getContent(),
				OrganizationAccountVo.class);
		PageInfo<OrganizationAccountVo> response = new PageInfo<>(roleVoContent, pageInfo.getTotalPages(),
				pageInfo.getLast(), pageInfo.getTotalElements(), pageInfo.getSize(), pageInfo.getNumber(),
				pageInfo.getFrist());
		for (int i = 0; i < pageInfo.getContent().size(); i++) {

			OrganizationDto organizationDto = pageInfo.getContent().get(i).getOrg();
			response.getContent().get(i).setCode(organizationDto.getCode());
			response.getContent().get(i).setName(organizationDto.getName());
			response.getContent().get(i).setLevel(organizationDto.getLevel());
			if (organizationDto.getLevel() == 1) {
				response.getContent().get(i).setChildOrgCount(organizationBusiness.findAll().size() - 1);
				response.getContent().get(i).setPopPulisherCount(publisherBusiness.findAll().size());
			} else {
				List<OrganizationDto> organizationDtos = organizationBusiness.listByParentId(organizationDto.getId());
				response.getContent().get(i).setChildOrgCount(organizationDtos.size());
				List<OrganizationPublisherDto> organizationPublisherDtos = publisherBusiness
						.findOrganizationPublishersByCode(organizationDto.getCode());
				response.getContent().get(i).setPopPulisherCount(organizationPublisherDtos.size());
			}
			BindCardDto bindCardDto = bindCardBusiness.getOrgBindCard(organizationDto.getId());
			if (bindCardDto != null) {
				response.getContent().get(i).setOrgPhone(bindCardDto.getPhone());
				response.getContent().get(i).setOrgName(bindCardDto.getName());
			}
		}
		long end = System.currentTimeMillis();
		logger.info("用时：{}", end - start);
		return new Response<>(response);
	}

	// @RequestMapping(value = "/state/{id}", method = RequestMethod.PUT)
	// @ApiImplicitParams({@ApiImplicitParam(paramType = "path", dataType =
	// "Long", name = "id", value = "代理商资产id", required =
	// true),@ApiImplicitParam(paramType = "path", dataType = "Integer", name =
	// "state", value = "代理商资产状态（1正常，2冻结）", required = true)})
	// @ApiOperation(value = "修改代理商资产状态")
	// public Response<OrganizationAccountDto> modifyState(@PathVariable Long
	// id, @PathVariable Integer state) {
	// OrganizationAccountDto result = accountBusiness.revisionState(id,state);
	// return new Response<>(result);
	// }

	@ApiImplicitParam(paramType = "query", dataType = "OrganizationAccountRequest", name = "request", value = "代理商资产对象", required = true)
	@ApiOperation(value = "冻结")
	@RequestMapping(value = "/freeze", method = RequestMethod.PUT)
	public Response<OrganizationAccountDto> freeze(OrganizationAccountRequest request) {
		OrganizationAccountDto organizationAccountDto = CopyBeanUtils.copyBeanProperties(OrganizationAccountDto.class,
				request, false);
		OrganizationAccountDto result = accountBusiness.freeze(organizationAccountDto);
		return new Response<>(result);
	}

	@RequestMapping(value = "/state/{id}", method = RequestMethod.PUT)
	@ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "代理商资产id", required = true)
	@ApiOperation(value = "解冻")
	public Response<OrganizationAccountDto> recover(@PathVariable Long id) {
		OrganizationAccountDto result = accountBusiness.recover(id);
		return new Response<>(result);
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ApiOperation(value = "导出代理资产数据")
	public void export(OrganizationAccountQuery query, HttpServletResponse svrResponse) {
		query.setPage(0);
		query.setSize(Integer.MAX_VALUE);
		// if (!StringUtil.isEmpty(query.getOrgName())) {
		// BindCardDto orgBindCard =
		// bindCardBusiness.findOrgBindCardByName(query.getOrgName());
		// if (orgBindCard != null) {
		// query.setOrgId(orgBindCard.getResourceId());
		// }
		// }
		PageInfo<OrganizationAccountDto> pageInfo = accountBusiness.pages(query);
		List<OrganizationAccountVo> roleVoContent = CopyBeanUtils.copyListBeanPropertiesToList(pageInfo.getContent(),
				OrganizationAccountVo.class);
		PageInfo<OrganizationAccountVo> response = new PageInfo<>(roleVoContent, pageInfo.getTotalPages(),
				pageInfo.getLast(), pageInfo.getTotalElements(), pageInfo.getSize(), pageInfo.getNumber(),
				pageInfo.getFrist());
		for (int i = 0; i < pageInfo.getContent().size(); i++) {

			OrganizationDto organizationDto = pageInfo.getContent().get(i).getOrg();
			response.getContent().get(i).setCode(organizationDto.getCode());
			response.getContent().get(i).setName(organizationDto.getName());
			response.getContent().get(i).setLevel(organizationDto.getLevel());
			if (organizationDto.getLevel() == 1) {
				response.getContent().get(i).setChildOrgCount(organizationBusiness.findAll().size() - 1);
				response.getContent().get(i).setPopPulisherCount(publisherBusiness.findAll().size());
			} else {
				List<OrganizationDto> organizationDtos = organizationBusiness.listByParentId(organizationDto.getId());
				response.getContent().get(i).setChildOrgCount(organizationDtos.size());
				List<OrganizationPublisherDto> organizationPublisherDtos = publisherBusiness
						.findOrganizationPublishersByCode(organizationDto.getCode());
				response.getContent().get(i).setPopPulisherCount(organizationPublisherDtos.size());
			}
			BindCardDto bindCardDto = bindCardBusiness.getOrgBindCard(organizationDto.getId());
			if (bindCardDto != null) {
				response.getContent().get(i).setOrgPhone(bindCardDto.getPhone());
				response.getContent().get(i).setOrgName(bindCardDto.getName());
			}
		}

		File file = null;
		FileInputStream is = null;
		try {
			String fileName = "account_" + String.valueOf(System.currentTimeMillis());
			file = File.createTempFile(fileName, ".xls");
			List<String> columnDescList = columnDescList();
			List<List<String>> dataList = dataList(response.getContent());
			PoiUtil.writeDataToExcel("代理资产数据", file, columnDescList, dataList);

			is = new FileInputStream(file);
			svrResponse.setContentType("application/vnd.ms-excel");
			svrResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
			IOUtils.copy(is, svrResponse.getOutputStream());
			svrResponse.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "导出代理资产数据到excel异常：" + e.getMessage());
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
			if (file != null) {
				file.delete();
			}
		}
	}

	private List<List<String>> dataList(List<OrganizationAccountVo> content) {
		List<List<String>> result = new ArrayList<>();
		for (OrganizationAccountVo trade : content) {
			String state = "正常";
			Integer ste = trade.getState();
			if (ste != null && ste == 2) {
				state = "冻结";
			}
			List<String> data = new ArrayList<>();
			data.add(String.valueOf(trade.getId() == null ? "" : trade.getId()));
			data.add(trade.getCode() == null ? "" : trade.getCode());
			data.add(trade.getName() == null ? "" : trade.getName());
			data.add(String.valueOf(trade.getLevel() == null ? "" : (trade.getLevel() + "级")));
			data.add(String.valueOf(trade.getChildOrgCount() == null ? "" : trade.getChildOrgCount()));
			data.add(String.valueOf(trade.getPopPulisherCount() == null ? "" : trade.getPopPulisherCount()));
			data.add(trade.getOrgName() == null ? "" : trade.getOrgName());
			data.add(trade.getOrgPhone() == null ? "" : trade.getOrgPhone());
			data.add(String.valueOf(trade.getAvailableBalance() == null ? "" : trade.getAvailableBalance()));
			data.add(String.valueOf(trade.getFrozenCapital() == null ? "" : trade.getFrozenCapital()));
			data.add(trade.getUpdateTime() != null ? sdf.format(trade.getUpdateTime()) : "");
			data.add(state);
			result.add(data);
		}
		return result;
	}

	private List<String> columnDescList() {
		List<String> result = new ArrayList<>();
		result.add("代理商ID");
		result.add("代理商代码");
		result.add("代理商名称");
		result.add("代理商层级");
		result.add("下线代理");
		result.add("推广客户");
		result.add("代理商姓名");
		result.add("手机号");
		result.add("账户余额");
		result.add("冻结金额");
		result.add("更新时间");
		result.add("资产状态");
		return result;
	}

}
