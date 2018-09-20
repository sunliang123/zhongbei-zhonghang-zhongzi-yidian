package com.waben.stock.applayer.promotion.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.promotion.business.OrganizationAccountFlowBusiness;
import com.waben.stock.applayer.promotion.business.OrganizationBusiness;
import com.waben.stock.applayer.promotion.security.SecurityUtil;
import com.waben.stock.applayer.promotion.util.PoiUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.organization.OrganizationAccountFlowWithTradeInfoDto;
import com.waben.stock.interfaces.enums.OrganizationAccountFlowType;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.OrganizationAccountFlowQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("promotionOrganizationAccountFlowController")
@RequestMapping("/orgflow")
@Api(description = "结算管理")
public class OrganizationAccountFlowController {

	org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public OrganizationAccountFlowBusiness organizationAccountFlowBusiness;

	@Autowired
	public OrganizationBusiness organizationBusiness;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/pagesWithTradeInfo", method = RequestMethod.GET)
	public Response<PageInfo<OrganizationAccountFlowWithTradeInfoDto>> pagesWithTradeInfo(
			OrganizationAccountFlowQuery query) {
		query.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		return new Response<>(organizationAccountFlowBusiness.pagesWithTradeInfo(query));
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ApiOperation(value = "queryType=1 导出资金流水 queryType=2 导出佣金结算")
	public void export(OrganizationAccountFlowQuery query, HttpServletResponse svrResponse) {
		query.setPage(0);
		query.setSize(Integer.MAX_VALUE);
		// query.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		PageInfo<OrganizationAccountFlowWithTradeInfoDto> result = organizationAccountFlowBusiness
				.pagesWithTradeInfo(query);
		File file = null;
		FileInputStream is = null;
		String fileName = "";
		try {
			// query.getQueryType() = 1 ? 资金流水 ： 佣金结算
			if (query.getQueryType() != null && query.getQueryType() == 1) {
				fileName = "capitalflow__" + String.valueOf(System.currentTimeMillis());
				file = File.createTempFile(fileName, ".xls");
				List<String> columnDescList = columnDescList();
				List<List<String>> dataList = dataList(result.getContent());
				PoiUtil.writeDataToExcel("资金流水", file, columnDescList, dataList);
			} else {
				fileName = "commission__" + String.valueOf(System.currentTimeMillis());
				file = File.createTempFile(fileName, ".xls");
				List<String> columnDescList = columnCommList();
				List<List<String>> dataList = dataCommList(result.getContent());
				PoiUtil.writeDataToExcel("佣金结算", file, columnDescList, dataList);
			}

			is = new FileInputStream(file);
			svrResponse.setContentType("application/vnd.ms-excel");
			svrResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
			IOUtils.copy(is, svrResponse.getOutputStream());
			svrResponse.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "导出代理商数据到excel异常：" + e.getMessage());
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

	// 资金流水
	private List<List<String>> dataList(List<OrganizationAccountFlowWithTradeInfoDto> content) {
		List<List<String>> result = new ArrayList<>();
		for (OrganizationAccountFlowWithTradeInfoDto trade : content) {
			List<String> data = new ArrayList<>();
			data.add(String.valueOf(trade.getResourceTradeNo() == null ? "" : trade.getResourceTradeNo()));
			data.add(trade.getOccurrenceTime() != null ? sdf.format(trade.getOccurrenceTime()) : "");
			data.add(trade.getType() != null
					? OrganizationAccountFlowType.getByIndex(String.valueOf(trade.getType())).getType() : "");
			data.add(String.valueOf(trade.getAmount() == null ? "" : trade.getAmount()));
			data.add(String.valueOf(trade.getAvailableBalance() == null ? "" : trade.getAvailableBalance()));
			data.add(trade.getStockCode() == null ? "" : trade.getStockCode());
			data.add(trade.getStockName() == null ? "" : trade.getStockName());
			data.add(trade.getOrgCode() + "/" + trade.getOrgName());
			result.add(data);
		}
		return result;
	}

	private List<String> columnDescList() {
		List<String> result = new ArrayList<>();
		result.add("订单编号");
		result.add("交易时间");
		result.add("交易类型");
		result.add("交易金额");
		result.add("账户余额");
		result.add("股票代码");
		result.add("标的股票");
		result.add("所属代理商代码/名称");
		return result;
	}

	// 佣金结算
	private List<List<String>> dataCommList(List<OrganizationAccountFlowWithTradeInfoDto> content) {
		List<List<String>> result = new ArrayList<>();
		for (OrganizationAccountFlowWithTradeInfoDto trade : content) {
			List<String> data = new ArrayList<>();
			data.add(String.valueOf(trade.getResourceTradeNo() == null ? "" : trade.getResourceTradeNo()));
			data.add(trade.getFlowNo() == null ? "" : trade.getFlowNo());
			data.add(trade.getPublisherName() == null ? "" : trade.getPublisherName());
			data.add(trade.getPublisherPhone() == null ? "" : trade.getPublisherPhone());
			data.add(trade.getStockCode() + "/" + trade.getStockName());
			data.add(trade.getCycleName() == null ? "" : trade.getCycleName());
			data.add(String.valueOf(trade.getOriginAmount() == null ? "" : trade.getOriginAmount())); // 原始收入
			data.add(String.valueOf(trade.getAmount() == null ? "" : trade.getAmount())); // 分成收入
			data.add(trade.getOccurrenceTime() != null ? sdf.format(trade.getOccurrenceTime()) : "");
			data.add(trade.getOrgCode() + "/" + trade.getOrgName());
			result.add(data);
		}
		return result;
	}

	private List<String> columnCommList() {
		List<String> result = new ArrayList<>();
		result.add("订单编号");
		result.add("流水号");
		result.add("客户姓名");
		result.add("客户账号");
		result.add("股票代码/名称");
		result.add("行权周期");
		result.add("权利金原始收入");
		result.add("分成收入");
		result.add("流水时间");
		result.add("所属代理商代码/名称");
		return result;
	}

}
